package com.mobileleader.lighting.service.soapws.pointaccess;

public class LgLcsPointAccessServiceEndpointProxy implements com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpoint {
  private String _endpoint = null;
  private com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpoint lgLcsPointAccessServiceEndpoint = null;
  
  public LgLcsPointAccessServiceEndpointProxy() {
    _initLgLcsPointAccessServiceEndpointProxy();
  }
  
  public LgLcsPointAccessServiceEndpointProxy(String endpoint) {
    _endpoint = endpoint;
    _initLgLcsPointAccessServiceEndpointProxy();
  }
  
  private void _initLgLcsPointAccessServiceEndpointProxy() {
    try {
      lgLcsPointAccessServiceEndpoint = (new com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceLocator()).getLgLcsPointAccessServiceEndpointPort();
      if (lgLcsPointAccessServiceEndpoint != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)lgLcsPointAccessServiceEndpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)lgLcsPointAccessServiceEndpoint)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (lgLcsPointAccessServiceEndpoint != null)
      ((javax.xml.rpc.Stub)lgLcsPointAccessServiceEndpoint)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.LgLcsPointAccessServiceEndpoint getLgLcsPointAccessServiceEndpoint() {
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint;
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeResponse getTime(com.mobileleader.lighting.service.soapws.pointaccess.types.GetTimeRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.getTime(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeResponse setTime(com.mobileleader.lighting.service.soapws.pointaccess.types.SetTimeRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.setTime(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveResponse checkSystemAlive(com.mobileleader.lighting.service.soapws.pointaccess.types.CheckSystemAliveRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.checkSystemAlive(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverResponse discover(com.mobileleader.lighting.service.soapws.pointaccess.types.DiscoverRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.discover(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesResponse readPointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.ReadPointValuesRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.readPointValues(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesResponse writePointValues(com.mobileleader.lighting.service.soapws.pointaccess.types.WritePointValuesRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.writePointValues(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsResponse subscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.SubscribeCovPointsRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.subscribeCovPoints(parameters);
  }
  
  public com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsResponse unsubscribeCovPoints(com.mobileleader.lighting.service.soapws.pointaccess.types.UnsubscribeCovPointsRequest parameters) throws java.rmi.RemoteException{
    if (lgLcsPointAccessServiceEndpoint == null)
      _initLgLcsPointAccessServiceEndpointProxy();
    return lgLcsPointAccessServiceEndpoint.unsubscribeCovPoints(parameters);
  }
  
  
}