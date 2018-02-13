package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.dto.ServiceDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceDtoMapper {
    long countByExample(ServiceDtoExample example);

    int deleteByExample(ServiceDtoExample example);

    int deleteByPrimaryKey(String serviceId);

    int insert(ServiceDto record);

    int insertSelective(ServiceDto record);

    List<ServiceDto> selectByExample(ServiceDtoExample example);

    ServiceDto selectByPrimaryKey(String serviceId);

    int updateByExampleSelective(@Param("record") ServiceDto record, @Param("example") ServiceDtoExample example);

    int updateByExample(@Param("record") ServiceDto record, @Param("example") ServiceDtoExample example);

    int updateByPrimaryKeySelective(ServiceDto record);

    int updateByPrimaryKey(ServiceDto record);
}