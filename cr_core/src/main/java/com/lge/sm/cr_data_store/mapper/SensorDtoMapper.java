package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SensorDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SensorDtoMapper {
    long countByExample(SensorDtoExample example);

    int deleteByExample(SensorDtoExample example);

    int deleteByPrimaryKey(String sensorId);

    int insert(SensorDto record);

    int insertSelective(SensorDto record);

    List<SensorDto> selectByExample(SensorDtoExample example);

    SensorDto selectByPrimaryKey(String sensorId);

    int updateByExampleSelective(@Param("record") SensorDto record, @Param("example") SensorDtoExample example);

    int updateByExample(@Param("record") SensorDto record, @Param("example") SensorDtoExample example);

    int updateByPrimaryKeySelective(SensorDto record);

    int updateByPrimaryKey(SensorDto record);
}