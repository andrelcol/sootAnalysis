package com.google.android.gms.maps.internal;

import android.os.IInterface;
import android.os.RemoteException;

public interface IUiSettingsDelegate extends IInterface {
  void setMapToolbarEnabled(boolean paramBoolean) throws RemoteException;
  
  void setMyLocationButtonEnabled(boolean paramBoolean) throws RemoteException;
  
  void setRotateGesturesEnabled(boolean paramBoolean) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/internal/IUiSettingsDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */