package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zztc extends zzta<Boolean> implements zzuu<Boolean>, zzwg, RandomAccess {
  private int size;
  
  private boolean[] zzbtr;
  
  static {
    (new zztc()).zzsw();
  }
  
  zztc() {
    this(new boolean[10], 0);
  }
  
  private zztc(boolean[] paramArrayOfboolean, int paramInt) {
    this.zzbtr = paramArrayOfboolean;
    this.size = paramInt;
  }
  
  private final void zza(int paramInt, boolean paramBoolean) {
    zzua();
    if (paramInt >= 0) {
      int i = this.size;
      if (paramInt <= i) {
        boolean[] arrayOfBoolean = this.zzbtr;
        if (i < arrayOfBoolean.length) {
          System.arraycopy(arrayOfBoolean, paramInt, arrayOfBoolean, paramInt + 1, i - paramInt);
        } else {
          boolean[] arrayOfBoolean1 = new boolean[i * 3 / 2 + 1];
          System.arraycopy(arrayOfBoolean, 0, arrayOfBoolean1, 0, paramInt);
          System.arraycopy(this.zzbtr, paramInt, arrayOfBoolean1, paramInt + 1, this.size - paramInt);
          this.zzbtr = arrayOfBoolean1;
        } 
        this.zzbtr[paramInt] = paramBoolean;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzak(paramInt));
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
  
  public final boolean addAll(Collection<? extends Boolean> paramCollection) {
    zzua();
    zzuq.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zztc))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    int i = ((zztc)paramCollection).size;
    if (i == 0)
      return false; 
    int j = this.size;
    if (Integer.MAX_VALUE - j >= i) {
      i = j + i;
      boolean[] arrayOfBoolean = this.zzbtr;
      if (i > arrayOfBoolean.length)
        this.zzbtr = Arrays.copyOf(arrayOfBoolean, i); 
      System.arraycopy(((zztc)paramCollection).zzbtr, 0, this.zzbtr, this.size, ((zztc)paramCollection).size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final void addBoolean(boolean paramBoolean) {
    zza(this.size, paramBoolean);
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zztc))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zztc)paramObject).size)
      return false; 
    paramObject = ((zztc)paramObject).zzbtr;
    for (byte b = 0; b < this.size; b++) {
      if (this.zzbtr[b] != paramObject[b])
        return false; 
    } 
    return true;
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + zzuq.zzu(this.zzbtr[b]); 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzua();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Boolean.valueOf(this.zzbtr[b]))) {
        paramObject = this.zzbtr;
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
      boolean[] arrayOfBoolean = this.zzbtr;
      System.arraycopy(arrayOfBoolean, paramInt2, arrayOfBoolean, paramInt1, this.size - paramInt2);
      this.size -= paramInt2 - paramInt1;
      this.modCount++;
      return;
    } 
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size() {
    return this.size;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */