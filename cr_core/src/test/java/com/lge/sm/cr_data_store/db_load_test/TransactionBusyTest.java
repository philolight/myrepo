package com.lge.sm.cr_data_store.db_load_test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.UserDtos;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.transaction.TxTestDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class TransactionBusyTest {
	@Autowired private TxTestDao sut;
	
	@Before
	public void setUp() {
		assertNotNull(sut);
				
		sut.deleteAll();
	}
	
	@After
	public void tearDown() {
		sut.deleteAll();
	}
	
	@Test
	public void testMultiThreadInsert() {
		List<ITestThread> ts = new ArrayList<ITestThread>();
		
		ts.add(new InsertThread(sut, 0));
		ts.add(new InsertThread(sut, 200));
		ts.add(new InsertThread(sut, 400));
		ts.add(new InsertThread(sut, 600));
		
		ts.forEach(each -> each.start());
		
		boolean done = true;
		while(done != true) {
			done = true;
			for(ITestThread each : ts) {
				if(each.done() == false) {
					done = false;
					break;
				}
			}
		}
		
		List<UserDto> users = sut.getUsers();
		List<LocationDto> locations = sut.getLocations();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		users = sut.getUsers();
		locations = sut.getLocations();
				
		assertThat(sut.getUsers(), hasSize(equalTo(40)));
		assertThat(sut.getLocations(), hasSize(equalTo(40)));
	}
	
	@Test
	public void testMultiThreadAccess() {
		List<ITestThread> ts = new ArrayList<ITestThread>();
		
		ts.add(new InsertThread(sut, 0));
		ts.add(new InsertThread(sut, 200));
		ts.add(new InsertThread(sut, 400));
		ts.add(new InsertThread(sut, 600));
		ts.add(new SelectThread(sut));
		ts.add(new DeleteThread(sut));
		
		ts.forEach(each -> each.start());
		
		boolean done = true;
		while(done != true) {
			done = true;
			for(ITestThread each : ts) {
				if(each.done() == false) {
					done = false;
					break;
				}
			}
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

interface ITestThread{
	public boolean done();
	public void start();
}

class InsertThread extends Thread implements ITestThread{
	private volatile boolean done = false;
	public synchronized boolean done() { return done; }
	
	private TxTestDao sut;
	private int start = 0;
	public InsertThread(TxTestDao sut, int start) {
		this.sut = sut;
		this.start = start;
	}
	
	@Override
	public void run() {
		for(int i = start; i < start + 10; i++) {
			LocationDto location = new LocationDto();
			location.setCdate("2017-10-11:11:46:45");
			location.setLocationId("loc" + i);
			location.setName("Ceasar");
			location.setTimeZoneId("A");
			
			UserDto user = new UserDto();
			user.setCdate("2017-10-11:11:46:45");
			user.setLastLoginTime("2017-10-11:11:46:45");
			user.setLoginCount(0L);
			user.setPassword("pw");
			user.setPwUdate("2017-10-11:11:46:45");
			user.setUserId("userId" + i);

			sut.noTransactionSuccess(location, user);
		}
		
		done = true;
	}
}

class SelectThread extends Thread implements ITestThread{
	private volatile boolean done = false;	
	public synchronized boolean done() { return done; }
	
	private TxTestDao sut;
	public SelectThread(TxTestDao sut) {
		this.sut = sut;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {		
			sut.getLocations();
			sut.getUsers();
		}
		
		done = true;
	}
}

class DeleteThread extends Thread implements ITestThread{
	private volatile boolean done = false;	
	public synchronized boolean done() { return done; }
	
	private TxTestDao sut;
	public DeleteThread(TxTestDao sut) {
		this.sut = sut;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i++) {		
			sut.deleteAll();
		}
		
		done = true;
	}
}