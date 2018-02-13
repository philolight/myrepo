package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ASensorDao;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.mapper.SensorDtoMapper;

public class SensorDao extends ASensorDao{
    public SensorDao(SensorDtoMapper mapper) {
        super(mapper);
    }
}
