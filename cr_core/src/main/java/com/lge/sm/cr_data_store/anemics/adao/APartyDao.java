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
import com.lge.sm.cr_data_store.mapper.PartyDtoMapper;
import com.lge.sm.cr_data_store.dto.PartyDto;
import com.lge.sm.cr_data_store.dto.PartyDtoExample;

abstract public class APartyDao implements DataAccessObject<PartyDto, PartyDtoExample>{
    protected final PartyDtoMapper mapper;
    protected final PartyDtoExample example = new PartyDtoExample();
          
    public APartyDao(PartyDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PartyDto select(String partyId){
        PartyDto record = new PartyDto();
		record.setPartyId(partyId);
        List<PartyDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyDto> select(List<PartyDto> records){
        List<PartyDto> list = new ArrayList<PartyDto>();
        for(PartyDto record : records){
            List<PartyDto> newRecords = null;
            synchronized(example) {
                PartyDtoExample.Criteria c = cleanExample().or();
                			c.andPartyIdEqualTo(record.getPartyId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyDto> selectAll(){
        synchronized(example) {
            List<PartyDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PartyDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<PartyDto> selectByExample(PartyDtoExample example) {
        List<PartyDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(PartyDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<PartyDto> records) {
        for(PartyDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PartyDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<PartyDto> records){
        for(PartyDto record : records){
            synchronized(example) {
                PartyDtoExample.Criteria c = cleanExample().or();
				c.andPartyIdEqualTo(record.getPartyId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String partyId){
        PartyDto record = new PartyDto();
		record.setPartyId(partyId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PartyDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<PartyDto> records) {
        for(PartyDto record : records){
            synchronized(example) {
                PartyDtoExample.Criteria c = cleanExample().or();
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
    
    public PartyDtoExample example() {
        return new PartyDtoExample();
    }
  
    protected List<PartyDto> sortByCdate(List<PartyDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected PartyDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
