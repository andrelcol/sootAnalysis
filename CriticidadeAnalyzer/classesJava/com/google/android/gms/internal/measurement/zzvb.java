package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzvb<K> implements Iterator<Map.Entry<K, Object>> {
  private Iterator<Map.Entry<K, Object>> zzbzv;
  
  public zzvb(Iterator<Map.Entry<K, Object>> paramIterator) {
    this.zzbzv = paramIterator;
  }
  
  public final boolean hasNext() {
    return this.zzbzv.hasNext();
  }
  
  public final void remove() {
    this.zzbzv.remove();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */