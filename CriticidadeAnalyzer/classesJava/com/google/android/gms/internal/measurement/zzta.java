package com.google.android.gms.internal.measurement;

import java.util.AbstractList;
import java.util.Collection;

abstract class zzta<E> extends AbstractList<E> implements zzuu<E> {
  private boolean zzbtn = true;
  
  public boolean add(E paramE) {
    zzua();
    return super.add(paramE);
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection) {
    zzua();
    return super.addAll(paramInt, paramCollection);
  }
  
  public boolean addAll(Collection<? extends E> paramCollection) {
    zzua();
    return super.addAll(paramCollection);
  }
  
  public void clear() {
    zzua();
    super.clear();
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject == this)
      return true; 
    if (!(paramObject instanceof java.util.List))
      return false; 
    if (!(paramObject instanceof java.util.RandomAccess))
      return super.equals(paramObject); 
    paramObject = paramObject;
    int i = size();
    if (i != paramObject.size())
      return false; 
    for (byte b = 0; b < i; b++) {
      if (!get(b).equals(paramObject.get(b)))
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    int j = size();
    int i = 1;
    for (byte b = 0; b < j; b++)
      i = i * 31 + get(b).hashCode(); 
    return i;
  }
  
  public boolean remove(Object paramObject) {
    zzua();
    return super.remove(paramObject);
  }
  
  public boolean removeAll(Collection<?> paramCollection) {
    zzua();
    return super.removeAll(paramCollection);
  }
  
  public boolean retainAll(Collection<?> paramCollection) {
    zzua();
    return super.retainAll(paramCollection);
  }
  
  public final void zzsw() {
    this.zzbtn = false;
  }
  
  public boolean zztz() {
    return this.zzbtn;
  }
  
  protected final void zzua() {
    if (this.zzbtn)
      return; 
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzta.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */