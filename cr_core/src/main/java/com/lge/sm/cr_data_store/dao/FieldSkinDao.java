package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AFieldSkinDao;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.mapper.FieldSkinDtoMapper;

public class FieldSkinDao extends AFieldSkinDao{
    public FieldSkinDao(FieldSkinDtoMapper mapper) {
        super(mapper);
    }
}
