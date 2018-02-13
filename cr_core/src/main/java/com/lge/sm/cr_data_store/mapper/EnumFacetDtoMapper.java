package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.dto.EnumFacetDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnumFacetDtoMapper {
    long countByExample(EnumFacetDtoExample example);

    int deleteByExample(EnumFacetDtoExample example);

    int deleteByPrimaryKey(Long enumFacetId);

    int insert(EnumFacetDto record);

    int insertSelective(EnumFacetDto record);

    List<EnumFacetDto> selectByExample(EnumFacetDtoExample example);

    EnumFacetDto selectByPrimaryKey(Long enumFacetId);

    int updateByExampleSelective(@Param("record") EnumFacetDto record, @Param("example") EnumFacetDtoExample example);

    int updateByExample(@Param("record") EnumFacetDto record, @Param("example") EnumFacetDtoExample example);

    int updateByPrimaryKeySelective(EnumFacetDto record);

    int updateByPrimaryKey(EnumFacetDto record);
}