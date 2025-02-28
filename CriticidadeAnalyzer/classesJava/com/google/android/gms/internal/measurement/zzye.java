package com.google.android.gms.internal.measurement;

public final class zzye implements Cloneable {
  private static final zzyf zzcey = new zzyf();
  
  private int mSize;
  
  private int[] zzcfa;
  
  private zzyf[] zzcfb;
  
  zzye() {
    this(10);
  }
  
  private zzye(int paramInt) {
    paramInt = idealIntArraySize(paramInt);
    this.zzcfa = new int[paramInt];
    this.zzcfb = new zzyf[paramInt];
    this.mSize = 0;
  }
  
  private static int idealIntArraySize(int paramInt) {
    int i;
    int j = paramInt << 2;
    paramInt = 4;
    while (true) {
      i = j;
      if (paramInt < 32) {
        i = (1 << paramInt) - 12;
        if (j <= i)
          break; 
        paramInt++;
        continue;
      } 
      break;
    } 
    return i / 4;
  }
  
  private final int zzcg(int paramInt) {
    int j = this.mSize - 1;
    int i = 0;
    while (i <= j) {
      int k = i + j >>> 1;
      int m = this.zzcfa[k];
      if (m < paramInt) {
        i = k + 1;
        continue;
      } 
      if (m > paramInt) {
        j = k - 1;
        continue;
      } 
      return k;
    } 
    return i ^ 0xFFFFFFFF;
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof zzye))
      return false; 
    paramObject = paramObject;
    int i = this.mSize;
    if (i != ((zzye)paramObject).mSize)
      return false; 
    int[] arrayOfInt1 = this.zzcfa;
    int[] arrayOfInt2 = ((zzye)paramObject).zzcfa;
    byte b = 0;
    while (true) {
      if (b < i) {
        if (arrayOfInt1[b] != arrayOfInt2[b]) {
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
      zzyf[] arrayOfZzyf = this.zzcfb;
      paramObject = ((zzye)paramObject).zzcfb;
      i = this.mSize;
      b = 0;
      while (true) {
        if (b < i) {
          if (!arrayOfZzyf[b].equals(paramObject[b])) {
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
    return false;
  }
  
  public final int hashCode() {
    int i = 17;
    for (byte b = 0; b < this.mSize; b++)
      i = (i * 31 + this.zzcfa[b]) * 31 + this.zzcfb[b].hashCode(); 
    return i;
  }
  
  public final boolean isEmpty() {
    return (this.mSize == 0);
  }
  
  final int size() {
    return this.mSize;
  }
  
  final void zza(int paramInt, zzyf paramzzyf) {
    int i = zzcg(paramInt);
    if (i >= 0) {
      this.zzcfb[i] = paramzzyf;
      return;
    } 
    i ^= 0xFFFFFFFF;
    if (i < this.mSize) {
      zzyf[] arrayOfZzyf = this.zzcfb;
      if (arrayOfZzyf[i] == zzcey) {
        this.zzcfa[i] = paramInt;
        arrayOfZzyf[i] = paramzzyf;
        return;
      } 
    } 
    int j = this.mSize;
    if (j >= this.zzcfa.length) {
      j = idealIntArraySize(j + 1);
      int[] arrayOfInt1 = new int[j];
      zzyf[] arrayOfZzyf1 = new zzyf[j];
      int[] arrayOfInt2 = this.zzcfa;
      System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 0, arrayOfInt2.length);
      zzyf[] arrayOfZzyf2 = this.zzcfb;
      System.arraycopy(arrayOfZzyf2, 0, arrayOfZzyf1, 0, arrayOfZzyf2.length);
      this.zzcfa = arrayOfInt1;
      this.zzcfb = arrayOfZzyf1;
    } 
    int k = this.mSize;
    if (k - i != 0) {
      int[] arrayOfInt = this.zzcfa;
      j = i + 1;
      System.arraycopy(arrayOfInt, i, arrayOfInt, j, k - i);
      zzyf[] arrayOfZzyf = this.zzcfb;
      System.arraycopy(arrayOfZzyf, i, arrayOfZzyf, j, this.mSize - i);
    } 
    this.zzcfa[i] = paramInt;
    this.zzcfb[i] = paramzzyf;
    this.mSize++;
  }
  
  final zzyf zzce(int paramInt) {
    paramInt = zzcg(paramInt);
    if (paramInt >= 0) {
      zzyf[] arrayOfZzyf = this.zzcfb;
      if (arrayOfZzyf[paramInt] != zzcey)
        return arrayOfZzyf[paramInt]; 
    } 
    return null;
  }
  
  final zzyf zzcf(int paramInt) {
    return this.zzcfb[paramInt];
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */