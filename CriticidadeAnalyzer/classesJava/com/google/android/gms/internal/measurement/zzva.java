package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzva<K> implements Map.Entry<K, Object> {
  private Map.Entry<K, zzuy> zzbzu;
  
  private zzva(Map.Entry<K, zzuy> paramEntry) {
    this.zzbzu = paramEntry;
  }
  
  public final K getKey() {
    return this.zzbzu.getKey();
  }
  
  public final Object getValue() {
    if ((zzuy)this.zzbzu.getValue() == null)
      return null; 
    zzuy.zzwz();
    throw null;
  }
  
  public final Object setValue(Object paramObject) {
    if (paramObject instanceof zzvv)
      return ((zzuy)this.zzbzu.getValue()).zzi((zzvv)paramObject); 
    throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
  }
  
  public final zzuy zzxa() {
    return this.zzbzu.getValue();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzva.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */