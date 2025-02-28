package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfr extends zzyc<zzfr> {
  private static volatile zzfr[] zzawu;
  
  public Integer zzavg = null;
  
  public zzfx zzawv = null;
  
  public zzfx zzaww = null;
  
  public Boolean zzawx = null;
  
  public zzfr() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfr[] zzmx() {
    if (zzawu == null)
      synchronized (zzyg.zzcfe) {
        if (zzawu == null)
          zzawu = new zzfr[0]; 
      }  
    return zzawu;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfr))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzavg;
    if (integer == null) {
      if (((zzfr)paramObject).zzavg != null)
        return false; 
    } else if (!integer.equals(((zzfr)paramObject).zzavg)) {
      return false;
    } 
    zzfx zzfx1 = this.zzawv;
    if (zzfx1 == null) {
      if (((zzfr)paramObject).zzawv != null)
        return false; 
    } else if (!zzfx1.equals(((zzfr)paramObject).zzawv)) {
      return false;
    } 
    zzfx1 = this.zzaww;
    if (zzfx1 == null) {
      if (((zzfr)paramObject).zzaww != null)
        return false; 
    } else if (!zzfx1.equals(((zzfr)paramObject).zzaww)) {
      return false;
    } 
    Boolean bool = this.zzawx;
    if (bool == null) {
      if (((zzfr)paramObject).zzawx != null)
        return false; 
    } else if (!bool.equals(((zzfr)paramObject).zzawx)) {
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
    int k;
    int m;
    int i1 = zzfr.class.getName().hashCode();
    Integer integer = this.zzavg;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    zzfx zzfx1 = this.zzawv;
    if (zzfx1 == null) {
      j = 0;
    } else {
      j = zzfx1.hashCode();
    } 
    zzfx1 = this.zzaww;
    if (zzfx1 == null) {
      k = 0;
    } else {
      k = zzfx1.hashCode();
    } 
    Boolean bool = this.zzawx;
    if (bool == null) {
      m = 0;
    } else {
      m = bool.hashCode();
    } 
    zzye zzye = this.zzcev;
    int n = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        n = b;
      } else {
        n = this.zzcev.hashCode();
      }  
    return (((((i1 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzavg;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    zzfx zzfx1 = this.zzawv;
    if (zzfx1 != null)
      paramzzya.zza(2, zzfx1); 
    zzfx1 = this.zzaww;
    if (zzfx1 != null)
      paramzzya.zza(3, zzfx1); 
    Boolean bool = this.zzawx;
    if (bool != null)
      paramzzya.zzb(4, bool.booleanValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int i = super.zzf();
    Integer integer = this.zzavg;
    int j = i;
    if (integer != null)
      j = i + zzya.zzh(1, integer.intValue()); 
    zzfx zzfx1 = this.zzawv;
    i = j;
    if (zzfx1 != null)
      i = j + zzya.zzb(2, zzfx1); 
    zzfx1 = this.zzaww;
    j = i;
    if (zzfx1 != null)
      j = i + zzya.zzb(3, zzfx1); 
    Boolean bool = this.zzawx;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(4) + 1;
    } 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */