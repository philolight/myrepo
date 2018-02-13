package com.lge.framework.ceasar.event.event_kind;

public class CreateEvent<T> extends EventComparable<T>{	
	private static final long serialVersionUID = -7627218598537886729L;

	public CreateEvent(T t, Object source){
		super(t, source);
	}
	
	public CreateEvent(T t){
		super(t);
	}
}
