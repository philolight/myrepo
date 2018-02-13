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
import com.lge.sm.cr_data_store.mapper.PersonDtoMapper;
import com.lge.sm.cr_data_store.dto.PersonDto;
import com.lge.sm.cr_data_store.dto.PersonDtoExample;

abstract public class APersonDao implements DataAccessObject<PersonDto, PersonDtoExample>{
    protected final PersonDtoMapper mapper;
    protected final PersonDtoExample example = new PersonDtoExample();
          
    public APersonDao(PersonDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PersonDto select(String userId){
        PersonDto record = new PersonDto();
		record.setUserId(userId);
        List<PersonDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PersonDto> select(List<PersonDto> records){
        List<PersonDto> list = new ArrayList<PersonDto>();
        for(PersonDto record : records){
            List<PersonDto> newRecords = null;
            synchronized(example) {
                PersonDtoExample.Criteria c = cleanExample().or();
                			c.andUserIdEqualTo(record.getUserId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PersonDto> selectAll(){
        synchronized(example) {
            List<PersonDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PersonDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PersonDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PersonDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<PersonDto> selectByExample(PersonDtoExample example) {
        List<PersonDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(PersonDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<PersonDto> records) {
        for(PersonDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PersonDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<PersonDto> records){
        for(PersonDto record : records){
            synchronized(example) {
                PersonDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(String userId){
        PersonDto record = new PersonDto();
		record.setUserId(userId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PersonDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<PersonDto> records) {
        for(PersonDto record : records){
            synchronized(example) {
                PersonDtoExample.Criteria c = cleanExample().or();
				c.andUserIdEqualTo(record.getUserId());
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
    
    public PersonDtoExample example() {
        return new PersonDtoExample();
    }
  
    protected List<PersonDto> sortByCdate(List<PersonDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected PersonDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
}
