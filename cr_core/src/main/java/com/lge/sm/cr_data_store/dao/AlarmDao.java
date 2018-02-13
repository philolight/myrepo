package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AAlarmDao;
import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.mapper.AlarmDtoMapper;

public class AlarmDao extends AAlarmDao{
    public AlarmDao(AlarmDtoMapper mapper) {
        super(mapper);
    }
}
