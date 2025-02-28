package com.google.android.gms.internal.measurement;

import java.util.List;

final class zzvi extends zzvf {
  private zzvi() {
    super(null);
  }
  
  private static <E> zzuu<E> zzd(Object paramObject, long paramLong) {
    return (zzuu<E>)zzxj.zzp(paramObject, paramLong);
  }
  
  final <L> List<L> zza(Object paramObject, long paramLong) {
    zzuu<?> zzuu2 = zzd(paramObject, paramLong);
    zzuu<?> zzuu1 = zzuu2;
    if (!zzuu2.zztz()) {
      int i = zzuu2.size();
      if (i == 0) {
        i = 10;
      } else {
        i <<= 1;
      } 
      zzuu1 = zzuu2.zzal(i);
      zzxj.zza(paramObject, paramLong, zzuu1);
    } 
    return (List)zzuu1;
  }
  
  final <E> void zza(Object paramObject1, Object<?> paramObject2, long paramLong) {
    Object<?> object;
    zzuu<?> zzuu1 = zzd(paramObject1, paramLong);
    zzuu<?> zzuu2 = zzd(paramObject2, paramLong);
    int i = zzuu1.size();
    int j = zzuu2.size();
    paramObject2 = (Object<?>)zzuu1;
    if (i > 0) {
      paramObject2 = (Object<?>)zzuu1;
      if (j > 0) {
        paramObject2 = (Object<?>)zzuu1;
        if (!zzuu1.zztz())
          paramObject2 = (Object<?>)zzuu1.zzal(j + i); 
        paramObject2.addAll(zzuu2);
      } 
    } 
    zzuu1 = zzuu2;
    if (i > 0)
      object = paramObject2; 
    zzxj.zza(paramObject1, paramLong, object);
  }
  
  final void zzb(Object paramObject, long paramLong) {
    zzd(paramObject, paramLong).zzsw();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */