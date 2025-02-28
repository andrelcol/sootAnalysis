package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzew implements Runnable {
  private final zzes zzasu;
  
  zzew(zzes paramzzes) {}
  
  public final void run() {
    zzeb zzeb = this.zzasu.zzasl;
    Context context = zzeb.getContext();
    this.zzasu.zzasl.zzgw();
    zzeb.zza(zzeb, new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzew.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */