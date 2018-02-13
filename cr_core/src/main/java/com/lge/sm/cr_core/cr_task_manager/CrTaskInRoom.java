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

package com.lge.sm.cr_core.cr_task_manager;

import java.util.Calendar;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.lge.framework.pacific.common.thread.DefaultManagedRunnable;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;

public class CrTaskInRoom extends DefaultManagedRunnable {
  private static final String TAG = CrTaskInRoom.class.getSimpleName();

  private RoomDto room = null;
  private LocationDto loc = null;
  private CrTaskConfigModel configModel = null;
  private Timer timerForChecker = new Timer();
  private long sessionId = 0;

  CrTaskInRoom(RoomDto room, LocationDto loc, CrTaskConfigModel configModel) throws InstantiationException {
    if (room != null && loc != null && configModel != null) { 
      this.room = room;
      this.loc = loc;
      this.configModel = configModel;
    } else {
      throw new InstantiationException();
    }
  }

  public void setSessionId(long sessionId) {
    this.sessionId = sessionId;
  }

  @Override
  public void doRun() {
    Logger.debug(TAG,
        "CrTaskRunnable is running. The roomId is " + room.getRoomId() + ". The configModel is " + configModel);

    if (configModel != null) {
      scheduleReservListChecker();
    } else {
      Logger.error(TAG, "The config model was not set yet. You have to set property in cr_core_config.properties.");
    }
  }

  private void scheduleReservListChecker() {
    Calendar startCalendar = Calendar.getInstance();
    double min = startCalendar.get(Calendar.MINUTE); // for test
    // int startMin = (int) (Math.ceil(min/10d) * 10d); // real code
    int startMin = (int) min;
    Logger.debug(TAG, "min : " + min + " , startMin : " + startMin + " , wakeup time : " + configModel.getWakeUpTime());
    startCalendar.set(Calendar.MINUTE, startMin); // real code

    if (timerForChecker != null) {
      try {
        timerForChecker.scheduleAtFixedRate(new CrReservListChecker(sessionId, room, loc, configModel),
            startCalendar.getTime(), TimeUnit.MILLISECONDS.convert(configModel.getWakeUpTime(), TimeUnit.MINUTES));
      } catch (InstantiationException e) {
        Logger.error(TAG, "The class member variable is null.");
      }
    }
  }

  protected void finalize() {
    if (timerForChecker != null) {
      timerForChecker.cancel();
      timerForChecker = null;
    }
  }
}
