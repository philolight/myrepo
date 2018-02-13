package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AEnumFacetDao;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.mapper.EnumFacetDtoMapper;

public class EnumFacetDao extends AEnumFacetDao{
    public EnumFacetDao(EnumFacetDtoMapper mapper) {
        super(mapper);
    }
}
