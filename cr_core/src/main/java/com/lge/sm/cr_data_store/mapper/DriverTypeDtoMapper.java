package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.dto.DriverTypeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DriverTypeDtoMapper {
    long countByExample(DriverTypeDtoExample example);

    int deleteByExample(DriverTypeDtoExample example);

    int deleteByPrimaryKey(String driverTypeId);

    int insert(DriverTypeDto record);

    int insertSelective(DriverTypeDto record);

    List<DriverTypeDto> selectByExample(DriverTypeDtoExample example);

    DriverTypeDto selectByPrimaryKey(String driverTypeId);

    int updateByExampleSelective(@Param("record") DriverTypeDto record, @Param("example") DriverTypeDtoExample example);

    int updateByExample(@Param("record") DriverTypeDto record, @Param("example") DriverTypeDtoExample example);

    int updateByPrimaryKeySelective(DriverTypeDto record);

    int updateByPrimaryKey(DriverTypeDto record);
}