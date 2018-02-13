package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.dto.DriverHistoryDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriverHistoryDtoMapper {
    long countByExample(DriverHistoryDtoExample example);

    int deleteByExample(DriverHistoryDtoExample example);

    int deleteByPrimaryKey(@Param("cdate") String cdate, @Param("driverId") Long driverId);

    int insert(DriverHistoryDto record);

    int insertSelective(DriverHistoryDto record);

    List<DriverHistoryDto> selectByExample(DriverHistoryDtoExample example);

    DriverHistoryDto selectByPrimaryKey(@Param("cdate") String cdate, @Param("driverId") Long driverId);

    int updateByExampleSelective(@Param("record") DriverHistoryDto record, @Param("example") DriverHistoryDtoExample example);

    int updateByExample(@Param("record") DriverHistoryDto record, @Param("example") DriverHistoryDtoExample example);

    int updateByPrimaryKeySelective(DriverHistoryDto record);

    int updateByPrimaryKey(DriverHistoryDto record);
}