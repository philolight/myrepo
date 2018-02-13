package com.lge.framework.ceasar.startable;

/**
 * Runner가 실행 순서를 결정하기 위해 구분하는 타입.
 */
public enum StartableType { // 선언된 순서에 따라 실행되기 때문에 순서가 중요함!!
	REPOSITORY{
		@Override public boolean stoppable() { return false; }
	},
	FRAMEWORK_COMPONENT,
	SERVICE,
	POST_STARTUP,	// 다른 구성자들이 모두 시작된 후에 실행되어야 할 타입들.
	NOT_DEFINED;
	
	public StartableType getByName(String name) {
		for(StartableType each : values()) {
			if(each.name().contains(name)) return each;
		}
		
		return NOT_DEFINED; // 정의되지 않은 타입
	}
	
	public boolean stoppable() { return true; } 
}
