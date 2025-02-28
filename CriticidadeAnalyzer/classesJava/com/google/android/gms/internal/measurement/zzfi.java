package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfi extends zzyc<zzfi> {
  private static volatile zzfi[] zzavf;
  
  public Integer zzavg = null;
  
  public zzfm[] zzavh = zzfm.zzmu();
  
  public zzfj[] zzavi = zzfj.zzms();
  
  private Boolean zzavj = null;
  
  private Boolean zzavk = null;
  
  public zzfi() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfi[] zzmr() {
    if (zzavf == null)
      synchronized (zzyg.zzcfe) {
        if (zzavf == null)
          zzavf = new zzfi[0]; 
      }  
    return zzavf;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfi))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzavg;
    if (integer == null) {
      if (((zzfi)paramObject).zzavg != null)
        return false; 
    } else if (!integer.equals(((zzfi)paramObject).zzavg)) {
      return false;
    } 
    if (!zzyg.equals((Object[])this.zzavh, (Object[])((zzfi)paramObject).zzavh))
      return false; 
    if (!zzyg.equals((Object[])this.zzavi, (Object[])((zzfi)paramObject).zzavi))
      return false; 
    Boolean bool = this.zzavj;
    if (bool == null) {
      if (((zzfi)paramObject).zzavj != null)
        return false; 
    } else if (!bool.equals(((zzfi)paramObject).zzavj)) {
      return false;
    } 
    bool = this.zzavk;
    if (bool == null) {
      if (((zzfi)paramObject).zzavk != null)
        return false; 
    } else if (!bool.equals(((zzfi)paramObject).zzavk)) {
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
    int n = zzfi.class.getName().hashCode();
    Integer integer = this.zzavg;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    int i2 = zzyg.hashCode((Object[])this.zzavh);
    int i1 = zzyg.hashCode((Object[])this.zzavi);
    Boolean bool = this.zzavj;
    if (bool == null) {
      j = 0;
    } else {
      j = bool.hashCode();
    } 
    bool = this.zzavk;
    if (bool == null) {
      k = 0;
    } else {
      k = bool.hashCode();
    } 
    zzye zzye = this.zzcev;
    int m = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        m = b;
      } else {
        m = this.zzcev.hashCode();
      }  
    return ((((((n + 527) * 31 + i) * 31 + i2) * 31 + i1) * 31 + j) * 31 + k) * 31 + m;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzavg;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    zzfm[] arrayOfZzfm = this.zzavh;
    byte b = 0;
    if (arrayOfZzfm != null && arrayOfZzfm.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfZzfm = this.zzavh;
        if (b1 < arrayOfZzfm.length) {
          zzfm zzfm1 = arrayOfZzfm[b1];
          if (zzfm1 != null)
            paramzzya.zza(2, zzfm1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    zzfj[] arrayOfZzfj = this.zzavi;
    if (arrayOfZzfj != null && arrayOfZzfj.length > 0) {
      byte b1 = b;
      while (true) {
        arrayOfZzfj = this.zzavi;
        if (b1 < arrayOfZzfj.length) {
          zzfj zzfj1 = arrayOfZzfj[b1];
          if (zzfj1 != null)
            paramzzya.zza(3, zzfj1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    Boolean bool = this.zzavj;
    if (bool != null)
      paramzzya.zzb(4, bool.booleanValue()); 
    bool = this.zzavk;
    if (bool != null)
      paramzzya.zzb(5, bool.booleanValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int i = super.zzf();
    Integer integer = this.zzavg;
    int j = i;
    if (integer != null)
      j = i + zzya.zzh(1, integer.intValue()); 
    zzfm[] arrayOfZzfm = this.zzavh;
    byte b = 0;
    i = j;
    if (arrayOfZzfm != null) {
      i = j;
      if (arrayOfZzfm.length > 0) {
        i = j;
        byte b1 = 0;
        while (true) {
          arrayOfZzfm = this.zzavh;
          if (b1 < arrayOfZzfm.length) {
            zzfm zzfm1 = arrayOfZzfm[b1];
            j = i;
            if (zzfm1 != null)
              j = i + zzya.zzb(2, zzfm1); 
            b1++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    zzfj[] arrayOfZzfj = this.zzavi;
    j = i;
    if (arrayOfZzfj != null) {
      j = i;
      if (arrayOfZzfj.length > 0) {
        byte b1 = b;
        while (true) {
          arrayOfZzfj = this.zzavi;
          j = i;
          if (b1 < arrayOfZzfj.length) {
            zzfj zzfj1 = arrayOfZzfj[b1];
            j = i;
            if (zzfj1 != null)
              j = i + zzya.zzb(3, zzfj1); 
            b1++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    Boolean bool = this.zzavj;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(4) + 1;
    } 
    bool = this.zzavk;
    j = i;
    if (bool != null) {
      bool.booleanValue();
      j = i + zzya.zzbd(5) + 1;
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */