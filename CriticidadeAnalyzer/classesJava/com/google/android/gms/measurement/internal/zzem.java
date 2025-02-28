package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzem implements Runnable {
  private final zzag zzagi;
  
  private final String zzagj;
  
  private final zzk zzaqn;
  
  private final zzeb zzasl;
  
  private final boolean zzasn;
  
  private final boolean zzaso;
  
  zzem(zzeb paramzzeb, boolean paramBoolean1, boolean paramBoolean2, zzag paramzzag, zzk paramzzk, String paramString) {}
  
  public final void run() {
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to send event to service");
      return;
    } 
    if (this.zzasn) {
      zzag zzag1;
      zzeb zzeb1 = this.zzasl;
      if (this.zzaso) {
        zzag1 = null;
      } else {
        zzag1 = this.zzagi;
      } 
      zzeb1.zza(zzaj, zzag1, this.zzaqn);
    } else {
      try {
        if (TextUtils.isEmpty(this.zzagj)) {
          zzaj.zza(this.zzagi, this.zzaqn);
        } else {
          zzaj.zza(this.zzagi, this.zzagj, this.zzasl.zzgt().zzjq());
        } 
      } catch (RemoteException remoteException) {
        this.zzasl.zzgt().zzjg().zzg("Failed to send event to the service", remoteException);
      } 
    } 
    zzeb.zze(this.zzasl);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */