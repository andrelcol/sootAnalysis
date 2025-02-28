package com.google.android.gms.internal.measurement;

import java.io.IOException;

abstract class zzxd<T, B> {
  abstract void zza(B paramB, int paramInt, long paramLong);
  
  abstract void zza(B paramB, int paramInt, zzte paramzzte);
  
  abstract void zza(B paramB, int paramInt, T paramT);
  
  abstract void zza(T paramT, zzxy paramzzxy) throws IOException;
  
  abstract boolean zza(zzwk paramzzwk);
  
  final boolean zza(B paramB, zzwk paramzzwk) throws IOException {
    int j = paramzzwk.getTag();
    int i = j >>> 3;
    j &= 0x7;
    if (j != 0) {
      if (j != 1) {
        if (j != 2) {
          if (j != 3) {
            if (j != 4) {
              if (j == 5) {
                zzc(paramB, i, paramzzwk.zzuo());
                return true;
              } 
              throw zzuv.zzwu();
            } 
            return false;
          } 
          B b = zzyk();
          do {
          
          } while (paramzzwk.zzvh() != Integer.MAX_VALUE && zza(b, paramzzwk));
          if ((0x4 | i << 3) == paramzzwk.getTag()) {
            zza(paramB, i, zzaf(b));
            return true;
          } 
          throw zzuv.zzwt();
        } 
        zza(paramB, i, paramzzwk.zzur());
        return true;
      } 
      zzb(paramB, i, paramzzwk.zzun());
      return true;
    } 
    zza(paramB, i, paramzzwk.zzul());
    return true;
  }
  
  abstract T zzaf(B paramB);
  
  abstract int zzai(T paramT);
  
  abstract T zzal(Object paramObject);
  
  abstract B zzam(Object paramObject);
  
  abstract int zzan(T paramT);
  
  abstract void zzb(B paramB, int paramInt, long paramLong);
  
  abstract void zzc(B paramB, int paramInt1, int paramInt2);
  
  abstract void zzc(T paramT, zzxy paramzzxy) throws IOException;
  
  abstract void zzf(Object paramObject, T paramT);
  
  abstract void zzg(Object paramObject, B paramB);
  
  abstract T zzh(T paramT1, T paramT2);
  
  abstract void zzy(Object paramObject);
  
  abstract B zzyk();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */