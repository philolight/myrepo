package com.lge.sm.cr_data_store.dao;

import java.util.List;

import com.lge.sm.cr_data_store.anemics.adao.AScriptDao;
import com.lge.sm.cr_data_store.dto.ScriptDto;
import com.lge.sm.cr_data_store.mapper.ScriptDtoMapper;

public class ScriptDao extends AScriptDao{
    public ScriptDao(ScriptDtoMapper mapper) {
        super(mapper);
    }
}
