package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import java.util.Iterator;
import java.util.Set;

public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Api.Client, GmsClientEventManager.GmsClientEventState {
  private final Set<Scope> mScopes;
  
  private final Account zax;
  
  protected GmsClient(Context paramContext, Looper paramLooper, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, gmsClientSupervisor, googleApiAvailability, paramInt, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  protected GmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailability paramGoogleApiAvailability, int paramInt, ClientSettings paramClientSettings, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, paramGmsClientSupervisor, (GoogleApiAvailabilityLight)paramGoogleApiAvailability, paramInt, zaa(paramConnectionCallbacks), zaa(paramOnConnectionFailedListener), paramClientSettings.getRealClientClassName());
    this.zax = paramClientSettings.getAccount();
    Set<Scope> set = paramClientSettings.getAllRequestedScopes();
    zaa(set);
    this.mScopes = set;
  }
  
  private static BaseGmsClient.BaseConnectionCallbacks zaa(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    return (paramConnectionCallbacks == null) ? null : new zaf(paramConnectionCallbacks);
  }
  
  private static BaseGmsClient.BaseOnConnectionFailedListener zaa(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    return (paramOnConnectionFailedListener == null) ? null : new zag(paramOnConnectionFailedListener);
  }
  
  private final Set<Scope> zaa(Set<Scope> paramSet) {
    validateScopes(paramSet);
    Iterator<Scope> iterator = paramSet.iterator();
    while (iterator.hasNext()) {
      if (paramSet.contains(iterator.next()))
        continue; 
      throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
    } 
    return paramSet;
  }
  
  public final Account getAccount() {
    return this.zax;
  }
  
  public int getMinApkVersion() {
    return super.getMinApkVersion();
  }
  
  protected final Set<Scope> getScopes() {
    return this.mScopes;
  }
  
  protected Set<Scope> validateScopes(Set<Scope> paramSet) {
    return paramSet;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/GmsClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */