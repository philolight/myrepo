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
import com.lge.sm.cr_data_store.mapper.AuthorityDtoMapper;
import com.lge.sm.cr_data_store.dto.AuthorityDto;
import com.lge.sm.cr_data_store.dto.AuthorityDtoExample;

abstract public class AAuthorityDao implements DataAccessObject<AuthorityDto, AuthorityDtoExample>{
    protected final AuthorityDtoMapper mapper;
    protected final AuthorityDtoExample example = new AuthorityDtoExample();
          
    public AAuthorityDao(AuthorityDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public AuthorityDto select(Long authorityId){
        AuthorityDto record = new AuthorityDto();
		record.setAuthorityId(authorityId);
        List<AuthorityDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityDto> select(List<AuthorityDto> records){
        List<AuthorityDto> list = new ArrayList<AuthorityDto>();
        for(AuthorityDto record : records){
            List<AuthorityDto> newRecords = null;
            synchronized(example) {
                AuthorityDtoExample.Criteria c = cleanExample().or();
                			c.andAuthorityIdEqualTo(record.getAuthorityId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityDto> selectAll(){
        synchronized(example) {
            List<AuthorityDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<AuthorityDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<AuthorityDto> selectByExample(AuthorityDtoExample example) {
        List<AuthorityDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(AuthorityDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<AuthorityDto> records) {
        for(AuthorityDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(AuthorityDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<AuthorityDto> records){
        for(AuthorityDto record : records){
            synchronized(example) {
                AuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long authorityId){
        AuthorityDto record = new AuthorityDto();
		record.setAuthorityId(authorityId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(AuthorityDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<AuthorityDto> records) {
        for(AuthorityDto record : records){
            synchronized(example) {
                AuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
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
    
    public AuthorityDtoExample example() {
        return new AuthorityDtoExample();
    }
  
    protected List<AuthorityDto> sortByCdate(List<AuthorityDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected AuthorityDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<AuthorityDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("authority_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getAuthorityId();
		return -1;
	}
}
