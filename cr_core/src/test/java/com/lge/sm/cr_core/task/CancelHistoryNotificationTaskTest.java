package com.lge.sm.cr_core.task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lge.framework.ceasar.repository.Repos;
import com.lge.sm.cr_data_store.LocationDtos;
import com.lge.sm.cr_data_store.RoomDtos;
import com.lge.sm.cr_data_store.ScheduleDtos;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.repository.CancelHistoryRepository;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class CancelHistoryNotificationTaskTest {
	private LocationDtos locationDtos = new LocationDtos();
	private RoomDtos roomDtos = new RoomDtos();
	
	private CancelHistoryTask cht;
	private CancelHistoryNotificationTask sut;
	private LocationEntity locationEntity1;
	private LocationEntity locationEntity2;
	private RoomEntity roomEntity1;
	private RoomEntity roomEntity2;
	
	@Before
	public void setUp() throws Exception{
		Repos.repo(CancelHistoryRepository.class).init();
		Repos.repo(CancelHistoryRepository.class).deleteAll();
		
		Repos.repo(ScheduleRepository.class).init();
		Repos.repo(ScheduleRepository.class).deleteAll();
			
		Repos.repo(RoomRepository.class).init();
		Repos.repo(RoomRepository.class).deleteAll();
		
		Repos.repo(LocationRepository.class).init();
		Repos.repo(LocationRepository.class).deleteAll();
		
		// Location을 두 개 생성한다.
		locationEntity1 = Repos.repo(LocationRepository.class).create(locationDtos.get(0));
		locationEntity2 = Repos.repo(LocationRepository.class).create(locationDtos.get(1));
		
		// Room을 두 개 생성한다.
		roomEntity1 = Repos.repo(RoomRepository.class).create(roomDtos.get(0));
		RoomDto roomDto = roomDtos.get(1);
		roomDto.setLocationId(locationEntity2.getLocationId());
		roomEntity2 = Repos.repo(RoomRepository.class).create(roomDto);
		
		cht = new CancelHistoryTask(Repos.repo(LocationRepository.class).getAll());
		sut = new CancelHistoryNotificationTask();
		sut.init();
		
		Thread.sleep(50); // wait to event publish
		
	}
	
	@After
	public void tearDown() {
		Repos.repo(CancelHistoryRepository.class).deleteAll();
		Repos.repo(ScheduleRepository.class).deleteAll();
		Repos.repo(RoomRepository.class).deleteAll();
		Repos.repo(LocationRepository.class).deleteAll();
	}
	
	@Test
	public void test() {
		cht.init();
	}
}
