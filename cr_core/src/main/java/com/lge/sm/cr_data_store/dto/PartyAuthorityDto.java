package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class PartyAuthorityDto implements Serializable {
	private static final long serialVersionUID = 1518229923836L;
    private Long authorityId;

    private String partyId;

    private String cdate;

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
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
