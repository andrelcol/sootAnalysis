package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class zzwh {
  private static final zzwh zzcbl = new zzwh();
  
  private final zzwm zzcbm = new zzvk();
  
  private final ConcurrentMap<Class<?>, zzwl<?>> zzcbn = new ConcurrentHashMap<Class<?>, zzwl<?>>();
  
  public static zzwh zzxt() {
    return zzcbl;
  }
  
  public final <T> zzwl<T> zzak(T paramT) {
    return zzi((Class)paramT.getClass());
  }
  
  public final <T> zzwl<T> zzi(Class<T> paramClass) {
    zzuq.zza(paramClass, "messageType");
    zzwl<T> zzwl2 = (zzwl)this.zzcbn.get(paramClass);
    zzwl<T> zzwl1 = zzwl2;
    if (zzwl2 == null) {
      zzwl1 = this.zzcbm.zzh(paramClass);
      zzuq.zza(paramClass, "messageType");
      zzuq.zza(zzwl1, "schema");
      zzwl<T> zzwl = (zzwl)this.zzcbn.putIfAbsent(paramClass, zzwl1);
      if (zzwl != null)
        zzwl1 = zzwl; 
    } 
    return zzwl1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */