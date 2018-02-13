package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class ServiceDto implements Serializable {
	private static final long serialVersionUID = 1518229923965L;
    private String serviceId;

    private String upperServiceId;

    private String scriptName;

    private Integer enable;

    private String startableId;

    private String cdate;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUpperServiceId() {
        return upperServiceId;
    }

    public void setUpperServiceId(String upperServiceId) {
        this.upperServiceId = upperServiceId;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

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
}
