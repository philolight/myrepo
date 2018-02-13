package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ADecimalRangeDao;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.mapper.DecimalRangeDtoMapper;

public class DecimalRangeDao extends ADecimalRangeDao{
    public DecimalRangeDao(DecimalRangeDtoMapper mapper) {
        super(mapper);
    }
}
