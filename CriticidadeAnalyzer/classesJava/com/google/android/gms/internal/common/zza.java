package com.google.android.gms.internal.common;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zza implements IInterface {
  private final IBinder zza;
  
  private final String zzb;
  
  protected zza(IBinder paramIBinder, String paramString) {
    this.zza = paramIBinder;
    this.zzb = paramString;
  }
  
  public IBinder asBinder() {
    return this.zza;
  }
  
  protected final Parcel zza() {
    Parcel parcel = Parcel.obtain();
    parcel.writeInterfaceToken(this.zzb);
    return parcel;
  }
  
  protected final Parcel zza(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zza.transact(paramInt, paramParcel, parcel, 0);
      parcel.readException();
      paramParcel.recycle();
      return parcel;
    } catch (RuntimeException runtimeException) {
      parcel.recycle();
      throw runtimeException;
    } finally {}
    paramParcel.recycle();
    throw parcel;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/common/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */