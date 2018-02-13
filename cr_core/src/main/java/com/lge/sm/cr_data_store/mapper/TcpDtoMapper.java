package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.TcpDto;
import com.lge.sm.cr_data_store.dto.TcpDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TcpDtoMapper {
    long countByExample(TcpDtoExample example);

    int deleteByExample(TcpDtoExample example);

    int deleteByPrimaryKey(Long tcpId);

    int insert(TcpDto record);

    int insertSelective(TcpDto record);

    List<TcpDto> selectByExample(TcpDtoExample example);

    TcpDto selectByPrimaryKey(Long tcpId);

    int updateByExampleSelective(@Param("record") TcpDto record, @Param("example") TcpDtoExample example);

    int updateByExample(@Param("record") TcpDto record, @Param("example") TcpDtoExample example);

    int updateByPrimaryKeySelective(TcpDto record);

    int updateByPrimaryKey(TcpDto record);
}