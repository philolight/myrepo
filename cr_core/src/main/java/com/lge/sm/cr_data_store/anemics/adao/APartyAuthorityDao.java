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
import com.lge.sm.cr_data_store.mapper.PartyAuthorityDtoMapper;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDto;
import com.lge.sm.cr_data_store.dto.PartyAuthorityDtoExample;

abstract public class APartyAuthorityDao implements DataAccessObject<PartyAuthorityDto, PartyAuthorityDtoExample>{
    protected final PartyAuthorityDtoMapper mapper;
    protected final PartyAuthorityDtoExample example = new PartyAuthorityDtoExample();
          
    public APartyAuthorityDao(PartyAuthorityDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PartyAuthorityDto select(Long authorityId,String partyId){
        PartyAuthorityDto record = new PartyAuthorityDto();
		record.setAuthorityId(authorityId);
		record.setPartyId(partyId);
        List<PartyAuthorityDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyAuthorityDto> select(List<PartyAuthorityDto> records){
        List<PartyAuthorityDto> list = new ArrayList<PartyAuthorityDto>();
        for(PartyAuthorityDto record : records){
            List<PartyAuthorityDto> newRecords = null;
            synchronized(example) {
                PartyAuthorityDtoExample.Criteria c = cleanExample().or();
                			c.andAuthorityIdEqualTo(record.getAuthorityId());
			c.andPartyIdEqualTo(record.getPartyId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyAuthorityDto> selectAll(){
        synchronized(example) {
            List<PartyAuthorityDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyAuthorityDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyAuthorityDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyAuthorityDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<PartyAuthorityDto> selectByExample(PartyAuthorityDtoExample example) {
        List<PartyAuthorityDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(PartyAuthorityDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<PartyAuthorityDto> records) {
        for(PartyAuthorityDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PartyAuthorityDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<PartyAuthorityDto> records){
        for(PartyAuthorityDto record : records){
            synchronized(example) {
                PartyAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
				c.andPartyIdEqualTo(record.getPartyId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long authorityId,String partyId){
        PartyAuthorityDto record = new PartyAuthorityDto();
		record.setAuthorityId(authorityId);
		record.setPartyId(partyId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PartyAuthorityDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<PartyAuthorityDto> records) {
        for(PartyAuthorityDto record : records){
            synchronized(example) {
                PartyAuthorityDtoExample.Criteria c = cleanExample().or();
				c.andAuthorityIdEqualTo(record.getAuthorityId());
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
    
    public PartyAuthorityDtoExample example() {
        return new PartyAuthorityDtoExample();
    }
  
    protected List<PartyAuthorityDto> sortByCdate(List<PartyAuthorityDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected PartyAuthorityDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
