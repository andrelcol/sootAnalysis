package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

final class zzeh implements Runnable {
  private final zzk zzaqn;
  
  private final zzeb zzasl;
  
  zzeh(zzeb paramzzeb, zzk paramzzk) {}
  
  public final void run() {
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send app launch");
      return;
    } 
    try {
      zzaj.zza(this.zzaqn);
      this.zzasl.zza(zzaj, (AbstractSafeParcelable)null, this.zzaqn);
      zzeb.zze(this.zzasl);
      return;
    } catch (RemoteException remoteException) {
      this.zzasl.zzgt().zzjg().zzg("Failed to send app launch to the service", remoteException);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzeh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */