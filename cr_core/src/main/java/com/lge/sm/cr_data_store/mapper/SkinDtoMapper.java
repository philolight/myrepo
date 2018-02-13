package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.dto.SkinDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SkinDtoMapper {
    long countByExample(SkinDtoExample example);

    int deleteByExample(SkinDtoExample example);

    int deleteByPrimaryKey(String skinId);

    int insert(SkinDto record);

    int insertSelective(SkinDto record);

    List<SkinDto> selectByExample(SkinDtoExample example);

    SkinDto selectByPrimaryKey(String skinId);

    int updateByExampleSelective(@Param("record") SkinDto record, @Param("example") SkinDtoExample example);

    int updateByExample(@Param("record") SkinDto record, @Param("example") SkinDtoExample example);

    int updateByPrimaryKeySelective(SkinDto record);

    int updateByPrimaryKey(SkinDto record);
}