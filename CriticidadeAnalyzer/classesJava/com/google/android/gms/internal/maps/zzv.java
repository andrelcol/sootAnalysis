package com.google.android.gms.internal.maps;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.maps.model.LatLng;

public final class zzv extends zza implements zzt {
  zzv(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
  }
  
  public final LatLng getPosition() throws RemoteException {
    Parcel parcel = zza(4, zza());
    LatLng latLng = zzc.<LatLng>zza(parcel, LatLng.CREATOR);
    parcel.recycle();
    return latLng;
  }
  
  public final void remove() throws RemoteException {
    zzb(1, zza());
  }
  
  public final void setZIndex(float paramFloat) throws RemoteException {
    Parcel parcel = zza();
    parcel.writeFloat(paramFloat);
    zzb(27, parcel);
  }
  
  public final void zze(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(29, parcel);
  }
  
  public final int zzj() throws RemoteException {
    Parcel parcel = zza(17, zza());
    int i = parcel.readInt();
    parcel.recycle();
    return i;
  }
  
  public final boolean zzj(zzt paramzzt) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, paramzzt);
    Parcel parcel1 = zza(16, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final IObjectWrapper zzk() throws RemoteException {
    Parcel parcel = zza(30, zza());
    IObjectWrapper iObjectWrapper = IObjectWrapper.Stub.asInterface(parcel.readStrongBinder());
    parcel.recycle();
    return iObjectWrapper;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/maps/zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */