package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzbm;
import com.google.android.gms.measurement.internal.zzbp;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzbp {
  private zzbm zzadb;
  
  public final BroadcastReceiver.PendingResult doGoAsync() {
    return goAsync();
  }
  
  public final void doStartService(Context paramContext, Intent paramIntent) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    if (this.zzadb == null)
      this.zzadb = new zzbm(this); 
    this.zzadb.onReceive(paramContext, paramIntent);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/AppMeasurementInstallReferrerReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */