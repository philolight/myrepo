package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

public class SensorDtos {
	private SlmDto slm = new SlmDtos().get(0);
	
	public List<SensorDto> list = new ArrayList<>();
	
	public SensorDtos(){		
		for(int i = 1; i <= 3; i++) {
			SensorDto dto = new SensorDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setSensorId("sensorId" + i);
			dto.setSlmId(slm.getSlmId());
			dto.setName("sensorNm" + i);
		}
	}
	
	public SensorDto get(int index) { return list.get(index); }
	
	public List<SensorDto> getList() { return list; }
}
