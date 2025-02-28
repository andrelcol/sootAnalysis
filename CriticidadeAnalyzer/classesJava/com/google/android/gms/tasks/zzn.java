package com.google.android.gms.tasks;

final class zzn implements Runnable {
  private final Task zzg;
  
  private final zzm zzq;
  
  zzn(zzm paramzzm, Task paramTask) {}
  
  public final void run() {
    synchronized (zzm.zza(this.zzq)) {
      if (zzm.zzb(this.zzq) != null)
        zzm.zzb(this.zzq).onSuccess(this.zzg.getResult()); 
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */