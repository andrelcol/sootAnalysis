package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfu extends zzyc<zzfu> {
  private static volatile zzfu[] zzaxf;
  
  public String name = null;
  
  public String zzamn = null;
  
  private Float zzauo = null;
  
  public Double zzaup = null;
  
  public Long zzaxg = null;
  
  public zzfu() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfu[] zzna() {
    if (zzaxf == null)
      synchronized (zzyg.zzcfe) {
        if (zzaxf == null)
          zzaxf = new zzfu[0]; 
      }  
    return zzaxf;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfu))
      return false; 
    paramObject = paramObject;
    String str = this.name;
    if (str == null) {
      if (((zzfu)paramObject).name != null)
        return false; 
    } else if (!str.equals(((zzfu)paramObject).name)) {
      return false;
    } 
    str = this.zzamn;
    if (str == null) {
      if (((zzfu)paramObject).zzamn != null)
        return false; 
    } else if (!str.equals(((zzfu)paramObject).zzamn)) {
      return false;
    } 
    Long long_ = this.zzaxg;
    if (long_ == null) {
      if (((zzfu)paramObject).zzaxg != null)
        return false; 
    } else if (!long_.equals(((zzfu)paramObject).zzaxg)) {
      return false;
    } 
    Float float_ = this.zzauo;
    if (float_ == null) {
      if (((zzfu)paramObject).zzauo != null)
        return false; 
    } else if (!float_.equals(((zzfu)paramObject).zzauo)) {
      return false;
    } 
    Double double_ = this.zzaup;
    if (double_ == null) {
      if (((zzfu)paramObject).zzaup != null)
        return false; 
    } else if (!double_.equals(((zzfu)paramObject).zzaup)) {
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
    int i2 = zzfu.class.getName().hashCode();
    String str = this.name;
    byte b = 0;
    if (str == null) {
      i = 0;
    } else {
      i = str.hashCode();
    } 
    str = this.zzamn;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    Long long_ = this.zzaxg;
    if (long_ == null) {
      k = 0;
    } else {
      k = long_.hashCode();
    } 
    Float float_ = this.zzauo;
    if (float_ == null) {
      m = 0;
    } else {
      m = float_.hashCode();
    } 
    Double double_ = this.zzaup;
    if (double_ == null) {
      n = 0;
    } else {
      n = double_.hashCode();
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
    String str = this.name;
    if (str != null)
      paramzzya.zzb(1, str); 
    str = this.zzamn;
    if (str != null)
      paramzzya.zzb(2, str); 
    Long long_ = this.zzaxg;
    if (long_ != null)
      paramzzya.zzi(3, long_.longValue()); 
    Float float_ = this.zzauo;
    if (float_ != null)
      paramzzya.zza(4, float_.floatValue()); 
    Double double_ = this.zzaup;
    if (double_ != null)
      paramzzya.zza(5, double_.doubleValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    String str = this.name;
    int i = j;
    if (str != null)
      i = j + zzya.zzc(1, str); 
    str = this.zzamn;
    j = i;
    if (str != null)
      j = i + zzya.zzc(2, str); 
    Long long_ = this.zzaxg;
    int k = j;
    if (long_ != null)
      k = j + zzya.zzd(3, long_.longValue()); 
    Float float_ = this.zzauo;
    i = k;
    if (float_ != null) {
      float_.floatValue();
      i = k + zzya.zzbd(4) + 4;
    } 
    Double double_ = this.zzaup;
    j = i;
    if (double_ != null) {
      double_.doubleValue();
      j = i + zzya.zzbd(5) + 8;
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */