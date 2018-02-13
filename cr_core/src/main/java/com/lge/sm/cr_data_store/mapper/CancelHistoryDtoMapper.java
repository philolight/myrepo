package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.dto.CancelHistoryDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CancelHistoryDtoMapper {
    long countByExample(CancelHistoryDtoExample example);

    int deleteByExample(CancelHistoryDtoExample example);

    int deleteByPrimaryKey(@Param("dateOf") String dateOf, @Param("locationId") String locationId);

    int insert(CancelHistoryDto record);

    int insertSelective(CancelHistoryDto record);

    List<CancelHistoryDto> selectByExample(CancelHistoryDtoExample example);

    CancelHistoryDto selectByPrimaryKey(@Param("dateOf") String dateOf, @Param("locationId") String locationId);

    int updateByExampleSelective(@Param("record") CancelHistoryDto record, @Param("example") CancelHistoryDtoExample example);

    int updateByExample(@Param("record") CancelHistoryDto record, @Param("example") CancelHistoryDtoExample example);

    int updateByPrimaryKeySelective(CancelHistoryDto record);

    int updateByPrimaryKey(CancelHistoryDto record);
}