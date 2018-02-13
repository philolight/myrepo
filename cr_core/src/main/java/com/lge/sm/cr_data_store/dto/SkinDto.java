package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class SkinDto implements Serializable {
	private static final long serialVersionUID = 1518229923978L;
    private String skinId;

    private String cdate;

    public String getSkinId() {
        return skinId;
    }

    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
