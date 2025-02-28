package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

final class zag implements BaseGmsClient.BaseOnConnectionFailedListener {
  private final GoogleApiClient.OnConnectionFailedListener zaok;
  
  zag(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {}
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult) {
    this.zaok.onConnectionFailed(paramConnectionResult);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */