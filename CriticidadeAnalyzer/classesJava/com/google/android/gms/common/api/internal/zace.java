package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zad;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import java.util.Set;

public final class zace extends zac implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
  private static Api.AbstractClientBuilder<? extends zad, SignInOptions> zaki = zaa.zaph;
  
  private final Context mContext;
  
  private final Handler mHandler;
  
  private Set<Scope> mScopes;
  
  private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zaau;
  
  private ClientSettings zaet;
  
  private zad zagb;
  
  private zach zakj;
  
  public zace(Context paramContext, Handler paramHandler, ClientSettings paramClientSettings) {
    this(paramContext, paramHandler, paramClientSettings, zaki);
  }
  
  public zace(Context paramContext, Handler paramHandler, ClientSettings paramClientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder) {
    this.mContext = paramContext;
    this.mHandler = paramHandler;
    Preconditions.checkNotNull(paramClientSettings, "ClientSettings must not be null");
    this.zaet = paramClientSettings;
    this.mScopes = paramClientSettings.getRequiredScopes();
    this.zaau = paramAbstractClientBuilder;
  }
  
  private final void zac(zaj paramzaj) {
    String str;
    ConnectionResult connectionResult = paramzaj.getConnectionResult();
    if (connectionResult.isSuccess()) {
      ResolveAccountResponse resolveAccountResponse = paramzaj.zacx();
      ConnectionResult connectionResult1 = resolveAccountResponse.getConnectionResult();
      if (!connectionResult1.isSuccess()) {
        str = String.valueOf(connectionResult1);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 48);
        stringBuilder.append("Sign-in succeeded with resolve account failure: ");
        stringBuilder.append(str);
        stringBuilder.toString();
        new Exception();
        this.zakj.zag(connectionResult1);
        this.zagb.disconnect();
        return;
      } 
      this.zakj.zaa(str.getAccountAccessor(), this.mScopes);
    } else {
      this.zakj.zag((ConnectionResult)str);
    } 
    this.zagb.disconnect();
  }
  
  public final void onConnected(Bundle paramBundle) {
    this.zagb.zaa((zad)this);
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult) {
    this.zakj.zag(paramConnectionResult);
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zagb.disconnect();
  }
  
  public final void zaa(zach paramzach) {
    zad zad1 = this.zagb;
    if (zad1 != null)
      zad1.disconnect(); 
    this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this)));
    Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder = this.zaau;
    Context context = this.mContext;
    Looper looper = this.mHandler.getLooper();
    ClientSettings clientSettings = this.zaet;
    this.zagb = (zad)abstractClientBuilder.buildClient(context, looper, clientSettings, clientSettings.getSignInOptions(), this, this);
    this.zakj = paramzach;
    Set<Scope> set = this.mScopes;
    if (set == null || set.isEmpty()) {
      this.mHandler.post(new zacf(this));
      return;
    } 
    this.zagb.connect();
  }
  
  public final void zab(zaj paramzaj) {
    this.mHandler.post(new zacg(this, paramzaj));
  }
  
  public final zad zabq() {
    return this.zagb;
  }
  
  public final void zabs() {
    zad zad1 = this.zagb;
    if (zad1 != null)
      zad1.disconnect(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */