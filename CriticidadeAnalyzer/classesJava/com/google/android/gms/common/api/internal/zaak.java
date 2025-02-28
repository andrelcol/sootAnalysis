package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.internal.zaj;
import com.google.android.gms.signin.zad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public final class zaak implements zabd {
  private final Context mContext;
  
  private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
  
  private final Lock zaeo;
  
  private final ClientSettings zaet;
  
  private final Map<Api<?>, Boolean> zaew;
  
  private final GoogleApiAvailabilityLight zaey;
  
  private ConnectionResult zafh;
  
  private final zabe zaft;
  
  private int zafw;
  
  private int zafx = 0;
  
  private int zafy;
  
  private final Bundle zafz = new Bundle();
  
  private final Set<Api.AnyClientKey> zaga = new HashSet<Api.AnyClientKey>();
  
  private zad zagb;
  
  private boolean zagc;
  
  private boolean zagd;
  
  private boolean zage;
  
  private IAccountAccessor zagf;
  
  private boolean zagg;
  
  private boolean zagh;
  
  private ArrayList<Future<?>> zagi = new ArrayList<Future<?>>();
  
  public zaak(zabe paramzabe, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, Lock paramLock, Context paramContext) {
    this.zaft = paramzabe;
    this.zaet = paramClientSettings;
    this.zaew = paramMap;
    this.zaey = paramGoogleApiAvailabilityLight;
    this.zace = paramAbstractClientBuilder;
    this.zaeo = paramLock;
    this.mContext = paramContext;
  }
  
  private final void zaa(zaj paramzaj) {
    String str;
    if (!zac(0))
      return; 
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
        zae(connectionResult1);
        return;
      } 
      this.zage = true;
      this.zagf = str.getAccountAccessor();
      this.zagg = str.getSaveDefaultAccount();
      this.zagh = str.isFromCrossClientAuth();
      zaap();
      return;
    } 
    if (zad((ConnectionResult)str)) {
      zaar();
      zaap();
      return;
    } 
    zae((ConnectionResult)str);
  }
  
  private final boolean zaao() {
    int i = --this.zafy;
    if (i > 0)
      return false; 
    if (i < 0) {
      this.zaft.zaee.zaay();
      new Exception();
      zae(new ConnectionResult(8, null));
      return false;
    } 
    ConnectionResult connectionResult = this.zafh;
    if (connectionResult != null) {
      this.zaft.zahs = this.zafw;
      zae(connectionResult);
      return false;
    } 
    return true;
  }
  
  private final void zaap() {
    if (this.zafy != 0)
      return; 
    if (!this.zagd || this.zage) {
      ArrayList<Api.Client> arrayList = new ArrayList();
      this.zafx = 1;
      this.zafy = this.zaft.zagz.size();
      for (Api.AnyClientKey<?> anyClientKey : this.zaft.zagz.keySet()) {
        if (this.zaft.zahp.containsKey(anyClientKey)) {
          if (zaao())
            zaaq(); 
          continue;
        } 
        arrayList.add(this.zaft.zagz.get(anyClientKey));
      } 
      if (!arrayList.isEmpty())
        this.zagi.add(zabh.zabb().submit(new zaaq(this, arrayList))); 
    } 
  }
  
  private final void zaaq() {
    Bundle bundle;
    this.zaft.zaba();
    zabh.zabb().execute(new zaal(this));
    zad zad1 = this.zagb;
    if (zad1 != null) {
      if (this.zagg)
        zad1.zaa(this.zagf, this.zagh); 
      zab(false);
    } 
    for (Api.AnyClientKey<?> anyClientKey : this.zaft.zahp.keySet())
      ((Api.Client)this.zaft.zagz.get(anyClientKey)).disconnect(); 
    if (this.zafz.isEmpty()) {
      zad1 = null;
    } else {
      bundle = this.zafz;
    } 
    this.zaft.zaht.zab(bundle);
  }
  
  private final void zaar() {
    this.zagd = false;
    this.zaft.zaee.zaha = Collections.emptySet();
    for (Api.AnyClientKey<?> anyClientKey : this.zaga) {
      if (!this.zaft.zahp.containsKey(anyClientKey))
        this.zaft.zahp.put(anyClientKey, new ConnectionResult(17, null)); 
    } 
  }
  
  private final void zaas() {
    ArrayList<Future<?>> arrayList = this.zagi;
    int i = arrayList.size();
    byte b = 0;
    while (b < i) {
      Future future = (Future)arrayList.get(b);
      b++;
      ((Future)future).cancel(true);
    } 
    this.zagi.clear();
  }
  
  private final Set<Scope> zaat() {
    ClientSettings clientSettings = this.zaet;
    if (clientSettings == null)
      return Collections.emptySet(); 
    HashSet<Scope> hashSet = new HashSet(clientSettings.getRequiredScopes());
    Map map = this.zaet.getOptionalApiSettings();
    for (Api api : map.keySet()) {
      if (!this.zaft.zahp.containsKey(api.getClientKey()))
        hashSet.addAll(((ClientSettings.OptionalApiSettings)map.get(api)).mScopes); 
    } 
    return hashSet;
  }
  
  private final void zab(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual zah : ()Lcom/google/android/gms/common/api/Api$BaseClientBuilder;
    //   4: invokevirtual getPriority : ()I
    //   7: istore #7
    //   9: iconst_0
    //   10: istore #6
    //   12: iload_3
    //   13: ifeq -> 58
    //   16: aload_1
    //   17: invokevirtual hasResolution : ()Z
    //   20: ifeq -> 29
    //   23: iconst_1
    //   24: istore #5
    //   26: goto -> 49
    //   29: aload_0
    //   30: getfield zaey : Lcom/google/android/gms/common/GoogleApiAvailabilityLight;
    //   33: aload_1
    //   34: invokevirtual getErrorCode : ()I
    //   37: invokevirtual getErrorResolutionIntent : (I)Landroid/content/Intent;
    //   40: ifnull -> 46
    //   43: goto -> 23
    //   46: iconst_0
    //   47: istore #5
    //   49: iload #6
    //   51: istore #4
    //   53: iload #5
    //   55: ifeq -> 81
    //   58: aload_0
    //   59: getfield zafh : Lcom/google/android/gms/common/ConnectionResult;
    //   62: ifnull -> 78
    //   65: iload #6
    //   67: istore #4
    //   69: iload #7
    //   71: aload_0
    //   72: getfield zafw : I
    //   75: if_icmpge -> 81
    //   78: iconst_1
    //   79: istore #4
    //   81: iload #4
    //   83: ifeq -> 97
    //   86: aload_0
    //   87: aload_1
    //   88: putfield zafh : Lcom/google/android/gms/common/ConnectionResult;
    //   91: aload_0
    //   92: iload #7
    //   94: putfield zafw : I
    //   97: aload_0
    //   98: getfield zaft : Lcom/google/android/gms/common/api/internal/zabe;
    //   101: getfield zahp : Ljava/util/Map;
    //   104: aload_2
    //   105: invokevirtual getClientKey : ()Lcom/google/android/gms/common/api/Api$AnyClientKey;
    //   108: aload_1
    //   109: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   114: pop
    //   115: return
  }
  
  private final void zab(boolean paramBoolean) {
    zad zad1 = this.zagb;
    if (zad1 != null) {
      if (zad1.isConnected() && paramBoolean)
        this.zagb.zacw(); 
      this.zagb.disconnect();
      if (this.zaet.isSignInClientDisconnectFixEnabled())
        this.zagb = null; 
      this.zagf = null;
    } 
  }
  
  private final boolean zac(int paramInt) {
    if (this.zafx != paramInt) {
      this.zaft.zaee.zaay();
      String str2 = String.valueOf(this);
      StringBuilder stringBuilder2 = new StringBuilder(String.valueOf(str2).length() + 23);
      stringBuilder2.append("Unexpected callback in ");
      stringBuilder2.append(str2);
      stringBuilder2.toString();
      int i = this.zafy;
      StringBuilder stringBuilder1 = new StringBuilder(33);
      stringBuilder1.append("mRemainingConnections=");
      stringBuilder1.append(i);
      stringBuilder1.toString();
      String str1 = zad(this.zafx);
      String str3 = zad(paramInt);
      StringBuilder stringBuilder3 = new StringBuilder(String.valueOf(str1).length() + 70 + String.valueOf(str3).length());
      stringBuilder3.append("GoogleApiClient connecting is in step ");
      stringBuilder3.append(str1);
      stringBuilder3.append(" but received callback for step ");
      stringBuilder3.append(str3);
      stringBuilder3.toString();
      new Exception();
      zae(new ConnectionResult(8, null));
      return false;
    } 
    return true;
  }
  
  private static String zad(int paramInt) {
    return (paramInt != 0) ? ((paramInt != 1) ? "UNKNOWN" : "STEP_GETTING_REMOTE_SERVICE") : "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
  }
  
  private final boolean zad(ConnectionResult paramConnectionResult) {
    return (this.zagc && !paramConnectionResult.hasResolution());
  }
  
  private final void zae(ConnectionResult paramConnectionResult) {
    zaas();
    zab(paramConnectionResult.hasResolution() ^ true);
    this.zaft.zaf(paramConnectionResult);
    this.zaft.zaht.zac(paramConnectionResult);
  }
  
  public final void begin() {
    this.zaft.zahp.clear();
    this.zagd = false;
    this.zafh = null;
    this.zafx = 0;
    this.zagc = true;
    this.zage = false;
    this.zagg = false;
    HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
    Iterator<Api> iterator = this.zaew.keySet().iterator();
    int i = 0;
    while (iterator.hasNext()) {
      byte b;
      Api<?> api = iterator.next();
      Api.Client client = this.zaft.zagz.get(api.getClientKey());
      if (api.zah().getPriority() == 1) {
        b = 1;
      } else {
        b = 0;
      } 
      i |= b;
      boolean bool = ((Boolean)this.zaew.get(api)).booleanValue();
      if (client.requiresSignIn()) {
        this.zagd = true;
        if (bool) {
          this.zaga.add(api.getClientKey());
        } else {
          this.zagc = false;
        } 
      } 
      hashMap.put(client, new zaam(this, api, bool));
    } 
    if (i != 0)
      this.zagd = false; 
    if (this.zagd) {
      this.zaet.setClientSessionId(Integer.valueOf(System.identityHashCode(this.zaft.zaee)));
      zaat zaat = new zaat(this, null);
      Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder = this.zace;
      Context context = this.mContext;
      Looper looper = this.zaft.zaee.getLooper();
      ClientSettings clientSettings = this.zaet;
      this.zagb = (zad)abstractClientBuilder.buildClient(context, looper, clientSettings, clientSettings.getSignInOptions(), zaat, zaat);
    } 
    this.zafy = this.zaft.zagz.size();
    this.zagi.add(zabh.zabb().submit(new zaan(this, (Map)hashMap)));
  }
  
  public final void connect() {}
  
  public final boolean disconnect() {
    zaas();
    zab(true);
    this.zaft.zaf(null);
    return true;
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  public final void onConnected(Bundle paramBundle) {
    if (!zac(1))
      return; 
    if (paramBundle != null)
      this.zafz.putAll(paramBundle); 
    if (zaao())
      zaaq(); 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    zae(new ConnectionResult(8, null));
  }
  
  public final void zaa(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    if (!zac(1))
      return; 
    zab(paramConnectionResult, paramApi, paramBoolean);
    if (zaao())
      zaaq(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */