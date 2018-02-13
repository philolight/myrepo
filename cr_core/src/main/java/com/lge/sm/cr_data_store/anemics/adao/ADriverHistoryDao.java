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
import com.lge.sm.cr_data_store.mapper.DriverHistoryDtoMapper;
import com.lge.sm.cr_data_store.dto.DriverHistoryDto;
import com.lge.sm.cr_data_store.dto.DriverHistoryDtoExample;

abstract public class ADriverHistoryDao implements DataAccessObject<DriverHistoryDto, DriverHistoryDtoExample>{
    protected final DriverHistoryDtoMapper mapper;
    protected final DriverHistoryDtoExample example = new DriverHistoryDtoExample();
          
    public ADriverHistoryDao(DriverHistoryDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public DriverHistoryDto select(String cdate,Long driverId){
        DriverHistoryDto record = new DriverHistoryDto();
		record.setCdate(cdate);
		record.setDriverId(driverId);
        List<DriverHistoryDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverHistoryDto> select(List<DriverHistoryDto> records){
        List<DriverHistoryDto> list = new ArrayList<DriverHistoryDto>();
        for(DriverHistoryDto record : records){
            List<DriverHistoryDto> newRecords = null;
            synchronized(example) {
                DriverHistoryDtoExample.Criteria c = cleanExample().or();
                			c.andCdateEqualTo(record.getCdate());
			c.andDriverIdEqualTo(record.getDriverId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverHistoryDto> selectAll(){
        synchronized(example) {
            List<DriverHistoryDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverHistoryDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverHistoryDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverHistoryDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<DriverHistoryDto> selectByExample(DriverHistoryDtoExample example) {
        List<DriverHistoryDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(DriverHistoryDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<DriverHistoryDto> records) {
        for(DriverHistoryDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(DriverHistoryDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<DriverHistoryDto> records){
        for(DriverHistoryDto record : records){
            synchronized(example) {
                DriverHistoryDtoExample.Criteria c = cleanExample().or();
				c.andCdateEqualTo(record.getCdate());
				c.andDriverIdEqualTo(record.getDriverId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String cdate,Long driverId){
        DriverHistoryDto record = new DriverHistoryDto();
		record.setCdate(cdate);
		record.setDriverId(driverId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(DriverHistoryDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<DriverHistoryDto> records) {
        for(DriverHistoryDto record : records){
            synchronized(example) {
                DriverHistoryDtoExample.Criteria c = cleanExample().or();
				c.andCdateEqualTo(record.getCdate());
				c.andDriverIdEqualTo(record.getDriverId());
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
    
    public DriverHistoryDtoExample example() {
        return new DriverHistoryDtoExample();
    }
  
    protected List<DriverHistoryDto> sortByCdate(List<DriverHistoryDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected DriverHistoryDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
