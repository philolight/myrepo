package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AUserAuthorityDao;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.mapper.UserAuthorityDtoMapper;

public class UserAuthorityDao extends AUserAuthorityDao{
    public UserAuthorityDao(UserAuthorityDtoMapper mapper) {
        super(mapper);
    }
}
