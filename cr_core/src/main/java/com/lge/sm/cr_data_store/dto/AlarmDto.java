package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class AlarmDto implements Serializable {
	private static final long serialVersionUID = 1518229923666L;
    private Long alarmId;

    private String cdate;

    private Integer enable;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}
