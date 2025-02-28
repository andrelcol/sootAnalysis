package com.google.android.gms.location;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzv extends zzb implements zzu {
  public static zzu zzb(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
    return (iInterface instanceof zzu) ? (zzu)iInterface : new zzw(paramIBinder);
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 != 1) {
      if (paramInt1 != 2)
        return false; 
      onLocationAvailability((LocationAvailability)zzc.zza(paramParcel1, LocationAvailability.CREATOR));
    } else {
      onLocationResult((LocationResult)zzc.zza(paramParcel1, LocationResult.CREATOR));
    } 
    return true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/zzv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */