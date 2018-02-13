package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AUserDao;
import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.mapper.UserDtoMapper;

public class UserDao extends AUserDao{
    public UserDao(UserDtoMapper mapper) {
        super(mapper);
    }
}
