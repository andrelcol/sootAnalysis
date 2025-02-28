package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zze<TResult, TContinuationResult> implements OnCanceledListener, OnFailureListener, OnSuccessListener<TContinuationResult>, zzq<TResult> {
  private final Executor zzd;
  
  private final Continuation<TResult, Task<TContinuationResult>> zze;
  
  private final zzu<TContinuationResult> zzf;
  
  public zze(Executor paramExecutor, Continuation<TResult, Task<TContinuationResult>> paramContinuation, zzu<TContinuationResult> paramzzu) {
    this.zzd = paramExecutor;
    this.zze = paramContinuation;
    this.zzf = paramzzu;
  }
  
  public final void onCanceled() {
    this.zzf.zza();
  }
  
  public final void onComplete(Task<TResult> paramTask) {
    this.zzd.execute(new zzf(this, paramTask));
  }
  
  public final void onFailure(Exception paramException) {
    this.zzf.setException(paramException);
  }
  
  public final void onSuccess(TContinuationResult paramTContinuationResult) {
    this.zzf.setResult(paramTContinuationResult);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */