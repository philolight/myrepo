package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ADriverHistoryDao;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.mapper.DriverHistoryDtoMapper;

public class DriverHistoryDao extends ADriverHistoryDao{
    public DriverHistoryDao(DriverHistoryDtoMapper mapper) {
        super(mapper);
    }
}
