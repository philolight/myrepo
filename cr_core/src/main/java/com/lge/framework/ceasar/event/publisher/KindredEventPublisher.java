package com.lge.framework.ceasar.event.publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lge.framework.ceasar.event.ObjectEventBroker;
import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.load.Load;
import com.lge.framework.ceasar.event.load.LoadManager;
import com.lge.framework.ceasar.event.load.LoadType;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;
import com.lge.framework.ceasar.logger.Logger;

/**
 * 같은 종류의 모든 객체들에 대하여 같은 종류의 이벤트가 발생했을 때 이를 전달하는 EventPublisher. 
 * (이벤트가 각 구독자에게 전달되는 순서는 고려하지 않는다.)
 * 긴급 구독자(urgentSubscriber)로 등록될 경우, 각각의 긴급 구독자들은 각각 별도의 쓰레드 안에서 이벤트를 받게 된다.
 * 이를 통해서 순차적으로 이벤트를 전송할 경우 다른 구독자의 처리 시간에 의해 이벤트 도착이 지연되는 것을 방지한다.
 */
@SuppressWarnings("rawtypes")
public class KindredEventPublisher<E extends Event> implements EventPublisher<E> {
	private static final String TAG = KindredEventPublisher.class.getSimpleName();
	
	protected List<EventSubscriber<? extends Event>> urgentSubscribers = Collections.synchronizedList(new ArrayList<>());
	protected List<EventSubscriber<? extends Event>> subscribers = Collections.synchronizedList(new ArrayList<>());
	
	protected BlockingQueue<E> q = new ArrayBlockingQueue<>(DEFAULT_QUEUE_SIZE);
	
	protected volatile Object[] array = new Object[0];		// 구독자들을 배열로 뽑아 내어 subscribers 객체에서 락이 걸리지 않도록 한다.
	protected volatile Object[] urgentArray = new Object[0];
	
	protected volatile ExecutorService eventExecutor;	// 이벤트 용 쓰레드 풀(pool). 이벤트 당 한 개의 쓰레드가 사용됨.
	protected volatile ExecutorService urgentExecutor;	// urgent 용 쓰레드 풀. 이벤트의 urgents 당 한 개의 쓰레드가 사용됨.
	protected volatile ExecutorService queueExecutor = Executors.newSingleThreadExecutor();
	
	private final int defaultEvents = 3;
	private final int defaultUrgents = 3;
	
	private int events = 0;
	private int urgents = 0;
	
	protected Load load = new Load(LoadType.EVENT);
	
	public KindredEventPublisher(String name) {
		load.setName(name);
		LoadManager.regist(load);
		this.setEventExecutor(defaultEvents);
		this.setUrgentExecutor(defaultUrgents);
		
		queueExecutor.execute(new QueueThread());
	}
		
	// 너무 많은 이벤트가 한번에 들어 올 경우 이벤트를 처리하는 쓰레드 개수를 늘린다.
	private void changeEventExecutors(int size) {
		if(events == defaultEvents && size < defaultEvents) return;
		// TODO : 더 이상 쓰레드를 늘릴 수 없을 경우 Queue Size를 늘려야 할까?
		if(size > DEFAULT_QUEUE_SIZE * 0.75 && events < defaultEvents * 16) this.setEventExecutor(events * 2);
		else if(events >= defaultEvents * 2 && size < DEFAULT_QUEUE_SIZE * 0.25) this.setEventExecutor(events/2);
	}
	
	// 너무 많은 Urgent가 등록될 경우 Urgent 쓰레드 개수를 늘린다.
	private void changeUrgentExecutors(int size) {
		if(urgents == defaultUrgents && size < defaultUrgents) return;
		if(size > urgents && urgents < defaultUrgents * 16) this.setUrgentExecutor(urgents * 2);
		else if(urgents >= defaultUrgents * 2 && size < urgents / 3) this.setEventExecutor(urgents/2);
	}
	
	private void setEventExecutor(int events) {
		this.events = events;
		eventExecutor = Executors.newFixedThreadPool(events);
	}
	
	private void setUrgentExecutor(int urgents) {
		this.urgents = urgents;
		urgentExecutor = Executors.newFixedThreadPool(urgents);
	}
	
	@Override
	public void regist(EventSubscriber<? super E> subscriber) {
		if(subscribers.contains(subscriber)) throw new IllegalArgumentException("Duplicated subscriber : " + subscriber.getClass().getSimpleName());
		subscribers.add(subscriber);
		array = subscribers.toArray();
	}
	
	/** 긴급(urgent) 구독자(subscriber)를 등록한다. 
	 *  긴급 구독자란, 이벤트가 발생하였을 때 (다른 구독자들에 의한 딜레이 없이) 시스템이 허용하는 최단 시간 내에 이벤트를 받아야 하는 구독자를 말한다.
	 *  긴급 구독자들은 이벤트를 개별 쓰레드에서 받게 되므로 다른 구독자의 처리를 기다릴 필요가 없다.
	 *  (쓰레드 풀의 사이즈에 의해 딜레이가 발생할 수 있다는 점에 유의할 것.)
	 * */
	public void registUrgent(EventSubscriber<? super E> subscriber) {
		if(urgentSubscribers.contains(subscriber) || subscribers.contains(subscriber)) throw new IllegalArgumentException("Duplicated subscriber : " + subscriber.getClass().getSimpleName());
		urgentSubscribers.add(subscriber);
		urgentArray = urgentSubscribers.toArray();
		changeUrgentExecutors(urgentSubscribers.size());
	}

	@Override
	public void unregist(EventSubscriber<? super E> subscriber) {
		if(urgentSubscribers.contains(subscriber)) {
			urgentSubscribers.remove(subscriber);
			urgentArray = urgentSubscribers.toArray();
			changeUrgentExecutors(urgentSubscribers.size());
		}
		
		if(subscribers.contains(subscriber)) {
			subscribers.remove(subscriber);
			array = subscribers.toArray();
		}
	}
	
	@Override
	public void unregistAll() {
		urgentSubscribers.clear();
		subscribers.clear();
		
		array = subscribers.toArray();
		urgentArray = urgentSubscribers.toArray();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public void publish(E event) {
		changeEventExecutors(q.size());
		try {
			q.put(event);
		} catch (InterruptedException e) {
			Logger.error(TAG, "Failed to process event : " + event);
			return;
		}
	}
		
	protected int sources = 0;
	/** 등록된  source 수를 증가시킴 */
	public void increaseSource() { sources++; }
	/** 등록된  source 수를 감소시킴 */
	public void decreaseSource() { if(sources != 0) sources--; }
	/** 등록된  source 수를 리턴 */
	public int sources() { return sources; }
	
	/** 전송자(publisher)로서의 역할이 끝났을 때 호출되는 메소드. */
	public void release() {
		unregistAll();
		LoadManager.unregist(load);
	}
	
	class QueueThread extends Thread{
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			while(true) {
				try {
					E event = q.take();
					
					eventExecutor.execute(()->{ // 이벤트의 publish 과정을 쓰레드로 독립시켜 줌으로써 이벤트 생성자가 빨리 release 될 수 있도록 한다.
						long start = System.currentTimeMillis();
						
						for(Object each : urgentArray) urgentExecutor.execute( ()->{
							long s = System.currentTimeMillis();				
							((EventSubscriber<? super E>)(each)).subscribe(event);
							load.add(System.currentTimeMillis() - s, 1);
						} ); // urgent 구독자들은 각각을 별도의 쓰레드에서 처리한다.
						for(Object each : array) ((EventSubscriber<? super E>)(each)).subscribe(event);			// 일반 구독자들은 (다른 구독자들에 의해 발생할 수 있는 딜레이를 감수하고) 같은 쓰레드에서 처리한다.
						load.add(System.currentTimeMillis() - start, array.length);
						
						ObjectEventBroker.publish(event);
					});
				} catch (InterruptedException e) {
					Logger.fatal(TAG, "Event Publisher down");
				}
			}
		}
	}
}
