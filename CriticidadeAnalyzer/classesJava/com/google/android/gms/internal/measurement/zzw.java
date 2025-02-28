package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzw extends zzq implements zzu {
  zzw(IBinder paramIBinder) {
    super(paramIBinder, "com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
  }
  
  public final Bundle zza(Bundle paramBundle) throws RemoteException {
    Parcel parcel2 = obtainAndWriteInterfaceToken();
    zzs.zza(parcel2, (Parcelable)paramBundle);
    Parcel parcel1 = transactAndReadException(1, parcel2);
    Bundle bundle = zzs.<Bundle>zza(parcel1, Bundle.CREATOR);
    parcel1.recycle();
    return bundle;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */