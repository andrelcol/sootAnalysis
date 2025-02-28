package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;

class zzto extends zztn {
  protected final byte[] zzbtz;
  
  zzto(byte[] paramArrayOfbyte) {
    if (paramArrayOfbyte != null) {
      this.zzbtz = paramArrayOfbyte;
      return;
    } 
    throw new NullPointerException();
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzte))
      return false; 
    if (size() != ((zzte)paramObject).size())
      return false; 
    if (size() == 0)
      return true; 
    if (paramObject instanceof zzto) {
      paramObject = paramObject;
      int j = zzuf();
      int i = paramObject.zzuf();
      return (j != 0 && i != 0 && j != i) ? false : zza((zzte)paramObject, 0, size());
    } 
    return paramObject.equals(this);
  }
  
  public int size() {
    return this.zzbtz.length;
  }
  
  protected final int zza(int paramInt1, int paramInt2, int paramInt3) {
    return zzuq.zza(paramInt1, this.zzbtz, zzug(), paramInt3);
  }
  
  protected final String zza(Charset paramCharset) {
    return new String(this.zzbtz, zzug(), size(), paramCharset);
  }
  
  final void zza(zztd paramzztd) throws IOException {
    paramzztd.zza(this.zzbtz, zzug(), size());
  }
  
  final boolean zza(zzte paramzzte, int paramInt1, int paramInt2) {
    if (paramInt2 <= paramzzte.size()) {
      if (paramInt2 <= paramzzte.size()) {
        if (paramzzte instanceof zzto) {
          paramzzte = paramzzte;
          byte[] arrayOfByte2 = this.zzbtz;
          byte[] arrayOfByte1 = ((zzto)paramzzte).zzbtz;
          int j = zzug();
          paramInt1 = zzug();
          for (int i = paramzzte.zzug(); paramInt1 < j + paramInt2; i++) {
            if (arrayOfByte2[paramInt1] != arrayOfByte1[i])
              return false; 
            paramInt1++;
          } 
          return true;
        } 
        return paramzzte.zzb(0, paramInt2).equals(zzb(0, paramInt2));
      } 
      paramInt1 = paramzzte.size();
      StringBuilder stringBuilder1 = new StringBuilder(59);
      stringBuilder1.append("Ran off end of other: 0, ");
      stringBuilder1.append(paramInt2);
      stringBuilder1.append(", ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    paramInt1 = size();
    StringBuilder stringBuilder = new StringBuilder(40);
    stringBuilder.append("Length too large: ");
    stringBuilder.append(paramInt2);
    stringBuilder.append(paramInt1);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public byte zzam(int paramInt) {
    return this.zzbtz[paramInt];
  }
  
  byte zzan(int paramInt) {
    return this.zzbtz[paramInt];
  }
  
  public final zzte zzb(int paramInt1, int paramInt2) {
    paramInt1 = zzte.zzb(0, paramInt2, size());
    return (paramInt1 == 0) ? zzte.zzbts : new zztj(this.zzbtz, zzug(), paramInt1);
  }
  
  public final boolean zzue() {
    int i = zzug();
    return zzxl.zzf(this.zzbtz, i, size() + i);
  }
  
  protected int zzug() {
    return 0;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */