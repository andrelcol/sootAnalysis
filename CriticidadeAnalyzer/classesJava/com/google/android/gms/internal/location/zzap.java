package com.google.android.gms.internal.location;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzap extends zza implements zzao {
  zzap(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.gms.location.internal.IGoogleLocationManagerService");
  }
  
  public final void zza(zzbf paramzzbf) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramzzbf);
    transactAndReadExceptionReturnVoid(59, parcel);
  }
  
  public final void zza(zzo paramzzo) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, (Parcelable)paramzzo);
    transactAndReadExceptionReturnVoid(75, parcel);
  }
  
  public final void zza(boolean paramBoolean) throws RemoteException {
    Parcel parcel = obtainAndWriteInterfaceToken();
    zzc.zza(parcel, paramBoolean);
    transactAndReadExceptionReturnVoid(12, parcel);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */