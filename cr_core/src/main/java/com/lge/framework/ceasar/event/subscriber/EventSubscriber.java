package com.lge.framework.ceasar.event.subscriber;

import com.lge.framework.ceasar.event.event_kind.Event;

public interface EventSubscriber<E extends Event<?>> {
	public static final int DEFAULT_QUEUE_SIZE = 100;
	/** 이벤트를 수신하는 메소드.*/
	public void subscribe(E event);
}