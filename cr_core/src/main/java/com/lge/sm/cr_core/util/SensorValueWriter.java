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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lge.framework.pacific.common.Utils;
import com.lge.framework.pacific.logger.Logger;

public class SensorValueWriter {
  private static final String TAG = SensorValueWriter.class.getSimpleName();
  private static final String DEFAULT_LOG_FILE_NAME = "sensor_values.log";

  private static final SensorValueWriter instance = new SensorValueWriter();

  private String directory = null;

  public static SensorValueWriter getInstance() {
    return instance;
  }

  public boolean start(String directory) {
    boolean ret = false;
    if (directory != null) {
      try {
        this.directory = directory;

        File resourcesDir = new File(directory);
        if (!resourcesDir.exists()) {
          resourcesDir.mkdirs();
        }

        File file = new File(Utils.getFilePath(directory, DEFAULT_LOG_FILE_NAME));
        FileWriter fw = new FileWriter(file, true);
        fw.close();

        ret = true;
      } catch (IOException e) {
      }
    }

    return ret;
  }

  public boolean start(String roomId, String directory) {
    boolean ret = false;
    if (roomId != null && directory != null) {
      try {
        this.directory = directory;

        File resourcesDir = new File(directory);
        if (!resourcesDir.exists()) {
          resourcesDir.mkdirs();
        }

        File file = new File(Utils.getFilePath(directory, roomId + DEFAULT_LOG_FILE_NAME));
        FileWriter fw = new FileWriter(file, true);
        fw.close();

        ret = true;
      } catch (IOException e) {
      }
    }

    return ret;
  }

  public void stop() {

  }

  public synchronized void write(String msg) {
    if (msg != null) {
      SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");      
      String fullMsg = new String(msg + "," + transFormat.format(new Date()) + "\r\n");

      try {
        File file = new File(Utils.getFilePath(this.directory, DEFAULT_LOG_FILE_NAME));
        FileWriter fw = new FileWriter(file, true);
        fw.write(fullMsg);
        fw.flush();
        fw.close();
      } catch (IOException e) {
        Logger.error(TAG, "IOException in write");
      }
    }
  }
  
  public synchronized void write(String roomId, String msg) {
    if (msg != null) {
      SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMddHHmmss");      
      String fullMsg = new String(msg + "," + transFormat.format(new Date()) + "\r\n");

      try {
        File file = new File(Utils.getFilePath(this.directory, roomId+DEFAULT_LOG_FILE_NAME));
        FileWriter fw = new FileWriter(file, true);
        fw.write(fullMsg);
        fw.flush();
        fw.close();
      } catch (IOException e) {
        Logger.error(TAG, "IOException in write");
      }
    }
  }

}
