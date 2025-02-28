package com.google.android.gms.measurement.internal;

final class zzdr implements Runnable {
  private final boolean zzaed;
  
  private final zzda zzarh;
  
  zzdr(zzda paramzzda, boolean paramBoolean) {}
  
  public final void run() {
    boolean bool2 = this.zzarh.zzada.isEnabled();
    boolean bool1 = this.zzarh.zzada.zzks();
    this.zzarh.zzada.zzd(this.zzaed);
    if (bool1 == this.zzaed)
      this.zzarh.zzada.zzgt().zzjo().zzg("Default data collection state already set to", Boolean.valueOf(this.zzaed)); 
    if (this.zzarh.zzada.isEnabled() == bool2 || this.zzarh.zzada.isEnabled() != this.zzarh.zzada.zzks())
      this.zzarh.zzada.zzgt().zzjl().zze("Default data collection is different than actual status", Boolean.valueOf(this.zzaed), Boolean.valueOf(bool2)); 
    zzda.zza(this.zzarh);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzdr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */