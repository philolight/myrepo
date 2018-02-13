package com.lge.framework.ceasar.repository;

import com.lge.framework.ceasar.dao.DataSelectObject;

public interface DtoCollector<DTO, EX> {
	public DataSelectObject<DTO, EX> getDso();
}
