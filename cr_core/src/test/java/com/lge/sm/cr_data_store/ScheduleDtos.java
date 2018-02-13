package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_core.common.ScheduleResult;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class ScheduleDtos {
	private LocationDto loc = new LocationDtos().get(0);
	private RoomDto room = new RoomDtos().get(0);
	public List<ScheduleDto> list = new ArrayList<>();
	
	public ScheduleDtos(){	
		for(int i = 1; i <= 3; i++) {
			ScheduleDto dto = new ScheduleDto();
			list.add(dto);
			
			dto.setCdate("2018010511460" + i);
			dto.setLocationId(loc.getLocationId());
			dto.setRoomId(room.getRoomId());
			dto.setChkDuration(1);
			dto.setDeptName("scheDeptNm" + i);
			dto.setSdate("20180105110000");
			dto.setEdate("20180105150000");
			dto.setScheduleId("scheId" + i);
			dto.setLocalDayOfWeek(1234);
			dto.setLocalDay(1);
			dto.setLocalDuration(20);
			dto.setLocalEhhmm("2017-10-11:11:46:45");
			dto.setLocalMonth(1);
			dto.setLocalShhmm("2017-10-11:11:46:45");
			dto.setLocalYear(20);
			dto.setName("scheNm" + i);
			dto.setResult(ScheduleResult.RESULT_VALID_BY_SENSED_TO_USE.getValue());
			dto.setSensorCnt(1);
			dto.setTotalDetect(2);
			dto.setTotalSensor(3);
			dto.setUserId("userId");
			dto.setUserName("userNm");
			dto.setErrorCnt(1);
			dto.setResultDate("20180105150000");
		}
	}
	
	public ScheduleDto get(int index) { return list.get(index); }
	
	public List<ScheduleDto> getList() { return list; }
}
