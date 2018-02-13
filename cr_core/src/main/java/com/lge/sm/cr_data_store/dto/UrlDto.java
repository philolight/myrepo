package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class UrlDto implements Serializable {
	private static final long serialVersionUID = 1518229924046L;
    private Long urlId;

    private String url;

    private Integer authEnable;

    private String userName;

    private String password;

    private String cdate;

    private Long driverId;

    public Long getUrlId() {
        return urlId;
    }

    public void setUrlId(Long urlId) {
        this.urlId = urlId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAuthEnable() {
        return authEnable;
    }

    public void setAuthEnable(Integer authEnable) {
        this.authEnable = authEnable;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
