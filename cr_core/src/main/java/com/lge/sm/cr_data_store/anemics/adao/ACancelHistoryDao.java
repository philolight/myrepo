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
import com.lge.sm.cr_data_store.mapper.CancelHistoryDtoMapper;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.dto.CancelHistoryDtoExample;

abstract public class ACancelHistoryDao implements DataAccessObject<CancelHistoryDto, CancelHistoryDtoExample>{
    protected final CancelHistoryDtoMapper mapper;
    protected final CancelHistoryDtoExample example = new CancelHistoryDtoExample();
          
    public ACancelHistoryDao(CancelHistoryDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public CancelHistoryDto select(String locationId,String dateOf){
        CancelHistoryDto record = new CancelHistoryDto();
		record.setLocationId(locationId);
		record.setDateOf(dateOf);
        List<CancelHistoryDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CancelHistoryDto> select(List<CancelHistoryDto> records){
        List<CancelHistoryDto> list = new ArrayList<CancelHistoryDto>();
        for(CancelHistoryDto record : records){
            List<CancelHistoryDto> newRecords = null;
            synchronized(example) {
                CancelHistoryDtoExample.Criteria c = cleanExample().or();
                			c.andLocationIdEqualTo(record.getLocationId());
			c.andDateOfEqualTo(record.getDateOf());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CancelHistoryDto> selectAll(){
        synchronized(example) {
            List<CancelHistoryDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CancelHistoryDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CancelHistoryDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<CancelHistoryDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<CancelHistoryDto> selectByExample(CancelHistoryDtoExample example) {
        List<CancelHistoryDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(CancelHistoryDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<CancelHistoryDto> records) {
        for(CancelHistoryDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(CancelHistoryDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<CancelHistoryDto> records){
        for(CancelHistoryDto record : records){
            synchronized(example) {
                CancelHistoryDtoExample.Criteria c = cleanExample().or();
				c.andLocationIdEqualTo(record.getLocationId());
				c.andDateOfEqualTo(record.getDateOf());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String locationId,String dateOf){
        CancelHistoryDto record = new CancelHistoryDto();
		record.setLocationId(locationId);
		record.setDateOf(dateOf);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(CancelHistoryDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<CancelHistoryDto> records) {
        for(CancelHistoryDto record : records){
            synchronized(example) {
                CancelHistoryDtoExample.Criteria c = cleanExample().or();
				c.andLocationIdEqualTo(record.getLocationId());
				c.andDateOfEqualTo(record.getDateOf());
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
    
    public CancelHistoryDtoExample example() {
        return new CancelHistoryDtoExample();
    }
  
    protected List<CancelHistoryDto> sortByCdate(List<CancelHistoryDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected CancelHistoryDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
