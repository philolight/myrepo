package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.LocationDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LocationDtoMapper {
    long countByExample(LocationDtoExample example);

    int deleteByExample(LocationDtoExample example);

    int deleteByPrimaryKey(String locationId);

    int insert(LocationDto record);

    int insertSelective(LocationDto record);

    List<LocationDto> selectByExample(LocationDtoExample example);

    LocationDto selectByPrimaryKey(String locationId);

    int updateByExampleSelective(@Param("record") LocationDto record, @Param("example") LocationDtoExample example);

    int updateByExample(@Param("record") LocationDto record, @Param("example") LocationDtoExample example);

    int updateByPrimaryKeySelective(LocationDto record);

    int updateByPrimaryKey(LocationDto record);
}