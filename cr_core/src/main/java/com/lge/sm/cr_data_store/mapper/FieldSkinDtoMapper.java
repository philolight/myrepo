package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.dto.FieldSkinDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FieldSkinDtoMapper {
    long countByExample(FieldSkinDtoExample example);

    int deleteByExample(FieldSkinDtoExample example);

    int deleteByPrimaryKey(String fieldSkinId);

    int insert(FieldSkinDto record);

    int insertSelective(FieldSkinDto record);

    List<FieldSkinDto> selectByExample(FieldSkinDtoExample example);

    FieldSkinDto selectByPrimaryKey(String fieldSkinId);

    int updateByExampleSelective(@Param("record") FieldSkinDto record, @Param("example") FieldSkinDtoExample example);

    int updateByExample(@Param("record") FieldSkinDto record, @Param("example") FieldSkinDtoExample example);

    int updateByPrimaryKeySelective(FieldSkinDto record);

    int updateByPrimaryKey(FieldSkinDto record);
}