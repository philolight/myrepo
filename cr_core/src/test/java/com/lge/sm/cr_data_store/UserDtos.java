package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.List;

import com.lge.sm.cr_data_store.dto.UserDto;

public class UserDtos{
	public List<UserDto> list = new ArrayList<>();
	
	public UserDtos(){
		for(int i = 1; i <= 3; i++) {
			UserDto dto = new UserDto();
			list.add(dto);
			
			dto.setCdate("2017-10-11:11:46:0" + i);
			dto.setLastLoginTime("2017-10-11:11:46:0" + i);
			dto.setLoginCount(0L);
			dto.setPassword("aaa"+i);
			dto.setPwUdate("2017-10-11:11:46:0" + i);
			dto.setUserId("userId" + i);
		}
	}
	
	public UserDto get(int index) { return list.get(index); }
	
	public List<UserDto> getList() { return list; }
}
