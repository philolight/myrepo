package com.lge.framework.ceasar.dao;

import java.util.List;

/**
 * DB로부터 데이터를 읽기(select)만 하는 interface.
 * DataAccessObject의 제한된 형태로, 임의로 데이터의 삽입/수정/삭제를 방지함으로써 부작용을 줄이기 위함.
 * 일반적으로 Presentation 계층이나 일부 벌크 데이터를 필요로 하는 서비스에서 사용될 것으로 예상됨.
 */
public interface DataSelectObject<DTO, EX> {
	public List<DTO> select(List<DTO> records);
	public List<DTO> selectAll();	
	public List<DTO> selectBetween(String cdateFrom, String cdateTo);
	public List<DTO> selectFrom(String cdateFrom);
	public List<DTO> selectTo(String cdateTo);
	public List<DTO> selectByExample(EX example);
}