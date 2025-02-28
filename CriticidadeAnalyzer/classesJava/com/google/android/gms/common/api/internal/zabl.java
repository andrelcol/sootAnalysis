package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zabl implements Runnable {
  private final GoogleApiManager.zaa zaiy;
  
  private final ConnectionResult zaiz;
  
  zabl(GoogleApiManager.zaa paramzaa, ConnectionResult paramConnectionResult) {}
  
  public final void run() {
    this.zaiy.onConnectionFailed(this.zaiz);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */