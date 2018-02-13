package com.lge.sm.cr_core.service.location_dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.service.AbstractService;
import com.lge.sm.cr_core.task.CancelHistoryNotificationTask;
import com.lge.sm.cr_core.task.CancelHistoryTask;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.repository.LocationRepository;

@Service
public class LocationDashboardService extends AbstractService{
	private static final String TAG = LocationDashboardService.class.getSimpleName();
	@Override public String getStartableId() { return TAG; }
	@Override public String getServiceId() { return TAG; }
		
	public LocationDashboardService() { super(); }
	
	@Override
	public boolean init() {
		return true;
	}
	
	@Override
	public boolean start() {
		List<LocationEntity> locations = Repos.repo(LocationRepository.class).getAll();
		CancelHistoryTask cht = new CancelHistoryTask(locations);
		cht.setUpperWatchableId(TAG);
		CancelHistoryNotificationTask chnt = new CancelHistoryNotificationTask();
		chnt.setUpperWatchableId(TAG);
		chnt.init();
		cht.init();
		return true;
	}

	@Override
	public boolean stop() {
		return true;
	}
	
}