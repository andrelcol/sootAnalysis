package com.google.android.gms.maps;

import com.google.android.gms.internal.maps.zzt;
import com.google.android.gms.maps.internal.zzas;
import com.google.android.gms.maps.model.Marker;

final class zzb extends zzas {
  private final GoogleMap.OnMarkerClickListener zzj;
  
  zzb(GoogleMap paramGoogleMap, GoogleMap.OnMarkerClickListener paramOnMarkerClickListener) {}
  
  public final boolean zza(zzt paramzzt) {
    return this.zzj.onMarkerClick(new Marker(paramzzt));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */