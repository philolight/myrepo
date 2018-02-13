/**
 * DiscoverResponse_T.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess.types;

public class DiscoverResponse_T  implements java.io.Serializable {
    private long result;

    private com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject[] pointObjects;

    public DiscoverResponse_T() {
    }

    public DiscoverResponse_T(
           long result,
           com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject[] pointObjects) {
           this.result = result;
           this.pointObjects = pointObjects;
    }


    /**
     * Gets the result value for this DiscoverResponse_T.
     * 
     * @return result
     */
    public long getResult() {
        return result;
    }


    /**
     * Sets the result value for this DiscoverResponse_T.
     * 
     * @param result
     */
    public void setResult(long result) {
        this.result = result;
    }


    /**
     * Gets the pointObjects value for this DiscoverResponse_T.
     * 
     * @return pointObjects
     */
    public com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject[] getPointObjects() {
        return pointObjects;
    }


    /**
     * Sets the pointObjects value for this DiscoverResponse_T.
     * 
     * @param pointObjects
     */
    public void setPointObjects(com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject[] pointObjects) {
        this.pointObjects = pointObjects;
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject getPointObjects(int i) {
        return this.pointObjects[i];
    }

    public void setPointObjects(int i, com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject _value) {
        this.pointObjects[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DiscoverResponse_T)) return false;
        DiscoverResponse_T other = (DiscoverResponse_T) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.result == other.getResult() &&
            ((this.pointObjects==null && other.getPointObjects()==null) || 
             (this.pointObjects!=null &&
              java.util.Arrays.equals(this.pointObjects, other.getPointObjects())));
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
        if (getPointObjects() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPointObjects());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPointObjects(), i);
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
        new org.apache.axis.description.TypeDesc(DiscoverResponse_T.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "discoverResponse_T"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("result");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "result"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pointObjects");
        elemField.setXmlName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointObjects"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointObject"));
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
