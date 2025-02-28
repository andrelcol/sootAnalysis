package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzg<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzd;
  
  private OnCanceledListener zzj;
  
  public zzg(Executor paramExecutor, OnCanceledListener paramOnCanceledListener) {
    this.zzd = paramExecutor;
    this.zzj = paramOnCanceledListener;
  }
  
  public final void onComplete(Task paramTask) {
    if (paramTask.isCanceled())
      synchronized (this.mLock) {
        if (this.zzj == null)
          return; 
        this.zzd.execute(new zzh(this));
      }  
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */