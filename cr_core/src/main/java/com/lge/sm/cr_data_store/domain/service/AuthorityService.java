package com.lge.sm.cr_data_store.domain.service;

import java.util.List;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.entity.PartyAuthorityEntity;
import com.lge.sm.cr_data_store.entity.PartyEntity;
import com.lge.sm.cr_data_store.entity.PartyUserEntity;
import com.lge.sm.cr_data_store.entity.ServiceAuthorityEntity;
import com.lge.sm.cr_data_store.entity.UserAuthorityEntity;
import com.lge.sm.cr_data_store.entity.UserEntity;
import com.lge.sm.cr_data_store.repository.PartyAuthorityRepository;
import com.lge.sm.cr_data_store.repository.PartyUserRepository;
import com.lge.sm.cr_data_store.repository.ServiceAuthorityRepository;
import com.lge.sm.cr_data_store.repository.UserAuthorityRepository;

public class AuthorityService {
/*	private static final String TAG = AuthorityService.class.getSimpleName();
	
	private static UserAuthorityRepository 	userAuthorityRepo;
	private static PartyAuthorityRepository	partyAuthorityRepo;
	private static PartyUserRepository partyUserRepo;
	private static ServiceAuthorityRepository serviceAuthorityRepo;
	
	*//** user가 가진 권한(Authority)과 Party(그룹)가 가진 권한을 합쳐 Authority 객체를 생성해 제공하는 메소드. *//*
	public static Authority getAuthority(UserEntity user) {
		List<UserAuthorityEntity> userAuthorities = userAuthorityRepo.getByUserId(user.getUserId());
		if(userAuthorities.size() != 1) {
			Logger.error(TAG, "Failed to make Authority object by : UserAuthorityEntity is not available");
			return null;
		}
		UserAuthorityEntity userAuthority = userAuthorities.get(0);
		
		
		
		List<ServiceAuthorityEntity> userServices = serviceAuthorityRepo.getByAuthorityId(userAuthority.getAuthorityId());		
		PartyAuthorityEntity partyAuthority = getPartyAuthority(user);
		
		Authority ret = new Authority(user, party);
		
		if(partyAuthority != null) {
			List<ServiceAuthorityEntity> partyServices = serviceAuthorityRepo.getByAuthorityId(partyAuthority.getAuthorityId());
			addServiceTo(ret, partyServices);
		}
		
		addServiceTo(ret, userServices);
		
		return ret;
	}
	
	private static PartyEntity getParty(UserEntity user) {
		List<PartyUserEntity> partyUsers = partyUserRepo.getByUserId(user.getUserId());
		if(partyUsers.size() != 1) {
			Logger.error(TAG, "Failed to make Authority object by : PartyUserEntity is not available");
			return null;
		}
		PartyUserEntity partyUser = partyUsers.get(0);
		
		PartyEntity party
	}
	
	private static PartyAuthorityEntity getPartyAuthority(UserEntity user) {
		List<PartyUserEntity> partyUsers = partyUserRepo.getByUserId(user.getUserId());
		if(partyUsers.size() != 1) {
			Logger.error(TAG, "Failed to make Authority object by : PartyUserEntity is not available");
			return null;
		}
		PartyUserEntity partyUser = partyUsers.get(0);
		
		List<PartyAuthorityEntity> partyAuthorities = partyAuthorityRepo.getByPartyId(partyUser.getPartyId());
		if(partyAuthorities.size() != 1) {
			Logger.error(TAG, "Failed to make Authority object by : PartyAuthorityEntity is not available");
			return null;
		}		
		PartyAuthorityEntity partyAuthority = partyAuthorities.get(0);
		
		return partyAuthority;
	}
	
	private static void addServiceTo(Authority authority, List<ServiceAuthorityEntity> serviceAuthorities) {
		for(ServiceAuthorityEntity each : serviceAuthorities) {
			authority.add(each.getServiceEntity());
		}
	}*/
}