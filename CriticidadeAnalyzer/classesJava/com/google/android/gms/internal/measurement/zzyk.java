package com.google.android.gms.internal.measurement;

import java.util.Arrays;

final class zzyk {
  final int tag;
  
  final byte[] zzbtz;
  
  zzyk(int paramInt, byte[] paramArrayOfbyte) {
    this.tag = paramInt;
    this.zzbtz = paramArrayOfbyte;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzyk))
      return false; 
    paramObject = paramObject;
    return (this.tag == ((zzyk)paramObject).tag && Arrays.equals(this.zzbtz, ((zzyk)paramObject).zzbtz));
  }
  
  public final int hashCode() {
    return (this.tag + 527) * 31 + Arrays.hashCode(this.zzbtz);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */