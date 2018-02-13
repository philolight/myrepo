package com.lge.framework.ceasar.watchable;

public enum WatchableType {
	SERVICE,
	TASK,
	THREAD,
	DRIVER,
	NOT_DEFINED;
	
	public WatchableType getByName(String name) {
		for(WatchableType each : values()) {
			if(each.name().contains(name)) return each;
		}
		
		return NOT_DEFINED; // 정의되지 않은 타입
	}
}