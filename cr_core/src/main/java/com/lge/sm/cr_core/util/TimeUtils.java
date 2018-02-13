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

package com.lge.sm.cr_core.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class TimeUtils {
  private static final HashMap<TimeZone, SimpleDateFormat> timeZoneFormatterMap = new HashMap<>();
  
  public static long getUtcLongWithFormat(String dateString, String format, String timeZone) throws Exception {
    return getDateWithFormat(dateString, format, new ParsePosition(0), timeZone).getTime();
  }
  
  public static Date getDateWithFormat(String dateString, String format, String timeZone) throws Exception {
    return getDateWithFormat(dateString, format, new ParsePosition(0), timeZone);
  }
  
  public static Date getDateWithFormat(String dateString, String format, ParsePosition position, String timeZone)
      throws Exception {
    Date ret = null;
    if (dateString != null && format != null && position != null && position.getIndex() < dateString.length()) {
      SimpleDateFormat sdf = getSimpleDateFormat(timeZone);
      synchronized (sdf) {
        sdf.applyPattern(format);
        ret = sdf.parse(dateString, position);
      }
    }
    return ret;
  }
  
  private static SimpleDateFormat getSimpleDateFormat(String timeZone) {
    SimpleDateFormat ret = null;

    TimeZone tz = null;
    if (timeZone != null) {
      tz = TimeZone.getTimeZone(timeZone);
    }
    if (tz == null) {
      tz = TimeZone.getDefault();
    }

    ret = timeZoneFormatterMap.get(tz);

    if (ret == null) {
      ret = new SimpleDateFormat();
      ret.setTimeZone(tz);
      timeZoneFormatterMap.put(tz, ret);
    }

    return ret;
  }
    
  public static int compareDatesWithTimezone(Date srcDate, Date destDate) {
    int ret = -2;
    if (srcDate != null && destDate != null) {
      if (srcDate.getTime() > destDate.getTime()) {
        ret = -1;
      } else {
        ret = (srcDate.getTime() == destDate.getTime()) ? 0 : 1;
      }
    } 
    return ret;
  } 
}
