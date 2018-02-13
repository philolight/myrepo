package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ADriverTypeDao;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.mapper.DriverTypeDtoMapper;

public class DriverTypeDao extends ADriverTypeDao{
    public DriverTypeDao(DriverTypeDtoMapper mapper) {
        super(mapper);
    }
}
