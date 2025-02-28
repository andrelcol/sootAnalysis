package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfk extends zzyc<zzfk> {
  private static volatile zzfk[] zzavr;
  
  public zzfn zzavs = null;
  
  public zzfl zzavt = null;
  
  public Boolean zzavu = null;
  
  public String zzavv = null;
  
  public zzfk() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfk[] zzmt() {
    if (zzavr == null)
      synchronized (zzyg.zzcfe) {
        if (zzavr == null)
          zzavr = new zzfk[0]; 
      }  
    return zzavr;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfk))
      return false; 
    paramObject = paramObject;
    zzfn zzfn1 = this.zzavs;
    if (zzfn1 == null) {
      if (((zzfk)paramObject).zzavs != null)
        return false; 
    } else if (!zzfn1.equals(((zzfk)paramObject).zzavs)) {
      return false;
    } 
    zzfl zzfl1 = this.zzavt;
    if (zzfl1 == null) {
      if (((zzfk)paramObject).zzavt != null)
        return false; 
    } else if (!zzfl1.equals(((zzfk)paramObject).zzavt)) {
      return false;
    } 
    Boolean bool = this.zzavu;
    if (bool == null) {
      if (((zzfk)paramObject).zzavu != null)
        return false; 
    } else if (!bool.equals(((zzfk)paramObject).zzavu)) {
      return false;
    } 
    String str = this.zzavv;
    if (str == null) {
      if (((zzfk)paramObject).zzavv != null)
        return false; 
    } else if (!str.equals(((zzfk)paramObject).zzavv)) {
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
    int i1 = zzfk.class.getName().hashCode();
    zzfn zzfn1 = this.zzavs;
    byte b = 0;
    if (zzfn1 == null) {
      i = 0;
    } else {
      i = zzfn1.hashCode();
    } 
    zzfl zzfl1 = this.zzavt;
    if (zzfl1 == null) {
      j = 0;
    } else {
      j = zzfl1.hashCode();
    } 
    Boolean bool = this.zzavu;
    if (bool == null) {
      k = 0;
    } else {
      k = bool.hashCode();
    } 
    String str = this.zzavv;
    if (str == null) {
      m = 0;
    } else {
      m = str.hashCode();
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
    zzfn zzfn1 = this.zzavs;
    if (zzfn1 != null)
      paramzzya.zza(1, zzfn1); 
    zzfl zzfl1 = this.zzavt;
    if (zzfl1 != null)
      paramzzya.zza(2, zzfl1); 
    Boolean bool = this.zzavu;
    if (bool != null)
      paramzzya.zzb(3, bool.booleanValue()); 
    String str = this.zzavv;
    if (str != null)
      paramzzya.zzb(4, str); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    zzfn zzfn1 = this.zzavs;
    int i = j;
    if (zzfn1 != null)
      i = j + zzya.zzb(1, zzfn1); 
    zzfl zzfl1 = this.zzavt;
    j = i;
    if (zzfl1 != null)
      j = i + zzya.zzb(2, zzfl1); 
    Boolean bool = this.zzavu;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(3) + 1;
    } 
    String str = this.zzavv;
    j = i;
    if (str != null)
      j = i + zzya.zzc(4, str); 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */