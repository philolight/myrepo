package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WatchableHistoryDtoMapper {
    long countByExample(WatchableHistoryDtoExample example);

    int deleteByExample(WatchableHistoryDtoExample example);

    int deleteByPrimaryKey(@Param("watchableId") String watchableId, @Param("cdate") String cdate);

    int insert(WatchableHistoryDto record);

    int insertSelective(WatchableHistoryDto record);

    List<WatchableHistoryDto> selectByExample(WatchableHistoryDtoExample example);

    WatchableHistoryDto selectByPrimaryKey(@Param("watchableId") String watchableId, @Param("cdate") String cdate);

    int updateByExampleSelective(@Param("record") WatchableHistoryDto record, @Param("example") WatchableHistoryDtoExample example);

    int updateByExample(@Param("record") WatchableHistoryDto record, @Param("example") WatchableHistoryDtoExample example);

    int updateByPrimaryKeySelective(WatchableHistoryDto record);

    int updateByPrimaryKey(WatchableHistoryDto record);
}