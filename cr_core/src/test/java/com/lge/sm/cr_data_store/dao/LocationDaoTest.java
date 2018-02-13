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

import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.dto.LocationDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class LocationDaoTest {
	private LocationDtos dtos;
	@Autowired
	private LocationDao sut;
	
	@Before
	public void setUp(){
		dtos = new LocationDtos();
		
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
		List<LocationDto> list = sut.selectAll();
		assertThat(list, hasSize(3));
	}
	
	@Test
	public void testSelect(){
		sut.insert(dtos.getList());

		LocationDto ret = sut.select(dtos.get(0).getLocationId());
		assertThat(dtos.get(0).getLocationId(), equalTo(ret.getLocationId()));
		
		ret = sut.select(dtos.get(1).getLocationId());
		assertThat(dtos.get(1).getLocationId(), equalTo(ret.getLocationId()));
		
		ret = sut.select(dtos.get(2).getLocationId());
		assertThat(dtos.get(2).getLocationId(), equalTo(ret.getLocationId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(dtos.getList());
		dtos.get(0).setName(dtos.get(0).getName() + "difference");
		dtos.get(1).setName(dtos.get(1).getName() + "difference");
		dtos.get(2).setName(dtos.get(2).getName() + "difference");
		
		sut.update(dtos.get(0));
		sut.update(Arrays.asList(dtos.get(1), dtos.get(2)));
		
		LocationDto record = sut.select(dtos.get(0).getLocationId());		
		assertThat(dtos.get(0).getName(), equalTo(record.getName()));
		
		record = sut.select(dtos.get(1).getLocationId());		
		assertThat(dtos.get(1).getName(), equalTo(record.getName()));
		
		record = sut.select(dtos.get(2).getLocationId());
		assertThat(dtos.get(2).getName(), equalTo(record.getName()));
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
		dtos.get(0).setLocationId(dtos.get(1).getLocationId()); // make primary key conflict
		assertThat(sut.insert(dtos.getList()), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
