package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfm extends zzyc<zzfm> {
  private static volatile zzfm[] zzawb;
  
  public Boolean zzavj = null;
  
  public Boolean zzavk = null;
  
  public Integer zzavm = null;
  
  public String zzawc = null;
  
  public zzfk zzawd = null;
  
  public zzfm() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfm[] zzmu() {
    if (zzawb == null)
      synchronized (zzyg.zzcfe) {
        if (zzawb == null)
          zzawb = new zzfm[0]; 
      }  
    return zzawb;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfm))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzavm;
    if (integer == null) {
      if (((zzfm)paramObject).zzavm != null)
        return false; 
    } else if (!integer.equals(((zzfm)paramObject).zzavm)) {
      return false;
    } 
    String str = this.zzawc;
    if (str == null) {
      if (((zzfm)paramObject).zzawc != null)
        return false; 
    } else if (!str.equals(((zzfm)paramObject).zzawc)) {
      return false;
    } 
    zzfk zzfk1 = this.zzawd;
    if (zzfk1 == null) {
      if (((zzfm)paramObject).zzawd != null)
        return false; 
    } else if (!zzfk1.equals(((zzfm)paramObject).zzawd)) {
      return false;
    } 
    Boolean bool = this.zzavj;
    if (bool == null) {
      if (((zzfm)paramObject).zzavj != null)
        return false; 
    } else if (!bool.equals(((zzfm)paramObject).zzavj)) {
      return false;
    } 
    bool = this.zzavk;
    if (bool == null) {
      if (((zzfm)paramObject).zzavk != null)
        return false; 
    } else if (!bool.equals(((zzfm)paramObject).zzavk)) {
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
    int n;
    int i2 = zzfm.class.getName().hashCode();
    Integer integer = this.zzavm;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    String str = this.zzawc;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    zzfk zzfk1 = this.zzawd;
    if (zzfk1 == null) {
      k = 0;
    } else {
      k = zzfk1.hashCode();
    } 
    Boolean bool = this.zzavj;
    if (bool == null) {
      m = 0;
    } else {
      m = bool.hashCode();
    } 
    bool = this.zzavk;
    if (bool == null) {
      n = 0;
    } else {
      n = bool.hashCode();
    } 
    zzye zzye = this.zzcev;
    int i1 = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        i1 = b;
      } else {
        i1 = this.zzcev.hashCode();
      }  
    return ((((((i2 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzavm;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    String str = this.zzawc;
    if (str != null)
      paramzzya.zzb(2, str); 
    zzfk zzfk1 = this.zzawd;
    if (zzfk1 != null)
      paramzzya.zza(3, zzfk1); 
    Boolean bool = this.zzavj;
    if (bool != null)
      paramzzya.zzb(4, bool.booleanValue()); 
    bool = this.zzavk;
    if (bool != null)
      paramzzya.zzb(5, bool.booleanValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Integer integer = this.zzavm;
    int i = j;
    if (integer != null)
      i = j + zzya.zzh(1, integer.intValue()); 
    String str = this.zzawc;
    j = i;
    if (str != null)
      j = i + zzya.zzc(2, str); 
    zzfk zzfk1 = this.zzawd;
    i = j;
    if (zzfk1 != null)
      i = j + zzya.zzb(3, zzfk1); 
    Boolean bool = this.zzavj;
    j = i;
    if (bool != null) {
      bool.booleanValue();
      j = i + zzya.zzbd(4) + 1;
    } 
    bool = this.zzavk;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(5) + 1;
    } 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */