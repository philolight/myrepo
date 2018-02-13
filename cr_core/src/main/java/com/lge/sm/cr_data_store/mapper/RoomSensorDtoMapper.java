package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomSensorDtoMapper {
    long countByExample(RoomSensorDtoExample example);

    int deleteByExample(RoomSensorDtoExample example);

    int deleteByPrimaryKey(Long roomSensorId);

    int insert(RoomSensorDto record);

    int insertSelective(RoomSensorDto record);

    List<RoomSensorDto> selectByExample(RoomSensorDtoExample example);

    RoomSensorDto selectByPrimaryKey(Long roomSensorId);

    int updateByExampleSelective(@Param("record") RoomSensorDto record, @Param("example") RoomSensorDtoExample example);

    int updateByExample(@Param("record") RoomSensorDto record, @Param("example") RoomSensorDtoExample example);

    int updateByPrimaryKeySelective(RoomSensorDto record);

    int updateByPrimaryKey(RoomSensorDto record);
}