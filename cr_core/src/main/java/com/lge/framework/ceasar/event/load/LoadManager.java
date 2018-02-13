package com.lge.framework.ceasar.event.load;

import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.startable.Startable;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.startable.StartableType;
import com.lge.framework.ceasar.startable.Starter;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.repository.EventHistoryRepository;

public class LoadManager implements Startable{
	// as a Startable ----------------------------------------------------------------------------------------------------------------------	
	
	static { Starter.add(new LoadManager()); }
	
	private StartableStatus startableStatus = StartableStatus.DEFAULT;	
	
	@Override public String getStartableId() { return this.getClass().getSimpleName(); }
	@Override public StartableType getStartableType() { return StartableType.FRAMEWORK_COMPONENT; }
	@Override public StartableStatus getStartableStatus() { return startableStatus; }
	@Override public void setStatus(StartableStatus status) { this.startableStatus = status; }

	private ExecutorService execService;
	
	@Override
	public boolean init() { return true; /* do nothing */ }
	
	private long saveInterval = 60 * 1000; // ms
	public void setSaveInterval(long ms) {
		this.saveInterval = ms;
	}
	
	@Override 
	public boolean start() {		
		execService = Executors.newSingleThreadExecutor();
		
		execService.execute(() -> {
			long start = System.currentTimeMillis();
			report();
			try { Thread.sleep(saveInterval); } catch (InterruptedException e) { /* do nothing */ }
			while(true) {
				report();
				long end = System.currentTimeMillis();
				for(Load each : loads.values()) {
					if(each.times() == 0) continue; // 한번도 호출되지 않은 이벤트는 DB에 저장하지 않음.
					EventHistoryDto dto = new EventHistoryDto();
					dto.setCdate(DateStringUtil.getCurrentDateString(TimeZone.getDefault().getID())); // TODO : DB datetime으로 변경해야 함.
					dto.setType(each.getType().toString());
					dto.setFrequency(each.frequency());
					dto.setLatency(each.latency());
					dto.setName(each.getName());
					dto.setTimes(each.times());
					Repos.repo(EventHistoryRepository.class).create(dto);						
				}
				
				start = end;
				try { Thread.sleep(saveInterval); } catch (InterruptedException e) { /* do nothing */ }
			}
		});
		
		return true; 
	}

	@Override 
	public boolean stop() {
		execService.shutdownNow();
		return true; 
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------
	
	private static Map<String, Load> loads = new ConcurrentHashMap<>();
	
	/**
	 * addLoad()는 Load 객체 관리를 LoadManager에 위임 시키면서 Load를 체크하기 위해 사용하는 객체임.
	 * database의 쿼리 속도를 측정하는 등의 용도로 활용될 예정.
	 */
	public static void addLoad(String name, LoadType type, long value, long times) {
		Load load = loads.get(name);
		if(load == null) {
			load = new Load(type);
			load.setName(name);
			regist(load);
		}
		
		load.add(value, times);
	}
	
	public static void regist(Load load) {
		loads.put(load.getName(), load);
	}
	
	public static void unregist(Load load) {
		loads.remove(load.getName());
	}
	
	/**
	 * Load 값을 모두 더한 값을 리턴한다.
	 * 각 Load들은 값을 리턴한 후 0으로 초기화 된다.
	 * 따라서 주기적으로 이 함수가 호출되면 해당 주기 동안의 load 값을 알 수 있다.
	 */
	public static long fullLoad() {
		long ret = 0;
		synchronized(loads) {
			for(Load each : loads.values()) ret += each.calculate();
		}
		
		return ret;
	}
	
	/**
	 * 각 load에 대한 name : value 맵을 리턴한다.
	 * 각 Load들은 값을 리턴한 후 0으로 초기화 된다.
	 * 따라서 주기적으로 이 함수가 호출되면 해당 주기 동안의 name : value 값을 알 수 있다.
	 */
	public static Map<String, Load> report() {
		synchronized(loads) {
			for(Load each : loads.values()) {
				each.calculate();
			}
		}
		
		return loads;
	}
}