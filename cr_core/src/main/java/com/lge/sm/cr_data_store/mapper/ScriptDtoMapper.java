package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.ScriptDto;
import com.lge.sm.cr_data_store.dto.ScriptDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScriptDtoMapper {
    long countByExample(ScriptDtoExample example);

    int deleteByExample(ScriptDtoExample example);

    int deleteByPrimaryKey(Long scriptId);

    int insert(ScriptDto record);

    int insertSelective(ScriptDto record);

    List<ScriptDto> selectByExample(ScriptDtoExample example);

    ScriptDto selectByPrimaryKey(Long scriptId);

    int updateByExampleSelective(@Param("record") ScriptDto record, @Param("example") ScriptDtoExample example);

    int updateByExample(@Param("record") ScriptDto record, @Param("example") ScriptDtoExample example);

    int updateByPrimaryKeySelective(ScriptDto record);

    int updateByPrimaryKey(ScriptDto record);
}