package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzw implements zzv {
  private final IBinder zzbo;
  
  zzw(IBinder paramIBinder) {
    this.zzbo = paramIBinder;
  }
  
  public final IBinder asBinder() {
    return this.zzbo;
  }
  
  public final void send(Message paramMessage) throws RemoteException {
    Parcel parcel = Parcel.obtain();
    parcel.writeInterfaceToken("com.google.android.gms.iid.IMessengerCompat");
    parcel.writeInt(1);
    paramMessage.writeToParcel(parcel, 0);
    try {
      this.zzbo.transact(1, parcel, null, 1);
      return;
    } finally {
      parcel.recycle();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */