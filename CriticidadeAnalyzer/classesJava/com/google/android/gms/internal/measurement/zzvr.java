package com.google.android.gms.internal.measurement;

import java.util.Map;

final class zzvr implements zzvq {
  public final Map<?, ?> zzac(Object paramObject) {
    return (zzvp)paramObject;
  }
  
  public final Map<?, ?> zzad(Object paramObject) {
    return (zzvp)paramObject;
  }
  
  public final boolean zzae(Object paramObject) {
    return !((zzvp)paramObject).isMutable();
  }
  
  public final Object zzaf(Object paramObject) {
    ((zzvp)paramObject).zzsw();
    return paramObject;
  }
  
  public final Object zzag(Object paramObject) {
    return zzvp.zzxg().zzxh();
  }
  
  public final zzvo<?, ?> zzah(Object paramObject) {
    throw new NoSuchMethodError();
  }
  
  public final int zzb(int paramInt, Object paramObject1, Object paramObject2) {
    paramObject1 = paramObject1;
    if (paramObject1.isEmpty())
      return 0; 
    paramObject1 = paramObject1.entrySet().iterator();
    if (!paramObject1.hasNext())
      return 0; 
    paramObject1 = paramObject1.next();
    paramObject1.getKey();
    paramObject1.getValue();
    throw new NoSuchMethodError();
  }
  
  public final Object zzc(Object paramObject1, Object paramObject2) {
    zzvp zzvp = (zzvp)paramObject1;
    paramObject2 = paramObject2;
    paramObject1 = zzvp;
    if (!paramObject2.isEmpty()) {
      paramObject1 = zzvp;
      if (!zzvp.isMutable())
        paramObject1 = zzvp.zzxh(); 
      paramObject1.zza((zzvp)paramObject2);
    } 
    return paramObject1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */