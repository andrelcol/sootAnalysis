package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfx extends zzyc<zzfx> {
  public long[] zzayp;
  
  public long[] zzayq;
  
  public zzfs[] zzayr;
  
  public zzfy[] zzays;
  
  public zzfx() {
    long[] arrayOfLong = zzyl.zzcfk;
    this.zzayp = arrayOfLong;
    this.zzayq = arrayOfLong;
    this.zzayr = zzfs.zzmy();
    this.zzays = zzfy.zznc();
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfx))
      return false; 
    zzfx zzfx1 = (zzfx)paramObject;
    if (!zzyg.equals(this.zzayp, zzfx1.zzayp))
      return false; 
    if (!zzyg.equals(this.zzayq, zzfx1.zzayq))
      return false; 
    if (!zzyg.equals((Object[])this.zzayr, (Object[])zzfx1.zzayr))
      return false; 
    if (!zzyg.equals((Object[])this.zzays, (Object[])zzfx1.zzays))
      return false; 
    paramObject = this.zzcev;
    if (paramObject == null || paramObject.isEmpty()) {
      paramObject = zzfx1.zzcev;
      return (paramObject == null || paramObject.isEmpty());
    } 
    return this.zzcev.equals(zzfx1.zzcev);
  }
  
  public final int hashCode() {
    int m = zzfx.class.getName().hashCode();
    int j = zzyg.hashCode(this.zzayp);
    int n = zzyg.hashCode(this.zzayq);
    int k = zzyg.hashCode((Object[])this.zzayr);
    int i1 = zzyg.hashCode((Object[])this.zzays);
    zzye zzye = this.zzcev;
    if (zzye == null || zzye.isEmpty()) {
      byte b = 0;
      return (((((m + 527) * 31 + j) * 31 + n) * 31 + k) * 31 + i1) * 31 + b;
    } 
    int i = this.zzcev.hashCode();
    return (((((m + 527) * 31 + j) * 31 + n) * 31 + k) * 31 + i1) * 31 + i;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    long[] arrayOfLong = this.zzayp;
    byte b = 0;
    if (arrayOfLong != null && arrayOfLong.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfLong = this.zzayp;
        if (b1 < arrayOfLong.length) {
          paramzzya.zza(1, arrayOfLong[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    arrayOfLong = this.zzayq;
    if (arrayOfLong != null && arrayOfLong.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfLong = this.zzayq;
        if (b1 < arrayOfLong.length) {
          paramzzya.zza(2, arrayOfLong[b1]);
          b1++;
          continue;
        } 
        break;
      } 
    } 
    zzfs[] arrayOfZzfs = this.zzayr;
    if (arrayOfZzfs != null && arrayOfZzfs.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfZzfs = this.zzayr;
        if (b1 < arrayOfZzfs.length) {
          zzfs zzfs1 = arrayOfZzfs[b1];
          if (zzfs1 != null)
            paramzzya.zza(3, zzfs1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    zzfy[] arrayOfZzfy = this.zzays;
    if (arrayOfZzfy != null && arrayOfZzfy.length > 0) {
      byte b1 = b;
      while (true) {
        arrayOfZzfy = this.zzays;
        if (b1 < arrayOfZzfy.length) {
          zzfy zzfy1 = arrayOfZzfy[b1];
          if (zzfy1 != null)
            paramzzya.zza(4, zzfy1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int k = super.zzf();
    long[] arrayOfLong = this.zzayp;
    boolean bool = false;
    int i = k;
    if (arrayOfLong != null) {
      i = k;
      if (arrayOfLong.length > 0) {
        byte b = 0;
        i = 0;
        while (true) {
          arrayOfLong = this.zzayp;
          if (b < arrayOfLong.length) {
            i += zzya.zzbg(arrayOfLong[b]);
            b++;
            continue;
          } 
          i = k + i + arrayOfLong.length * 1;
          break;
        } 
      } 
    } 
    arrayOfLong = this.zzayq;
    int j = i;
    if (arrayOfLong != null) {
      j = i;
      if (arrayOfLong.length > 0) {
        k = 0;
        j = 0;
        while (true) {
          arrayOfLong = this.zzayq;
          if (k < arrayOfLong.length) {
            j += zzya.zzbg(arrayOfLong[k]);
            k++;
            continue;
          } 
          j = i + j + arrayOfLong.length * 1;
          break;
        } 
      } 
    } 
    zzfs[] arrayOfZzfs = this.zzayr;
    i = j;
    if (arrayOfZzfs != null) {
      i = j;
      if (arrayOfZzfs.length > 0) {
        i = j;
        k = 0;
        while (true) {
          arrayOfZzfs = this.zzayr;
          if (k < arrayOfZzfs.length) {
            zzfs zzfs1 = arrayOfZzfs[k];
            j = i;
            if (zzfs1 != null)
              j = i + zzya.zzb(3, zzfs1); 
            k++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    zzfy[] arrayOfZzfy = this.zzays;
    k = i;
    if (arrayOfZzfy != null) {
      k = i;
      if (arrayOfZzfy.length > 0) {
        j = bool;
        while (true) {
          arrayOfZzfy = this.zzays;
          k = i;
          if (j < arrayOfZzfy.length) {
            zzfy zzfy1 = arrayOfZzfy[j];
            k = i;
            if (zzfy1 != null)
              k = i + zzya.zzb(4, zzfy1); 
            j++;
            i = k;
            continue;
          } 
          break;
        } 
      } 
    } 
    return k;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */