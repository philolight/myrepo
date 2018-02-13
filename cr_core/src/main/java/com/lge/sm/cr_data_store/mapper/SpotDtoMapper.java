package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.dto.SpotDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpotDtoMapper {
    long countByExample(SpotDtoExample example);

    int deleteByExample(SpotDtoExample example);

    int deleteByPrimaryKey(Long spotId);

    int insert(SpotDto record);

    int insertSelective(SpotDto record);

    List<SpotDto> selectByExample(SpotDtoExample example);

    SpotDto selectByPrimaryKey(Long spotId);

    int updateByExampleSelective(@Param("record") SpotDto record, @Param("example") SpotDtoExample example);

    int updateByExample(@Param("record") SpotDto record, @Param("example") SpotDtoExample example);

    int updateByPrimaryKeySelective(SpotDto record);

    int updateByPrimaryKey(SpotDto record);
}