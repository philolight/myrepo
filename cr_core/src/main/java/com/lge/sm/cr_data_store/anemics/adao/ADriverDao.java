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
import com.lge.sm.cr_data_store.mapper.DriverDtoMapper;
import com.lge.sm.cr_data_store.dto.DriverDto;
import com.lge.sm.cr_data_store.dto.DriverDtoExample;

abstract public class ADriverDao implements DataAccessObject<DriverDto, DriverDtoExample>{
    protected final DriverDtoMapper mapper;
    protected final DriverDtoExample example = new DriverDtoExample();
          
    public ADriverDao(DriverDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public DriverDto select(Long driverId){
        DriverDto record = new DriverDto();
		record.setDriverId(driverId);
        List<DriverDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverDto> select(List<DriverDto> records){
        List<DriverDto> list = new ArrayList<DriverDto>();
        for(DriverDto record : records){
            List<DriverDto> newRecords = null;
            synchronized(example) {
                DriverDtoExample.Criteria c = cleanExample().or();
                			c.andDriverIdEqualTo(record.getDriverId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverDto> selectAll(){
        synchronized(example) {
            List<DriverDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<DriverDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<DriverDto> selectByExample(DriverDtoExample example) {
        List<DriverDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(DriverDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<DriverDto> records) {
        for(DriverDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(DriverDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<DriverDto> records){
        for(DriverDto record : records){
            synchronized(example) {
                DriverDtoExample.Criteria c = cleanExample().or();
				c.andDriverIdEqualTo(record.getDriverId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long driverId){
        DriverDto record = new DriverDto();
		record.setDriverId(driverId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(DriverDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<DriverDto> records) {
        for(DriverDto record : records){
            synchronized(example) {
                DriverDtoExample.Criteria c = cleanExample().or();
				c.andDriverIdEqualTo(record.getDriverId());
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
    
    public DriverDtoExample example() {
        return new DriverDtoExample();
    }
  
    protected List<DriverDto> sortByCdate(List<DriverDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected DriverDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<DriverDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("driver_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getDriverId();
		return -1;
	}
}
