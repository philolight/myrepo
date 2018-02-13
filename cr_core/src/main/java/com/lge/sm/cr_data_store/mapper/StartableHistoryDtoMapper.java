package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.dto.StartableHistoryDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StartableHistoryDtoMapper {
    long countByExample(StartableHistoryDtoExample example);

    int deleteByExample(StartableHistoryDtoExample example);

    int deleteByPrimaryKey(@Param("startableId") String startableId, @Param("cdate") String cdate);

    int insert(StartableHistoryDto record);

    int insertSelective(StartableHistoryDto record);

    List<StartableHistoryDto> selectByExample(StartableHistoryDtoExample example);

    StartableHistoryDto selectByPrimaryKey(@Param("startableId") String startableId, @Param("cdate") String cdate);

    int updateByExampleSelective(@Param("record") StartableHistoryDto record, @Param("example") StartableHistoryDtoExample example);

    int updateByExample(@Param("record") StartableHistoryDto record, @Param("example") StartableHistoryDtoExample example);

    int updateByPrimaryKeySelective(StartableHistoryDto record);

    int updateByPrimaryKey(StartableHistoryDto record);
}