package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class CancelHistoryDto implements Serializable {
	private static final long serialVersionUID = 1518229923723L;
    private String dateOf;

    private String locationId;

    private Integer reservations;

    private Integer cancels;

    private Integer cancelMinutes;

    private Float cancelRate;

    private String cdate;

    private Integer reuses;

    public String getDateOf() {
        return dateOf;
    }

    public void setDateOf(String dateOf) {
        this.dateOf = dateOf;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Integer getReservations() {
        return reservations;
    }

    public void setReservations(Integer reservations) {
        this.reservations = reservations;
    }

    public Integer getCancels() {
        return cancels;
    }

    public void setCancels(Integer cancels) {
        this.cancels = cancels;
    }

    public Integer getCancelMinutes() {
        return cancelMinutes;
    }

    public void setCancelMinutes(Integer cancelMinutes) {
        this.cancelMinutes = cancelMinutes;
    }

    public Float getCancelRate() {
        return cancelRate;
    }

    public void setCancelRate(Float cancelRate) {
        this.cancelRate = cancelRate;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Integer getReuses() {
        return reuses;
    }

    public void setReuses(Integer reuses) {
        this.reuses = reuses;
    }
}
