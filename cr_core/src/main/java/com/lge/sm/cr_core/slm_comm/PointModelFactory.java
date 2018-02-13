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

import com.lge.sm.cr_core.slm_comm.inf.IPoint;
import com.lge.sm.cr_core.slm_comm.model.PointDeviceState;
import com.lge.sm.cr_core.slm_comm.model.PointDimming;
import com.lge.sm.cr_core.slm_comm.model.PointDimmingSet;
import com.lge.sm.cr_core.slm_comm.model.PointGatewayState;
import com.lge.sm.cr_core.slm_comm.model.PointGroupControlAlarm;
import com.lge.sm.cr_core.slm_comm.model.PointLux;
import com.lge.sm.cr_core.slm_comm.model.PointOccupancy;
import com.lge.sm.cr_core.slm_comm.model.PointOnOff;
import com.lge.sm.cr_core.slm_comm.model.PointOnOffSet;

public class PointModelFactory {
  
  public static IPoint getPointModel(String typeString, String value) {
    IPoint ret = null;
    if (typeString != null) {
      if (SlmPointType.POINT_ON_OFF.getTypeString().equals(typeString)) {
        Integer convertValue = Integer.parseInt(value.trim());        
        ret = new PointOnOff(convertValue);        
      } else if (SlmPointType.POINT_DIMMING.getTypeString().equals(typeString)) {
        Long convertValue = Long.parseLong(value);
        ret = new PointDimming(convertValue);
      } else if (SlmPointType.POINT_DEVICE_STATE.getTypeString().equals(typeString)) {
        ret = new PointDeviceState(value);
      } else if (SlmPointType.POINT_LUX.getTypeString().equals(typeString)) {
        Long convertValue = Long.parseLong(value);
        ret = new PointLux(convertValue);
      } else if (SlmPointType.POINT_OCCUPANCY.getTypeString().equals(typeString)) {
        Integer convertValue = Integer.parseInt(value.trim()); 
        ret = new PointOccupancy(convertValue);      
      } else if (SlmPointType.POINT_GATEWAY_STATE.getTypeString().equals(typeString)) {
        ret = new PointGatewayState(value);
      } else if (SlmPointType.POINT_ON_OFF_SET.getTypeString().equals(typeString)) {
        Integer convertValue = Integer.parseInt(value.trim());        
        ret = new PointOnOffSet(convertValue);        
      } else if (SlmPointType.POINT_DIMMING_SET.getTypeString().equals(typeString)) {
        Long convertValue = Long.parseLong(value);
        ret = new PointDimmingSet(convertValue);
      } else if (SlmPointType.POINT_GROUP_CONTROL_ALARM.getTypeString().equals(typeString)) {
        Integer convertValue = Integer.parseInt(value.trim());        
        ret = new PointGroupControlAlarm(convertValue);
      }
    }
    
    return ret;
  }
}
