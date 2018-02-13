package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.dto.StringRangeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StringRangeDtoMapper {
    long countByExample(StringRangeDtoExample example);

    int deleteByExample(StringRangeDtoExample example);

    int deleteByPrimaryKey(Long stringRangeId);

    int insert(StringRangeDto record);

    int insertSelective(StringRangeDto record);

    List<StringRangeDto> selectByExample(StringRangeDtoExample example);

    StringRangeDto selectByPrimaryKey(Long stringRangeId);

    int updateByExampleSelective(@Param("record") StringRangeDto record, @Param("example") StringRangeDtoExample example);

    int updateByExample(@Param("record") StringRangeDto record, @Param("example") StringRangeDtoExample example);

    int updateByPrimaryKeySelective(StringRangeDto record);

    int updateByPrimaryKey(StringRangeDto record);
}