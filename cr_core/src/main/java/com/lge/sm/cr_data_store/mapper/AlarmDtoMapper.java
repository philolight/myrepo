package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.dto.AlarmDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlarmDtoMapper {
    long countByExample(AlarmDtoExample example);

    int deleteByExample(AlarmDtoExample example);

    int deleteByPrimaryKey(Long alarmId);

    int insert(AlarmDto record);

    int insertSelective(AlarmDto record);

    List<AlarmDto> selectByExample(AlarmDtoExample example);

    AlarmDto selectByPrimaryKey(Long alarmId);

    int updateByExampleSelective(@Param("record") AlarmDto record, @Param("example") AlarmDtoExample example);

    int updateByExample(@Param("record") AlarmDto record, @Param("example") AlarmDtoExample example);

    int updateByPrimaryKeySelective(AlarmDto record);

    int updateByPrimaryKey(AlarmDto record);
}