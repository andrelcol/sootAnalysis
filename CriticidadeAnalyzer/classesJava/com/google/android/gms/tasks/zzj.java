package com.google.android.gms.tasks;

final class zzj implements Runnable {
  private final Task zzg;
  
  private final zzi zzm;
  
  zzj(zzi paramzzi, Task paramTask) {}
  
  public final void run() {
    synchronized (zzi.zza(this.zzm)) {
      if (zzi.zzb(this.zzm) != null)
        zzi.zzb(this.zzm).onComplete(this.zzg); 
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */