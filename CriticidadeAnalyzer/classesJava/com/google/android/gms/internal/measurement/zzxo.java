package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;

final class zzxo extends zzxn {
  final int zzb(int paramInt1, byte[] paramArrayOfbyte, int paramInt2, int paramInt3) {
    while (paramInt2 < paramInt3 && paramArrayOfbyte[paramInt2] >= 0)
      paramInt2++; 
    paramInt1 = paramInt2;
    if (paramInt2 >= paramInt3)
      return 0; 
    while (true) {
      if (paramInt1 >= paramInt3)
        return 0; 
      paramInt2 = paramInt1 + 1;
      byte b = paramArrayOfbyte[paramInt1];
      paramInt1 = paramInt2;
      if (b < 0) {
        if (b < -32) {
          if (paramInt2 >= paramInt3)
            return b; 
          if (b >= -62) {
            paramInt1 = paramInt2 + 1;
            if (paramArrayOfbyte[paramInt2] > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (b < -16) {
          if (paramInt2 >= paramInt3 - 1)
            return zzxl.zzi(paramArrayOfbyte, paramInt2, paramInt3); 
          int i = paramInt2 + 1;
          paramInt1 = paramArrayOfbyte[paramInt2];
          if (paramInt1 <= -65 && (b != -32 || paramInt1 >= -96) && (b != -19 || paramInt1 < -96)) {
            paramInt1 = i + 1;
            if (paramArrayOfbyte[i] > -65)
              return -1; 
            continue;
          } 
          return -1;
        } 
        if (paramInt2 >= paramInt3 - 2)
          return zzxl.zzi(paramArrayOfbyte, paramInt2, paramInt3); 
        paramInt1 = paramInt2 + 1;
        paramInt2 = paramArrayOfbyte[paramInt2];
        if (paramInt2 <= -65 && (b << 28) + paramInt2 + 112 >> 30 == 0) {
          paramInt2 = paramInt1 + 1;
          if (paramArrayOfbyte[paramInt1] <= -65) {
            paramInt1 = paramInt2 + 1;
            if (paramArrayOfbyte[paramInt2] > -65)
              break; 
            continue;
          } 
        } 
        break;
      } 
    } 
    return -1;
  }
  
  final int zzb(CharSequence paramCharSequence, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    int j = paramCharSequence.length();
    int k = paramInt2 + paramInt1;
    paramInt2 = 0;
    while (paramInt2 < j) {
      int m = paramInt2 + paramInt1;
      if (m < k) {
        char c = paramCharSequence.charAt(paramInt2);
        if (c < '') {
          paramArrayOfbyte[m] = (byte)c;
          paramInt2++;
        } 
      } 
    } 
    if (paramInt2 == j)
      return paramInt1 + j; 
    int i = paramInt1 + paramInt2;
    paramInt1 = paramInt2;
    while (paramInt1 < j) {
      char c = paramCharSequence.charAt(paramInt1);
      if (c < '' && i < k) {
        paramInt2 = i + 1;
        paramArrayOfbyte[i] = (byte)c;
      } else if (c < 'ࠀ' && i <= k - 2) {
        int m = i + 1;
        paramArrayOfbyte[i] = (byte)(c >>> 6 | 0x3C0);
        paramInt2 = m + 1;
        paramArrayOfbyte[m] = (byte)(c & 0x3F | 0x80);
      } else if ((c < '?' || '?' < c) && i <= k - 3) {
        paramInt2 = i + 1;
        paramArrayOfbyte[i] = (byte)(c >>> 12 | 0x1E0);
        i = paramInt2 + 1;
        paramArrayOfbyte[paramInt2] = (byte)(c >>> 6 & 0x3F | 0x80);
        paramInt2 = i + 1;
        paramArrayOfbyte[i] = (byte)(c & 0x3F | 0x80);
      } else if (i <= k - 4) {
        paramInt2 = paramInt1 + 1;
        if (paramInt2 != paramCharSequence.length()) {
          char c1 = paramCharSequence.charAt(paramInt2);
          if (Character.isSurrogatePair(c, c1)) {
            paramInt1 = Character.toCodePoint(c, c1);
            int m = i + 1;
            paramArrayOfbyte[i] = (byte)(paramInt1 >>> 18 | 0xF0);
            i = m + 1;
            paramArrayOfbyte[m] = (byte)(paramInt1 >>> 12 & 0x3F | 0x80);
            m = i + 1;
            paramArrayOfbyte[i] = (byte)(paramInt1 >>> 6 & 0x3F | 0x80);
            i = m + 1;
            paramArrayOfbyte[m] = (byte)(paramInt1 & 0x3F | 0x80);
            paramInt1 = paramInt2;
            paramInt2 = i;
          } else {
            paramInt1 = paramInt2;
            throw new zzxp(paramInt1 - 1, j);
          } 
        } else {
          throw new zzxp(paramInt1 - 1, j);
        } 
      } else {
        if ('?' <= c && c <= '?') {
          paramInt2 = paramInt1 + 1;
          if (paramInt2 == paramCharSequence.length() || !Character.isSurrogatePair(c, paramCharSequence.charAt(paramInt2)))
            throw new zzxp(paramInt1, j); 
        } 
        paramCharSequence = new StringBuilder(37);
        paramCharSequence.append("Failed writing ");
        paramCharSequence.append(c);
        paramCharSequence.append(" at index ");
        paramCharSequence.append(i);
        throw new ArrayIndexOutOfBoundsException(paramCharSequence.toString());
      } 
      paramInt1++;
      i = paramInt2;
    } 
    return i;
  }
  
  final void zzb(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    zzxn.zzc(paramCharSequence, paramByteBuffer);
  }
  
  final String zzh(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws zzuv {
    if ((paramInt1 | paramInt2 | paramArrayOfbyte.length - paramInt1 - paramInt2) >= 0) {
      int j = paramInt1 + paramInt2;
      char[] arrayOfChar = new char[paramInt2];
      paramInt2 = 0;
      while (paramInt1 < j) {
        byte b = paramArrayOfbyte[paramInt1];
        if (zzxm.zzh(b)) {
          paramInt1++;
          zzxm.zzb(b, arrayOfChar, paramInt2);
          paramInt2++;
        } 
      } 
      int i = paramInt2;
      paramInt2 = paramInt1;
      paramInt1 = i;
      while (paramInt2 < j) {
        i = paramInt2 + 1;
        byte b = paramArrayOfbyte[paramInt2];
        if (zzxm.zzh(b)) {
          paramInt2 = paramInt1 + 1;
          zzxm.zzb(b, arrayOfChar, paramInt1);
          paramInt1 = paramInt2;
          paramInt2 = i;
          while (paramInt2 < j) {
            b = paramArrayOfbyte[paramInt2];
            if (zzxm.zzh(b)) {
              paramInt2++;
              zzxm.zzb(b, arrayOfChar, paramInt1);
              paramInt1++;
            } 
          } 
          continue;
        } 
        if (zzxm.zzi(b)) {
          if (i < j) {
            zzxm.zzb(b, paramArrayOfbyte[i], arrayOfChar, paramInt1);
            paramInt2 = i + 1;
            paramInt1++;
            continue;
          } 
          throw zzuv.zzwx();
        } 
        if (zzxm.zzj(b)) {
          if (i < j - 1) {
            paramInt2 = i + 1;
            zzxm.zzb(b, paramArrayOfbyte[i], paramArrayOfbyte[paramInt2], arrayOfChar, paramInt1);
            paramInt2++;
            paramInt1++;
            continue;
          } 
          throw zzuv.zzwx();
        } 
        if (i < j - 2) {
          paramInt2 = i + 1;
          byte b1 = paramArrayOfbyte[i];
          i = paramInt2 + 1;
          zzxm.zzb(b, b1, paramArrayOfbyte[paramInt2], paramArrayOfbyte[i], arrayOfChar, paramInt1);
          paramInt2 = i + 1;
          paramInt1 = paramInt1 + 1 + 1;
          continue;
        } 
        throw zzuv.zzwx();
      } 
      return new String(arrayOfChar, 0, paramInt1);
    } 
    throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", new Object[] { Integer.valueOf(paramArrayOfbyte.length), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */