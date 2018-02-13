package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class RoomSensorDto implements Serializable {
	private static final long serialVersionUID = 1518229923894L;
    private Long roomSensorId;

    private String roomId;

    private String sensorId;

    private String cdate;

    private String udate;

    private Integer status;

    private String locationId;

    public Long getRoomSensorId() {
        return roomSensorId;
    }

    public void setRoomSensorId(Long roomSensorId) {
        this.roomSensorId = roomSensorId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
}
