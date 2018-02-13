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

package com.lge.sm.cr_core.reservation_checker;

import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.pacific.command_handler.inf.ICommand;
import com.lge.sm.cr_core.common.CommandType;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class CrReservCommand implements ICommand {
  private static final String TAG = CrReservCommand.class.getSimpleName();
  
  private long sessionId;
  private RoomDto locRoomInfo;
  private CrTaskConfigModel config;
  private ScheduleDto schedule;  
  private IDestroyedListener listener;
  private String scheduleId = null;
  private LocationDto location = null;
  
  @Override
  public String getType() {
    return CommandType.CR_COMMAND_RESERVATION_CHECK.getTypeString();
  }
  
  public CrReservCommand(long sessionId, RoomDto locRoomInfo, CrTaskConfigModel config, ScheduleDto schedule, LocationDto location, IDestroyedListener listener) {
    this.sessionId = sessionId;
    this.locRoomInfo = locRoomInfo;
    this.config = config;
    this.schedule = schedule;
    this.location = location;
    this.listener = listener;
  }
  
  public long getSessionId() {
    return sessionId;
  }
  
  public RoomDto getLocationRoomInfo() {
    return locRoomInfo;
  }
  
  public CrTaskConfigModel getConfigModel() {
    return config;
  }
  
  public ScheduleDto getCrReservSchedule() {
    return schedule;
  }
  
  public LocationDto getLocation() {
	  return location;
  }
  
  public IDestroyedListener getDestroyedListener() {
    return listener;
  }
  
  public CrReservCommand clone() {
    return new CrReservCommand(sessionId, (RoomDto)Factory.deepCopy(locRoomInfo), config.clone(), (ScheduleDto)Factory.deepCopy(schedule), (LocationDto)Factory.deepCopy(location), listener);
  }

  @Override
  public String getId() {   
    return (scheduleId != null) ? scheduleId : schedule.getScheduleId();
  }

  @Override
  public void setId(String id) {    
    this.scheduleId = id;
  }

}
