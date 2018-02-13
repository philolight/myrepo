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
import com.lge.sm.cr_data_store.RoomSensorDtos;
import com.lge.sm.cr_data_store.RoomDtos;
import com.lge.sm.cr_data_store.SensorDtos;
import com.lge.sm.cr_data_store.SlmDtos;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class RoomSensorDaoTest {
	private RoomSensorDtos dtos = new RoomSensorDtos();
	
	@Autowired private RoomSensorDao sut;	
	@Autowired private LocationDao locDAO;	
	@Autowired private RoomDao roomDao;	
	@Autowired private SensorDao sensorDao;	
	@Autowired private SlmDao slmDao;
	
	private SlmDto slm = new SlmDtos().get(0);
	private LocationDto loc = new LocationDtos().get(0);
	private RoomDto room = new RoomDtos().get(0);
	private SensorDto sensor = new SensorDtos().get(0);
		
	@Before
	public void setUp(){
		assertNotNull(sut);
		
		assertNotNull(slmDao);		
		assertNotNull(locDAO);		
		assertNotNull(roomDao);				
		assertNotNull(sensorDao);
		
		sut.deleteAll();
		roomDao.deleteAll();
		sensorDao.deleteAll();
		slmDao.deleteAll();
		locDAO.deleteAll();
		
		locDAO.insert(loc);
		roomDao.insert(room);
		slmDao.insert(slm);
		sensorDao.insert(sensor);
	}
	
	@After
	public void tearDown(){
		sut.deleteAll();
		roomDao.deleteAll();
		sensorDao.deleteAll();
		slmDao.deleteAll();
		locDAO.deleteAll();
	}
	
	@Test
	public void testEmpty(){
		List<RoomSensorDto> list = sut.selectAll();
		assertThat(list.size(), equalTo(0));
	}
			
	@Test
	public void testInsert(){
		assertThat(sut.insert(dtos.get(0)), equalTo(Boolean.TRUE));
		sut.insert(Arrays.asList(dtos.get(1), dtos.get(2)));
		List<RoomSensorDto> list = sut.selectAll();
		assertThat(list, hasSize(3));
	}
	
	@Test
	public void testSelect(){		
		sut.insert(dtos.getList());
		
		List<RoomSensorDto> list = sut.selectAll();
				
		RoomSensorDto ret = sut.select(dtos.get(0).getRoomSensorId());
		assertThat(dtos.get(0).getLocationId(), equalTo(ret.getLocationId()));
		ret = sut.select(dtos.get(1).getRoomSensorId());
		assertThat(dtos.get(1).getLocationId(), equalTo(ret.getLocationId()));
		ret = sut.select(dtos.get(2).getRoomSensorId());
		assertThat(dtos.get(2).getLocationId(), equalTo(ret.getLocationId()));
	}
	
	@Test
	public void testUpdate(){
		sut.insert(dtos.getList());
		dtos.get(0).setStatus(dtos.get(0).getStatus() + 1);
		dtos.get(1).setStatus(dtos.get(1).getStatus() + 1);
		dtos.get(2).setStatus(dtos.get(2).getStatus() + 1);
		
		sut.update(dtos.get(0));
		sut.update(Arrays.asList(dtos.get(1), dtos.get(2)));
		
		RoomSensorDto record = sut.select(dtos.get(0).getRoomSensorId());
		assertThat(dtos.get(0).getStatus(), equalTo(record.getStatus()));
		
		record = sut.select(dtos.get(1).getRoomSensorId());		
		assertThat(dtos.get(1).getStatus(), equalTo(record.getStatus()));
		
		record = sut.select(dtos.get(2).getRoomSensorId());		
		assertThat(dtos.get(2).getStatus(), equalTo(record.getStatus()));
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
		dtos.get(1).setRoomSensorId(dtos.get(0).getRoomSensorId()); 		// make primary key conflict
		assertThat(sut.insert(Arrays.asList(dtos.get(0), dtos.get(1), dtos.get(2))), equalTo(Boolean.FALSE));
		assertThat("assert list.size() == 0 by rollback", sut.selectAll(), hasSize(0));
	}
}
