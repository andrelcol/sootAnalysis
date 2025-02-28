package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzi<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzd;
  
  private OnCompleteListener<TResult> zzl;
  
  public zzi(Executor paramExecutor, OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzd = paramExecutor;
    this.zzl = paramOnCompleteListener;
  }
  
  public final void onComplete(Task<TResult> paramTask) {
    synchronized (this.mLock) {
      if (this.zzl == null)
        return; 
      this.zzd.execute(new zzj(this, paramTask));
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */