package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ARoomDao;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.mapper.RoomDtoMapper;

public class RoomDao extends ARoomDao{
    public RoomDao(RoomDtoMapper mapper) {
        super(mapper);
    }
}
