package com.google.android.gms.measurement.internal;

import android.os.RemoteException;

final class zzei implements Runnable {
  private final zzdx zzasd;
  
  private final zzeb zzasl;
  
  zzei(zzeb paramzzeb, zzdx paramzzdx) {}
  
  public final void run() {
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Failed to send current screen to service");
      return;
    } 
    try {
      if (this.zzasd == null) {
        zzaj.zza(0L, (String)null, (String)null, this.zzasl.getContext().getPackageName());
      } else {
        zzaj.zza(this.zzasd.zzarr, this.zzasd.zzuw, this.zzasd.zzarq, this.zzasl.getContext().getPackageName());
      } 
      zzeb.zze(this.zzasl);
      return;
    } catch (RemoteException remoteException) {
      this.zzasl.zzgt().zzjg().zzg("Failed to send current screen to the service", remoteException);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzei.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */