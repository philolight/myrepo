package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class PointAttributeDto implements Serializable {
	private static final long serialVersionUID = 1518229923870L;
    private Long pointAttributeId;

    private String name;

    private String value;

    private String type;

    private Long pointId;

    private String cdate;

    public Long getPointAttributeId() {
        return pointAttributeId;
    }

    public void setPointAttributeId(Long pointAttributeId) {
        this.pointAttributeId = pointAttributeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
