package com.google.android.gms.tasks;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class Tasks {
  public static <TResult> TResult await(Task<TResult> paramTask) throws ExecutionException, InterruptedException {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    if (paramTask.isComplete())
      return zzb(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    zza.await();
    return zzb(paramTask);
  }
  
  public static <TResult> TResult await(Task<TResult> paramTask, long paramLong, TimeUnit paramTimeUnit) throws ExecutionException, InterruptedException, TimeoutException {
    Preconditions.checkNotMainThread();
    Preconditions.checkNotNull(paramTask, "Task must not be null");
    Preconditions.checkNotNull(paramTimeUnit, "TimeUnit must not be null");
    if (paramTask.isComplete())
      return zzb(paramTask); 
    zza zza = new zza(null);
    zza(paramTask, zza);
    if (zza.await(paramLong, paramTimeUnit))
      return zzb(paramTask); 
    throw new TimeoutException("Timed out waiting for Task");
  }
  
  public static <TResult> Task<TResult> forResult(TResult paramTResult) {
    zzu<TResult> zzu = new zzu();
    zzu.setResult(paramTResult);
    return zzu;
  }
  
  private static void zza(Task<?> paramTask, zzb paramzzb) {
    paramTask.addOnSuccessListener(TaskExecutors.zzw, paramzzb);
    paramTask.addOnFailureListener(TaskExecutors.zzw, paramzzb);
    paramTask.addOnCanceledListener(TaskExecutors.zzw, paramzzb);
  }
  
  private static <TResult> TResult zzb(Task<TResult> paramTask) throws ExecutionException {
    if (paramTask.isSuccessful())
      return paramTask.getResult(); 
    if (paramTask.isCanceled())
      throw new CancellationException("Task is already canceled"); 
    throw new ExecutionException(paramTask.getException());
  }
  
  private static final class zza implements zzb {
    private final CountDownLatch zzaf = new CountDownLatch(1);
    
    private zza() {}
    
    public final void await() throws InterruptedException {
      this.zzaf.await();
    }
    
    public final boolean await(long param1Long, TimeUnit param1TimeUnit) throws InterruptedException {
      return this.zzaf.await(param1Long, param1TimeUnit);
    }
    
    public final void onCanceled() {
      this.zzaf.countDown();
    }
    
    public final void onFailure(Exception param1Exception) {
      this.zzaf.countDown();
    }
    
    public final void onSuccess(Object param1Object) {
      this.zzaf.countDown();
    }
  }
  
  static interface zzb extends OnCanceledListener, OnFailureListener, OnSuccessListener<Object> {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */