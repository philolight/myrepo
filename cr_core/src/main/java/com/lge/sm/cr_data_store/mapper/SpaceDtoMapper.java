package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.dto.SpaceDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpaceDtoMapper {
    long countByExample(SpaceDtoExample example);

    int deleteByExample(SpaceDtoExample example);

    int deleteByPrimaryKey(Long spaceId);

    int insert(SpaceDto record);

    int insertSelective(SpaceDto record);

    List<SpaceDto> selectByExample(SpaceDtoExample example);

    SpaceDto selectByPrimaryKey(Long spaceId);

    int updateByExampleSelective(@Param("record") SpaceDto record, @Param("example") SpaceDtoExample example);

    int updateByExample(@Param("record") SpaceDto record, @Param("example") SpaceDtoExample example);

    int updateByPrimaryKeySelective(SpaceDto record);

    int updateByPrimaryKey(SpaceDto record);
}