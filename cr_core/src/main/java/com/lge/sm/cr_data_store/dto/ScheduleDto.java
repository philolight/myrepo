package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class ScheduleDto implements Serializable {
	private static final long serialVersionUID = 1518229923907L;
    private String scheduleId;

    private String roomId;

    private String name;

    private String userId;

    private String userName;

    private String deptName;

    private String sdate;

    private String edate;

    private Integer localYear;

    private Integer localMonth;

    private Integer localDay;

    private Integer localDayOfWeek;

    private String localShhmm;

    private String localEhhmm;

    private Integer localDuration;

    private Integer sensorCnt;

    private Integer totalSensor;

    private Integer totalDetect;

    private Integer chkDuration;

    private Integer result;

    private String cdate;

    private String locationId;

    private Integer errorCnt;

    private String resultDate;

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public Integer getLocalYear() {
        return localYear;
    }

    public void setLocalYear(Integer localYear) {
        this.localYear = localYear;
    }

    public Integer getLocalMonth() {
        return localMonth;
    }

    public void setLocalMonth(Integer localMonth) {
        this.localMonth = localMonth;
    }

    public Integer getLocalDay() {
        return localDay;
    }

    public void setLocalDay(Integer localDay) {
        this.localDay = localDay;
    }

    public Integer getLocalDayOfWeek() {
        return localDayOfWeek;
    }

    public void setLocalDayOfWeek(Integer localDayOfWeek) {
        this.localDayOfWeek = localDayOfWeek;
    }

    public String getLocalShhmm() {
        return localShhmm;
    }

    public void setLocalShhmm(String localShhmm) {
        this.localShhmm = localShhmm;
    }

    public String getLocalEhhmm() {
        return localEhhmm;
    }

    public void setLocalEhhmm(String localEhhmm) {
        this.localEhhmm = localEhhmm;
    }

    public Integer getLocalDuration() {
        return localDuration;
    }

    public void setLocalDuration(Integer localDuration) {
        this.localDuration = localDuration;
    }

    public Integer getSensorCnt() {
        return sensorCnt;
    }

    public void setSensorCnt(Integer sensorCnt) {
        this.sensorCnt = sensorCnt;
    }

    public Integer getTotalSensor() {
        return totalSensor;
    }

    public void setTotalSensor(Integer totalSensor) {
        this.totalSensor = totalSensor;
    }

    public Integer getTotalDetect() {
        return totalDetect;
    }

    public void setTotalDetect(Integer totalDetect) {
        this.totalDetect = totalDetect;
    }

    public Integer getChkDuration() {
        return chkDuration;
    }

    public void setChkDuration(Integer chkDuration) {
        this.chkDuration = chkDuration;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getErrorCnt() {
        return errorCnt;
    }

    public void setErrorCnt(Integer errorCnt) {
        this.errorCnt = errorCnt;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }
}
