package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class WatchableHistoryDto implements Serializable {
	private static final long serialVersionUID = 1518229924093L;
    private String watchableId;

    private String cdate;

    private Long runCount;

    private Long latency;

    private String modifiedTime;

    public String getWatchableId() {
        return watchableId;
    }

    public void setWatchableId(String watchableId) {
        this.watchableId = watchableId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Long getRunCount() {
        return runCount;
    }

    public void setRunCount(Long runCount) {
        this.runCount = runCount;
    }

    public Long getLatency() {
        return latency;
    }

    public void setLatency(Long latency) {
        this.latency = latency;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
