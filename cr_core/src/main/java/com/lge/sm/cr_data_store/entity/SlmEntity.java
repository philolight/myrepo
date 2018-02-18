package com.lge.sm.cr_data_store.entity;


import java.net.MalformedURLException;
import java.net.URL;

import com.lge.framework.ceasar.logger.Logger;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.sm.cr_data_store.anemics.aentity.ASlmEntity;
import com.lge.sm.cr_data_store.common.Constants;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.repository.SlmRepository;

public class SlmEntity extends ASlmEntity implements Cloneable{
    private static final String TAG = SlmEntity.class.getSimpleName();
  
    protected SlmEntity() {} // for Serialize
  
    public SlmEntity(SlmDto dto) {
        super(dto);
    }

    @Override
    public boolean flush() {
        return Repos.repo(SlmRepository.class).update(this);
    }
    
    @Override
    protected SlmEntity clone() throws CloneNotSupportedException {
        return Repos.repo(SlmRepository.class).cloneOf(this);
    }
    
    public String getSlmPath() {
    	  String ret = null;
    	  if (getProtocol() != null && getIp() != null && getPort() > 0) {
    		  try {
    			  URL url = new URL(getProtocol(), getIp(), getPort(), Constants.POINT_ACCESS_SERVICE_API_PATH);
    			  ret = url.toString();
    		  } catch (MalformedURLException e) {
    			  Logger.error(TAG, e.getMessage());
    		  }
    	  }
    	  return ret;
      }
}
