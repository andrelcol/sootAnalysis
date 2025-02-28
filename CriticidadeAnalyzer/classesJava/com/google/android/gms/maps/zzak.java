package com.google.android.gms.maps;

import android.os.RemoteException;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.internal.zzaq;

final class zzak extends zzaq {
  private final OnMapReadyCallback zzbc;
  
  zzak(SupportMapFragment.zza paramzza, OnMapReadyCallback paramOnMapReadyCallback) {}
  
  public final void zza(IGoogleMapDelegate paramIGoogleMapDelegate) throws RemoteException {
    this.zzbc.onMapReady(new GoogleMap(paramIGoogleMapDelegate));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */