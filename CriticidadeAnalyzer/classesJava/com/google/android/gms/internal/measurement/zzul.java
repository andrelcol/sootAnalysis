package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzul extends zzta<Float> implements zzuu<Float>, zzwg, RandomAccess {
  private int size;
  
  private float[] zzbyc;
  
  static {
    (new zzul()).zzsw();
  }
  
  zzul() {
    this(new float[10], 0);
  }
  
  private zzul(float[] paramArrayOffloat, int paramInt) {
    this.zzbyc = paramArrayOffloat;
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
  
  private final void zzc(int paramInt, float paramFloat) {
    zzua();
    if (paramInt >= 0) {
      int i = this.size;
      if (paramInt <= i) {
        float[] arrayOfFloat = this.zzbyc;
        if (i < arrayOfFloat.length) {
          System.arraycopy(arrayOfFloat, paramInt, arrayOfFloat, paramInt + 1, i - paramInt);
        } else {
          float[] arrayOfFloat1 = new float[i * 3 / 2 + 1];
          System.arraycopy(arrayOfFloat, 0, arrayOfFloat1, 0, paramInt);
          System.arraycopy(this.zzbyc, paramInt, arrayOfFloat1, paramInt + 1, this.size - paramInt);
          this.zzbyc = arrayOfFloat1;
        } 
        this.zzbyc[paramInt] = paramFloat;
        this.size++;
        this.modCount++;
        return;
      } 
    } 
    throw new IndexOutOfBoundsException(zzak(paramInt));
  }
  
  public final boolean addAll(Collection<? extends Float> paramCollection) {
    zzua();
    zzuq.checkNotNull(paramCollection);
    if (!(paramCollection instanceof zzul))
      return super.addAll(paramCollection); 
    paramCollection = paramCollection;
    int i = ((zzul)paramCollection).size;
    if (i == 0)
      return false; 
    int j = this.size;
    if (Integer.MAX_VALUE - j >= i) {
      i = j + i;
      float[] arrayOfFloat = this.zzbyc;
      if (i > arrayOfFloat.length)
        this.zzbyc = Arrays.copyOf(arrayOfFloat, i); 
      System.arraycopy(((zzul)paramCollection).zzbyc, 0, this.zzbyc, this.size, ((zzul)paramCollection).size);
      this.size = i;
      this.modCount++;
      return true;
    } 
    throw new OutOfMemoryError();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzul))
      return super.equals(paramObject); 
    paramObject = paramObject;
    if (this.size != ((zzul)paramObject).size)
      return false; 
    paramObject = ((zzul)paramObject).zzbyc;
    for (byte b = 0; b < this.size; b++) {
      if (Float.floatToIntBits(this.zzbyc[b]) != Float.floatToIntBits(paramObject[b]))
        return false; 
    } 
    return true;
  }
  
  public final int hashCode() {
    int i = 1;
    for (byte b = 0; b < this.size; b++)
      i = i * 31 + Float.floatToIntBits(this.zzbyc[b]); 
    return i;
  }
  
  public final boolean remove(Object paramObject) {
    zzua();
    for (byte b = 0; b < this.size; b++) {
      if (paramObject.equals(Float.valueOf(this.zzbyc[b]))) {
        paramObject = this.zzbyc;
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
      float[] arrayOfFloat = this.zzbyc;
      System.arraycopy(arrayOfFloat, paramInt2, arrayOfFloat, paramInt1, this.size - paramInt2);
      this.size -= paramInt2 - paramInt1;
      this.modCount++;
      return;
    } 
    throw new IndexOutOfBoundsException("toIndex < fromIndex");
  }
  
  public final int size() {
    return this.size;
  }
  
  public final void zzc(float paramFloat) {
    zzc(this.size, paramFloat);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzul.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */