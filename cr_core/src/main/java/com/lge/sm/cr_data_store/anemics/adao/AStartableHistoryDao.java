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
import com.lge.sm.cr_data_store.mapper.StartableHistoryDtoMapper;
import com.lge.sm.cr_data_store.dto.StartableHistoryDto;
import com.lge.sm.cr_data_store.dto.StartableHistoryDtoExample;

abstract public class AStartableHistoryDao implements DataAccessObject<StartableHistoryDto, StartableHistoryDtoExample>{
    protected final StartableHistoryDtoMapper mapper;
    protected final StartableHistoryDtoExample example = new StartableHistoryDtoExample();
          
    public AStartableHistoryDao(StartableHistoryDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public StartableHistoryDto select(String startableId,String cdate){
        StartableHistoryDto record = new StartableHistoryDto();
		record.setStartableId(startableId);
		record.setCdate(cdate);
        List<StartableHistoryDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableHistoryDto> select(List<StartableHistoryDto> records){
        List<StartableHistoryDto> list = new ArrayList<StartableHistoryDto>();
        for(StartableHistoryDto record : records){
            List<StartableHistoryDto> newRecords = null;
            synchronized(example) {
                StartableHistoryDtoExample.Criteria c = cleanExample().or();
                			c.andStartableIdEqualTo(record.getStartableId());
			c.andCdateEqualTo(record.getCdate());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableHistoryDto> selectAll(){
        synchronized(example) {
            List<StartableHistoryDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableHistoryDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableHistoryDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StartableHistoryDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<StartableHistoryDto> selectByExample(StartableHistoryDtoExample example) {
        List<StartableHistoryDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(StartableHistoryDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<StartableHistoryDto> records) {
        for(StartableHistoryDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(StartableHistoryDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<StartableHistoryDto> records){
        for(StartableHistoryDto record : records){
            synchronized(example) {
                StartableHistoryDtoExample.Criteria c = cleanExample().or();
				c.andStartableIdEqualTo(record.getStartableId());
				c.andCdateEqualTo(record.getCdate());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String startableId,String cdate){
        StartableHistoryDto record = new StartableHistoryDto();
		record.setStartableId(startableId);
		record.setCdate(cdate);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(StartableHistoryDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<StartableHistoryDto> records) {
        for(StartableHistoryDto record : records){
            synchronized(example) {
                StartableHistoryDtoExample.Criteria c = cleanExample().or();
				c.andStartableIdEqualTo(record.getStartableId());
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
    
    public StartableHistoryDtoExample example() {
        return new StartableHistoryDtoExample();
    }
  
    protected List<StartableHistoryDto> sortByCdate(List<StartableHistoryDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected StartableHistoryDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
