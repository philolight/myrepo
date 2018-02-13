package com.lge.framework.ceasar.event.event_kind;

public class LoadEvent<T> extends EventComparable<T>{	
	private static final long serialVersionUID = -7627218598537886729L;

	public LoadEvent(T t, Object source){
		super(t, source);
	}
	
	public LoadEvent(T t){
		super(t);
	}
}