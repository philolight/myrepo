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

import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.lge.sm.cr_interface.rmi_service.model.CommonLocation;
import com.lge.sm.cr_interface.rmi_service.model.CommonRoom;
import com.lge.sm.cr_interface.rmi_service.model.CommonSchedule;
import com.lge.sm.cr_interface.rmi_service.model.CommonSensor;
import com.lge.sm.cr_interface.rmi_service.model.CommonSlm;

public class Convertor {
  public static CommonLocation convertCommonLocation(LocationDto location) {
    return (location != null)
        ? new CommonLocation(location.getLocationId(), location.getName(), location.getTimeZoneId(), location.getCdate()) : null;
  }

  public static CommonRoom convertCommonRoom(RoomDto room) {
    return (room != null) ? new CommonRoom(room.getRoomId(), room.getLocationId(), room.getName(), room.getCdate()) : null;
  }

  public static CommonSensor convertCommonSensor(SensorDto sensor) {
    return (sensor != null)
        ? new CommonSensor(sensor.getSensorId(), sensor.getSlmId(), sensor.getName(), sensor.getCdate()) : null;
  }

  public static CommonSlm convertCommonSlm(SlmDto slm) {
    return (slm != null) ? new CommonSlm(slm.getSlmId(), slm.getProtocol(), slm.getIp(), slm.getPort(),
        slm.getUseAuth(), slm.getUserId(), slm.getUserPw(), slm.getCdate()) : null;
  }
 
  public static CommonSchedule convertCommonSchedule(ScheduleDto schedule) {
    return (schedule != null) ? new CommonSchedule(
    		  schedule.getScheduleId(), schedule.getLocationId(), schedule.getRoomId(), schedule.getName(), schedule.getUserId(),
    		  schedule.getUserName(), schedule.getDeptName(), schedule.getSdate(), schedule.getEdate(),
    		  schedule.getLocalYear(), schedule.getLocalMonth(), schedule.getLocalDay(), schedule.getLocalDayOfWeek(),
    		  schedule.getLocalShhmm(), schedule.getLocalEhhmm(), schedule.getLocalDuration(),
    		  schedule.getSensorCnt(), schedule.getTotalSensor(), schedule.getTotalDetect(),
    		  schedule.getChkDuration(), schedule.getResult(), schedule.getCdate()
    		) : null;
  }

  public static LocationDto convertLocation(CommonLocation commonLocation) {
    LocationDto ret = null;
    if (commonLocation != null) {
      ret = new LocationDto();
      ret.setLocationId(commonLocation.getLocationId());
      ret.setName(commonLocation.getName());
      ret.setTimeZoneId(commonLocation.getTimeZoneId());
      ret.setCdate(commonLocation.getCdate());
    }
    return ret;
  }

  public static RoomDto convertRoom(CommonRoom commonRoom) {
    RoomDto ret = null;
    if (commonRoom != null) {
      ret = new RoomDto();
      ret.setRoomId(commonRoom.getRoomId());
      ret.setLocationId(commonRoom.getLocationId());
      ret.setName(commonRoom.getName());
      ret.setCdate(commonRoom.getCdate());
    }
    return ret;
  }

  public static SensorDto convertSensor(CommonSensor commonSensor) {
    SensorDto ret = null;
    if (commonSensor != null) {
      ret = new SensorDto();
      ret.setSensorId(commonSensor.getSensorId());
      ret.setSlmId(commonSensor.getSlmId());
      ret.setName(commonSensor.getName());
      ret.setCdate(commonSensor.getCdate());
    }
    return ret;
  }

  public static SlmDto convertSlm(CommonSlm commonSlm) {
    SlmDto ret = null;
    if (commonSlm != null) {
      ret = new SlmDto();
      ret.setSlmId(commonSlm.getSlmId());
      ret.setProtocol(commonSlm.getProtocol());
      ret.setIp(commonSlm.getIp());
      ret.setPort(commonSlm.getPort());
      ret.setUseAuth(commonSlm.getUseAuth());
      ret.setUserId(commonSlm.getUserId());
      ret.setUserPw(commonSlm.getUserPw());
      ret.setCdate(commonSlm.getCdate());
    }
    return ret;
  }

}
