package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzf extends zzb implements zze {
  public static zze zza(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
    return (iInterface instanceof zze) ? (zze)iInterface : new zzg(paramIBinder);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/ads_identifier/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */