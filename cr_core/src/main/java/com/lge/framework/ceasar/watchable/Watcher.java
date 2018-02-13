package com.lge.framework.ceasar.watchable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.lge.framework.ceasar.startable.Startable;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.startable.StartableType;
import com.lge.framework.ceasar.startable.Starter;

/**
 * Watcher는 시스템의 내부 동작 상태에 대한 가시성을 제공해 주는 클래스이다.
 * Watcher의 관리 대상은 Watchable 인터페이스를 상속 받은 객체들이다.
 * Watchable의 root 객체들(Watcher에 의해 직접 관리되어야 할 Watchable 객체들)은 객체 생성시  Watcher.add(this) 메소드를 호출하여 Watcher에 등록 시킨다.
 * 객체의 생성이 온전히 완료된 후에 등록되도록 해야 하기 때문에 Watchable의 root 객체들은 모두 Factory Method를 구현해야 하고, 생성자는 disable 시켜야 한다.
 * Watchable이 소멸하는 경우, Watchable 스스로가 Watcher의 관리로부터 제거되기 위해서 Watcher.remove(this)를 호출한다.
 */

public class Watcher implements Startable{
	// as a Startable ----------------------------------------------------------------------------------------------------------------------

	protected StartableStatus status = StartableStatus.DEFAULT;

	@Override
	public StartableType getStartableType() { return StartableType.FRAMEWORK_COMPONENT; }

	@Override
	public StartableStatus getStartableStatus() { return status; }

	public void setStatus(StartableStatus status) { this.status = status; }

	public Watcher() {
		Starter.add(this);
	}

	// --------------------------------------------------------------------------------------------------------------------------------------
	
	
	private static final String TAG = Watcher.class.getSimpleName();
	
	private static Map<WatchableType, Set<Watchable>> map = new HashMap<>();
	
	/**
	 * Watchable을 등록하는 메소드.
	 * Watcher 객체가 생성되기 전에 등록을 원하는 객체를 위해서 static으로 선언함.
	 **/
	public static void add(Watchable watchable) {
		Set<Watchable> watchables = map.get(watchable.getWatchableType());
		if(watchables == null) {
			watchables = new HashSet<>();
			map.put(watchable.getWatchableType(), watchables);
		}
		watchables.add(watchable);
	}
	
	public static void remove(Watchable watchable) {
		Set<Watchable> watchables = map.get(watchable.getWatchableType());
		if(watchables == null) return;
		watchables.remove(watchable);
	}
	
	public Map<WatchableType, Set<Watchable>> getWatchables(){
		return map;
	}
	
	
	@Override
	public String getStartableId() {
		return this.getClass().getSimpleName();
	}

	/** Watchable에 대한 초기화 수행 */
	@Override
	public boolean init() {
		for(Set<Watchable> watchables : map.values()) {
			for(Watchable each : watchables) {
				if(initWatchable(each) == false) return false;
			}
		}

		return false;
	}
	
	private boolean initWatchable(Watchable watchable) {
		return false;
		// TODO : DB에서 해당 Watchable을 읽어들인다.
		// TODO : DB에 없으면 DB에 Watchable을 등록한다.
		// Watchable 테이블에는
		// id, modified_time(yyyy.MM.dd), run_count, total_taken_time(ms), time_zone_id, is_old 가 들어간다.
		// TODO : 하위 Watchable들도 등록해야 한다.		
	}

	@Override
	public boolean start() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean stop() {
		// TODO Auto-generated method stub
		return false;
	}
}

class WatcherRunner extends Thread{
	private final Watcher watcher;
	public WatcherRunner(Watcher watcher) {
		this.watcher = watcher;
	}
	
	@Override
	public void run() {
		
	}
}
