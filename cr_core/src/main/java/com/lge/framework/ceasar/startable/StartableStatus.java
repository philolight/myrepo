package com.lge.framework.ceasar.startable;

public enum StartableStatus {
	NONE,
	CREATED,
	INITIALIZED,
	RUNNING,
	STOPPED,
	FAULT,
	NOT_DEFINED;
	
	public static StartableStatus DEFAULT = StartableStatus.CREATED;
	
	public static StartableStatus getByName(String name) {
		for(StartableStatus each : values()) {
			if(each.name().contains(name)) return each;
		}
		
		return NOT_DEFINED; // 정의되지 않은 타입
	}
}