package com.google.android.gms.location;

import android.location.Location;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.location.zzb;
import com.google.android.gms.internal.location.zzc;

public abstract class zzy extends zzb implements zzx {
  public zzy() {
    super("com.google.android.gms.location.ILocationListener");
  }
  
  public static zzx zzc(IBinder paramIBinder) {
    if (paramIBinder == null)
      return null; 
    IInterface iInterface = paramIBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
    return (iInterface instanceof zzx) ? (zzx)iInterface : new zzz(paramIBinder);
  }
  
  protected final boolean dispatchTransaction(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2) throws RemoteException {
    if (paramInt1 == 1) {
      onLocationChanged((Location)zzc.zza(paramParcel1, Location.CREATOR));
      return true;
    } 
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */