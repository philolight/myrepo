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

package com.lge.sm.cr_core.slm_comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import com.lge.framework.ceasar.util.ModelUtil;
import com.lge.framework.pacific.common.Utils;
import com.lge.framework.pacific.common.thread.DefaultManagedRunnable;
import com.lge.framework.pacific.common.thread.ManagedThreadFactory;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_core.slm_comm.inf.IChangedPoint;
import com.lge.sm.cr_core.slm_comm.inf.IPoint;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.dto.SensorDto;
import com.lge.sm.cr_data_store.dto.SlmDto;
import com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpoint;
import com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpointProxy;
import com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue;
import com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest;
import com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse;
import com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse_T;

public class SlmCommunicator {
  private static final String TAG = SlmCommunicator.class.getSimpleName();
  private static final String ERROR_CODE = "-20";

  private static final SlmCommunicator instance = new SlmCommunicator();
  private Map<String/* hostUrl */, LgLcsPointAccessServiceEndpoint/* soapClient */> soapClientMap = new ConcurrentHashMap<>();
  private Map<String/* sensor path */, IChangedPoint/* listener */> listenerMap = new ConcurrentHashMap<>();
  private Map<String/* sensor path */, SensorDto/* sensor */> sensorMap = new ConcurrentHashMap<>();
  private long managedThreadId = 0L;

  private AtomicBoolean isDone = new AtomicBoolean(false);

  public static SlmCommunicator getInstance() {
    return instance;
  }

  public synchronized boolean start(List<String> hostUrlList) {
    boolean ret = false;
    try {
      if (hostUrlList != null) {
        for (String hostUrl : hostUrlList) {
          LgLcsPointAccessServiceEndpoint soapClient = new LgLcsPointAccessServiceEndpointProxy(hostUrl);
          soapClientMap.put(hostUrl, soapClient);
        }
      }

      managedThreadId = ManagedThreadFactory.getInstance().getThreadAndStart(new DefaultManagedRunnable() {
        @Override
        public void doRun() {
          while (true) {
            if (listenerMap != null && listenerMap.size() > 0 && sensorMap != null && sensorMap.size() > 0) {
              List<SensorDto> sensorList = new ArrayList<>();
              sensorList.addAll(sensorMap.values());

              HashMap<String, IPoint> pointMap = readPointValueMod(sensorList);
              if (pointMap != null && !pointMap.isEmpty()) {
                for (String sensorPath : pointMap.keySet()) {
                  if (sensorPath != null) {
                    IChangedPoint listener = listenerMap.get(sensorPath);
                    if (listener != null) {
                      IPoint point = pointMap.get(sensorPath);
                      listener.onChangedPoint(point, (point != null) ? SlmValidationType.OK : SlmValidationType.NOT_OK);
                    }
                  } else {
                    Logger.error(TAG, "sensorPath is null in loop thread.");
                  }
                }
              } else {
                Logger.error(TAG, "The poinMap in Slm is null or empty. The Slm system is not stable.");
                if (listenerMap != null) {
                  for (IChangedPoint listener : listenerMap.values()) {
                    listener.onChangedPoint(null, SlmValidationType.NOT_OK);
                  }
                }
              }

            } else {
              // Logger.info(TAG, "listener is not registered anyone.");
            }

            synchronized (this) {
              try {
                long sensorPeriodMilli = TimeUnit.MILLISECONDS
                    .convert(
                        Integer
                            .parseInt(
                                PropertyManager.getPropertyManager()
                                    .get(Constants.CR_CORE_CONFIG_FILE_NAME,
                                        Constants.CR_CORE_CONFIG_GET_SENSOR_PERIOD_KEY, null)
                                    .trim()),
                        TimeUnit.SECONDS);
                this.wait(sensorPeriodMilli);
                // this.wait(1000);
              } catch (InterruptedException e) {
                Logger.error(TAG, "catched a InterruptedException in SlmCommunication loop thread.");
                stop();
                break;
              }
            }
          }
        }
      });

      ret = true;
    } catch (Exception e) {
      Logger.error(TAG, "The initialization of SlmCommunicator is failed. " + e);
    } finally {
      isDone.set(true);
    }

    return ret;
  }

  public synchronized void stop() {
    if (soapClientMap != null) {
      for (LgLcsPointAccessServiceEndpoint soapClient : soapClientMap.values()) {
        soapClient = null;
      }
    }

    if (listenerMap != null) {
      listenerMap.clear();
    }

    if (sensorMap != null) {
      sensorMap.clear();
    }

    ManagedThreadFactory.getInstance().terminateManagedThread(managedThreadId);

    isDone.set(false);
  }

  public void addListener(SensorDto sensor, IChangedPoint listener) {
    if (listenerMap != null && sensorMap != null && sensor != null && listener != null) {
      listenerMap.put(sensor.getSensorId(), listener);
      sensorMap.put(sensor.getSensorId(), sensor);
    }
  }

  public void addListener(List<SensorDto> sensorList, IChangedPoint listener) {
    if (listenerMap != null && sensorMap != null && sensorList != null && listener != null) {
      for (SensorDto sensor : sensorList) {
        listenerMap.put(sensor.getSensorId(), listener);
        sensorMap.put(sensor.getSensorId(), sensor);
      }
    }
  }

  public void removeListener(SensorDto sensor) {
    if (listenerMap != null && sensorMap != null && sensor != null) {
      listenerMap.remove(sensor.getSensorId());
      sensorMap.remove(sensor.getSensorId());
    }
  }

  public void removeListener(List<SensorDto> sensorList) {
    if (listenerMap != null && sensorMap != null && sensorList != null) {
      for (SensorDto sensor : sensorList) {
        listenerMap.remove(sensor.getSensorId());
        sensorMap.remove(sensor.getSensorId());
      }
    }
  }

  private void waitInitialization() {
    while (true) {
      if (isDone.get()) {
        break;
      }
      synchronized (this) {
        try {
          this.wait(100);
        } catch (InterruptedException e) {
        }
      }
    }
  }

  private LgLcsPointAccessServiceEndpoint getSoapClient(String slmId) {
    waitInitialization();

    LgLcsPointAccessServiceEndpoint ret = null;
    if (slmId != null) {
      SlmDto slm = DataStore.getInstance().getSlm(slmId);
      if (slm != null) {
        ret = soapClientMap.get(ModelUtil.getSlmPath(slm));
      }
    }
    return ret;
  }

  public HashMap<String, IPoint> readPointValueMod(List<SensorDto> sensorList) {
    waitInitialization();

    Map<LgLcsPointAccessServiceEndpoint /* soap client */, List<SensorDto>> innerClientMap = new HashMap<>();
    HashMap<String, IPoint> ret = new HashMap<>();

    if (soapClientMap != null && sensorList != null) {
      List<SensorDto> innerSensorList = new ArrayList<>();
      innerSensorList.addAll(sensorList);

      for (SensorDto sensor : sensorList) {
        if (sensor != null) {
          LgLcsPointAccessServiceEndpoint soapClient = getSoapClient(sensor.getSlmId());
          List<SensorDto> sensors = innerClientMap.get(soapClient);
          if (sensors != null) {
            sensors.add(sensor);
          } else {
            List<SensorDto> sensorForNewbee = new ArrayList<>();
            sensorForNewbee.add(sensor);
            innerClientMap.put(soapClient, sensorForNewbee);
          }
        }
      }

      for (LgLcsPointAccessServiceEndpoint soapClient : innerClientMap.keySet()) {
        if (soapClient != null) {
          List<SensorDto> sensors = innerClientMap.get(soapClient);
          if (sensors != null) {
            ReadPointValuesRequest parameters = new ReadPointValuesRequest();
            String[] pathArray = new String[sensors.size()];
            int loopCnt = 0;
            for (SensorDto innerSensor : sensors) {
              pathArray[loopCnt++] = innerSensor.getSensorId();
            }
            parameters.setReq(pathArray);
            try {
              ReadPointValuesResponse resp = soapClient.readPointValues(parameters);
              if (resp != null && resp.getRes() != null) {
                ReadPointValuesResponse_T respT = resp.getRes();
                if (respT != null) {
                  if (respT.getPointValues() != null) {
                    for (PointValue value : respT.getPointValues()) {
                      if (value.getValue() != null && !value.getValue().equals(ERROR_CODE)) {
                        String path = value.getPath();
                        if (path != null) {
                          String[] splittedPath = path.split("/");
                          String typeString = splittedPath[splittedPath.length - 1];
                          if (typeString != null) {
                            ret.put(path, PointModelFactory.getPointModel(typeString, value.getValue()));
                          }
                        }
                      }
                    } // for (PointValue
                  } else {
                    Logger.debug(TAG, "no point data. check sensor sensors=" + sensors);
                  }
                }
              }
            } catch (Exception e) {
              Logger.debug(TAG, "The exception is occurred while reading point values through soap client. "
                  + Utils.getStringFromException(e));
            }
          }
        }
      }

    } else {
      Logger.error(TAG, "the input argument is null. ");
      ret = null;
    }

    return ret;
  }
  // public HashMap<String/* sensorPath */, IPoint/* Point model */>
  // readPointValue(List<Sensor> sensorList) {
  // HashMap<String, IPoint> ret = new HashMap<>();
  // if (soapClientMap != null && sensorList != null) {
  // for (Sensor sensor : sensorList) {
  // if (sensor != null) {
  // Slm slm = DataStore.getInstance().getSlm(sensor.getSlmId());
  // if (slm != null) {
  // LgLcsPointAccessServiceEndpoint soapClient =
  // soapClientMap.get(slm.getSlmPath());
  // if (soapClient != null) {
  // try {
  // ReadPointValuesRequest parameters = new ReadPointValuesRequest();
  // parameters.setReq(Utils.convertToStringArray(sensor.getSensorPath()));
  //
  // ReadPointValuesResponse resp = soapClient.readPointValues(parameters);
  // if (resp != null && resp.getRes() != null) {
  // ReadPointValuesResponse_T respT = resp.getRes();
  // for (PointValue value : respT.getPointValues()) {
  // if (value.getValue() != null && !value.getValue().equals(ERROR_CODE)) {
  // String path = value.getPath();
  // if (path != null) {
  // String[] splittedPath = path.split("/");
  // String typeString = splittedPath[splittedPath.length - 1];
  // if (typeString != null) {
  // ret.put(value.getPath(), PointModelFactory.getPointModel(typeString,
  // value.getValue()));
  // }
  // }
  // }
  // }
  // } else {
  // Logger.debug(TAG, "The response of request is null. ");
  // }
  // } catch (Exception e) {
  // Logger.debug(TAG, "The exception is occurred while reading point values
  // through soap client. " + e);
  // }
  // } else {
  // Logger.debug(TAG, "soap client is null. ");
  // }
  // }
  // }
  // }
  // } else {
  // Logger.error(TAG, "the input argument is null. ");
  // ret = null;
  // }
  //
  // return ret;
  // }
  //
  // // for test
  // public HashMap<String/* slmPath */, HashMap<String/* sensorPath */,
  // IPoint/*
  // * Point
  // * model
  // */>> readPointValueTest(
  // List<Sensor> sensorList) {
  // HashMap<String, HashMap<String, IPoint>> ret = new HashMap<>();
  // if (soapClientMap != null && sensorList != null) {
  // for (Sensor sensor : sensorList) {
  // if (sensor != null) {
  // Slm slm = DataStore.getInstance().getSlm(sensor.getSlmId());
  // if (slm != null) {
  // LgLcsPointAccessServiceEndpoint soapClient =
  // soapClientMap.get(slm.getSlmPath());
  // if (soapClient != null) {
  // HashMap<String, IPoint> sensorValueMap = new HashMap<>();
  // try {
  // ReadPointValuesRequest parameters = new ReadPointValuesRequest();
  // parameters.setReq(Utils.convertToStringArray(sensor.getSensorPath()));
  //
  // ReadPointValuesResponse resp = soapClient.readPointValues(parameters);
  // if (resp != null && resp.getRes() != null) {
  // ReadPointValuesResponse_T respT = resp.getRes();
  // for (PointValue value : respT.getPointValues()) {
  // if (value.getValue() != null && !value.getValue().equals(ERROR_CODE)) {
  // String path = value.getPath();
  // if (path != null) {
  // String[] splittedPath = path.split("/");
  // String typeString = splittedPath[splittedPath.length - 1];
  // if (typeString != null) {
  // sensorValueMap.put(value.getPath(),
  // PointModelFactory.getPointModel(typeString, value.getValue()));
  // ret.put(slm.getSlmPath(), sensorValueMap);
  // }
  // }
  // }
  // }
  //
  // } else {
  // // Logger.debug(TAG, "The response of request is null. ");
  // }
  // } catch (Exception e) {
  // // Logger.debug(TAG, "The exception is occurred while reading
  // // point values through soap client. ");
  // }
  // } else {
  // // Logger.debug(TAG, "soap client is null. ");
  // }
  // }
  // }
  // }
  // } else {
  // Logger.error(TAG, "the input argument is null. ");
  // ret = null;
  // }
  //
  // return ret;
  // }

}
