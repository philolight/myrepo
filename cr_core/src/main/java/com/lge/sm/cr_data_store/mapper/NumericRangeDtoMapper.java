package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.dto.NumericRangeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NumericRangeDtoMapper {
    long countByExample(NumericRangeDtoExample example);

    int deleteByExample(NumericRangeDtoExample example);

    int deleteByPrimaryKey(Long numericRangeId);

    int insert(NumericRangeDto record);

    int insertSelective(NumericRangeDto record);

    List<NumericRangeDto> selectByExample(NumericRangeDtoExample example);

    NumericRangeDto selectByPrimaryKey(Long numericRangeId);

    int updateByExampleSelective(@Param("record") NumericRangeDto record, @Param("example") NumericRangeDtoExample example);

    int updateByExample(@Param("record") NumericRangeDto record, @Param("example") NumericRangeDtoExample example);

    int updateByPrimaryKeySelective(NumericRangeDto record);

    int updateByPrimaryKey(NumericRangeDto record);
}