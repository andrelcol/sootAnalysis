package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzte implements Serializable, Iterable<Byte> {
  static {
    if (zztb.zzub()) {
      zztp zztp = new zztp(null);
    } else {
      zzti = new zzti(null);
    } 
    zzbtt = zzti;
    new zztg();
  }
  
  private static int zza(byte paramByte) {
    return paramByte & 0xFF;
  }
  
  static zztm zzao(int paramInt) {
    return new zztm(paramInt, null);
  }
  
  static int zzb(int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2 - paramInt1;
    if ((paramInt1 | paramInt2 | i | paramInt3 - paramInt2) < 0) {
      if (paramInt1 >= 0) {
        if (paramInt2 < paramInt1) {
          StringBuilder stringBuilder2 = new StringBuilder(66);
          stringBuilder2.append("Beginning index larger than ending index: ");
          stringBuilder2.append(paramInt1);
          stringBuilder2.append(", ");
          stringBuilder2.append(paramInt2);
          throw new IndexOutOfBoundsException(stringBuilder2.toString());
        } 
        StringBuilder stringBuilder1 = new StringBuilder(37);
        stringBuilder1.append("End index: ");
        stringBuilder1.append(paramInt2);
        stringBuilder1.append(" >= ");
        stringBuilder1.append(paramInt3);
        throw new IndexOutOfBoundsException(stringBuilder1.toString());
      } 
      StringBuilder stringBuilder = new StringBuilder(32);
      stringBuilder.append("Beginning index: ");
      stringBuilder.append(paramInt1);
      stringBuilder.append(" < 0");
      throw new IndexOutOfBoundsException(stringBuilder.toString());
    } 
    return i;
  }
  
  public static zzte zzb(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    zzb(paramInt1, paramInt1 + paramInt2, paramArrayOfbyte.length);
    return new zzto(zzbtt.zzc(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  public static zzte zzga(String paramString) {
    return new zzto(paramString.getBytes(zzuq.UTF_8));
  }
  
  static zzte zzi(byte[] paramArrayOfbyte) {
    return new zzto(paramArrayOfbyte);
  }
  
  public abstract boolean equals(Object paramObject);
  
  public final int hashCode() {
    int j = this.zzbsk;
    int i = j;
    if (j == 0) {
      i = size();
      j = zza(i, 0, i);
      i = j;
      if (j == 0)
        i = 1; 
      this.zzbsk = i;
    } 
    return i;
  }
  
  public abstract int size();
  
  public final String toString() {
    return String.format("<ByteString@%s size=%d>", new Object[] { Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()) });
  }
  
  protected abstract int zza(int paramInt1, int paramInt2, int paramInt3);
  
  protected abstract String zza(Charset paramCharset);
  
  abstract void zza(zztd paramzztd) throws IOException;
  
  public abstract byte zzam(int paramInt);
  
  abstract byte zzan(int paramInt);
  
  public abstract zzte zzb(int paramInt1, int paramInt2);
  
  public final String zzud() {
    Charset charset = zzuq.UTF_8;
    return (size() == 0) ? "" : zza(charset);
  }
  
  public abstract boolean zzue();
  
  protected final int zzuf() {
    return this.zzbsk;
  }
  
  static {
    zzti zzti;
  }
  
  public static final zzte zzbts = new zzto(zzuq.zzbzc);
  
  private static final zztk zzbtt;
  
  private int zzbsk = 0;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzte.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */