package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class zzuf<FieldDescriptorType extends zzuh<FieldDescriptorType>> {
  private static final zzuf zzbvl = new zzuf(true);
  
  private boolean zzbqa;
  
  private final zzwo<FieldDescriptorType, Object> zzbvj = zzwo.zzbw(16);
  
  private boolean zzbvk = false;
  
  private zzuf() {}
  
  private zzuf(boolean paramBoolean) {
    zzsw();
  }
  
  static int zza(zzxs paramzzxs, int paramInt, Object paramObject) {
    int i = zztv.zzbd(paramInt);
    paramInt = i;
    if (paramzzxs == zzxs.zzcds) {
      zzuq.zzf((zzvv)paramObject);
      paramInt = i << 1;
    } 
    return paramInt + zzb(paramzzxs, paramObject);
  }
  
  private final Object zza(FieldDescriptorType paramFieldDescriptorType) {
    paramFieldDescriptorType = (FieldDescriptorType)this.zzbvj.get(paramFieldDescriptorType);
    if (!(paramFieldDescriptorType instanceof zzuy))
      return paramFieldDescriptorType; 
    zzuy.zzwz();
    throw null;
  }
  
  private final void zza(FieldDescriptorType paramFieldDescriptorType, Object paramObject) {
    if (paramFieldDescriptorType.zzwb()) {
      if (paramObject instanceof List) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll((List)paramObject);
        int i = arrayList.size();
        byte b = 0;
        while (b < i) {
          paramObject = arrayList.get(b);
          b++;
          zza(paramFieldDescriptorType.zzvz(), paramObject);
        } 
        paramObject = arrayList;
      } else {
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      } 
    } else {
      zza(paramFieldDescriptorType.zzvz(), paramObject);
    } 
    if (paramObject instanceof zzuy)
      this.zzbvk = true; 
    this.zzbvj.zza(paramFieldDescriptorType, paramObject);
  }
  
  private static void zza(zzxs paramzzxs, Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: getstatic com/google/android/gms/internal/measurement/zzug.zzbvm : [I
    //   8: aload_0
    //   9: invokevirtual zzyv : ()Lcom/google/android/gms/internal/measurement/zzxx;
    //   12: invokevirtual ordinal : ()I
    //   15: iaload
    //   16: istore_2
    //   17: iconst_0
    //   18: istore_3
    //   19: iload_2
    //   20: tableswitch default -> 72, 1 -> 168, 2 -> 160, 3 -> 152, 4 -> 144, 5 -> 136, 6 -> 128, 7 -> 111, 8 -> 92, 9 -> 75
    //   72: goto -> 173
    //   75: aload_1
    //   76: instanceof com/google/android/gms/internal/measurement/zzvv
    //   79: ifne -> 106
    //   82: aload_1
    //   83: instanceof com/google/android/gms/internal/measurement/zzuy
    //   86: ifeq -> 173
    //   89: goto -> 106
    //   92: aload_1
    //   93: instanceof java/lang/Integer
    //   96: ifne -> 106
    //   99: aload_1
    //   100: instanceof com/google/android/gms/internal/measurement/zzur
    //   103: ifeq -> 173
    //   106: iconst_1
    //   107: istore_3
    //   108: goto -> 173
    //   111: aload_1
    //   112: instanceof com/google/android/gms/internal/measurement/zzte
    //   115: ifne -> 106
    //   118: aload_1
    //   119: instanceof [B
    //   122: ifeq -> 173
    //   125: goto -> 106
    //   128: aload_1
    //   129: instanceof java/lang/String
    //   132: istore_3
    //   133: goto -> 173
    //   136: aload_1
    //   137: instanceof java/lang/Boolean
    //   140: istore_3
    //   141: goto -> 173
    //   144: aload_1
    //   145: instanceof java/lang/Double
    //   148: istore_3
    //   149: goto -> 173
    //   152: aload_1
    //   153: instanceof java/lang/Float
    //   156: istore_3
    //   157: goto -> 173
    //   160: aload_1
    //   161: instanceof java/lang/Long
    //   164: istore_3
    //   165: goto -> 173
    //   168: aload_1
    //   169: instanceof java/lang/Integer
    //   172: istore_3
    //   173: iload_3
    //   174: ifeq -> 178
    //   177: return
    //   178: new java/lang/IllegalArgumentException
    //   181: dup
    //   182: ldc 'Wrong object type used with protocol message reflection.'
    //   184: invokespecial <init> : (Ljava/lang/String;)V
    //   187: athrow
  }
  
  private static int zzb(zzuh<?> paramzzuh, Object paramObject) {
    zzxs zzxs = paramzzuh.zzvz();
    int i = paramzzuh.zzc();
    if (paramzzuh.zzwb()) {
      boolean bool1 = paramzzuh.zzwc();
      boolean bool = false;
      int j = 0;
      if (bool1) {
        Iterator iterator1 = ((List)paramObject).iterator();
        while (iterator1.hasNext())
          j += zzb(zzxs, iterator1.next()); 
        return zztv.zzbd(i) + j + zztv.zzbl(j);
      } 
      Iterator iterator = ((List)paramObject).iterator();
      for (j = bool; iterator.hasNext(); j += zza(zzxs, i, iterator.next()));
      return j;
    } 
    return zza(zzxs, i, paramObject);
  }
  
  private static int zzb(zzxs paramzzxs, Object paramObject) {
    switch (zzug.zzbun[paramzzxs.ordinal()]) {
      default:
        throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
      case 18:
        return (paramObject instanceof zzur) ? zztv.zzbj(((zzur)paramObject).zzc()) : zztv.zzbj(((Integer)paramObject).intValue());
      case 17:
        return zztv.zzay(((Long)paramObject).longValue());
      case 16:
        return zztv.zzbg(((Integer)paramObject).intValue());
      case 15:
        return zztv.zzba(((Long)paramObject).longValue());
      case 14:
        return zztv.zzbi(((Integer)paramObject).intValue());
      case 13:
        return zztv.zzbf(((Integer)paramObject).intValue());
      case 12:
        return (paramObject instanceof zzte) ? zztv.zzb((zzte)paramObject) : zztv.zzk((byte[])paramObject);
      case 11:
        return (paramObject instanceof zzte) ? zztv.zzb((zzte)paramObject) : zztv.zzgc((String)paramObject);
      case 10:
        return (paramObject instanceof zzuy) ? zztv.zza((zzuy)paramObject) : zztv.zzc((zzvv)paramObject);
      case 9:
        return zztv.zzd((zzvv)paramObject);
      case 8:
        return zztv.zzt(((Boolean)paramObject).booleanValue());
      case 7:
        return zztv.zzbh(((Integer)paramObject).intValue());
      case 6:
        return zztv.zzaz(((Long)paramObject).longValue());
      case 5:
        return zztv.zzbe(((Integer)paramObject).intValue());
      case 4:
        return zztv.zzax(((Long)paramObject).longValue());
      case 3:
        return zztv.zzaw(((Long)paramObject).longValue());
      case 2:
        return zztv.zzb(((Float)paramObject).floatValue());
      case 1:
        break;
    } 
    return zztv.zzc(((Double)paramObject).doubleValue());
  }
  
  private static boolean zzc(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    zzuh zzuh = (zzuh)paramEntry.getKey();
    if (zzuh.zzwa() == zzxx.zzcem) {
      Iterator<zzvv> iterator;
      if (zzuh.zzwb()) {
        iterator = ((List)paramEntry.getValue()).iterator();
        while (iterator.hasNext()) {
          if (!((zzvv)iterator.next()).isInitialized())
            return false; 
        } 
      } else {
        iterator = iterator.getValue();
        if (iterator instanceof zzvv) {
          if (!((zzvv)iterator).isInitialized())
            return false; 
        } else {
          if (iterator instanceof zzuy)
            return true; 
          throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        } 
      } 
    } 
    return true;
  }
  
  private final void zzd(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    zzuh zzuh = (zzuh)paramEntry.getKey();
    Object object = paramEntry.getValue();
    if (!(object instanceof zzuy)) {
      if (zzuh.zzwb()) {
        object2 = zza((FieldDescriptorType)zzuh);
        Object object1 = object2;
        if (object2 == null)
          object1 = new ArrayList(); 
        for (Object object2 : object)
          ((List<Object>)object1).add(zzz(object2)); 
        this.zzbvj.zza((FieldDescriptorType)zzuh, object1);
        return;
      } 
      if (zzuh.zzwa() == zzxx.zzcem) {
        Object object1 = zza((FieldDescriptorType)zzuh);
        if (object1 == null) {
          this.zzbvj.zza((FieldDescriptorType)zzuh, zzz(object));
          return;
        } 
        if (object1 instanceof zzwb) {
          object1 = zzuh.zza((zzwb)object1, (zzwb)object);
        } else {
          object1 = zzuh.zza(((zzvv)object1).zzwh(), (zzvv)object).zzwo();
        } 
        this.zzbvj.zza((FieldDescriptorType)zzuh, object1);
        return;
      } 
      this.zzbvj.zza((FieldDescriptorType)zzuh, zzz(object));
      return;
    } 
    zzuy.zzwz();
    throw null;
  }
  
  private static int zze(Map.Entry<FieldDescriptorType, Object> paramEntry) {
    zzuh<?> zzuh = (zzuh)paramEntry.getKey();
    Object object = paramEntry.getValue();
    return (zzuh.zzwa() == zzxx.zzcem && !zzuh.zzwb() && !zzuh.zzwc()) ? ((object instanceof zzuy) ? zztv.zzb(((zzuh)paramEntry.getKey()).zzc(), (zzuy)object) : zztv.zzd(((zzuh)paramEntry.getKey()).zzc(), (zzvv)object)) : zzb(zzuh, object);
  }
  
  public static <T extends zzuh<T>> zzuf<T> zzvw() {
    return zzbvl;
  }
  
  private static Object zzz(Object paramObject) {
    if (paramObject instanceof zzwb)
      return ((zzwb)paramObject).zzxp(); 
    if (paramObject instanceof byte[]) {
      paramObject = paramObject;
      byte[] arrayOfByte = new byte[paramObject.length];
      System.arraycopy(paramObject, 0, arrayOfByte, 0, paramObject.length);
      return arrayOfByte;
    } 
    return paramObject;
  }
  
  final Iterator<Map.Entry<FieldDescriptorType, Object>> descendingIterator() {
    return this.zzbvk ? new zzvb<FieldDescriptorType>(this.zzbvj.zzye().iterator()) : this.zzbvj.zzye().iterator();
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzuf))
      return false; 
    paramObject = paramObject;
    return this.zzbvj.equals(((zzuf)paramObject).zzbvj);
  }
  
  public final int hashCode() {
    return this.zzbvj.hashCode();
  }
  
  final boolean isEmpty() {
    return this.zzbvj.isEmpty();
  }
  
  public final boolean isImmutable() {
    return this.zzbqa;
  }
  
  public final boolean isInitialized() {
    for (byte b = 0; b < this.zzbvj.zzyc(); b++) {
      if (!zzc(this.zzbvj.zzbx(b)))
        return false; 
    } 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.zzbvj.zzyd().iterator();
    while (iterator.hasNext()) {
      if (!zzc(iterator.next()))
        return false; 
    } 
    return true;
  }
  
  public final Iterator<Map.Entry<FieldDescriptorType, Object>> iterator() {
    return this.zzbvk ? new zzvb<FieldDescriptorType>(this.zzbvj.entrySet().iterator()) : this.zzbvj.entrySet().iterator();
  }
  
  public final void zza(zzuf<FieldDescriptorType> paramzzuf) {
    for (byte b = 0; b < paramzzuf.zzbvj.zzyc(); b++)
      zzd(paramzzuf.zzbvj.zzbx(b)); 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = paramzzuf.zzbvj.zzyd().iterator();
    while (iterator.hasNext())
      zzd(iterator.next()); 
  }
  
  public final void zzsw() {
    if (this.zzbqa)
      return; 
    this.zzbvj.zzsw();
    this.zzbqa = true;
  }
  
  public final int zzvx() {
    byte b = 0;
    int i = 0;
    while (b < this.zzbvj.zzyc()) {
      Map.Entry<FieldDescriptorType, Object> entry = this.zzbvj.zzbx(b);
      i += zzb((zzuh)entry.getKey(), entry.getValue());
      b++;
    } 
    for (Map.Entry<FieldDescriptorType, Object> entry : this.zzbvj.zzyd())
      i += zzb((zzuh)entry.getKey(), entry.getValue()); 
    return i;
  }
  
  public final int zzvy() {
    byte b = 0;
    int i = 0;
    while (b < this.zzbvj.zzyc()) {
      i += zze(this.zzbvj.zzbx(b));
      b++;
    } 
    Iterator<Map.Entry<FieldDescriptorType, Object>> iterator = this.zzbvj.zzyd().iterator();
    while (iterator.hasNext())
      i += zze(iterator.next()); 
    return i;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzuf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */