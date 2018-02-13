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
import com.lge.sm.cr_data_store.mapper.RoomSensorDtoMapper;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDtoExample;

abstract public class ARoomSensorDao implements DataAccessObject<RoomSensorDto, RoomSensorDtoExample>{
    protected final RoomSensorDtoMapper mapper;
    protected final RoomSensorDtoExample example = new RoomSensorDtoExample();
          
    public ARoomSensorDao(RoomSensorDtoMapper mapper) {
        this.mapper = mapper;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public RoomSensorDto select(Long roomSensorId){
        RoomSensorDto record = new RoomSensorDto();
		record.setRoomSensorId(roomSensorId);
        List<RoomSensorDto> list = select(Arrays.asList(record));
        return list.size() == 1 ? list.get(0) : null;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<RoomSensorDto> select(List<RoomSensorDto> records){
        List<RoomSensorDto> list = new ArrayList<RoomSensorDto>();
        for(RoomSensorDto record : records){
            List<RoomSensorDto> newRecords = null;
            synchronized(example) {
                RoomSensorDtoExample.Criteria c = cleanExample().or();
                			c.andRoomSensorIdEqualTo(record.getRoomSensorId());
                newRecords = mapper.selectByExample(example);
            }
            if(newRecords != null) list.addAll(newRecords);
        }
            
        return list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<RoomSensorDto> selectAll(){
        synchronized(example) {
            List<RoomSensorDto> list = mapper.selectByExample(cleanExample());
            return (list == null) ? Collections.emptyList() : list;
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<RoomSensorDto> selectBetween(String cdateFrom, String cdateTo){
        synchronized(example) {
            example.or().andCdateBetween(cdateFrom, cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<RoomSensorDto> selectFrom(String cdateFrom){
        synchronized(example) {
            example.or().andCdateGreaterThanOrEqualTo(cdateFrom);     
            return sortByCdate(selectByExample(example));
        }
    }
  
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<RoomSensorDto> selectTo(String cdateTo){
        synchronized(example) {
            cleanExample().or().andCdateLessThanOrEqualTo(cdateTo);     
            return sortByCdate(selectByExample(example));
        }
    }
    
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    @Override
    public List<RoomSensorDto> selectByExample(RoomSensorDtoExample example) {
        List<RoomSensorDto> list = mapper.selectByExample(example);
        return (list == null) ? Collections.emptyList() : list;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(RoomSensorDto record) {
        return insert(Arrays.asList(record));
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean insert(List<RoomSensorDto> records) {
        for(RoomSensorDto record : records){
            mapper.insert(record);
        }     
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(RoomSensorDto record){
        return update(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean update(List<RoomSensorDto> records){
        for(RoomSensorDto record : records){
            synchronized(example) {
                RoomSensorDtoExample.Criteria c = cleanExample().or();
				c.andRoomSensorIdEqualTo(record.getRoomSensorId());
                mapper.updateByExample(record, example);
            }
        }
        return true;
    }
  
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(Long roomSensorId){
        RoomSensorDto record = new RoomSensorDto();
		record.setRoomSensorId(roomSensorId);
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(RoomSensorDto record) {
        return delete(Arrays.asList(record));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean delete(List<RoomSensorDto> records) {
        for(RoomSensorDto record : records){
            synchronized(example) {
                RoomSensorDtoExample.Criteria c = cleanExample().or();
				c.andRoomSensorIdEqualTo(record.getRoomSensorId());
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
    
    public RoomSensorDtoExample example() {
        return new RoomSensorDtoExample();
    }
  
    protected List<RoomSensorDto> sortByCdate(List<RoomSensorDto> list){
        if(list == null) return Collections.emptyList();
        Collections.sort(list, (a, b) -> {
            return a.getCdate().compareTo(b.getCdate());
        });
    
        return list;
    }
  
    protected RoomSensorDtoExample cleanExample() {
        example.clear();
        return example;
    }
    
    
	public long getLastId() throws IllegalAccessError{
		List<RoomSensorDto> dtos = null;
			synchronized(example) {
			cleanExample().setOrderByClause("room_sensor_id desc limit 1");
			example.or();
			dtos = mapper.selectByExample(example);
		}
		if(dtos == null) throw new IllegalAccessError("database error");
		if(dtos.size() == 1) return dtos.get(0).getRoomSensorId();
		return -1;
	}
}
