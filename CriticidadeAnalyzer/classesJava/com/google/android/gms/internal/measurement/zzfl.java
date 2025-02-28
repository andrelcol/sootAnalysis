package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfl extends zzyc<zzfl> {
  public Integer zzavw = null;
  
  public Boolean zzavx = null;
  
  public String zzavy = null;
  
  public String zzavz = null;
  
  public String zzawa = null;
  
  public zzfl() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  private final zzfl zzc(zzxz paramzzxz) throws IOException {
    while (true) {
      int i = paramzzxz.zzuj();
      if (i != 0) {
        if (i != 8) {
          if (i != 16) {
            if (i != 26) {
              if (i != 34) {
                if (i != 42) {
                  if (!zza(paramzzxz, i))
                    return this; 
                  continue;
                } 
                this.zzawa = paramzzxz.readString();
                continue;
              } 
              this.zzavz = paramzzxz.readString();
              continue;
            } 
            this.zzavy = paramzzxz.readString();
            continue;
          } 
          this.zzavx = Boolean.valueOf(paramzzxz.zzup());
          continue;
        } 
        int j = paramzzxz.getPosition();
        try {
          int k = paramzzxz.zzvb();
          if (k >= 0 && k <= 4) {
            this.zzavw = Integer.valueOf(k);
            continue;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          StringBuilder stringBuilder = new StringBuilder();
          this(46);
          stringBuilder.append(k);
          stringBuilder.append(" is not a valid enum ComparisonType");
          this(stringBuilder.toString());
          throw illegalArgumentException;
        } catch (IllegalArgumentException illegalArgumentException) {
          paramzzxz.zzcb(j);
          zza(paramzzxz, i);
          continue;
        } 
      } 
      return this;
    } 
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzfl))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzavw;
    if (integer == null) {
      if (((zzfl)paramObject).zzavw != null)
        return false; 
    } else if (!integer.equals(((zzfl)paramObject).zzavw)) {
      return false;
    } 
    Boolean bool = this.zzavx;
    if (bool == null) {
      if (((zzfl)paramObject).zzavx != null)
        return false; 
    } else if (!bool.equals(((zzfl)paramObject).zzavx)) {
      return false;
    } 
    String str = this.zzavy;
    if (str == null) {
      if (((zzfl)paramObject).zzavy != null)
        return false; 
    } else if (!str.equals(((zzfl)paramObject).zzavy)) {
      return false;
    } 
    str = this.zzavz;
    if (str == null) {
      if (((zzfl)paramObject).zzavz != null)
        return false; 
    } else if (!str.equals(((zzfl)paramObject).zzavz)) {
      return false;
    } 
    str = this.zzawa;
    if (str == null) {
      if (((zzfl)paramObject).zzawa != null)
        return false; 
    } else if (!str.equals(((zzfl)paramObject).zzawa)) {
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
    int i2 = zzfl.class.getName().hashCode();
    Integer integer = this.zzavw;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.intValue();
    } 
    Boolean bool = this.zzavx;
    if (bool == null) {
      j = 0;
    } else {
      j = bool.hashCode();
    } 
    String str = this.zzavy;
    if (str == null) {
      k = 0;
    } else {
      k = str.hashCode();
    } 
    str = this.zzavz;
    if (str == null) {
      m = 0;
    } else {
      m = str.hashCode();
    } 
    str = this.zzawa;
    if (str == null) {
      n = 0;
    } else {
      n = str.hashCode();
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
    Integer integer = this.zzavw;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    Boolean bool = this.zzavx;
    if (bool != null)
      paramzzya.zzb(2, bool.booleanValue()); 
    String str = this.zzavy;
    if (str != null)
      paramzzya.zzb(3, str); 
    str = this.zzavz;
    if (str != null)
      paramzzya.zzb(4, str); 
    str = this.zzawa;
    if (str != null)
      paramzzya.zzb(5, str); 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Integer integer = this.zzavw;
    int i = j;
    if (integer != null)
      i = j + zzya.zzh(1, integer.intValue()); 
    Boolean bool = this.zzavx;
    j = i;
    if (bool != null) {
      bool.booleanValue();
      j = i + zzya.zzbd(2) + 1;
    } 
    String str = this.zzavy;
    i = j;
    if (str != null)
      i = j + zzya.zzc(3, str); 
    str = this.zzavz;
    j = i;
    if (str != null)
      j = i + zzya.zzc(4, str); 
    str = this.zzawa;
    i = j;
    if (str != null)
      i = j + zzya.zzc(5, str); 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */