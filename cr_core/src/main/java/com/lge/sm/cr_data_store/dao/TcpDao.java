package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.ATcpDao;
import com.lge.sm.cr_data_store.dto.TcpDto;
import com.lge.sm.cr_data_store.mapper.TcpDtoMapper;

public class TcpDao extends ATcpDao{
    public TcpDao(TcpDtoMapper mapper) {
        super(mapper);
    }
}
