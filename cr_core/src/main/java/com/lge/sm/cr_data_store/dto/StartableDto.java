package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class StartableDto implements Serializable {
	private static final long serialVersionUID = 1518229924015L;
    private String startableId;

    private String type;

    private String status;

    private String cdate;

    public String getStartableId() {
        return startableId;
    }

    public void setStartableId(String startableId) {
        this.startableId = startableId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
