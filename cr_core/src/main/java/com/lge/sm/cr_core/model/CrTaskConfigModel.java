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

package com.lge.sm.cr_core.model;

import com.lge.sm.cr_core.common.Constants;

public class CrTaskConfigModel {
  private Integer crTaskManagerWakeUpTime = Constants.DEFAULT_CR_TASK_MANAGER_WAKEUP_TIME_MIN;
  private Integer crReservationAliveTime = Constants.DEFAULT_CR_RESERVATION_ALIVE_TIME_MIN;
  private Integer crGetSensorValuesPeriod = Constants.DEFAULT_CR_GET_SENSOR_VALUE_PERIOD_MIN;

  public CrTaskConfigModel(String wakeUpTime, String aliveTime, String getSensorPeriod) {
    if (wakeUpTime != null) {
      crTaskManagerWakeUpTime = Integer.parseInt(wakeUpTime.trim());
    }
    if (aliveTime != null) {
      crReservationAliveTime = Integer.parseInt(aliveTime.trim());
    }
    if (getSensorPeriod != null) {
      crGetSensorValuesPeriod = Integer.parseInt(getSensorPeriod.trim());
    }    
  }
  
  public CrTaskConfigModel(int wakeUpTime, int aliveTime, int getSensorPeriod) {
    crTaskManagerWakeUpTime = wakeUpTime;
    crReservationAliveTime = aliveTime;
    crGetSensorValuesPeriod = getSensorPeriod;
  }

  public int getWakeUpTime() {
    return crTaskManagerWakeUpTime;
  }

  public int getAliveTime() {
    return crReservationAliveTime;
  }

  public int getSensorPeriod() {
    return crGetSensorValuesPeriod;
  }
  
  public CrTaskConfigModel clone() {
    return new CrTaskConfigModel(this.crTaskManagerWakeUpTime, this.crReservationAliveTime, this.crGetSensorValuesPeriod);
  }
}
