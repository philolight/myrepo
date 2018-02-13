package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.dto.UserAuthorityDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthorityDtoMapper {
    long countByExample(UserAuthorityDtoExample example);

    int deleteByExample(UserAuthorityDtoExample example);

    int deleteByPrimaryKey(@Param("userId") String userId, @Param("authorityId") Long authorityId);

    int insert(UserAuthorityDto record);

    int insertSelective(UserAuthorityDto record);

    List<UserAuthorityDto> selectByExample(UserAuthorityDtoExample example);

    UserAuthorityDto selectByPrimaryKey(@Param("userId") String userId, @Param("authorityId") Long authorityId);

    int updateByExampleSelective(@Param("record") UserAuthorityDto record, @Param("example") UserAuthorityDtoExample example);

    int updateByExample(@Param("record") UserAuthorityDto record, @Param("example") UserAuthorityDtoExample example);

    int updateByPrimaryKeySelective(UserAuthorityDto record);

    int updateByPrimaryKey(UserAuthorityDto record);
}