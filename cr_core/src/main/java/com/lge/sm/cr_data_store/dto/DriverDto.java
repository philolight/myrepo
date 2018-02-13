package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class DriverDto implements Serializable {
	private static final long serialVersionUID = 1518229923746L;
    private Long driverId;

    private Integer enable;

    private Integer pingEnable;

    private Integer alarmEnable;

    private Long pingFrequency;

    private String name;

    private String driverTypeId;

    private Integer pollEnable;

    private String cdate;

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getPingEnable() {
        return pingEnable;
    }

    public void setPingEnable(Integer pingEnable) {
        this.pingEnable = pingEnable;
    }

    public Integer getAlarmEnable() {
        return alarmEnable;
    }

    public void setAlarmEnable(Integer alarmEnable) {
        this.alarmEnable = alarmEnable;
    }

    public Long getPingFrequency() {
        return pingFrequency;
    }

    public void setPingFrequency(Long pingFrequency) {
        this.pingFrequency = pingFrequency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDriverTypeId() {
        return driverTypeId;
    }

    public void setDriverTypeId(String driverTypeId) {
        this.driverTypeId = driverTypeId;
    }

    public Integer getPollEnable() {
        return pollEnable;
    }

    public void setPollEnable(Integer pollEnable) {
        this.pollEnable = pollEnable;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
