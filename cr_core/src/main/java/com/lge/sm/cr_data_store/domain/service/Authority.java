package com.lge.sm.cr_data_store.domain.service;

import java.util.HashSet;
import java.util.Set;

import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.entity.UserEntity;

public class Authority {
	private Set<ServiceEntity> availableServices = new HashSet<>();
	private final UserEntity userEntity;
	private final PartyEntity partyEntity;
	
	public Authority(UserEntity userEntity, PartyEntity partyEntity) {
		this.userEntity = userEntity;
		this.partyEntity = partyEntity;
	}
	
	public UserEntity getUserEntity() {
		return userEntity;
	}
	
	void add(ServiceEntity serviceEntity) {
		availableServices.add(serviceEntity);
	}
	
	void remove(ServiceEntity serviceEntity) {
		availableServices.remove(serviceEntity);
	}
	
	public boolean isAvailable(ServiceEntity serviceEntity) {
		return availableServices.contains(serviceEntity);
	}
}
