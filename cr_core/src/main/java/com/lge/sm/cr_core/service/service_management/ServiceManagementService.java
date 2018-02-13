package com.lge.sm.cr_core.service.service_management;

import java.util.List;

import javax.naming.ServiceUnavailableException;

import org.springframework.stereotype.Service;

import com.lge.framework.ceasar.service.AbstractService;
import com.lge.framework.ceasar.service.ServiceManager;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.sm.cr_data_store.dto.ServiceDto;

@Service
public class ServiceManagementService extends AbstractService{
	private ServiceManager serviceManager;
	public void enableService(String serviceId) throws ServiceUnavailableException {
		serviceManager.enableService(serviceId);
	}
	
	public void disableService(String serviceId) throws ServiceUnavailableException {
		serviceManager.disableService(serviceId);
	}
	
	public List<ServiceDto> getServices(){
		return serviceManager.getServices();
	}

	@Override
	public String getServiceId() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getStartableId() {
		return getServiceId();
	}

	@Override
	public boolean init() {
		return true; // do nothing
	}

	@Override
	public boolean start() {
		return true; // do nothing
	}

	@Override
	public boolean stop() {
		return true; // do nothing
	}
	
	@Override
	public boolean getServiceEnable() {
		// 이 서비스는 항상 Enable임.
		return true;
	}
	
	@Override
	public void setServiceEnable(boolean serviceEnable) {
		// 이 서비스는 stop할 수 없음.
	}
	
	public void setStartableStatus(StartableStatus status) {
		status = StartableStatus.RUNNING;
	}
}
