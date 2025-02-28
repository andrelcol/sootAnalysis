package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;

final class zzbt implements Thread.UncaughtExceptionHandler {
  private final String zzapd;
  
  private final zzbr zzape;
  
  public zzbt(zzbr paramzzbr, String paramString) {
    Preconditions.checkNotNull(paramString);
    this.zzapd = paramString;
  }
  
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzape : Lcom/google/android/gms/measurement/internal/zzbr;
    //   6: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   9: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   12: aload_0
    //   13: getfield zzapd : Ljava/lang/String;
    //   16: aload_2
    //   17: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Exception table:
    //   from	to	target	type
    //   2	20	23	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */