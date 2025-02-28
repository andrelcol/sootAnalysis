package com.google.android.gms.internal.measurement;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzq implements IInterface {
  private final IBinder zzqt;
  
  private final String zzqu;
  
  protected zzq(IBinder paramIBinder, String paramString) {
    this.zzqt = paramIBinder;
    this.zzqu = paramString;
  }
  
  public IBinder asBinder() {
    return this.zzqt;
  }
  
  protected final Parcel obtainAndWriteInterfaceToken() {
    Parcel parcel = Parcel.obtain();
    parcel.writeInterfaceToken(this.zzqu);
    return parcel;
  }
  
  protected final Parcel transactAndReadException(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zzqt.transact(paramInt, paramParcel, parcel, 0);
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
  
  protected final void zza(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zzqt.transact(paramInt, paramParcel, parcel, 0);
      parcel.readException();
      return;
    } finally {
      paramParcel.recycle();
      parcel.recycle();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */