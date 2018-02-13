package com.lge.framework.ceasar.watchable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lge.framework.ceasar.util.TimeUtil;
import com.lge.sm.cr_core.common.Constants;

abstract public class AbstractWatchable implements Watchable{
	private long runCount = 0;
	private String timeZoneId = "GMT+9:00";
	
	protected WatchableType watchableType = WatchableType.TASK;
	protected List<Watchable> kids = new ArrayList<>();
	protected Date lastStartTime = TimeUtil.DEFAULT_DATE;
	protected Date startTime = TimeUtil.DEFAULT_DATE;
	protected Date endTime = TimeUtil.DEFAULT_DATE;
	protected long period = -1;
	protected long takenTime = -1;
	protected String upperWatchableId = "";
	
	public AbstractWatchable() {
		Watcher.add(this);
	}

	
	@Override
	public String getUpperWatchableId() { return upperWatchableId;}

	@Override
	public void setUpperWatchableId(String upperWatchableId) { this.upperWatchableId = upperWatchableId;}
	
	@Override
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	@Override
	public String getTimeZoneId() {
		return timeZoneId;
	}
	
	@Override
	public String getLastStartTime() {
		return TimeUtil.dateToString(startTime, timeZoneId, Constants.DISPLAY_DATE_FORMAT);
	}

	@Override
	public String getLastLatency() {
		return utcLongToString(takenTime);
	}

	@Override
	public long getRunCount() {
		return runCount;
	}
	
	public List<Watchable> getKids(){
		return kids;
	}
	
	public WatchableType getWatchableType() {
		return watchableType;
	}
	
	@Override
	public String getPeriod() {
		return utcLongToString(period);
	}
	
	@Override
	public void runBegin() {
		lastStartTime = startTime;
		startTime = new Date();
		period = startTime.getTime() - lastStartTime.getTime();
	}

	@Override
	public void runEnd() {
		endTime = new Date();
		takenTime = endTime.getTime() - startTime.getTime();
	}
	
	/**
	 * utcLong (ms)으로 계산된 시간 값을 문자열로 변환하는 메소드.
	 * Format : "HH:mm:ss.mmm(sec)"
	 **/
	private String utcLongToString(long value) {
		String ret = "";
		long second = value / 1000;
		ret = "." + String.format("%03d(sec)", (period % 1000));		
		if(second > 59) {
			long minute = second / 60;
			ret = String.format(":%02d", (second % 60)) + ret;
			if(minute > 59) {
				long hour = minute / 60;
				ret = String.format("%d", hour) + ret;
			}
			else ret = minute + ret;
		}
		else ret = second + ret;
		
		return ret;
	}
	
	public void run() {
		runCount++;
		runBegin();
		actualRun();
		runEnd();
	}
	
	abstract protected void actualRun();
	
	protected WatchableStatus watchableStatus = WatchableStatus.NORMAL;
	protected String faultCause = "";
	protected Date faultDate = TimeUtil.DEFAULT_DATE;
	public WatchableStatus getWatchableStatus() { return watchableStatus; }
	public String getFaultCause() { return faultCause; }
	public String faultTime() { return TimeUtil.dateToString(faultDate, timeZoneId, Constants.DISPLAY_DATE_FORMAT); }
	
	public void setFault(String cause) {
		watchableStatus = WatchableStatus.FAULT;
		faultDate = new Date();
		faultCause = cause;
	}
	
	public void setNormal() {
		watchableStatus = WatchableStatus.NORMAL;
		faultDate = new Date();
		faultCause = "";
	}
}