package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfs extends zzyc<zzfs> {
  private static volatile zzfs[] zzawy;
  
  public Integer zzawz = null;
  
  public Long zzaxa = null;
  
  public zzfs() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfs[] zzmy() {
    if (zzawy == null)
      synchronized (zzyg.zzcfe) {
        if (zzawy == null)
          zzawy = new zzfs[0]; 
      }  
    return zzawy;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfs))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzawz;
    if (integer == null) {
      if (((zzfs)paramObject).zzawz != null)
        return false; 
    } else if (!integer.equals(((zzfs)paramObject).zzawz)) {
      return false;
    } 
    Long long_ = this.zzaxa;
    if (long_ == null) {
      if (((zzfs)paramObject).zzaxa != null)
        return false; 
    } else if (!long_.equals(((zzfs)paramObject).zzaxa)) {
      return false;
    } 
    zzye zzye = this.zzcev;
    if (zzye == null || zzye.isEmpty()) {
      paramObject = ((zzyc)paramObject).zzcev;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzcev.equals(((zzyc)paramObject).zzcev);
  }
  
  public final int hashCode() {
    int i;
    int j;
    int m = zzfs.class.getName().hashCode();
    Integer integer = this.zzawz;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    Long long_ = this.zzaxa;
    if (long_ == null) {
      j = 0;
    } else {
      j = long_.hashCode();
    } 
    zzye zzye = this.zzcev;
    int k = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        k = b;
      } else {
        k = this.zzcev.hashCode();
      }  
    return (((m + 527) * 31 + i) * 31 + j) * 31 + k;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzawz;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    Long long_ = this.zzaxa;
    if (long_ != null)
      paramzzya.zzi(2, long_.longValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Integer integer = this.zzawz;
    int i = j;
    if (integer != null)
      i = j + zzya.zzh(1, integer.intValue()); 
    Long long_ = this.zzaxa;
    j = i;
    if (long_ != null)
      j = i + zzya.zzd(2, long_.longValue()); 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */