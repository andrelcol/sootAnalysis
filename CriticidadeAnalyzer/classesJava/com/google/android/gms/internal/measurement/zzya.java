package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzya {
  private final ByteBuffer zzbut;
  
  private zztv zzcet;
  
  private int zzceu;
  
  private zzya(ByteBuffer paramByteBuffer) {
    this.zzbut = paramByteBuffer;
    this.zzbut.order(ByteOrder.LITTLE_ENDIAN);
  }
  
  private zzya(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this(ByteBuffer.wrap(paramArrayOfbyte, paramInt1, paramInt2));
  }
  
  private static int zza(CharSequence paramCharSequence) {
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
                  paramCharSequence = new StringBuilder(39);
                  paramCharSequence.append("Unpaired surrogate at index ");
                  paramCharSequence.append(j);
                  throw new IllegalArgumentException(paramCharSequence.toString());
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
  
  public static int zzb(int paramInt, zzyi paramzzyi) {
    paramInt = zzbd(paramInt);
    int i = paramzzyi.zzvx();
    return paramInt + zzbl(i) + i;
  }
  
  public static int zzbd(int paramInt) {
    return zzbl(paramInt << 3);
  }
  
  public static int zzbe(int paramInt) {
    return (paramInt >= 0) ? zzbl(paramInt) : 10;
  }
  
  private final void zzbf(long paramLong) throws IOException {
    while (true) {
      if ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L) {
        zzcc((int)paramLong);
        return;
      } 
      zzcc((int)paramLong & 0x7F | 0x80);
      paramLong >>>= 7L;
    } 
  }
  
  public static int zzbg(long paramLong) {
    return ((0xFFFFFFFFFFFFFF80L & paramLong) == 0L) ? 1 : (((0xFFFFFFFFFFFFC000L & paramLong) == 0L) ? 2 : (((0xFFFFFFFFFFE00000L & paramLong) == 0L) ? 3 : (((0xFFFFFFFFF0000000L & paramLong) == 0L) ? 4 : (((0xFFFFFFF800000000L & paramLong) == 0L) ? 5 : (((0xFFFFFC0000000000L & paramLong) == 0L) ? 6 : (((0xFFFE000000000000L & paramLong) == 0L) ? 7 : (((0xFF00000000000000L & paramLong) == 0L) ? 8 : (((paramLong & Long.MIN_VALUE) == 0L) ? 9 : 10))))))));
  }
  
  public static int zzbl(int paramInt) {
    return ((paramInt & 0xFFFFFF80) == 0) ? 1 : (((paramInt & 0xFFFFC000) == 0) ? 2 : (((0xFFE00000 & paramInt) == 0) ? 3 : (((paramInt & 0xF0000000) == 0) ? 4 : 5)));
  }
  
  public static int zzc(int paramInt, String paramString) {
    return zzbd(paramInt) + zzgc(paramString);
  }
  
  private final void zzcc(int paramInt) throws IOException {
    byte b = (byte)paramInt;
    if (this.zzbut.hasRemaining()) {
      this.zzbut.put(b);
      return;
    } 
    throw new zzyb(this.zzbut.position(), this.zzbut.limit());
  }
  
  public static int zzd(int paramInt, long paramLong) {
    return zzbd(paramInt) + zzbg(paramLong);
  }
  
  private static void zzd(CharSequence paramCharSequence, ByteBuffer paramByteBuffer) {
    if (!paramByteBuffer.isReadOnly()) {
      BufferOverflowException bufferOverflowException;
      boolean bool = paramByteBuffer.hasArray();
      int j = 0;
      int i = 0;
      if (bool)
        try {
          ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException1;
          byte[] arrayOfByte = paramByteBuffer.array();
          j = paramByteBuffer.arrayOffset() + paramByteBuffer.position();
          int m = paramByteBuffer.remaining();
          int n = paramCharSequence.length();
          int i1 = m + j;
          while (i < n) {
            m = i + j;
            if (m < i1) {
              char c = paramCharSequence.charAt(i);
              if (c < '') {
                arrayOfByte[m] = (byte)c;
                i++;
              } 
            } 
          } 
          if (i == n) {
            m = j + n;
          } else {
            j += i;
            while (true) {
              m = j;
              if (i < n) {
                char c = paramCharSequence.charAt(i);
                if (c < '' && j < i1) {
                  m = j + 1;
                  arrayOfByte[j] = (byte)c;
                  j = m;
                } else if (c < 'ࠀ' && j <= i1 - 2) {
                  m = j + 1;
                  arrayOfByte[j] = (byte)(c >>> 6 | 0x3C0);
                  j = m + 1;
                  arrayOfByte[m] = (byte)(c & 0x3F | 0x80);
                } else if ((c < '?' || '?' < c) && j <= i1 - 3) {
                  int i2 = j + 1;
                  arrayOfByte[j] = (byte)(c >>> 12 | 0x1E0);
                  m = i2 + 1;
                  arrayOfByte[i2] = (byte)(c >>> 6 & 0x3F | 0x80);
                  j = m + 1;
                  arrayOfByte[m] = (byte)(c & 0x3F | 0x80);
                } else if (j <= i1 - 4) {
                  m = i + 1;
                  if (m != paramCharSequence.length()) {
                    char c1 = paramCharSequence.charAt(m);
                    if (Character.isSurrogatePair(c, c1)) {
                      i = Character.toCodePoint(c, c1);
                      int i2 = j + 1;
                      arrayOfByte[j] = (byte)(i >>> 18 | 0xF0);
                      j = i2 + 1;
                      arrayOfByte[i2] = (byte)(i >>> 12 & 0x3F | 0x80);
                      i2 = j + 1;
                      arrayOfByte[j] = (byte)(i >>> 6 & 0x3F | 0x80);
                      j = i2 + 1;
                      arrayOfByte[i2] = (byte)(i & 0x3F | 0x80);
                      i = m;
                    } else {
                      i = m;
                      IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
                      paramCharSequence = new StringBuilder();
                      super(39);
                      paramCharSequence.append("Unpaired surrogate at index ");
                      paramCharSequence.append(i - 1);
                      this(paramCharSequence.toString());
                      throw illegalArgumentException;
                    } 
                  } else {
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException();
                    paramCharSequence = new StringBuilder();
                    super(39);
                    paramCharSequence.append("Unpaired surrogate at index ");
                    paramCharSequence.append(i - 1);
                    this(paramCharSequence.toString());
                    throw illegalArgumentException;
                  } 
                } else {
                  arrayIndexOutOfBoundsException1 = new ArrayIndexOutOfBoundsException();
                  paramCharSequence = new StringBuilder();
                  super(37);
                  paramCharSequence.append("Failed writing ");
                  paramCharSequence.append(c);
                  paramCharSequence.append(" at index ");
                  paramCharSequence.append(j);
                  this(paramCharSequence.toString());
                  throw arrayIndexOutOfBoundsException1;
                } 
                i++;
                continue;
              } 
              break;
            } 
          } 
          arrayIndexOutOfBoundsException1.position(m - arrayIndexOutOfBoundsException1.arrayOffset());
          return;
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
          bufferOverflowException = new BufferOverflowException();
          bufferOverflowException.initCause(arrayIndexOutOfBoundsException);
          throw bufferOverflowException;
        }  
      int k = arrayIndexOutOfBoundsException.length();
      for (i = j; i < k; i++) {
        char c = arrayIndexOutOfBoundsException.charAt(i);
        if (c < '') {
          bufferOverflowException.put((byte)c);
        } else if (c < 'ࠀ') {
          bufferOverflowException.put((byte)(c >>> 6 | 0x3C0));
          bufferOverflowException.put((byte)(c & 0x3F | 0x80));
        } else if (c < '?' || '?' < c) {
          bufferOverflowException.put((byte)(c >>> 12 | 0x1E0));
          bufferOverflowException.put((byte)(c >>> 6 & 0x3F | 0x80));
          bufferOverflowException.put((byte)(c & 0x3F | 0x80));
        } else {
          j = i + 1;
          if (j != arrayIndexOutOfBoundsException.length()) {
            char c1 = arrayIndexOutOfBoundsException.charAt(j);
            if (Character.isSurrogatePair(c, c1)) {
              i = Character.toCodePoint(c, c1);
              bufferOverflowException.put((byte)(i >>> 18 | 0xF0));
              bufferOverflowException.put((byte)(i >>> 12 & 0x3F | 0x80));
              bufferOverflowException.put((byte)(i >>> 6 & 0x3F | 0x80));
              bufferOverflowException.put((byte)(i & 0x3F | 0x80));
              i = j;
            } else {
              i = j;
              StringBuilder stringBuilder = new StringBuilder(39);
              stringBuilder.append("Unpaired surrogate at index ");
              stringBuilder.append(i - 1);
              throw new IllegalArgumentException(stringBuilder.toString());
            } 
          } else {
            StringBuilder stringBuilder = new StringBuilder(39);
            stringBuilder.append("Unpaired surrogate at index ");
            stringBuilder.append(i - 1);
            throw new IllegalArgumentException(stringBuilder.toString());
          } 
        } 
      } 
      return;
    } 
    throw new ReadOnlyBufferException();
  }
  
  public static int zzgc(String paramString) {
    int i = zza(paramString);
    return zzbl(i) + i;
  }
  
  public static int zzh(int paramInt1, int paramInt2) {
    return zzbd(paramInt1) + zzbe(paramInt2);
  }
  
  public static zzya zzk(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzya(paramArrayOfbyte, 0, paramInt2);
  }
  
  public static zzya zzo(byte[] paramArrayOfbyte) {
    return zzk(paramArrayOfbyte, 0, paramArrayOfbyte.length);
  }
  
  private final zztv zzyz() throws IOException {
    if (this.zzcet == null) {
      this.zzcet = zztv.zza(this.zzbut);
      this.zzceu = this.zzbut.position();
    } else if (this.zzceu != this.zzbut.position()) {
      this.zzcet.write(this.zzbut.array(), this.zzceu, this.zzbut.position() - this.zzceu);
      this.zzceu = this.zzbut.position();
    } 
    return this.zzcet;
  }
  
  public final void zza(int paramInt, double paramDouble) throws IOException {
    zzc(paramInt, 1);
    long l = Double.doubleToLongBits(paramDouble);
    if (this.zzbut.remaining() >= 8) {
      this.zzbut.putLong(l);
      return;
    } 
    throw new zzyb(this.zzbut.position(), this.zzbut.limit());
  }
  
  public final void zza(int paramInt, float paramFloat) throws IOException {
    zzc(paramInt, 5);
    paramInt = Float.floatToIntBits(paramFloat);
    if (this.zzbut.remaining() >= 4) {
      this.zzbut.putInt(paramInt);
      return;
    } 
    throw new zzyb(this.zzbut.position(), this.zzbut.limit());
  }
  
  public final void zza(int paramInt, long paramLong) throws IOException {
    zzc(paramInt, 0);
    zzbf(paramLong);
  }
  
  public final void zza(int paramInt, zzyi paramzzyi) throws IOException {
    zzc(paramInt, 2);
    zzb(paramzzyi);
  }
  
  public final void zzb(int paramInt, String paramString) throws IOException {
    zzc(paramInt, 2);
    try {
      zzyb zzyb;
      int i = zzbl(paramString.length());
      if (i == zzbl(paramString.length() * 3)) {
        paramInt = this.zzbut.position();
        if (this.zzbut.remaining() >= i) {
          this.zzbut.position(paramInt + i);
          zzd(paramString, this.zzbut);
          int j = this.zzbut.position();
          this.zzbut.position(paramInt);
          zzcd(j - paramInt - i);
          this.zzbut.position(j);
          return;
        } 
        zzyb = new zzyb();
        this(paramInt + i, this.zzbut.limit());
        throw zzyb;
      } 
      zzcd(zza((CharSequence)zzyb));
      zzd((CharSequence)zzyb, this.zzbut);
      return;
    } catch (BufferOverflowException bufferOverflowException) {
      zzyb zzyb = new zzyb(this.zzbut.position(), this.zzbut.limit());
      zzyb.initCause(bufferOverflowException);
      throw zzyb;
    } 
  }
  
  public final void zzb(int paramInt, boolean paramBoolean) throws IOException {
    zzc(paramInt, 0);
    byte b = (byte)paramBoolean;
    if (this.zzbut.hasRemaining()) {
      this.zzbut.put(b);
      return;
    } 
    throw new zzyb(this.zzbut.position(), this.zzbut.limit());
  }
  
  public final void zzb(zzyi paramzzyi) throws IOException {
    zzcd(paramzzyi.zzzh());
    paramzzyi.zza(this);
  }
  
  public final void zzc(int paramInt1, int paramInt2) throws IOException {
    zzcd(paramInt1 << 3 | paramInt2);
  }
  
  public final void zzcd(int paramInt) throws IOException {
    while (true) {
      if ((paramInt & 0xFFFFFF80) == 0) {
        zzcc(paramInt);
        return;
      } 
      zzcc(paramInt & 0x7F | 0x80);
      paramInt >>>= 7;
    } 
  }
  
  public final void zzd(int paramInt1, int paramInt2) throws IOException {
    zzc(paramInt1, 0);
    if (paramInt2 >= 0) {
      zzcd(paramInt2);
      return;
    } 
    zzbf(paramInt2);
  }
  
  public final void zze(int paramInt, zzvv paramzzvv) throws IOException {
    zztv zztv1 = zzyz();
    zztv1.zza(paramInt, paramzzvv);
    zztv1.flush();
    this.zzceu = this.zzbut.position();
  }
  
  public final void zzi(int paramInt, long paramLong) throws IOException {
    zzc(paramInt, 0);
    zzbf(paramLong);
  }
  
  public final void zzp(byte[] paramArrayOfbyte) throws IOException {
    int i = paramArrayOfbyte.length;
    if (this.zzbut.remaining() >= i) {
      this.zzbut.put(paramArrayOfbyte, 0, i);
      return;
    } 
    throw new zzyb(this.zzbut.position(), this.zzbut.limit());
  }
  
  public final void zzza() {
    if (this.zzbut.remaining() == 0)
      return; 
    throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[] { Integer.valueOf(this.zzbut.remaining()) }));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzya.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */