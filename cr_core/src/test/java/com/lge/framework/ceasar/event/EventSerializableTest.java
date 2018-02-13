package com.lge.framework.ceasar.event;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.Event;
import com.lge.sm.cr_data_store.UserDtos;
import com.lge.sm.cr_data_store.dao.UserDao;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class EventSerializableTest {
	UserDtos dtos = new UserDtos();
	@Autowired
	private UserDao dao;

	@Autowired
	private UserRepository sut;

	@Before
	public void setUp() {
		dao.deleteAll();
	}

	@After
	public void tearDown() {
		dao.deleteAll();
	}

	@Test
	public void testEventSerialzable() throws Exception{
		String tmpFileName = "serializedTest.txt";
		dao.insert(dtos.getList());

		sut.init();

		UserEntity entity = sut.get(dtos.get(0));

		CreateEvent<UserEntity> event = new CreateEvent<>(entity);

		Event<?> e = (Event<?>) event;

		//Saving of object in a file
		FileOutputStream fos = new FileOutputStream(tmpFileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);

		// Method for serialization of B's class object
		oos.writeObject(event);

		// closing streams
		oos.close();
		fos.close();

		// Reading the object from a file
		FileInputStream fis = new FileInputStream(tmpFileName);
		ObjectInputStream ois = new ObjectInputStream(fis);

		// Method for de-serialization of B's class object

		Event<?> e2 = (Event<?>) ois.readObject();
		
		// closing streams
		ois.close();
		fis.close();
		
		File f = new File(tmpFileName);
		f.delete();
		
		CreateEvent<UserEntity> event2 = (CreateEvent<UserEntity>)e2;

		assertThat(event2.getClass(), equalTo(event.getClass()));
		assertThat(event2.getTarget().getUserId(), equalTo(entity.getUserId()));
		assertThat(event2.getTarget(), equalTo(entity));
		
		Thread.sleep(50); // to end
	}
}

class Cat implements Serializable{
	private static final long serialVersionUID = -6087011866909689129L;
	String eye = "";
	Cat(String eye){
		this.eye = eye;
	}
}
