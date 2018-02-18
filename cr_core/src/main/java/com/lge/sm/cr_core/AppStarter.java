package com.lge.sm.cr_core;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.lge.framework.ceasar.logger.LogLevel;
import com.lge.framework.ceasar.logger.Logger;
import com.lge.framework.ceasar.startable.Starter;
import com.lge.sm.cr_core.cr_task_manager.CrTaskManager;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.dao.CancelHistoryDao;
import com.lge.sm.cr_data_store.dao.LocationDao;
import com.lge.sm.cr_data_store.dao.RoomDao;
import com.lge.sm.cr_data_store.dao.RoomSensorDao;
import com.lge.sm.cr_data_store.dao.ScheduleDao;
import com.lge.sm.cr_data_store.dao.SensorDao;
import com.lge.sm.cr_data_store.dao.SlmDao;

@Component
public class AppStarter {
	private static final String TAG = AppStarter.class.getSimpleName();
	
	@Autowired private ApplicationContext ac;

	@PostConstruct
	public void init(){
		Logger.init("log", LogLevel.DEBUG);

		Logger.debug(TAG, "cr_core start");
		  
//		linkSpringContext();
//
//		if (CrTaskManager.getInstance().start()) {
//			Logger.debug(TAG, "CrTaskManager start");
//		} else {
//			Logger.error(TAG, "The initialization of CrTaskManager is failed.");
//		}

		Starter starter = new Starter();
		if(starter.init() == false) {
			Logger.error(TAG, "Starter init() failed.");
		}

		if(starter.start() == false) {
			Logger.error(TAG, "Starter start() failed.");
		}
		
//		try {
//			FileWriter fw = new FileWriter(new File("skinnerMap.json"));
//			fw.append(SkinnerManager.getSkinTypeMap());
//			System.out.println("file write done");
//			fw.close();
//			
//			fw = new FileWriter(new File("skinnerList.json"));
//			fw.append(SkinnerManager.getSkinTypeList());
//			System.out.println("file write done");
//			fw.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		List<String> list = Arrays.asList("User","Authority","PartyUser","NumericRange","PartyAuthority","Startable","Party","StartableHistory","EventHistory","RoomSensor","Slm","ServiceAuthority","Room","Script","EnumFacet","CancelHistory","Service","AuthorityLocation","FieldSkin","StringRange","Watchable","WatchableHistory","DecimalRange","Schedule","Skin","UserAuthority","Person","Location","Sensor");
//		for(String each : list) {
//			FileWriter fw;
//			try {
//				fw = new FileWriter(new File(each + ".json"));
//				fw.append(SkinnerManager.getSkinizedAll(each));
//				System.out.println("file write done : " + each);
//				fw.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
	}

	protected void linkSpringContext() {
		System.out.println(ac);
		
		LocationDao locationDao = (LocationDao) ac.getBean(LocationDao.class);
		RoomDao roomDao = (RoomDao) ac.getBean(RoomDao.class);
		RoomSensorDao roomSensorDao = (RoomSensorDao) ac.getBean(RoomSensorDao.class);
		ScheduleDao scheduleDao = (ScheduleDao) ac.getBean(ScheduleDao.class);
		SensorDao sensorDao = (SensorDao) ac.getBean(SensorDao.class);
		SlmDao slmDao = (SlmDao) ac.getBean(SlmDao.class);
		CancelHistoryDao cancelHistoryDao = (CancelHistoryDao) ac.getBean(CancelHistoryDao.class);

		DataStore ds = DataStore.getInstance();
		ds.setLocationDao(locationDao);
		ds.setRoomDao(roomDao);
		ds.setRoomSensorDao(roomSensorDao);
		ds.setScheduleDao(scheduleDao);
		ds.setSensorDao(sensorDao);
		ds.setSlmDao(slmDao);
		ds.setDailyReportDao(cancelHistoryDao);
	}
}
