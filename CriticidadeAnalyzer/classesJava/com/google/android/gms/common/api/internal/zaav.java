package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import java.util.Collections;
import java.util.Iterator;

public final class zaav implements zabd {
  private final zabe zaft;
  
  public zaav(zabe paramzabe) {
    this.zaft = paramzabe;
  }
  
  public final void begin() {
    Iterator<Api.Client> iterator = this.zaft.zagz.values().iterator();
    while (iterator.hasNext())
      ((Api.Client)iterator.next()).disconnect(); 
    this.zaft.zaee.zaha = Collections.emptySet();
  }
  
  public final void connect() {
    this.zaft.zaaz();
  }
  
  public final boolean disconnect() {
    return true;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final void onConnected(Bundle paramBundle) {}
  
  public final void onConnectionSuspended(int paramInt) {}
  
  public final void zaa(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */