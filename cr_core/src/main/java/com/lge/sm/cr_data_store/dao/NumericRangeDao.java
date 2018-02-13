package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ANumericRangeDao;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.mapper.NumericRangeDtoMapper;

public class NumericRangeDao extends ANumericRangeDao{
    public NumericRangeDao(NumericRangeDtoMapper mapper) {
        super(mapper);
    }
}
