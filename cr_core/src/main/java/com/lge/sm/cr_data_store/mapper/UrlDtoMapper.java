package com.lge.sm.cr_data_store.mapper;

import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.dto.UrlDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrlDtoMapper {
    long countByExample(UrlDtoExample example);

    int deleteByExample(UrlDtoExample example);

    int deleteByPrimaryKey(@Param("urlId") Long urlId, @Param("url") String url);

    int insert(UrlDto record);

    int insertSelective(UrlDto record);

    List<UrlDto> selectByExample(UrlDtoExample example);

    UrlDto selectByPrimaryKey(@Param("urlId") Long urlId, @Param("url") String url);

    int updateByExampleSelective(@Param("record") UrlDto record, @Param("example") UrlDtoExample example);

    int updateByExample(@Param("record") UrlDto record, @Param("example") UrlDtoExample example);

    int updateByPrimaryKeySelective(UrlDto record);

    int updateByPrimaryKey(UrlDto record);
}