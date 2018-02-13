package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.dto.StartableDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StartableDtoMapper {
    long countByExample(StartableDtoExample example);

    int deleteByExample(StartableDtoExample example);

    int deleteByPrimaryKey(String startableId);

    int insert(StartableDto record);

    int insertSelective(StartableDto record);

    List<StartableDto> selectByExample(StartableDtoExample example);

    StartableDto selectByPrimaryKey(String startableId);

    int updateByExampleSelective(@Param("record") StartableDto record, @Param("example") StartableDtoExample example);

    int updateByExample(@Param("record") StartableDto record, @Param("example") StartableDtoExample example);

    int updateByPrimaryKeySelective(StartableDto record);

    int updateByPrimaryKey(StartableDto record);
}