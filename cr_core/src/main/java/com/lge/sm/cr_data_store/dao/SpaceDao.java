package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ASpaceDao;
import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.mapper.SpaceDtoMapper;

public class SpaceDao extends ASpaceDao{
    public SpaceDao(SpaceDtoMapper mapper) {
        super(mapper);
    }
}
