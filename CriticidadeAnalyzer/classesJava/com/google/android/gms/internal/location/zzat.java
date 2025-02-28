package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.zzv;

final class zzat extends zzv {
  private final ListenerHolder<LocationCallback> zzda;
  
  public final void onLocationAvailability(LocationAvailability paramLocationAvailability) {
    this.zzda.notifyListener(new zzav(this, paramLocationAvailability));
  }
  
  public final void onLocationResult(LocationResult paramLocationResult) {
    this.zzda.notifyListener(new zzau(this, paramLocationResult));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */