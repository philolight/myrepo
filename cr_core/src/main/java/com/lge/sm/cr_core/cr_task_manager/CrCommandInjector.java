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

import com.lge.framework.ceasar.util.Factory;
import com.lge.framework.pacific.command_handler.CommandQueue;
import com.lge.framework.pacific.common.thread.DefaultManagedRunnable;
import com.lge.framework.pacific.logger.Logger;
import com.lge.sm.cr_core.cr_system_comm.CrSystemCommunicator;
import com.lge.sm.cr_core.model.CrTaskConfigModel;
import com.lge.sm.cr_core.reservation_checker.CrReservCommand;
import com.lge.sm.cr_core.reservation_checker.IDestroyedListener;
import com.lge.sm.cr_core.util.TimeUtils;
import com.lge.sm.cr_data_store.dto.LocationDto;
import com.lge.sm.cr_data_store.dto.RoomDto;
import com.lge.sm.cr_data_store.dto.ScheduleDto;

public class CrCommandInjector extends DefaultManagedRunnable {
	private static final String TAG = CrCommandInjector.class.getSimpleName();

	private long sessionId = 0;
	private RoomDto room = null;
	private LocationDto loc = null;
	private CrTaskConfigModel config = null;
	private ScheduleDto schedule = null;

	public CrCommandInjector(long sessionId, RoomDto room, LocationDto loc, CrTaskConfigModel config, ScheduleDto schedule)
			throws InstantiationException {				
		if (room != null && loc != null && config != null && schedule != null) {
			this.sessionId = sessionId;
			this.room = room;
			this.loc = loc;
			this.config = config;
			this.schedule = schedule;
		} else {
			throw new InstantiationException();
		}
	}

	protected void finalize() {
		room = null;
		loc = null;
		config = null;
	}

	@Override
	public void doRun() throws InterruptedException {
		try {
			long startDate = TimeUtils.getUtcLongWithFormat(schedule.getSdate(), com.lge.sm.cr_core.common.Constants.DEFAULT_DATE_FORMAT, loc.getTimeZoneId());
			long currentTime = System.currentTimeMillis();
			long delay = startDate - currentTime;

			Logger.debug(TAG,
					"schedule name : " + schedule.getName() + " , sessionId : " + sessionId + " , delay : " + delay);

			if (delay >= 0) {
				synchronized (this) {
					this.wait(delay); // real code
				}

				if (CrSystemCommunicator.getInstance().isExistScheduleId(room.getLocationId(), room.getRoomId(),
						loc.getTimeZoneId(), schedule.getScheduleId())) { // real code
					Logger.debug(TAG, "this schedule is injected to CommandQueue. " + " , schedule name : "
							+ schedule.getName() + " , sessionId : " + sessionId);

					CommandQueue.getInstance().injectCommand(new CrReservCommand(sessionId, Factory.deepCopy(room), config.clone(),
							Factory.deepCopy(schedule), Factory.deepCopy(loc), new IDestroyedListener() {
						@Override
						public void onDestroyed(long sessionId, String scheduleId) {
							CrManagedScheduleMap.getInstance().removeScheduleAndStopInjector(sessionId, scheduleId);
						}
					}));
				} else {
					Logger.debug(TAG, "This schedule is already handled. schedule name : " + schedule.getName());
				}
			} else {
			  Logger.debug(TAG, "This command injector is terminated by user cancle/change schedule info." + " , schedule name : "
            + schedule.getName() + " , sessionId : " + sessionId);
				return;
			}
		} catch (Exception e) {
		}
	}
}
