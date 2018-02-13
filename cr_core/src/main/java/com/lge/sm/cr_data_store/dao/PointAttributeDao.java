package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APointAttributeDao;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.mapper.PointAttributeDtoMapper;

public class PointAttributeDao extends APointAttributeDao{
    public PointAttributeDao(PointAttributeDtoMapper mapper) {
        super(mapper);
    }
}
