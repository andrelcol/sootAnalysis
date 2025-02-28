package com.google.android.gms.maps;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class CameraUpdate {
  private final IObjectWrapper zze;
  
  CameraUpdate(IObjectWrapper paramIObjectWrapper) {
    Preconditions.checkNotNull(paramIObjectWrapper);
    this.zze = paramIObjectWrapper;
  }
  
  public final IObjectWrapper zzb() {
    return this.zze;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/CameraUpdate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */