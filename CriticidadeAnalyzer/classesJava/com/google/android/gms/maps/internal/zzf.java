package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zze;

public final class zzf extends zza implements zze {
  zzf(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.ICreator");
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper, int paramInt) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel.writeInt(paramInt);
    zzb(6, parcel);
  }
  
  public final IMapFragmentDelegate zzc(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    IMapFragmentDelegate iMapFragmentDelegate;
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel = zza(2, parcel);
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IMapFragmentDelegate");
      if (iInterface instanceof IMapFragmentDelegate) {
        iMapFragmentDelegate = (IMapFragmentDelegate)iInterface;
      } else {
        iMapFragmentDelegate = new zzj((IBinder)iMapFragmentDelegate);
      } 
    } 
    parcel.recycle();
    return iMapFragmentDelegate;
  }
  
  public final ICameraUpdateFactoryDelegate zze() throws RemoteException {
    IInterface iInterface;
    Parcel parcel = zza(4, zza());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iInterface = null;
    } else {
      iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate");
      if (iInterface instanceof ICameraUpdateFactoryDelegate) {
        iInterface = iInterface;
      } else {
        iInterface = new zzb(iBinder);
      } 
    } 
    parcel.recycle();
    return (ICameraUpdateFactoryDelegate)iInterface;
  }
  
  public final zze zzf() throws RemoteException {
    Parcel parcel = zza(5, zza());
    zze zze1 = com.google.android.gms.internal.maps.zzf.zzb(parcel.readStrongBinder());
    parcel.recycle();
    return zze1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */