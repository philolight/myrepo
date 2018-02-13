package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APartyDao;
import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.mapper.PartyDtoMapper;

public class PartyDao extends APartyDao{
    public PartyDao(PartyDtoMapper mapper) {
        super(mapper);
    }
}
