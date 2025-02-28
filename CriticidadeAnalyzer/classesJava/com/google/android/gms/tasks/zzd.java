package com.google.android.gms.tasks;

final class zzd implements Runnable {
  private final Task zzg;
  
  private final zzc zzh;
  
  zzd(zzc paramzzc, Task paramTask) {}
  
  public final void run() {
    if (this.zzg.isCanceled()) {
      zzc.zza(this.zzh).zza();
      return;
    } 
    try {
      Object object = zzc.zzb(this.zzh).then(this.zzg);
      zzc.zza(this.zzh).setResult(object);
      return;
    } catch (RuntimeExecutionException runtimeExecutionException) {
      if (runtimeExecutionException.getCause() instanceof Exception) {
        zzc.zza(this.zzh).setException((Exception)runtimeExecutionException.getCause());
        return;
      } 
      zzc.zza(this.zzh).setException(runtimeExecutionException);
      return;
    } catch (Exception exception) {
      zzc.zza(this.zzh).setException(exception);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */