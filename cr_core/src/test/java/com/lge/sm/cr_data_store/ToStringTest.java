package com.lge.sm.cr_data_store;

import org.junit.Test;

import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.ceasar.util.ToString;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class ToStringTest {	
	@Test
	public void toLineTest(){
		ScheduleDto sche = new ScheduleDto();
		Factory.setDefaultFields(sche);
		
		System.out.println(ToString.toLine(sche));
	}
	
	@Test
	public void toStringTest(){
		ScheduleDto sche = new ScheduleDto();
		Factory.setDefaultFields(sche);
		
		System.out.println(ToString.toString(sche));
	}
}
