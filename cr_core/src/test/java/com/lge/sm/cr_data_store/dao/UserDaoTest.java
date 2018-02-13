package com.lge.sm.cr_data_store.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.sm.cr_data_store.UserDtos;
import com.lge.sm.cr_data_store.dto.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class UserDaoTest {
	private UserDtos dtos;
	@Autowired
	private UserDao sut;
	
	@Before
	public void setUp(){
		dtos = new UserDtos();
		
		assertNotNull(sut);
		
		sut.deleteAll();
	}
	
	@After
	public void tearDown(){
		sut.deleteAll();
	}
	
	@Test
	public void testEmpty(){
		assertThat(sut.selectAll(), hasSize(0));
	}
			
	@Test
	public void testInsert(){
		assertThat(sut.insert(dtos.get(0)), equalTo(Boolean.TRUE));
		
		sut.insert(Arrays.asList(dtos.get(1), dtos.get(2)));
		List<UserDto> list = sut.selectAll();
		assertThat(list, hasSize(3));
	}
	
	@Test
	public void testSelect(){
		sut.insert(dtos.getList());

		UserDto ret = sut.select(dtos.get(0).getUserId());
		assertThat(dtos.get(0).getUserId(), equalTo(ret.getUserId()));
		
		ret = sut.select(dtos.get(1).getUserId());
		assertThat(dtos.get(1).getUserId(), equalTo(ret.getUserId()));
		
		ret = sut.select(dtos.get(2).getUserId());
		assertThat(dtos.get(2).getUserId(), equalTo(ret.getUserId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(dtos.getList());
		dtos.get(0).setPassword(dtos.get(0).getPassword() + "difference");
		dtos.get(1).setPassword(dtos.get(1).getPassword() + "difference");
		dtos.get(2).setPassword(dtos.get(2).getPassword() + "difference");
		
		sut.update(dtos.get(0));
		sut.update(Arrays.asList(dtos.get(1), dtos.get(2)));
		
		UserDto record = sut.select(dtos.get(0).getUserId());		
		assertThat(dtos.get(0).getPassword(), equalTo(record.getPassword()));
		
		record = sut.select(dtos.get(1).getUserId());		
		assertThat(dtos.get(1).getPassword(), equalTo(record.getPassword()));
		
		record = sut.select(dtos.get(2).getUserId());
		assertThat(dtos.get(2).getPassword(), equalTo(record.getPassword()));
	}
	
	@Test
	public void testDelete(){
		sut.insert(dtos.getList());
		assertThat(sut.selectAll(), hasSize(3));
		
		sut.delete(dtos.get(0));
		assertThat(sut.selectAll(), hasSize(2));
		
		sut.delete(Arrays.asList(dtos.get(1), dtos.get(2)));
		assertThat(sut.selectAll(), hasSize(0));
	}
	
	@Test
	public void testTransactionRollback(){
		dtos.get(0).setUserId(dtos.get(1).getUserId()); // make primary key conflict
		assertThat(sut.insert(dtos.getList()), equalTo(false));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
