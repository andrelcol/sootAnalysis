package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Handler;

public final class zzfd extends zzf {
  private Handler handler;
  
  private long zzatb = zzbx().elapsedRealtime();
  
  private long zzatc = this.zzatb;
  
  private final zzy zzatd = new zzfe(this, this.zzada);
  
  private final zzy zzate = new zzff(this, this.zzada);
  
  zzfd(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final void zzai(long paramLong) {
    zzaf();
    zzlm();
    if (zzgv().zze(zzgk().zzal(), zzai.zzaky))
      (zzgu()).zzant.set(false); 
    zzgt().zzjo().zzg("Activity resumed, time", Long.valueOf(paramLong));
    this.zzatb = paramLong;
    this.zzatc = this.zzatb;
    if (zzgv().zzbi(zzgk().zzal())) {
      zzaj(zzbx().currentTimeMillis());
      return;
    } 
    this.zzatd.cancel();
    this.zzate.cancel();
    if (zzgu().zzaf(zzbx().currentTimeMillis())) {
      (zzgu()).zzanp.set(true);
      (zzgu()).zzanr.set(0L);
    } 
    if ((zzgu()).zzanp.get()) {
      this.zzatd.zzh(Math.max(0L, (zzgu()).zzann.get() - (zzgu()).zzanr.get()));
      return;
    } 
    this.zzate.zzh(Math.max(0L, 3600000L - (zzgu()).zzanr.get()));
  }
  
  private final void zzak(long paramLong) {
    zzaf();
    zzlm();
    if (zzgv().zze(zzgk().zzal(), zzai.zzaky))
      (zzgu()).zzant.set(true); 
    this.zzatd.cancel();
    this.zzate.cancel();
    zzgt().zzjo().zzg("Activity paused, time", Long.valueOf(paramLong));
    if (this.zzatb != 0L)
      (zzgu()).zzanr.set((zzgu()).zzanr.get() + paramLong - this.zzatb); 
  }
  
  private final void zzal(long paramLong) {
    Long long_1;
    zzaf();
    long l = zzbx().elapsedRealtime();
    zzgt().zzjo().zzg("Session started, time", Long.valueOf(l));
    boolean bool = zzgv().zzbg(zzgk().zzal());
    Long long_2 = null;
    if (bool) {
      long_1 = Long.valueOf(paramLong / 1000L);
    } else {
      long_1 = null;
    } 
    if (zzgv().zzbh(zzgk().zzal()))
      long_2 = Long.valueOf(-1L); 
    zzgj().zza("auto", "_sid", long_1, paramLong);
    zzgj().zza("auto", "_sno", long_2, paramLong);
    (zzgu()).zzanp.set(false);
    Bundle bundle = new Bundle();
    if (zzgv().zzbg(zzgk().zzal()))
      bundle.putLong("_sid", long_1.longValue()); 
    zzgj().zza("auto", "_s", paramLong, bundle);
    (zzgu()).zzanq.set(paramLong);
  }
  
  private final void zzlm() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield handler : Landroid/os/Handler;
    //   6: ifnonnull -> 25
    //   9: new com/google/android/gms/internal/measurement/zzdl
    //   12: astore_1
    //   13: aload_1
    //   14: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   17: invokespecial <init> : (Landroid/os/Looper;)V
    //   20: aload_0
    //   21: aload_1
    //   22: putfield handler : Landroid/os/Handler;
    //   25: aload_0
    //   26: monitorexit
    //   27: return
    //   28: astore_1
    //   29: aload_0
    //   30: monitorexit
    //   31: aload_1
    //   32: athrow
    // Exception table:
    //   from	to	target	type
    //   2	25	28	finally
    //   25	27	28	finally
    //   29	31	28	finally
  }
  
  private final void zzlq() {
    zzaf();
    zza(false, false);
    zzgi().zzm(zzbx().elapsedRealtime());
  }
  
  final void zza(long paramLong, boolean paramBoolean) {
    zzaf();
    zzlm();
    this.zzatd.cancel();
    this.zzate.cancel();
    if (zzgu().zzaf(paramLong)) {
      (zzgu()).zzanp.set(true);
      (zzgu()).zzanr.set(0L);
    } 
    if (paramBoolean && zzgv().zzbj(zzgk().zzal()))
      (zzgu()).zzanq.set(paramLong); 
    if ((zzgu()).zzanp.get()) {
      zzal(paramLong);
      return;
    } 
    this.zzate.zzh(Math.max(0L, 3600000L - (zzgu()).zzanr.get()));
  }
  
  public final boolean zza(boolean paramBoolean1, boolean paramBoolean2) {
    zzaf();
    zzcl();
    long l2 = zzbx().elapsedRealtime();
    (zzgu()).zzanq.set(zzbx().currentTimeMillis());
    long l1 = l2 - this.zzatb;
    if (!paramBoolean1 && l1 < 1000L) {
      zzgt().zzjo().zzg("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(l1));
      return false;
    } 
    (zzgu()).zzanr.set(l1);
    zzgt().zzjo().zzg("Recording user engagement, ms", Long.valueOf(l1));
    Bundle bundle = new Bundle();
    bundle.putLong("_et", l1);
    zzdy.zza(zzgm().zzle(), bundle, true);
    if (zzgv().zzbk(zzgk().zzal()))
      if (zzgv().zze(zzgk().zzal(), zzai.zzalc)) {
        if (!paramBoolean2)
          zzlp(); 
      } else if (paramBoolean2) {
        bundle.putLong("_fr", 1L);
      } else {
        zzlp();
      }  
    if (!zzgv().zze(zzgk().zzal(), zzai.zzalc) || !paramBoolean2)
      zzgj().logEvent("auto", "_e", bundle); 
    this.zzatb = l2;
    this.zzate.cancel();
    this.zzate.zzh(Math.max(0L, 3600000L - (zzgu()).zzanr.get()));
    return true;
  }
  
  final void zzaj(long paramLong) {
    zzaf();
    zzlm();
    zza(paramLong, false);
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  protected final void zzlo() {
    zzaf();
    zzal(zzbx().currentTimeMillis());
  }
  
  final long zzlp() {
    long l1 = zzbx().elapsedRealtime();
    long l2 = this.zzatc;
    this.zzatc = l1;
    return l1 - l2;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */