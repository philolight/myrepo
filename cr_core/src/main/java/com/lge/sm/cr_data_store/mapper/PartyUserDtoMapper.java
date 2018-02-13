package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.dto.PartyUserDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyUserDtoMapper {
    long countByExample(PartyUserDtoExample example);

    int deleteByExample(PartyUserDtoExample example);

    int deleteByPrimaryKey(@Param("userId") String userId, @Param("partyId") String partyId);

    int insert(PartyUserDto record);

    int insertSelective(PartyUserDto record);

    List<PartyUserDto> selectByExample(PartyUserDtoExample example);

    PartyUserDto selectByPrimaryKey(@Param("userId") String userId, @Param("partyId") String partyId);

    int updateByExampleSelective(@Param("record") PartyUserDto record, @Param("example") PartyUserDtoExample example);

    int updateByExample(@Param("record") PartyUserDto record, @Param("example") PartyUserDtoExample example);

    int updateByPrimaryKeySelective(PartyUserDto record);

    int updateByPrimaryKey(PartyUserDto record);
}