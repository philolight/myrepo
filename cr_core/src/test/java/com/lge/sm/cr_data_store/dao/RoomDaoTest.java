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

import com.lge.framework.ceasar.util.ToString;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.RoomDtos;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class RoomDaoTest {
	private RoomDtos vos = new RoomDtos();
	private LocationDto loc = new LocationDtos().get(0);
	
	@Autowired
	private LocationDao locationDao = null;
	
	@Autowired
	private RoomDao sut = null;
		
	@Before
	public void setUp(){
		assertNotNull(sut);
		
		sut.deleteAll();
						
		locationDao.insert(loc);
	}
	
	@After
	public void tearDown(){
		sut.deleteAll();
		locationDao.deleteAll();
	}
	
	@Test
	public void testEmpty(){
		assertThat(sut.selectAll(), hasSize(0));
	}
		
	@Test
	public void testInsert(){
		assertThat(sut.insert(vos.get(0)), equalTo(Boolean.TRUE));
		assertThat(sut.selectAll(), hasSize(1));
		
		sut.insert(Arrays.asList(vos.get(1), vos.get(2)));
		assertThat(sut.selectAll(), hasSize(3));
	}
	
	@Test
	public void testSelect(){
		sut.insert(vos.getList());

		RoomDto ret = sut.select(vos.get(0).getRoomId());

		assertThat(vos.get(0).getRoomId(), equalTo(ret.getRoomId()));
		assertThat(vos.get(0).getLocationId(), equalTo(ret.getLocationId()));
		
		ret = sut.select(vos.get(1).getRoomId());
		assertThat(vos.get(1).getRoomId(), equalTo(ret.getRoomId()));
		assertThat(vos.get(1).getLocationId(), equalTo(ret.getLocationId()));
		
		ret = sut.select(vos.get(2).getRoomId());
		assertThat(vos.get(2).getRoomId(), equalTo(ret.getRoomId()));
		assertThat(vos.get(2).getLocationId(), equalTo(ret.getLocationId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(vos.getList());
		vos.get(0).setName(vos.get(0).getName() + "difference");
		vos.get(1).setName(vos.get(1).getName() + "difference");
		vos.get(2).setName(vos.get(2).getName() + "difference");
		
		sut.update(vos.get(0));
		sut.update(Arrays.asList(vos.get(1), vos.get(2)));
		
		RoomDto record = sut.select(vos.get(0).getRoomId());	
		assertThat(vos.get(0).getName(), equalTo(record.getName()));
		
		record = sut.select(vos.get(1).getRoomId());
		assertThat(vos.get(1).getName(), equalTo(record.getName()));
		
		record = sut.select(vos.get(2).getRoomId());		
		assertThat(vos.get(2).getName(), equalTo(record.getName()));
	}
	
	@Test
	public void testDelete(){
		sut.insert(vos.getList());
		assertThat(sut.selectAll(), hasSize(3));
		
		sut.delete(vos.get(0));
		assertThat(sut.selectAll(), hasSize(2));
		
		sut.delete(Arrays.asList(vos.get(1), vos.get(2)));		
		assertThat(sut.selectAll(), hasSize(0));
	}
	
	@Test
	public void testTransactionRollback(){
		vos.get(1).setLocationId(vos.get(0).getLocationId()); 	// make primary key conflict
		vos.get(1).setRoomId(vos.get(0).getRoomId()); 	// make primary key conflict
		assertThat(sut.insert(Arrays.asList(vos.get(0), vos.get(1), vos.get(2))), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
