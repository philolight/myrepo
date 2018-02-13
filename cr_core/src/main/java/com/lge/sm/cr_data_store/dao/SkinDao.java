package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ASkinDao;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.mapper.SkinDtoMapper;

public class SkinDao extends ASkinDao{
    public SkinDao(SkinDtoMapper mapper) {
        super(mapper);
    }
}
