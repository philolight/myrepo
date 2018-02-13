package com.lge.sm.cr_data_store.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.sm.cr_data_store.SlmDtos;
import com.lge.sm.cr_data_store.dto.SlmDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class SlmDaoTest {
	private SlmDtos dtos = new SlmDtos();
	
	@Autowired private SlmDao sut = null;

	@Before
	public void setUp(){
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
		assertThat(sut.selectAll(), hasSize(3));
	}
	
	@Test
	public void testSelect(){
		sut.insert(Arrays.asList(dtos.get(0), dtos.get(1), dtos.get(2)));

		SlmDto ret = sut.select(dtos.get(0).getSlmId());
		assertThat(dtos.get(0).getSlmId(), equalTo(ret.getSlmId()));
		
		ret = sut.select(dtos.get(1).getSlmId());
		assertThat(dtos.get(1).getSlmId(), equalTo(ret.getSlmId()));
		
		ret = sut.select(dtos.get(2).getSlmId());
		assertThat(dtos.get(2).getSlmId(), equalTo(ret.getSlmId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(Arrays.asList(dtos.get(0), dtos.get(1), dtos.get(2)));
		dtos.get(0).setUserId(dtos.get(0).getUserId() + "difference");
		dtos.get(1).setUserId(dtos.get(1).getUserId() + "difference");
		dtos.get(2).setUserId(dtos.get(2).getUserId() + "difference");
		
		sut.update(dtos.get(0));
		sut.update(Arrays.asList(dtos.get(1), dtos.get(2)));
		
		SlmDto record = sut.select(dtos.get(0).getSlmId());		
		assertThat(dtos.get(0).getSlmId(), equalTo(record.getSlmId()));
		
		record = sut.select(dtos.get(1).getSlmId());		
		assertThat(dtos.get(1).getSlmId(), equalTo(record.getSlmId()));
		
		record = sut.select(dtos.get(2).getSlmId());		
		assertThat(dtos.get(2).getSlmId(), equalTo(record.getSlmId()));
	}
	
	@Test
	public void testDelete(){
		sut.insert(Arrays.asList(dtos.get(0), dtos.get(1), dtos.get(2)));
		assertThat(sut.selectAll(), hasSize(3));
		
		sut.delete(dtos.get(0));
		assertThat(sut.selectAll(), hasSize(2));
		
		sut.delete(Arrays.asList(dtos.get(1), dtos.get(2)));
		assertThat(sut.selectAll(), hasSize(0));
	}
	
	@Test
	public void testTransactionRollback(){
		dtos.get(1).setSlmId(dtos.get(0).getSlmId()); // make primary key conflict
		assertThat(sut.insert(Arrays.asList(dtos.get(0), dtos.get(1), dtos.get(2))), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
