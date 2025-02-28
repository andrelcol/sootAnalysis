package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public interface ICameraUpdateFactoryDelegate extends IInterface {
  IObjectWrapper newCameraPosition(CameraPosition paramCameraPosition) throws RemoteException;
  
  IObjectWrapper newLatLng(LatLng paramLatLng) throws RemoteException;
  
  IObjectWrapper newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) throws RemoteException;
  
  IObjectWrapper newLatLngBoundsWithSize(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) throws RemoteException;
  
  IObjectWrapper newLatLngZoom(LatLng paramLatLng, float paramFloat) throws RemoteException;
  
  IObjectWrapper zoomTo(float paramFloat) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/ICameraUpdateFactoryDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */