package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class DriverHistoryDto implements Serializable {
	private static final long serialVersionUID = 1518229923760L;
    private String cdate;

    private Long driverId;

    private String status;

    private String faultCause;

    private Integer enable;

    private Integer pingEnable;

    private Long pingFrequency;

    private Integer pollEnable;

    private Long pollFrequency;

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFaultCause() {
        return faultCause;
    }

    public void setFaultCause(String faultCause) {
        this.faultCause = faultCause;
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

    public Long getPingFrequency() {
        return pingFrequency;
    }

    public void setPingFrequency(Long pingFrequency) {
        this.pingFrequency = pingFrequency;
    }

    public Integer getPollEnable() {
        return pollEnable;
    }

    public void setPollEnable(Integer pollEnable) {
        this.pollEnable = pollEnable;
    }

    public Long getPollFrequency() {
        return pollFrequency;
    }

    public void setPollFrequency(Long pollFrequency) {
        this.pollFrequency = pollFrequency;
    }
}
