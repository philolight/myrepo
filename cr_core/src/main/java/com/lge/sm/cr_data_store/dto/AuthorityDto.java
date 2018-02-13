package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class AuthorityDto implements Serializable {
	private static final long serialVersionUID = 1518229923690L;
    private Long authorityId;

    private String type;

    private String cdate;

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
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
