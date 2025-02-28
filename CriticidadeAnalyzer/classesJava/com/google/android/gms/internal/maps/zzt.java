package com.google.android.gms.internal.maps;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public interface zzt extends IInterface {
  LatLng getPosition() throws RemoteException;
  
  void remove() throws RemoteException;
  
  void setZIndex(float paramFloat) throws RemoteException;
  
  void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException;
  
  int zzj() throws RemoteException;
  
  boolean zzj(zzt paramzzt) throws RemoteException;
  
  IObjectWrapper zzk() throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/maps/zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */