package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfy extends zzyc<zzfy> {
  private static volatile zzfy[] zzayt;
  
  public Integer zzawz = null;
  
  public long[] zzayu = zzyl.zzcfk;
  
  public zzfy() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfy[] zznc() {
    if (zzayt == null)
      synchronized (zzyg.zzcfe) {
        if (zzayt == null)
          zzayt = new zzfy[0]; 
      }  
    return zzayt;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfy))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzawz;
    if (integer == null) {
      if (((zzfy)paramObject).zzawz != null)
        return false; 
    } else if (!integer.equals(((zzfy)paramObject).zzawz)) {
      return false;
    } 
    if (!zzyg.equals(this.zzayu, ((zzfy)paramObject).zzayu))
      return false; 
    zzye zzye = this.zzcev;
    if (zzye == null || zzye.isEmpty()) {
      paramObject = ((zzyc)paramObject).zzcev;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzcev.equals(((zzyc)paramObject).zzcev);
  }
  
  public final int hashCode() {
    int i;
    int k = zzfy.class.getName().hashCode();
    Integer integer = this.zzawz;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    int m = zzyg.hashCode(this.zzayu);
    zzye zzye = this.zzcev;
    int j = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        j = b;
      } else {
        j = this.zzcev.hashCode();
      }  
    return (((k + 527) * 31 + i) * 31 + m) * 31 + j;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzawz;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    long[] arrayOfLong = this.zzayu;
    if (arrayOfLong != null && arrayOfLong.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfLong = this.zzayu;
        if (b < arrayOfLong.length) {
          paramzzya.zzi(2, arrayOfLong[b]);
          b++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Integer integer = this.zzawz;
    int i = j;
    if (integer != null)
      i = j + zzya.zzh(1, integer.intValue()); 
    long[] arrayOfLong = this.zzayu;
    j = i;
    if (arrayOfLong != null) {
      j = i;
      if (arrayOfLong.length > 0) {
        j = 0;
        int k = 0;
        while (true) {
          arrayOfLong = this.zzayu;
          if (j < arrayOfLong.length) {
            k += zzya.zzbg(arrayOfLong[j]);
            j++;
            continue;
          } 
          j = i + k + arrayOfLong.length * 1;
          break;
        } 
      } 
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */