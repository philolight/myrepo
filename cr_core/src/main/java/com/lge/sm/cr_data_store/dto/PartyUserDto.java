package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class PartyUserDto implements Serializable {
	private static final long serialVersionUID = 1518229923848L;
    private String userId;

    private String partyId;

    private String cdate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
