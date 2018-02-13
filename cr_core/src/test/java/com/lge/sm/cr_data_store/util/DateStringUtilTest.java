package com.lge.sm.cr_data_store.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.lge.framework.ceasar.util.DateStringUtil;

public class DateStringUtilTest {
	@Test
	public void testGetCurrentDateString() {
		System.out.println(DateStringUtil.getCurrentDateString("GMT"));
		System.out.println(DateStringUtil.getCurrentDateString("GMT+9:00"));
	}
	
	@Test
	public void testDate() {
		String current 	= "20180105012345";
		String prev 	= "20180104012345";
		String next 	= "20180106012345";
		assertThat(prev, equalTo(DateStringUtil.getPreviousDate(current)));
		assertThat(next, equalTo(DateStringUtil.getNextDate(current)));
	}
}
