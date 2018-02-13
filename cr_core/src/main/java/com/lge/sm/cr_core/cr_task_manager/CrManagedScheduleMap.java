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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.lge.framework.pacific.common.thread.ManagedThreadFactory;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.model.CrManagedSchedule;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class CrManagedScheduleMap {
  private static final String TAG = CrManagedScheduleMap.class.getSimpleName();

  private static final CrManagedScheduleMap instance = new CrManagedScheduleMap();

  private ConcurrentHashMap<Long /* sessionId */, HashMap<String /* scheduleId */, CrManagedSchedule>> managedScheduleMap = new ConcurrentHashMap<>();

  public static CrManagedScheduleMap getInstance() {
    return instance;
  }

  public void clearAsSessionId(long sessionId) {
    synchronized (managedScheduleMap) {
      for (CrManagedSchedule managedSchedule : managedScheduleMap.get(sessionId).values()) {
        ManagedThreadFactory.getInstance().terminateManagedThread(managedSchedule.getThreadId());
      }
      managedScheduleMap.remove(sessionId);
    }
  }

  public void clearAll() {
    synchronized (managedScheduleMap) {
      Set<Long> sessionIds = managedScheduleMap.keySet();

      for (long sessionId : sessionIds) {
        clearAsSessionId(sessionId);
      }

      managedScheduleMap.clear();
    }
  }

  public void makeMapAsSessionId(long sessionId) {
    synchronized (managedScheduleMap) {
      managedScheduleMap.put(sessionId, new HashMap<String, CrManagedSchedule>());
      Logger.debug(TAG, "@@@@@ make map : " + managedScheduleMap);
    }
  }

  public boolean putScheduleAndStartInjector(long sessionId, ScheduleDto schedule, CrCommandInjector commandInjector) {
    HashMap<String, CrManagedSchedule> innerManagedScheduleMap = null;
           
    synchronized (managedScheduleMap) {
      innerManagedScheduleMap = managedScheduleMap.get(sessionId);
    }

    if (innerManagedScheduleMap != null) {
      long threadId = ManagedThreadFactory.getInstance().getThreadAndStart(commandInjector,
          TimeUnit.MILLISECONDS.convert(Constants.DEFAULT_CR_COMMAND_INJECTOR_TIMEOUT_HOUR, TimeUnit.HOURS));

      innerManagedScheduleMap.put(schedule.getScheduleId(), new CrManagedSchedule(threadId, schedule));
      return true;
    } else {
      Logger.error(TAG, "This session id is invalid. putScheduleAndStartInjector. session id : " + sessionId);
      return false;
    }
  }

  public ScheduleDto getSchedule(long sessionId, String scheduleId) {
    ScheduleDto ret = null;
    HashMap<String, CrManagedSchedule> innerManagedScheduleMap = managedScheduleMap.get(sessionId);
    if (innerManagedScheduleMap != null) {
      CrManagedSchedule managedSchedule= innerManagedScheduleMap.get(scheduleId);
      if (managedSchedule != null) {
        ret = managedSchedule.getSchedule();
      } else {
        Logger.error(TAG, "This schedule id is invalid,");       
      }      
    } else {
      Logger.error(TAG, "This session id is invalid. getSchedule");      
    }
    return ret;
  }

  public boolean removeScheduleAndStopInjector(long sessionId, String scheduleId) {
    boolean ret = false;
    HashMap<String, CrManagedSchedule> innerManagedScheduleMap = null;
    synchronized (managedScheduleMap) {
      innerManagedScheduleMap = managedScheduleMap.get(sessionId);
    }

    if (scheduleId != null && innerManagedScheduleMap != null && innerManagedScheduleMap.containsKey(scheduleId)) {
      ManagedThreadFactory.getInstance().terminateManagedThread(innerManagedScheduleMap.get(scheduleId).getThreadId());
      innerManagedScheduleMap.remove(scheduleId);
      Logger.debug(TAG, "The schedule is removed. scheduleId id : " + scheduleId);
      ret = true; 
    } else {
//      Logger.info(TAG, "This session id is invalid. removeScheduleAndStopInjector");
    }
    return ret;
  }

  public boolean isExistSchedule(long sessionId, String scheduleId) {
    HashMap<String, CrManagedSchedule> innerManagedScheduleMap = null;
    synchronized (managedScheduleMap) {
      innerManagedScheduleMap = managedScheduleMap.get(sessionId);
    }

    if (innerManagedScheduleMap != null) {
      return innerManagedScheduleMap.containsKey(scheduleId);
    } else {
      return false;
    }
  }

  public List<String> getAllScheduleIdList() {
    List<String> ret = null;
    Collection<HashMap<String, CrManagedSchedule>> innerMap = null;
    synchronized (managedScheduleMap) {
      innerMap = managedScheduleMap.values();
    }
    
    if (innerMap != null) {
      ret = new ArrayList<>();
      for (HashMap<String, CrManagedSchedule> scheduleMap : innerMap) {
        ret.addAll(scheduleMap.keySet());
      }
    } else {
      Logger.error(TAG, "getAllScheduleIdList, inner map is null.");
    }
    
    return ret;
  }

}
