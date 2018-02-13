package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class SensorDto implements Serializable {
	private static final long serialVersionUID = 1518229923945L;
    private String sensorId;

    private String slmId;

    private String name;

    private String cdate;

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSlmId() {
        return slmId;
    }

    public void setSlmId(String slmId) {
        this.slmId = slmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
