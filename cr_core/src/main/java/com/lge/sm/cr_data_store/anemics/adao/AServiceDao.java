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
import com.lge.sm.cr_data_store.mapper.ServiceDtoMapper;
import com.lge.sm.cr_data_store.dto.ServiceDto;
import com.lge.sm.cr_data_store.dto.ServiceDtoExample;

abstract public class AServiceDao implements DataAccessObject<ServiceDto, ServiceDtoExample>{
    protected final ServiceDtoMapper mapper;
    protected final ServiceDtoExample example = new ServiceDtoExample();
          
    public AServiceDao(ServiceDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ServiceDto select(String serviceId){
        ServiceDto record = new ServiceDto();
		record.setServiceId(serviceId);
        List<ServiceDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceDto> select(List<ServiceDto> records){
        List<ServiceDto> list = new ArrayList<ServiceDto>();
        for(ServiceDto record : records){
            List<ServiceDto> newRecords = null;
            synchronized(example) {
                ServiceDtoExample.Criteria c = cleanExample().or();
                			c.andServiceIdEqualTo(record.getServiceId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceDto> selectAll(){
        synchronized(example) {
            List<ServiceDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ServiceDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<ServiceDto> selectByExample(ServiceDtoExample example) {
        List<ServiceDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(ServiceDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<ServiceDto> records) {
        for(ServiceDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(ServiceDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<ServiceDto> records){
        for(ServiceDto record : records){
            synchronized(example) {
                ServiceDtoExample.Criteria c = cleanExample().or();
				c.andServiceIdEqualTo(record.getServiceId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String serviceId){
        ServiceDto record = new ServiceDto();
		record.setServiceId(serviceId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ServiceDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<ServiceDto> records) {
        for(ServiceDto record : records){
            synchronized(example) {
                ServiceDtoExample.Criteria c = cleanExample().or();
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
    
    public ServiceDtoExample example() {
        return new ServiceDtoExample();
    }
  
    protected List<ServiceDto> sortByCdate(List<ServiceDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected ServiceDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
