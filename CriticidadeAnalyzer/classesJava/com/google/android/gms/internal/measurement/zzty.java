package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzty extends zzta<Double> implements zzuu<Double>, zzwg, RandomAccess {
  private int size;
  
  private double[] zzbva;
  
  static {
    (new zzty()).zzsw();
  }
  
  zzty() {
    this(new double[10], 0);
  }
  
  private zzty(double[] paramArrayOfdouble, int paramInt) {
    this.zzbva = paramArrayOfdouble;
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
  
  private final void zzc(int paramInt, double paramDouble) {
    zzua();
    if (paramInt >= 0) {
      int i = this.size;
      if (paramInt <= i) {
        double[] arrayOfDouble = this.zzbva;
        if (i < arrayOfDouble.length) {
          System.arraycopy(arrayOfDouble, paramInt, arrayOfDouble, paramInt + 1, i - paramInt);
        } else {
          double[] arrayOfDouble1 = new double[i * 3 / 2 + 1];
          System.arraycopy(arrayOfDouble, 0, arrayOfDouble1, 0, paramInt);
          System.arraycopy(this.zzbva, paramInt, arrayOfDouble1, paramInt + 1, this.size - paramInt);
          this.zzbva = arrayOfDouble1;
        } 
        this.zzbva[paramInt] = paramDouble;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzak(paramInt));
  }
  
  public final boolean addAll(Collection<? extends Double> paramCollection) {
    zzua();
    zzuq.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zzty))
      return super.addAll(paramCollection); 
    zzty zzty1 = (zzty)paramCollection;
    int i = zzty1.size;
    if (i == 0)
      return false; 
    int j = this.size;
    if (Integer.MAX_VALUE - j >= i) {
      i = j + i;
      double[] arrayOfDouble = this.zzbva;
      if (i > arrayOfDouble.length)
        this.zzbva = Arrays.copyOf(arrayOfDouble, i); 
      System.arraycopy(zzty1.zzbva, 0, this.zzbva, this.size, zzty1.size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzty))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zzty)paramObject).size)
      return false; 
    paramObject = ((zzty)paramObject).zzbva;
    for (byte b = 0; b < this.size; b++) {
      if (Double.doubleToLongBits(this.zzbva[b]) != Double.doubleToLongBits(paramObject[b]))
        return false; 
    } 
    return true;
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + zzuq.zzbd(Double.doubleToLongBits(this.zzbva[b])); 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzua();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Double.valueOf(this.zzbva[b]))) {
        paramObject = this.zzbva;
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
      double[] arrayOfDouble = this.zzbva;
      System.arraycopy(arrayOfDouble, paramInt2, arrayOfDouble, paramInt1, this.size - paramInt2);
      this.size -= paramInt2 - paramInt1;
      this.modCount++;
      return;
    } 
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size() {
    return this.size;
  }
  
  public final void zzd(double paramDouble) {
    zzc(this.size, paramDouble);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */