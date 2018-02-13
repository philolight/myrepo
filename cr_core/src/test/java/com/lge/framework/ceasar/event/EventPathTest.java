package com.lge.framework.ceasar.event;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.publisher.EventPublisher;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

public class EventPathTest {	
	private static CCS ccs;
	private static CUS cus;
	private static TCS tcs;
	private static TUS tus;
	
	private static CCS ccs2;
	private static CUS cus2;
	private static TCS tcs2;
	private static TUS tus2;
	
	private static EventPublisher<CreateEvent<Cat>> ccp;
	private static EventPublisher<UpdateEvent<Cat>> cup;
	private static EventPublisher<CreateEvent<Tiger>> tcp;
	private static EventPublisher<UpdateEvent<Tiger>> tup;
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		ccs = new CCS();
		cus = new CUS();	
		tcs = new TCS();
		tus = new TUS();
		
		ccs2 = new CCS();
		cus2 = new CUS();	
		tcs2 = new TCS();
		tus2 = new TUS();
		
		EventBroker.subscribe(Cat.class, CreateEvent.class, ccs);
		EventBroker.subscribe(Cat.class, UpdateEvent.class, cus);
		
		EventBroker.subscribe(Tiger.class, CreateEvent.class, tcs);
		EventBroker.subscribe(Tiger.class, UpdateEvent.class, tus);
		
		EventBroker.subscribe(Cat.class, CreateEvent.class, ccs2);
		EventBroker.subscribe(Cat.class, UpdateEvent.class, cus2);
		
		EventBroker.subscribe(Tiger.class, CreateEvent.class, tcs2);
		EventBroker.subscribe(Tiger.class, UpdateEvent.class, tus2);
		
		ccp = (EventPublisher<CreateEvent<Cat>>)EventBroker.getPublisher	(Cat.class, CreateEvent.class);
		cup = (EventPublisher<UpdateEvent<Cat>>)EventBroker.getPublisher	(Cat.class, UpdateEvent.class);
		tcp = (EventPublisher<CreateEvent<Tiger>>)EventBroker.getPublisher	(Tiger.class, CreateEvent.class);
		tup = (EventPublisher<UpdateEvent<Tiger>>)EventBroker.getPublisher	(Tiger.class, UpdateEvent.class);
	}
	
	@After
	public void tearDown() {
		EventBroker.retireSource(Cat.class, CreateEvent.class);
		EventBroker.retireSource(Cat.class, UpdateEvent.class);
		EventBroker.retireSource(Tiger.class, CreateEvent.class);
		EventBroker.retireSource(Tiger.class, UpdateEvent.class);
	}
		
	@Test
	public void testPath_이벤트는이벤트타입과타겟타입에따라다른구독자에게전달되어야한다() throws Exception{
		ccp.publish(new CreateEvent<Cat>(new Cat())); // cc
		Thread.sleep(50);
		assertThat(ccs.called, equalTo(1));
		assertThat(ccs2.called, equalTo(1));
		
		cup.publish(new UpdateEvent<Cat>(new Cat())); // cu
		Thread.sleep(50);
		assertThat(cus.called, equalTo(1));
		assertThat(cus2.called, equalTo(1));
		
		tcp.publish(new CreateEvent<Tiger>(new Tiger())); // tc
		Thread.sleep(50);
		assertThat(tcs.called, equalTo(1));
		assertThat(tcs2.called, equalTo(1));
		
		tup.publish(new UpdateEvent<Tiger>(new Tiger())); // tu
		Thread.sleep(50);
		assertThat(tus.called, equalTo(1));
		assertThat(tus2.called, equalTo(1));
	}
	
	static class Cat {}
	static class Tiger {}
	
	static class CCS implements EventSubscriber<CreateEvent<Cat>>{
		private int called = 0;
		@Override
		public void subscribe(CreateEvent<Cat> event) {
			called ++;
//			System.out.println("received create event : " + event.getTarget().getClass().getSimpleName());
		}
	}

	static class CUS implements EventSubscriber<UpdateEvent<Cat>>{
		private int called = 0;
		@Override
		public void subscribe(UpdateEvent<Cat> event) {
			called ++;
//			System.out.println("received update event : " + event.getTarget().getClass().getSimpleName());
		}
	}

	static class TCS implements EventSubscriber<CreateEvent<Tiger>>{
		private int called = 0;
		@Override
		public void subscribe(CreateEvent<Tiger> event) {
			called ++;
//			System.out.println("received create event : " + event.getTarget().getClass().getSimpleName());
		}
	}

	static class TUS implements EventSubscriber<UpdateEvent<Tiger>>{
		private int called = 0;
		@Override
		public void subscribe(UpdateEvent<Tiger> event) {
			called ++;
//			System.out.println("received update event : " + event.getTarget().getClass().getSimpleName());
		}
	}
}
