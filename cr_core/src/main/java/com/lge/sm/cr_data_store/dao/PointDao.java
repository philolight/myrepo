package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APointDao;
import com.lge.sm.cr_data_store.dto.PointDto;
import com.lge.sm.cr_data_store.mapper.PointDtoMapper;

public class PointDao extends APointDao{
    public PointDao(PointDtoMapper mapper) {
        super(mapper);
    }
}
