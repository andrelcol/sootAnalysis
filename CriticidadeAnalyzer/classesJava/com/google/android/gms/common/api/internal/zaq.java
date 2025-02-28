package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Preconditions;

public final class zaq implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  public final Api<?> mApi;
  
  private final boolean zaec;
  
  private zar zaed;
  
  public zaq(Api<?> paramApi, boolean paramBoolean) {
    this.mApi = paramApi;
    this.zaec = paramBoolean;
  }
  
  private final void zav() {
    Preconditions.checkNotNull(this.zaed, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
  }
  
  public final void onConnected(Bundle paramBundle) {
    zav();
    this.zaed.onConnected(paramBundle);
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult) {
    zav();
    this.zaed.zaa(paramConnectionResult, this.mApi, this.zaec);
  }
  
  public final void onConnectionSuspended(int paramInt) {
    zav();
    this.zaed.onConnectionSuspended(paramInt);
  }
  
  public final void zaa(zar paramzar) {
    this.zaed = paramzar;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */