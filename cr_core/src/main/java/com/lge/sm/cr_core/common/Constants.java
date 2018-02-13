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

package com.lge.sm.cr_core.common;

public class Constants {
  public static final int MAX_NUM_FOR_RETRY_INIT = 60;   
  public static final int DEFAULT_CR_TASK_MANAGER_WAKEUP_TIME_MIN = 10;
  public static final int DEFAULT_CR_RESERVATION_ALIVE_TIME_MIN = 15;
  public static final int DEFAULT_CR_GET_SENSOR_VALUE_PERIOD_MIN = 60; 
  
  public static final String DEFAULT_EXTERNAL_PROPERTY_PATH = "resources";
  public static final String CR_CORE_CONFIG_FILE_NAME = "cr_core_config";
  public static final String CR_CORE_CONFIG_WAKEUP_TIME_KEY = "CrTaskManagerWakeUpTime";
  public static final String CR_CORE_CONFIG_ALIVE_TIME_KEY = "CrReservationAliveTime";
  public static final String CR_CORE_CONFIG_GET_SENSOR_PERIOD_KEY = "CrGetSensorValuesPeriod";
  public static final String CR_CORE_CONFIG_GET_SENSOR_INTERVAL_KEY = "CrGetSensorValuesInterval";
  public static final String CR_CORE_CONFIG_GET_SENSOR_COUNT_KEY = "CrGetSensorValuesCount";
  public static final String CR_CORE_CONFIG_WRITE_SENSOR_VALUE_INTERVAL = "CrWriteSensorValueInterval";
  public static final String CR_CORE_CONFIG_SENSOR_THRESHOLD = "CrSensorThreshold";
  public static final String CR_CORE_CONFIG_CR_SYSTEM_HOST_NAME_KEY = "CrSystemHostName";  
  public static final String CR_CORE_CONFIG_TIME_FOR_EXCEPTING_LONG_SCHED_KEY = "CrTimeForExceptingLongSched";
  public static final String CR_CORE_CONFIG_DELETE_FUNCTION_ACTIVATION = "CrDeleteFunctionActivation";
   
  public static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
  public static final String DISPLAY_DATE_FORMAT = "yyyy.MM.dd.HH:mm:ss";
   
  public static final String PORT_SEPERATOR = ":";
  
  public static final String DEFAULT_SLM_CONN_PATH = "http://10.158.221.11:8080/lighting/api/soap/PointAccessService";
  public static final String DEFAULT_SLM_OCCUPANCY_PATH = "/마곡 N1동 1/5F/Lighting2011/Occupancy";
}
