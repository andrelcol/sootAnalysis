package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

final class zzvh extends zzvf {
  private static final Class<?> zzcae = Collections.unmodifiableList(Collections.emptyList()).getClass();
  
  private zzvh() {
    super(null);
  }
  
  private static <L> List<L> zza(Object<?> paramObject, long paramLong, int paramInt) {
    Object<?> object;
    List<?> list = zzc(paramObject, paramLong);
    if (list.isEmpty()) {
      ArrayList arrayList;
      if (list instanceof zzve) {
        zzvd zzvd = new zzvd(paramInt);
      } else if (list instanceof zzwg && list instanceof zzuu) {
        zzuu zzuu = ((zzuu)list).zzal(paramInt);
      } else {
        arrayList = new ArrayList(paramInt);
      } 
      zzxj.zza(paramObject, paramLong, arrayList);
    } else {
      if (zzcae.isAssignableFrom(list.getClass())) {
        ArrayList<?> arrayList = new ArrayList(list.size() + paramInt);
        arrayList.addAll(list);
        zzxj.zza(paramObject, paramLong, arrayList);
        paramObject = (Object<?>)arrayList;
      } else if (list instanceof zzxg) {
        zzvd zzvd = new zzvd(list.size() + paramInt);
        zzvd.addAll((zzxg)list);
        zzxj.zza(paramObject, paramLong, zzvd);
        paramObject = (Object<?>)zzvd;
      } else {
        List<?> list1 = list;
        if (list instanceof zzwg) {
          list1 = list;
          if (list instanceof zzuu) {
            zzuu<?> zzuu = (zzuu)list;
            list1 = list;
            if (!zzuu.zztz()) {
              list1 = zzuu.zzal(list.size() + paramInt);
              zzxj.zza(paramObject, paramLong, list1);
            } 
          } 
        } 
        return (List)list1;
      } 
      object = paramObject;
    } 
    return (List)object;
  }
  
  private static <E> List<E> zzc(Object paramObject, long paramLong) {
    return (List<E>)zzxj.zzp(paramObject, paramLong);
  }
  
  final <L> List<L> zza(Object paramObject, long paramLong) {
    return zza(paramObject, paramLong, 10);
  }
  
  final <E> void zza(Object paramObject1, Object<?> paramObject2, long paramLong) {
    paramObject2 = zzc(paramObject2, paramLong);
    List<?> list = zza(paramObject1, paramLong, paramObject2.size());
    int i = list.size();
    int j = paramObject2.size();
    if (i > 0 && j > 0)
      list.addAll((Collection<?>)paramObject2); 
    if (i > 0)
      paramObject2 = (Object<?>)list; 
    zzxj.zza(paramObject1, paramLong, paramObject2);
  }
  
  final void zzb(Object paramObject, long paramLong) {
    List<?> list = (List)zzxj.zzp(paramObject, paramLong);
    if (list instanceof zzve) {
      list = ((zzve)list).zzxc();
    } else {
      if (zzcae.isAssignableFrom(list.getClass()))
        return; 
      if (list instanceof zzwg && list instanceof zzuu) {
        paramObject = list;
        if (paramObject.zztz())
          paramObject.zzsw(); 
        return;
      } 
      list = Collections.unmodifiableList(list);
    } 
    zzxj.zza(paramObject, paramLong, list);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */