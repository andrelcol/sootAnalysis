package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzup extends zzta<Integer> implements zzuu<Integer>, zzwg, RandomAccess {
  private int size;
  
  private int[] zzbzb;
  
  static {
    (new zzup()).zzsw();
  }
  
  zzup() {
    this(new int[10], 0);
  }
  
  private zzup(int[] paramArrayOfint, int paramInt) {
    this.zzbzb = paramArrayOfint;
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
  
  private final void zzp(int paramInt1, int paramInt2) {
    zzua();
    if (paramInt1 >= 0) {
      int i = this.size;
      if (paramInt1 <= i) {
        int[] arrayOfInt = this.zzbzb;
        if (i < arrayOfInt.length) {
          System.arraycopy(arrayOfInt, paramInt1, arrayOfInt, paramInt1 + 1, i - paramInt1);
        } else {
          int[] arrayOfInt1 = new int[i * 3 / 2 + 1];
          System.arraycopy(arrayOfInt, 0, arrayOfInt1, 0, paramInt1);
          System.arraycopy(this.zzbzb, paramInt1, arrayOfInt1, paramInt1 + 1, this.size - paramInt1);
          this.zzbzb = arrayOfInt1;
        } 
        this.zzbzb[paramInt1] = paramInt2;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzak(paramInt1));
  }
  
  public final boolean addAll(Collection<? extends Integer> paramCollection) {
    zzua();
    zzuq.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zzup))
      return super.addAll(paramCollection); 
    zzup zzup1 = (zzup)paramCollection;
    int j = zzup1.size;
    if (j == 0)
      return false; 
    int i = this.size;
    if (Integer.MAX_VALUE - i >= j) {
      i += j;
      int[] arrayOfInt = this.zzbzb;
      if (i > arrayOfInt.length)
        this.zzbzb = Arrays.copyOf(arrayOfInt, i); 
      System.arraycopy(zzup1.zzbzb, 0, this.zzbzb, this.size, zzup1.size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzup))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zzup)paramObject).size)
      return false; 
    paramObject = ((zzup)paramObject).zzbzb;
    for (byte b = 0; b < this.size; b++) {
      if (this.zzbzb[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public final int getInt(int paramInt) {
    zzaj(paramInt);
    return this.zzbzb[paramInt];
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + this.zzbzb[b]; 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzua();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Integer.valueOf(this.zzbzb[b]))) {
        paramObject = this.zzbzb;
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
      int[] arrayOfInt = this.zzbzb;
      System.arraycopy(arrayOfInt, paramInt2, arrayOfInt, paramInt1, this.size - paramInt2);
      this.size -= paramInt2 - paramInt1;
      this.modCount++;
      return;
    } 
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size() {
    return this.size;
  }
  
  public final void zzbo(int paramInt) {
    zzp(this.size, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */