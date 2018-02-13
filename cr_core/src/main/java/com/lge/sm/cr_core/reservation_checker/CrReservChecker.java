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

import static java.lang.Math.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.pacific.command_handler.CommandQueue;
import com.lge.framework.pacific.command_handler.inf.ICommand;
import com.lge.framework.pacific.command_handler.inf.ICommandRunnable;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.common.CommandType;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.common.ScheduleResult;
import com.lge.sm.cr_core.cr_system_comm.CrSystemCommunicator;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_core.slm_comm.SlmCommunicator;
import com.lge.sm.cr_core.slm_comm.SlmValidationType;
import com.lge.sm.cr_core.slm_comm.inf.IChangedPoint;
import com.lge.sm.cr_core.slm_comm.inf.IPoint;
import com.lge.sm.cr_core.slm_comm.model.PointOccupancy;
import com.lge.sm.cr_core.util.SensorValueWriter;
import com.lge.sm.cr_core.util.UtilConstants;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.dto.ScheduleDto;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.entity.ScheduleEntity;

public class CrReservChecker implements ICommandRunnable {
  private static final String TAG = CrReservChecker.class.getSimpleName();

  private long totalSum = 0;
  private long currentSum = 0;
  private long totalDigit = 0;
  private long currentDigit = 0;

  private long startDateLocal = 0;
  private long sensorCnt = 0;
  private long errorSum = 0;

  private CrReservCommand command = null;
  private IChangedPoint listener = new IChangedPoint() {
    @Override
    public void onChangedPoint(IPoint point, SlmValidationType isValidation) {
      if (isValidation.isValid()) {
        Integer value = ((PointOccupancy) point).getValue();
        if (value > -1) {
          totalSum += value;
          currentSum += value;
        } else {
          Logger.debug(TAG, "The connection of point is not stable.");
          errorSum++;
        }
      } else {
        Logger.debug(TAG, "the error is occurred from slm.");
        errorSum++;
      }
    }
  };

  @Override
  public void run() {
    writeSensorValue();

    List<SensorDto> sensorList = DataStore.getInstance().getSensorList(command.getLocationRoomInfo().getLocationId(),
        command.getLocationRoomInfo().getRoomId());
    if (sensorList != null) {
      SlmCommunicator.getInstance().addListener(sensorList, listener);
      sensorCnt = sensorList.size();
    }
    startDateLocal = System.currentTimeMillis();
    try {
      Logger.debug(TAG, "CrReservChecker is running " + " , schedule id : "
          + command.getCrReservSchedule().getScheduleId() + " , sessionId : " + command.getSessionId());
      ScheduleDto schedule = command.getCrReservSchedule();
      if (schedule != null) {
        long aliveTimeMilli = TimeUnit.MILLISECONDS.convert(command.getConfigModel().getAliveTime(), TimeUnit.MINUTES);
        Logger.debug(TAG, "##### aliveTime : " + aliveTimeMilli);
        long endDateLocal = startDateLocal + aliveTimeMilli;
        long sensorPeriodMilli = TimeUnit.MILLISECONDS.convert(command.getConfigModel().getSensorPeriod(),
            TimeUnit.SECONDS);

        while (true) {
          if (endDateLocal >= System.currentTimeMillis()) {
            Logger.debug(TAG, "schedule name : " + command.getCrReservSchedule().getName() + " , currentTime : "
                + System.currentTimeMillis());

            if (!DataStore.getInstance().isExistScheduleResult(command.getCrReservSchedule())) {
              Logger.debug(TAG,
                  "get sensor data. current time : " + System.currentTimeMillis() + " , next check time : "
                      + (System.currentTimeMillis() + sensorPeriodMilli) + " , schedule name : "
                      + command.getCrReservSchedule().getName());

              int thresholdCnt = Integer.parseInt(PropertyManager.getPropertyManager()
                  .get(Constants.CR_CORE_CONFIG_FILE_NAME, Constants.CR_CORE_CONFIG_SENSOR_THRESHOLD, "0").trim());

              if (thresholdCnt > 0) {
                calculateSensorValue();

                if ((totalSum + errorSum) > thresholdCnt) {
                  Logger.debug(TAG,
                      "insert log to DataStore. This schedule is valid by sensed to use cr." + " , schedule name : "
                          + command.getCrReservSchedule().getName() + " , sessionId : " + command.getSessionId());
                  insertSchedule(schedule, ScheduleResult.RESULT_VALID_BY_SENSED_TO_USE);
                  return;
                }

              } else {
                calculateSensorValue();
              }
            } else {
              Logger.debug(TAG, "already handled schedule. " + " , schedule name : "
                  + command.getCrReservSchedule().getName() + " , sessionId : " + command.getSessionId());
              break;
            }
          } else {
            if (!DataStore.getInstance().isExistScheduleResult(schedule)) {
              Logger.debug(TAG, "insert log to DataStore. This schedule is invalid by timeout." + " , schedule name : "
                  + command.getCrReservSchedule().getName() + " , sessionId : " + command.getSessionId());

              insertSchedule(schedule, ScheduleResult.RESULT_INVALID_BY_TIMEOUT);

              String delFuncActivation = PropertyManager.getPropertyManager()
                  .get(Constants.CR_CORE_CONFIG_FILE_NAME, Constants.CR_CORE_CONFIG_DELETE_FUNCTION_ACTIVATION, "false")
                  .trim().toLowerCase();

              if (delFuncActivation.equals("true")) {
                CrSystemCommunicator.getInstance().deleteReservSchedule(schedule.getUserId(), schedule.getScheduleId()); // realcode
                writeCancelLog();
              }
            }
            return;
          }
          synchronized (this) {
            this.wait(sensorPeriodMilli);
          }
        }
      } else {
        Logger.error(TAG, "The schedule is null. This checker runnable will be terminated. ");
      }
    } catch (Exception e) {
      Logger.error(TAG, "error .. excetpion is " + e);
    } finally {
      CommandQueue.getInstance().stopAndRemoveCommandRunnable(CommandType.CR_COMMAND_RESERVATION_CHECK.getTypeString(),
          command.getCrReservSchedule().getScheduleId());      
      Logger.debug(TAG, "This schedule is terminated." + command.getCrReservSchedule().getName() + " , sessionId : "
          + command.getSessionId());
    }
  }

  @Override
  public void setCommand(ICommand command) {
    this.command = (CrReservCommand) command;
  }

  @Override
  public ICommandRunnable makeCommandRunnableInstance() {
    return new CrReservChecker();
  }

  @Override
  public void stop() {
  }

  private void insertSchedule(ScheduleDto schedule, ScheduleResult result) {
    try {
      schedule.setSensorCnt(toIntExact(sensorCnt));
      schedule.setTotalSensor(toIntExact(totalSum));
      schedule.setTotalDetect(toIntExact(totalDigit));
      schedule.setChkDuration(toIntExact(System.currentTimeMillis() - startDateLocal)/(60*1000));
      schedule.setResult(result.getValue());
      schedule.setErrorCnt(toIntExact(errorSum));
      schedule.setResultDate(DateStringUtil.getCurrentDateString(command.getLocation().getTimeZoneId()));
      DataStore.getInstance().addScheduleResult(schedule);
    } catch (ArithmeticException e) {
      Logger.debug(TAG, "the arithmaticExcept is occurred.");
    }
  }

  private void writeSensorValue() {
    SensorValueWriter.getInstance().write(command.getLocationRoomInfo().getRoomId(),
        command.getLocationRoomInfo().getLocationId() + "," + command.getLocationRoomInfo().getRoomId() + ","
            + command.getCrReservSchedule().getScheduleId() + "," + command.getCrReservSchedule().getName() + ","
            + totalSum + "," + currentSum + "," + totalDigit + "," + currentDigit + "," + errorSum);
  }

  private void writeCancelLog() {
    SensorValueWriter.getInstance().write(UtilConstants.CANCEL_SCHEDULE_LOG_FILE_NAME,
        "room id : " + command.getCrReservSchedule().getRoomId() + " , schedule id : "
            + command.getCrReservSchedule().getScheduleId() + " , schedule name : "
            + command.getCrReservSchedule().getName());
  }

  private void loggingSensorValue() {
    Logger.debug(TAG,
        command.getLocationRoomInfo().getLocationId() + "," + command.getLocationRoomInfo().getRoomId() + ","
            + command.getCrReservSchedule().getScheduleId() + "," + totalSum + "," + currentSum + "," + totalDigit + ","
            + currentDigit + "," + errorSum);
  }

  private void calculateSensorValue() {
    currentDigit = (currentSum > 0) ? 1 : 0;
    totalDigit += currentDigit;

    writeSensorValue();
    loggingSensorValue();

    currentSum = 0;
    currentDigit = 0;
  }
}
