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
import com.lge.sm.cr_data_store.mapper.ScheduleDtoMapper;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.ScheduleDtoExample;

abstract public class AScheduleDao implements DataAccessObject<ScheduleDto, ScheduleDtoExample>{
    protected final ScheduleDtoMapper mapper;
    protected final ScheduleDtoExample example = new ScheduleDtoExample();
          
    public AScheduleDao(ScheduleDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ScheduleDto select(String scheduleId){
        ScheduleDto record = new ScheduleDto();
		record.setScheduleId(scheduleId);
        List<ScheduleDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScheduleDto> select(List<ScheduleDto> records){
        List<ScheduleDto> list = new ArrayList<ScheduleDto>();
        for(ScheduleDto record : records){
            List<ScheduleDto> newRecords = null;
            synchronized(example) {
                ScheduleDtoExample.Criteria c = cleanExample().or();
                			c.andScheduleIdEqualTo(record.getScheduleId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScheduleDto> selectAll(){
        synchronized(example) {
            List<ScheduleDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScheduleDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScheduleDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScheduleDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<ScheduleDto> selectByExample(ScheduleDtoExample example) {
        List<ScheduleDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(ScheduleDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<ScheduleDto> records) {
        for(ScheduleDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(ScheduleDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<ScheduleDto> records){
        for(ScheduleDto record : records){
            synchronized(example) {
                ScheduleDtoExample.Criteria c = cleanExample().or();
				c.andScheduleIdEqualTo(record.getScheduleId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String scheduleId){
        ScheduleDto record = new ScheduleDto();
		record.setScheduleId(scheduleId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ScheduleDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<ScheduleDto> records) {
        for(ScheduleDto record : records){
            synchronized(example) {
                ScheduleDtoExample.Criteria c = cleanExample().or();
				c.andScheduleIdEqualTo(record.getScheduleId());
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
    
    public ScheduleDtoExample example() {
        return new ScheduleDtoExample();
    }
  
    protected List<ScheduleDto> sortByCdate(List<ScheduleDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected ScheduleDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
