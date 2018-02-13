package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AScheduleDao;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.mapper.ScheduleDtoMapper;

public class ScheduleDao extends AScheduleDao{
    public ScheduleDao(ScheduleDtoMapper mapper) {
        super(mapper);
    }
}
