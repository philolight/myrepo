package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.dto.PartyDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyDtoMapper {
    long countByExample(PartyDtoExample example);

    int deleteByExample(PartyDtoExample example);

    int deleteByPrimaryKey(String partyId);

    int insert(PartyDto record);

    int insertSelective(PartyDto record);

    List<PartyDto> selectByExample(PartyDtoExample example);

    PartyDto selectByPrimaryKey(String partyId);

    int updateByExampleSelective(@Param("record") PartyDto record, @Param("example") PartyDtoExample example);

    int updateByExample(@Param("record") PartyDto record, @Param("example") PartyDtoExample example);

    int updateByPrimaryKeySelective(PartyDto record);

    int updateByPrimaryKey(PartyDto record);
}