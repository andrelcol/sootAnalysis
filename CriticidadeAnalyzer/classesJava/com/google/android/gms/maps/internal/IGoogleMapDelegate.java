package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public interface IGoogleMapDelegate extends IInterface {
  zzt addMarker(MarkerOptions paramMarkerOptions) throws RemoteException;
  
  void animateCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramzzc) throws RemoteException;
  
  void clear() throws RemoteException;
  
  IUiSettingsDelegate getUiSettings() throws RemoteException;
  
  void moveCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  boolean setMapStyle(MapStyleOptions paramMapStyleOptions) throws RemoteException;
  
  void setMyLocationEnabled(boolean paramBoolean) throws RemoteException;
  
  void setOnMapClickListener(zzaj paramzzaj) throws RemoteException;
  
  void setOnMapLongClickListener(zzan paramzzan) throws RemoteException;
  
  void setOnMarkerClickListener(zzar paramzzar) throws RemoteException;
  
  void setTrafficEnabled(boolean paramBoolean) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/IGoogleMapDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */