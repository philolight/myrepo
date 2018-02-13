package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.dto.WatchableDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WatchableDtoMapper {
    long countByExample(WatchableDtoExample example);

    int deleteByExample(WatchableDtoExample example);

    int deleteByPrimaryKey(String watchableId);

    int insert(WatchableDto record);

    int insertSelective(WatchableDto record);

    List<WatchableDto> selectByExample(WatchableDtoExample example);

    WatchableDto selectByPrimaryKey(String watchableId);

    int updateByExampleSelective(@Param("record") WatchableDto record, @Param("example") WatchableDtoExample example);

    int updateByExample(@Param("record") WatchableDto record, @Param("example") WatchableDtoExample example);

    int updateByPrimaryKeySelective(WatchableDto record);

    int updateByPrimaryKey(WatchableDto record);
}