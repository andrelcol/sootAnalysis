package com.google.android.gms.common.util.concurrent;

import android.os.Process;

final class zza implements Runnable {
  private final int priority;
  
  private final Runnable zzhu;
  
  public zza(Runnable paramRunnable, int paramInt) {
    this.zzhu = paramRunnable;
    this.priority = paramInt;
  }
  
  public final void run() {
    Process.setThreadPriority(this.priority);
    this.zzhu.run();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/concurrent/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */