package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzft extends zzyc<zzft> {
  private static volatile zzft[] zzaxb;
  
  public Integer count = null;
  
  public String name = null;
  
  public zzfu[] zzaxc = zzfu.zzna();
  
  public Long zzaxd = null;
  
  public Long zzaxe = null;
  
  public zzft() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzft[] zzmz() {
    if (zzaxb == null)
      synchronized (zzyg.zzcfe) {
        if (zzaxb == null)
          zzaxb = new zzft[0]; 
      }  
    return zzaxb;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzft))
      return false; 
    paramObject = paramObject;
    if (!zzyg.equals((Object[])this.zzaxc, (Object[])((zzft)paramObject).zzaxc))
      return false; 
    String str = this.name;
    if (str == null) {
      if (((zzft)paramObject).name != null)
        return false; 
    } else if (!str.equals(((zzft)paramObject).name)) {
      return false;
    } 
    Long long_ = this.zzaxd;
    if (long_ == null) {
      if (((zzft)paramObject).zzaxd != null)
        return false; 
    } else if (!long_.equals(((zzft)paramObject).zzaxd)) {
      return false;
    } 
    long_ = this.zzaxe;
    if (long_ == null) {
      if (((zzft)paramObject).zzaxe != null)
        return false; 
    } else if (!long_.equals(((zzft)paramObject).zzaxe)) {
      return false;
    } 
    Integer integer = this.count;
    if (integer == null) {
      if (((zzft)paramObject).count != null)
        return false; 
    } else if (!integer.equals(((zzft)paramObject).count)) {
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
    int i2 = zzft.class.getName().hashCode();
    int i1 = zzyg.hashCode((Object[])this.zzaxc);
    String str = this.name;
    byte b = 0;
    if (str == null) {
      i = 0;
    } else {
      i = str.hashCode();
    } 
    Long long_ = this.zzaxd;
    if (long_ == null) {
      j = 0;
    } else {
      j = long_.hashCode();
    } 
    long_ = this.zzaxe;
    if (long_ == null) {
      k = 0;
    } else {
      k = long_.hashCode();
    } 
    Integer integer = this.count;
    if (integer == null) {
      m = 0;
    } else {
      m = integer.hashCode();
    } 
    zzye zzye = this.zzcev;
    int n = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        n = b;
      } else {
        n = this.zzcev.hashCode();
      }  
    return ((((((i2 + 527) * 31 + i1) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    zzfu[] arrayOfZzfu = this.zzaxc;
    if (arrayOfZzfu != null && arrayOfZzfu.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfZzfu = this.zzaxc;
        if (b < arrayOfZzfu.length) {
          zzfu zzfu1 = arrayOfZzfu[b];
          if (zzfu1 != null)
            paramzzya.zza(1, zzfu1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    String str = this.name;
    if (str != null)
      paramzzya.zzb(2, str); 
    Long long_ = this.zzaxd;
    if (long_ != null)
      paramzzya.zzi(3, long_.longValue()); 
    long_ = this.zzaxe;
    if (long_ != null)
      paramzzya.zzi(4, long_.longValue()); 
    Integer integer = this.count;
    if (integer != null)
      paramzzya.zzd(5, integer.intValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int i = super.zzf();
    zzfu[] arrayOfZzfu = this.zzaxc;
    int j = i;
    if (arrayOfZzfu != null) {
      j = i;
      if (arrayOfZzfu.length > 0) {
        byte b = 0;
        while (true) {
          arrayOfZzfu = this.zzaxc;
          j = i;
          if (b < arrayOfZzfu.length) {
            zzfu zzfu1 = arrayOfZzfu[b];
            j = i;
            if (zzfu1 != null)
              j = i + zzya.zzb(1, zzfu1); 
            b++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    String str = this.name;
    i = j;
    if (str != null)
      i = j + zzya.zzc(2, str); 
    Long long_ = this.zzaxd;
    j = i;
    if (long_ != null)
      j = i + zzya.zzd(3, long_.longValue()); 
    long_ = this.zzaxe;
    i = j;
    if (long_ != null)
      i = j + zzya.zzd(4, long_.longValue()); 
    Integer integer = this.count;
    j = i;
    if (integer != null)
      j = i + zzya.zzh(5, integer.intValue()); 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzft.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */