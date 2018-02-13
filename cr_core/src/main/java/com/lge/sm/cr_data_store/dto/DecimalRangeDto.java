package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class DecimalRangeDto implements Serializable {
	private static final long serialVersionUID = 1518229923734L;
    private Long decimalRangeId;

    private Long valueFrom;

    private Long valueTo;

    private String cdate;

    private String fieldSkinId;

    public Long getDecimalRangeId() {
        return decimalRangeId;
    }

    public void setDecimalRangeId(Long decimalRangeId) {
        this.decimalRangeId = decimalRangeId;
    }

    public Long getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Long valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Long getValueTo() {
        return valueTo;
    }

    public void setValueTo(Long valueTo) {
        this.valueTo = valueTo;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getFieldSkinId() {
        return fieldSkinId;
    }

    public void setFieldSkinId(String fieldSkinId) {
        this.fieldSkinId = fieldSkinId;
    }
}
