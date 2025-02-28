package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfz extends zzyc<zzfz> {
  private static volatile zzfz[] zzayv;
  
  public String name = null;
  
  public String zzamn = null;
  
  private Float zzauo = null;
  
  public Double zzaup = null;
  
  public Long zzaxg = null;
  
  public Long zzayw = null;
  
  public zzfz() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfz[] zznd() {
    if (zzayv == null)
      synchronized (zzyg.zzcfe) {
        if (zzayv == null)
          zzayv = new zzfz[0]; 
      }  
    return zzayv;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfz))
      return false; 
    paramObject = paramObject;
    Long long_2 = this.zzayw;
    if (long_2 == null) {
      if (((zzfz)paramObject).zzayw != null)
        return false; 
    } else if (!long_2.equals(((zzfz)paramObject).zzayw)) {
      return false;
    } 
    String str = this.name;
    if (str == null) {
      if (((zzfz)paramObject).name != null)
        return false; 
    } else if (!str.equals(((zzfz)paramObject).name)) {
      return false;
    } 
    str = this.zzamn;
    if (str == null) {
      if (((zzfz)paramObject).zzamn != null)
        return false; 
    } else if (!str.equals(((zzfz)paramObject).zzamn)) {
      return false;
    } 
    Long long_1 = this.zzaxg;
    if (long_1 == null) {
      if (((zzfz)paramObject).zzaxg != null)
        return false; 
    } else if (!long_1.equals(((zzfz)paramObject).zzaxg)) {
      return false;
    } 
    Float float_ = this.zzauo;
    if (float_ == null) {
      if (((zzfz)paramObject).zzauo != null)
        return false; 
    } else if (!float_.equals(((zzfz)paramObject).zzauo)) {
      return false;
    } 
    Double double_ = this.zzaup;
    if (double_ == null) {
      if (((zzfz)paramObject).zzaup != null)
        return false; 
    } else if (!double_.equals(((zzfz)paramObject).zzaup)) {
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
    int i1;
    int i3 = zzfz.class.getName().hashCode();
    Long long_2 = this.zzayw;
    byte b = 0;
    if (long_2 == null) {
      i = 0;
    } else {
      i = long_2.hashCode();
    } 
    String str = this.name;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    str = this.zzamn;
    if (str == null) {
      k = 0;
    } else {
      k = str.hashCode();
    } 
    Long long_1 = this.zzaxg;
    if (long_1 == null) {
      m = 0;
    } else {
      m = long_1.hashCode();
    } 
    Float float_ = this.zzauo;
    if (float_ == null) {
      n = 0;
    } else {
      n = float_.hashCode();
    } 
    Double double_ = this.zzaup;
    if (double_ == null) {
      i1 = 0;
    } else {
      i1 = double_.hashCode();
    } 
    zzye zzye = this.zzcev;
    int i2 = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        i2 = b;
      } else {
        i2 = this.zzcev.hashCode();
      }  
    return (((((((i3 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Long long_2 = this.zzayw;
    if (long_2 != null)
      paramzzya.zzi(1, long_2.longValue()); 
    String str = this.name;
    if (str != null)
      paramzzya.zzb(2, str); 
    str = this.zzamn;
    if (str != null)
      paramzzya.zzb(3, str); 
    Long long_1 = this.zzaxg;
    if (long_1 != null)
      paramzzya.zzi(4, long_1.longValue()); 
    Float float_ = this.zzauo;
    if (float_ != null)
      paramzzya.zza(5, float_.floatValue()); 
    Double double_ = this.zzaup;
    if (double_ != null)
      paramzzya.zza(6, double_.doubleValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Long long_2 = this.zzayw;
    int i = j;
    if (long_2 != null)
      i = j + zzya.zzd(1, long_2.longValue()); 
    String str = this.name;
    j = i;
    if (str != null)
      j = i + zzya.zzc(2, str); 
    str = this.zzamn;
    i = j;
    if (str != null)
      i = j + zzya.zzc(3, str); 
    Long long_1 = this.zzaxg;
    j = i;
    if (long_1 != null)
      j = i + zzya.zzd(4, long_1.longValue()); 
    Float float_ = this.zzauo;
    i = j;
    if (float_ != null) {
      float_.floatValue();
      i = j + zzya.zzbd(5) + 4;
    } 
    Double double_ = this.zzaup;
    j = i;
    if (double_ != null) {
      double_.doubleValue();
      j = i + zzya.zzbd(6) + 8;
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */