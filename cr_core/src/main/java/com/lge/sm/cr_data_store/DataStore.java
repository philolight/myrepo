/**
 * Copyright (C) 2017 LG Electronics Inc. All Rights Reserved.
 * Though every care has been taken to ensure the accuracy of this document,
 * LG Electronics Inc. cannot accept responsibility for any errors or
 * omissions or for any loss occurred to any person, whether legal or natural,
 * from acting, or refraining from action, as a result of the information
 * contained herein. Information in this document is subject to change at any
 * time without obligation to notify any person of such changes.
 * LG Electronics Inc. may have patents or patent pending applications,
 * trademarks copyrights or other intellectual property rights covering subject
 * matter in this document. The furnishing of this document does not give the
 * recipient or reader any license to these patents, trademarks copyrights or
 * other intellectual property rights.
 * No part of this document may be communicated, distributed, reproduced or
 * transmitted in any form or by any means, electronic or mechanical or
 * otherwise, for any purpose, without the prior written permission of
 * LG Electronics Inc.
 * The document is subject to revision without further notice.
 * All brand names and product names mentioned in this document are trademarks
 * or registered trademarks of their respective owners
 *
 * Author: eungsuk.shon
 */

package com.lge.sm.cr_data_store;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.dao.CancelHistoryDao;
import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.dao.RoomDao;
import com.lge.sm.cr_data_store.dao.RoomSensorDao;
import com.lge.sm.cr_data_store.dao.ScheduleDao;
import com.lge.sm.cr_data_store.dao.SensorDao;
import com.lge.sm.cr_data_store.dao.SlmDao;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.RoomDtoExample;
import com.lge.sm.cr_data_store.dto.RoomSensorDto;
import com.lge.sm.cr_data_store.dto.RoomSensorDtoExample;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

public class DataStore {
  private static final String TAG = DataStore.class.getSimpleName();

	private static final DataStore instance = new DataStore();
	private IChangedDataListener<RoomDto> roomListener = null;
	private IChangedDataListener<SlmDto> slmListener = null;
	
	private LocationDao 	locationDao;
	private RoomDao 		roomDao;
	private RoomSensorDao 	roomSensorDao;
	private ScheduleDao 	scheduleDao;
	private SensorDao 		sensorDao;
	private SlmDao 			slmDao;
	private CancelHistoryDao 	cancelHistoryDao;
	
	public void setLocationDao		(LocationDao locationDao) 		{ this.locationDao = locationDao; }
	public void setRoomDao			(RoomDao roomDao) 				{ this.roomDao = roomDao; }
	public void setRoomSensorDao	(RoomSensorDao roomSensorDao) 	{ this.roomSensorDao = roomSensorDao; }
	public void setScheduleDao		(ScheduleDao scheduleDao) 		{ this.scheduleDao = scheduleDao; }
	public void setSensorDao		(SensorDao sensorDao) 			{ this.sensorDao = sensorDao; }
	public void setSlmDao			(SlmDao slmDao) 				{ this.slmDao = slmDao; }
	public void setDailyReportDao	(CancelHistoryDao dailyReportDao) { this.cancelHistoryDao = dailyReportDao; }

  public static DataStore getInstance() {
    return instance;
  }

  public boolean start() {
    boolean ret = false;

    ret = true;

    return ret;
  }

  public void stop() {

  }

  public synchronized void addChangedDataListenerToRoom(IChangedDataListener listener) {
    roomListener = listener;
  }

  public synchronized void removeChangedDataListenerFromRoom(IChangedDataListener listener) {
    roomListener = null;
  }

  public synchronized void addChangedDataListenerToSlm(IChangedDataListener listener) {
    slmListener = listener;
  }

  public synchronized void removeChangedDataListenerFromSlm(IChangedDataListener listener) {
    slmListener = null;
  }

  public synchronized List<RoomDto> getRoomList() {
    return roomDao.selectAll();
  }

  public synchronized List<RoomDto> getRoomList(String locationId) {
    RoomDtoExample example = roomDao.example();
    example.or().andLocationIdEqualTo(locationId).andEnableEqualTo(1);
    return roomDao.selectByExample(example);
  }

  public synchronized List<LocationDto> getLocationList() {
    return locationDao.selectAll();
  }

  public synchronized List<LocationDto> getLocationList(List<RoomDto> roomList) {
    List<LocationDto> query = new ArrayList<>();
    for (RoomDto each : roomList) {
      LocationDto loc = new LocationDto();
      loc.setLocationId(each.getLocationId());
      query.add(loc);
    }

    List<LocationDto> ret = locationDao.select(query);

    if (query.size() != ret.size()) {
      Logger.error(TAG, "Could not found room's location");
      return null;
    }

    return ret;
  }

  public synchronized RoomDto getRoom(String locationId, String roomId) {
    RoomDtoExample example = roomDao.example();
    example.or().andLocationIdEqualTo(locationId).andRoomIdEqualTo(roomId);
    List<RoomDto> list = roomDao.selectByExample(example);
    return (list == null || list.size() == 0) ? null : list.get(0);
  } 

  public synchronized List<SlmDto> getSlmList() {
    return slmDao.selectAll();
  }

  public synchronized SlmDto getSlm(String slmId) {
    return slmDao.select(slmId);
  }

  public synchronized List<SensorDto> getSensorList(String locationId, String roomId) {
    RoomSensorDtoExample example = roomSensorDao.example();
    example.or().andLocationIdEqualTo(locationId).andRoomIdEqualTo(roomId).andStatusEqualTo(1);
    List<RoomSensorDto> rsList = roomSensorDao.selectByExample(example);

    Set<String> sensorIdSet = new HashSet<>();

    for (RoomSensorDto each : rsList) {
      sensorIdSet.add(each.getSensorId());
    }

    List<SensorDto> query = new ArrayList<>();
    for (String each : sensorIdSet) {
      SensorDto sensor = new SensorDto();
      Factory.setDefaultFields(sensor);
      sensor.setSensorId(each);
      query.add(sensor);
    }

    List<SensorDto> ret = sensorDao.select(query);

    return ret;
  }

  public synchronized boolean addScheduleResult(ScheduleDto schedule) {
    return scheduleDao.insert(schedule);
  }

  public synchronized boolean isExistScheduleResult(ScheduleDto schedule) {
    return scheduleDao.select(schedule.getScheduleId()) != null;
  }

  public synchronized boolean isExistScheduleResult(String scheduleId) {
    return scheduleDao.select(scheduleId) != null;
  }

  public synchronized ScheduleDto getSchedule(String scheduleId) {
    return scheduleDao.select(scheduleId);
  }

//  private boolean isPackageMode = false;
//  private File targetDir = null;
//  private SqlSessionFactory sqlSessionFactory = null;
//
//  private DataStore() {
//    initialize();
//  }
//
//  private void initialize() {
//    checkEnvironments();
//    copyFiles();
//  }

//  private void checkEnvironments() {
//    isPackageMode = Utils.isClassInPackageFile(DataStore.class);
//    targetDir = new File(Utils.getDirPathOfClass(DataStore.class) + (isPackageMode ? "" : "../"));
//  }

//  private void copyFiles() {
//    try {
//      checkAndCopy(FILE_NAME_CONFIG);
//      checkAndCopy(FILE_NAME_QUERY);
//
//    } catch (FileNotFoundException fnfe) {
//      Logger.error(TAG, fnfe.getMessage());
//      System.exit(0);
//    }
//  }
//
//  enum COPY_RESULT {
//    UNDEFINED, FILE_EXISTED, FILE_OVERWRITED, FILE_COPIED,
//  };
//
//  private COPY_RESULT checkAndCopy(String fileName) throws FileNotFoundException {
//    COPY_RESULT ret = COPY_RESULT.UNDEFINED;
//    File targetFile = new File(targetDir, fileName);
//
//    if (targetFile.exists()) {
//      // TODO: compare the size of file and if it is updated, copy it again.
//      ret = COPY_RESULT.FILE_EXISTED;
//    } else {
//      // no target file
//      URL defaultUrl = this.getClass().getClassLoader().getResource(fileName);
//      if (defaultUrl != null) {
//        InputStream in = null;
//        OutputStream out = null;
//        try {
//          in = defaultUrl.openStream();
//          out = new FileOutputStream(targetFile);
//          copy(in, out);
//          ret = COPY_RESULT.FILE_COPIED;
//        } catch (IOException e) {
//        } finally {
//          Utils.closeOutputStream(out);
//          Utils.closeInputStream(in);
//        }
//      } else {
//        throw new FileNotFoundException("no file " + fileName);
//      }
//    }
//    Logger.debug(TAG, fileName + " " + ret.toString());
//    return ret;
//  }
//
//  private boolean copy(InputStream in, OutputStream out) throws NullPointerException {
//    boolean ret = false;
//
//    if (in != null && out != null) {
//      byte[] buffer = new byte[4096];
//      int size = 0;
//      try {
//        while ((size = in.read(buffer)) > 0) {
//          out.write(buffer, 0, size);
//        }
//        out.flush();
//      } catch (IOException ioe) {
//        Logger.error(TAG, ioe.getMessage());
//      }
//    } else {
//      throw new NullPointerException();
//    }
//    return ret;
//  }
}
