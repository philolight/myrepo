package com.lge.framework.ceasar.entity;

abstract public class Entity<DTO> extends DtoOwner<DTO>{
	protected MapKey mapKey;
	
	protected Entity() {}  // for Serialize
	
	public Entity(DTO dto) {
		super(dto);
	}
	
	public MapKey mapKey() { return mapKey; } 
}
