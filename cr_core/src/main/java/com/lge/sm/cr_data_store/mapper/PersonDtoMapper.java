package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.dto.PersonDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PersonDtoMapper {
    long countByExample(PersonDtoExample example);

    int deleteByExample(PersonDtoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(PersonDto record);

    int insertSelective(PersonDto record);

    List<PersonDto> selectByExample(PersonDtoExample example);

    PersonDto selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") PersonDto record, @Param("example") PersonDtoExample example);

    int updateByExample(@Param("record") PersonDto record, @Param("example") PersonDtoExample example);

    int updateByPrimaryKeySelective(PersonDto record);

    int updateByPrimaryKey(PersonDto record);
}