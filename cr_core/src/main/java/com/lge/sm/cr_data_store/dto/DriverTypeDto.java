package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class DriverTypeDto implements Serializable {
	private static final long serialVersionUID = 1518229923769L;
    private String driverTypeId;

    private String cdate;

    public String getDriverTypeId() {
        return driverTypeId;
    }

    public void setDriverTypeId(String driverTypeId) {
        this.driverTypeId = driverTypeId;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
