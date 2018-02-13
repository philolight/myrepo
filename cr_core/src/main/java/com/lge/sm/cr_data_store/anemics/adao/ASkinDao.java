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
import com.lge.sm.cr_data_store.mapper.SkinDtoMapper;
import com.lge.sm.cr_data_store.dto.SkinDto;
import com.lge.sm.cr_data_store.dto.SkinDtoExample;

abstract public class ASkinDao implements DataAccessObject<SkinDto, SkinDtoExample>{
    protected final SkinDtoMapper mapper;
    protected final SkinDtoExample example = new SkinDtoExample();
          
    public ASkinDao(SkinDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SkinDto select(String skinId){
        SkinDto record = new SkinDto();
		record.setSkinId(skinId);
        List<SkinDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SkinDto> select(List<SkinDto> records){
        List<SkinDto> list = new ArrayList<SkinDto>();
        for(SkinDto record : records){
            List<SkinDto> newRecords = null;
            synchronized(example) {
                SkinDtoExample.Criteria c = cleanExample().or();
                			c.andSkinIdEqualTo(record.getSkinId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SkinDto> selectAll(){
        synchronized(example) {
            List<SkinDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SkinDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SkinDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SkinDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<SkinDto> selectByExample(SkinDtoExample example) {
        List<SkinDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(SkinDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<SkinDto> records) {
        for(SkinDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(SkinDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<SkinDto> records){
        for(SkinDto record : records){
            synchronized(example) {
                SkinDtoExample.Criteria c = cleanExample().or();
				c.andSkinIdEqualTo(record.getSkinId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String skinId){
        SkinDto record = new SkinDto();
		record.setSkinId(skinId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(SkinDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<SkinDto> records) {
        for(SkinDto record : records){
            synchronized(example) {
                SkinDtoExample.Criteria c = cleanExample().or();
				c.andSkinIdEqualTo(record.getSkinId());
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
    
    public SkinDtoExample example() {
        return new SkinDtoExample();
    }
  
    protected List<SkinDto> sortByCdate(List<SkinDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected SkinDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
