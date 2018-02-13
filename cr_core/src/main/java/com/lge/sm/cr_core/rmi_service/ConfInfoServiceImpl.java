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

package com.lge.sm.cr_core.rmi_service;

import java.util.ArrayList;
import java.util.List;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_interface.rmi_service.inf.ConfInfoService;
import com.lge.sm.cr_interface.rmi_service.model.CommonLocation;
import com.lge.sm.cr_interface.rmi_service.model.CommonRoom;
import com.lge.sm.cr_interface.rmi_service.model.CommonSchedule;
import com.lge.sm.cr_interface.rmi_service.model.CommonSensor;
import com.lge.sm.cr_interface.rmi_service.model.CommonSlm;

public class ConfInfoServiceImpl implements ConfInfoService {
  private static final String TAG = ConfInfoServiceImpl.class.getName();    
  
  @Override
  public List<CommonRoom> getRoomList() {
    List<CommonRoom> ret = new ArrayList<>();
    List<RoomDto> roomList = DataStore.getInstance().getRoomList();
    if (roomList != null) {
      for (RoomDto room : roomList) {
        ret.add(Convertor.convertCommonRoom(room));
      }
    } else {
      Logger.error(TAG, "getRoomList, roomList is null");
      return null;
    }
    return ret;
  }

  @Override
  public CommonRoom getRoom(String locationId, String roomId) {
    CommonRoom ret = null;
    if (locationId != null && roomId != null) {
      ret = Convertor.convertCommonRoom(DataStore.getInstance().getRoom(locationId, roomId));
    } else {
      Logger.error(TAG, "getRoom, input args are null.");
    }
    return ret;
  }

  @Override
  public List<CommonLocation> getLocationList(List<CommonRoom> roomList) {
    List<CommonLocation> ret = null;
    if (roomList != null) {
      List<RoomDto> originRoomList = new ArrayList<>();
      for (CommonRoom commonRoom : roomList) {
        originRoomList.add(Convertor.convertRoom(commonRoom));
      } 
      
      List<LocationDto> locationList = DataStore.getInstance().getLocationList(originRoomList);
      if (locationList != null) {
        ret = new ArrayList<>();
        for (LocationDto location : locationList) {
          ret.add(Convertor.convertCommonLocation(location));
        }
      } else {
        Logger.error(TAG, "getLocationList, location list is null.");
      }
    } else {
      Logger.error(TAG, "getLocationList, input args are null.");
    }
    return ret;
  }

  @Override
  public List<CommonSlm> getSlmList() {
    List<CommonSlm> ret = null;    
    List<SlmDto> slmList = DataStore.getInstance().getSlmList();
    if (slmList != null) {
      ret = new ArrayList<>();
      for (SlmDto slm : slmList) {
        ret.add(Convertor.convertCommonSlm(slm));
      }
    } else {
      Logger.error(TAG, "getSlmList, slm list is null.");
    }
    return ret;
  }

  @Override
  public CommonSlm getSlm(String slmId) {
    CommonSlm ret = null;
    if (slmId != null) {
      SlmDto slm = DataStore.getInstance().getSlm(slmId);      
      ret = (slm != null) ? Convertor.convertCommonSlm(slm) : null;
    } else {
      Logger.error(TAG, "getSlm, input args are null.");
    }
    return ret;
  }

  @Override
  public List<CommonSensor> getSensorList(String locationId, String roomId) {
    List<CommonSensor> ret = null;
    if (locationId != null && roomId != null) {
      List<SensorDto> sensorList = DataStore.getInstance().getSensorList(locationId, roomId);
      if (sensorList != null) {
        ret = new ArrayList<>();
        for (SensorDto sensor : sensorList) {
          ret.add(Convertor.convertCommonSensor(sensor));
        }
      } else {
        Logger.error(TAG, "getSensorList, sensorList is null.");
      }
    } else {
      Logger.error(TAG, "getSensorList, input args are null.");
    }
    return ret;
  }
  
  @Override
  public CommonSchedule getSchedule(String scheduleId) {
    CommonSchedule ret = null;
    if (scheduleId != null) {
      ScheduleDto sche = DataStore.getInstance().getSchedule(scheduleId);
      if (sche != null) {
        ret = Convertor.convertCommonSchedule(sche);
      } else {
        Logger.error(TAG, "getSchedule, schedule is null.");
      }
    } else {
      Logger.error(TAG, "getSchedule, input args are null.");
    }
    return null;
  }

}
