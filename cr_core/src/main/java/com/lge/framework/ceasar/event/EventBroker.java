package com.lge.framework.ceasar.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.GenericTypeResolver;

import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.publisher.KindredEventPublisher;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

@SuppressWarnings({"rawtypes", "unchecked"})
public class EventBroker {
	private static volatile Map<Class<?>, Map<Class<?>, KindredEventPublisher<? extends Event>>> map = new ConcurrentHashMap<>();

	/** 이벤트 생성자(source)가 더 이상 생성자 역할을 수행하지 않을 때 호출. 
	 *  주의 : 이벤트 생성자가 사라질 때 꼭 이 메소드를 호출해 주어야 메모리 누수가 발생하지 않는다.
	 * */
	public static void retireSource(Class<?> targetType, Class<? extends Event> eventType) {
		synchronized(map) {
			if(map.containsKey(targetType) == false) throw new IllegalArgumentException("No EventPublisher for target Type : " + targetType.getSimpleName());
			if(map.get(targetType).containsKey(eventType) == false) throw new IllegalArgumentException("No EventPublisher for event Type : " + eventType.getSimpleName());
			KindredEventPublisher<?> publisher = map.get(targetType).get(eventType);
			publisher.decreaseSource();

			if(publisher.sources() == 0) {
				publisher.release();
				map.get(targetType).remove(eventType);
				if(map.get(targetType).size() == 0) map.remove(targetType);
			}
		}
	}
	
	/** 특정 이벤트에 대한 구독을 등록하는 메소드. */
	public static <E extends Event> void subscribe(Class<?> targetType, Class<? extends Event> eventType, EventSubscriber<? super E> subscriber) {
		Class<?> subscriberEventType = GenericTypeResolver.resolveTypeArgument(subscriber.getClass(), EventSubscriber.class);
		
		if(eventType.equals(subscriberEventType) == false) throw new IllegalArgumentException("Type Mismatch : " + eventType.getSimpleName() + " " + subscriberEventType.getSimpleName());
		
		((KindredEventPublisher<E>)getProperPublisher(targetType, eventType)).regist(subscriber);
	}
	
	/** 특정 이벤트에 대한 긴급 구독을 등록하는 메소드. 긴급 구독은 다른 구독자들의 이벤트 처리에서 발생하는 딜레이 없이 이벤트 구독이 가능하다. */
	public static <E extends Event> void subscribeUrgent(Class<?> targetType, Class<? extends Event> eventType, EventSubscriber<? super E> subscriber) {
		Class<?> type = GenericTypeResolver.resolveTypeArgument(subscriber.getClass(), EventSubscriber.class);
		if(eventType.equals(type) == false) throw new IllegalArgumentException("Type Mismatch : " + eventType.getSimpleName() + " " + type.getSimpleName());
		
		((KindredEventPublisher<E>)getProperPublisher(targetType, eventType)).registUrgent(subscriber);
	}
	
	/** 이벤트에 대한 구독을 취소하는 메소드. */
	public static <E extends Event> void unsubscribe(Class<?> targetType, Class<? extends Event> eventType, EventSubscriber<? super E> subscriber) {
		((KindredEventPublisher<E>)getProperPublisher(targetType, eventType)).unregist(subscriber);
	}
	
	/** 이벤트 publisher를 찾고, 없으면 생성해서 제공해 주는 메소드. */
	public static KindredEventPublisher<?> getPublisher(Class<?> targetType, Class<? extends Event> eventType){
		KindredEventPublisher<?> publisher = getProperPublisher(targetType, eventType);
		
		publisher.increaseSource();
		
		return publisher;
	}
	
	private static KindredEventPublisher<?> getProperPublisher(Class<?> targetType, Class<? extends Event> eventType){
		KindredEventPublisher<?> publisher = null;
		synchronized(map) {
			if(map.containsKey(targetType) == false) newPublisher(targetType, eventType);
			
			publisher = (KindredEventPublisher<?>)map.get(targetType).get(eventType);
			if(publisher == null) {
				newPublisher(targetType, eventType);
				publisher = (KindredEventPublisher<?>)map.get(targetType).get(eventType);
			}
		}
		
		return publisher;
	}
	
	/** 대상 객체의 타입과 이벤트 타입에 맞는 Publisher를 등록하는 메소드. */
	private static <E extends Event> void newPublisher(Class<?> targetType, Class<? extends Event> eventType) {
		KindredEventPublisher<?> publisher = new KindredEventPublisher(targetType.getSimpleName() + " " + eventType.getSimpleName() + " " + KindredEventPublisher.class.getSimpleName());
		
		Map<Class<?>, KindredEventPublisher<? extends Event>> eventMap = map.get(targetType);
		if(eventMap == null) {
			eventMap = new ConcurrentHashMap<>();
			map.put(targetType, eventMap);
		}
		
		eventMap.put(eventType, publisher);		
	}
}