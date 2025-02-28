package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.List;

final class zzwi<E> extends zzta<E> {
  private static final zzwi<Object> zzcbo;
  
  private final List<E> zzcab;
  
  static {
    zzwi<Object> zzwi1 = new zzwi();
    zzcbo = zzwi1;
    zzwi1.zzsw();
  }
  
  zzwi() {
    this(new ArrayList<E>(10));
  }
  
  private zzwi(List<E> paramList) {
    this.zzcab = paramList;
  }
  
  public static <E> zzwi<E> zzxu() {
    return (zzwi)zzcbo;
  }
  
  public final void add(int paramInt, E paramE) {
    zzua();
    this.zzcab.add(paramInt, paramE);
    this.modCount++;
  }
  
  public final E get(int paramInt) {
    return this.zzcab.get(paramInt);
  }
  
  public final E remove(int paramInt) {
    zzua();
    E e = this.zzcab.remove(paramInt);
    this.modCount++;
    return e;
  }
  
  public final E set(int paramInt, E paramE) {
    zzua();
    paramE = this.zzcab.set(paramInt, paramE);
    this.modCount++;
    return paramE;
  }
  
  public final int size() {
    return this.zzcab.size();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */