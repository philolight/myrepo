package com.lge.framework.ceasar.domain.service;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.DeleteEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;
import com.lge.framework.ceasar.util.ToString;
import com.lge.sm.cr_data_store.UserDtos;
import com.lge.sm.cr_data_store.dao.UserDao;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.UserRepository;

/** framework.domain.service가 정상 동작하는지 테스트한다. */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class EntityServiceTest {
	private UserDtos dtos;	
	@Autowired private UserRepository userRepo;
	
	CES ces = new CES();
	UES ues = new UES();
	DES des = new DES();
	
	@Before
	public void setUp() throws Exception{
		dtos = new UserDtos();
		userRepo.init();
		userRepo.deleteAll();
	}
	
	@After
	public void tearDown() {
		userRepo.deleteAll();
	}
		
	@Test
	public void testCUD() throws Exception{
		userRepo.init();
		
		EventBroker.subscribe(UserEntity.class, CreateEvent.class, ces);
		EventBroker.subscribe(UserEntity.class, UpdateEvent.class, ues);
		EventBroker.subscribe(UserEntity.class, DeleteEvent.class, des);
		
		userRepo.create(dtos.get(0));
		UserEntity entity = userRepo.get(dtos.get(0));
		userRepo.update(entity);
		userRepo.delete(entity);

		Thread.sleep(100);

		assertThat(ces.called, equalTo(1));
		assertThat(ues.called, equalTo(1));
		assertThat(des.called, equalTo(1));
	}
	
	static class CES implements EventSubscriber<CreateEvent<UserEntity>>{
		int called = 0;
		@Override public void subscribe(CreateEvent<UserEntity> event) {
			called ++; 
		}
	}

	static class UES implements EventSubscriber<UpdateEvent<UserEntity>>{
		int called = 0;
		@Override public void subscribe(UpdateEvent<UserEntity> event) {
			called ++;
		}
	}
	
	static class DES implements EventSubscriber<DeleteEvent<UserEntity>>{
		int called = 0;
		@Override public void subscribe(DeleteEvent<UserEntity> event) {
			called++; 
		}
	}
}
