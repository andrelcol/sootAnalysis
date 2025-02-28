package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.util.List;

final class zztx implements zzxy {
  private final zztv zzbty;
  
  private zztx(zztv paramzztv) {
    zzuq.zza(paramzztv, "output");
    this.zzbty = paramzztv;
    this.zzbty.zzbup = this;
  }
  
  public static zztx zza(zztv paramzztv) {
    zztx zztx1 = paramzztv.zzbup;
    return (zztx1 != null) ? zztx1 : new zztx(paramzztv);
  }
  
  public final void zza(int paramInt, double paramDouble) throws IOException {
    this.zzbty.zza(paramInt, paramDouble);
  }
  
  public final void zza(int paramInt, float paramFloat) throws IOException {
    this.zzbty.zza(paramInt, paramFloat);
  }
  
  public final void zza(int paramInt, long paramLong) throws IOException {
    this.zzbty.zza(paramInt, paramLong);
  }
  
  public final void zza(int paramInt, zzte paramzzte) throws IOException {
    this.zzbty.zza(paramInt, paramzzte);
  }
  
  public final void zza(int paramInt, Object paramObject) throws IOException {
    if (paramObject instanceof zzte) {
      this.zzbty.zzb(paramInt, (zzte)paramObject);
      return;
    } 
    this.zzbty.zzb(paramInt, (zzvv)paramObject);
  }
  
  public final void zza(int paramInt, Object paramObject, zzwl paramzzwl) throws IOException {
    this.zzbty.zza(paramInt, (zzvv)paramObject, paramzzwl);
  }
  
  public final void zza(int paramInt, List<String> paramList) throws IOException {
    boolean bool = paramList instanceof zzve;
    byte b1 = 0;
    byte b2 = 0;
    if (bool) {
      zzve zzve = (zzve)paramList;
      for (b1 = b2; b1 < paramList.size(); b1++) {
        Object object = zzve.zzbp(b1);
        if (object instanceof String) {
          this.zzbty.zzb(paramInt, (String)object);
        } else {
          this.zzbty.zza(paramInt, (zzte)object);
        } 
      } 
      return;
    } 
    while (b1 < paramList.size()) {
      this.zzbty.zzb(paramInt, paramList.get(b1));
      b1++;
    } 
  }
  
  public final void zza(int paramInt, List<?> paramList, zzwl paramzzwl) throws IOException {
    for (byte b = 0; b < paramList.size(); b++)
      zza(paramInt, paramList.get(b), paramzzwl); 
  }
  
  public final void zza(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzbe(((Integer)paramList.get(paramInt)).intValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzaz(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zzd(paramInt, ((Integer)paramList.get(i)).intValue());
      i++;
    } 
  }
  
  public final void zzb(int paramInt, long paramLong) throws IOException {
    this.zzbty.zzb(paramInt, paramLong);
  }
  
  public final void zzb(int paramInt, Object paramObject, zzwl<Object> paramzzwl) throws IOException {
    zztv zztv1 = this.zzbty;
    paramObject = paramObject;
    zztv1.zzc(paramInt, 3);
    paramzzwl.zza(paramObject, zztv1.zzbup);
    zztv1.zzc(paramInt, 4);
  }
  
  public final void zzb(int paramInt, String paramString) throws IOException {
    this.zzbty.zzb(paramInt, paramString);
  }
  
  public final void zzb(int paramInt, List<zzte> paramList) throws IOException {
    for (byte b = 0; b < paramList.size(); b++)
      this.zzbty.zza(paramInt, paramList.get(b)); 
  }
  
  public final void zzb(int paramInt, List<?> paramList, zzwl paramzzwl) throws IOException {
    for (byte b = 0; b < paramList.size(); b++)
      zzb(paramInt, paramList.get(b), paramzzwl); 
  }
  
  public final void zzb(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzbh(((Integer)paramList.get(paramInt)).intValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzbc(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zzg(paramInt, ((Integer)paramList.get(i)).intValue());
      i++;
    } 
  }
  
  public final void zzb(int paramInt, boolean paramBoolean) throws IOException {
    this.zzbty.zzb(paramInt, paramBoolean);
  }
  
  public final void zzbm(int paramInt) throws IOException {
    this.zzbty.zzc(paramInt, 3);
  }
  
  public final void zzbn(int paramInt) throws IOException {
    this.zzbty.zzc(paramInt, 4);
  }
  
  public final void zzc(int paramInt, long paramLong) throws IOException {
    this.zzbty.zzc(paramInt, paramLong);
  }
  
  public final void zzc(int paramInt, List<Long> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzaw(((Long)paramList.get(paramInt)).longValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzat(((Long)paramList.get(paramInt)).longValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zza(paramInt, ((Long)paramList.get(i)).longValue());
      i++;
    } 
  }
  
  public final void zzd(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zzd(paramInt1, paramInt2);
  }
  
  public final void zzd(int paramInt, List<Long> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzax(((Long)paramList.get(b)).longValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzat(((Long)paramList.get(paramInt)).longValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zza(paramInt, ((Long)paramList.get(b)).longValue());
      b++;
    } 
  }
  
  public final void zze(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zze(paramInt1, paramInt2);
  }
  
  public final void zze(int paramInt, List<Long> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzaz(((Long)paramList.get(paramInt)).longValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzav(((Long)paramList.get(paramInt)).longValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zzc(paramInt, ((Long)paramList.get(i)).longValue());
      i++;
    } 
  }
  
  public final void zzf(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zzf(paramInt1, paramInt2);
  }
  
  public final void zzf(int paramInt, List<Float> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzb(((Float)paramList.get(b)).floatValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zza(((Float)paramList.get(paramInt)).floatValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zza(paramInt, ((Float)paramList.get(b)).floatValue());
      b++;
    } 
  }
  
  public final void zzg(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zzg(paramInt1, paramInt2);
  }
  
  public final void zzg(int paramInt, List<Double> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzc(((Double)paramList.get(b)).doubleValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzb(((Double)paramList.get(paramInt)).doubleValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zza(paramInt, ((Double)paramList.get(b)).doubleValue());
      b++;
    } 
  }
  
  public final void zzh(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzbj(((Integer)paramList.get(b)).intValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzaz(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zzd(paramInt, ((Integer)paramList.get(b)).intValue());
      b++;
    } 
  }
  
  public final void zzi(int paramInt, long paramLong) throws IOException {
    this.zzbty.zza(paramInt, paramLong);
  }
  
  public final void zzi(int paramInt, List<Boolean> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzt(((Boolean)paramList.get(b)).booleanValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzs(((Boolean)paramList.get(paramInt)).booleanValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zzb(paramInt, ((Boolean)paramList.get(b)).booleanValue());
      b++;
    } 
  }
  
  public final void zzj(int paramInt, long paramLong) throws IOException {
    this.zzbty.zzc(paramInt, paramLong);
  }
  
  public final void zzj(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzbf(((Integer)paramList.get(paramInt)).intValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzba(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zze(paramInt, ((Integer)paramList.get(i)).intValue());
      i++;
    } 
  }
  
  public final void zzk(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzbi(((Integer)paramList.get(b)).intValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzbc(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zzg(paramInt, ((Integer)paramList.get(b)).intValue());
      b++;
    } 
  }
  
  public final void zzl(int paramInt, List<Long> paramList, boolean paramBoolean) throws IOException {
    int i = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      paramInt = 0;
      i = 0;
      while (paramInt < paramList.size()) {
        i += zztv.zzba(((Long)paramList.get(paramInt)).longValue());
        paramInt++;
      } 
      this.zzbty.zzba(i);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzav(((Long)paramList.get(paramInt)).longValue()); 
      return;
    } 
    while (i < paramList.size()) {
      this.zzbty.zzc(paramInt, ((Long)paramList.get(i)).longValue());
      i++;
    } 
  }
  
  public final void zzm(int paramInt, List<Integer> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzbg(((Integer)paramList.get(b)).intValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzbb(((Integer)paramList.get(paramInt)).intValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zzf(paramInt, ((Integer)paramList.get(b)).intValue());
      b++;
    } 
  }
  
  public final void zzn(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zzg(paramInt1, paramInt2);
  }
  
  public final void zzn(int paramInt, List<Long> paramList, boolean paramBoolean) throws IOException {
    byte b = 0;
    boolean bool = false;
    if (paramBoolean) {
      this.zzbty.zzc(paramInt, 2);
      b = 0;
      paramInt = 0;
      while (b < paramList.size()) {
        paramInt += zztv.zzay(((Long)paramList.get(b)).longValue());
        b++;
      } 
      this.zzbty.zzba(paramInt);
      for (paramInt = bool; paramInt < paramList.size(); paramInt++)
        this.zzbty.zzau(((Long)paramList.get(paramInt)).longValue()); 
      return;
    } 
    while (b < paramList.size()) {
      this.zzbty.zzb(paramInt, ((Long)paramList.get(b)).longValue());
      b++;
    } 
  }
  
  public final void zzo(int paramInt1, int paramInt2) throws IOException {
    this.zzbty.zzd(paramInt1, paramInt2);
  }
  
  public final int zzvm() {
    return zzuo.zze.zzbyx;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */