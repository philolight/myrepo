package com.lge.sm.cr_data_store.util;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.lge.framework.ceasar.util.Factory;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class FactoryTest {
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
	public void locationTest(){
		LocationDto org = new LocationDto();
		org.setCdate("A");
		org.setLocationId("B");
		org.setName("C");
		org.setTimeZoneId("D");
		
		LocationDto copy = Factory.deepCopy(org);
		
		assertThat(org.getCdate(), equalTo(copy.getCdate()));
		assertThat(org.getLocationId(), equalTo(copy.getLocationId()));
		assertThat(org.getName(), equalTo(copy.getName()));
		assertThat(org.getTimeZoneId(), equalTo(copy.getTimeZoneId()));
	}
	
	@Test
	public void scheduleTest(){
		ScheduleDto org = new ScheduleDto();
		org.setCdate("A");
		org.setChkDuration(1);
		org.setLocalEhhmm("2017-10-11:11:46:45");
		org.setResult(3);
		org.setLocalShhmm("2017-10-11:11:46:45");
		
		ScheduleDto copy = Factory.deepCopy(org);
		
		assertThat(org.getCdate(), equalTo(copy.getCdate()));
		assertThat(org.getChkDuration(), equalTo(copy.getChkDuration()));
		assertThat(org.getLocalEhhmm(), equalTo(copy.getLocalEhhmm()));
		assertThat(org.getResult(), equalTo(copy.getResult()));
		assertThat(org.getLocalShhmm(), equalTo(copy.getLocalShhmm()));
		assertThat(org.getLocationId(), equalTo(copy.getLocationId()));
	}
	
	@Test
	public void testClassTest(){		
		SampleClass copy = Factory.deepCopy(sample);
		
		assertThat(sample.getA(), equalTo(copy.getA()));
		assertThat(sample.getB(), equalTo(copy.getB()));
		assertThat(sample.getS(), equalTo(copy.getS()));
		assertThat(sample.getBool(), equalTo(copy.getBool()));
		
		assertThat(sample.getaInt()[0], equalTo(copy.getaInt()[0]));
		assertThat(sample.getaInt()[1], equalTo(copy.getaInt()[1]));
		assertThat(sample.getaInt()[2], equalTo(copy.getaInt()[2]));
	}
}