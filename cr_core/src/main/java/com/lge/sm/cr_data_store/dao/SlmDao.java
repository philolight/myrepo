package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ASlmDao;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.mapper.SlmDtoMapper;

public class SlmDao extends ASlmDao{
    public SlmDao(SlmDtoMapper mapper) {
        super(mapper);
    }
}
