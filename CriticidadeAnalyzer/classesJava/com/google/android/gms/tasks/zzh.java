package com.google.android.gms.tasks;

final class zzh implements Runnable {
  private final zzg zzk;
  
  zzh(zzg paramzzg) {}
  
  public final void run() {
    synchronized (zzg.zza(this.zzk)) {
      if (zzg.zzb(this.zzk) != null)
        zzg.zzb(this.zzk).onCanceled(); 
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/tasks/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */