package com.lge.sm.cr_data_store.transaction;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.dao.UserDao;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.UserDto;

@Transactional
public class TxTestDao {
	private LocationDao locationDao;
	private UserDao userDao;
	public TxTestDao(LocationDao locationDao, UserDao userDao) {
		this.locationDao = locationDao;
		this.userDao = userDao;
	}
	
	public void noTransactionSuccess(LocationDto location, UserDto room) {
		locationDao.insert(location);
		userDao.insert(room);
	}
	
	public void noTransactionHasException(LocationDto location, UserDto room) {
		locationDao.insert(location);
		Integer a = 0 / 0; // exception
		userDao.insert(room);
	}
		
	public void transactionFail(LocationDto location, UserDto room) {
		locationDao.insert(location);
		@SuppressWarnings("unused")
		Integer a = 0 / 0; // exception
		userDao.insert(room);
	}
	
	public boolean transactionFailReturnBoolean(LocationDto location, UserDto room) {
		locationDao.insert(location);
		@SuppressWarnings("unused")
		Integer a = 0 / 0; // exception
		userDao.insert(room);
		return true;
	}
	
	public boolean transactionSuccessReturnBoolean(LocationDto location, UserDto room) {
		locationDao.insert(location);
		userDao.insert(room);
		return true;
	}
	
	public Object transactionFailReturnObject(LocationDto location, UserDto room) {
		locationDao.insert(location);
		@SuppressWarnings("unused")
		Integer a = 0 / 0; // exception
		userDao.insert(room);
		return new Object();
	}
	
	public List<LocationDto> getLocations(){
		return locationDao.selectAll();
	}
	
	public List<UserDto> getUsers(){
		return userDao.selectAll();
	}
	
	public void deleteAll() {
		locationDao.deleteAll();
		userDao.deleteAll();
	}
}
