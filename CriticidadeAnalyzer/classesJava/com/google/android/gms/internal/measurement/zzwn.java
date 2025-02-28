package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

final class zzwn {
  private static final Class<?> zzcbp = zzya();
  
  private static final zzxd<?, ?> zzcbq = zzv(false);
  
  private static final zzxd<?, ?> zzcbr = zzv(true);
  
  private static final zzxd<?, ?> zzcbs = new zzxf();
  
  static <UT, UB> UB zza(int paramInt1, int paramInt2, UB paramUB, zzxd<UT, UB> paramzzxd) {
    UB uB = paramUB;
    if (paramUB == null)
      uB = paramzzxd.zzyk(); 
    paramzzxd.zza(uB, paramInt1, paramInt2);
    return uB;
  }
  
  static <UT, UB> UB zza(int paramInt, List<Integer> paramList, zzut paramzzut, UB paramUB, zzxd<UT, UB> paramzzxd) {
    UB uB;
    if (paramzzut == null)
      return paramUB; 
    if (paramList instanceof java.util.RandomAccess) {
      int i = paramList.size();
      byte b1 = 0;
      byte b2 = 0;
      while (b1 < i) {
        int j = ((Integer)paramList.get(b1)).intValue();
        if (paramzzut.zzb(j)) {
          if (b1 != b2)
            paramList.set(b2, Integer.valueOf(j)); 
          b2++;
        } else {
          paramUB = zza(paramInt, j, paramUB, paramzzxd);
        } 
        b1++;
      } 
      uB = paramUB;
      if (b2 != i) {
        paramList.subList(b2, i).clear();
        uB = paramUB;
      } 
    } else {
      Iterator<Integer> iterator = paramList.iterator();
      while (true) {
        uB = paramUB;
        if (iterator.hasNext()) {
          int i = ((Integer)iterator.next()).intValue();
          if (!paramzzut.zzb(i)) {
            paramUB = zza(paramInt, i, paramUB, paramzzxd);
            iterator.remove();
          } 
          continue;
        } 
        break;
      } 
    } 
    return uB;
  }
  
  public static void zza(int paramInt, List<String> paramList, zzxy paramzzxy) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zza(paramInt, paramList); 
  }
  
  public static void zza(int paramInt, List<?> paramList, zzxy paramzzxy, zzwl paramzzwl) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zza(paramInt, paramList, paramzzwl); 
  }
  
  public static void zza(int paramInt, List<Double> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzg(paramInt, paramList, paramBoolean); 
  }
  
  static <T, FT extends zzuh<FT>> void zza(zzuc<FT> paramzzuc, T paramT1, T paramT2) {
    zzuf<FT> zzuf = paramzzuc.zzw(paramT2);
    if (!zzuf.isEmpty())
      paramzzuc.zzx(paramT1).zza(zzuf); 
  }
  
  static <T> void zza(zzvq paramzzvq, T paramT1, T paramT2, long paramLong) {
    zzxj.zza(paramT1, paramLong, paramzzvq.zzc(zzxj.zzp(paramT1, paramLong), zzxj.zzp(paramT2, paramLong)));
  }
  
  static <T, UT, UB> void zza(zzxd<UT, UB> paramzzxd, T paramT1, T paramT2) {
    paramzzxd.zzf(paramT1, paramzzxd.zzh(paramzzxd.zzal(paramT1), paramzzxd.zzal(paramT2)));
  }
  
  static int zzaa(List<Long> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzay(paramList.getLong(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzay(((Long)paramList.get(j)).longValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzab(List<Integer> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzup) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbj(paramList.getInt(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbj(((Integer)paramList.get(j)).intValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzac(List<Integer> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzup) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbe(paramList.getInt(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbe(((Integer)paramList.get(j)).intValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzad(List<Integer> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzup) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbf(paramList.getInt(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbf(((Integer)paramList.get(j)).intValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzae(List<Integer> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzup) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbg(paramList.getInt(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzbg(((Integer)paramList.get(j)).intValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzaf(List<?> paramList) {
    return paramList.size() << 2;
  }
  
  static int zzag(List<?> paramList) {
    return paramList.size() << 3;
  }
  
  static int zzah(List<?> paramList) {
    return paramList.size();
  }
  
  public static void zzb(int paramInt, List<zzte> paramList, zzxy paramzzxy) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzb(paramInt, paramList); 
  }
  
  public static void zzb(int paramInt, List<?> paramList, zzxy paramzzxy, zzwl paramzzwl) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzb(paramInt, paramList, paramzzwl); 
  }
  
  public static void zzb(int paramInt, List<Float> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzf(paramInt, paramList, paramBoolean); 
  }
  
  static int zzc(int paramInt, Object paramObject, zzwl paramzzwl) {
    return (paramObject instanceof zzvc) ? zztv.zza(paramInt, (zzvc)paramObject) : zztv.zzb(paramInt, (zzvv)paramObject, paramzzwl);
  }
  
  static int zzc(int paramInt, List<?> paramList) {
    Object object;
    int j = paramList.size();
    byte b1 = 0;
    byte b2 = 0;
    if (j == 0)
      return 0; 
    int i = zztv.zzbd(paramInt) * j;
    paramInt = i;
    if (paramList instanceof zzve) {
      zzve zzve = (zzve)paramList;
      paramInt = i;
      b1 = b2;
      while (true) {
        i = paramInt;
        if (b1 < j) {
          object = zzve.zzbp(b1);
          if (object instanceof zzte) {
            i = zztv.zzb((zzte)object);
          } else {
            i = zztv.zzgc((String)object);
          } 
          paramInt += i;
          b1++;
          continue;
        } 
        break;
      } 
    } else {
      while (true) {
        i = paramInt;
        if (b1 < j) {
          zzte zzte = (zzte)object.get(b1);
          if (zzte instanceof zzte) {
            i = zztv.zzb(zzte);
          } else {
            i = zztv.zzgc((String)zzte);
          } 
          paramInt += i;
          b1++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  static int zzc(int paramInt, List<?> paramList, zzwl paramzzwl) {
    int k = paramList.size();
    int j = 0;
    if (k == 0)
      return 0; 
    int i = zztv.zzbd(paramInt) * k;
    for (paramInt = j; paramInt < k; paramInt++) {
      Object object = paramList.get(paramInt);
      if (object instanceof zzvc) {
        j = zztv.zza((zzvc)object);
      } else {
        j = zztv.zzb((zzvv)object, paramzzwl);
      } 
      i += j;
    } 
    return i;
  }
  
  public static void zzc(int paramInt, List<Long> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzc(paramInt, paramList, paramBoolean); 
  }
  
  static int zzd(int paramInt, List<zzte> paramList) {
    int j = paramList.size();
    int i = 0;
    if (j == 0)
      return 0; 
    j *= zztv.zzbd(paramInt);
    paramInt = i;
    i = j;
    while (paramInt < paramList.size()) {
      i += zztv.zzb(paramList.get(paramInt));
      paramInt++;
    } 
    return i;
  }
  
  static int zzd(int paramInt, List<zzvv> paramList, zzwl paramzzwl) {
    int j = paramList.size();
    byte b = 0;
    if (j == 0)
      return 0; 
    int i = 0;
    while (b < j) {
      i += zztv.zzc(paramInt, paramList.get(b), paramzzwl);
      b++;
    } 
    return i;
  }
  
  public static void zzd(int paramInt, List<Long> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzd(paramInt, paramList, paramBoolean); 
  }
  
  public static void zze(int paramInt, List<Long> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzn(paramInt, paramList, paramBoolean); 
  }
  
  static boolean zze(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject1.equals(paramObject2)));
  }
  
  public static void zzf(int paramInt, List<Long> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zze(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzg(int paramInt, List<Long> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzl(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzh(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zza(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzi(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzj(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzj(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzm(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzj(Class<?> paramClass) {
    if (!zzuo.class.isAssignableFrom(paramClass)) {
      Class<?> clazz = zzcbp;
      if (clazz != null && !clazz.isAssignableFrom(paramClass))
        throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite"); 
    } 
  }
  
  public static void zzk(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzb(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzl(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzk(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzm(int paramInt, List<Integer> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzh(paramInt, paramList, paramBoolean); 
  }
  
  public static void zzn(int paramInt, List<Boolean> paramList, zzxy paramzzxy, boolean paramBoolean) throws IOException {
    if (paramList != null && !paramList.isEmpty())
      paramzzxy.zzi(paramInt, paramList, paramBoolean); 
  }
  
  static int zzo(int paramInt, List<Long> paramList, boolean paramBoolean) {
    return (paramList.size() == 0) ? 0 : (zzy(paramList) + paramList.size() * zztv.zzbd(paramInt));
  }
  
  static int zzp(int paramInt, List<Long> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzz(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzq(int paramInt, List<Long> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzaa(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzr(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzab(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzs(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzac(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzt(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzad(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzu(int paramInt, List<Integer> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (zzae(paramList) + i * zztv.zzbd(paramInt));
  }
  
  static int zzv(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (i * zztv.zzk(paramInt, 0));
  }
  
  private static zzxd<?, ?> zzv(boolean paramBoolean) {
    try {
      return (clazz == null) ? null : clazz.getConstructor(new Class[] { boolean.class }).newInstance(new Object[] { Boolean.valueOf(paramBoolean) });
    } finally {
      Exception exception = null;
    } 
  }
  
  static int zzw(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (i * zztv.zzg(paramInt, 0L));
  }
  
  static int zzx(int paramInt, List<?> paramList, boolean paramBoolean) {
    int i = paramList.size();
    return (i == 0) ? 0 : (i * zztv.zzc(paramInt, true));
  }
  
  public static zzxd<?, ?> zzxx() {
    return zzcbq;
  }
  
  public static zzxd<?, ?> zzxy() {
    return zzcbr;
  }
  
  public static zzxd<?, ?> zzxz() {
    return zzcbs;
  }
  
  static int zzy(List<Long> paramList) {
    int k = paramList.size();
    int j = 0;
    int i = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzaw(paramList.getLong(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzaw(((Long)paramList.get(j)).longValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
  
  private static Class<?> zzya() {
    try {
      return Class.forName("com.google.protobuf.GeneratedMessage");
    } finally {
      Exception exception = null;
    } 
  }
  
  private static Class<?> zzyb() {
    try {
      return Class.forName("com.google.protobuf.UnknownFieldSetSchema");
    } finally {
      Exception exception = null;
    } 
  }
  
  static int zzz(List<Long> paramList) {
    int k = paramList.size();
    int i = 0;
    int j = 0;
    if (k == 0)
      return 0; 
    if (paramList instanceof zzvj) {
      paramList = paramList;
      int m = 0;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzax(paramList.getLong(j));
          j++;
          continue;
        } 
        break;
      } 
    } else {
      int m = 0;
      j = i;
      while (true) {
        i = m;
        if (j < k) {
          m += zztv.zzax(((Long)paramList.get(j)).longValue());
          j++;
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */