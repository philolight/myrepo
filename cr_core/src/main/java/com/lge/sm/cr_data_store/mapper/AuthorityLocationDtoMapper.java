package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityLocationDtoMapper {
    long countByExample(AuthorityLocationDtoExample example);

    int deleteByExample(AuthorityLocationDtoExample example);

    int deleteByPrimaryKey(@Param("locationId") String locationId, @Param("authorityId") Long authorityId);

    int insert(AuthorityLocationDto record);

    int insertSelective(AuthorityLocationDto record);

    List<AuthorityLocationDto> selectByExample(AuthorityLocationDtoExample example);

    AuthorityLocationDto selectByPrimaryKey(@Param("locationId") String locationId, @Param("authorityId") Long authorityId);

    int updateByExampleSelective(@Param("record") AuthorityLocationDto record, @Param("example") AuthorityLocationDtoExample example);

    int updateByExample(@Param("record") AuthorityLocationDto record, @Param("example") AuthorityLocationDtoExample example);

    int updateByPrimaryKeySelective(AuthorityLocationDto record);

    int updateByPrimaryKey(AuthorityLocationDto record);
}