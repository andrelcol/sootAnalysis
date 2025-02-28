package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzen implements Runnable {
  private final zzk zzaqn;
  
  private final zzeb zzasl;
  
  private final boolean zzasn;
  
  private final boolean zzaso;
  
  private final zzo zzasp;
  
  private final zzo zzasq;
  
  zzen(zzeb paramzzeb, boolean paramBoolean1, boolean paramBoolean2, zzo paramzzo1, zzk paramzzk, zzo paramzzo2) {}
  
  public final void run() {
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send conditional user property to service");
      return;
    } 
    if (this.zzasn) {
      zzo zzo1;
      zzeb zzeb1 = this.zzasl;
      if (this.zzaso) {
        zzo1 = null;
      } else {
        zzo1 = this.zzasp;
      } 
      zzeb1.zza(zzaj, zzo1, this.zzaqn);
    } else {
      try {
        if (TextUtils.isEmpty(this.zzasq.packageName)) {
          zzaj.zza(this.zzasp, this.zzaqn);
        } else {
          zzaj.zzb(this.zzasp);
        } 
      } catch (RemoteException remoteException) {
        this.zzasl.zzgt().zzjg().zzg("Failed to send conditional user property to the service", remoteException);
      } 
    } 
    zzeb.zze(this.zzasl);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */