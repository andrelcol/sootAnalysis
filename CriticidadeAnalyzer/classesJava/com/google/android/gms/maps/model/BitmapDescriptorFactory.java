package com.google.android.gms.maps.model;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.maps.zze;

public final class BitmapDescriptorFactory {
  private static zze zzcm;
  
  public static BitmapDescriptor defaultMarker() {
    try {
      return new BitmapDescriptor(zzg().zzi());
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromBitmap(Bitmap paramBitmap) {
    try {
      return new BitmapDescriptor(zzg().zza(paramBitmap));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static BitmapDescriptor fromResource(int paramInt) {
    try {
      return new BitmapDescriptor(zzg().zza(paramInt));
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public static void zza(zze paramzze) {
    if (zzcm != null)
      return; 
    Preconditions.checkNotNull(paramzze);
    zzcm = paramzze;
  }
  
  private static zze zzg() {
    zze zze1 = zzcm;
    Preconditions.checkNotNull(zze1, "IBitmapDescriptorFactory is not initialized");
    return zze1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/model/BitmapDescriptorFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */