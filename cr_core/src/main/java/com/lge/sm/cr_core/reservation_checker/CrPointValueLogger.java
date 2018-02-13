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

import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import com.lge.sm.cr_core.util.SensorValueWriter;

//TODO : just for logging sensor data.. it will be deleted.
public class CrPointValueLogger extends TimerTask {
  private static final String TAG = CrPointValueLogger.class.getSimpleName();
  private CrReservCommand command = null;
  private AtomicLong occupancyCnt = new AtomicLong(0);
  private AtomicBoolean isFirst = new AtomicBoolean(true);

  CrPointValueLogger(CrReservCommand command) {
    this.command = command;
  }

  public void setOccupancyCnt(long occupancyCnt) {
    this.occupancyCnt.set(occupancyCnt);
  }

  @Override
  public void run() {
    // 파일에 로그 기록
    
//    if (isFirst.get()) {
//      SensorValueWriter.getInstance().write("[" + command.getLocationRoomInfo().getLocationId() + "][" + command.getLocationRoomInfo().getRoomId()
//          +"]["+ command.getCrReservSchedule().getScheduleId() + "][" + command.getCrReservSchedule().getScheduleName() + "][" +occupancyCnt.get() + "]");
//      isFirst.set(false);
//    } else {
//      SensorValueWriter.getInstance().write("[" + command.getLocationRoomInfo().getLocationId() + "][" + command.getLocationRoomInfo().getRoomId()
//          +"]["+ command.getCrReservSchedule().getScheduleId() + "][" + occupancyCnt.get() + "]");
//    }
    
  }
}
