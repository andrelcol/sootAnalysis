package com.google.android.gms.internal.measurement;

import java.io.IOException;

public abstract class zzyi {
  protected volatile int zzcff = -1;
  
  public String toString() {
    return zzyj.zzc(this);
  }
  
  public abstract zzyi zza(zzxz paramzzxz) throws IOException;
  
  public void zza(zzya paramzzya) throws IOException {}
  
  protected int zzf() {
    return 0;
  }
  
  public final int zzvx() {
    int i = zzf();
    this.zzcff = i;
    return i;
  }
  
  public zzyi zzzb() throws CloneNotSupportedException {
    return (zzyi)super.clone();
  }
  
  public final int zzzh() {
    if (this.zzcff < 0)
      zzvx(); 
    return this.zzcff;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */