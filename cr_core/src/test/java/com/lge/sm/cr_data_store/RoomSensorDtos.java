package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

public class RoomSensorDtos {
	private LocationDto loc = new LocationDtos().get(0);
	private RoomDto room = new RoomDtos().get(0);
	private SensorDto sensor = new SensorDtos().get(0);
	private SlmDto slm = new SlmDtos().get(0);
	
	public List<RoomSensorDto> list = new ArrayList<>();
	
	public RoomSensorDtos(){
		for(int i = 1; i <= 3; i++) {
			RoomSensorDto dto = new RoomSensorDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setRoomSensorId((long)i);
			dto.setLocationId(loc.getLocationId());
			dto.setRoomId(room.getRoomId());
			dto.setSensorId(sensor.getSensorId());
			dto.setStatus(1);
			dto.setUdate("2017-10-11:11:46:0" + i);
		}
	}
	
	public RoomSensorDto get(int index) { return list.get(index); }
	
	public List<RoomSensorDto> getList() { return list; }
}
