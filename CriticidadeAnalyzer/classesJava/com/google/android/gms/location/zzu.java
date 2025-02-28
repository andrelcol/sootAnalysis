package com.google.android.gms.location;

import android.os.IInterface;
import android.os.RemoteException;

public interface zzu extends IInterface {
  void onLocationAvailability(LocationAvailability paramLocationAvailability) throws RemoteException;
  
  void onLocationResult(LocationResult paramLocationResult) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */