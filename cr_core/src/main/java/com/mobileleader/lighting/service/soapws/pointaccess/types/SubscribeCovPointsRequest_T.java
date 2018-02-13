/**
 * SubscribeCovPointsRequest_T.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess.types;

public class SubscribeCovPointsRequest_T  implements java.io.Serializable {
    private java.lang.String[] pointPaths;

    private long covLifeTime;

    private java.lang.String notiUrl;

    public SubscribeCovPointsRequest_T() {
    }

    public SubscribeCovPointsRequest_T(
           java.lang.String[] pointPaths,
           long covLifeTime,
           java.lang.String notiUrl) {
           this.pointPaths = pointPaths;
           this.covLifeTime = covLifeTime;
           this.notiUrl = notiUrl;
    }


    /**
     * Gets the pointPaths value for this SubscribeCovPointsRequest_T.
     * 
     * @return pointPaths
     */
    public java.lang.String[] getPointPaths() {
        return pointPaths;
    }


    /**
     * Sets the pointPaths value for this SubscribeCovPointsRequest_T.
     * 
     * @param pointPaths
     */
    public void setPointPaths(java.lang.String[] pointPaths) {
        this.pointPaths = pointPaths;
    }

    public java.lang.String getPointPaths(int i) {
        return this.pointPaths[i];
    }

    public void setPointPaths(int i, java.lang.String _value) {
        this.pointPaths[i] = _value;
    }


    /**
     * Gets the covLifeTime value for this SubscribeCovPointsRequest_T.
     * 
     * @return covLifeTime
     */
    public long getCovLifeTime() {
        return covLifeTime;
    }


    /**
     * Sets the covLifeTime value for this SubscribeCovPointsRequest_T.
     * 
     * @param covLifeTime
     */
    public void setCovLifeTime(long covLifeTime) {
        this.covLifeTime = covLifeTime;
    }


    /**
     * Gets the notiUrl value for this SubscribeCovPointsRequest_T.
     * 
     * @return notiUrl
     */
    public java.lang.String getNotiUrl() {
        return notiUrl;
    }


    /**
     * Sets the notiUrl value for this SubscribeCovPointsRequest_T.
     * 
     * @param notiUrl
     */
    public void setNotiUrl(java.lang.String notiUrl) {
        this.notiUrl = notiUrl;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubscribeCovPointsRequest_T)) return false;
        SubscribeCovPointsRequest_T other = (SubscribeCovPointsRequest_T) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pointPaths==null && other.getPointPaths()==null) || 
             (this.pointPaths!=null &&
              java.util.Arrays.equals(this.pointPaths, other.getPointPaths()))) &&
            this.covLifeTime == other.getCovLifeTime() &&
            ((this.notiUrl==null && other.getNotiUrl()==null) || 
             (this.notiUrl!=null &&
              this.notiUrl.equals(other.getNotiUrl())));
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
        if (getPointPaths() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPointPaths());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPointPaths(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getCovLifeTime()).hashCode();
        if (getNotiUrl() != null) {
            _hashCode += getNotiUrl().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubscribeCovPointsRequest_T.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsRequest_T"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointPaths");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointPaths"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("covLifeTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "covLifeTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("notiUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "notiUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
