package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

final class zzts extends zztq {
  private final byte[] buffer;
  
  private int limit;
  
  private int pos;
  
  private int zzbug;
  
  private int zzbuh;
  
  private int zzbui;
  
  private int zzbuj = Integer.MAX_VALUE;
  
  private zzts(byte[] paramArrayOfbyte, int paramInt1, int paramInt2, boolean paramBoolean) {
    super(null);
    this.buffer = paramArrayOfbyte;
    this.limit = paramInt2 + paramInt1;
    this.pos = paramInt1;
    this.zzbuh = this.pos;
  }
  
  private final int zzvb() throws IOException {
    int j = this.pos;
    int i = this.limit;
    if (i != j) {
      byte[] arrayOfByte = this.buffer;
      int k = j + 1;
      j = arrayOfByte[j];
      if (j >= 0) {
        this.pos = k;
        return j;
      } 
      if (i - k >= 9) {
        i = k + 1;
        j ^= arrayOfByte[k] << 7;
        if (j < 0) {
          k = j ^ 0xFFFFFF80;
        } else {
          k = i + 1;
          j ^= arrayOfByte[i] << 14;
          if (j >= 0) {
            j ^= 0x3F80;
            i = k;
            k = j;
          } else {
            i = k + 1;
            k = j ^ arrayOfByte[k] << 21;
            if (k < 0) {
              k ^= 0xFFE03F80;
            } else {
              int m = i + 1;
              byte b = arrayOfByte[i];
              j = k ^ b << 28 ^ 0xFE03F80;
              k = j;
              i = m;
              if (b < 0) {
                int n = m + 1;
                k = j;
                i = n;
                if (arrayOfByte[m] < 0) {
                  int i1 = n + 1;
                  k = j;
                  i = i1;
                  if (arrayOfByte[n] < 0) {
                    m = i1 + 1;
                    k = j;
                    i = m;
                    if (arrayOfByte[i1] < 0) {
                      n = m + 1;
                      k = j;
                      i = n;
                      if (arrayOfByte[m] < 0) {
                        i = n + 1;
                        if (arrayOfByte[n] >= 0) {
                          k = j;
                          this.pos = i;
                          return k;
                        } 
                      } else {
                        this.pos = i;
                        return k;
                      } 
                    } else {
                      this.pos = i;
                      return k;
                    } 
                  } else {
                    this.pos = i;
                    return k;
                  } 
                } else {
                  this.pos = i;
                  return k;
                } 
              } else {
                this.pos = i;
                return k;
              } 
              return (int)zzuy();
            } 
          } 
        } 
        this.pos = i;
        return k;
      } 
    } 
    return (int)zzuy();
  }
  
  private final long zzvc() throws IOException {
    int j;
    int k = this.pos;
    int i = this.limit;
    if (i != k) {
      byte[] arrayOfByte = this.buffer;
      j = k + 1;
      k = arrayOfByte[k];
      if (k >= 0) {
        this.pos = j;
        return k;
      } 
      if (i - j >= 9) {
        i = j + 1;
        k ^= arrayOfByte[j] << 7;
        if (k < 0) {
          j = k ^ 0xFFFFFF80;
        } else {
          long l1;
          j = i + 1;
          k ^= arrayOfByte[i] << 14;
          if (k >= 0) {
            l1 = (k ^ 0x3F80);
            i = j;
          } else {
            i = j + 1;
            j = k ^ arrayOfByte[j] << 21;
            if (j < 0) {
              j ^= 0xFFE03F80;
            } else {
              long l2 = j;
              k = i + 1;
              long l3 = l2 ^ arrayOfByte[i] << 28L;
              if (l3 >= 0L) {
                l2 = 266354560L;
                i = k;
              } else {
                j = k + 1;
                l2 = l3 ^ arrayOfByte[k] << 35L;
                if (l2 < 0L) {
                  l3 = -34093383808L;
                  i = j;
                } else {
                  i = j + 1;
                  l3 = l2 ^ arrayOfByte[j] << 42L;
                  if (l3 >= 0L) {
                    l2 = 4363953127296L;
                  } else {
                    j = i + 1;
                    l2 = l3 ^ arrayOfByte[i] << 49L;
                    if (l2 < 0L) {
                      l3 = -558586000294016L;
                      i = j;
                    } else {
                      i = j + 1;
                      l2 = l2 ^ arrayOfByte[j] << 56L ^ 0xFE03F80FE03F80L;
                      if (l2 < 0L) {
                        j = i + 1;
                        if (arrayOfByte[i] >= 0L) {
                          i = j;
                          this.pos = i;
                          return l2;
                        } 
                      } else {
                        this.pos = i;
                        return l2;
                      } 
                      return zzuy();
                    } 
                    l2 ^= l3;
                  } 
                  l2 = l3 ^ l2;
                } 
                l2 ^= l3;
              } 
              l2 = l3 ^ l2;
            } 
            l1 = j;
          } 
          this.pos = i;
          return l1;
        } 
      } else {
        return zzuy();
      } 
    } else {
      return zzuy();
    } 
    long l = j;
  }
  
  private final int zzvd() throws IOException {
    int i = this.pos;
    if (this.limit - i >= 4) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 4;
      byte b2 = arrayOfByte[i];
      byte b3 = arrayOfByte[i + 1];
      byte b1 = arrayOfByte[i + 2];
      return (arrayOfByte[i + 3] & 0xFF) << 24 | b2 & 0xFF | (b3 & 0xFF) << 8 | (b1 & 0xFF) << 16;
    } 
    throw zzuv.zzwq();
  }
  
  private final long zzve() throws IOException {
    int i = this.pos;
    if (this.limit - i >= 8) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 8;
      long l5 = arrayOfByte[i];
      long l2 = arrayOfByte[i + 1];
      long l1 = arrayOfByte[i + 2];
      long l4 = arrayOfByte[i + 3];
      long l6 = arrayOfByte[i + 4];
      long l7 = arrayOfByte[i + 5];
      long l3 = arrayOfByte[i + 6];
      return (arrayOfByte[i + 7] & 0xFFL) << 56L | l5 & 0xFFL | (l2 & 0xFFL) << 8L | (l1 & 0xFFL) << 16L | (l4 & 0xFFL) << 24L | (l6 & 0xFFL) << 32L | (l7 & 0xFFL) << 40L | (l3 & 0xFFL) << 48L;
    } 
    throw zzuv.zzwq();
  }
  
  private final void zzvf() {
    this.limit += this.zzbug;
    int j = this.limit;
    int i = j - this.zzbuh;
    int k = this.zzbuj;
    if (i > k) {
      this.zzbug = i - k;
      this.limit = j - this.zzbug;
      return;
    } 
    this.zzbug = 0;
  }
  
  private final byte zzvg() throws IOException {
    int i = this.pos;
    if (i != this.limit) {
      byte[] arrayOfByte = this.buffer;
      this.pos = i + 1;
      return arrayOfByte[i];
    } 
    throw zzuv.zzwq();
  }
  
  public final double readDouble() throws IOException {
    return Double.longBitsToDouble(zzve());
  }
  
  public final float readFloat() throws IOException {
    return Float.intBitsToFloat(zzvd());
  }
  
  public final String readString() throws IOException {
    int i = zzvb();
    if (i > 0) {
      int k = this.limit;
      int j = this.pos;
      if (i <= k - j) {
        String str = new String(this.buffer, j, i, zzuq.UTF_8);
        this.pos += i;
        return str;
      } 
    } 
    if (i == 0)
      return ""; 
    if (i < 0)
      throw zzuv.zzwr(); 
    throw zzuv.zzwq();
  }
  
  public final <T extends zzvv> T zza(zzwf<T> paramzzwf, zzub paramzzub) throws IOException {
    int i = zzvb();
    if (this.zzbua < this.zzbub) {
      i = zzas(i);
      this.zzbua++;
      zzvv zzvv = (zzvv)paramzzwf.zza(this, paramzzub);
      zzap(0);
      this.zzbua--;
      zzat(i);
      return (T)zzvv;
    } 
    throw zzuv.zzwv();
  }
  
  public final void zzap(int paramInt) throws zzuv {
    if (this.zzbui == paramInt)
      return; 
    throw zzuv.zzwt();
  }
  
  public final boolean zzaq(int paramInt) throws IOException {
    int j = paramInt & 0x7;
    boolean bool = false;
    int i = 0;
    if (j != 0) {
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            if (j != 4) {
              if (j == 5) {
                zzau(4);
                return true;
              } 
              throw zzuv.zzwu();
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
      zzau(8);
      return true;
    } 
    paramInt = bool;
    if (this.limit - this.pos >= 10) {
      paramInt = i;
      while (paramInt < 10) {
        byte[] arrayOfByte = this.buffer;
        i = this.pos;
        this.pos = i + 1;
        if (arrayOfByte[i] < 0) {
          paramInt++;
          continue;
        } 
        return true;
      } 
      throw zzuv.zzws();
    } 
    while (paramInt < 10) {
      if (zzvg() < 0) {
        paramInt++;
        continue;
      } 
      return true;
    } 
    throw zzuv.zzws();
  }
  
  public final int zzas(int paramInt) throws zzuv {
    if (paramInt >= 0) {
      paramInt += zzva();
      int i = this.zzbuj;
      if (paramInt <= i) {
        this.zzbuj = paramInt;
        zzvf();
        return i;
      } 
      throw zzuv.zzwq();
    } 
    throw zzuv.zzwr();
  }
  
  public final void zzat(int paramInt) {
    this.zzbuj = paramInt;
    zzvf();
  }
  
  public final void zzau(int paramInt) throws IOException {
    if (paramInt >= 0) {
      int j = this.limit;
      int i = this.pos;
      if (paramInt <= j - i) {
        this.pos = i + paramInt;
        return;
      } 
    } 
    if (paramInt < 0)
      throw zzuv.zzwr(); 
    throw zzuv.zzwq();
  }
  
  public final int zzuj() throws IOException {
    if (zzuz()) {
      this.zzbui = 0;
      return 0;
    } 
    this.zzbui = zzvb();
    int i = this.zzbui;
    if (i >>> 3 != 0)
      return i; 
    throw new zzuv("Protocol message contained an invalid tag (zero).");
  }
  
  public final long zzuk() throws IOException {
    return zzvc();
  }
  
  public final long zzul() throws IOException {
    return zzvc();
  }
  
  public final int zzum() throws IOException {
    return zzvb();
  }
  
  public final long zzun() throws IOException {
    return zzve();
  }
  
  public final int zzuo() throws IOException {
    return zzvd();
  }
  
  public final boolean zzup() throws IOException {
    return (zzvc() != 0L);
  }
  
  public final String zzuq() throws IOException {
    int i = zzvb();
    if (i > 0) {
      int k = this.limit;
      int j = this.pos;
      if (i <= k - j) {
        String str = zzxl.zzh(this.buffer, j, i);
        this.pos += i;
        return str;
      } 
    } 
    if (i == 0)
      return ""; 
    if (i <= 0)
      throw zzuv.zzwr(); 
    throw zzuv.zzwq();
  }
  
  public final zzte zzur() throws IOException {
    int i = zzvb();
    if (i > 0) {
      int k = this.limit;
      int j = this.pos;
      if (i <= k - j) {
        zzte zzte = zzte.zzb(this.buffer, j, i);
        this.pos += i;
        return zzte;
      } 
    } 
    if (i == 0)
      return zzte.zzbts; 
    if (i > 0) {
      int k = this.limit;
      int j = this.pos;
      if (i <= k - j) {
        this.pos = i + j;
        byte[] arrayOfByte = Arrays.copyOfRange(this.buffer, j, this.pos);
        return zzte.zzi(arrayOfByte);
      } 
    } 
    if (i <= 0) {
      if (i == 0) {
        byte[] arrayOfByte = zzuq.zzbzc;
        return zzte.zzi(arrayOfByte);
      } 
      throw zzuv.zzwr();
    } 
    throw zzuv.zzwq();
  }
  
  public final int zzus() throws IOException {
    return zzvb();
  }
  
  public final int zzut() throws IOException {
    return zzvb();
  }
  
  public final int zzuu() throws IOException {
    return zzvd();
  }
  
  public final long zzuv() throws IOException {
    return zzve();
  }
  
  public final int zzuw() throws IOException {
    int i = zzvb();
    return -(i & 0x1) ^ i >>> 1;
  }
  
  public final long zzux() throws IOException {
    long l = zzvc();
    return -(l & 0x1L) ^ l >>> 1L;
  }
  
  final long zzuy() throws IOException {
    long l = 0L;
    for (byte b = 0; b < 64; b += 7) {
      byte b1 = zzvg();
      l |= (b1 & Byte.MAX_VALUE) << b;
      if ((b1 & 0x80) == 0)
        return l; 
    } 
    throw zzuv.zzws();
  }
  
  public final boolean zzuz() throws IOException {
    return (this.pos == this.limit);
  }
  
  public final int zzva() {
    return this.pos - this.zzbuh;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzts.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */