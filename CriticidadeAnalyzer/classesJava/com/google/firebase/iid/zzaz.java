package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

final class zzaz extends BroadcastReceiver {
  private zzay zzdk;
  
  public zzaz(zzay paramzzay) {
    this.zzdk = paramzzay;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    zzay zzay1 = this.zzdk;
    if (zzay1 == null)
      return; 
    if (!zzay1.zzao())
      return; 
    FirebaseInstanceId.zzl();
    FirebaseInstanceId.zza(this.zzdk, 0L);
    this.zzdk.getContext().unregisterReceiver(this);
    this.zzdk = null;
  }
  
  public final void zzap() {
    FirebaseInstanceId.zzl();
    IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    this.zzdk.getContext().registerReceiver(this, intentFilter);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */