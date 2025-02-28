package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfp extends zzyc<zzfp> {
  public String zzafi = null;
  
  public Long zzawm = null;
  
  private Integer zzawn = null;
  
  public zzfq[] zzawo = zzfq.zzmw();
  
  public zzfo[] zzawp = zzfo.zzmv();
  
  public zzfi[] zzawq = zzfi.zzmr();
  
  private String zzawr = null;
  
  private Boolean zzaws = null;
  
  public zzfp() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfp))
      return false; 
    paramObject = paramObject;
    Long long_ = this.zzawm;
    if (long_ == null) {
      if (((zzfp)paramObject).zzawm != null)
        return false; 
    } else if (!long_.equals(((zzfp)paramObject).zzawm)) {
      return false;
    } 
    String str2 = this.zzafi;
    if (str2 == null) {
      if (((zzfp)paramObject).zzafi != null)
        return false; 
    } else if (!str2.equals(((zzfp)paramObject).zzafi)) {
      return false;
    } 
    Integer integer = this.zzawn;
    if (integer == null) {
      if (((zzfp)paramObject).zzawn != null)
        return false; 
    } else if (!integer.equals(((zzfp)paramObject).zzawn)) {
      return false;
    } 
    if (!zzyg.equals((Object[])this.zzawo, (Object[])((zzfp)paramObject).zzawo))
      return false; 
    if (!zzyg.equals((Object[])this.zzawp, (Object[])((zzfp)paramObject).zzawp))
      return false; 
    if (!zzyg.equals((Object[])this.zzawq, (Object[])((zzfp)paramObject).zzawq))
      return false; 
    String str1 = this.zzawr;
    if (str1 == null) {
      if (((zzfp)paramObject).zzawr != null)
        return false; 
    } else if (!str1.equals(((zzfp)paramObject).zzawr)) {
      return false;
    } 
    Boolean bool = this.zzaws;
    if (bool == null) {
      if (((zzfp)paramObject).zzaws != null)
        return false; 
    } else if (!bool.equals(((zzfp)paramObject).zzaws)) {
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
    int i2 = zzfp.class.getName().hashCode();
    Long long_ = this.zzawm;
    byte b = 0;
    if (long_ == null) {
      i = 0;
    } else {
      i = long_.hashCode();
    } 
    String str2 = this.zzafi;
    if (str2 == null) {
      j = 0;
    } else {
      j = str2.hashCode();
    } 
    Integer integer = this.zzawn;
    if (integer == null) {
      k = 0;
    } else {
      k = integer.hashCode();
    } 
    int i3 = zzyg.hashCode((Object[])this.zzawo);
    int i5 = zzyg.hashCode((Object[])this.zzawp);
    int i4 = zzyg.hashCode((Object[])this.zzawq);
    String str1 = this.zzawr;
    if (str1 == null) {
      m = 0;
    } else {
      m = str1.hashCode();
    } 
    Boolean bool = this.zzaws;
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
    return (((((((((i2 + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + i3) * 31 + i5) * 31 + i4) * 31 + m) * 31 + n) * 31 + i1;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Long long_ = this.zzawm;
    if (long_ != null)
      paramzzya.zzi(1, long_.longValue()); 
    String str2 = this.zzafi;
    if (str2 != null)
      paramzzya.zzb(2, str2); 
    Integer integer = this.zzawn;
    if (integer != null)
      paramzzya.zzd(3, integer.intValue()); 
    zzfq[] arrayOfZzfq = this.zzawo;
    byte b = 0;
    if (arrayOfZzfq != null && arrayOfZzfq.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfZzfq = this.zzawo;
        if (b1 < arrayOfZzfq.length) {
          zzfq zzfq1 = arrayOfZzfq[b1];
          if (zzfq1 != null)
            paramzzya.zza(4, zzfq1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    zzfo[] arrayOfZzfo = this.zzawp;
    if (arrayOfZzfo != null && arrayOfZzfo.length > 0) {
      byte b1 = 0;
      while (true) {
        arrayOfZzfo = this.zzawp;
        if (b1 < arrayOfZzfo.length) {
          zzfo zzfo1 = arrayOfZzfo[b1];
          if (zzfo1 != null)
            paramzzya.zza(5, zzfo1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    zzfi[] arrayOfZzfi = this.zzawq;
    if (arrayOfZzfi != null && arrayOfZzfi.length > 0) {
      byte b1 = b;
      while (true) {
        arrayOfZzfi = this.zzawq;
        if (b1 < arrayOfZzfi.length) {
          zzfi zzfi1 = arrayOfZzfi[b1];
          if (zzfi1 != null)
            paramzzya.zza(6, zzfi1); 
          b1++;
          continue;
        } 
        break;
      } 
    } 
    String str1 = this.zzawr;
    if (str1 != null)
      paramzzya.zzb(7, str1); 
    Boolean bool = this.zzaws;
    if (bool != null)
      paramzzya.zzb(8, bool.booleanValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Long long_ = this.zzawm;
    int i = j;
    if (long_ != null)
      i = j + zzya.zzd(1, long_.longValue()); 
    String str2 = this.zzafi;
    j = i;
    if (str2 != null)
      j = i + zzya.zzc(2, str2); 
    Integer integer = this.zzawn;
    i = j;
    if (integer != null)
      i = j + zzya.zzh(3, integer.intValue()); 
    zzfq[] arrayOfZzfq = this.zzawo;
    byte b = 0;
    j = i;
    if (arrayOfZzfq != null) {
      j = i;
      if (arrayOfZzfq.length > 0) {
        byte b1 = 0;
        while (true) {
          arrayOfZzfq = this.zzawo;
          if (b1 < arrayOfZzfq.length) {
            zzfq zzfq1 = arrayOfZzfq[b1];
            j = i;
            if (zzfq1 != null)
              j = i + zzya.zzb(4, zzfq1); 
            b1++;
            i = j;
            continue;
          } 
          j = i;
          break;
        } 
      } 
    } 
    zzfo[] arrayOfZzfo = this.zzawp;
    i = j;
    if (arrayOfZzfo != null) {
      i = j;
      if (arrayOfZzfo.length > 0) {
        i = j;
        j = 0;
        while (true) {
          arrayOfZzfo = this.zzawp;
          if (j < arrayOfZzfo.length) {
            zzfo zzfo1 = arrayOfZzfo[j];
            int k = i;
            if (zzfo1 != null)
              k = i + zzya.zzb(5, zzfo1); 
            j++;
            i = k;
            continue;
          } 
          break;
        } 
      } 
    } 
    zzfi[] arrayOfZzfi = this.zzawq;
    j = i;
    if (arrayOfZzfi != null) {
      j = i;
      if (arrayOfZzfi.length > 0) {
        byte b1 = b;
        while (true) {
          arrayOfZzfi = this.zzawq;
          j = i;
          if (b1 < arrayOfZzfi.length) {
            zzfi zzfi1 = arrayOfZzfi[b1];
            j = i;
            if (zzfi1 != null)
              j = i + zzya.zzb(6, zzfi1); 
            b1++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    String str1 = this.zzawr;
    i = j;
    if (str1 != null)
      i = j + zzya.zzc(7, str1); 
    Boolean bool = this.zzaws;
    j = i;
    if (bool != null) {
      bool.booleanValue();
      j = i + zzya.zzbd(8) + 1;
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */