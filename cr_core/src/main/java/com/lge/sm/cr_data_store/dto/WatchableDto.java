package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class WatchableDto implements Serializable {
	private static final long serialVersionUID = 1518229924079L;
    private String watchableId;

    private String upperWatchableId;

    private String timeZoneId;

    private String modifiedTime;

    private String type;

    private String cdate;

    public String getWatchableId() {
        return watchableId;
    }

    public void setWatchableId(String watchableId) {
        this.watchableId = watchableId;
    }

    public String getUpperWatchableId() {
        return upperWatchableId;
    }

    public void setUpperWatchableId(String upperWatchableId) {
        this.upperWatchableId = upperWatchableId;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
