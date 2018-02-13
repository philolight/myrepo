package com.lge.framework.ceasar.event;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.GenericTypeResolver;

import com.lge.framework.ceasar.event.event_kind.DeleteEvent;
import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.publisher.EventPublisher;
import com.lge.framework.ceasar.event.publisher.ObjectEventPublisher;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ObjectEventBroker {
	private static Map<Object, Map<Class<?>, ObjectEventPublisher<? extends Event>>> map = new ConcurrentHashMap<>();
	
	/** 이벤트를 전송하는 메소드.
	 *  이벤트는 구독자가 있어야만 전송된다.
	 **/
	public static <E extends Event> void publish(E event) {
		EventPublisher<E> publisher = (EventPublisher<E>)getPublisherIfExist(event.getTarget(), event.getClass());
		if(publisher != null) {
			publisher.publish(event);
			
			if(event instanceof DeleteEvent) { // 해당 Object에 대한 삭제 이벤트 이므로 이후에는 더 이상 해당 객체에 대한 이벤트가 없음.
				removePublisher(event.getTarget());
			}
		}
	}
		
	/** 특정 이벤트에 대한 구독을 등록하는 메소드. */
	public static <E extends Event> void subscribe(Object target, Class<? extends Event> eventType, EventSubscriber<? super E> subscriber) {
		Class<?> subscriberEventType = GenericTypeResolver.resolveTypeArgument(subscriber.getClass(), EventSubscriber.class);
		
		if(eventType.equals(subscriberEventType) == false) throw new IllegalArgumentException("Type Mismatch : " + eventType.getSimpleName() + " " + subscriberEventType.getSimpleName());
		
		((ObjectEventPublisher<E>)getPublisher(target, eventType)).regist(subscriber);
	}
	
	/** 이벤트 소스가 더 이상 유효하지 않을 경우 publisher를 EventManager에서 제거하는 메소드. 
	 *  주의 : 이벤트 소스가 사라질 때 꼭 이 메소드를 호출해 주어야 메모리 누수가 발생하지 않는다.
	 * */
	public static synchronized void removePublisher(Object target) {
		if(map.containsKey(target) == false) throw new IllegalArgumentException("No EventPublisher for target : " + target.getClass().getSimpleName());

		for(Class<?> key : map.get(target).keySet()) {
			ObjectEventPublisher<?> publisher = map.get(target).get(key);
			publisher.unregistAll();
		}
		map.get(target).clear();
		map.remove(target);
	}
		
	/** 이벤트에 대한 구독을 취소하는 메소드. */
	public static <E extends Event> void unsubscribe(Object target, Class<? extends Event> eventType, EventSubscriber<? super E> subscriber) {
		((ObjectEventPublisher<E>)getPublisher(target, eventType)).unregist(subscriber);
	}
	
	/** 이벤트 publisher를 찾고, 없으면 생성해서 제공해 주는 메소드. */
	public static synchronized EventPublisher<?> getPublisher(Object target, Class<? extends Event> eventType){
		if(map.containsKey(target) == false) addPublisher(target, eventType);
		
		ObjectEventPublisher<?> publisher = (ObjectEventPublisher<?>)map.get(target).get(eventType);
		if(publisher == null) {
			addPublisher(target, eventType);
			publisher = (ObjectEventPublisher<?>)map.get(target).get(eventType);
		}
		
		return publisher;
	}
	
	/** 이벤트 publisher를 찾고, 없으면 제공하지 않는 메소드. */
	public static ObjectEventPublisher<?> getPublisherIfExist(Object target, Class<? extends Event> eventType){
		Map<Class<?>, ObjectEventPublisher<? extends Event>> publisherMap = map.get(target);
		if(publisherMap == null) return null;
		
		return publisherMap.get(eventType);
	}
	
	/** 대상 객체의 타입과 이벤트 타입에 맞는 Publisher를 등록하는 메소드. */
	private static <E extends Event> void addPublisher(Object target, Class<? extends Event> eventType) {
		ObjectEventPublisher<?> publisher = new ObjectEventPublisher(target.getClass().getSimpleName() + "." + eventType.getSimpleName() + " " + ObjectEventPublisher.class.getSimpleName());
		
		Map<Class<?>, ObjectEventPublisher<? extends Event>> eventMap = map.get(target);
		if(eventMap == null) {
			eventMap = new ConcurrentHashMap<>();
			map.put(target, eventMap);
		}
		
		eventMap.put(eventType, publisher);		
	}
}
