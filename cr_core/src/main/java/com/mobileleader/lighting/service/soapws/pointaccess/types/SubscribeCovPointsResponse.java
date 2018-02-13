/**
 * SubscribeCovPointsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess.types;

public class SubscribeCovPointsResponse  implements java.io.Serializable {
    private com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse_T res;

    public SubscribeCovPointsResponse() {
    }

    public SubscribeCovPointsResponse(
           com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse_T res) {
           this.res = res;
    }


    /**
     * Gets the res value for this SubscribeCovPointsResponse.
     * 
     * @return res
     */
    public com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse_T getRes() {
        return res;
    }


    /**
     * Sets the res value for this SubscribeCovPointsResponse.
     * 
     * @param res
     */
    public void setRes(com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse_T res) {
        this.res = res;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubscribeCovPointsResponse)) return false;
        SubscribeCovPointsResponse other = (SubscribeCovPointsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.res==null && other.getRes()==null) || 
             (this.res!=null &&
              this.res.equals(other.getRes())));
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
        if (getRes() != null) {
            _hashCode += getRes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubscribeCovPointsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">subscribeCovPointsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("res");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "res"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsResponse_T"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
