package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.lge.sm.cr_data_store.dto.LocationDto;

public class LocationDtos{
	public List<LocationDto> list = new ArrayList<>();
	
	public LocationDtos(){		
		for(int i = 1; i <= 3; i++) {
			LocationDto dto = new LocationDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setLocationId("loc" + i);
			dto.setName("Name" + i);
			dto.setTimeZoneId(TimeZone.getDefault().getID());
		}
	}
	
	public LocationDto get(int index) { return list.get(index); }
	
	public List<LocationDto> getList() { return list; }
}
