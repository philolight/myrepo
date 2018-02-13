package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AEventHistoryDao;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.mapper.EventHistoryDtoMapper;

public class EventHistoryDao extends AEventHistoryDao{
    public EventHistoryDao(EventHistoryDtoMapper mapper) {
        super(mapper);
    }
}
