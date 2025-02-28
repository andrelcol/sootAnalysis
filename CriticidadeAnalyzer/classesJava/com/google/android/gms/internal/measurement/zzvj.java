package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzvj extends zzta<Long> implements zzuu<Long>, zzwg, RandomAccess {
  private int size;
  
  private long[] zzcag;
  
  static {
    (new zzvj()).zzsw();
  }
  
  zzvj() {
    this(new long[10], 0);
  }
  
  private zzvj(long[] paramArrayOflong, int paramInt) {
    this.zzcag = paramArrayOflong;
    this.size = paramInt;
  }
  
  private final void zzaj(int paramInt) {
    if (paramInt >= 0 && paramInt < this.size)
      return; 
    throw new IndexOutOfBoundsException(zzak(paramInt));
  }
  
  private final String zzak(int paramInt) {
    int i = this.size;
    StringBuilder stringBuilder = new StringBuilder(35);
    stringBuilder.append("Index:");
    stringBuilder.append(paramInt);
    stringBuilder.append(", Size:");
    stringBuilder.append(i);
    return stringBuilder.toString();
  }
  
  private final void zzk(int paramInt, long paramLong) {
    zzua();
    if (paramInt >= 0) {
      int i = this.size;
      if (paramInt <= i) {
        long[] arrayOfLong = this.zzcag;
        if (i < arrayOfLong.length) {
          System.arraycopy(arrayOfLong, paramInt, arrayOfLong, paramInt + 1, i - paramInt);
        } else {
          long[] arrayOfLong1 = new long[i * 3 / 2 + 1];
          System.arraycopy(arrayOfLong, 0, arrayOfLong1, 0, paramInt);
          System.arraycopy(this.zzcag, paramInt, arrayOfLong1, paramInt + 1, this.size - paramInt);
          this.zzcag = arrayOfLong1;
        } 
        this.zzcag[paramInt] = paramLong;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzak(paramInt));
  }
  
  public final boolean addAll(Collection<? extends Long> paramCollection) {
    zzua();
    zzuq.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zzvj))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    int i = ((zzvj)paramCollection).size;
    if (i == 0)
      return false; 
    int j = this.size;
    if (Integer.MAX_VALUE - j >= i) {
      i = j + i;
      long[] arrayOfLong = this.zzcag;
      if (i > arrayOfLong.length)
        this.zzcag = Arrays.copyOf(arrayOfLong, i); 
      System.arraycopy(((zzvj)paramCollection).zzcag, 0, this.zzcag, this.size, ((zzvj)paramCollection).size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzvj))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zzvj)paramObject).size)
      return false; 
    paramObject = ((zzvj)paramObject).zzcag;
    for (byte b = 0; b < this.size; b++) {
      if (this.zzcag[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public final long getLong(int paramInt) {
    zzaj(paramInt);
    return this.zzcag[paramInt];
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + zzuq.zzbd(this.zzcag[b]); 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzua();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Long.valueOf(this.zzcag[b]))) {
        paramObject = this.zzcag;
        System.arraycopy(paramObject, b + 1, paramObject, b, this.size - b - 1);
        this.size--;
        this.modCount++;
        return true;
      } 
    } 
    return false;
  }
  
  protected final void removeRange(int paramInt1, int paramInt2) {
    zzua();
    if (paramInt2 >= paramInt1) {
      long[] arrayOfLong = this.zzcag;
      System.arraycopy(arrayOfLong, paramInt2, arrayOfLong, paramInt1, this.size - paramInt2);
      this.size -= paramInt2 - paramInt1;
      this.modCount++;
      return;
    } 
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size() {
    return this.size;
  }
  
  public final void zzbe(long paramLong) {
    zzk(this.size, paramLong);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */