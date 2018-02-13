package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class StringRangeDto implements Serializable {
	private static final long serialVersionUID = 1518229924030L;
    private Long stringRangeId;

    private Integer length;

    private String regex;

    private String cdate;

    private String scriptName;

    private String fieldSkinId;

    public Long getStringRangeId() {
        return stringRangeId;
    }

    public void setStringRangeId(Long stringRangeId) {
        this.stringRangeId = stringRangeId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getFieldSkinId() {
        return fieldSkinId;
    }

    public void setFieldSkinId(String fieldSkinId) {
        this.fieldSkinId = fieldSkinId;
    }
}
