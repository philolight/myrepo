package com.lge.sm.cr_data_store.transaction;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class TransactionTest {
	@Autowired private TxTestDao sut;

	private LocationDto location;
	private UserDto user;
	
	@Before
	public void setUp() {
		assertNotNull(sut);
		
		location = new LocationDtos().get(0);		
		user = new UserDtos().get(0);
		
		sut.deleteAll();
	}
	
	@Test
	public void testNoTransactionFail() {
		sut.noTransactionSuccess(location, user);
		
		assertThat(sut.getLocations(), hasSize(1));
		assertThat(sut.getUsers(), hasSize(1));
	}
	
	@Test
	public void testNoTransactionHasException() {
		sut.noTransactionHasException(location, user);
		
		assertThat(sut.getLocations(), hasSize(0));
		assertThat(sut.getUsers(), hasSize(0));
	}
	
	@Test
	public void testTransactionFail_Rollback() {
		sut.transactionFail(location, user);
		assertThat(sut.getLocations(), hasSize(0)); // rollback required(size must be 0)
		assertThat(sut.getUsers(), hasSize(0));
	}
		
	@Test
	public void testTransactionFailReturnBoolean() {
		boolean ret = sut.transactionFailReturnBoolean(location, user);
		assertThat(ret, equalTo(false));
	}
	
	@Test
	public void testTransactionSuccessReturnBoolean() {
		boolean ret = sut.transactionSuccessReturnBoolean(location, user);
		assertThat(ret, equalTo(true));
	}
	
	@Test
	public void testTransactionFailReturnObject() {
		Object ret = sut.transactionFailReturnObject(location, user);
		assertThat(ret, equalTo(null));
	}
	
	@After
	public void tearDown() {
		sut.deleteAll();
	}
}