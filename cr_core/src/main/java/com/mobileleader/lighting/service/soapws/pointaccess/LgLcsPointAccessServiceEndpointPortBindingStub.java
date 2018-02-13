/**
 * LgLcsPointAccessServiceEndpointPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess;

public class LgLcsPointAccessServiceEndpointPortBindingStub extends org.apache.axis.client.Stub implements com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpoint {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[8];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getTime");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "getTimeRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">getTimeRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">getTimeResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "getTimeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("setTime");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "setTimeRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">setTimeRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">setTimeResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "setTimeResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("checkSystemAlive");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "checkSystemAliveRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">checkSystemAliveRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">checkSystemAliveResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "checkSystemAliveResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("discover");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "discoverRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">discoverRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">discoverResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "discoverResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("readPointValues");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "readPointValuesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">readPointValuesRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">readPointValuesResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "readPointValuesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("writePointValues");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "writePointValuesRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">writePointValuesRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">writePointValuesResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "writePointValuesResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("subscribeCovPoints");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">subscribeCovPointsRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">subscribeCovPointsResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("unsubscribeCovPoints");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "unsubscribeCovPointsRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">unsubscribeCovPointsRequest"), com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsRequest.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">unsubscribeCovPointsResponse"));
        oper.setReturnClass(com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "unsubscribeCovPointsResponse"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

    }

    public LgLcsPointAccessServiceEndpointPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public LgLcsPointAccessServiceEndpointPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public LgLcsPointAccessServiceEndpointPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">checkSystemAliveRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">checkSystemAliveResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">discoverRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">discoverResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">getTimeRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">getTimeResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">readPointValuesRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">readPointValuesResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">setTimeRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">setTimeResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">subscribeCovPointsRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">subscribeCovPointsResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">unsubscribeCovPointsRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">unsubscribeCovPointsResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">writePointValuesRequest");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", ">writePointValuesResponse");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "checkSystemAliveRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "checkSystemAliveResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "discoverRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "discoverResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "getTimeRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "getTimeResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointObject");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.PointObject.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointValue");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "readPointValuesRequest_T");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointPaths");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "readPointValuesResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "setTimeRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "setTimeResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "subscribeCovPointsResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "unsubscribeCovPointsRequest_T");
            cachedSerQNames.add(qName);
            cls = java.lang.String[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string");
            qName2 = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointPaths");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "unsubscribeCovPointsResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "writePointValuesRequest_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.PointValue[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointValue");
            qName2 = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "pointValues");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://com/mobileleader/lighting/service/soapws/pointaccess/types", "writePointValuesResponse_T");
            cachedSerQNames.add(qName);
            cls = com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse_T.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse getTime(com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "getTime"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse setTime(com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "setTime"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse checkSystemAlive(com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "checkSystemAlive"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse discover(com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "discover"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse readPointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "readPointValues"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse writePointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "writePointValues"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse subscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "subscribeCovPoints"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse unsubscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsRequest parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "unsubscribeCovPoints"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse) _resp;
            } catch (java.lang.Exception _exception) {
                return (com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse) org.apache.axis.utils.JavaUtils.convert(_resp, com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
