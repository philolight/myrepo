package com.lge.framework.ceasar.service;

import com.lge.framework.ceasar.startable.Startable;

public interface IService extends Startable{
	public String getServiceId();
	public String getUpperServiceId();
	public void setUpperService(AbstractService upperService);
	public void setServiceEnable(boolean enable);
	public boolean getServiceEnable();
}
