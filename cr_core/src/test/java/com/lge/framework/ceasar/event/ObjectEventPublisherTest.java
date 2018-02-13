package com.lge.framework.ceasar.event;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.ObjectEventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.DeleteEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.publisher.EventPublisher;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

public class ObjectEventPublisherTest {
	private OCCS occs;
	private OCUS ocus;
	private OCDS ocds;
	
	private EventPublisher<CreateEvent<Cat>> ccp;
	private EventPublisher<UpdateEvent<Cat>> cup;
	private EventPublisher<DeleteEvent<Cat>> cdp;
	
	private Cat object = new Cat();
	
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		occs = new OCCS();
		ocus = new OCUS();
		ocds = new OCDS();
		
		ObjectEventBroker.subscribe(object, CreateEvent.class, occs);
		ObjectEventBroker.subscribe(object, UpdateEvent.class, ocus);
		ObjectEventBroker.subscribe(object, DeleteEvent.class, ocds);
				
		ccp = (EventPublisher<CreateEvent<Cat>>)EventBroker.getPublisher	(Cat.class, CreateEvent.class);
		cup = (EventPublisher<UpdateEvent<Cat>>)EventBroker.getPublisher	(Cat.class, UpdateEvent.class);
		cdp = (EventPublisher<DeleteEvent<Cat>>)EventBroker.getPublisher	(Cat.class, DeleteEvent.class);
	}
	
	@After
	public void tearDown() {
		EventBroker.retireSource(Cat.class, CreateEvent.class);
		EventBroker.retireSource(Cat.class, UpdateEvent.class);
		EventBroker.retireSource(Cat.class, DeleteEvent.class);
	}
		
	@Test
	public void test_특정객체에대한이벤트만을받아야한다() throws Exception{
		ccp.publish(new CreateEvent<Cat>(new Cat())); // 새로운 객체의 이벤트는 받지 않는다.
		Thread.sleep(50);
		assertThat(occs.called, equalTo(0));
		
		cup.publish(new UpdateEvent<Cat>(new Cat())); // 새로운 객체의 이벤트는 받지 않는다.
		Thread.sleep(50);
		assertThat(ocus.called, equalTo(0));
		
		cdp.publish(new DeleteEvent<Cat>(new Cat())); // 새로운 객체의 이벤트는 받지 않는다.
		Thread.sleep(50);
		assertThat(ocds.called, equalTo(0));

		ccp.publish(new CreateEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(occs.called, equalTo(1));
		
		cup.publish(new UpdateEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(ocus.called, equalTo(1));
		
		cdp.publish(new DeleteEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(ocds.called, equalTo(1));
	}
	
	@Test
	public void testUnregistAfterDeleteEvent_Delete이벤트이후에는모든이벤트를해제한다() throws Exception{
		cdp.publish(new DeleteEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(ocds.called, equalTo(1));
		ocds.called = 0;
		
		ccp.publish(new CreateEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(occs.called, equalTo(0));
		
		cup.publish(new UpdateEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(ocus.called, equalTo(0));
		
		cdp.publish(new DeleteEvent<Cat>(object));
		Thread.sleep(50);
		assertThat(ocds.called, equalTo(0));
	}
	
	static class Cat {}
	
	static class OCCS implements EventSubscriber<CreateEvent<Cat>>{
		private int called = 0;
		@Override
		public void subscribe(CreateEvent<Cat> event) {
			called ++;
//			System.out.println("received create event : " + event.getTarget().getClass().getSimpleName());
		}
	}

	static class OCUS implements EventSubscriber<UpdateEvent<Cat>>{
		private int called = 0;
		@Override
		public void subscribe(UpdateEvent<Cat> event) {
			called ++;
//			System.out.println("received update event : " + event.getTarget().getClass().getSimpleName());
		}
	}

	static class OCDS implements EventSubscriber<DeleteEvent<Cat>>{
		private int called = 0;
		@Override
		public void subscribe(DeleteEvent<Cat> event) {
			called ++;
//			System.out.println("received create event : " + event.getTarget().getClass().getSimpleName());
		}
	}
}