package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class PartyDto implements Serializable {
	private static final long serialVersionUID = 1518229923842L;
    private String partyId;

    private String description;

    private String cdate;

    public String getPartyId() {
        return partyId;
    }

    public void setPartyId(String partyId) {
        this.partyId = partyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
