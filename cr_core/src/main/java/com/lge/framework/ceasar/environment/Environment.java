package com.lge.framework.ceasar.environment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.mariadb.jdbc.internal.util.Utils;

import com.lge.framework.ceasar.logger.Logger;
import com.lge.framework.ceasar.startable.Startable;
import com.lge.framework.ceasar.startable.StartableStatus;
import com.lge.framework.ceasar.startable.StartableType;
import com.lge.framework.ceasar.startable.Starter;
import com.lge.sm.cr_data_store.DataStore;

public class Environment implements Startable{
	private static final String TAG = Environment.class.getSimpleName();
	
	// as a Startable ----------------------------------------------------------------------------------------------------------------------	
		
	static { Starter.add(new Environment()); }
	
	private StartableStatus startableStatus = StartableStatus.DEFAULT;	
	
	@Override public String getStartableId() { return this.getClass().getSimpleName(); }
	@Override public StartableType getStartableType() { return StartableType.FRAMEWORK_COMPONENT; }
	@Override public StartableStatus getStartableStatus() { return startableStatus; }
	@Override public void setStatus(StartableStatus status) { this.startableStatus = status; }

	@Override
	public boolean init() {
/*	    boolean isPackageMode = Utils.isClassInPackageFile(DataStore.class);
	    String targetDir = Utils.getDirPathOfClass(DataStore.class) + (isPackageMode ? "" : "../");
	    map.put("root", targetDir);*/
		return true;
	}

	@Override 
	public boolean start() {		
		return true; 
	}

	@Override 
	public boolean stop() {
		return true; 
	}
	
	// ---------------------------------------------------------------------------------------------------------------------------------------
	
	private static Map<String, String> map = new ConcurrentHashMap<>();
	
	public static String get(String key) {
		return map.get(key);
	}
	
	public static void set(String key, String value) {
		set(null, key, value);
	}
	
	public static void set(String parent, String key, String value) {
		if(map.containsKey(key)) {
			Logger.error(TAG, "Duplicated Environment Key.");
			return;
		}
		
		if(parent != null) value = map.get(parent) + "/" + value;
		if(value.endsWith("/") == false) value += "/";
		map.put(key, value);
	}
}
