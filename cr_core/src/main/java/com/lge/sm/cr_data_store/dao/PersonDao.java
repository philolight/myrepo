package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.APersonDao;
import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.mapper.PersonDtoMapper;

public class PersonDao extends APersonDao{
    public PersonDao(PersonDtoMapper mapper) {
        super(mapper);
    }
}
