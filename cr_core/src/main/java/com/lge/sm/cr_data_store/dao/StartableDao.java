package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AStartableDao;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.mapper.StartableDtoMapper;

public class StartableDao extends AStartableDao{
    public StartableDao(StartableDtoMapper mapper) {
        super(mapper);
    }
}
