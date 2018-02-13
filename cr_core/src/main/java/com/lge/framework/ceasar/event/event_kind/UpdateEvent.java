package com.lge.framework.ceasar.event.event_kind;

public class UpdateEvent<T> extends EventComparable<T>{
	private static final long serialVersionUID = -4783331082571664386L;

	public UpdateEvent(T t, Object source){
		super(t, source);
	}
	
	public UpdateEvent(T t){
		super(t);
	}
}
