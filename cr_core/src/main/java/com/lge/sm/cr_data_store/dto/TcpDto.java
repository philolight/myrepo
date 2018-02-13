package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class TcpDto implements Serializable {
	private static final long serialVersionUID = 1518229924037L;
    private Long tcpId;

    private String ip;

    private Integer port;

    private String cdate;

    private Long driverId;

    public Long getTcpId() {
        return tcpId;
    }

    public void setTcpId(Long tcpId) {
        this.tcpId = tcpId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
