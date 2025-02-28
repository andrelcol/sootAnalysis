package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Arrays;

public final class zzxe {
  private static final zzxe zzcch = new zzxe(0, new int[0], new Object[0], false);
  
  private int count;
  
  private boolean zzbtn;
  
  private int zzbyg = -1;
  
  private Object[] zzcat;
  
  private int[] zzcci;
  
  private zzxe() {
    this(0, new int[8], new Object[8], true);
  }
  
  private zzxe(int paramInt, int[] paramArrayOfint, Object[] paramArrayOfObject, boolean paramBoolean) {
    this.count = paramInt;
    this.zzcci = paramArrayOfint;
    this.zzcat = paramArrayOfObject;
    this.zzbtn = paramBoolean;
  }
  
  static zzxe zza(zzxe paramzzxe1, zzxe paramzzxe2) {
    int i = paramzzxe1.count + paramzzxe2.count;
    int[] arrayOfInt = Arrays.copyOf(paramzzxe1.zzcci, i);
    System.arraycopy(paramzzxe2.zzcci, 0, arrayOfInt, paramzzxe1.count, paramzzxe2.count);
    Object[] arrayOfObject = Arrays.copyOf(paramzzxe1.zzcat, i);
    System.arraycopy(paramzzxe2.zzcat, 0, arrayOfObject, paramzzxe1.count, paramzzxe2.count);
    return new zzxe(i, arrayOfInt, arrayOfObject, true);
  }
  
  private static void zzb(int paramInt, Object paramObject, zzxy paramzzxy) throws IOException {
    int i = paramInt >>> 3;
    paramInt &= 0x7;
    if (paramInt != 0) {
      if (paramInt != 1) {
        if (paramInt != 2) {
          if (paramInt != 3) {
            if (paramInt == 5) {
              paramzzxy.zzg(i, ((Integer)paramObject).intValue());
              return;
            } 
            throw new RuntimeException(zzuv.zzwu());
          } 
          if (paramzzxy.zzvm() == zzuo.zze.zzbyx) {
            paramzzxy.zzbm(i);
            ((zzxe)paramObject).zzb(paramzzxy);
            paramzzxy.zzbn(i);
            return;
          } 
          paramzzxy.zzbn(i);
          ((zzxe)paramObject).zzb(paramzzxy);
          paramzzxy.zzbm(i);
          return;
        } 
        paramzzxy.zza(i, (zzte)paramObject);
        return;
      } 
      paramzzxy.zzc(i, ((Long)paramObject).longValue());
      return;
    } 
    paramzzxy.zzi(i, ((Long)paramObject).longValue());
  }
  
  public static zzxe zzyl() {
    return zzcch;
  }
  
  static zzxe zzym() {
    return new zzxe();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject == null)
      return false; 
    if (!(paramObject instanceof zzxe))
      return false; 
    paramObject = paramObject;
    int i = this.count;
    if (i == ((zzxe)paramObject).count) {
      int[] arrayOfInt2 = this.zzcci;
      int[] arrayOfInt1 = ((zzxe)paramObject).zzcci;
      byte b = 0;
      while (true) {
        if (b < i) {
          if (arrayOfInt2[b] != arrayOfInt1[b]) {
            b = 0;
            break;
          } 
          b++;
          continue;
        } 
        b = 1;
        break;
      } 
      if (b != 0) {
        Object[] arrayOfObject = this.zzcat;
        paramObject = ((zzxe)paramObject).zzcat;
        i = this.count;
        b = 0;
        while (true) {
          if (b < i) {
            if (!arrayOfObject[b].equals(paramObject[b])) {
              b = 0;
              break;
            } 
            b++;
            continue;
          } 
          b = 1;
          break;
        } 
        if (b != 0)
          return true; 
      } 
    } 
    return false;
  }
  
  public final int hashCode() {
    int k = this.count;
    int[] arrayOfInt = this.zzcci;
    boolean bool = false;
    int j = 17;
    byte b = 0;
    int i = 17;
    while (b < k) {
      i = i * 31 + arrayOfInt[b];
      b++;
    } 
    Object[] arrayOfObject = this.zzcat;
    int m = this.count;
    for (b = bool; b < m; b++)
      j = j * 31 + arrayOfObject[b].hashCode(); 
    return ((k + 527) * 31 + i) * 31 + j;
  }
  
  final void zza(zzxy paramzzxy) throws IOException {
    if (paramzzxy.zzvm() == zzuo.zze.zzbyy) {
      for (int i = this.count - 1; i >= 0; i--)
        paramzzxy.zza(this.zzcci[i] >>> 3, this.zzcat[i]); 
      return;
    } 
    for (byte b = 0; b < this.count; b++)
      paramzzxy.zza(this.zzcci[b] >>> 3, this.zzcat[b]); 
  }
  
  final void zzb(int paramInt, Object paramObject) {
    if (this.zzbtn) {
      int i = this.count;
      if (i == this.zzcci.length) {
        if (i < 4) {
          i = 8;
        } else {
          i >>= 1;
        } 
        i = this.count + i;
        this.zzcci = Arrays.copyOf(this.zzcci, i);
        this.zzcat = Arrays.copyOf(this.zzcat, i);
      } 
      int[] arrayOfInt = this.zzcci;
      i = this.count;
      arrayOfInt[i] = paramInt;
      this.zzcat[i] = paramObject;
      this.count = i + 1;
      return;
    } 
    throw new UnsupportedOperationException();
  }
  
  public final void zzb(zzxy paramzzxy) throws IOException {
    if (this.count == 0)
      return; 
    if (paramzzxy.zzvm() == zzuo.zze.zzbyx) {
      for (byte b = 0; b < this.count; b++)
        zzb(this.zzcci[b], this.zzcat[b], paramzzxy); 
      return;
    } 
    for (int i = this.count - 1; i >= 0; i--)
      zzb(this.zzcci[i], this.zzcat[i], paramzzxy); 
  }
  
  final void zzb(StringBuilder paramStringBuilder, int paramInt) {
    for (byte b = 0; b < this.count; b++)
      zzvy.zzb(paramStringBuilder, paramInt, String.valueOf(this.zzcci[b] >>> 3), this.zzcat[b]); 
  }
  
  public final void zzsw() {
    this.zzbtn = false;
  }
  
  public final int zzvx() {
    int i = this.zzbyg;
    if (i != -1)
      return i; 
    byte b = 0;
    int j = 0;
    while (b < this.count) {
      int k = this.zzcci[b];
      i = k >>> 3;
      k &= 0x7;
      if (k != 0) {
        if (k != 1) {
          if (k != 2) {
            if (k != 3) {
              if (k == 5) {
                i = zztv.zzk(i, ((Integer)this.zzcat[b]).intValue());
              } else {
                throw new IllegalStateException(zzuv.zzwu());
              } 
            } else {
              i = (zztv.zzbd(i) << 1) + ((zzxe)this.zzcat[b]).zzvx();
            } 
          } else {
            i = zztv.zzc(i, (zzte)this.zzcat[b]);
          } 
        } else {
          i = zztv.zzg(i, ((Long)this.zzcat[b]).longValue());
        } 
      } else {
        i = zztv.zze(i, ((Long)this.zzcat[b]).longValue());
      } 
      j += i;
      b++;
    } 
    this.zzbyg = j;
    return j;
  }
  
  public final int zzyn() {
    int i = this.zzbyg;
    if (i != -1)
      return i; 
    byte b = 0;
    i = 0;
    while (b < this.count) {
      i += zztv.zzd(this.zzcci[b] >>> 3, (zzte)this.zzcat[b]);
      b++;
    } 
    this.zzbyg = i;
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */