package com.lge.framework.ceasar.dao;

import java.util.List;

public interface DataAccessObject<DTO, EX> extends DataSelectObject<DTO, EX>{
	public boolean insert(DTO record);	
	public boolean insert(List<DTO> records);	
	public boolean update(DTO record);
	public boolean update(List<DTO> records);
	public boolean delete(DTO record);
	public boolean delete(List<DTO> records);
	public boolean deleteAll();
}
