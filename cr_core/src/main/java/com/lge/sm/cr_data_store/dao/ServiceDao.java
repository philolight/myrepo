package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AServiceDao;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.mapper.ServiceDtoMapper;

public class ServiceDao extends AServiceDao{
    public ServiceDao(ServiceDtoMapper mapper) {
        super(mapper);
    }
}
