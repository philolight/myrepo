/**
 * ReadPointValuesResponse_T.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess.types;

public class ReadPointValuesResponse_T  implements java.io.Serializable {
    private long result;

    private com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue[] pointValues;

    public ReadPointValuesResponse_T() {
    }

    public ReadPointValuesResponse_T(
           long result,
           com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue[] pointValues) {
           this.result = result;
           this.pointValues = pointValues;
    }


    /**
     * Gets the result value for this ReadPointValuesResponse_T.
     * 
     * @return result
     */
    public long getResult() {
        return result;
    }


    /**
     * Sets the result value for this ReadPointValuesResponse_T.
     * 
     * @param result
     */
    public void setResult(long result) {
        this.result = result;
    }


    /**
     * Gets the pointValues value for this ReadPointValuesResponse_T.
     * 
     * @return pointValues
     */
    public com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue[] getPointValues() {
        return pointValues;
    }


    /**
     * Sets the pointValues value for this ReadPointValuesResponse_T.
     * 
     * @param pointValues
     */
    public void setPointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue[] pointValues) {
        this.pointValues = pointValues;
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue getPointValues(int i) {
        return this.pointValues[i];
    }

    public void setPointValues(int i, com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue _value) {
        this.pointValues[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReadPointValuesResponse_T)) return false;
        ReadPointValuesResponse_T other = (ReadPointValuesResponse_T) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.result == other.getResult() &&
            ((this.pointValues==null && other.getPointValues()==null) || 
             (this.pointValues!=null &&
              java.util.Arrays.equals(this.pointValues, other.getPointValues())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += new Long(getResult()).hashCode();
        if (getPointValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPointValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPointValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReadPointValuesResponse_T.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "readPointValuesResponse_T"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointValue"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
