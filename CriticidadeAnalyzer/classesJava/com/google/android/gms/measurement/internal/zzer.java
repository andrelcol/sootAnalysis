package com.google.android.gms.measurement.internal;

final class zzer implements Runnable {
  private final zzk zzaqn;
  
  private final zzfu zzaqq;
  
  private final zzeb zzasl;
  
  private final boolean zzaso;
  
  zzer(zzeb paramzzeb, boolean paramBoolean, zzfu paramzzfu, zzk paramzzk) {}
  
  public final void run() {
    zzfu zzfu1;
    zzaj zzaj = zzeb.zzd(this.zzasl);
    if (zzaj == null) {
      this.zzasl.zzgt().zzjg().zzby("Discarding data. Failed to set user attribute");
      return;
    } 
    zzeb zzeb1 = this.zzasl;
    if (this.zzaso) {
      zzfu1 = null;
    } else {
      zzfu1 = this.zzaqq;
    } 
    zzeb1.zza(zzaj, zzfu1, this.zzaqn);
    zzeb.zze(this.zzasl);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */