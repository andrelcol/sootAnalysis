package com.google.android.gms.measurement.internal;

final class zzev implements Runnable {
  private final zzes zzasu;
  
  private final zzaj zzasv;
  
  zzev(zzes paramzzes, zzaj paramzzaj) {}
  
  public final void run() {
    synchronized (this.zzasu) {
      zzes.zza(this.zzasu, false);
      if (!this.zzasu.zzasl.isConnected()) {
        this.zzasu.zzasl.zzgt().zzjn().zzby("Connected to remote service");
        this.zzasu.zzasl.zza(this.zzasv);
      } 
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzev.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */