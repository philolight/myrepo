package com.lge.sm.cr_data_store.domain.service;

import java.util.List;

import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.entity.AuthorityEntity;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.repository.AuthorityLocationRepository;
import com.lge.sm.cr_data_store.repository.AuthorityRepository;

public class AuthorityManager {	
	// Location이 새로 만들어질 경우, ADMIN은 자동으로 해당 Location에 대한 접근 권한을 가져야 한다.
	private EventSubscriber<CreateEvent<LocationEntity>> createLocationEventSubscriber = new EventSubscriber<CreateEvent<LocationEntity>>() {
		@Override
		public void subscribe(CreateEvent<LocationEntity> event) {
			List<AuthorityEntity> authorityEntityList = Repos.repo(AuthorityRepository.class).getAll();
			
			for(AuthorityEntity each : authorityEntityList) {
				if(AuthorityType.getType(each.getType()) == AuthorityType.ADMIN) {
					AuthorityLocationDto alDto = new AuthorityLocationDto();
					alDto.setAuthorityId(each.getAuthorityId());
					alDto.setCdate(DateStringUtil.getCurrentDateString(event.getTarget().getTimeZoneId())); // TODO : change to datetime of DB
					alDto.setLocationId(event.getTarget().getLocationId());
					Repos.repo(AuthorityLocationRepository.class).create(alDto);
				}
			}
			
		}
	};	
}
