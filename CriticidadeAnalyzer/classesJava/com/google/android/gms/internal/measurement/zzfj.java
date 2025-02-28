package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfj extends zzyc<zzfj> {
  private static volatile zzfj[] zzavl;
  
  public Boolean zzavj = null;
  
  public Boolean zzavk = null;
  
  public Integer zzavm = null;
  
  public String zzavn = null;
  
  public zzfk[] zzavo = zzfk.zzmt();
  
  private Boolean zzavp = null;
  
  public zzfl zzavq = null;
  
  public zzfj() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  public static zzfj[] zzms() {
    if (zzavl == null)
      synchronized (zzyg.zzcfe) {
        if (zzavl == null)
          zzavl = new zzfj[0]; 
      }  
    return zzavl;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfj))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzavm;
    if (integer == null) {
      if (((zzfj)paramObject).zzavm != null)
        return false; 
    } else if (!integer.equals(((zzfj)paramObject).zzavm)) {
      return false;
    } 
    String str = this.zzavn;
    if (str == null) {
      if (((zzfj)paramObject).zzavn != null)
        return false; 
    } else if (!str.equals(((zzfj)paramObject).zzavn)) {
      return false;
    } 
    if (!zzyg.equals((Object[])this.zzavo, (Object[])((zzfj)paramObject).zzavo))
      return false; 
    Boolean bool2 = this.zzavp;
    if (bool2 == null) {
      if (((zzfj)paramObject).zzavp != null)
        return false; 
    } else if (!bool2.equals(((zzfj)paramObject).zzavp)) {
      return false;
    } 
    zzfl zzfl1 = this.zzavq;
    if (zzfl1 == null) {
      if (((zzfj)paramObject).zzavq != null)
        return false; 
    } else if (!zzfl1.equals(((zzfj)paramObject).zzavq)) {
      return false;
    } 
    Boolean bool1 = this.zzavj;
    if (bool1 == null) {
      if (((zzfj)paramObject).zzavj != null)
        return false; 
    } else if (!bool1.equals(((zzfj)paramObject).zzavj)) {
      return false;
    } 
    bool1 = this.zzavk;
    if (bool1 == null) {
      if (((zzfj)paramObject).zzavk != null)
        return false; 
    } else if (!bool1.equals(((zzfj)paramObject).zzavk)) {
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
    int i3 = zzfj.class.getName().hashCode();
    Integer integer = this.zzavm;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.hashCode();
    } 
    String str = this.zzavn;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    int i4 = zzyg.hashCode((Object[])this.zzavo);
    Boolean bool2 = this.zzavp;
    if (bool2 == null) {
      k = 0;
    } else {
      k = bool2.hashCode();
    } 
    zzfl zzfl1 = this.zzavq;
    if (zzfl1 == null) {
      m = 0;
    } else {
      m = zzfl1.hashCode();
    } 
    Boolean bool1 = this.zzavj;
    if (bool1 == null) {
      n = 0;
    } else {
      n = bool1.hashCode();
    } 
    bool1 = this.zzavk;
    if (bool1 == null) {
      i1 = 0;
    } else {
      i1 = bool1.hashCode();
    } 
    zzye zzye = this.zzcev;
    int i2 = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        i2 = b;
      } else {
        i2 = this.zzcev.hashCode();
      }  
    return ((((((((i3 + 527) * 31 + i) * 31 + j) * 31 + i4) * 31 + k) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzavm;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    String str = this.zzavn;
    if (str != null)
      paramzzya.zzb(2, str); 
    zzfk[] arrayOfZzfk = this.zzavo;
    if (arrayOfZzfk != null && arrayOfZzfk.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfZzfk = this.zzavo;
        if (b < arrayOfZzfk.length) {
          zzfk zzfk1 = arrayOfZzfk[b];
          if (zzfk1 != null)
            paramzzya.zza(3, zzfk1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    Boolean bool2 = this.zzavp;
    if (bool2 != null)
      paramzzya.zzb(4, bool2.booleanValue()); 
    zzfl zzfl1 = this.zzavq;
    if (zzfl1 != null)
      paramzzya.zza(5, zzfl1); 
    Boolean bool1 = this.zzavj;
    if (bool1 != null)
      paramzzya.zzb(6, bool1.booleanValue()); 
    bool1 = this.zzavk;
    if (bool1 != null)
      paramzzya.zzb(7, bool1.booleanValue()); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int i = super.zzf();
    Integer integer = this.zzavm;
    int j = i;
    if (integer != null)
      j = i + zzya.zzh(1, integer.intValue()); 
    String str = this.zzavn;
    i = j;
    if (str != null)
      i = j + zzya.zzc(2, str); 
    zzfk[] arrayOfZzfk = this.zzavo;
    j = i;
    if (arrayOfZzfk != null) {
      j = i;
      if (arrayOfZzfk.length > 0) {
        byte b = 0;
        while (true) {
          arrayOfZzfk = this.zzavo;
          j = i;
          if (b < arrayOfZzfk.length) {
            zzfk zzfk1 = arrayOfZzfk[b];
            j = i;
            if (zzfk1 != null)
              j = i + zzya.zzb(3, zzfk1); 
            b++;
            i = j;
            continue;
          } 
          break;
        } 
      } 
    } 
    Boolean bool2 = this.zzavp;
    i = j;
    if (bool2 != null) {
      bool2.booleanValue();
      i = j + zzya.zzbd(4) + 1;
    } 
    zzfl zzfl1 = this.zzavq;
    j = i;
    if (zzfl1 != null)
      j = i + zzya.zzb(5, zzfl1); 
    Boolean bool1 = this.zzavj;
    i = j;
    if (bool1 != null) {
      bool1.booleanValue();
      i = j + zzya.zzbd(6) + 1;
    } 
    bool1 = this.zzavk;
    j = i;
    if (bool1 != null) {
      bool1.booleanValue();
      j = i + zzya.zzbd(7) + 1;
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */