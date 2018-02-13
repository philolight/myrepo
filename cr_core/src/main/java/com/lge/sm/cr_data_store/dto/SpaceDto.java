package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class SpaceDto implements Serializable {
	private static final long serialVersionUID = 1518229923997L;
    private Long spaceId;

    private Long parentSpaceId;

    private String name;

    private String floor;

    private String type;

    private String cdate;

    public Long getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(Long spaceId) {
        this.spaceId = spaceId;
    }

    public Long getParentSpaceId() {
        return parentSpaceId;
    }

    public void setParentSpaceId(Long parentSpaceId) {
        this.parentSpaceId = parentSpaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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
