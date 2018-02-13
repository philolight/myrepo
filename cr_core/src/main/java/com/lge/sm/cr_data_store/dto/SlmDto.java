package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class SlmDto implements Serializable {
	private static final long serialVersionUID = 1518229923987L;
    private String slmId;

    private String protocol;

    private String ip;

    private Integer port;

    private Integer useAuth;

    private String userId;

    private String userPw;

    private String cdate;

    public String getSlmId() {
        return slmId;
    }

    public void setSlmId(String slmId) {
        this.slmId = slmId;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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

    public Integer getUseAuth() {
        return useAuth;
    }

    public void setUseAuth(Integer useAuth) {
        this.useAuth = useAuth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }
}
