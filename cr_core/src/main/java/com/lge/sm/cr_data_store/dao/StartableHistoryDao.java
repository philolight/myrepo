package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AStartableHistoryDao;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.mapper.StartableHistoryDtoMapper;

public class StartableHistoryDao extends AStartableHistoryDao{
    public StartableHistoryDao(StartableHistoryDtoMapper mapper) {
        super(mapper);
    }
}
