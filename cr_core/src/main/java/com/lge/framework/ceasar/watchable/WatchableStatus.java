package com.lge.framework.ceasar.watchable;

public enum WatchableStatus {
	NORMAL,
	FAULT,
	NOT_DEFINED;
	
	public WatchableStatus getByName(String name) {
		for(WatchableStatus each : values()) {
			if(each.name().contains(name)) return each;
		}
		
		return NOT_DEFINED; // 정의되지 않은 타입
	}
}
