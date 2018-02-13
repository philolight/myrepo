package com.lge.framework.ceasar.event;

import java.util.ArrayList;
import java.util.List;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.load.Load;
import com.lge.framework.ceasar.event.load.LoadManager;
import com.lge.framework.ceasar.event.publisher.EventPublisher;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

public class EventPerformanceTest {
	public static void main(String[] args) {
		compare();
//		target();
	}
	
	public static void addSubscribers() {
		List<CCS> ccList = new ArrayList<>();
		List<CUS> cuList = new ArrayList<>();
		List<TCS> tcList = new ArrayList<>();
		List<TUS> tuList = new ArrayList<>();
		
		for(int i = 0; i < 10; i++) ccList.add(new CCS());	
		for(int i = 0; i < 10; i++) cuList.add(new CUS());
		for(int i = 0; i < 10; i++) tcList.add(new TCS());
		for(int i = 0; i < 10; i++) tuList.add(new TUS());

		ccList.forEach(each -> EventBroker.subscribe(Cat.class, CreateEvent.class, each));
		cuList.forEach(each -> EventBroker.subscribe(Cat.class, UpdateEvent.class, each));
		tcList.forEach(each -> EventBroker.subscribe(Tiger.class, CreateEvent.class, each));
		tuList.forEach(each -> EventBroker.subscribe(Tiger.class, UpdateEvent.class, each));
	}
	
	public static void waiting() {
		new Thread(()->{
			try {
				int n = 0;
				while(n++ < 20) {
					Thread.sleep(1000);
					long sum = 0;
					for(Load each : LoadManager.report().values()) {
						sum += each.times();
					}
					System.out.println((n) + " " + sum);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public static void target() {
		addSubscribers();
		
		waiting();
		
		eventSending();
	}
	
	public static void compare() {
		addSubscribers();
		
		waiting();
		
		eventSending();
	}
	
	public static void eventSending() {
		EventPublisher<CreateEvent<Cat>> ccp = (EventPublisher<CreateEvent<Cat>>)EventBroker.getPublisher(Cat.class, CreateEvent.class);
		EventPublisher<UpdateEvent<Cat>> cup = (EventPublisher<UpdateEvent<Cat>>)EventBroker.getPublisher(Cat.class, UpdateEvent.class);
		EventPublisher<CreateEvent<Tiger>> tcp = (EventPublisher<CreateEvent<Tiger>>)EventBroker.getPublisher(Tiger.class, CreateEvent.class);
		EventPublisher<UpdateEvent<Tiger>> tup = (EventPublisher<UpdateEvent<Tiger>>)EventBroker.getPublisher(Tiger.class, UpdateEvent.class);
			
		for(int i = 0; i < 5000000; i++) {
			ccp.publish(new CreateEvent<Cat>(new Cat()));
			tcp.publish(new CreateEvent<Tiger>(new Tiger()));
			cup.publish(new UpdateEvent<Cat>(new Cat()));
			tup.publish(new UpdateEvent<Tiger>(new Tiger()));
		}
	}
	
	public static void load() {
/*		String str = "";
		for(int k = 0; k < 10; k++) {
			for(int i = 0; i < 10; i++) {
				str += (i%10+'0');
			}
			Thread.yield();
		}*/
	}
	
	static class Cat{}
	static class Tiger{}

	static class CCS implements EventSubscriber<CreateEvent<Cat>>{
		@Override
		public void subscribe(CreateEvent<Cat> event) {
			load();
		}
	}
	
	static class CUS implements EventSubscriber<UpdateEvent<Cat>>{
		@Override
		public void subscribe(UpdateEvent<Cat> event) {
			load();
		}
	}
	
	static class TCS implements EventSubscriber<CreateEvent<Tiger>>{
		@Override
		public void subscribe(CreateEvent<Tiger> event) {
			load();
		}
	}
	
	static class TUS implements EventSubscriber<UpdateEvent<Tiger>>{
		@Override
		public void subscribe(UpdateEvent<Tiger> event) {
			load();
		}
	}
}