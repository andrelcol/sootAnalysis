package com.google.android.gms.tasks;

final class zzf implements Runnable {
  private final Task zzg;
  
  private final zze zzi;
  
  zzf(zze paramzze, Task paramTask) {}
  
  public final void run() {
    try {
      Task task = (Task)zze.zza(this.zzi).then(this.zzg);
      if (task == null) {
        this.zzi.onFailure(new NullPointerException("Continuation returned null"));
        return;
      } 
      task.addOnSuccessListener(TaskExecutors.zzw, this.zzi);
      task.addOnFailureListener(TaskExecutors.zzw, this.zzi);
      task.addOnCanceledListener(TaskExecutors.zzw, this.zzi);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zze.zzb(this.zzi).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zze.zzb(this.zzi).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zze.zzb(this.zzi).setException(exception);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */