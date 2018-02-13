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
import com.lge.sm.cr_data_store.mapper.StartableDtoMapper;
import com.lge.sm.cr_data_store.dto.StartableDto;
import com.lge.sm.cr_data_store.dto.StartableDtoExample;

abstract public class AStartableDao implements DataAccessObject<StartableDto, StartableDtoExample>{
    protected final StartableDtoMapper mapper;
    protected final StartableDtoExample example = new StartableDtoExample();
          
    public AStartableDao(StartableDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public StartableDto select(String startableId){
        StartableDto record = new StartableDto();
		record.setStartableId(startableId);
        List<StartableDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableDto> select(List<StartableDto> records){
        List<StartableDto> list = new ArrayList<StartableDto>();
        for(StartableDto record : records){
            List<StartableDto> newRecords = null;
            synchronized(example) {
                StartableDtoExample.Criteria c = cleanExample().or();
                			c.andStartableIdEqualTo(record.getStartableId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableDto> selectAll(){
        synchronized(example) {
            List<StartableDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<StartableDto> selectByExample(StartableDtoExample example) {
        List<StartableDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(StartableDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<StartableDto> records) {
        for(StartableDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(StartableDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<StartableDto> records){
        for(StartableDto record : records){
            synchronized(example) {
                StartableDtoExample.Criteria c = cleanExample().or();
				c.andStartableIdEqualTo(record.getStartableId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String startableId){
        StartableDto record = new StartableDto();
		record.setStartableId(startableId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(StartableDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<StartableDto> records) {
        for(StartableDto record : records){
            synchronized(example) {
                StartableDtoExample.Criteria c = cleanExample().or();
				c.andStartableIdEqualTo(record.getStartableId());
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
    
    public StartableDtoExample example() {
        return new StartableDtoExample();
    }
  
    protected List<StartableDto> sortByCdate(List<StartableDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected StartableDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
