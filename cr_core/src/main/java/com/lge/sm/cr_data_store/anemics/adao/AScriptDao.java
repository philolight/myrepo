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
import com.lge.sm.cr_data_store.mapper.ScriptDtoMapper;
import com.lge.sm.cr_data_store.dto.ScriptDto;
import com.lge.sm.cr_data_store.dto.ScriptDtoExample;

abstract public class AScriptDao implements DataAccessObject<ScriptDto, ScriptDtoExample>{
    protected final ScriptDtoMapper mapper;
    protected final ScriptDtoExample example = new ScriptDtoExample();
          
    public AScriptDao(ScriptDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public ScriptDto select(Long scriptId){
        ScriptDto record = new ScriptDto();
		record.setScriptId(scriptId);
        List<ScriptDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScriptDto> select(List<ScriptDto> records){
        List<ScriptDto> list = new ArrayList<ScriptDto>();
        for(ScriptDto record : records){
            List<ScriptDto> newRecords = null;
            synchronized(example) {
                ScriptDtoExample.Criteria c = cleanExample().or();
                			c.andScriptIdEqualTo(record.getScriptId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScriptDto> selectAll(){
        synchronized(example) {
            List<ScriptDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScriptDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScriptDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<ScriptDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<ScriptDto> selectByExample(ScriptDtoExample example) {
        List<ScriptDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(ScriptDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<ScriptDto> records) {
        for(ScriptDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(ScriptDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<ScriptDto> records){
        for(ScriptDto record : records){
            synchronized(example) {
                ScriptDtoExample.Criteria c = cleanExample().or();
				c.andScriptIdEqualTo(record.getScriptId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long scriptId){
        ScriptDto record = new ScriptDto();
		record.setScriptId(scriptId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(ScriptDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<ScriptDto> records) {
        for(ScriptDto record : records){
            synchronized(example) {
                ScriptDtoExample.Criteria c = cleanExample().or();
				c.andScriptIdEqualTo(record.getScriptId());
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
    
    public ScriptDtoExample example() {
        return new ScriptDtoExample();
    }
  
    protected List<ScriptDto> sortByCdate(List<ScriptDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected ScriptDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<ScriptDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("script_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getScriptId();
		return -1;
	}
}
