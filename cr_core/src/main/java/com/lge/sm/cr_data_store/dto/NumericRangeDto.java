package com.lge.sm.cr_data_store.dto;

import java.io.Serializable;

public class NumericRangeDto implements Serializable {
	private static final long serialVersionUID = 1518229923827L;
    private Long numericRangeId;

    private Double valueFrom;

    private Double valueTo;

    private String cdate;

    private String fieldSkinId;

    public Long getNumericRangeId() {
        return numericRangeId;
    }

    public void setNumericRangeId(Long numericRangeId) {
        this.numericRangeId = numericRangeId;
    }

    public Double getValueFrom() {
        return valueFrom;
    }

    public void setValueFrom(Double valueFrom) {
        this.valueFrom = valueFrom;
    }

    public Double getValueTo() {
        return valueTo;
    }

    public void setValueTo(Double valueTo) {
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
