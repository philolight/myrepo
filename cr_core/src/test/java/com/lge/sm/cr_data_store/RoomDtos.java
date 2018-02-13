package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;

public class RoomDtos {
	private LocationDto loc = new LocationDtos().get(0);
	public List<RoomDto> list = new ArrayList<>();
	
	public RoomDtos(){		
		for(int i = 1; i <= 3; i++) {
			RoomDto dto = new RoomDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setEnable(i);
			dto.setLocationId(loc.getLocationId());
			dto.setRoomId("room" + i);
			dto.setName("Name" + i);
			dto.setUdate("2017-10-11:11:46:0" + i);
		}
	}
	
	public RoomDto get(int index) { return list.get(index); }
	
	public List<RoomDto> getList() { return list; }
}
