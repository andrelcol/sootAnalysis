package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zza;
import com.google.android.gms.internal.maps.zzc;
import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.internal.maps.zzu;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public final class zzg extends zza implements IGoogleMapDelegate {
  zzg(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.maps.internal.IGoogleMapDelegate");
  }
  
  public final zzt addMarker(MarkerOptions paramMarkerOptions) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (Parcelable)paramMarkerOptions);
    Parcel parcel1 = zza(11, parcel2);
    zzt zzt = zzu.zzg(parcel1.readStrongBinder());
    parcel1.recycle();
    return zzt;
  }
  
  public final void animateCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(5, parcel);
  }
  
  public final void animateCameraWithDurationAndCallback(IObjectWrapper paramIObjectWrapper, int paramInt, zzc paramzzc) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    parcel.writeInt(paramInt);
    zzc.zza(parcel, paramzzc);
    zzb(7, parcel);
  }
  
  public final void clear() throws RemoteException {
    zzb(14, zza());
  }
  
  public final IUiSettingsDelegate getUiSettings() throws RemoteException {
    IUiSettingsDelegate iUiSettingsDelegate;
    Parcel parcel = zza(25, zza());
    IBinder iBinder = parcel.readStrongBinder();
    if (iBinder == null) {
      iBinder = null;
    } else {
      IInterface iInterface = iBinder.queryLocalInterface("com.google.android.gms.maps.internal.IUiSettingsDelegate");
      if (iInterface instanceof IUiSettingsDelegate) {
        iUiSettingsDelegate = (IUiSettingsDelegate)iInterface;
      } else {
        iUiSettingsDelegate = new zzbx((IBinder)iUiSettingsDelegate);
      } 
    } 
    parcel.recycle();
    return iUiSettingsDelegate;
  }
  
  public final void moveCamera(IObjectWrapper paramIObjectWrapper) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, (IInterface)paramIObjectWrapper);
    zzb(4, parcel);
  }
  
  public final boolean setMapStyle(MapStyleOptions paramMapStyleOptions) throws RemoteException {
    Parcel parcel2 = zza();
    zzc.zza(parcel2, (Parcelable)paramMapStyleOptions);
    Parcel parcel1 = zza(91, parcel2);
    boolean bool = zzc.zza(parcel1);
    parcel1.recycle();
    return bool;
  }
  
  public final void setMyLocationEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zza();
    zzc.writeBoolean(parcel, paramBoolean);
    zzb(22, parcel);
  }
  
  public final void setOnMapClickListener(zzaj paramzzaj) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, paramzzaj);
    zzb(28, parcel);
  }
  
  public final void setOnMapLongClickListener(zzan paramzzan) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, paramzzan);
    zzb(29, parcel);
  }
  
  public final void setOnMarkerClickListener(zzar paramzzar) throws RemoteException {
    Parcel parcel = zza();
    zzc.zza(parcel, paramzzar);
    zzb(30, parcel);
  }
  
  public final void setTrafficEnabled(boolean paramBoolean) throws RemoteException {
    Parcel parcel = zza();
    zzc.writeBoolean(parcel, paramBoolean);
    zzb(18, parcel);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */