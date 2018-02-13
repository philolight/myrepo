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
import com.lge.sm.cr_data_store.mapper.UserAuthorityDtoMapper;
import com.lge.sm.cr_data_store.dto.UserAuthorityDto;
import com.lge.sm.cr_data_store.dto.UserAuthorityDtoExample;

abstract public class AUserAuthorityDao implements DataAccessObject<UserAuthorityDto, UserAuthorityDtoExample>{
    protected final UserAuthorityDtoMapper mapper;
    protected final UserAuthorityDtoExample example = new UserAuthorityDtoExample();
          
    public AUserAuthorityDao(UserAuthorityDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public UserAuthorityDto select(String userId,Long authorityId){
        UserAuthorityDto record = new UserAuthorityDto();
		record.setUserId(userId);
		record.setAuthorityId(authorityId);
        List<UserAuthorityDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserAuthorityDto> select(List<UserAuthorityDto> records){
        List<UserAuthorityDto> list = new ArrayList<UserAuthorityDto>();
        for(UserAuthorityDto record : records){
            List<UserAuthorityDto> newRecords = null;
            synchronized(example) {
                UserAuthorityDtoExample.Criteria c = cleanExample().or();
                			c.andUserIdEqualTo(record.getUserId());
			c.andAuthorityIdEqualTo(record.getAuthorityId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserAuthorityDto> selectAll(){
        synchronized(example) {
            List<UserAuthorityDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserAuthorityDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserAuthorityDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<UserAuthorityDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<UserAuthorityDto> selectByExample(UserAuthorityDtoExample example) {
        List<UserAuthorityDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(UserAuthorityDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<UserAuthorityDto> records) {
        for(UserAuthorityDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(UserAuthorityDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<UserAuthorityDto> records){
        for(UserAuthorityDto record : records){
            synchronized(example) {
                UserAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
				c.andAuthorityIdEqualTo(record.getAuthorityId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String userId,Long authorityId){
        UserAuthorityDto record = new UserAuthorityDto();
		record.setUserId(userId);
		record.setAuthorityId(authorityId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(UserAuthorityDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<UserAuthorityDto> records) {
        for(UserAuthorityDto record : records){
            synchronized(example) {
                UserAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
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
    
    public UserAuthorityDtoExample example() {
        return new UserAuthorityDtoExample();
    }
  
    protected List<UserAuthorityDto> sortByCdate(List<UserAuthorityDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected UserAuthorityDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
