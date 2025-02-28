package com.google.android.gms.measurement.internal;

final class zzfk extends zzy {
  private final zzfn zzata;
  
  private final zzfj zzath;
  
  zzfk(zzfj paramzzfj, zzct paramzzct, zzfn paramzzfn) {
    super(paramzzct);
  }
  
  public final void run() {
    this.zzath.cancel();
    this.zzath.zzgt().zzjo().zzby("Starting upload from DelayedRunnable");
    this.zzata.zzlz();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */