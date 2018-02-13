package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ACancelHistoryDao;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.mapper.CancelHistoryDtoMapper;

public class CancelHistoryDao extends ACancelHistoryDao{
    public CancelHistoryDao(CancelHistoryDtoMapper mapper) {
        super(mapper);
    }
}
