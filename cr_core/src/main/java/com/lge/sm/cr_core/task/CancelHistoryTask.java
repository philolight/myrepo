package com.lge.sm.cr_core.task;

import static com.lge.framework.ceasar.util.DateStringUtil.getCurrentDateString;
import static com.lge.framework.ceasar.util.DateStringUtil.getEndDate;
import static com.lge.framework.ceasar.util.DateStringUtil.getNextDate;
import static com.lge.framework.ceasar.util.DateStringUtil.getStartDate;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lge.framework.ceasar.entity.MapKey;
import com.lge.framework.ceasar.event.EventBroker;
import com.lge.framework.ceasar.event.event_kind.CreateEvent;
import com.lge.framework.ceasar.event.event_kind.DeleteEvent;
import com.lge.framework.ceasar.event.event_kind.UpdateEvent;
import com.lge.framework.ceasar.event.subscriber.EventSubscriber;
import com.lge.framework.ceasar.logger.Logger;
import com.lge.framework.ceasar.repository.Repos;
import com.lge.framework.ceasar.util.CsvUtil;
import com.lge.framework.ceasar.util.DateStringUtil;
import com.lge.framework.ceasar.util.FileUtil;
import com.lge.sm.cr_core.common.Constants;
import com.lge.sm.cr_core.common.ScheduleResult;
import com.lge.sm.cr_core.property_manager.PropertyManager;
import com.lge.sm.cr_data_store.dto.CancelHistoryDto;
import com.lge.sm.cr_data_store.dto.CancelHistoryDtoExample;
import com.lge.sm.cr_data_store.dto.ScheduleDtoExample;
import com.lge.sm.cr_data_store.entity.CancelHistoryEntity;
import com.lge.sm.cr_data_store.entity.LocationEntity;
import com.lge.sm.cr_data_store.entity.ScheduleEntity;
import com.lge.sm.cr_data_store.repository.CancelHistoryRepository;
import com.lge.sm.cr_data_store.repository.ScheduleRepository;

/**
 *  CancelHistoryTask는 시스템이 시작되었을 때 모든 Location에 대한 CancelHistory들이 어느 시점까지 계산되었는지 확인한다.
 *  그리고 마지막으로 계산된 CancelHistory 날짜 이후부터 현재 시점 기준으로 전날까지의 CancelHistory를 계산한다.
 *  만약 해당 Location에 대한 CancelHistory가 하나도 없으면  Schedule이 처음 저장된 날짜로부터 CancelHistory를 계산한다.
 *  만약 해당 Location의 CancelHistory도 없고 Schedule도 없으면 시작 시에는 아무 것도 계산하지 않는다.
 *  시작시 할 일들이 종료 되면 매 주기(30분)마다 어떤 Location이 현지 시각 기준으로 0시 0분인지 확인하고,(즉 하루가 바뀌었는지 확인하고)
 *  0시 0분인 Location들에 대해서 현재 시간 기준으로 어제의 CancelHistory를 계산한다.
 *  
 *  CancelHistoryDto의 dateOf 필드는 항상 리포트 대상 날짜의 CancelHistory의 0시 0분 0초이다.(CancelHistory를 계산한 시점이 아님을 주의할 것)
 */
public class CancelHistoryTask extends Task{
	private static final String TAG = CancelHistoryTask.class.getSimpleName();
	
	private ExecutorService executorService;

	private static long taskPeriod = 30 * 60 * 1000L; // ms
	
	protected Map<MapKey, LocationSucceed> locations = new ConcurrentHashMap<>();
	
	// 새로운 Location이 생성되었을 경우 해당 Location에 대한 CancelHistory도 매일 생성해 주어야 한다.
	private EventSubscriber<CreateEvent<LocationEntity>> locationCreateSubscriber = new EventSubscriber<CreateEvent<LocationEntity>>() {
		@Override
		public void subscribe(CreateEvent<LocationEntity> event) {
			LocationEntity newLocation = event.getTarget();
			if(locations.containsKey(newLocation.mapKey())) return;
			locations.put(newLocation.mapKey(), new LocationSucceed(newLocation));
		}
	};
	
	// Location 정보가 변경되었을 경우 Location 계산을 위한 정보도 변경해 주어야 한다.(예를 들어 TimeZoneId가 바뀌었을 경우)
	private EventSubscriber<UpdateEvent<LocationEntity>> locationUpdateSubscriber = new EventSubscriber<UpdateEvent<LocationEntity>>() {
		@Override
		public void subscribe(UpdateEvent<LocationEntity> event) {
			LocationEntity newLocation = event.getTarget();
			locations.put(newLocation.mapKey(), new LocationSucceed(newLocation));
		}		
	};
	
	// Location 정보가 삭제되었을 경우 CancelHistory 정보 생성도 중지해야 한다.
	private EventSubscriber<DeleteEvent<LocationEntity>> locationDeleteSubscriber = new EventSubscriber<DeleteEvent<LocationEntity>>() {
		@Override
		public void subscribe(DeleteEvent<LocationEntity> event) {
			LocationEntity newLocation = event.getTarget();
			try{ locations.remove(newLocation.mapKey()); } catch(Exception e) {/* no thing */}
		}		
	};

	public CancelHistoryTask(List<LocationEntity> locations){
		super();
		
		// 관리 대상인 Location을 Map에 등록한다. Map을 통해 Location 관련 이벤트 발생시 빠르게 대처한다.
		for(LocationEntity each : locations) this.locations.put(each.mapKey(), new LocationSucceed(each));
	}

	// init()은 Task가 생성되고 바로 호출된다. 
	@Override
	public void init() {
		// Event 구독자 등록
		EventBroker.subscribe(LocationEntity.class, CreateEvent.class, locationCreateSubscriber);
		EventBroker.subscribe(LocationEntity.class, UpdateEvent.class, locationUpdateSubscriber);
		EventBroker.subscribe(LocationEntity.class, DeleteEvent.class, locationDeleteSubscriber);
		
		start(); // 현재는 start()를 호출하여 바로 동작을 시작한다.
	}
	
	// 주기적으로 CancelHistory를 생성하도록 쓰레드를 만들어 실행시킨다.
	public void start() {
		executorService = Executors.newSingleThreadExecutor();
		executorService.execute(new RunThread(this));		
	}
	
	// 동작 중인 쓰레드를 종료시킨다.
	public void stop() {
		executorService.shutdownNow();
	}
	
	// 시스템이 새로 시작 되었거나, Task가 중지(stop)되었다가 다시 시작(start) 되었을 때 실행해야 할 메소드.
	protected void onStart() {
//System.out.println("onStart()");
		for(LocationSucceed pair : locations.values())			
			calculateCancelHistory(pair);
	}
	
	private void calculateCancelHistory(LocationSucceed pair) {
		LocationEntity location = pair.location;
		
		String dateToMakeHistory;
		
		dateToMakeHistory = getDateToMakeHistory(location);
		if(dateToMakeHistory == null) {
			// Database에 접근하는데 실패한 경우임.
			pair.succeed = false;
			return;
		}
		
		String todayStartTime = getTodayStartTime(location.getTimeZoneId());
		while(todayStartTime.equals(dateToMakeHistory) == false) {
			ScheduleRepository scheRepo = Repos.repo(ScheduleRepository.class);
			List<ScheduleEntity> scheduleEntities = scheRepo.getByCriteria(scheRepo.criteria().andLocationIdEqualTo(location.getLocationId()).andCdateBetween(getStartDate(dateToMakeHistory), getEndDate(dateToMakeHistory)));
			
			CancelHistoryDto cancelHistoryDto = newCancelHistoryDtoBySchedule(scheduleEntities, dateToMakeHistory, location);
			Repos.repo(CancelHistoryRepository.class).create(cancelHistoryDto); // CancelHistoryRepository를 통해서 cancelHistory를 저장한다.
						
			dateToMakeHistory = getStartDate(DateStringUtil.getNextDate(dateToMakeHistory));
		}
		
		pair.succeed = true;
	}
	
	protected CancelHistoryDto newCancelHistoryDtoBySchedule(List<ScheduleEntity> scheduleEntities, String dateOf, LocationEntity location) {
		// CancelHistory 초기화
		CancelHistoryDto dto = new CancelHistoryDto();
		dto.setDateOf(getStartDate(dateOf));
		dto.setCancelMinutes(0);
		dto.setCancelRate(0.0f);
		dto.setCancels(0);
		dto.setCdate(getCurrentDateString(location.getTimeZoneId()));
		dto.setLocationId(location.getLocationId());
		dto.setReservations(0);
						
		// CancelHistory 통계 계산
		for(ScheduleEntity each : scheduleEntities) {
			ScheduleResult result = ScheduleResult.getScheduleResult(each.getResult());
			switch(result) {
			case RESULT_INVALID_BY_TIMEOUT :
				dto.setReservations(dto.getReservations() + 1);
				dto.setCancels(dto.getCancels() + 1);
				dto.setCancelMinutes(dto.getCancelMinutes() + each.getLocalDuration() - each.getChkDuration());
				break;
			case RESULT_INVALID_BY_USER_CANCLE :
				break;
			case RESULT_VALID_BY_ALREADY_HANDLED :
				break;
			case RESULT_VALID_BY_SENSED_TO_USE :
				dto.setReservations(dto.getReservations() + 1);
				break;
			case RESULT_VALID_BY_TERMINATED_SUDDENLY :
				dto.setReservations(dto.getReservations() + 1);
				break;
			case RESULT_VALID_BY_CHANGED_PROPERTY_VALUE :
				dto.setReservations(dto.getReservations() + 1);
				break;
			case RESULT_VALID_BY_EXCEED_ERROR :
				dto.setReservations(dto.getReservations() + 1);
				break;
			}
		}
		
		if(dto.getReservations() != 0) dto.setCancelRate((float)(100.0f * dto.getCancels()) / (float)dto.getReservations());
		
		dto.setReuses(reuses(scheduleEntities));
				
		if(makeCancelListFile(scheduleEntities, location.getLocationId()) == false) Logger.error(TAG, "Failed to make cancel report file.");

		return dto;
	}
	
	protected int reuses(List<ScheduleEntity> entities) {
		
		// Entity들을 Sdate(회의 시작 시간) 기준으로 정렬.
		entities.sort(new Comparator<ScheduleEntity>() {
			@Override
			public int compare(ScheduleEntity o1, ScheduleEntity o2) {
				return o1.getSdate().compareTo(o2.getSdate());
			}					
		});
		
		int reuses = 0;
		
		for(int i = 0; i < entities.size(); i++) {
			ScheduleEntity entity = entities.get(i);
			if(entity.getResult() != ScheduleResult.RESULT_INVALID_BY_TIMEOUT.getValue()) continue;
			for(int j = i + 1; j < entities.size(); j++) {
				ScheduleEntity target = entities.get(j);
				// 어떤 회의의 시작 시각이 취소된 회의의 시작 시각과 종료 시각 사이에 있다면 reuse 개수를 증가 시킨다. 
				if(entity.getLocationId().equals(target.getLocationId()) &&
						entity.getRoomId().equals(target.getRoomId()) &&
						target.getSdate().compareTo(entity.getSdate()) >= 0 && 
						target.getSdate().compareTo(entity.getEdate()) <= 0) reuses++;
			}
		}
		
		return reuses;
	}
	
	protected boolean makeCancelListFile(List<ScheduleEntity> entities, String locationId) {
		String folder = "./" + PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.folder", null);
		String extension = PropertyManager.getPropertyManager().get(Constants.CR_CORE_CONFIG_FILE_NAME, "cr.daily-report.extension", null);
		String fileName = locationId + "." + extension;
		
		boolean fileCreated = FileUtil.createFileIfNotExist(folder, fileName);
		String fullPathFileName = folder + "/" + fileName;
		BufferedWriter fw = FileUtil.getCsvFileBufferedWriter(fullPathFileName);
		
		if(fw == null) {
			Logger.error(TAG, "Cannot open File : " + fullPathFileName);
			return false;
		}

		try {
			if(fileCreated == true) { // CSV 파일이 새로 만들어졌다면 컬럼 이름을 추가한다.
				fw.write(
						"Date" + "," +
						"RoomId" +"," +
						"ScheduleId" +"," +
						"StartDateTime" + "," +
						"EndDateTime" + "," +
						"UserName" + "," +
						"DeptName" + "," +
						"Title" + "," +
						"CanceledTime" + "," + 
						"TotalSensor" + "," +
						"TotalDetect" + "\r\n"
						);								
			}

			for(ScheduleEntity each : entities) {
				ScheduleResult result = ScheduleResult.getScheduleResult(each.getResult());
				if(!result.equals(ScheduleResult.RESULT_INVALID_BY_TIMEOUT)) continue;
				fw.write(
						CsvUtil.asColumn(getStartDate(each.getSdate())) + "," +
						CsvUtil.asColumn(each.getRoomId()) +"," +
						CsvUtil.asColumn(each.getScheduleId()) +"," +
						CsvUtil.asColumn(each.getSdate()) + "," +
						CsvUtil.asColumn(each.getEdate()) + "," +
						CsvUtil.asColumn(each.getUserName()) + "," +
						CsvUtil.asColumn(each.getDeptName()) + "," +
						CsvUtil.asColumn(each.getName()) + "," +
						CsvUtil.asColumn(each.getLocalShhmm().substring(0, 2) + ":" + String.format("%02d", each.getChkDuration())) + "," + 
						each.getTotalSensor() + "," +
						each.getTotalDetect() + "\r\n"
						);
			}
			
			fw.flush();
			fw.close();
		} catch (IOException e) {
			Logger.error(TAG, "Cannot write File : " + fullPathFileName);
			return false;
		}
		
		return true;
	}
	
	private String getDateToMakeHistory(LocationEntity location){
		String dateString = getStartDate(getLastDateOfCancelHistory(location));
		if(dateString != null) dateString = getNextDate(dateString); // 마지막 CancelHistoryEntity의 다음 날
		else dateString = getLastDateOfSchedule(location); 
		return getStartDate(dateString);
	}

	private String getLastDateOfCancelHistory(LocationEntity location){
		CancelHistoryRepository repo = Repos.repo(CancelHistoryRepository.class);		
		CancelHistoryDtoExample example = repo.example();
		
		example.setOrderByClause("date_of desc limit 1");
		example.or().andLocationIdEqualTo(location.getLocationId());

		List<CancelHistoryEntity> list = repo.getByExample(example);

		if(list == null) return null;

		if(list.size() == 1) return getStartDate(list.get(0).getDateOf());
		
		return null; // no cancelHistory
	}

	private String getLastDateOfSchedule(LocationEntity location){
		ScheduleRepository repo = Repos.repo(ScheduleRepository.class);		
		ScheduleDtoExample example = repo.example();		
		example.setOrderByClause("cdate asc limit 1");
		example.or().andLocationIdEqualTo(location.getLocationId());

		List<ScheduleEntity> list = repo.getByExample(example);

		if(list == null) return null;

		if(list.size() == 1) return getStartDate(list.get(0).getSdate());

		// 마지막 CancelHistory 계산 날짜를 알아낼 근거(CancelHistory, Schedule)가 없을 경우 오늘 날짜를 리턴한다. 
		return getCurrentDateString(location.getTimeZoneId());
	}

	@Override
	public String getWatchableId() {
		return this.getClass().getSimpleName();
	}

	@Override
	public String getModifiedTime() {
		return "20171222000000";
	}

	@Override
	protected void runTask() {
		for(LocationSucceed pair : locations.values()) {			
			// 이전에 CancelHistory 생성이 성공했고, 날짜가 바뀌지 않았다면 CancelHistory를 계산하지 않는다.
			if(pair.succeed && !timeChecker.dateChanged(pair.location.getTimeZoneId())) continue;
			
			calculateCancelHistory(pair);
		}
	}
	
	// 주기적으로 각 Location에 대한 CancelHistory를 계산하기 위해 동작하는 쓰레드.
	class RunThread extends Thread{
		private ITask task;
		RunThread(ITask task){ this.task = task; }
		
		@Override
		public void run() {
			onStart();
			
			while(true) {
				try {
					Thread.sleep( untilNextOnTime() );
				} catch (InterruptedException e) { /* do nothing */ }
				
				task.run();
			}
		}
		
		private long untilNextOnTime() {
			Date date = new Date();
			return taskPeriod() - (date.getTime() % taskPeriod());
		}
	}
	
	// Location에 대한 CancelHistory가 성공했는지 여부를 저장하는 객체. 
	class LocationSucceed{
		boolean succeed; // 이전 CancelHistory 계산이 성공한 Location일 경우 true, 아니면 false.
		LocationEntity location;
		public LocationSucceed(LocationEntity location, boolean succeed){
			this.location = location;
			this.succeed = succeed;
		}
		
		public LocationSucceed(LocationEntity location){ // 초기화될 때는 succeed는 false임
			this(location, false);
		}
	}
	
	protected TimeChecker timeChecker = new TimeChecker();
	
	protected void setTimeChecker(TimeChecker generator) {
		this.timeChecker = generator;
	}
	
	protected String getTodayStartTime(String timeZoneId) {
		return timeChecker.generate(timeZoneId);
	}
		
	protected synchronized long taskPeriod() {
		return taskPeriod;
	}
	
	protected synchronized void setTaskPeriod(long period) {
		taskPeriod = period;
	}
}

class TimeChecker{
	public String generate(String timeZoneId) {
		return getStartDate(getCurrentDateString(timeZoneId));
	}
	
	public boolean dateChanged(String timeZoneId) {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId));
		return (c.get(Calendar.HOUR_OF_DAY) == 0 && c.get(Calendar.MINUTE) < 5);
	}
}