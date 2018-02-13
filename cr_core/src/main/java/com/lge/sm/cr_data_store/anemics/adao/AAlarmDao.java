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
import com.lge.sm.cr_data_store.mapper.AlarmDtoMapper;
import com.lge.sm.cr_data_store.dto.AlarmDto;
import com.lge.sm.cr_data_store.dto.AlarmDtoExample;

abstract public class AAlarmDao implements DataAccessObject<AlarmDto, AlarmDtoExample>{
    protected final AlarmDtoMapper mapper;
    protected final AlarmDtoExample example = new AlarmDtoExample();
          
    public AAlarmDao(AlarmDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public AlarmDto select(Long alarmId){
        AlarmDto record = new AlarmDto();
		record.setAlarmId(alarmId);
        List<AlarmDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AlarmDto> select(List<AlarmDto> records){
        List<AlarmDto> list = new ArrayList<AlarmDto>();
        for(AlarmDto record : records){
            List<AlarmDto> newRecords = null;
            synchronized(example) {
                AlarmDtoExample.Criteria c = cleanExample().or();
                			c.andAlarmIdEqualTo(record.getAlarmId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AlarmDto> selectAll(){
        synchronized(example) {
            List<AlarmDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AlarmDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AlarmDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AlarmDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<AlarmDto> selectByExample(AlarmDtoExample example) {
        List<AlarmDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(AlarmDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<AlarmDto> records) {
        for(AlarmDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(AlarmDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<AlarmDto> records){
        for(AlarmDto record : records){
            synchronized(example) {
                AlarmDtoExample.Criteria c = cleanExample().or();
				c.andAlarmIdEqualTo(record.getAlarmId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long alarmId){
        AlarmDto record = new AlarmDto();
		record.setAlarmId(alarmId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(AlarmDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<AlarmDto> records) {
        for(AlarmDto record : records){
            synchronized(example) {
                AlarmDtoExample.Criteria c = cleanExample().or();
				c.andAlarmIdEqualTo(record.getAlarmId());
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
    
    public AlarmDtoExample example() {
        return new AlarmDtoExample();
    }
  
    protected List<AlarmDto> sortByCdate(List<AlarmDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected AlarmDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<AlarmDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("alarm_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getAlarmId();
		return -1;
	}
}
