package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ASpotDao;
import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.mapper.SpotDtoMapper;

public class SpotDao extends ASpotDao{
    public SpotDao(SpotDtoMapper mapper) {
        super(mapper);
    }
}
