package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.RemoteException;

public abstract class zzak extends zzb implements zzaj {
  public zzak() {
    super("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      zza(zzc.<zzad>zza(paramParcel1, zzad.CREATOR));
      return true;
    } 
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */