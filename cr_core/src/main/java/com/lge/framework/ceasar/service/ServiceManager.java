package com.lge.framework.ceasar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.ServiceUnavailableException;

import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.startable.Starter;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.entity.ServiceEntity;
import com.lge.sm.cr_data_store.repository.ServiceRepository;

public class ServiceManager {
	private Starter starter; // TODO : @autowired
	
	private static Map<String, AbstractService> map = new HashMap<>();
	
	public static void add(AbstractService abstractService){
		map.put(abstractService.getServiceId(), abstractService);
	}
	
	public void enableService(String serviceId) throws ServiceUnavailableException {
		AbstractService abstractService = find(serviceId);
		starter.restart(abstractService);
		abstractService.setServiceEnable(true);
	}

	public void disableService(String serviceId) throws ServiceUnavailableException {
		AbstractService abstractService = find(serviceId);
		starter.stop(abstractService);
		abstractService.setServiceEnable(false);
	}
	
	private AbstractService find(String serviceId) throws ServiceUnavailableException {
		AbstractService abstractService = map.get(serviceId);		
		if(abstractService == null) throw new ServiceUnavailableException("No such serviceId : " + serviceId);		
		return abstractService;
	}

	public List<ServiceDto> getServices() {
		return Repos.repo(ServiceRepository.class).getDtoList();
	}
}
