package com.lge.framework.ceasar.event.subscriber;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.framework.ceasar.event.publisher.KindredEventPublisher;
import com.lge.framework.pacific.logger.Logger;

/**
 * 들어온 이벤트를 시간 순서대로 처리할 수 있도록 만들어진 Event Subscriber.
 * Event 객체는 CompareTo를 통해 Event 생성 시각이 더 과거인 Event가 높은 우선순위를 갖도록 구현되어 있다.
 * BlockingQueue에 PriorityBlockingQueue 객체를 이용하고, Event의 과거 우선 특성을 이용하여 가장 오래된 Event를 리턴한다.
 * TODO : 이벤트의 발생 시점부터 처리 시점까지의 온전한 순서를 보장할 수 없다. 어쨌든 먼저 들어온 이벤트가 먼저 처리될 수 밖에 없다.
 */
abstract public class SequentialEventSubscriber<E extends Event<?>> implements EventSubscriber<E>{
	private static final String TAG = KindredEventPublisher.class.getSimpleName();
	
	// PriorityBlockingQueue를 이용, 들어온 이벤트를 시간 순서대로 처리할 수 있게 한다.
	BlockingQueue<E> q = new PriorityBlockingQueue<>(DEFAULT_QUEUE_SIZE);
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public SequentialEventSubscriber() {
		executor.execute(()->{
			while(true) {
				try {
					E event = q.take(); 	// 이벤트가 들어오기를 기다린 후(blocking)
					processEvent(event);	// 들어온 이벤트를 처리한다.
				} catch (InterruptedException e) {
					Logger.fatal(TAG, "Event Queue take fault");
				}
			}
		});
	}
	
	/** 이벤트를 수신하는 메소드.*/
	public void subscribe(E event) {
		try {
			q.put(event);
		} catch (InterruptedException e) {
			Logger.fatal(TAG, "Event Queue put fault");
		}
	}
	
	abstract public void processEvent(E event);
}