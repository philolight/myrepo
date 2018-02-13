package com.lge.framework.ceasar.startable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.entity.StartableEntity;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;
import com.lge.sm.cr_data_store.repository.StartableHistoryRepository;
import com.lge.sm.cr_data_store.repository.StartableRepository;

/**
 * Starter는 서비스 제공에 앞서 시스템 구동시에 실행시켜야 할 객체들을 핸들링하는 클래스이다.
 * 시스템 구동시에 실행되어야 하는 객체들은 Startable 인터페이스를 상속 받은 객체들이다.
 * Startable 객체들은 객체 생성과 함께  Starter.add(this) 메소드를 호출하여 Starter가 실행되기 전에 스스로를 Starter에 등록 시킨다.
 * 이후 Starter는 Application의 명령에 따라 각 Startable들의 init(), start() 메소드들을 차례로 실행하여 서비스를 수행할 수 있는 상태로 만든다.
 */

public class Starter {
	private static final String TAG = Starter.class.getSimpleName();
	
	private static Map<StartableType, List<Startable>> map = new HashMap<>();
	private static Map<String, StartableEntity> entityMap = new HashMap<>();
	private static Map<String, StartableHistoryDto> historyMap = new HashMap<>();
	
	public static void add(Startable startable) {
		List<Startable> startables = map.get(startable.getStartableType());
		if(startables == null) {
			startables = new ArrayList<>();
			map.put(startable.getStartableType(), startables);
		}
		startables.add(startable);
	}
	
	/** Startable의 init()를 호출하여 객체를 초기화시키는 메소드. */
	public boolean init() {
		Repos.repo(StartableRepository.class).init(); // StartableRepository는 우선 이용해야 하므로 미리 초기화 한다.
				
		List<StartableEntity> entities = Repos.repo(StartableRepository.class).getAll();
		for(StartableEntity each : entities) {
			each.setStatus(StartableStatus.NOT_DEFINED.toString());
			entityMap.put(each.getStartableId(), each);
		}
		
		/** 한번도 DB에 등록되지 않은 서비스들을 DB에 등록함 */
		for(List<Startable> list : map.values()) {
			for(Startable each : list) {
				if(entityMap.containsKey(each.getStartableId()) == false) {
					StartableDto dto = new StartableDto();
					dto.setCdate(DateStringUtil.getCurrentDateString(TimeZone.getDefault().getID())); // TODO : db time now 로 바꿔야 함.
					dto.setStartableId(each.getStartableId());
					dto.setStatus(StartableStatus.NOT_DEFINED.toString());
					dto.setType(each.getStartableType().toString());
					StartableEntity newEntity = Repos.repo(StartableRepository.class).create(dto);
					entityMap.put(each.getStartableId(), newEntity);
				}
				
				StartableHistoryDto h = new StartableHistoryDto();
				h.setCdate(DateStringUtil.getCurrentDateString(TimeZone.getDefault().getID())); // TODO : db time now 로 바꿔야 함.
				h.setReport("");
				h.setStartableId(each.getStartableId());
				h.setStartDate(DateStringUtil.getCurrentDateString(TimeZone.getDefault().getID()));
				h.setStartSucceed(0);
				h.setLatency(0L);
				historyMap.put(each.getStartableId(), h);
			}
		}
		
		boolean ret = true;
		for(StartableType each : StartableType.values()){
			if(map.containsKey(each) == false) continue;
			if(init(map.get(each)) == false) ret = false;
		}
		
		updateStartableDB();
		
		return ret;
	}
	
	/** Startable의 start()를 호출하여 객체를 시작시키는 메소드. */
	public boolean start() {		
		boolean ret = true;
		
		for(StartableType each : StartableType.values()){
			if(map.containsKey(each) == false) continue;
			if(start(map.get(each)) == false) ret = false;
		}
		
		updateStartableDB();
		updateStartableHistoryDB();
		return ret;
	}
	
	/** Startable의 stop()을 호출하여 객체를 종료시키는 메소드. */
	public boolean stop() {
		boolean ret = true;
		for(StartableType each : StartableType.values()){
			if(map.containsKey(each) == false) continue;
			if(stop(map.get(each)) == false) ret = false;
		}
		
		updateStartableDB();
		
		return ret;
	}
	
	private boolean init(List<Startable> list) {
		for(Startable each : list) {
			long start = System.currentTimeMillis();
			if(each.init() == false) {
				entityMap.get(each.getStartableId()).setStatus(StartableStatus.FAULT.toString());
				Logger.error(TAG, "init failed : " + each.getClass().getSimpleName());
				return false;
			}
			long end = System.currentTimeMillis();
			each.setStatus(StartableStatus.INITIALIZED);
			entityMap.get(each.getStartableId()).setStatus(StartableStatus.INITIALIZED.toString());
			historyMap.get(each.getStartableId()).setLatency(end - start);
			
			Logger.info(TAG, each.getStartableId() + " init done.");
		}
		
		return true;
	}
	
	private boolean start(List<Startable> list) {
		for(Startable each : list) {
			long start = System.currentTimeMillis();
			if(each.start() == false) {
				entityMap.get(each.getStartableId()).setStatus(StartableStatus.FAULT.toString());
				Logger.error(TAG, "start failed : " + each.getStartableId());
				each.setStatus(StartableStatus.FAULT);
				return false;
			}
			long end = System.currentTimeMillis();
			each.setStatus(StartableStatus.RUNNING);
			entityMap.get(each.getStartableId()).setStatus(StartableStatus.RUNNING.toString());
			long time = historyMap.get(each.getStartableId()).getLatency();
			time = time + (end - start);
			historyMap.get(each.getStartableId()).setLatency(time);
			Logger.info(TAG, each.getStartableId() + " started.");
		}
		
		return true;
	}
	
	private boolean stop(List<Startable> list) {
		for(Startable each : list) {
			if(each.stop() == false) {
				entityMap.get(each.getStartableId()).setStatus(StartableStatus.FAULT.toString());
				Logger.error(TAG, "stop failed : " + each.getStartableId());
				each.setStatus(StartableStatus.FAULT);
				return false;
			}
			
			each.setStatus(StartableStatus.STOPPED);
			entityMap.get(each.getStartableId()).setStatus(StartableStatus.STOPPED.toString());
			Logger.info(TAG, each.getStartableId() + " stopped.");
		}
		
		return true;
	}
		
	public boolean stop(Startable startable) {
		List<Startable> startables = Arrays.asList(startable);
		return stop(startables);
	}
	
	public boolean restart(Startable startable) {
		List<Startable> startables = Arrays.asList(startable);
		if(init(startables) == false) return false;
		return start(startables);
	}
	
	private void updateStartableDB() {
		List<StartableEntity> updated = new ArrayList<>();
		updated.addAll(entityMap.values());
		Repos.repo(StartableRepository.class).update(updated);
	}
	
	private void updateStartableHistoryDB() {
		for(StartableHistoryDto each : historyMap.values()) {
			Repos.repo(StartableHistoryRepository.class).create(each);			
		}
	}
}