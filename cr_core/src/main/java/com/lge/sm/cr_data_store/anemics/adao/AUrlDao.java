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
import com.lge.sm.cr_data_store.mapper.UrlDtoMapper;
import com.lge.sm.cr_data_store.dto.UrlDto;
import com.lge.sm.cr_data_store.dto.UrlDtoExample;

abstract public class AUrlDao implements DataAccessObject<UrlDto, UrlDtoExample>{
    protected final UrlDtoMapper mapper;
    protected final UrlDtoExample example = new UrlDtoExample();
          
    public AUrlDao(UrlDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UrlDto select(Long urlId,String url){
        UrlDto record = new UrlDto();
		record.setUrlId(urlId);
		record.setUrl(url);
        List<UrlDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UrlDto> select(List<UrlDto> records){
        List<UrlDto> list = new ArrayList<UrlDto>();
        for(UrlDto record : records){
            List<UrlDto> newRecords = null;
            synchronized(example) {
                UrlDtoExample.Criteria c = cleanExample().or();
                			c.andUrlIdEqualTo(record.getUrlId());
			c.andUrlEqualTo(record.getUrl());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UrlDto> selectAll(){
        synchronized(example) {
            List<UrlDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UrlDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UrlDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UrlDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<UrlDto> selectByExample(UrlDtoExample example) {
        List<UrlDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(UrlDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<UrlDto> records) {
        for(UrlDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(UrlDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<UrlDto> records){
        for(UrlDto record : records){
            synchronized(example) {
                UrlDtoExample.Criteria c = cleanExample().or();
				c.andUrlIdEqualTo(record.getUrlId());
				c.andUrlEqualTo(record.getUrl());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long urlId,String url){
        UrlDto record = new UrlDto();
		record.setUrlId(urlId);
		record.setUrl(url);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(UrlDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<UrlDto> records) {
        for(UrlDto record : records){
            synchronized(example) {
                UrlDtoExample.Criteria c = cleanExample().or();
				c.andUrlIdEqualTo(record.getUrlId());
				c.andUrlEqualTo(record.getUrl());
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
    
    public UrlDtoExample example() {
        return new UrlDtoExample();
    }
  
    protected List<UrlDto> sortByCdate(List<UrlDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected UrlDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
