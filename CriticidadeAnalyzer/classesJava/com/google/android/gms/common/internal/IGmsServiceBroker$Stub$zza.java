package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

final class IGmsServiceBroker$Stub$zza implements IGmsServiceBroker {
  private final IBinder zza;
  
  IGmsServiceBroker$Stub$zza(IBinder paramIBinder) {
    this.zza = paramIBinder;
  }
  
  public final IBinder asBinder() {
    return this.zza;
  }
  
  public final void getService(IGmsCallbacks paramIGmsCallbacks, GetServiceRequest paramGetServiceRequest) throws RemoteException {
    Parcel parcel2 = Parcel.obtain();
    Parcel parcel1 = Parcel.obtain();
    try {
      parcel2.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
      if (paramIGmsCallbacks != null) {
        IBinder iBinder = paramIGmsCallbacks.asBinder();
      } else {
        paramIGmsCallbacks = null;
      } 
      parcel2.writeStrongBinder((IBinder)paramIGmsCallbacks);
      if (paramGetServiceRequest != null) {
        parcel2.writeInt(1);
        paramGetServiceRequest.writeToParcel(parcel2, 0);
      } else {
        parcel2.writeInt(0);
      } 
      this.zza.transact(46, parcel2, parcel1, 0);
      parcel1.readException();
      return;
    } finally {
      parcel1.recycle();
      parcel2.recycle();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/IGmsServiceBroker$Stub$zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */