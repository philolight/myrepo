package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class EnumFacetDto implements Serializable {
	private static final long serialVersionUID = 1518229923774L;
    private Long enumFacetId;

    private String value;

    private String cdate;

    private String scriptName;

    private String fieldSkinId;

    public Long getEnumFacetId() {
        return enumFacetId;
    }

    public void setEnumFacetId(Long enumFacetId) {
        this.enumFacetId = enumFacetId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
