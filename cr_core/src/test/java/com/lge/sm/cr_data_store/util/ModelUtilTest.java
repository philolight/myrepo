package com.lge.sm.cr_data_store.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.ceasar.util.ModelUtil;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class ModelUtilTest {
	private SampleClass sample = null;
	
	@Before
	public void setUp(){
		sample = new SampleClass();
		sample.setA(10);
		sample.setB(20L);
		sample.setS("S");
		sample.setBool(Boolean.TRUE);
		
		int [] aInt = new int[10];
		aInt[0] = 1;
		aInt[1] = 2;
		aInt[2] = 3;
		
		Short [] aShort = new Short[10];
		aShort[0] = Short.valueOf((short)1);
		aShort[1] = Short.valueOf((short)2);
		aShort[2] = Short.valueOf((short)3);
		
		sample.setaInt(aInt);
		sample.setaShort(aShort);
	}
	
	@Test
	public void testIsEqualTrue(){
		SampleClass copy = Factory.deepCopy(sample);
		
		assertTrue(ModelUtil.isEqual(sample, copy));
	}
	
	@Test
	public void testIsEqualFalse(){
		SampleClass copy = Factory.deepCopy(sample);
		
		sample.aInt[4] = 2;		
		assertFalse(ModelUtil.isEqual(sample, copy));
		
		sample.aInt[4] = 0;
		assertTrue(ModelUtil.isEqual(sample, copy));
		
		sample.aShort[4] = Short.valueOf((short) 4);		
		assertFalse(ModelUtil.isEqual(sample, copy));
		
		sample.aShort[4] = null;
		assertTrue(ModelUtil.isEqual(sample, copy));
		
		sample.aInt[0] = 100000000;		
		assertFalse(ModelUtil.isEqual(sample, copy));
	}
	
	@Test
	public void testHasNullField() {
		ScheduleDto sche = new ScheduleDto();
		
		sche.setCdate("2017-10-11:11:46:45");
		sche.setLocationId("loc1");
		sche.setRoomId("roomId1");
		sche.setChkDuration(1);
		sche.setDeptName("scheDeptNm1");
		sche.setEdate("2017-10-11:11:46:45");
		sche.setScheduleId("scheId1");
		sche.setLocalDayOfWeek(1234);
		sche.setLocalDay(1);
		sche.setLocalDuration(20);
		sche.setLocalEhhmm("2017-10-11:11:46:45");
		sche.setLocalMonth(1);
		sche.setLocalShhmm("2017-10-11:11:46:45");
		sche.setLocalYear(20);
		sche.setName("scheNm1");
		sche.setResult(1);
		sche.setSdate("2017-10-11:11:46:45");
		sche.setSensorCnt(1);
		sche.setTotalDetect(2);
		sche.setTotalSensor(3);
		sche.setUserId("userId");
		sche.setUserName("userNm");
		sche.setErrorCnt(1);
		sche.setResultDate("2017-10-11:11:46:45");
		
		assertThat(ModelUtil.hasNullFields(sche), equalTo(false));
		
		sche.setUserName(null);
		
		assertThat(ModelUtil.hasNullFields(sche), equalTo(true));
	}
}
