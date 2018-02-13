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

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.ceasar.util.TimeUtil;
import com.lge.framework.pacific.command_handler.CommandQueue;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.common.CommandType;
import com.lge.sm.cr_core.common.ScheduleResult;
import com.lge.sm.cr_core.cr_system_comm.CrSystemCommunicator;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_core.util.TimeUtils;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class CrReservListChecker extends TimerTask {
  private static final String TAG = CrReservListChecker.class.getSimpleName();

  private long sessionId = 0;
  private RoomDto room = null;
  private LocationDto loc = null;
  private CrTaskConfigModel config = null;

  CrReservListChecker(long sessionId, RoomDto room, LocationDto loc, CrTaskConfigModel config)
      throws InstantiationException {
    if (room != null && loc != null && config != null) {
      this.sessionId = sessionId;
      this.room = room;
      this.loc = loc;
      this.config = config;
    } else {
      throw new InstantiationException();
    }
  }

  private boolean isValidSchedule(ScheduleDto schedule) {
    boolean ret = false;
    if (schedule != null) {
      try {
        long startDate = TimeUtils.getUtcLongWithFormat(schedule.getSdate(),
            com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT, loc.getTimeZoneId());
        long currentTime = System.currentTimeMillis();

        Logger.debug(TAG, "startDate : " + new Date(startDate) + " currentTime : " + new Date(currentTime)
            + " schedule : " + schedule.getName());

        if (startDate >= currentTime) {
          ret = true;
        }
      } catch (Exception e) {
      }
    }

    return ret;
  }

  private boolean isToLongSchedule(ScheduleDto schedule) {
    boolean ret = false;
    try {
      if (schedule != null) {        
        long startDateTime = TimeUtils.getUtcLongWithFormat(schedule.getSdate(),
            com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT, loc.getTimeZoneId());
        long endDateTime = TimeUtils.getUtcLongWithFormat(schedule.getEdate(),
            com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT, loc.getTimeZoneId());

        long differenceMin = (endDateTime - startDateTime) / 60000;
        long timeForExceptingLongSched = Long
            .parseLong(
                PropertyManager.getPropertyManager()
                    .get(com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_FILE_NAME,
                        com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_TIME_FOR_EXCEPTING_LONG_SCHED_KEY, null)
                    .trim());

        if (differenceMin >= timeForExceptingLongSched) {
          ret = true;
        }
      }
    } catch (Exception e) {
    }

    return ret;
  }

  // 171116
  public void removeAndStopCancledSchedule(List<ScheduleDto> scheduleList) {
    List<String> existScheduleIdList = CrManagedScheduleMap.getInstance().getAllScheduleIdList();
    if (existScheduleIdList != null) {
      for (String scheduleId : existScheduleIdList) {
        if (!scheduleList.contains(scheduleId)) {
          CrManagedScheduleMap.getInstance().removeScheduleAndStopInjector(sessionId, scheduleId);
          CommandQueue.getInstance()
              .stopAndRemoveCommandRunnable(CommandType.CR_COMMAND_RESERVATION_CHECK.getTypeString(), scheduleId);
        }
      }
    }
  }

  @Override
  public void run() {
    Logger.debug(TAG, "CrReservListChecker is running. get cr reserv list from CrSystemCommunicator.");

    List<ScheduleDto> scheduleList = CrSystemCommunicator.getInstance().getReservScheduleList(room.getLocationId(),
        room.getRoomId(), loc.getTimeZoneId()); // real code
    if (scheduleList != null && scheduleList.size() > 0) {
      for (ScheduleDto schedule : scheduleList) {
        if (!isValidSchedule(schedule)) {
          if (!DataStore.getInstance().isExistScheduleResult(schedule)
              && !CrManagedScheduleMap.getInstance().isExistSchedule(sessionId, schedule.getScheduleId())) {
            Logger.debug(TAG,
                "insert log to DataStore. This schedule is valid because the serviceEnable check time is already passed."
                    + " , schedule name : " + schedule.getName() + " , sessionId : " + sessionId);
            schedule.setLocalShhmm(TimeUtil.koreaTimeToLocal(schedule.getSdate(), loc.getTimeZoneId(),
                com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT));
            schedule.setLocalEhhmm(TimeUtil.koreaTimeToLocal(schedule.getEdate(), loc.getTimeZoneId(),
                com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT));
            schedule.setChkDuration(0);
            schedule.setSensorCnt(0);
            schedule.setTotalSensor(0);
            schedule.setTotalDetect(0);
            schedule.setErrorCnt(0);
            schedule.setResult(ScheduleResult.RESULT_VALID_BY_ALREADY_HANDLED.getValue());
            schedule.setResultDate(DateStringUtil.getCurrentDateString(loc.getTimeZoneId()));
            DataStore.getInstance().addScheduleResult(schedule);
          }
          continue;
        }

        // for excepting the long schedule
        if (isToLongSchedule(schedule)) {
          Logger.debug(TAG,
              "This schedule is too long, so cr_core will be skipped this. room id : " + schedule.getRoomId()
                  + ", schedule id : " + schedule.getScheduleId() + " , schedule name : " + schedule.getName());
          continue;
        }

        if (CrManagedScheduleMap.getInstance().isExistSchedule(sessionId, schedule.getScheduleId())) {
          Logger.debug(TAG, "This schedule is old thing.. " + " , schedule name : " + schedule.getName()
              + " , sessionId : " + sessionId);
          // TODO : will be deleted. maybe this contents is unneccessary.
          ScheduleDto oldSchedule = CrManagedScheduleMap.getInstance().getSchedule(sessionId, schedule.getScheduleId());
          if (oldSchedule != null) {
            String oldStartDate = oldSchedule.getSdate();
            String oldEndDate = oldSchedule.getEdate();
            String newStartDate = schedule.getSdate();
            String newEndDate = schedule.getEdate();
            String oldScheduleName = oldSchedule.getName();
            String newScheduleName = schedule.getName();

            if (!oldStartDate.equals(newStartDate) || !oldEndDate.equals(newEndDate)
                || !oldScheduleName.equals(newScheduleName)) {
              CrManagedScheduleMap.getInstance().removeScheduleAndStopInjector(sessionId, oldSchedule.getScheduleId());
              CommandQueue.getInstance().stopAndRemoveCommandRunnable(
                  CommandType.CR_COMMAND_RESERVATION_CHECK.getTypeString(), oldSchedule.getScheduleId());
              try {
                CrManagedScheduleMap.getInstance().putScheduleAndStartInjector(sessionId, schedule,
                    new CrCommandInjector(sessionId, Factory.deepCopy(room), Factory.deepCopy(loc), config.clone(),
                        Factory.deepCopy(schedule)));
              } catch (InstantiationException e) {
                Logger.debug(TAG, "putScheduleAndStartInjector is failed because of InstantiationException. "
                    + " , schedule name : " + schedule.getName() + " , sessionId : " + sessionId);
                continue;
              }
            } // else { // pass }
          } else {
            Logger.debug(TAG, "The old schedule is already invalid. schedule name : " + schedule.getName());
            continue;
          }

        } else {
          Logger.debug(TAG, "This schedule is new thing~~ " + " , schedule name : " + schedule.getName()
              + " , sessionId : " + sessionId);
          try {
            CrManagedScheduleMap.getInstance().putScheduleAndStartInjector(sessionId, schedule,
                new CrCommandInjector(sessionId, (RoomDto) Factory.deepCopy(room), (LocationDto) Factory.deepCopy(loc),
                    config.clone(), (ScheduleDto) Factory.deepCopy(schedule)));
          } catch (InstantiationException e) {
            Logger.debug(TAG, "putScheduleAndStartInjector is failed because of InstantiationException. "
                + " , schedule name : " + schedule.getName() + " , sessionId : " + sessionId);
            continue;
          }
        }
      }
    }
  }
}
