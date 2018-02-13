package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.dto.PointAttributeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointAttributeDtoMapper {
    long countByExample(PointAttributeDtoExample example);

    int deleteByExample(PointAttributeDtoExample example);

    int deleteByPrimaryKey(Long pointAttributeId);

    int insert(PointAttributeDto record);

    int insertSelective(PointAttributeDto record);

    List<PointAttributeDto> selectByExample(PointAttributeDtoExample example);

    PointAttributeDto selectByPrimaryKey(Long pointAttributeId);

    int updateByExampleSelective(@Param("record") PointAttributeDto record, @Param("example") PointAttributeDtoExample example);

    int updateByExample(@Param("record") PointAttributeDto record, @Param("example") PointAttributeDtoExample example);

    int updateByPrimaryKeySelective(PointAttributeDto record);

    int updateByPrimaryKey(PointAttributeDto record);
}