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
import com.lge.sm.cr_data_store.mapper.EventHistoryDtoMapper;
import com.lge.sm.cr_data_store.dto.EventHistoryDto;
import com.lge.sm.cr_data_store.dto.EventHistoryDtoExample;

abstract public class AEventHistoryDao implements DataAccessObject<EventHistoryDto, EventHistoryDtoExample>{
    protected final EventHistoryDtoMapper mapper;
    protected final EventHistoryDtoExample example = new EventHistoryDtoExample();
          
    public AEventHistoryDao(EventHistoryDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public EventHistoryDto select(String name,String cdate){
        EventHistoryDto record = new EventHistoryDto();
		record.setName(name);
		record.setCdate(cdate);
        List<EventHistoryDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EventHistoryDto> select(List<EventHistoryDto> records){
        List<EventHistoryDto> list = new ArrayList<EventHistoryDto>();
        for(EventHistoryDto record : records){
            List<EventHistoryDto> newRecords = null;
            synchronized(example) {
                EventHistoryDtoExample.Criteria c = cleanExample().or();
                			c.andNameEqualTo(record.getName());
			c.andCdateEqualTo(record.getCdate());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EventHistoryDto> selectAll(){
        synchronized(example) {
            List<EventHistoryDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EventHistoryDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EventHistoryDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EventHistoryDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<EventHistoryDto> selectByExample(EventHistoryDtoExample example) {
        List<EventHistoryDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(EventHistoryDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<EventHistoryDto> records) {
        for(EventHistoryDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(EventHistoryDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<EventHistoryDto> records){
        for(EventHistoryDto record : records){
            synchronized(example) {
                EventHistoryDtoExample.Criteria c = cleanExample().or();
				c.andNameEqualTo(record.getName());
				c.andCdateEqualTo(record.getCdate());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String name,String cdate){
        EventHistoryDto record = new EventHistoryDto();
		record.setName(name);
		record.setCdate(cdate);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(EventHistoryDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<EventHistoryDto> records) {
        for(EventHistoryDto record : records){
            synchronized(example) {
                EventHistoryDtoExample.Criteria c = cleanExample().or();
				c.andNameEqualTo(record.getName());
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
    
    public EventHistoryDtoExample example() {
        return new EventHistoryDtoExample();
    }
  
    protected List<EventHistoryDto> sortByCdate(List<EventHistoryDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected EventHistoryDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
