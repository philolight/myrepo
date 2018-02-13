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
import com.lge.sm.cr_data_store.mapper.DecimalRangeDtoMapper;
import com.lge.sm.cr_data_store.dto.DecimalRangeDto;
import com.lge.sm.cr_data_store.dto.DecimalRangeDtoExample;

abstract public class ADecimalRangeDao implements DataAccessObject<DecimalRangeDto, DecimalRangeDtoExample>{
    protected final DecimalRangeDtoMapper mapper;
    protected final DecimalRangeDtoExample example = new DecimalRangeDtoExample();
          
    public ADecimalRangeDao(DecimalRangeDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public DecimalRangeDto select(Long decimalRangeId){
        DecimalRangeDto record = new DecimalRangeDto();
		record.setDecimalRangeId(decimalRangeId);
        List<DecimalRangeDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DecimalRangeDto> select(List<DecimalRangeDto> records){
        List<DecimalRangeDto> list = new ArrayList<DecimalRangeDto>();
        for(DecimalRangeDto record : records){
            List<DecimalRangeDto> newRecords = null;
            synchronized(example) {
                DecimalRangeDtoExample.Criteria c = cleanExample().or();
                			c.andDecimalRangeIdEqualTo(record.getDecimalRangeId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DecimalRangeDto> selectAll(){
        synchronized(example) {
            List<DecimalRangeDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DecimalRangeDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DecimalRangeDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DecimalRangeDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<DecimalRangeDto> selectByExample(DecimalRangeDtoExample example) {
        List<DecimalRangeDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(DecimalRangeDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<DecimalRangeDto> records) {
        for(DecimalRangeDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(DecimalRangeDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<DecimalRangeDto> records){
        for(DecimalRangeDto record : records){
            synchronized(example) {
                DecimalRangeDtoExample.Criteria c = cleanExample().or();
				c.andDecimalRangeIdEqualTo(record.getDecimalRangeId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long decimalRangeId){
        DecimalRangeDto record = new DecimalRangeDto();
		record.setDecimalRangeId(decimalRangeId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(DecimalRangeDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<DecimalRangeDto> records) {
        for(DecimalRangeDto record : records){
            synchronized(example) {
                DecimalRangeDtoExample.Criteria c = cleanExample().or();
				c.andDecimalRangeIdEqualTo(record.getDecimalRangeId());
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
    
    public DecimalRangeDtoExample example() {
        return new DecimalRangeDtoExample();
    }
  
    protected List<DecimalRangeDto> sortByCdate(List<DecimalRangeDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected DecimalRangeDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<DecimalRangeDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("decimal_range_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getDecimalRangeId();
		return -1;
	}
}
