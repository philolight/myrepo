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
import com.lge.sm.cr_data_store.mapper.WatchableHistoryDtoMapper;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDto;
import com.lge.sm.cr_data_store.dto.WatchableHistoryDtoExample;

abstract public class AWatchableHistoryDao implements DataAccessObject<WatchableHistoryDto, WatchableHistoryDtoExample>{
    protected final WatchableHistoryDtoMapper mapper;
    protected final WatchableHistoryDtoExample example = new WatchableHistoryDtoExample();
          
    public AWatchableHistoryDao(WatchableHistoryDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public WatchableHistoryDto select(String watchableId,String cdate){
        WatchableHistoryDto record = new WatchableHistoryDto();
		record.setWatchableId(watchableId);
		record.setCdate(cdate);
        List<WatchableHistoryDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableHistoryDto> select(List<WatchableHistoryDto> records){
        List<WatchableHistoryDto> list = new ArrayList<WatchableHistoryDto>();
        for(WatchableHistoryDto record : records){
            List<WatchableHistoryDto> newRecords = null;
            synchronized(example) {
                WatchableHistoryDtoExample.Criteria c = cleanExample().or();
                			c.andWatchableIdEqualTo(record.getWatchableId());
			c.andCdateEqualTo(record.getCdate());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableHistoryDto> selectAll(){
        synchronized(example) {
            List<WatchableHistoryDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableHistoryDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableHistoryDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<WatchableHistoryDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<WatchableHistoryDto> selectByExample(WatchableHistoryDtoExample example) {
        List<WatchableHistoryDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(WatchableHistoryDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<WatchableHistoryDto> records) {
        for(WatchableHistoryDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(WatchableHistoryDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<WatchableHistoryDto> records){
        for(WatchableHistoryDto record : records){
            synchronized(example) {
                WatchableHistoryDtoExample.Criteria c = cleanExample().or();
				c.andWatchableIdEqualTo(record.getWatchableId());
				c.andCdateEqualTo(record.getCdate());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String watchableId,String cdate){
        WatchableHistoryDto record = new WatchableHistoryDto();
		record.setWatchableId(watchableId);
		record.setCdate(cdate);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(WatchableHistoryDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<WatchableHistoryDto> records) {
        for(WatchableHistoryDto record : records){
            synchronized(example) {
                WatchableHistoryDtoExample.Criteria c = cleanExample().or();
				c.andWatchableIdEqualTo(record.getWatchableId());
				c.andCdateEqualTo(record.getCdate());
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
    
    public WatchableHistoryDtoExample example() {
        return new WatchableHistoryDtoExample();
    }
  
    protected List<WatchableHistoryDto> sortByCdate(List<WatchableHistoryDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected WatchableHistoryDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
