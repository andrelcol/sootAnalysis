package com.google.android.gms.measurement.internal;

abstract class zzf extends zze {
  private boolean zzvz;
  
  zzf(zzbw paramzzbw) {
    super(paramzzbw);
    this.zzada.zzb(this);
  }
  
  final boolean isInitialized() {
    return this.zzvz;
  }
  
  protected final void zzcl() {
    if (isInitialized())
      return; 
    throw new IllegalStateException("Not initialized");
  }
  
  public final void zzgx() {
    if (!this.zzvz) {
      zzgz();
      this.zzada.zzku();
      this.zzvz = true;
      return;
    } 
    throw new IllegalStateException("Can't initialize twice");
  }
  
  protected abstract boolean zzgy();
  
  protected void zzgz() {}
  
  public final void zzq() {
    if (!this.zzvz) {
      if (!zzgy()) {
        this.zzada.zzku();
        this.zzvz = true;
      } 
      return;
    } 
    throw new IllegalStateException("Can't initialize twice");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */