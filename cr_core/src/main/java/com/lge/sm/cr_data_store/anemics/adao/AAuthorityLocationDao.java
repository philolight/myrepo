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
import com.lge.sm.cr_data_store.mapper.AuthorityLocationDtoMapper;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDto;
import com.lge.sm.cr_data_store.dto.AuthorityLocationDtoExample;

abstract public class AAuthorityLocationDao implements DataAccessObject<AuthorityLocationDto, AuthorityLocationDtoExample>{
    protected final AuthorityLocationDtoMapper mapper;
    protected final AuthorityLocationDtoExample example = new AuthorityLocationDtoExample();
          
    public AAuthorityLocationDao(AuthorityLocationDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public AuthorityLocationDto select(Long authorityId,String locationId){
        AuthorityLocationDto record = new AuthorityLocationDto();
		record.setAuthorityId(authorityId);
		record.setLocationId(locationId);
        List<AuthorityLocationDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityLocationDto> select(List<AuthorityLocationDto> records){
        List<AuthorityLocationDto> list = new ArrayList<AuthorityLocationDto>();
        for(AuthorityLocationDto record : records){
            List<AuthorityLocationDto> newRecords = null;
            synchronized(example) {
                AuthorityLocationDtoExample.Criteria c = cleanExample().or();
                			c.andAuthorityIdEqualTo(record.getAuthorityId());
			c.andLocationIdEqualTo(record.getLocationId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityLocationDto> selectAll(){
        synchronized(example) {
            List<AuthorityLocationDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityLocationDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityLocationDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityLocationDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<AuthorityLocationDto> selectByExample(AuthorityLocationDtoExample example) {
        List<AuthorityLocationDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(AuthorityLocationDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<AuthorityLocationDto> records) {
        for(AuthorityLocationDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(AuthorityLocationDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<AuthorityLocationDto> records){
        for(AuthorityLocationDto record : records){
            synchronized(example) {
                AuthorityLocationDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
				c.andLocationIdEqualTo(record.getLocationId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long authorityId,String locationId){
        AuthorityLocationDto record = new AuthorityLocationDto();
		record.setAuthorityId(authorityId);
		record.setLocationId(locationId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(AuthorityLocationDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<AuthorityLocationDto> records) {
        for(AuthorityLocationDto record : records){
            synchronized(example) {
                AuthorityLocationDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
				c.andLocationIdEqualTo(record.getLocationId());
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
    
    public AuthorityLocationDtoExample example() {
        return new AuthorityLocationDtoExample();
    }
  
    protected List<AuthorityLocationDto> sortByCdate(List<AuthorityLocationDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected AuthorityLocationDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
