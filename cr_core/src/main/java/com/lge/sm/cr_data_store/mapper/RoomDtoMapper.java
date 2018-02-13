package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.RoomDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomDtoMapper {
    long countByExample(RoomDtoExample example);

    int deleteByExample(RoomDtoExample example);

    int deleteByPrimaryKey(String roomId);

    int insert(RoomDto record);

    int insertSelective(RoomDto record);

    List<RoomDto> selectByExample(RoomDtoExample example);

    RoomDto selectByPrimaryKey(String roomId);

    int updateByExampleSelective(@Param("record") RoomDto record, @Param("example") RoomDtoExample example);

    int updateByExample(@Param("record") RoomDto record, @Param("example") RoomDtoExample example);

    int updateByPrimaryKeySelective(RoomDto record);

    int updateByPrimaryKey(RoomDto record);
}