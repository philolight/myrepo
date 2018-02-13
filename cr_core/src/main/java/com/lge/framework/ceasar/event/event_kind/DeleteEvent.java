package com.lge.framework.ceasar.event.event_kind;

public class DeleteEvent<T> extends EventComparable<T>{
	private static final long serialVersionUID = 7546187070709587323L;

	public DeleteEvent(T t, Object source){
		super(t, source);
	}
	
	public DeleteEvent(T t){
		super(t);
	}
}