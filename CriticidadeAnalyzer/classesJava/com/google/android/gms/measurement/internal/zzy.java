package com.google.android.gms.measurement.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

abstract class zzy {
  private static volatile Handler handler;
  
  private final zzct zzahn;
  
  private final Runnable zzyo;
  
  private volatile long zzyp;
  
  zzy(zzct paramzzct) {
    Preconditions.checkNotNull(paramzzct);
    this.zzahn = paramzzct;
    this.zzyo = new zzz(this, paramzzct);
  }
  
  private final Handler getHandler() {
    // Byte code:
    //   0: getstatic com/google/android/gms/measurement/internal/zzy.handler : Landroid/os/Handler;
    //   3: ifnull -> 10
    //   6: getstatic com/google/android/gms/measurement/internal/zzy.handler : Landroid/os/Handler;
    //   9: areturn
    //   10: ldc com/google/android/gms/measurement/internal/zzy
    //   12: monitorenter
    //   13: getstatic com/google/android/gms/measurement/internal/zzy.handler : Landroid/os/Handler;
    //   16: ifnonnull -> 43
    //   19: new com/google/android/gms/internal/measurement/zzdl
    //   22: astore_1
    //   23: aload_1
    //   24: aload_0
    //   25: getfield zzahn : Lcom/google/android/gms/measurement/internal/zzct;
    //   28: invokeinterface getContext : ()Landroid/content/Context;
    //   33: invokevirtual getMainLooper : ()Landroid/os/Looper;
    //   36: invokespecial <init> : (Landroid/os/Looper;)V
    //   39: aload_1
    //   40: putstatic com/google/android/gms/measurement/internal/zzy.handler : Landroid/os/Handler;
    //   43: getstatic com/google/android/gms/measurement/internal/zzy.handler : Landroid/os/Handler;
    //   46: astore_1
    //   47: ldc com/google/android/gms/measurement/internal/zzy
    //   49: monitorexit
    //   50: aload_1
    //   51: areturn
    //   52: astore_1
    //   53: ldc com/google/android/gms/measurement/internal/zzy
    //   55: monitorexit
    //   56: aload_1
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   13	43	52	finally
    //   43	50	52	finally
    //   53	56	52	finally
  }
  
  final void cancel() {
    this.zzyp = 0L;
    getHandler().removeCallbacks(this.zzyo);
  }
  
  public abstract void run();
  
  public final boolean zzej() {
    return (this.zzyp != 0L);
  }
  
  public final void zzh(long paramLong) {
    cancel();
    if (paramLong >= 0L) {
      this.zzyp = this.zzahn.zzbx().currentTimeMillis();
      if (!getHandler().postDelayed(this.zzyo, paramLong))
        this.zzahn.zzgt().zzjg().zzg("Failed to schedule delayed post. time", Long.valueOf(paramLong)); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */