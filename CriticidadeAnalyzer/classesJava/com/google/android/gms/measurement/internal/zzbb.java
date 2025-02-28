package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;

class zzbb extends BroadcastReceiver {
  private boolean zzabj;
  
  private boolean zzabk;
  
  private final zzfn zzamx;
  
  zzbb(zzfn paramzzfn) {
    Preconditions.checkNotNull(paramzzfn);
    this.zzamx = paramzzfn;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    this.zzamx.zzlx();
    String str = paramIntent.getAction();
    this.zzamx.zzgt().zzjo().zzg("NetworkBroadcastReceiver received action", str);
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(str)) {
      boolean bool = this.zzamx.zzlt().zzfb();
      if (this.zzabk != bool) {
        this.zzabk = bool;
        this.zzamx.zzgs().zzc(new zzbc(this, bool));
      } 
      return;
    } 
    this.zzamx.zzgt().zzjj().zzg("NetworkBroadcastReceiver received unknown action", str);
  }
  
  public final void unregister() {
    this.zzamx.zzlx();
    this.zzamx.zzgs().zzaf();
    this.zzamx.zzgs().zzaf();
    if (!this.zzabj)
      return; 
    this.zzamx.zzgt().zzjo().zzby("Unregistering connectivity change receiver");
    this.zzabj = false;
    this.zzabk = false;
    Context context = this.zzamx.getContext();
    try {
      context.unregisterReceiver(this);
      return;
    } catch (IllegalArgumentException illegalArgumentException) {
      this.zzamx.zzgt().zzjg().zzg("Failed to unregister the network broadcast receiver", illegalArgumentException);
      return;
    } 
  }
  
  public final void zzey() {
    this.zzamx.zzlx();
    this.zzamx.zzgs().zzaf();
    if (this.zzabj)
      return; 
    this.zzamx.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    this.zzabk = this.zzamx.zzlt().zzfb();
    this.zzamx.zzgt().zzjo().zzg("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzabk));
    this.zzabj = true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */