package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ARoomSensorDao;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.mapper.RoomSensorDtoMapper;

public class RoomSensorDao extends ARoomSensorDao{
    public RoomSensorDao(RoomSensorDtoMapper mapper) {
        super(mapper);
    }
}
