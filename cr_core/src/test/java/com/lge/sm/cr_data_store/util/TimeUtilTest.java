package com.lge.sm.cr_data_store.util;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.lge.framework.ceasar.util.TimeUtil;
import com.lge.sm.cr_data_store.common.Constants;

public class TimeUtilTest {
	@Test
	public void stringTimeToUtcGmtTest(){
		String time = "20171102164700";
						
		String gmtTimeZoneId = "GMT+00:00";
		String gmt1TimeZoneId = "GMT+01:00";
		
		assertThat(TimeUtil.koreaTimeToLocal(time, gmtTimeZoneId, Constants.DEFAULT_DATE_FORMAT), equalTo("20171102074700"));
		assertThat(TimeUtil.koreaTimeToLocal(time, gmt1TimeZoneId, Constants.DEFAULT_DATE_FORMAT), equalTo("20171102084700"));
	}
	
	@Test
	public void testDayFromLocalTimeString(){
		String time = "20171122143456"; // 수요일
		
		SimpleDateFormat f = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		Date date = null;

		try {
			date = f.parse(time);
		} catch (ParseException e) {
			date = TimeUtil.DEFAULT_DATE;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
//		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		assertThat(c.get(Calendar.DAY_OF_WEEK), equalTo(4));
		assertThat(c.get(Calendar.DAY_OF_MONTH), equalTo(22));
		assertThat(c.get(Calendar.MONTH)+1, equalTo(11));
		assertThat(c.get(Calendar.YEAR), equalTo(2017));
	}
	
	@Test
	public void testTimeDifference() {
		String startTime = "20171010012356";
		String endTime = "20171010014556";
		
		SimpleDateFormat f = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		
		Date startDate = null;
		Date endDate = null;

		try {
			startDate = f.parse(startTime);
			endDate = f.parse(endTime);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			startDate = TimeUtil.DEFAULT_DATE;
			endDate = TimeUtil.DEFAULT_DATE;
		}
		
		assertThat((int)(TimeUnit.MILLISECONDS.toMinutes(endDate.getTime() - startDate.getTime())), equalTo(22));
	}
}
