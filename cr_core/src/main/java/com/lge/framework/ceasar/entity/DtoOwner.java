package com.lge.framework.ceasar.entity;

import java.io.Serializable;

import com.lge.framework.ceasar.util.Factory;

public class DtoOwner<DTO> implements Serializable{
	protected DTO dto;
	
	protected DtoOwner() {} // for Serialize
	
	public DtoOwner(DTO dto) {
		this.dto = dto;
	}
	public DTO getDto() { return (DTO)Factory.deepCopy(dto); }
}
