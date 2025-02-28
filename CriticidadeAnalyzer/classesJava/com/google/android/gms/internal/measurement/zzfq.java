package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfq extends zzyc<zzfq> {
  private static volatile zzfq[] zzawt;
  
  public String value = null;
  
  public String zzoj = null;
  
  public zzfq() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfq[] zzmw() {
    if (zzawt == null)
      synchronized (zzyg.zzcfe) {
        if (zzawt == null)
          zzawt = new zzfq[0]; 
      }  
    return zzawt;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfq))
      return false; 
    paramObject = paramObject;
    String str = this.zzoj;
    if (str == null) {
      if (((zzfq)paramObject).zzoj != null)
        return false; 
    } else if (!str.equals(((zzfq)paramObject).zzoj)) {
      return false;
    } 
    str = this.value;
    if (str == null) {
      if (((zzfq)paramObject).value != null)
        return false; 
    } else if (!str.equals(((zzfq)paramObject).value)) {
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
    int m = zzfq.class.getName().hashCode();
    String str = this.zzoj;
    byte b = 0;
    if (str == null) {
      i = 0;
    } else {
      i = str.hashCode();
    } 
    str = this.value;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
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
    String str = this.zzoj;
    if (str != null)
      paramzzya.zzb(1, str); 
    str = this.value;
    if (str != null)
      paramzzya.zzb(2, str); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    String str = this.zzoj;
    int i = j;
    if (str != null)
      i = j + zzya.zzc(1, str); 
    str = this.value;
    j = i;
    if (str != null)
      j = i + zzya.zzc(2, str); 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */