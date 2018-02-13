package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.dto.EventHistoryDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EventHistoryDtoMapper {
    long countByExample(EventHistoryDtoExample example);

    int deleteByExample(EventHistoryDtoExample example);

    int deleteByPrimaryKey(@Param("name") String name, @Param("cdate") String cdate);

    int insert(EventHistoryDto record);

    int insertSelective(EventHistoryDto record);

    List<EventHistoryDto> selectByExample(EventHistoryDtoExample example);

    EventHistoryDto selectByPrimaryKey(@Param("name") String name, @Param("cdate") String cdate);

    int updateByExampleSelective(@Param("record") EventHistoryDto record, @Param("example") EventHistoryDtoExample example);

    int updateByExample(@Param("record") EventHistoryDto record, @Param("example") EventHistoryDtoExample example);

    int updateByPrimaryKeySelective(EventHistoryDto record);

    int updateByPrimaryKey(EventHistoryDto record);
}