package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zabo implements Runnable {
  private final ConnectionResult zaiz;
  
  private final GoogleApiManager.zac zajg;
  
  zabo(GoogleApiManager.zac paramzac, ConnectionResult paramConnectionResult) {}
  
  public final void run() {
    if (this.zaiz.isSuccess()) {
      GoogleApiManager.zac.zaa(this.zajg, true);
      if (GoogleApiManager.zac.zaa(this.zajg).requiresSignIn()) {
        GoogleApiManager.zac.zab(this.zajg);
        return;
      } 
      try {
        GoogleApiManager.zac.zaa(this.zajg).getRemoteService(null, Collections.emptySet());
        return;
      } catch (SecurityException securityException) {
        ((GoogleApiManager.zaa)GoogleApiManager.zaj(this.zajg.zaim).get(GoogleApiManager.zac.zac(this.zajg))).onConnectionFailed(new ConnectionResult(10));
        return;
      } 
    } 
    ((GoogleApiManager.zaa)GoogleApiManager.zaj(this.zajg.zaim).get(GoogleApiManager.zac.zac(this.zajg))).onConnectionFailed(this.zaiz);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */