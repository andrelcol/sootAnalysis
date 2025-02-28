package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class CameraUpdateFactory {
  private static ICameraUpdateFactoryDelegate zzf;
  
  public static CameraUpdate newCameraPosition(CameraPosition paramCameraPosition) {
    try {
      return new CameraUpdate(zzc().newCameraPosition(paramCameraPosition));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLng(LatLng paramLatLng) {
    try {
      return new CameraUpdate(zzc().newLatLng(paramLatLng));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt) {
    try {
      return new CameraUpdate(zzc().newLatLngBounds(paramLatLngBounds, paramInt));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngBounds(LatLngBounds paramLatLngBounds, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return new CameraUpdate(zzc().newLatLngBoundsWithSize(paramLatLngBounds, paramInt1, paramInt2, paramInt3));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate newLatLngZoom(LatLng paramLatLng, float paramFloat) {
    try {
      return new CameraUpdate(zzc().newLatLngZoom(paramLatLng, paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static CameraUpdate zoomTo(float paramFloat) {
    try {
      return new CameraUpdate(zzc().zoomTo(paramFloat));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static void zza(ICameraUpdateFactoryDelegate paramICameraUpdateFactoryDelegate) {
    Preconditions.checkNotNull(paramICameraUpdateFactoryDelegate);
    zzf = paramICameraUpdateFactoryDelegate;
  }
  
  private static ICameraUpdateFactoryDelegate zzc() {
    ICameraUpdateFactoryDelegate iCameraUpdateFactoryDelegate = zzf;
    Preconditions.checkNotNull(iCameraUpdateFactoryDelegate, "CameraUpdateFactory is not initialized");
    return iCameraUpdateFactoryDelegate;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/CameraUpdateFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */