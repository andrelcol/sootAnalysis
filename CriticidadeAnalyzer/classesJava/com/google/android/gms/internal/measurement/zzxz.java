package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzxz {
  private final byte[] buffer;
  
  private int zzbua;
  
  private int zzbub = 64;
  
  private int zzbug;
  
  private int zzbui;
  
  private int zzbuj = Integer.MAX_VALUE;
  
  private final int zzceo;
  
  private final int zzcep;
  
  private int zzceq;
  
  private int zzcer;
  
  private zztq zzces;
  
  private zzxz(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    this.buffer = paramArrayOfbyte;
    this.zzceo = paramInt1;
    paramInt2 += paramInt1;
    this.zzceq = paramInt2;
    this.zzcep = paramInt2;
    this.zzcer = paramInt1;
  }
  
  private final void zzau(int paramInt) throws IOException {
    if (paramInt >= 0) {
      int i = this.zzcer;
      int j = this.zzbuj;
      if (i + paramInt <= j) {
        if (paramInt <= this.zzceq - i) {
          this.zzcer = i + paramInt;
          return;
        } 
        throw zzyh.zzzd();
      } 
      zzau(j - i);
      throw zzyh.zzzd();
    } 
    throw zzyh.zzze();
  }
  
  public static zzxz zzj(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
    return new zzxz(paramArrayOfbyte, 0, paramInt2);
  }
  
  private final void zzvf() {
    this.zzceq += this.zzbug;
    int j = this.zzceq;
    int i = this.zzbuj;
    if (j > i) {
      this.zzbug = j - i;
      this.zzceq = j - this.zzbug;
      return;
    } 
    this.zzbug = 0;
  }
  
  private final byte zzvg() throws IOException {
    int i = this.zzcer;
    if (i != this.zzceq) {
      byte[] arrayOfByte = this.buffer;
      this.zzcer = i + 1;
      return arrayOfByte[i];
    } 
    throw zzyh.zzzd();
  }
  
  private final zztq zzyx() throws IOException {
    if (this.zzces == null)
      this.zzces = zztq.zzd(this.buffer, this.zzceo, this.zzcep); 
    int j = this.zzces.zzva();
    int i = this.zzcer - this.zzceo;
    if (j <= i) {
      this.zzces.zzau(i - j);
      this.zzces.zzar(this.zzbub - this.zzbua);
      return this.zzces;
    } 
    throw new IOException(String.format("CodedInputStream read ahead of CodedInputByteBufferNano: %s > %s", new Object[] { Integer.valueOf(j), Integer.valueOf(i) }));
  }
  
  public final int getPosition() {
    return this.zzcer - this.zzceo;
  }
  
  public final String readString() throws IOException {
    int i = zzvb();
    if (i >= 0) {
      int k = this.zzceq;
      int j = this.zzcer;
      if (i <= k - j) {
        String str = new String(this.buffer, j, i, zzyg.UTF_8);
        this.zzcer += i;
        return str;
      } 
      throw zzyh.zzzd();
    } 
    throw zzyh.zzze();
  }
  
  public final <T extends zzuo<T, ?>> T zza(zzwf<T> paramzzwf) throws IOException {
    try {
      zzuo zzuo = zzyx().<zzuo>zza(paramzzwf, zzub.zzvs());
      zzaq(this.zzbui);
      return (T)zzuo;
    } catch (zzuv zzuv) {
      throw new zzyh("", zzuv);
    } 
  }
  
  public final void zza(zzyi paramzzyi) throws IOException {
    int i = zzvb();
    if (this.zzbua < this.zzbub) {
      i = zzas(i);
      this.zzbua++;
      paramzzyi.zza(this);
      zzap(0);
      this.zzbua--;
      zzat(i);
      return;
    } 
    throw zzyh.zzzg();
  }
  
  public final void zzap(int paramInt) throws zzyh {
    if (this.zzbui == paramInt)
      return; 
    throw new zzyh("Protocol message end-group tag did not match expected tag.");
  }
  
  public final boolean zzaq(int paramInt) throws IOException {
    int i = paramInt & 0x7;
    if (i != 0) {
      if (i != 1) {
        if (i != 2) {
          if (i != 3) {
            if (i != 4) {
              if (i == 5) {
                zzvd();
                return true;
              } 
              throw new zzyh("Protocol message tag had invalid wire type.");
            } 
            return false;
          } 
          do {
            i = zzuj();
          } while (i != 0 && zzaq(i));
          zzap(paramInt >>> 3 << 3 | 0x4);
          return true;
        } 
        zzau(zzvb());
        return true;
      } 
      zzve();
      return true;
    } 
    zzvb();
    return true;
  }
  
  public final int zzas(int paramInt) throws zzyh {
    if (paramInt >= 0) {
      paramInt += this.zzcer;
      int i = this.zzbuj;
      if (paramInt <= i) {
        this.zzbuj = paramInt;
        zzvf();
        return i;
      } 
      throw zzyh.zzzd();
    } 
    throw zzyh.zzze();
  }
  
  public final void zzat(int paramInt) {
    this.zzbuj = paramInt;
    zzvf();
  }
  
  public final void zzcb(int paramInt) {
    zzt(paramInt, this.zzbui);
  }
  
  public final byte[] zzs(int paramInt1, int paramInt2) {
    if (paramInt2 == 0)
      return zzyl.zzcfq; 
    byte[] arrayOfByte = new byte[paramInt2];
    int i = this.zzceo;
    System.arraycopy(this.buffer, i + paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
  
  final void zzt(int paramInt1, int paramInt2) {
    int i = this.zzcer;
    int j = this.zzceo;
    if (paramInt1 <= i - j) {
      if (paramInt1 >= 0) {
        this.zzcer = j + paramInt1;
        this.zzbui = paramInt2;
        return;
      } 
      StringBuilder stringBuilder1 = new StringBuilder(24);
      stringBuilder1.append("Bad position ");
      stringBuilder1.append(paramInt1);
      throw new IllegalArgumentException(stringBuilder1.toString());
    } 
    StringBuilder stringBuilder = new StringBuilder(50);
    stringBuilder.append("Position ");
    stringBuilder.append(paramInt1);
    stringBuilder.append(" is beyond current ");
    stringBuilder.append(i - j);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final int zzuj() throws IOException {
    if (this.zzcer == this.zzceq) {
      this.zzbui = 0;
      return 0;
    } 
    this.zzbui = zzvb();
    int i = this.zzbui;
    if (i != 0)
      return i; 
    throw new zzyh("Protocol message contained an invalid tag (zero).");
  }
  
  public final boolean zzup() throws IOException {
    return (zzvb() != 0);
  }
  
  public final int zzvb() throws IOException {
    byte b = zzvg();
    if (b >= 0)
      return b; 
    int i = b & Byte.MAX_VALUE;
    int j = zzvg();
    if (j >= 0) {
      j = j << 7;
    } else {
      i |= (j & 0x7F) << 7;
      j = zzvg();
      if (j >= 0) {
        j <<= 14;
      } else {
        i |= (j & 0x7F) << 14;
        j = zzvg();
        if (j >= 0) {
          j <<= 21;
        } else {
          byte b1 = zzvg();
          j = i | (j & 0x7F) << 21 | b1 << 28;
          i = j;
          if (b1 < 0) {
            for (i = 0; i < 5; i++) {
              if (zzvg() >= 0)
                return j; 
            } 
            throw zzyh.zzzf();
          } 
          return i;
        } 
      } 
    } 
    i |= j;
    return i;
  }
  
  public final long zzvc() throws IOException {
    byte b = 0;
    long l = 0L;
    while (b < 64) {
      byte b1 = zzvg();
      l |= (b1 & Byte.MAX_VALUE) << b;
      if ((b1 & 0x80) == 0)
        return l; 
      b += 7;
    } 
    throw zzyh.zzzf();
  }
  
  public final int zzvd() throws IOException {
    return zzvg() & 0xFF | (zzvg() & 0xFF) << 8 | (zzvg() & 0xFF) << 16 | (zzvg() & 0xFF) << 24;
  }
  
  public final long zzve() throws IOException {
    byte b8 = zzvg();
    byte b4 = zzvg();
    byte b5 = zzvg();
    byte b2 = zzvg();
    byte b3 = zzvg();
    byte b1 = zzvg();
    byte b7 = zzvg();
    byte b6 = zzvg();
    long l = b8;
    return (b4 & 0xFFL) << 8L | l & 0xFFL | (b5 & 0xFFL) << 16L | (b2 & 0xFFL) << 24L | (b3 & 0xFFL) << 32L | (b1 & 0xFFL) << 40L | (b7 & 0xFFL) << 48L | (b6 & 0xFFL) << 56L;
  }
  
  public final int zzyy() {
    int i = this.zzbuj;
    return (i == Integer.MAX_VALUE) ? -1 : (i - this.zzcer);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */