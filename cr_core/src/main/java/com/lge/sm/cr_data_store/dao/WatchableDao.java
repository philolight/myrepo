package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AWatchableDao;
import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.mapper.WatchableDtoMapper;

public class WatchableDao extends AWatchableDao{
    public WatchableDao(WatchableDtoMapper mapper) {
        super(mapper);
    }
}
