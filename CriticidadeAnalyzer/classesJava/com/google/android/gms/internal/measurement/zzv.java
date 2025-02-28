package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;

public abstract class zzv extends zzr implements zzu {
  public static zzu zza(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
    return (iInterface instanceof zzu) ? (zzu)iInterface : new zzw(paramIBinder);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */