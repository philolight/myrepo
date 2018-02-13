package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class ServiceAuthorityDto implements Serializable {
	private static final long serialVersionUID = 1518229923957L;
    private String serviceId;

    private Long authorityId;

    private String cdate;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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
