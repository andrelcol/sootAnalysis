package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzwq implements Iterator<Map.Entry<K, V>> {
  private int pos = zzwo.zzb(this.zzcca).size();
  
  private Iterator<Map.Entry<K, V>> zzcbz;
  
  private final zzwo zzcca;
  
  private zzwq(zzwo paramzzwo) {}
  
  private final Iterator<Map.Entry<K, V>> zzyh() {
    if (this.zzcbz == null)
      this.zzcbz = zzwo.zzd(this.zzcca).entrySet().iterator(); 
    return this.zzcbz;
  }
  
  public final boolean hasNext() {
    int i = this.pos;
    return ((i > 0 && i <= zzwo.zzb(this.zzcca).size()) || zzyh().hasNext());
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */