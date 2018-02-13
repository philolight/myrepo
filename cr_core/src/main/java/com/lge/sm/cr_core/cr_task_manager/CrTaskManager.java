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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import com.lge.framework.ceasar.util.ModelUtil;
import com.lge.framework.pacific.command_handler.CommandQueue;
import com.lge.framework.pacific.common.thread.ManagedThreadFactory;
import com.lge.framework.pacific.common.thread.ManagedThreadFactory.ManagedThread;
import com.lge.framework.pacific.logger.Logger;
import com.lge.framework.pacific.propman.inf.IConfigManagerListener;
import com.lge.sm.cr_core.common.CommandType;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.cr_system_comm.CrSystemCommunicator;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_core.reservation_checker.CrReservChecker;
import com.lge.sm.cr_core.slm_comm.SlmCommunicator;
import com.lge.sm.cr_core.util.SensorValueWriter;
import com.lge.sm.cr_core.util.UtilConstants;
import com.lge.sm.cr_data_store.DataStore;
import com.lge.sm.cr_data_store.IChangedDataListener;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.SlmDto;

public class CrTaskManager implements IConfigManagerListener {
	private static final String TAG = CrTaskManager.class.getSimpleName();

	private static final CrTaskManager instance = new CrTaskManager();

	private HashMap<Long, ManagedThread> threadMap = new HashMap<>();	
	private Timer scheduleCleanTimer = null;

	private PropertyLoader propertyLoader = new PropertyLoader();

	private IChangedDataListener<RoomDto> roomListener = new IChangedDataListener<RoomDto>() {
		@Override
		public void onChangedData(List<RoomDto> changedList) { // TODO : changedList를 사용하지 않고 stop()과 start()만 호출하고 있음.
			stop();
			start();
		}
	};

	private IChangedDataListener<SlmDto> slmListener = new IChangedDataListener<SlmDto>() {
		@Override
		public void onChangedData(List<SlmDto> changedList) {
			SlmCommunicator.getInstance().stop();
			initializeSlmCommunicator();
		}
	};

	private CrScheduleMapCleaner cleaner = new CrScheduleMapCleaner() {
		@Override
		public void run() {
			Logger.debug(TAG, "##### The schedule map is clearing.");
			stop();
			start();
		}
	};

	public static CrTaskManager getInstance() {
		return instance;
	}

	public boolean start() { // TODO : stop() / start()를 런타임 중간에 자주 호출하게 될 것이라면 이 함수에서는 새로 start()를 호출할 때 다시 실행해야 할 코드들만 넣어야 함.
		boolean ret = true;

		// 외부 파일로부터 사용할 Property들을 읽어온다.
		propertyLoader.initializePropertyManager();

		// CrSystemCommunicator(회의예약시스템과 통신)를 start한다.
		initializeCrSystemCommunicator();

		// SlmCommunicator를 start한다.
		initializeSlmCommunicator();
		
		Logger.info(TAG, "initializeSlmCommunicator() done. Room DB read start.");
		
		// start thread for each location-room and initialize CrManagedScheduleMap
		List<RoomDto> roomList = new ArrayList<RoomDto>();
		for (LocationDto location : DataStore.getInstance().getLocationList()) {
			roomList.addAll(DataStore.getInstance().getRoomList(location.getLocationId()));
		}
		
		Logger.info(TAG, "initializeSlmCommunicator() done. Room DB read done.");
		Logger.info(TAG, "initializeSlmCommunicator() done. Location DB read start.");

		List<LocationDto> locList = DataStore.getInstance().getLocationList(roomList);
		if (roomList != null && locList != null) {
			ret = makeAndStartThreads(roomList, locList, propertyLoader.getPropertiesFromExternalPropertyFile());
		} else {
			Logger.error(TAG, "The room id is not exist. Please check DB file about room id.");
			return false;
		}
		
		Logger.info(TAG, "initializeSlmCommunicator() done. Location DB read done.");

		initializeCommandQueue(roomList.size());

		DataStore.getInstance().addChangedDataListenerToRoom(roomListener);
		DataStore.getInstance().addChangedDataListenerToSlm(slmListener);

		SensorValueWriter.getInstance().start(UtilConstants.CANCEL_SCHEDULE_LOG_FILE_NAME, "log");
		
		Logger.info(TAG, "CrTaskManager.start() done.");

		return ret;
	}

	private boolean initializeCrSystemCommunicator() {
		boolean ret = false;

		PropertyManager propMan = PropertyManager.getPropertyManager();
		if (propMan != null) {
			propMan.setListener(this);
			String crSystemHostName = propMan.get(Constants.CR_CORE_CONFIG_FILE_NAME,
					Constants.CR_CORE_CONFIG_CR_SYSTEM_HOST_NAME_KEY, null);
			if (CrSystemCommunicator.getInstance().start(crSystemHostName) != null) {
				ret = true;
			}
		}

		return ret;
	}

	private boolean initializeSlmCommunicator() {
		boolean ret = false;

		/*		LocationRepository locationRepo = null;
		RoomRepository roomRepo = null;
		SlmRepository slmRepo = null;

		List<SlmEntity> slmList = new ArrayList<>();
		List<LocationEntity> locations = locationRepo.getAll();
		for(LocationEntity location : locations) {
			List<RoomEntity> rooms = roomRepo.getByLocationId(location.getLocationId());
			for(RoomEntity room : rooms) {
				List<RoomSensorEntity> roomSensors = room.getRoomSensorEntityList();
				for(RoomSensorEntity roomSensor : roomSensors) {
					slmList.add(roomSensor.getSensorEntity().getSlmEntity());
				}
			}
		}

		List<String> slmUrlList = new ArrayList<>();

		for(SlmEntity slm : slmList) {
			slmUrlList.add(slm.getSlmPath());
		}*/

		List<SlmDto> slmIdList = DataStore.getInstance().getSlmList();
		if (slmIdList != null) {
			List<String> urlList = new ArrayList<>();
			for (SlmDto slm : slmIdList) {
				if (slm != null) {
					SlmDto fullSlm = DataStore.getInstance().getSlm(slm.getSlmId());
					if (fullSlm != null) {
						urlList.add(ModelUtil.getSlmPath(fullSlm));
					}
				}
			}

			if (urlList != null && urlList.size() > 0) {
				SlmCommunicator.getInstance().start(urlList);
				ret = true;
			}
		}

		return ret;
	}

	private void cleanSchedule() {
		scheduleCleanTimer = new Timer();

		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.HOUR_OF_DAY, 0); // real code
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		Logger.debug(TAG, "today date : " + startCal.getTime());

		startCal.add(Calendar.DAY_OF_MONTH, 1);
		Logger.debug(TAG, "cleaner start date : " + startCal.getTime());

		if (scheduleCleanTimer != null) {
			scheduleCleanTimer.scheduleAtFixedRate(cleaner, startCal.getTime(), TimeUnit.MILLISECONDS
					.convert(com.lge.sm.cr_core.cr_task_manager.Constants.DEFAULT_CLEAR_PERIOD_HOUR, TimeUnit.HOURS));
		}
	}

	private void initializeCommandQueue(int roomNum) {
		CommandQueue.getInstance().setCorePoolSize(roomNum)
		.registerCommandAndRunnable(CommandType.CR_COMMAND_RESERVATION_CHECK.getTypeString(), new CrReservChecker())
		.start();
	}

	private boolean makeAndStartThreads(List<RoomDto> roomList, List<LocationDto> locList, CrTaskConfigModel configModel) {
		Logger.debug(TAG, "external_property_path : " + propertyLoader.getExternalPropertyPath());

		if (roomList != null) {
			for (int i = 0; i < roomList.size(); i++) {
				RoomDto room = roomList.get(i);
				LocationDto loc = locList.get(i);
				try {
					CrTaskInRoom runnable = new CrTaskInRoom(
							DataStore.getInstance().getRoom(room.getLocationId(), room.getRoomId()), loc, configModel); // TODO
					// :
					// Ȯ��
					ManagedThread threadAsRoomId = ManagedThreadFactory.getInstance().getThread(runnable);
					if (threadAsRoomId != null && runnable != null) {
						long sessionId = threadAsRoomId.getId();
						runnable.setSessionId(sessionId);
						threadAsRoomId.start();
						threadMap.put(sessionId, threadAsRoomId);

						CrManagedScheduleMap.getInstance().makeMapAsSessionId(sessionId);

						SensorValueWriter.getInstance().start(room.getRoomId(), "log");

					}
				} catch (InstantiationException e) {
					Logger.error(TAG, "This Room Instance is null. So Thread in this room will be not maden. ");
					continue;
				}
			}
		} else {
			Logger.error(TAG, "The room id is not exist. Please check DB file about room id.");
			return false;
		}
		return true;
	}

	public void stop() {
		if (threadMap != null) {
			for (ManagedThread thread : threadMap.values()) {
				thread.terminate();
				thread = null;
			}
			threadMap.clear();
		}

		CommandQueue.getInstance().stop();

		DataStore.getInstance().removeChangedDataListenerFromRoom(roomListener);
		DataStore.getInstance().removeChangedDataListenerFromSlm(slmListener);
		DataStore.getInstance().stop();

		CrSystemCommunicator.getInstance().stop();

		if (scheduleCleanTimer != null) {
			scheduleCleanTimer.cancel();
			scheduleCleanTimer = null;
		}
	}

	@Override
	public void changedConfigValue(HashMap<String, String> changedKeyValueMap) {
		stop();
		start();
	}
}
