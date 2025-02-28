package com.google.android.gms.measurement.internal;

abstract class zzfm extends zzfl {
  private boolean zzvz;
  
  zzfm(zzfn paramzzfn) {
    super(paramzzfn);
    this.zzamx.zzb(this);
  }
  
  final boolean isInitialized() {
    return this.zzvz;
  }
  
  protected final void zzcl() {
    if (isInitialized())
      return; 
    throw new IllegalStateException("Not initialized");
  }
  
  protected abstract boolean zzgy();
  
  public final void zzq() {
    if (!this.zzvz) {
      zzgy();
      this.zzamx.zzmg();
      this.zzvz = true;
      return;
    } 
    throw new IllegalStateException("Can't initialize twice");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */