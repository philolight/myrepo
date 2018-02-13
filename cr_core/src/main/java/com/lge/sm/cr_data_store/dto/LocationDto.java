package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class LocationDto implements Serializable {
	private static final long serialVersionUID = 1518229923820L;
    private String locationId;

    private String name;

    private String timeZoneId;

    private String cdate;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
