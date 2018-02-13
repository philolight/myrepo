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
import com.lge.sm.cr_data_store.mapper.NumericRangeDtoMapper;
import com.lge.sm.cr_data_store.dto.NumericRangeDto;
import com.lge.sm.cr_data_store.dto.NumericRangeDtoExample;

abstract public class ANumericRangeDao implements DataAccessObject<NumericRangeDto, NumericRangeDtoExample>{
    protected final NumericRangeDtoMapper mapper;
    protected final NumericRangeDtoExample example = new NumericRangeDtoExample();
          
    public ANumericRangeDao(NumericRangeDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public NumericRangeDto select(Long numericRangeId){
        NumericRangeDto record = new NumericRangeDto();
		record.setNumericRangeId(numericRangeId);
        List<NumericRangeDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<NumericRangeDto> select(List<NumericRangeDto> records){
        List<NumericRangeDto> list = new ArrayList<NumericRangeDto>();
        for(NumericRangeDto record : records){
            List<NumericRangeDto> newRecords = null;
            synchronized(example) {
                NumericRangeDtoExample.Criteria c = cleanExample().or();
                			c.andNumericRangeIdEqualTo(record.getNumericRangeId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<NumericRangeDto> selectAll(){
        synchronized(example) {
            List<NumericRangeDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<NumericRangeDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<NumericRangeDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<NumericRangeDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<NumericRangeDto> selectByExample(NumericRangeDtoExample example) {
        List<NumericRangeDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(NumericRangeDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<NumericRangeDto> records) {
        for(NumericRangeDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(NumericRangeDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<NumericRangeDto> records){
        for(NumericRangeDto record : records){
            synchronized(example) {
                NumericRangeDtoExample.Criteria c = cleanExample().or();
				c.andNumericRangeIdEqualTo(record.getNumericRangeId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long numericRangeId){
        NumericRangeDto record = new NumericRangeDto();
		record.setNumericRangeId(numericRangeId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(NumericRangeDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<NumericRangeDto> records) {
        for(NumericRangeDto record : records){
            synchronized(example) {
                NumericRangeDtoExample.Criteria c = cleanExample().or();
				c.andNumericRangeIdEqualTo(record.getNumericRangeId());
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
    
    public NumericRangeDtoExample example() {
        return new NumericRangeDtoExample();
    }
  
    protected List<NumericRangeDto> sortByCdate(List<NumericRangeDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected NumericRangeDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<NumericRangeDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("numeric_range_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getNumericRangeId();
		return -1;
	}
}
