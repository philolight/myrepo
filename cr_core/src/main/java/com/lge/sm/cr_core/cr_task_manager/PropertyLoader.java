package com.lge.sm.cr_core.cr_task_manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_core.property_manager.PropertyManager;

public class PropertyLoader {
	private static final String TAG = PropertyLoader.class.getSimpleName();
	
	private String externalPropertyPath = System.getProperty("external_property_path");
	
	public String getExternalPropertyPath() { return externalPropertyPath; }
	
	public void initializePropertyManager() {
		if (externalPropertyPath == null) {
			externalPropertyPath = Constants.DEFAULT_EXTERNAL_PROPERTY_PATH;
			
			File resourcesDir = new File(Constants.DEFAULT_EXTERNAL_PROPERTY_PATH);
			if (!resourcesDir.exists()) {
				resourcesDir.mkdirs();
			}

			File file = new File(Constants.DEFAULT_EXTERNAL_PROPERTY_PATH + File.separator
					+ Constants.CR_CORE_CONFIG_FILE_NAME + ".properties");

			if (!file.exists()) {
				try {
					InputStream is = Thread.currentThread().getContextClassLoader()
							.getResourceAsStream(Constants.CR_CORE_CONFIG_FILE_NAME + ".properties");
					OutputStream os = new FileOutputStream(file);
					if (is != null && os != null) {
						int readBuffer = 0;
						byte[] buffer = new byte[1024];
						while ((readBuffer = is.read(buffer)) != -1) {
							os.write(buffer, 0, readBuffer);
						}
						is.close();
						os.close();
					}
				} catch (FileNotFoundException e) {
					Logger.error(TAG, "The FileNotFoundException is occurred while copying properties files." + e);
					return;
				} catch (IOException e) {
					Logger.error(TAG, "The FileNotFoundException is occurred while copying properties files." + e);
					return;
				}
			}
		}

		PropertyManager.getPropertyManager().initialize(externalPropertyPath);
	}
	
	public CrTaskConfigModel getPropertiesFromExternalPropertyFile() {
		return new CrTaskConfigModel(
				PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME,
						Constants.CR_CORE_CONFIG_WAKEUP_TIME_KEY, null),
				PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME,
						Constants.CR_CORE_CONFIG_ALIVE_TIME_KEY, null),
				PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME,
						Constants.CR_CORE_CONFIG_GET_SENSOR_PERIOD_KEY, null));
	}
}
