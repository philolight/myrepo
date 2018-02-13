package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyAuthorityDtoMapper {
    long countByExample(PartyAuthorityDtoExample example);

    int deleteByExample(PartyAuthorityDtoExample example);

    int deleteByPrimaryKey(@Param("authorityId") Long authorityId, @Param("partyId") String partyId);

    int insert(PartyAuthorityDto record);

    int insertSelective(PartyAuthorityDto record);

    List<PartyAuthorityDto> selectByExample(PartyAuthorityDtoExample example);

    PartyAuthorityDto selectByPrimaryKey(@Param("authorityId") Long authorityId, @Param("partyId") String partyId);

    int updateByExampleSelective(@Param("record") PartyAuthorityDto record, @Param("example") PartyAuthorityDtoExample example);

    int updateByExample(@Param("record") PartyAuthorityDto record, @Param("example") PartyAuthorityDtoExample example);

    int updateByPrimaryKeySelective(PartyAuthorityDto record);

    int updateByPrimaryKey(PartyAuthorityDto record);
}