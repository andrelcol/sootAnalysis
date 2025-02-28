package com.google.firebase.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzf extends Binder {
  private final zzb zzu;
  
  zzf(zzb paramzzb) {
    this.zzu = paramzzb;
  }
  
  public final void zza(zzd paramzzd) {
    if (Binder.getCallingUid() == Process.myUid()) {
      Log.isLoggable("EnhancedIntentService", 3);
      if (this.zzu.zzc(paramzzd.intent)) {
        paramzzd.finish();
        return;
      } 
      Log.isLoggable("EnhancedIntentService", 3);
      this.zzu.zzi.execute(new zzg(this, paramzzd));
      return;
    } 
    throw new SecurityException("Binding only allowed within app");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */