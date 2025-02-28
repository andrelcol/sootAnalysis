package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzel implements Runnable {
  private final zzk zzaqn;
  
  private final zzeb zzasl;
  
  zzel(zzeb paramzzeb, zzk paramzzk) {}
  
  public final void run() {
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Failed to send measurementEnabled to service");
      return;
    } 
    try {
      zzaj.zzb(this.zzaqn);
      zzeb.zze(this.zzasl);
      return;
    } catch (RemoteException remoteException) {
      this.zzasl.zzgt().zzjg().zzg("Failed to send measurementEnabled to the service", remoteException);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */