package com.lge.framework.ceasar.service;

import java.util.HashSet;
import java.util.Set;

import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.startable.StartableType;
import com.lge.framework.ceasar.startable.Starter;

abstract public class AbstractService implements IService{
	
// as a Startable ----------------------------------------------------------------------------------------------------------------------
	
	protected StartableStatus status = StartableStatus.DEFAULT;
	
	@Override
	public StartableType getStartableType() { return StartableType.SERVICE; }

	@Override
	public StartableStatus getStartableStatus() { return status; }
	
	public void setStatus(StartableStatus status) { this.status = status; }
	
	public AbstractService() {
		Starter.add(this);
		ServiceManager.add(this);
	}

// --------------------------------------------------------------------------------------------------------------------------------------
	
	protected AbstractService upperService;
	protected boolean serviceEnable = false;
	protected Set<AbstractService> kids = new HashSet<AbstractService>();
	
	@Override
	public String getUpperServiceId() {
		if(upperService == null) return "System";
		return upperService.getServiceId();
	}
	
	@Override
	public void setUpperService(AbstractService upperService) {
		this.upperService = upperService;
	}
	
	@Override
	public void setServiceEnable(boolean serviceEnable) {
		this.serviceEnable = serviceEnable;
	}
	
	@Override
	public boolean getServiceEnable() {
		return serviceEnable;
	}
}
