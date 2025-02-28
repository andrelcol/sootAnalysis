package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfv extends zzyc<zzfv> {
  public zzfw[] zzaxh = zzfw.zznb();
  
  public zzfv() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfv))
      return false; 
    zzfv zzfv1 = (zzfv)paramObject;
    if (!zzyg.equals((Object[])this.zzaxh, (Object[])zzfv1.zzaxh))
      return false; 
    paramObject = this.zzcev;
    if (paramObject == null || paramObject.isEmpty()) {
      paramObject = zzfv1.zzcev;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzcev.equals(zzfv1.zzcev);
  }
  
  public final int hashCode() {
    int k = zzfv.class.getName().hashCode();
    int j = zzyg.hashCode((Object[])this.zzaxh);
    zzye zzye = this.zzcev;
    if (zzye == null || zzye.isEmpty()) {
      byte b = 0;
      return ((k + 527) * 31 + j) * 31 + b;
    } 
    int i = this.zzcev.hashCode();
    return ((k + 527) * 31 + j) * 31 + i;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    zzfw[] arrayOfZzfw = this.zzaxh;
    if (arrayOfZzfw != null && arrayOfZzfw.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfZzfw = this.zzaxh;
        if (b < arrayOfZzfw.length) {
          zzfw zzfw1 = arrayOfZzfw[b];
          if (zzfw1 != null)
            paramzzya.zza(1, zzfw1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int i = super.zzf();
    zzfw[] arrayOfZzfw = this.zzaxh;
    int j = i;
    if (arrayOfZzfw != null) {
      j = i;
      if (arrayOfZzfw.length > 0) {
        byte b = 0;
        while (true) {
          arrayOfZzfw = this.zzaxh;
          j = i;
          if (b < arrayOfZzfw.length) {
            zzfw zzfw1 = arrayOfZzfw[b];
            j = i;
            if (zzfw1 != null)
              j = i + zzya.zzb(1, zzfw1); 
            b++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */