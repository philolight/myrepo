package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APartyUserDao;
import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.mapper.PartyUserDtoMapper;

public class PartyUserDao extends APartyUserDao{
    public PartyUserDao(PartyUserDtoMapper mapper) {
        super(mapper);
    }
}
