package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.ScheduleDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleDtoMapper {
    long countByExample(ScheduleDtoExample example);

    int deleteByExample(ScheduleDtoExample example);

    int deleteByPrimaryKey(String scheduleId);

    int insert(ScheduleDto record);

    int insertSelective(ScheduleDto record);

    List<ScheduleDto> selectByExample(ScheduleDtoExample example);

    ScheduleDto selectByPrimaryKey(String scheduleId);

    int updateByExampleSelective(@Param("record") ScheduleDto record, @Param("example") ScheduleDtoExample example);

    int updateByExample(@Param("record") ScheduleDto record, @Param("example") ScheduleDtoExample example);

    int updateByPrimaryKeySelective(ScheduleDto record);

    int updateByPrimaryKey(ScheduleDto record);
}