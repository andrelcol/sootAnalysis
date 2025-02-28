package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzk<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzd;
  
  private OnFailureListener zzn;
  
  public zzk(Executor paramExecutor, OnFailureListener paramOnFailureListener) {
    this.zzd = paramExecutor;
    this.zzn = paramOnFailureListener;
  }
  
  public final void onComplete(Task<TResult> paramTask) {
    if (!paramTask.isSuccessful() && !paramTask.isCanceled())
      synchronized (this.mLock) {
        if (this.zzn == null)
          return; 
        this.zzd.execute(new zzl(this, paramTask));
      }  
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */