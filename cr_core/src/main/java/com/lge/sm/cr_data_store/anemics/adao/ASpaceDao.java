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
import com.lge.sm.cr_data_store.mapper.SpaceDtoMapper;
import com.lge.sm.cr_data_store.dto.SpaceDto;
import com.lge.sm.cr_data_store.dto.SpaceDtoExample;

abstract public class ASpaceDao implements DataAccessObject<SpaceDto, SpaceDtoExample>{
    protected final SpaceDtoMapper mapper;
    protected final SpaceDtoExample example = new SpaceDtoExample();
          
    public ASpaceDao(SpaceDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public SpaceDto select(Long spaceId){
        SpaceDto record = new SpaceDto();
		record.setSpaceId(spaceId);
        List<SpaceDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpaceDto> select(List<SpaceDto> records){
        List<SpaceDto> list = new ArrayList<SpaceDto>();
        for(SpaceDto record : records){
            List<SpaceDto> newRecords = null;
            synchronized(example) {
                SpaceDtoExample.Criteria c = cleanExample().or();
                			c.andSpaceIdEqualTo(record.getSpaceId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpaceDto> selectAll(){
        synchronized(example) {
            List<SpaceDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpaceDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpaceDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<SpaceDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<SpaceDto> selectByExample(SpaceDtoExample example) {
        List<SpaceDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(SpaceDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<SpaceDto> records) {
        for(SpaceDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(SpaceDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<SpaceDto> records){
        for(SpaceDto record : records){
            synchronized(example) {
                SpaceDtoExample.Criteria c = cleanExample().or();
				c.andSpaceIdEqualTo(record.getSpaceId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long spaceId){
        SpaceDto record = new SpaceDto();
		record.setSpaceId(spaceId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(SpaceDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<SpaceDto> records) {
        for(SpaceDto record : records){
            synchronized(example) {
                SpaceDtoExample.Criteria c = cleanExample().or();
				c.andSpaceIdEqualTo(record.getSpaceId());
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
    
    public SpaceDtoExample example() {
        return new SpaceDtoExample();
    }
  
    protected List<SpaceDto> sortByCdate(List<SpaceDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected SpaceDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<SpaceDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("space_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getSpaceId();
		return -1;
	}
}
