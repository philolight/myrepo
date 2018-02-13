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
import com.lge.sm.cr_data_store.mapper.FieldSkinDtoMapper;
import com.lge.sm.cr_data_store.dto.FieldSkinDto;
import com.lge.sm.cr_data_store.dto.FieldSkinDtoExample;

abstract public class AFieldSkinDao implements DataAccessObject<FieldSkinDto, FieldSkinDtoExample>{
    protected final FieldSkinDtoMapper mapper;
    protected final FieldSkinDtoExample example = new FieldSkinDtoExample();
          
    public AFieldSkinDao(FieldSkinDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public FieldSkinDto select(String fieldSkinId){
        FieldSkinDto record = new FieldSkinDto();
		record.setFieldSkinId(fieldSkinId);
        List<FieldSkinDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<FieldSkinDto> select(List<FieldSkinDto> records){
        List<FieldSkinDto> list = new ArrayList<FieldSkinDto>();
        for(FieldSkinDto record : records){
            List<FieldSkinDto> newRecords = null;
            synchronized(example) {
                FieldSkinDtoExample.Criteria c = cleanExample().or();
                			c.andFieldSkinIdEqualTo(record.getFieldSkinId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<FieldSkinDto> selectAll(){
        synchronized(example) {
            List<FieldSkinDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<FieldSkinDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<FieldSkinDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<FieldSkinDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<FieldSkinDto> selectByExample(FieldSkinDtoExample example) {
        List<FieldSkinDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(FieldSkinDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<FieldSkinDto> records) {
        for(FieldSkinDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(FieldSkinDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<FieldSkinDto> records){
        for(FieldSkinDto record : records){
            synchronized(example) {
                FieldSkinDtoExample.Criteria c = cleanExample().or();
				c.andFieldSkinIdEqualTo(record.getFieldSkinId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String fieldSkinId){
        FieldSkinDto record = new FieldSkinDto();
		record.setFieldSkinId(fieldSkinId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(FieldSkinDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<FieldSkinDto> records) {
        for(FieldSkinDto record : records){
            synchronized(example) {
                FieldSkinDtoExample.Criteria c = cleanExample().or();
				c.andFieldSkinIdEqualTo(record.getFieldSkinId());
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
    
    public FieldSkinDtoExample example() {
        return new FieldSkinDtoExample();
    }
  
    protected List<FieldSkinDto> sortByCdate(List<FieldSkinDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected FieldSkinDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
