package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.dto.DecimalRangeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DecimalRangeDtoMapper {
    long countByExample(DecimalRangeDtoExample example);

    int deleteByExample(DecimalRangeDtoExample example);

    int deleteByPrimaryKey(Long decimalRangeId);

    int insert(DecimalRangeDto record);

    int insertSelective(DecimalRangeDto record);

    List<DecimalRangeDto> selectByExample(DecimalRangeDtoExample example);

    DecimalRangeDto selectByPrimaryKey(Long decimalRangeId);

    int updateByExampleSelective(@Param("record") DecimalRangeDto record, @Param("example") DecimalRangeDtoExample example);

    int updateByExample(@Param("record") DecimalRangeDto record, @Param("example") DecimalRangeDtoExample example);

    int updateByPrimaryKeySelective(DecimalRangeDto record);

    int updateByPrimaryKey(DecimalRangeDto record);
}