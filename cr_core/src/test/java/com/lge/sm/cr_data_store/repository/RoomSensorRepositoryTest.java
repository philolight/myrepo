package com.lge.sm.cr_data_store.repository;

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

import com.lge.sm.cr_data_store.RoomSensorDtos;
import com.lge.sm.cr_data_store.dao.RoomSensorDao;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class RoomSensorRepositoryTest {
	private RoomSensorDtos dtos = new RoomSensorDtos();
	
	@Autowired private RoomSensorRepository sut;
	@Autowired private RoomSensorDao dao;
	
	@Before
	public void setUp() {
		dao.deleteAll();
	}
	
	@Test
	public void testNextId_DB에데이터가하나도없을경우nextId는0이어야한다() {
		List<RoomSensorDto> list = dao.selectAll();
		assertThat(list, hasSize(0));
		
		sut.init();
		assertThat(sut.getNextId(), equalTo(0L));
	}
	
	@Test
	public void testNextId_DB에데이터가있을경우nextId는가장큰Id에1을더한값이어야한다() {
		dao.insert(dtos.getList());
		sut.init();
		
		long biggestId = -1;
		List<RoomSensorDto> list = dao.selectAll();
		for(RoomSensorDto each : list) if(biggestId < each.getRoomSensorId()) biggestId = each.getRoomSensorId();
		
		assertThat(sut.getNextId(), equalTo(biggestId + 1)); 
	}
	
	@After
	public void tearDown() {
		dao.deleteAll();
	}
}
