package com.lge.sm.cr_core.task;

import static com.lge.framework.ceasar.util.DateStringUtil.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

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
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.entity.CancelHistoryEntity;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.entity.RoomEntity;
import com.lge.sm.cr_data_store.repository.CancelHistoryRepository;
import com.lge.sm.cr_data_store.repository.LocationRepository;
import com.lge.sm.cr_data_store.repository.RoomRepository;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;

/**
 *  CancelHistoryTask는 시스템이 시작되었을 때 모든 Location에 대한 CancelHistory들이 어느 시점까지 계산되었는지 확인한다.
 *  그리고 마지막으로 계산된 CancelHistory 날짜 이후부터 현재 시점 기준으로 전날까지의 CancelHistory를 계산한다.
 *  만약 해당 Location에 대한 CancelHistory가 하나도 없으면  Schedule이 처음 저장된 날짜로부터 CancelHistory를 계산한다.
 *  만약 해당 Location의 CancelHistory도 없고 Schedule도 없으면 시작 시에는 아무 것도 계산하지 않는다.
 *  시작시 할 일들이 종료 되면 매 주기(30분)마다 어떤 Location이 현지 시각 기준으로 0시 0분인지 확인하고,(즉 하루가 바뀌었는지 확인하고)
 *  0시 0분인 Location들에 대해서 현재 시간 기준으로 어제의 CancelHistory를 계산한다.
 *  
 *  CancelHistoryDto의 dateOf 필드는 항상 리포트 대상 날짜의 CancelHistory의 0시 0분 0초이다.(CancelHistory를 계산한 시점이 아님을 주의할 것)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/lge/sm/cr_data_store/data_store_test_context.xml"})
public class CancelHistoryTaskTest{
	private LocationDtos locationDtos = new LocationDtos();
	private RoomDtos roomDtos = new RoomDtos();
	private ScheduleDtos scheduleDtos = new ScheduleDtos();
	
	private CancelHistoryTask sut;
	private LocationEntity locationEntity;
	private RoomEntity roomEntity;
	
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
		
		// Location을 한 개 생성한다.
		locationEntity = Repos.repo(LocationRepository.class).create(locationDtos.get(0));
		
		// Room을 한 개 생성한다.
		roomEntity = Repos.repo(RoomRepository.class).create(roomDtos.get(0));
		
		sut = new CancelHistoryTask(Repos.repo(LocationRepository.class).getAll());
		
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
	public void testCreateLocation_Location이새로생성되었을때locations가증가해야한다() throws Exception{			
		assertThat(sut.locations.size(), equalTo(1));
		
		assertThat(sut.locations.get(locationEntity.mapKey()).succeed, equalTo(false));
	}
	
	@Test
	public void testCreateLocation_Location이새로갱신되었을때locations가갱신되어야한다() throws Exception{
		sut.init();
		
		locationEntity.setName("locName2");
		Repos.repo(LocationRepository.class).update(locationEntity);
		Thread.sleep(50);
		
		assertThat(sut.locations.size(), equalTo(1));
		assertThat(sut.locations.get(locationEntity.mapKey()).location.getName(), equalTo(locationEntity.getName()));
	}
	
	@Test
	public void testCreateLocation_Location이삭제되었을때locations가삭제되어야한다() throws Exception{
		sut.init();
		
		Repos.repo(LocationRepository.class).delete(locationEntity);
		Thread.sleep(50);
		
		assertThat(sut.locations.size(), equalTo(0));
	}
	
	@Test
	public void testNoCancelHistoryAndNoSchedule_CancelHistory와Schedule이없으면CancelHistory를새로생성하지않는다() throws Exception{
		Repos.repo(CancelHistoryRepository.class).init();
		
		sut.init();
		
		Thread.sleep(50);
		
		List<CancelHistoryEntity> list = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(list, hasSize(0));
	}
	
	@Test
	public void testWithTwoDaysAgoSchedule_CancelHistory가없고2일전Schedule정보가있다면2일간의CancelHistory를생성해야한다() throws Exception{
		Repos.repo(CancelHistoryRepository.class).init();
				
		List<CancelHistoryEntity> cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(0));
		
		ScheduleDto schedule = scheduleDtos.get(0);
		String today = getCurrentDateString(locationEntity.getTimeZoneId());
		today = getStartDate(today);
		String twoDaysAgo = getPreviousDate(getPreviousDate(today));
		schedule.setSdate(twoDaysAgo);
		schedule.setEdate(twoDaysAgo);
		Repos.repo(ScheduleRepository.class).create(schedule);
		
		sut.onStart();
		
		cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(2));
	}
	
	@Test
	public void testWithTwoDaysAgoCancelHistory_3일전의CancelHistory가있을경우2일간의CancelHistory를새로생성해야한다() throws Exception{
		Repos.repo(CancelHistoryRepository.class).init();
		
		List<CancelHistoryEntity> cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(0));

		String today = getCurrentDateString(locationEntity.getTimeZoneId());
		String threeDaysAgo = getPreviousDate(getPreviousDate(getPreviousDate(today)));
		
		CancelHistoryDto chd = new CancelHistoryDto();
		chd.setCancelMinutes(0);
		chd.setCancelRate(0.0f);
		chd.setCancels(0);
		chd.setReuses(0);
		chd.setCdate(threeDaysAgo);
		chd.setDateOf(threeDaysAgo); // date_of를 3일 전으로 설정한 CancelHistoryDto를 생성한다.
		chd.setLocationId(locationEntity.getLocationId());
		chd.setReservations(0);
		
		Repos.repo(CancelHistoryRepository.class).create(chd);
		
		sut.onStart();
		
		cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(3)); // 3일전의 CancelHistory 1개 + 생성된 2개의 CancelHistory
	}
	
	@Test
	public void testDayChanged_날짜가바뀌면어제의CancelHistory를새로생성해야한다() throws Exception{
		List<CancelHistoryEntity> cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(0));

		String today = getStartDate(getCurrentDateString(locationEntity.getTimeZoneId()));
		String oneDayAgo = getPreviousDate(today);
		
		CancelHistoryDto chd = new CancelHistoryDto();
		chd.setCancelMinutes(0);
		chd.setCancelRate(0.0f);
		chd.setCancels(0);
		chd.setReuses(0);
		chd.setCdate(oneDayAgo);
		chd.setDateOf(oneDayAgo); // date_of를 3일 전으로 설정한 CancelHistoryDto를 생성한다.
		chd.setLocationId(locationEntity.getLocationId());
		chd.setReservations(0);
		Repos.repo(CancelHistoryRepository.class).create(chd);
					
		cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(1));
		
		TestTodayStringGenerator gen = new TestTodayStringGenerator();
		gen.testToday = getNextDate(today); // 내일 날짜를 '오늘'로 입력. 하루가 지났음을 설정한다.
		sut.setTimeChecker(gen);
		sut.run();
		
		cancelHistories = Repos.repo(CancelHistoryRepository.class).getByCriteria(Repos.repo(CancelHistoryRepository.class).criteria());
		assertThat(cancelHistories, hasSize(2)); // 3일전의 CancelHistory 1개 + 생성된 1개의 CancelHistory
	}
	
	class TestTodayStringGenerator extends TimeChecker{
		private String testToday;
		@Override
		public String generate(String timeZoneId) {
			return testToday;
		}
		
		@Override
		public boolean dateChanged(String timeZoneId) {
			return true;
		}
	}
}
