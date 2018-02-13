package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AWatchableHistoryDao;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.mapper.WatchableHistoryDtoMapper;

public class WatchableHistoryDao extends AWatchableHistoryDao{
    public WatchableHistoryDao(WatchableHistoryDtoMapper mapper) {
        super(mapper);
    }
}
