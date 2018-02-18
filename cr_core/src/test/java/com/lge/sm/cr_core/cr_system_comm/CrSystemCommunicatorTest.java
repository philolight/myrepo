package com.lge.sm.cr_core.cr_system_comm;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.logger.LogLevel;
import com.lge.framework.ceasar.logger.Logger;
import com.lge.sm.cr_core.cr_task_manager.PropertyLoader;

public class CrSystemCommunicatorTest {
	private CrSystemCommunicator sut = CrSystemCommunicator.getInstance();
	private FakeScheduleSource fss = new FakeScheduleSource();
	
	@Before
	public void setUp() {
		Logger.init("log", LogLevel.DEBUG);
		sut.setScheduleSource(fss);
		
		PropertyLoader loader = new PropertyLoader();
		loader.initializePropertyManager();
	}
	
	@Test
	public void testDelete() {
		fss.deleteOk = true;
		fss.deleteReturnsNull = false;
		
//		public static String KEY_BODY_SCHEDULE_ID = "scheduleId";
//		public static String KEY_BODY_USER_ID = "userId";
		
		boolean ret = sut.deleteReservSchedule("userId", "scheduleId");

		assertThat(fss.userId, equalTo("userId"));
		assertThat(fss.scheId, equalTo("scheduleId"));
		assertThat(ret, equalTo(true));
		
		assertThat(sut.deleteReservSchedule(null, "scheduleId"), equalTo(false));
		
		fss.deleteOk = false;
		assertThat(sut.deleteReservSchedule("userId", "scheduleId"), equalTo(false));
		
		fss.deleteReturnsNull = false;
		assertThat(sut.deleteReservSchedule("userId", "scheduleId"), equalTo(false));
	}
	
	@Test
	public void testGetScheduleListByRoom() {
		PropertyLoader propertyLoader = new PropertyLoader();
		propertyLoader.initializePropertyManager();
		
		fss.getScheduleListByRoomReturnOk = true;

		assertThat(sut.getReservScheduleList("locationId", "roomId", "timezone"), hasSize(3));
		assertThat(sut.isExistScheduleId("locationId", "roomId", "timezone", "scheId1"), equalTo(true));
		
		assertThat(sut.getReservScheduleList(null, "roomId", "timezone"), hasSize(0));
		
		fss.getScheduleListByRoomReturnOk = false;
		assertThat(sut.getReservScheduleList("locationId", "roomId", "timezone"), hasSize(0));
		

	}
}
