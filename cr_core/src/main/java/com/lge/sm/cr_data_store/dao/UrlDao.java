package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AUrlDao;
import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.mapper.UrlDtoMapper;

public class UrlDao extends AUrlDao{
    public UrlDao(UrlDtoMapper mapper) {
        super(mapper);
    }
}
