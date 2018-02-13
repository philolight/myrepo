package com.lge.framework.ceasar.event;

import org.junit.After;
import org.junit.Test;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;

public class TypeMismatchTest {
	private static CCS ccs = new CCS();
	private static CUS cus = new CUS();	
				
	@Test(expected = IllegalArgumentException.class)
	public void typeMismatchTest1_Subscriber등록시Subscriber의타입과등록하려는이벤트타입이매치되지않으면Exception이발생한다(){		
		EventBroker.subscribe(Cat.class, UpdateEvent.class, ccs); // EventType Mismatch
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void typeMismatchTest2_Subscriber등록시Subscriber의타입과등록하려는이벤트타입이매치되지않으면Exception이발생한다(){		
		EventBroker.subscribe(Cat.class, CreateEvent.class, cus); // EventType Mismatch
	}
			
	static class Cat {}
	static class Tiger {}

	static class CCS implements EventSubscriber<CreateEvent<Cat>>{
		@Override public void subscribe(CreateEvent<Cat> event) {}
	}

	static class CUS implements EventSubscriber<UpdateEvent<Cat>>{
		@Override public void subscribe(UpdateEvent<Cat> event) {}
	}

	static class TCS implements EventSubscriber<CreateEvent<Tiger>>{
		@Override public void subscribe(CreateEvent<Tiger> event) {}
	}

	static class TUS implements EventSubscriber<UpdateEvent<Tiger>>{
		@Override public void subscribe(UpdateEvent<Tiger> event) {}
	}
}
