package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

final class zzww implements Iterator<Map.Entry<K, V>> {
  private int pos = -1;
  
  private Iterator<Map.Entry<K, V>> zzcbz;
  
  private final zzwo zzcca;
  
  private boolean zzcce;
  
  private zzww(zzwo paramzzwo) {}
  
  private final Iterator<Map.Entry<K, V>> zzyh() {
    if (this.zzcbz == null)
      this.zzcbz = zzwo.zzc(this.zzcca).entrySet().iterator(); 
    return this.zzcbz;
  }
  
  public final boolean hasNext() {
    return (this.pos + 1 < zzwo.zzb(this.zzcca).size() || (!zzwo.zzc(this.zzcca).isEmpty() && zzyh().hasNext()));
  }
  
  public final void remove() {
    if (this.zzcce) {
      this.zzcce = false;
      zzwo.zza(this.zzcca);
      if (this.pos < zzwo.zzb(this.zzcca).size()) {
        zzwo zzwo1 = this.zzcca;
        int i = this.pos;
        this.pos = i - 1;
        zzwo.zza(zzwo1, i);
        return;
      } 
      zzyh().remove();
      return;
    } 
    throw new IllegalStateException("remove() was called before next()");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzww.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */