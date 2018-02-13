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
import com.lge.sm.cr_data_store.mapper.PointAttributeDtoMapper;
import com.lge.sm.cr_data_store.dto.PointAttributeDto;
import com.lge.sm.cr_data_store.dto.PointAttributeDtoExample;

abstract public class APointAttributeDao implements DataAccessObject<PointAttributeDto, PointAttributeDtoExample>{
    protected final PointAttributeDtoMapper mapper;
    protected final PointAttributeDtoExample example = new PointAttributeDtoExample();
          
    public APointAttributeDao(PointAttributeDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public PointAttributeDto select(Long pointAttributeId){
        PointAttributeDto record = new PointAttributeDto();
		record.setPointAttributeId(pointAttributeId);
        List<PointAttributeDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PointAttributeDto> select(List<PointAttributeDto> records){
        List<PointAttributeDto> list = new ArrayList<PointAttributeDto>();
        for(PointAttributeDto record : records){
            List<PointAttributeDto> newRecords = null;
            synchronized(example) {
                PointAttributeDtoExample.Criteria c = cleanExample().or();
                			c.andPointAttributeIdEqualTo(record.getPointAttributeId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PointAttributeDto> selectAll(){
        synchronized(example) {
            List<PointAttributeDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PointAttributeDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PointAttributeDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<PointAttributeDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<PointAttributeDto> selectByExample(PointAttributeDtoExample example) {
        List<PointAttributeDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(PointAttributeDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<PointAttributeDto> records) {
        for(PointAttributeDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(PointAttributeDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<PointAttributeDto> records){
        for(PointAttributeDto record : records){
            synchronized(example) {
                PointAttributeDtoExample.Criteria c = cleanExample().or();
				c.andPointAttributeIdEqualTo(record.getPointAttributeId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long pointAttributeId){
        PointAttributeDto record = new PointAttributeDto();
		record.setPointAttributeId(pointAttributeId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(PointAttributeDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<PointAttributeDto> records) {
        for(PointAttributeDto record : records){
            synchronized(example) {
                PointAttributeDtoExample.Criteria c = cleanExample().or();
				c.andPointAttributeIdEqualTo(record.getPointAttributeId());
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
    
    public PointAttributeDtoExample example() {
        return new PointAttributeDtoExample();
    }
  
    protected List<PointAttributeDto> sortByCdate(List<PointAttributeDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected PointAttributeDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<PointAttributeDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("point_attribute_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getPointAttributeId();
		return -1;
	}
}
