package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.dto.DriverDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriverDtoMapper {
    long countByExample(DriverDtoExample example);

    int deleteByExample(DriverDtoExample example);

    int deleteByPrimaryKey(Long driverId);

    int insert(DriverDto record);

    int insertSelective(DriverDto record);

    List<DriverDto> selectByExample(DriverDtoExample example);

    DriverDto selectByPrimaryKey(Long driverId);

    int updateByExampleSelective(@Param("record") DriverDto record, @Param("example") DriverDtoExample example);

    int updateByExample(@Param("record") DriverDto record, @Param("example") DriverDtoExample example);

    int updateByPrimaryKeySelective(DriverDto record);

    int updateByPrimaryKey(DriverDto record);
}