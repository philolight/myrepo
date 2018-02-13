/**
 * LgLcsPointAccessServiceEndpoint.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.mobileleader.lighting.service.soapws.pointaccess;

public interface LgLcsPointAccessServiceEndpoint extends java.rmi.Remote {
    public com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse getTime(com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse setTime(com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse checkSystemAlive(com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse discover(com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse readPointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse writePointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse subscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest parameters) throws java.rmi.RemoteException;
    public com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse unsubscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsRequest parameters) throws java.rmi.RemoteException;
}
