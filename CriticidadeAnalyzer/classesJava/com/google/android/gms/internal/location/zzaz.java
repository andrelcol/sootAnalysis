package com.google.android.gms.internal.location;

import android.content.Context;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public final class zzaz extends zzk {
  private final zzas zzde;
  
  public zzaz(Context paramContext, Looper paramLooper, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String paramString, ClientSettings paramClientSettings) {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramString, paramClientSettings);
    this.zzde = new zzas(paramContext, this.zzcb);
  }
  
  public final void disconnect() {
    synchronized (this.zzde) {
      boolean bool = isConnected();
      if (bool)
        try {
          this.zzde.removeAllListeners();
          this.zzde.zzb();
        } catch (Exception exception) {} 
      super.disconnect();
      return;
    } 
  }
  
  public final void zza(LocationRequest paramLocationRequest, ListenerHolder<LocationListener> paramListenerHolder, zzaj paramzzaj) throws RemoteException {
    synchronized (this.zzde) {
      this.zzde.zza(paramLocationRequest, paramListenerHolder, paramzzaj);
      return;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */