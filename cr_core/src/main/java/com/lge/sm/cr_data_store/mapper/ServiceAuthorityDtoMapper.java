package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceAuthorityDtoMapper {
    long countByExample(ServiceAuthorityDtoExample example);

    int deleteByExample(ServiceAuthorityDtoExample example);

    int deleteByPrimaryKey(@Param("serviceId") String serviceId, @Param("authorityId") Long authorityId);

    int insert(ServiceAuthorityDto record);

    int insertSelective(ServiceAuthorityDto record);

    List<ServiceAuthorityDto> selectByExample(ServiceAuthorityDtoExample example);

    ServiceAuthorityDto selectByPrimaryKey(@Param("serviceId") String serviceId, @Param("authorityId") Long authorityId);

    int updateByExampleSelective(@Param("record") ServiceAuthorityDto record, @Param("example") ServiceAuthorityDtoExample example);

    int updateByExample(@Param("record") ServiceAuthorityDto record, @Param("example") ServiceAuthorityDtoExample example);

    int updateByPrimaryKeySelective(ServiceAuthorityDto record);

    int updateByPrimaryKey(ServiceAuthorityDto record);
}