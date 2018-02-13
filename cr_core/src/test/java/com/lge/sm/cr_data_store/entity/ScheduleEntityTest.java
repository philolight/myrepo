package com.lge.sm.cr_data_store.entity;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.RoomDtos;
import com.lge.sm.cr_data_store.ScheduleDtos;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class ScheduleEntityTest {
	LocationDtos locationDtos = new LocationDtos();
	RoomDtos roomDtos = new RoomDtos();
	ScheduleDtos dtos = new ScheduleDtos();
	
	@Autowired private ScheduleRepository repo;
	@Autowired private RoomRepository roomRepo;
	@Autowired private LocationRepository locationRepo;
	private ScheduleEntity sut;
	
	@Before
	public void setUp() {
		locationRepo.init();
		locationRepo.deleteAll();
		locationRepo.create(locationDtos.get(0));
		roomRepo.init();
		roomRepo.deleteAll();
		roomRepo.create(roomDtos.get(0));
		repo.create(dtos.get(0));
		sut = repo.getByCriteria(repo.criteria().andLocationIdEqualTo(dtos.get(0).getLocationId()).andRoomIdEqualTo(dtos.get(0).getRoomId())).get(0);
	}
	
	@Test
	public void testMapKey() {
		MapKey key1 = sut.mapKey();
		MapKey key2 = ScheduleEntity.newMapKey(sut.getDto());
		assertThat(key1, equalTo(key2));
	}
		
	@After
	public void tearDown() {
		repo.deleteAll();
		roomRepo.deleteAll();
		locationRepo.deleteAll();
	}
}
