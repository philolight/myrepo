package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AServiceAuthorityDao;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.mapper.ServiceAuthorityDtoMapper;

public class ServiceAuthorityDao extends AServiceAuthorityDao{
    public ServiceAuthorityDao(ServiceAuthorityDtoMapper mapper) {
        super(mapper);
    }
}
