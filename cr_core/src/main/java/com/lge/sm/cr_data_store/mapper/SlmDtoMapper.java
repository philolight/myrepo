package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.dto.SlmDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlmDtoMapper {
    long countByExample(SlmDtoExample example);

    int deleteByExample(SlmDtoExample example);

    int deleteByPrimaryKey(String slmId);

    int insert(SlmDto record);

    int insertSelective(SlmDto record);

    List<SlmDto> selectByExample(SlmDtoExample example);

    SlmDto selectByPrimaryKey(String slmId);

    int updateByExampleSelective(@Param("record") SlmDto record, @Param("example") SlmDtoExample example);

    int updateByExample(@Param("record") SlmDto record, @Param("example") SlmDtoExample example);

    int updateByPrimaryKeySelective(SlmDto record);

    int updateByPrimaryKey(SlmDto record);
}