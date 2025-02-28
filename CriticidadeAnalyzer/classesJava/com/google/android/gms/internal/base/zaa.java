package com.google.android.gms.internal.base;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zaa implements IInterface {
  private final IBinder zaa;
  
  private final String zab;
  
  protected zaa(IBinder paramIBinder, String paramString) {
    this.zaa = paramIBinder;
    this.zab = paramString;
  }
  
  public IBinder asBinder() {
    return this.zaa;
  }
  
  protected final Parcel zaa() {
    Parcel parcel = Parcel.obtain();
    parcel.writeInterfaceToken(this.zab);
    return parcel;
  }
  
  protected final void zab(int paramInt, Parcel paramParcel) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    try {
      this.zaa.transact(paramInt, paramParcel, parcel, 0);
      parcel.readException();
      return;
    } finally {
      paramParcel.recycle();
      parcel.recycle();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/base/zaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */