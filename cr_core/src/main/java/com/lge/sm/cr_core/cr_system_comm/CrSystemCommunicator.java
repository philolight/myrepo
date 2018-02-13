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

package com.lge.sm.cr_core.cr_system_comm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.lge.framework.ceasar.util.TimeUtil;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class CrSystemCommunicator {
  private static final String TAG = CrSystemCommunicator.class.getSimpleName();

  private IScheduleSource scheduleSource = new ScheduleSource();

  public void setScheduleSource(IScheduleSource scheduleSource) {
    this.scheduleSource = scheduleSource;
  }

  private static final CrSystemCommunicator instance = new CrSystemCommunicator();
  private String hostName = null;

  public static CrSystemCommunicator getInstance() {
    return instance;
  }

  public CrSystemCommunicator start(String hostName) {
    this.hostName = hostName;
    return this;
  }

  public void stop() {
    this.hostName = null;
  }

  public boolean isExistScheduleId() {
    return true;
  }

  public boolean isExistScheduleId(String locationId, String roomId, String timezone, String scheduleId) {
    List<ScheduleDto> scheduleList = getReservScheduleList(locationId, roomId, timezone); // real
    if (scheduleList != null) {
      for (ScheduleDto schedule : scheduleList) {
        if (schedule.getScheduleId().equals(scheduleId))
          return true;
      }
    }

    return false;
  }

  public boolean deleteReservSchedule(String userId, String scheduleId) {
    if (userId == null || scheduleId == null) {
      Logger.error(TAG, "The input argument is invalid.");
      return false;
    }

    boolean ret = false;

    try {
      if (userId != null && scheduleId != null) {
        JSONObject body = new JSONObject();
        body.put(Constants.KEY_BODY_USER_ID, userId);
        body.put(Constants.KEY_BODY_SCHEDULE_ID, scheduleId);

        String hostName = PropertyManager.getPropertyManager().get(
            com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_FILE_NAME,
            com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_CR_SYSTEM_HOST_NAME_KEY, null);

        if (hostName != null) {

          JSONObject respObj = scheduleSource.delete(body, hostName);

          if (respObj != null) {
            JSONObject headerObj = (JSONObject) respObj.get(Constants.KEY_HEADER);
            if (headerObj != null) {
              String returnDesc = (String) headerObj.get(Constants.KEY_HEADER_RETURN_DESC);
              ret = (returnDesc != null) ? returnDesc.equals(Constants.STRING_RETURN_DESC_OK) : false;
            } else {
              Logger.error(TAG, "The header of response is null. ");
            }
          } else {
            Logger.error(TAG, "The response on delete shcedule is null.");
          }
        } else {
          Logger.error(TAG,  "host name is not exist.");
        }
      } else {
        Logger.error(TAG, "The input argument is invalid.");
      }
    } catch (Exception e) {
      Logger.error(TAG, "The Exception is occurred. The error msg is " + e);
    }

    return ret;
  }

  @SuppressWarnings("unchecked")
  public List<ScheduleDto> getReservScheduleList(String locationId, String roomId, String timezone) { // real
    // code
    List<ScheduleDto> ret = new ArrayList<>();

    try {
      if (locationId != null && roomId != null && timezone != null) {
        JSONObject body = new JSONObject();
        body.put(Constants.KEY_ROOM_ID, roomId);
        body.put(Constants.KEY_TIMEZONE_CODE, timezone);
        body.put(Constants.KEY_LOCATION_ID, locationId);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timezone));
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        String startTime = "" + cal.get(Calendar.YEAR) + (month > 9 ? month : "0" + month)
            + (date > 9 ? date : "0" + date);
        body.put(Constants.KEY_START_TIME, startTime); // real code
        body.put(Constants.KEY_END_TIME, startTime); // real code
        // body.put(Constants.KEY_START_TIME, "20160401"); // for test
        // body.put(Constants.KEY_END_TIME, "20160601"); // for test
        body.put(Constants.KEY_PAGE_INDEX, "1");
        body.put(Constants.KEY_PAGE_PER_RECORD, String.valueOf(24 * (60 / 10)));

        JSONObject respObj = scheduleSource.getScheduleListByRoom(body,
            PropertyManager.getPropertyManager().get(com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_FILE_NAME,
                com.lge.sm.cr_core.common.Constants.CR_CORE_CONFIG_CR_SYSTEM_HOST_NAME_KEY, null).trim());

        if (respObj != null) {
          JSONObject headerObj = (JSONObject) respObj.get(Constants.KEY_HEADER);
          if (headerObj != null
              && headerObj.get(Constants.KEY_HEADER_RETURN_DESC).equals(Constants.STRING_RETURN_DESC_OK)) {
            JSONObject bodyObj = (JSONObject) respObj.get(Constants.KEY_BODY);
            if (bodyObj != null) {
              JSONArray meetingList = (JSONArray) bodyObj.get(Constants.KEY_BODY_MEETING_LIST);
              if (meetingList != null && meetingList.size() > 0) {
                for (Object obj : meetingList) {
                  JSONObject scheduleObj = (JSONObject) obj;
                  ret.add(newScheduleByJson(scheduleObj, locationId, roomId, timezone));
                }
              } else {
                Logger.error(TAG, "The meeting list is empty.");
              }
            } else {
              Logger.error(TAG, "The body is empty.");
            }

          } else {
            Logger.error(TAG, "The return descript is not OK.");
          }
        } else {
          Logger.error(TAG, "The response obj is null.");
        }

      } else {
        Logger.error(TAG, "The input arguments is invalid.");
      }

    } catch (Exception e) {
      Logger.error(TAG, "The Exception is occurred. The error msg is " + e);
    }

    return ret;
  }

  public ScheduleDto newScheduleByJson(JSONObject scheduleObj, String locationId, String roomId, String timezone) {
    ScheduleDto sche = new ScheduleDto();
    // create time = db time 기준
    sche.setCdate(TimeUtil.getFormattedDate(new Date(), com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT));
    sche.setLocationId(locationId);
    sche.setRoomId(roomId);
    sche.setDeptName((String) scheduleObj.get(Constants.KEY_BODY_DEPT_NAME));
    sche.setSdate((String) scheduleObj.get(Constants.KEY_BODY_START_DATE) + "00");
    sche.setEdate((String) scheduleObj.get(Constants.KEY_BODY_END_DATE) + "00");
    sche.setScheduleId((String) scheduleObj.get(Constants.KEY_BODY_SCHEDULE_ID));

    sche.setLocalShhmm(TimeUtil.getHHmm(sche.getSdate(), com.lge.sm.cr_data_store.common.Constants.DEFAULT_DATE_FORMAT));
    sche.setLocalEhhmm(TimeUtil.getHHmm(sche.getEdate(), com.lge.sm.cr_data_store.common.Constants.DEFAULT_DATE_FORMAT));

    SimpleDateFormat f = new SimpleDateFormat(com.lge.sm.cr_data_store.common.Constants.DEFAULT_DATE_FORMAT);
    Date startDate = null;
    Date endDate = null;

    try {
      startDate = f.parse(sche.getSdate());
      endDate = f.parse(sche.getEdate());
    } catch (ParseException e) {
      startDate = TimeUtil.DEFAULT_DATE;
      endDate = TimeUtil.DEFAULT_DATE;
    }

    sche.setLocalDuration((int) (TimeUnit.MILLISECONDS.toMinutes(endDate.getTime() - startDate.getTime())));

    Calendar c = Calendar.getInstance();
    c.setTime(startDate);

    sche.setLocalDay(c.get(Calendar.DAY_OF_MONTH));
    sche.setLocalMonth(c.get(Calendar.MONTH) + 1);
    sche.setLocalYear(c.get(Calendar.YEAR));
    sche.setLocalDayOfWeek(c.get(Calendar.DAY_OF_WEEK));

    sche.setName((String) scheduleObj.get(Constants.KEY_BODY_SCHEDULE_NAME));
    sche.setUserId((String) scheduleObj.get(Constants.KEY_BODY_USER_ID));
    sche.setUserName((String) scheduleObj.get(Constants.KEY_BODY_USER_NAME));

    // if (ModelUtil.hasNullFields(sche) == true) {
    // Logger.error(TAG, "ScheduleVo has null field(s). Maybe Schedule table was
    // modified.");
    // }

    return sche;
  }
}