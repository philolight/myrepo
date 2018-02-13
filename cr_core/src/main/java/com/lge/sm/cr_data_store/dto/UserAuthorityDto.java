package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class UserAuthorityDto implements Serializable {
	private static final long serialVersionUID = 1518229924058L;
    private String userId;

    private Long authorityId;

    private String cdate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
