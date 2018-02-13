package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class StartableHistoryDto implements Serializable {
	private static final long serialVersionUID = 1518229924023L;
    private String startableId;

    private String cdate;

    private Integer startSucceed;

    private String startDate;

    private Long latency;

    private String report;

    public String getStartableId() {
        return startableId;
    }

    public void setStartableId(String startableId) {
        this.startableId = startableId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Integer getStartSucceed() {
        return startSucceed;
    }

    public void setStartSucceed(Integer startSucceed) {
        this.startSucceed = startSucceed;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Long getLatency() {
        return latency;
    }

    public void setLatency(Long latency) {
        this.latency = latency;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}
