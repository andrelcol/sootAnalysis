package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
  private final zzd zzv;
  
  private final zzf zzw;
  
  zzg(zzf paramzzf, zzd paramzzd) {}
  
  public final void run() {
    Log.isLoggable("EnhancedIntentService", 3);
    zzf.zza(this.zzw).zzd(this.zzv.intent);
    this.zzv.finish();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */