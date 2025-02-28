package com.google.android.gms.common.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface IGmsServiceBroker extends IInterface {
  void getService(IGmsCallbacks paramIGmsCallbacks, GetServiceRequest paramGetServiceRequest) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/IGmsServiceBroker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */