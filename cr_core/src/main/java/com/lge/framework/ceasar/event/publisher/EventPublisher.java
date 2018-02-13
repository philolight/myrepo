package com.lge.framework.ceasar.event.publisher;

import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

@SuppressWarnings("rawtypes")
public interface EventPublisher<E extends Event> {
	public static final int DEFAULT_QUEUE_SIZE = 10;
	
	/** 구독자(subscriber)를 등록한다. 
	 *  일반 구독자란, 이벤트 구독자들을 순차적으로 처리하면서 발생하게 되는 처리 딜레이를 감수할 수 있는 구독자이다.
	 *  일반 구독자들은 하나의 쓰레드 안에서 순차적으로 이벤트를 받는다.
	 * */
	public void regist(EventSubscriber<? super E> subscriber);
			
	/** 구독자의 구독을 해제한다. */
	public void unregist(EventSubscriber<? super E> subscriber);
	
	/** 모든 구독자(긴급 구독자 포함)의 구독을 해제한다. */
	public void unregistAll();
	
	/** 구독자들에게 이벤트를 전송하는 메소드 */
	public void publish(E event);
}