package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APartyAuthorityDao;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.mapper.PartyAuthorityDtoMapper;

public class PartyAuthorityDao extends APartyAuthorityDao{
    public PartyAuthorityDao(PartyAuthorityDtoMapper mapper) {
        super(mapper);
    }
}
