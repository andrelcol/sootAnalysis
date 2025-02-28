package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzfn extends zzyc<zzfn> {
  public Integer zzawe = null;
  
  public String zzawf = null;
  
  public Boolean zzawg = null;
  
  public String[] zzawh = zzyl.zzcfo;
  
  public zzfn() {
    this.zzcev = null;
    this.zzcff = -1;
  }
  
  private final zzfn zzd(zzxz paramzzxz) throws IOException {
    while (true) {
      int i = paramzzxz.zzuj();
      if (i != 0) {
        if (i != 8) {
          if (i != 18) {
            if (i != 24) {
              int k;
              if (i != 34) {
                if (!zza(paramzzxz, i))
                  return this; 
                continue;
              } 
              int m = zzyl.zzb(paramzzxz, 34);
              String[] arrayOfString = this.zzawh;
              if (arrayOfString == null) {
                k = 0;
              } else {
                k = arrayOfString.length;
              } 
              arrayOfString = new String[m + k];
              m = k;
              if (k != 0) {
                System.arraycopy(this.zzawh, 0, arrayOfString, 0, k);
                m = k;
              } 
              while (m < arrayOfString.length - 1) {
                arrayOfString[m] = paramzzxz.readString();
                paramzzxz.zzuj();
                m++;
              } 
              arrayOfString[m] = paramzzxz.readString();
              this.zzawh = arrayOfString;
              continue;
            } 
            this.zzawg = Boolean.valueOf(paramzzxz.zzup());
            continue;
          } 
          this.zzawf = paramzzxz.readString();
          continue;
        } 
        int j = paramzzxz.getPosition();
        try {
          int k = paramzzxz.zzvb();
          if (k >= 0 && k <= 6) {
            this.zzawe = Integer.valueOf(k);
            continue;
          } 
          IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
          StringBuilder stringBuilder = new StringBuilder();
          this(41);
          stringBuilder.append(k);
          stringBuilder.append(" is not a valid enum MatchType");
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
    if (!(paramObject instanceof zzfn))
      return false; 
    paramObject = paramObject;
    Integer integer = this.zzawe;
    if (integer == null) {
      if (((zzfn)paramObject).zzawe != null)
        return false; 
    } else if (!integer.equals(((zzfn)paramObject).zzawe)) {
      return false;
    } 
    String str = this.zzawf;
    if (str == null) {
      if (((zzfn)paramObject).zzawf != null)
        return false; 
    } else if (!str.equals(((zzfn)paramObject).zzawf)) {
      return false;
    } 
    Boolean bool = this.zzawg;
    if (bool == null) {
      if (((zzfn)paramObject).zzawg != null)
        return false; 
    } else if (!bool.equals(((zzfn)paramObject).zzawg)) {
      return false;
    } 
    if (!zzyg.equals((Object[])this.zzawh, (Object[])((zzfn)paramObject).zzawh))
      return false; 
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
    int n = zzfn.class.getName().hashCode();
    Integer integer = this.zzawe;
    byte b = 0;
    if (integer == null) {
      i = 0;
    } else {
      i = integer.intValue();
    } 
    String str = this.zzawf;
    if (str == null) {
      j = 0;
    } else {
      j = str.hashCode();
    } 
    Boolean bool = this.zzawg;
    if (bool == null) {
      k = 0;
    } else {
      k = bool.hashCode();
    } 
    int i1 = zzyg.hashCode((Object[])this.zzawh);
    zzye zzye = this.zzcev;
    int m = b;
    if (zzye != null)
      if (zzye.isEmpty()) {
        m = b;
      } else {
        m = this.zzcev.hashCode();
      }  
    return (((((n + 527) * 31 + i) * 31 + j) * 31 + k) * 31 + i1) * 31 + m;
  }
  
  public final void zza(zzya paramzzya) throws IOException {
    Integer integer = this.zzawe;
    if (integer != null)
      paramzzya.zzd(1, integer.intValue()); 
    String str = this.zzawf;
    if (str != null)
      paramzzya.zzb(2, str); 
    Boolean bool = this.zzawg;
    if (bool != null)
      paramzzya.zzb(3, bool.booleanValue()); 
    String[] arrayOfString = this.zzawh;
    if (arrayOfString != null && arrayOfString.length > 0) {
      byte b = 0;
      while (true) {
        arrayOfString = this.zzawh;
        if (b < arrayOfString.length) {
          String str1 = arrayOfString[b];
          if (str1 != null)
            paramzzya.zzb(4, str1); 
          b++;
          continue;
        } 
        break;
      } 
    } 
    super.zza(paramzzya);
  }
  
  protected final int zzf() {
    int j = super.zzf();
    Integer integer = this.zzawe;
    int i = j;
    if (integer != null)
      i = j + zzya.zzh(1, integer.intValue()); 
    String str = this.zzawf;
    j = i;
    if (str != null)
      j = i + zzya.zzc(2, str); 
    Boolean bool = this.zzawg;
    i = j;
    if (bool != null) {
      bool.booleanValue();
      i = j + zzya.zzbd(3) + 1;
    } 
    String[] arrayOfString = this.zzawh;
    j = i;
    if (arrayOfString != null) {
      j = i;
      if (arrayOfString.length > 0) {
        byte b = 0;
        int m = 0;
        int k = 0;
        while (true) {
          arrayOfString = this.zzawh;
          if (b < arrayOfString.length) {
            String str1 = arrayOfString[b];
            int n = m;
            j = k;
            if (str1 != null) {
              j = k + 1;
              n = m + zzya.zzgc(str1);
            } 
            b++;
            m = n;
            k = j;
            continue;
          } 
          j = i + m + k * 1;
          break;
        } 
      } 
    } 
    return j;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */