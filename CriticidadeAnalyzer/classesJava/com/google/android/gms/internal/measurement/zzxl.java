package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

final class zzxl {
  private static final zzxn zzcdi;
  
  static {
    boolean bool;
    zzxo zzxo;
    if (zzxj.zzyo() && zzxj.zzyp()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool && !zztb.zzub()) {
      zzxq zzxq = new zzxq();
    } else {
      zzxo = new zzxo();
    } 
    zzcdi = zzxo;
  }
  
  static int zza(CharSequence paramCharSequence) {
    int k;
    int n = paramCharSequence.length();
    int m = 0;
    int j;
    for (j = 0; j < n && paramCharSequence.charAt(j) < ''; j++);
    int i = n;
    while (true) {
      k = i;
      if (j < n) {
        k = paramCharSequence.charAt(j);
        if (k < 2048) {
          i += 127 - k >>> 31;
          j++;
          continue;
        } 
        int i1 = paramCharSequence.length();
        k = m;
        while (j < i1) {
          char c = paramCharSequence.charAt(j);
          if (c < 'ࠀ') {
            k += 127 - c >>> 31;
            m = j;
          } else {
            int i2 = k + 2;
            k = i2;
            m = j;
            if ('?' <= c) {
              k = i2;
              m = j;
              if (c <= '?')
                if (Character.codePointAt(paramCharSequence, j) >= 65536) {
                  m = j + 1;
                  k = i2;
                } else {
                  throw new zzxp(j, i1);
                }  
            } 
          } 
          j = m + 1;
        } 
        k = i + k;
      } 
      break;
    } 
    if (k >= n)
      return k; 
    long l = k;
    paramCharSequence = new StringBuilder(54);
    paramCharSequence.append("UTF-8 length does not fit in int: ");
    paramCharSequence.append(l + 4294967296L);
    throw new IllegalArgumentException(paramCharSequence.toString());
  }
  
  static int zza(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return zzcdi.zzb(paramCharSequence, paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  static void zza(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    zzxn zzxn1 = zzcdi;
    if (paramByteBuffer.hasArray()) {
      int i = paramByteBuffer.arrayOffset();
      paramByteBuffer.position(zza(paramCharSequence, paramByteBuffer.array(), paramByteBuffer.position() + i, paramByteBuffer.remaining()) - i);
      return;
    } 
    if (paramByteBuffer.isDirect()) {
      zzxn1.zzb(paramCharSequence, paramByteBuffer);
      return;
    } 
    zzxn.zzc(paramCharSequence, paramByteBuffer);
  }
  
  private static int zzbz(int paramInt) {
    int i = paramInt;
    if (paramInt > -12)
      i = -1; 
    return i;
  }
  
  private static int zzc(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt1 > -12 || paramInt2 > -65 || paramInt3 > -65) ? -1 : (paramInt1 ^ paramInt2 << 8 ^ paramInt3 << 16);
  }
  
  public static boolean zzf(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return zzcdi.zzf(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  private static int zzg(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    byte b = paramArrayOfbyte[paramInt1 - 1];
    paramInt2 -= paramInt1;
    if (paramInt2 != 0) {
      if (paramInt2 != 1) {
        if (paramInt2 == 2)
          return zzc(b, paramArrayOfbyte[paramInt1], paramArrayOfbyte[paramInt1 + 1]); 
        throw new AssertionError();
      } 
      return zzq(b, paramArrayOfbyte[paramInt1]);
    } 
    return zzbz(b);
  }
  
  static String zzh(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws zzuv {
    return zzcdi.zzh(paramArrayOfbyte, paramInt1, paramInt2);
  }
  
  public static boolean zzl(byte[] paramArrayOfbyte) {
    return zzcdi.zzf(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  private static int zzq(int paramInt1, int paramInt2) {
    return (paramInt1 > -12 || paramInt2 > -65) ? -1 : (paramInt1 ^ paramInt2 << 8);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */