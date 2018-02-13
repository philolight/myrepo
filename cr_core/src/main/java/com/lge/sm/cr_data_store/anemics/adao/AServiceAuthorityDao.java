package com.lge.sm.cr_data_store.anemics.adao;

/**
  * <<<<<<<< Automatically Generated Code >>>>>>>>
  * If you want to modify this file, use Inherited class in ".dao" package.
  */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.lge.framework.ceasar.dao.DataAccessObject;
import com.lge.sm.cr_data_store.mapper.ServiceAuthorityDtoMapper;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDto;
import com.lge.sm.cr_data_store.dto.ServiceAuthorityDtoExample;

abstract public class AServiceAuthorityDao implements DataAccessObject<ServiceAuthorityDto, ServiceAuthorityDtoExample>{
    protected final ServiceAuthorityDtoMapper mapper;
    protected final ServiceAuthorityDtoExample example = new ServiceAuthorityDtoExample();
          
    public AServiceAuthorityDao(ServiceAuthorityDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ServiceAuthorityDto select(Long authorityId,String serviceId){
        ServiceAuthorityDto record = new ServiceAuthorityDto();
		record.setAuthorityId(authorityId);
		record.setServiceId(serviceId);
        List<ServiceAuthorityDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceAuthorityDto> select(List<ServiceAuthorityDto> records){
        List<ServiceAuthorityDto> list = new ArrayList<ServiceAuthorityDto>();
        for(ServiceAuthorityDto record : records){
            List<ServiceAuthorityDto> newRecords = null;
            synchronized(example) {
                ServiceAuthorityDtoExample.Criteria c = cleanExample().or();
                			c.andAuthorityIdEqualTo(record.getAuthorityId());
			c.andServiceIdEqualTo(record.getServiceId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceAuthorityDto> selectAll(){
        synchronized(example) {
            List<ServiceAuthorityDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceAuthorityDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceAuthorityDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceAuthorityDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<ServiceAuthorityDto> selectByExample(ServiceAuthorityDtoExample example) {
        List<ServiceAuthorityDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(ServiceAuthorityDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<ServiceAuthorityDto> records) {
        for(ServiceAuthorityDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(ServiceAuthorityDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<ServiceAuthorityDto> records){
        for(ServiceAuthorityDto record : records){
            synchronized(example) {
                ServiceAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
				c.andServiceIdEqualTo(record.getServiceId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long authorityId,String serviceId){
        ServiceAuthorityDto record = new ServiceAuthorityDto();
		record.setAuthorityId(authorityId);
		record.setServiceId(serviceId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ServiceAuthorityDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<ServiceAuthorityDto> records) {
        for(ServiceAuthorityDto record : records){
            synchronized(example) {
                ServiceAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
				c.andServiceIdEqualTo(record.getServiceId());
                mapper.deleteByExample(example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteAll() {
        synchronized(example) {
            mapper.deleteByExample(cleanExample());
            return true;
        }
    }
    
    public ServiceAuthorityDtoExample example() {
        return new ServiceAuthorityDtoExample();
    }
  
    protected List<ServiceAuthorityDto> sortByCdate(List<ServiceAuthorityDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected ServiceAuthorityDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
