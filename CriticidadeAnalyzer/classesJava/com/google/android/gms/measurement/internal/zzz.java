package com.google.android.gms.measurement.internal;

final class zzz implements Runnable {
  private final zzct zzaho;
  
  private final zzy zzahp;
  
  zzz(zzy paramzzy, zzct paramzzct) {}
  
  public final void run() {
    this.zzaho.zzgw();
    if (zzn.isMainThread()) {
      this.zzaho.zzgs().zzc(this);
      return;
    } 
    boolean bool = this.zzahp.zzej();
    zzy.zza(this.zzahp, 0L);
    if (bool)
      this.zzahp.run(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */