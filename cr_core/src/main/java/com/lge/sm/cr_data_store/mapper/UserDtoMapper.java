package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.UserDto;
import com.lge.sm.cr_data_store.dto.UserDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDtoMapper {
    long countByExample(UserDtoExample example);

    int deleteByExample(UserDtoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserDto record);

    int insertSelective(UserDto record);

    List<UserDto> selectByExample(UserDtoExample example);

    UserDto selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByExample(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByPrimaryKeySelective(UserDto record);

    int updateByPrimaryKey(UserDto record);
}