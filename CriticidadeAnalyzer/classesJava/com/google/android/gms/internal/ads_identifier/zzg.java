package com.google.android.gms.internal.ads_identifier;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzg extends zza implements zze {
  zzg(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
  }
  
  public final String getId() throws RemoteException {
    Parcel parcel = transactAndReadException(1, obtainAndWriteInterfaceToken());
    String str = parcel.readString();
    parcel.recycle();
    return str;
  }
  
  public final boolean zzb(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, true);
    parcel = transactAndReadException(2, parcel);
    paramBoolean = zzc.zza(parcel);
    parcel.recycle();
    return paramBoolean;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/ads_identifier/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */