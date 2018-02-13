package com.lge.framework.ceasar.event.publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.load.Load;
import com.lge.framework.ceasar.event.load.LoadManager;
import com.lge.framework.ceasar.event.load.LoadType;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

/**
 * 특정 객체(Object)에 대한 특정 Event가 발생했을 때 그 이벤트를 구독자(subscriber)들에게 전달하는 전송자(publisher). 
 * (이벤트가 각 구독자에게 전달되는 순서는 고려하지 않는다.)
 */
@SuppressWarnings("rawtypes")
public class ObjectEventPublisher<E extends Event> implements EventPublisher<E> {
	protected List<EventSubscriber<? super E>> subscribers = Collections.synchronizedList(new ArrayList<>());
	
	protected volatile Object[] array = new Object[0];		// 구독자들을 배열로 뽑아 내어 subscribers 객체에서 락이 걸리지 않도록 한다.
	
	protected Load load = new Load(LoadType.EVENT);
	
	public ObjectEventPublisher(String name) {
		load.setName(name);
		LoadManager.regist(load);
	}
	
	@Override
	public void regist(EventSubscriber<? super E> subscriber) {
		if(subscribers.contains(subscriber)) throw new IllegalArgumentException("Duplicated subscriber : " + subscriber.getClass().getSimpleName());
		subscribers.add(subscriber);
		array = subscribers.toArray();
	}
	
	@Override
	public void unregist(EventSubscriber<? super E> subscriber) {
		if(subscribers.contains(subscriber)) {
			subscribers.remove(subscriber);
			array = subscribers.toArray();
		}
	}
	
	@Override
	public void unregistAll() {
		subscribers.clear();
		array = subscribers.toArray();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void publish(E event) {
		long start = System.currentTimeMillis();
		
		for(Object each : array) ((EventSubscriber<? super E>)(each)).subscribe(event);
		load.add(System.currentTimeMillis() - start, array.length);
	}
		
	public void release() {
		unregistAll();
		LoadManager.unregist(load);
	}
}
