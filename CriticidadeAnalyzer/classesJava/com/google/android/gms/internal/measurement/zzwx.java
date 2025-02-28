package com.google.android.gms.internal.measurement;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

class zzwx extends AbstractSet<Map.Entry<K, V>> {
  private final zzwo zzcca;
  
  private zzwx(zzwo paramzzwo) {}
  
  public void clear() {
    this.zzcca.clear();
  }
  
  public boolean contains(Object paramObject) {
    Map.Entry entry = (Map.Entry)paramObject;
    paramObject = this.zzcca.get(entry.getKey());
    entry = (Map.Entry)entry.getValue();
    return (paramObject == entry || (paramObject != null && paramObject.equals(entry)));
  }
  
  public Iterator<Map.Entry<K, V>> iterator() {
    return new zzww(this.zzcca, null);
  }
  
  public boolean remove(Object paramObject) {
    paramObject = paramObject;
    if (contains(paramObject)) {
      this.zzcca.remove(paramObject.getKey());
      return true;
    } 
    return false;
  }
  
  public int size() {
    return this.zzcca.size();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */