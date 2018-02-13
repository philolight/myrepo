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
import com.lge.sm.cr_data_store.mapper.StringRangeDtoMapper;
import com.lge.sm.cr_data_store.dto.StringRangeDto;
import com.lge.sm.cr_data_store.dto.StringRangeDtoExample;

abstract public class AStringRangeDao implements DataAccessObject<StringRangeDto, StringRangeDtoExample>{
    protected final StringRangeDtoMapper mapper;
    protected final StringRangeDtoExample example = new StringRangeDtoExample();
          
    public AStringRangeDao(StringRangeDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public StringRangeDto select(Long stringRangeId){
        StringRangeDto record = new StringRangeDto();
		record.setStringRangeId(stringRangeId);
        List<StringRangeDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StringRangeDto> select(List<StringRangeDto> records){
        List<StringRangeDto> list = new ArrayList<StringRangeDto>();
        for(StringRangeDto record : records){
            List<StringRangeDto> newRecords = null;
            synchronized(example) {
                StringRangeDtoExample.Criteria c = cleanExample().or();
                			c.andStringRangeIdEqualTo(record.getStringRangeId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StringRangeDto> selectAll(){
        synchronized(example) {
            List<StringRangeDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StringRangeDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StringRangeDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<StringRangeDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<StringRangeDto> selectByExample(StringRangeDtoExample example) {
        List<StringRangeDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(StringRangeDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<StringRangeDto> records) {
        for(StringRangeDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(StringRangeDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<StringRangeDto> records){
        for(StringRangeDto record : records){
            synchronized(example) {
                StringRangeDtoExample.Criteria c = cleanExample().or();
				c.andStringRangeIdEqualTo(record.getStringRangeId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long stringRangeId){
        StringRangeDto record = new StringRangeDto();
		record.setStringRangeId(stringRangeId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(StringRangeDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<StringRangeDto> records) {
        for(StringRangeDto record : records){
            synchronized(example) {
                StringRangeDtoExample.Criteria c = cleanExample().or();
				c.andStringRangeIdEqualTo(record.getStringRangeId());
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
    
    public StringRangeDtoExample example() {
        return new StringRangeDtoExample();
    }
  
    protected List<StringRangeDto> sortByCdate(List<StringRangeDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected StringRangeDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<StringRangeDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("string_range_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getStringRangeId();
		return -1;
	}
}
