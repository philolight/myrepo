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
import com.lge.sm.cr_data_store.mapper.TcpDtoMapper;
import com.lge.sm.cr_data_store.dto.TcpDto;
import com.lge.sm.cr_data_store.dto.TcpDtoExample;

abstract public class ATcpDao implements DataAccessObject<TcpDto, TcpDtoExample>{
    protected final TcpDtoMapper mapper;
    protected final TcpDtoExample example = new TcpDtoExample();
          
    public ATcpDao(TcpDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public TcpDto select(Long tcpId){
        TcpDto record = new TcpDto();
		record.setTcpId(tcpId);
        List<TcpDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TcpDto> select(List<TcpDto> records){
        List<TcpDto> list = new ArrayList<TcpDto>();
        for(TcpDto record : records){
            List<TcpDto> newRecords = null;
            synchronized(example) {
                TcpDtoExample.Criteria c = cleanExample().or();
                			c.andTcpIdEqualTo(record.getTcpId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TcpDto> selectAll(){
        synchronized(example) {
            List<TcpDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TcpDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TcpDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<TcpDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<TcpDto> selectByExample(TcpDtoExample example) {
        List<TcpDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(TcpDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<TcpDto> records) {
        for(TcpDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(TcpDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<TcpDto> records){
        for(TcpDto record : records){
            synchronized(example) {
                TcpDtoExample.Criteria c = cleanExample().or();
				c.andTcpIdEqualTo(record.getTcpId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long tcpId){
        TcpDto record = new TcpDto();
		record.setTcpId(tcpId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(TcpDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<TcpDto> records) {
        for(TcpDto record : records){
            synchronized(example) {
                TcpDtoExample.Criteria c = cleanExample().or();
				c.andTcpIdEqualTo(record.getTcpId());
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
    
    public TcpDtoExample example() {
        return new TcpDtoExample();
    }
  
    protected List<TcpDto> sortByCdate(List<TcpDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected TcpDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<TcpDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("tcp_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getTcpId();
		return -1;
	}
}
