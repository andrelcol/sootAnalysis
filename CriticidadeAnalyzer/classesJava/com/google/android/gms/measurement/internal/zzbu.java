package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

final class zzbu<V> extends FutureTask<V> implements Comparable<zzbu> {
  private final String zzapd;
  
  private final zzbr zzape;
  
  private final long zzapf;
  
  final boolean zzapg;
  
  zzbu(zzbr paramzzbr, Runnable paramRunnable, boolean paramBoolean, String paramString) {
    super(paramRunnable, null);
    Preconditions.checkNotNull(paramString);
    this.zzapf = zzbr.zzkh().getAndIncrement();
    this.zzapd = paramString;
    this.zzapg = false;
    if (this.zzapf == Long.MAX_VALUE)
      paramzzbr.zzgt().zzjg().zzby("Tasks index overflow"); 
  }
  
  zzbu(zzbr paramzzbr, Callable<V> paramCallable, boolean paramBoolean, String paramString) {
    super(paramCallable);
    Preconditions.checkNotNull(paramString);
    this.zzapf = zzbr.zzkh().getAndIncrement();
    this.zzapd = paramString;
    this.zzapg = paramBoolean;
    if (this.zzapf == Long.MAX_VALUE)
      paramzzbr.zzgt().zzjg().zzby("Tasks index overflow"); 
  }
  
  protected final void setException(Throwable paramThrowable) {
    this.zzape.zzgt().zzjg().zzg(this.zzapd, paramThrowable);
    super.setException(paramThrowable);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */