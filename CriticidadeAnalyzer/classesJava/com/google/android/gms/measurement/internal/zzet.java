package com.google.android.gms.measurement.internal;

final class zzet implements Runnable {
  private final zzaj zzast;
  
  private final zzes zzasu;
  
  zzet(zzes paramzzes, zzaj paramzzaj) {}
  
  public final void run() {
    synchronized (this.zzasu) {
      zzes.zza(this.zzasu, false);
      if (!this.zzasu.zzasl.isConnected()) {
        this.zzasu.zzasl.zzgt().zzjo().zzby("Connected to service");
        this.zzasu.zzasl.zza(this.zzast);
      } 
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */