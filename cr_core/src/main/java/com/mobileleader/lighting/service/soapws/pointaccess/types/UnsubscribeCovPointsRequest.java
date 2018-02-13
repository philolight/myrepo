/**
 * UnsubscribeCovPointsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess.types;

public class UnsubscribeCovPointsRequest  implements java.io.Serializable {
    private java.lang.String[] req;

    public UnsubscribeCovPointsRequest() {
    }

    public UnsubscribeCovPointsRequest(
           java.lang.String[] req) {
           this.req = req;
    }


    /**
     * Gets the req value for this UnsubscribeCovPointsRequest.
     * 
     * @return req
     */
    public java.lang.String[] getReq() {
        return req;
    }


    /**
     * Sets the req value for this UnsubscribeCovPointsRequest.
     * 
     * @param req
     */
    public void setReq(java.lang.String[] req) {
        this.req = req;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UnsubscribeCovPointsRequest)) return false;
        UnsubscribeCovPointsRequest other = (UnsubscribeCovPointsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.req==null && other.getReq()==null) || 
             (this.req!=null &&
              java.util.Arrays.equals(this.req, other.getReq())));
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
        if (getReq() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReq());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReq(), i);
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
        new org.apache.axis.description.TypeDesc(UnsubscribeCovPointsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">unsubscribeCovPointsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("req");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "req"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointPaths"));
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
