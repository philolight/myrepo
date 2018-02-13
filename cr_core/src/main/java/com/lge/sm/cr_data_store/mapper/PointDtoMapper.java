package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PointDto;
import com.lge.sm.cr_data_store.dto.PointDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointDtoMapper {
    long countByExample(PointDtoExample example);

    int deleteByExample(PointDtoExample example);

    int deleteByPrimaryKey(Long pointId);

    int insert(PointDto record);

    int insertSelective(PointDto record);

    List<PointDto> selectByExample(PointDtoExample example);

    PointDto selectByPrimaryKey(Long pointId);

    int updateByExampleSelective(@Param("record") PointDto record, @Param("example") PointDtoExample example);

    int updateByExample(@Param("record") PointDto record, @Param("example") PointDtoExample example);

    int updateByPrimaryKeySelective(PointDto record);

    int updateByPrimaryKey(PointDto record);
}