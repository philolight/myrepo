package com.lge.framework.ceasar.event.event_kind;

import java.io.Serializable;

public interface Event<T> extends Serializable, Comparable<Event<?>>{
	public static Object DEFAULT_SOURCE = new Object();
	
	/** 이벤트로 전송되는 타겟 객체를 리턴하는 메소드. */
	public T getTarget();
	
	/** 이벤트 생성자(source) 객체를 리턴하는 메소드. 이벤트를 보낸 생성자 정보를 알 수 있다.*/
	public Object getSource();
	
	/** 이벤트로 전송되는 타겟 객체의 타입을 리턴하는 메소드.
	 *	EventManager는 이 타입을 기반으로 적절한 송신자(publisher)를 찾을 수 있다.
	 **/
	public Class<?> getTargetType();
	
	/** 이벤트가 생산된 시점을 담은 time(ms) 를 리턴하는 메소드 */  
	public long getTime();
}