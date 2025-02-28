package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzm<TResult> implements zzq<TResult> {
  private final Object mLock = new Object();
  
  private final Executor zzd;
  
  private OnSuccessListener<? super TResult> zzp;
  
  public zzm(Executor paramExecutor, OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzd = paramExecutor;
    this.zzp = paramOnSuccessListener;
  }
  
  public final void onComplete(Task<TResult> paramTask) {
    if (paramTask.isSuccessful())
      synchronized (this.mLock) {
        if (this.zzp == null)
          return; 
        this.zzd.execute(new zzn(this, paramTask));
      }  
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */