package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AAuthorityDao;
import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.mapper.AuthorityDtoMapper;

public class AuthorityDao extends AAuthorityDao{
    public AuthorityDao(AuthorityDtoMapper mapper) {
        super(mapper);
    }
}
