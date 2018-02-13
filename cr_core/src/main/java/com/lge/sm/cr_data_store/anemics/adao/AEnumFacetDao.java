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
import com.lge.sm.cr_data_store.mapper.EnumFacetDtoMapper;
import com.lge.sm.cr_data_store.dto.EnumFacetDto;
import com.lge.sm.cr_data_store.dto.EnumFacetDtoExample;

abstract public class AEnumFacetDao implements DataAccessObject<EnumFacetDto, EnumFacetDtoExample>{
    protected final EnumFacetDtoMapper mapper;
    protected final EnumFacetDtoExample example = new EnumFacetDtoExample();
          
    public AEnumFacetDao(EnumFacetDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public EnumFacetDto select(Long enumFacetId){
        EnumFacetDto record = new EnumFacetDto();
		record.setEnumFacetId(enumFacetId);
        List<EnumFacetDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EnumFacetDto> select(List<EnumFacetDto> records){
        List<EnumFacetDto> list = new ArrayList<EnumFacetDto>();
        for(EnumFacetDto record : records){
            List<EnumFacetDto> newRecords = null;
            synchronized(example) {
                EnumFacetDtoExample.Criteria c = cleanExample().or();
                			c.andEnumFacetIdEqualTo(record.getEnumFacetId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EnumFacetDto> selectAll(){
        synchronized(example) {
            List<EnumFacetDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EnumFacetDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EnumFacetDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<EnumFacetDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<EnumFacetDto> selectByExample(EnumFacetDtoExample example) {
        List<EnumFacetDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(EnumFacetDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<EnumFacetDto> records) {
        for(EnumFacetDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(EnumFacetDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<EnumFacetDto> records){
        for(EnumFacetDto record : records){
            synchronized(example) {
                EnumFacetDtoExample.Criteria c = cleanExample().or();
				c.andEnumFacetIdEqualTo(record.getEnumFacetId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long enumFacetId){
        EnumFacetDto record = new EnumFacetDto();
		record.setEnumFacetId(enumFacetId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(EnumFacetDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<EnumFacetDto> records) {
        for(EnumFacetDto record : records){
            synchronized(example) {
                EnumFacetDtoExample.Criteria c = cleanExample().or();
				c.andEnumFacetIdEqualTo(record.getEnumFacetId());
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
    
    public EnumFacetDtoExample example() {
        return new EnumFacetDtoExample();
    }
  
    protected List<EnumFacetDto> sortByCdate(List<EnumFacetDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected EnumFacetDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<EnumFacetDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("enum_facet_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getEnumFacetId();
		return -1;
	}
}
