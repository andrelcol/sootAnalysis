package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IUiSettingsDelegate;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public final class UiSettings {
  private final IUiSettingsDelegate zzcj;
  
  UiSettings(IUiSettingsDelegate paramIUiSettingsDelegate) {
    this.zzcj = paramIUiSettingsDelegate;
  }
  
  public final void setMapToolbarEnabled(boolean paramBoolean) {
    try {
      this.zzcj.setMapToolbarEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setMyLocationButtonEnabled(boolean paramBoolean) {
    try {
      this.zzcj.setMyLocationButtonEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
  
  public final void setRotateGesturesEnabled(boolean paramBoolean) {
    try {
      this.zzcj.setRotateGesturesEnabled(paramBoolean);
      return;
    } catch (RemoteException remoteException) {
      throw new RuntimeRemoteException(remoteException);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/UiSettings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */