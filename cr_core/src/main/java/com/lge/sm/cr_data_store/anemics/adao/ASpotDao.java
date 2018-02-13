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
import com.lge.sm.cr_data_store.mapper.SpotDtoMapper;
import com.lge.sm.cr_data_store.dto.SpotDto;
import com.lge.sm.cr_data_store.dto.SpotDtoExample;

abstract public class ASpotDao implements DataAccessObject<SpotDto, SpotDtoExample>{
    protected final SpotDtoMapper mapper;
    protected final SpotDtoExample example = new SpotDtoExample();
          
    public ASpotDao(SpotDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SpotDto select(Long spotId){
        SpotDto record = new SpotDto();
		record.setSpotId(spotId);
        List<SpotDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpotDto> select(List<SpotDto> records){
        List<SpotDto> list = new ArrayList<SpotDto>();
        for(SpotDto record : records){
            List<SpotDto> newRecords = null;
            synchronized(example) {
                SpotDtoExample.Criteria c = cleanExample().or();
                			c.andSpotIdEqualTo(record.getSpotId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpotDto> selectAll(){
        synchronized(example) {
            List<SpotDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpotDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpotDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpotDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<SpotDto> selectByExample(SpotDtoExample example) {
        List<SpotDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(SpotDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<SpotDto> records) {
        for(SpotDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(SpotDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<SpotDto> records){
        for(SpotDto record : records){
            synchronized(example) {
                SpotDtoExample.Criteria c = cleanExample().or();
				c.andSpotIdEqualTo(record.getSpotId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long spotId){
        SpotDto record = new SpotDto();
		record.setSpotId(spotId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(SpotDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<SpotDto> records) {
        for(SpotDto record : records){
            synchronized(example) {
                SpotDtoExample.Criteria c = cleanExample().or();
				c.andSpotIdEqualTo(record.getSpotId());
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
    
    public SpotDtoExample example() {
        return new SpotDtoExample();
    }
  
    protected List<SpotDto> sortByCdate(List<SpotDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected SpotDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<SpotDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("spot_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getSpotId();
		return -1;
	}
}
