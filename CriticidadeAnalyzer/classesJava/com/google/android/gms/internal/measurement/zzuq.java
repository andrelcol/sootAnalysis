package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class zzuq {
  static final Charset UTF_8 = Charset.forName("UTF-8");
  
  public static final byte[] zzbzc;
  
  static {
    Charset.forName("ISO-8859-1");
    byte[] arrayOfByte = new byte[0];
    zzbzc = arrayOfByte;
    ByteBuffer.wrap(arrayOfByte);
    arrayOfByte = zzbzc;
    zztq.zza(arrayOfByte, 0, arrayOfByte.length, false);
  }
  
  static <T> T checkNotNull(T paramT) {
    if (paramT != null)
      return paramT; 
    throw new NullPointerException();
  }
  
  public static int hashCode(byte[] paramArrayOfbyte) {
    int i = paramArrayOfbyte.length;
    int j = zza(i, paramArrayOfbyte, 0, i);
    i = j;
    if (j == 0)
      i = 1; 
    return i;
  }
  
  static int zza(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    int i = paramInt1;
    for (paramInt1 = paramInt2; paramInt1 < paramInt2 + paramInt3; paramInt1++)
      i = i * 31 + paramArrayOfbyte[paramInt1]; 
    return i;
  }
  
  static <T> T zza(T paramT, String paramString) {
    if (paramT != null)
      return paramT; 
    throw new NullPointerException(paramString);
  }
  
  static Object zzb(Object paramObject1, Object paramObject2) {
    paramObject1 = ((zzvv)paramObject1).zzwh();
    paramObject1.zza((zzvv)paramObject2);
    return paramObject1.zzwn();
  }
  
  public static int zzbd(long paramLong) {
    return (int)(paramLong ^ paramLong >>> 32L);
  }
  
  static boolean zzf(zzvv paramzzvv) {
    return false;
  }
  
  public static boolean zzl(byte[] paramArrayOfbyte) {
    return zzxl.zzl(paramArrayOfbyte);
  }
  
  public static String zzm(byte[] paramArrayOfbyte) {
    return new String(paramArrayOfbyte, UTF_8);
  }
  
  public static int zzu(boolean paramBoolean) {
    return paramBoolean ? 1231 : 1237;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzuq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */