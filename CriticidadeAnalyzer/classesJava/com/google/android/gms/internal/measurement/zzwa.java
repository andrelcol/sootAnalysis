package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.Map;

final class zzwa<T> implements zzwl<T> {
  private final zzvv zzcaw;
  
  private final boolean zzcax;
  
  private final zzxd<?, ?> zzcbg;
  
  private final zzuc<?> zzcbh;
  
  private zzwa(zzxd<?, ?> paramzzxd, zzuc<?> paramzzuc, zzvv paramzzvv) {
    this.zzcbg = paramzzxd;
    this.zzcax = paramzzuc.zze(paramzzvv);
    this.zzcbh = paramzzuc;
    this.zzcaw = paramzzvv;
  }
  
  static <T> zzwa<T> zza(zzxd<?, ?> paramzzxd, zzuc<?> paramzzuc, zzvv paramzzvv) {
    return new zzwa<T>(paramzzxd, paramzzuc, paramzzvv);
  }
  
  public final boolean equals(T paramT1, T paramT2) {
    return !this.zzcbg.zzal(paramT1).equals(this.zzcbg.zzal(paramT2)) ? false : (this.zzcax ? this.zzcbh.zzw(paramT1).equals(this.zzcbh.zzw(paramT2)) : true);
  }
  
  public final int hashCode(T paramT) {
    int j = this.zzcbg.zzal(paramT).hashCode();
    int i = j;
    if (this.zzcax)
      i = j * 53 + this.zzcbh.zzw(paramT).hashCode(); 
    return i;
  }
  
  public final T newInstance() {
    return (T)this.zzcaw.zzwi().zzwn();
  }
  
  public final void zza(T paramT, zzwk paramzzwk, zzub paramzzub) throws IOException {
    zzxd<?, ?> zzxd1 = this.zzcbg;
    zzuc<?> zzuc1 = this.zzcbh;
    Object object = zzxd1.zzam(paramT);
    zzuf<?> zzuf = zzuc1.zzx(paramT);
    try {
      while (true) {
        boolean bool;
        int i = paramzzwk.zzvh();
        if (i == Integer.MAX_VALUE)
          return; 
        i = paramzzwk.getTag();
        if (i != 11) {
          if ((i & 0x7) == 2) {
            Object object1 = zzuc1.zza(paramzzub, this.zzcaw, i >>> 3);
            if (object1 == null) {
              bool = zzxd1.zza(object, paramzzwk);
            } else {
              zzuc1.zza(paramzzwk, object1, paramzzub, zzuf);
              throw null;
            } 
          } else {
            bool = paramzzwk.zzvi();
          } 
        } else {
          Object object1 = null;
          zzte zzte = null;
          i = 0;
          while (paramzzwk.zzvh() != Integer.MAX_VALUE) {
            int j = paramzzwk.getTag();
            if (j == 16) {
              i = paramzzwk.zzus();
              object1 = zzuc1.zza(paramzzub, this.zzcaw, i);
              continue;
            } 
            if (j == 26) {
              if (object1 == null) {
                zzte = paramzzwk.zzur();
                continue;
              } 
              zzuc1.zza(paramzzwk, object1, paramzzub, zzuf);
              throw null;
            } 
            if (!paramzzwk.zzvi())
              break; 
          } 
          if (paramzzwk.getTag() == 12) {
            if (zzte != null)
              if (object1 == null) {
                zzxd1.zza(object, i, zzte);
              } else {
                zzuc1.zza(zzte, object1, paramzzub, zzuf);
                throw null;
              }  
            bool = true;
          } else {
            throw zzuv.zzwt();
          } 
        } 
        if (!bool)
          return; 
      } 
    } finally {
      zzxd1.zzg(paramT, object);
    } 
  }
  
  public final void zza(T paramT, zzxy paramzzxy) throws IOException {
    for (Map.Entry entry : this.zzcbh.zzw(paramT)) {
      zzuh zzuh = (zzuh)entry.getKey();
      if (zzuh.zzwa() == zzxx.zzcem && !zzuh.zzwb() && !zzuh.zzwc()) {
        if (entry instanceof zzva) {
          paramzzxy.zza(zzuh.zzc(), ((zzva)entry).zzxa().zztw());
          continue;
        } 
        paramzzxy.zza(zzuh.zzc(), entry.getValue());
        continue;
      } 
      throw new IllegalStateException("Found invalid MessageSet item.");
    } 
    zzxd<?, ?> zzxd1 = this.zzcbg;
    zzxd1.zzc(zzxd1.zzal(paramT), paramzzxy);
  }
  
  public final int zzai(T paramT) {
    zzxd<?, ?> zzxd1 = this.zzcbg;
    int j = zzxd1.zzan(zzxd1.zzal(paramT)) + 0;
    int i = j;
    if (this.zzcax)
      i = j + this.zzcbh.zzw(paramT).zzvy(); 
    return i;
  }
  
  public final boolean zzaj(T paramT) {
    return this.zzcbh.zzw(paramT).isInitialized();
  }
  
  public final void zzd(T paramT1, T paramT2) {
    zzwn.zza(this.zzcbg, paramT1, paramT2);
    if (this.zzcax)
      zzwn.zza(this.zzcbh, paramT1, paramT2); 
  }
  
  public final void zzy(T paramT) {
    this.zzcbg.zzy(paramT);
    this.zzcbh.zzy(paramT);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */