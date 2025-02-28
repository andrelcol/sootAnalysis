package com.google.android.gms.tasks;

public class TaskCompletionSource<TResult> {
  private final zzu<TResult> zza = new zzu<TResult>();
  
  public Task<TResult> getTask() {
    return this.zza;
  }
  
  public void setException(Exception paramException) {
    this.zza.setException(paramException);
  }
  
  public void setResult(TResult paramTResult) {
    this.zza.setResult(paramTResult);
  }
  
  public boolean trySetException(Exception paramException) {
    return this.zza.trySetException(paramException);
  }
  
  public boolean trySetResult(TResult paramTResult) {
    return this.zza.trySetResult(paramTResult);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/TaskCompletionSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */