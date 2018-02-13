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
import com.lge.sm.cr_data_store.mapper.DriverTypeDtoMapper;
import com.lge.sm.cr_data_store.dto.DriverTypeDto;
import com.lge.sm.cr_data_store.dto.DriverTypeDtoExample;

abstract public class ADriverTypeDao implements DataAccessObject<DriverTypeDto, DriverTypeDtoExample>{
    protected final DriverTypeDtoMapper mapper;
    protected final DriverTypeDtoExample example = new DriverTypeDtoExample();
          
    public ADriverTypeDao(DriverTypeDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public DriverTypeDto select(String driverTypeId){
        DriverTypeDto record = new DriverTypeDto();
		record.setDriverTypeId(driverTypeId);
        List<DriverTypeDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverTypeDto> select(List<DriverTypeDto> records){
        List<DriverTypeDto> list = new ArrayList<DriverTypeDto>();
        for(DriverTypeDto record : records){
            List<DriverTypeDto> newRecords = null;
            synchronized(example) {
                DriverTypeDtoExample.Criteria c = cleanExample().or();
                			c.andDriverTypeIdEqualTo(record.getDriverTypeId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverTypeDto> selectAll(){
        synchronized(example) {
            List<DriverTypeDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverTypeDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverTypeDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverTypeDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<DriverTypeDto> selectByExample(DriverTypeDtoExample example) {
        List<DriverTypeDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(DriverTypeDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<DriverTypeDto> records) {
        for(DriverTypeDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(DriverTypeDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<DriverTypeDto> records){
        for(DriverTypeDto record : records){
            synchronized(example) {
                DriverTypeDtoExample.Criteria c = cleanExample().or();
				c.andDriverTypeIdEqualTo(record.getDriverTypeId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String driverTypeId){
        DriverTypeDto record = new DriverTypeDto();
		record.setDriverTypeId(driverTypeId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(DriverTypeDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<DriverTypeDto> records) {
        for(DriverTypeDto record : records){
            synchronized(example) {
                DriverTypeDtoExample.Criteria c = cleanExample().or();
				c.andDriverTypeIdEqualTo(record.getDriverTypeId());
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
    
    public DriverTypeDtoExample example() {
        return new DriverTypeDtoExample();
    }
  
    protected List<DriverTypeDto> sortByCdate(List<DriverTypeDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected DriverTypeDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
