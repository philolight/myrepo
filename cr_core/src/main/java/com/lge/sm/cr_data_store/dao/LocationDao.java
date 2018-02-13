package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ALocationDao;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.mapper.LocationDtoMapper;

public class LocationDao extends ALocationDao{
    public LocationDao(LocationDtoMapper mapper) {
        super(mapper);
    }
}
