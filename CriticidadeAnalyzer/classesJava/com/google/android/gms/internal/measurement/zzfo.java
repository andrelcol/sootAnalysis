package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfo extends zzyc<zzfo> {
  private static volatile zzfo[] zzawi;
  
  public String name = null;
  
  public Boolean zzawj = null;
  
  public Boolean zzawk = null;
  
  public Integer zzawl = null;
  
  public zzfo() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfo[] zzmv() {
    if (zzawi == null)
      synchronized (zzyg.zzcfe) {
        if (zzawi == null)
          zzawi = new zzfo[0]; 
      }  
    return zzawi;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfo))
      return false; 
    paramObject = paramObject;
    String str = this.name;
    if (str == null) {
      if (((zzfo)paramObject).name != null)
        return false; 
    } else if (!str.equals(((zzfo)paramObject).name)) {
      return false;
    } 
    Boolean bool = this.zzawj;
    if (bool == null) {
      if (((zzfo)paramObject).zzawj != null)
        return false; 
    } else if (!bool.equals(((zzfo)paramObject).zzawj)) {
      return false;
    } 
    bool = this.zzawk;
    if (bool == null) {
      if (((zzfo)paramObject).zzawk != null)
        return false; 
    } else if (!bool.equals(((zzfo)paramObject).zzawk)) {
      return false;
    } 
    Integer integer = this.zzawl;
    if (integer == null) {
      if (((zzfo)paramObject).zzawl != null)
        return false; 
    } else if (!integer.equals(((zzfo)paramObject).zzawl)) {
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
    int i1 = zzfo.class.getName().hashCode();
    String str = this.name;
    byte b = 0;
    if (str == null) {
      i = 0;
    } else {
      i = str.hashCode();
    } 
    Boolean bool = this.zzawj;
    if (bool == null) {
      j = 0;
    } else {
      j = bool.hashCode();
    } 
    bool = this.zzawk;
    if (bool == null) {
      k = 0;
    } else {
      k = bool.hashCode();
    } 
    Integer integer = this.zzawl;
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
    return (((((i1 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    String str = this.name;
    if (str != null)
      paramzzya.zzb(1, str); 
    Boolean bool = this.zzawj;
    if (bool != null)
      paramzzya.zzb(2, bool.booleanValue()); 
    bool = this.zzawk;
    if (bool != null)
      paramzzya.zzb(3, bool.booleanValue()); 
    Integer integer = this.zzawl;
    if (integer != null)
      paramzzya.zzd(4, integer.intValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    String str = this.name;
    int i = j;
    if (str != null)
      i = j + zzya.zzc(1, str); 
    Boolean bool = this.zzawj;
    j = i;
    if (bool != null) {
      bool.booleanValue();
      j = i + zzya.zzbd(2) + 1;
    } 
    bool = this.zzawk;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(3) + 1;
    } 
    Integer integer = this.zzawl;
    j = i;
    if (integer != null)
      j = i + zzya.zzh(4, integer.intValue()); 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */