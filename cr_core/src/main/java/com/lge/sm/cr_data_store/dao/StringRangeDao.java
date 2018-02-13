package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AStringRangeDao;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.mapper.StringRangeDtoMapper;

public class StringRangeDao extends AStringRangeDao{
    public StringRangeDao(StringRangeDtoMapper mapper) {
        super(mapper);
    }
}
