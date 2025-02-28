package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

final class zzu<TResult> extends Task<TResult> {
  private final Object mLock = new Object();
  
  private TResult zzaa;
  
  private Exception zzab;
  
  private final zzr<TResult> zzx = new zzr<TResult>();
  
  private boolean zzy;
  
  private volatile boolean zzz;
  
  private final void zzb() {
    Preconditions.checkState(this.zzy, "Task is not yet complete");
  }
  
  private final void zzc() {
    Preconditions.checkState(this.zzy ^ true, "Task is already complete");
  }
  
  private final void zzd() {
    if (!this.zzz)
      return; 
    throw new CancellationException("Task is already canceled.");
  }
  
  private final void zze() {
    synchronized (this.mLock) {
      if (!this.zzy)
        return; 
      this.zzx.zza(this);
      return;
    } 
  }
  
  public final Task<TResult> addOnCanceledListener(Executor paramExecutor, OnCanceledListener paramOnCanceledListener) {
    this.zzx.zza(new zzg<TResult>(paramExecutor, paramOnCanceledListener));
    zze();
    return this;
  }
  
  public final Task<TResult> addOnCompleteListener(Executor paramExecutor, OnCompleteListener<TResult> paramOnCompleteListener) {
    this.zzx.zza(new zzi<TResult>(paramExecutor, paramOnCompleteListener));
    zze();
    return this;
  }
  
  public final Task<TResult> addOnFailureListener(Executor paramExecutor, OnFailureListener paramOnFailureListener) {
    this.zzx.zza(new zzk<TResult>(paramExecutor, paramOnFailureListener));
    zze();
    return this;
  }
  
  public final Task<TResult> addOnSuccessListener(Executor paramExecutor, OnSuccessListener<? super TResult> paramOnSuccessListener) {
    this.zzx.zza(new zzm<TResult>(paramExecutor, paramOnSuccessListener));
    zze();
    return this;
  }
  
  public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor paramExecutor, Continuation<TResult, TContinuationResult> paramContinuation) {
    zzu<TContinuationResult> zzu1 = new zzu();
    this.zzx.zza(new zzc<TResult, TContinuationResult>(paramExecutor, paramContinuation, zzu1));
    zze();
    return zzu1;
  }
  
  public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor paramExecutor, Continuation<TResult, Task<TContinuationResult>> paramContinuation) {
    zzu<TContinuationResult> zzu1 = new zzu();
    this.zzx.zza(new zze<TResult, TContinuationResult>(paramExecutor, paramContinuation, zzu1));
    zze();
    return zzu1;
  }
  
  public final Exception getException() {
    synchronized (this.mLock) {
      return this.zzab;
    } 
  }
  
  public final TResult getResult() {
    synchronized (this.mLock) {
      zzb();
      zzd();
      if (this.zzab == null)
        return this.zzaa; 
      RuntimeExecutionException runtimeExecutionException = new RuntimeExecutionException();
      this(this.zzab);
      throw runtimeExecutionException;
    } 
  }
  
  public final <X extends Throwable> TResult getResult(Class<X> paramClass) throws X {
    synchronized (this.mLock) {
      RuntimeExecutionException runtimeExecutionException;
      zzb();
      zzd();
      if (!paramClass.isInstance(this.zzab)) {
        if (this.zzab == null)
          return this.zzaa; 
        runtimeExecutionException = new RuntimeExecutionException();
        this(this.zzab);
        throw (X)runtimeExecutionException;
      } 
      throw (X)runtimeExecutionException.cast(this.zzab);
    } 
  }
  
  public final boolean isCanceled() {
    return this.zzz;
  }
  
  public final boolean isComplete() {
    synchronized (this.mLock) {
      return this.zzy;
    } 
  }
  
  public final boolean isSuccessful() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzy && !this.zzz && this.zzab == null) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public final void setException(Exception paramException) {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      zzc();
      this.zzy = true;
      this.zzab = paramException;
      this.zzx.zza(this);
      return;
    } 
  }
  
  public final void setResult(TResult paramTResult) {
    synchronized (this.mLock) {
      zzc();
      this.zzy = true;
      this.zzaa = paramTResult;
      this.zzx.zza(this);
      return;
    } 
  }
  
  public final boolean trySetException(Exception paramException) {
    Preconditions.checkNotNull(paramException, "Exception must not be null");
    synchronized (this.mLock) {
      if (this.zzy)
        return false; 
      this.zzy = true;
      this.zzab = paramException;
      this.zzx.zza(this);
      return true;
    } 
  }
  
  public final boolean trySetResult(TResult paramTResult) {
    synchronized (this.mLock) {
      if (this.zzy)
        return false; 
      this.zzy = true;
      this.zzaa = paramTResult;
      this.zzx.zza(this);
      return true;
    } 
  }
  
  public final boolean zza() {
    synchronized (this.mLock) {
      if (this.zzy)
        return false; 
      this.zzy = true;
      this.zzz = true;
      this.zzx.zza(this);
      return true;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */