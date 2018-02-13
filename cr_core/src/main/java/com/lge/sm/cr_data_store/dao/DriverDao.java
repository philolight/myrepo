package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ADriverDao;
import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.mapper.DriverDtoMapper;

public class DriverDao extends ADriverDao{
    public DriverDao(DriverDtoMapper mapper) {
        super(mapper);
    }
}
