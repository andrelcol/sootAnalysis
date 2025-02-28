package com.google.android.gms.signin.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;

public class SignInClientImpl extends GmsClient<zaf> implements zad {
  private final ClientSettings zaet;
  
  private Integer zaoe;
  
  private final boolean zasb = true;
  
  private final Bundle zasc;
  
  private SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    super(paramContext, paramLooper, 44, paramClientSettings, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zaet = paramClientSettings;
    this.zasc = paramBundle;
    this.zaoe = paramClientSettings.getClientSessionId();
  }
  
  public SignInClientImpl(Context paramContext, Looper paramLooper, boolean paramBoolean, ClientSettings paramClientSettings, SignInOptions paramSignInOptions, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    this(paramContext, paramLooper, true, paramClientSettings, createBundleFromClientSettings(paramClientSettings), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public static Bundle createBundleFromClientSettings(ClientSettings paramClientSettings) {
    SignInOptions signInOptions = paramClientSettings.getSignInOptions();
    Integer integer = paramClientSettings.getClientSessionId();
    Bundle bundle = new Bundle();
    bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)paramClientSettings.getAccount());
    if (integer != null)
      bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", integer.intValue()); 
    if (signInOptions != null) {
      bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.isOfflineAccessRequested());
      bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.isIdTokenRequested());
      bundle.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.getServerClientId());
      bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.isForceCodeForRefreshToken());
      bundle.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.getHostedDomain());
      bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.waitForAccessTokenRefresh());
      if (signInOptions.getAuthApiSignInModuleVersion() != null)
        bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", signInOptions.getAuthApiSignInModuleVersion().longValue()); 
      if (signInOptions.getRealClientLibraryVersion() != null)
        bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", signInOptions.getRealClientLibraryVersion().longValue()); 
    } 
    return bundle;
  }
  
  public final void connect() {
    connect((BaseGmsClient.ConnectionProgressReportCallbacks)new BaseGmsClient.LegacyClientCallbackAdapter((BaseGmsClient)this));
  }
  
  protected Bundle getGetServiceRequestExtraArgs() {
    String str = this.zaet.getRealClientPackageName();
    if (!getContext().getPackageName().equals(str))
      this.zasc.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zaet.getRealClientPackageName()); 
    return this.zasc;
  }
  
  public int getMinApkVersion() {
    return 12451000;
  }
  
  protected String getServiceDescriptor() {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  protected String getStartServiceAction() {
    return "com.google.android.gms.signin.service.START";
  }
  
  public boolean requiresSignIn() {
    return this.zasb;
  }
  
  public final void zaa(IAccountAccessor paramIAccountAccessor, boolean paramBoolean) {
    try {
      ((zaf)getService()).zaa(paramIAccountAccessor, this.zaoe.intValue(), paramBoolean);
    } catch (RemoteException remoteException) {}
  }
  
  public final void zaa(zad paramzad) {
    Preconditions.checkNotNull(paramzad, "Expecting a valid ISignInCallbacks");
    try {
      Account account = this.zaet.getAccountOrDefault();
      GoogleSignInAccount googleSignInAccount = null;
      if ("<<default account>>".equals(account.name))
        googleSignInAccount = Storage.getInstance(getContext()).getSavedDefaultGoogleSignInAccount(); 
      ResolveAccountRequest resolveAccountRequest = new ResolveAccountRequest();
      this(account, this.zaoe.intValue(), googleSignInAccount);
      zaf zaf = (zaf)getService();
      zah zah = new zah();
      this(resolveAccountRequest);
      zaf.zaa(zah, paramzad);
      return;
    } catch (RemoteException remoteException) {
      try {
        zaj zaj = new zaj();
        this(8);
        paramzad.zab(zaj);
      } catch (RemoteException remoteException1) {}
      return;
    } 
  }
  
  public final void zacw() {
    try {
      ((zaf)getService()).zam(this.zaoe.intValue());
    } catch (RemoteException remoteException) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/signin/internal/SignInClientImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */