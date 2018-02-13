package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AAuthorityLocationDao;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.mapper.AuthorityLocationDtoMapper;

public class AuthorityLocationDao extends AAuthorityLocationDao{
    public AuthorityLocationDao(AuthorityLocationDtoMapper mapper) {
        super(mapper);
    }
}
