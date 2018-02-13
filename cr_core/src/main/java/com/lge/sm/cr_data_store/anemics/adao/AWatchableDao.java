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
import com.lge.sm.cr_data_store.mapper.WatchableDtoMapper;
import com.lge.sm.cr_data_store.dto.WatchableDto;
import com.lge.sm.cr_data_store.dto.WatchableDtoExample;

abstract public class AWatchableDao implements DataAccessObject<WatchableDto, WatchableDtoExample>{
    protected final WatchableDtoMapper mapper;
    protected final WatchableDtoExample example = new WatchableDtoExample();
          
    public AWatchableDao(WatchableDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public WatchableDto select(String watchableId){
        WatchableDto record = new WatchableDto();
		record.setWatchableId(watchableId);
        List<WatchableDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableDto> select(List<WatchableDto> records){
        List<WatchableDto> list = new ArrayList<WatchableDto>();
        for(WatchableDto record : records){
            List<WatchableDto> newRecords = null;
            synchronized(example) {
                WatchableDtoExample.Criteria c = cleanExample().or();
                			c.andWatchableIdEqualTo(record.getWatchableId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableDto> selectAll(){
        synchronized(example) {
            List<WatchableDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<WatchableDto> selectByExample(WatchableDtoExample example) {
        List<WatchableDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(WatchableDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<WatchableDto> records) {
        for(WatchableDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(WatchableDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<WatchableDto> records){
        for(WatchableDto record : records){
            synchronized(example) {
                WatchableDtoExample.Criteria c = cleanExample().or();
				c.andWatchableIdEqualTo(record.getWatchableId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String watchableId){
        WatchableDto record = new WatchableDto();
		record.setWatchableId(watchableId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(WatchableDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<WatchableDto> records) {
        for(WatchableDto record : records){
            synchronized(example) {
                WatchableDtoExample.Criteria c = cleanExample().or();
				c.andWatchableIdEqualTo(record.getWatchableId());
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
    
    public WatchableDtoExample example() {
        return new WatchableDtoExample();
    }
  
    protected List<WatchableDto> sortByCdate(List<WatchableDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected WatchableDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
