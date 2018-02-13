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
import com.lge.sm.cr_data_store.mapper.SlmDtoMapper;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_data_store.dto.SlmDtoExample;

abstract public class ASlmDao implements DataAccessObject<SlmDto, SlmDtoExample>{
    protected final SlmDtoMapper mapper;
    protected final SlmDtoExample example = new SlmDtoExample();
          
    public ASlmDao(SlmDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SlmDto select(String slmId){
        SlmDto record = new SlmDto();
		record.setSlmId(slmId);
        List<SlmDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SlmDto> select(List<SlmDto> records){
        List<SlmDto> list = new ArrayList<SlmDto>();
        for(SlmDto record : records){
            List<SlmDto> newRecords = null;
            synchronized(example) {
                SlmDtoExample.Criteria c = cleanExample().or();
                			c.andSlmIdEqualTo(record.getSlmId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SlmDto> selectAll(){
        synchronized(example) {
            List<SlmDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SlmDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SlmDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SlmDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<SlmDto> selectByExample(SlmDtoExample example) {
        List<SlmDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(SlmDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<SlmDto> records) {
        for(SlmDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(SlmDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<SlmDto> records){
        for(SlmDto record : records){
            synchronized(example) {
                SlmDtoExample.Criteria c = cleanExample().or();
				c.andSlmIdEqualTo(record.getSlmId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String slmId){
        SlmDto record = new SlmDto();
		record.setSlmId(slmId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(SlmDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<SlmDto> records) {
        for(SlmDto record : records){
            synchronized(example) {
                SlmDtoExample.Criteria c = cleanExample().or();
				c.andSlmIdEqualTo(record.getSlmId());
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
    
    public SlmDtoExample example() {
        return new SlmDtoExample();
    }
  
    protected List<SlmDto> sortByCdate(List<SlmDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected SlmDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
