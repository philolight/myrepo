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
import com.lge.sm.cr_data_store.mapper.PartyUserDtoMapper;
import com.lge.sm.cr_data_store.dto.PartyUserDto;
import com.lge.sm.cr_data_store.dto.PartyUserDtoExample;

abstract public class APartyUserDao implements DataAccessObject<PartyUserDto, PartyUserDtoExample>{
    protected final PartyUserDtoMapper mapper;
    protected final PartyUserDtoExample example = new PartyUserDtoExample();
          
    public APartyUserDao(PartyUserDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PartyUserDto select(String userId,String partyId){
        PartyUserDto record = new PartyUserDto();
		record.setUserId(userId);
		record.setPartyId(partyId);
        List<PartyUserDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyUserDto> select(List<PartyUserDto> records){
        List<PartyUserDto> list = new ArrayList<PartyUserDto>();
        for(PartyUserDto record : records){
            List<PartyUserDto> newRecords = null;
            synchronized(example) {
                PartyUserDtoExample.Criteria c = cleanExample().or();
                			c.andUserIdEqualTo(record.getUserId());
			c.andPartyIdEqualTo(record.getPartyId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyUserDto> selectAll(){
        synchronized(example) {
            List<PartyUserDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyUserDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyUserDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyUserDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<PartyUserDto> selectByExample(PartyUserDtoExample example) {
        List<PartyUserDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(PartyUserDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<PartyUserDto> records) {
        for(PartyUserDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PartyUserDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<PartyUserDto> records){
        for(PartyUserDto record : records){
            synchronized(example) {
                PartyUserDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
				c.andPartyIdEqualTo(record.getPartyId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String userId,String partyId){
        PartyUserDto record = new PartyUserDto();
		record.setUserId(userId);
		record.setPartyId(partyId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PartyUserDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<PartyUserDto> records) {
        for(PartyUserDto record : records){
            synchronized(example) {
                PartyUserDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
				c.andPartyIdEqualTo(record.getPartyId());
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
    
    public PartyUserDtoExample example() {
        return new PartyUserDtoExample();
    }
  
    protected List<PartyUserDto> sortByCdate(List<PartyUserDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected PartyUserDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
