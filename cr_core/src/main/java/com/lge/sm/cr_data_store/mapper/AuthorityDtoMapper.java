package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.dto.AuthorityDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuthorityDtoMapper {
    long countByExample(AuthorityDtoExample example);

    int deleteByExample(AuthorityDtoExample example);

    int deleteByPrimaryKey(Long authorityId);

    int insert(AuthorityDto record);

    int insertSelective(AuthorityDto record);

    List<AuthorityDto> selectByExample(AuthorityDtoExample example);

    AuthorityDto selectByPrimaryKey(Long authorityId);

    int updateByExampleSelective(@Param("record") AuthorityDto record, @Param("example") AuthorityDtoExample example);

    int updateByExample(@Param("record") AuthorityDto record, @Param("example") AuthorityDtoExample example);

    int updateByPrimaryKeySelective(AuthorityDto record);

    int updateByPrimaryKey(AuthorityDto record);
}