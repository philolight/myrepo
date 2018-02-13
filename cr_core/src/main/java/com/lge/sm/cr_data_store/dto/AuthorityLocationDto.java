package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class AuthorityLocationDto implements Serializable {
	private static final long serialVersionUID = 1518229923712L;
    private String locationId;

    private Long authorityId;

    private String cdate;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
