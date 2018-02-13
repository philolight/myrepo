package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.SlmDto;

public class SlmDtos {
	public List<SlmDto> list = new ArrayList<>();
	
	public SlmDtos(){		
		for(int i = 1; i <= 3; i++) {
			SlmDto dto = new SlmDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setSlmId("slmId" + i);
			dto.setIp("192.168.0.1");
			dto.setPort(1234);
			dto.setProtocol("TCP/IP");
			dto.setUseAuth(0);
			dto.setUserId("slmUser" + i);
			dto.setUserPw("slmPw");
		}
	}
	
	public SlmDto get(int index) { return list.get(index); }
	
	public List<SlmDto> getList() { return list; }
}
