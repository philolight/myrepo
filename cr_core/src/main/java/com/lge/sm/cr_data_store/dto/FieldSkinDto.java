package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class FieldSkinDto implements Serializable {
	private static final long serialVersionUID = 1518229923801L;
    private String fieldSkinId;

    private String name;

    private String type;

    private String scriptName;

    private String cdate;

    private Integer visible;

    private Integer editable;

    private Integer encryption;

    private Integer hideTyping;

    private String skinId;

    private Integer nillable;

    private Integer autoFill;

    private Integer isPk;

    private Integer isFk;

    public String getFieldSkinId() {
        return fieldSkinId;
    }

    public void setFieldSkinId(String fieldSkinId) {
        this.fieldSkinId = fieldSkinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

    public Integer getEncryption() {
        return encryption;
    }

    public void setEncryption(Integer encryption) {
        this.encryption = encryption;
    }

    public Integer getHideTyping() {
        return hideTyping;
    }

    public void setHideTyping(Integer hideTyping) {
        this.hideTyping = hideTyping;
    }

    public String getSkinId() {
        return skinId;
    }

    public void setSkinId(String skinId) {
        this.skinId = skinId;
    }

    public Integer getNillable() {
        return nillable;
    }

    public void setNillable(Integer nillable) {
        this.nillable = nillable;
    }

    public Integer getAutoFill() {
        return autoFill;
    }

    public void setAutoFill(Integer autoFill) {
        this.autoFill = autoFill;
    }

    public Integer getIsPk() {
        return isPk;
    }

    public void setIsPk(Integer isPk) {
        this.isPk = isPk;
    }

    public Integer getIsFk() {
        return isFk;
    }

    public void setIsFk(Integer isFk) {
        this.isFk = isFk;
    }
}
