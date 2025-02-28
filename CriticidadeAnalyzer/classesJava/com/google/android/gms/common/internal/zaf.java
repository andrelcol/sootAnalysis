package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;

final class zaf implements BaseGmsClient.BaseConnectionCallbacks {
  private final GoogleApiClient.ConnectionCallbacks zaoj;
  
  zaf(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {}
  
  public final void onConnected(Bundle paramBundle) {
    this.zaoj.onConnected(paramBundle);
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zaoj.onConnectionSuspended(paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */