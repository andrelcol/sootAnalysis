package com.google.android.gms.measurement.internal;

public final class zzau {
  private final int priority;
  
  private final zzas zzamk;
  
  private final boolean zzaml;
  
  private final boolean zzamm;
  
  zzau(zzas paramzzas, int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    this.priority = paramInt;
    this.zzaml = paramBoolean1;
    this.zzamm = paramBoolean2;
  }
  
  public final void zzby(String paramString) {
    this.zzamk.zza(this.priority, this.zzaml, this.zzamm, paramString, null, null, null);
  }
  
  public final void zzd(String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    this.zzamk.zza(this.priority, this.zzaml, this.zzamm, paramString, paramObject1, paramObject2, paramObject3);
  }
  
  public final void zze(String paramString, Object paramObject1, Object paramObject2) {
    this.zzamk.zza(this.priority, this.zzaml, this.zzamm, paramString, paramObject1, paramObject2, null);
  }
  
  public final void zzg(String paramString, Object paramObject) {
    this.zzamk.zza(this.priority, this.zzaml, this.zzamm, paramString, paramObject, null, null);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzau.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */