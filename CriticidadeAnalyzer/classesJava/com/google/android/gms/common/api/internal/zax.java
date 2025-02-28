package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zax implements zabs {
  private final Looper zabj;
  
  private final GoogleApiManager zabm;
  
  private final Lock zaeo;
  
  private final ClientSettings zaet;
  
  private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu = new HashMap<Api.AnyClientKey<?>, zaw<?>>();
  
  private final Map<Api.AnyClientKey<?>, zaw<?>> zaev = new HashMap<Api.AnyClientKey<?>, zaw<?>>();
  
  private final Map<Api<?>, Boolean> zaew;
  
  private final zaaw zaex;
  
  private final GoogleApiAvailabilityLight zaey;
  
  private final Condition zaez;
  
  private final boolean zafa;
  
  private final boolean zafb;
  
  private final Queue<BaseImplementation$ApiMethodImpl<?, ?>> zafc = new LinkedList<BaseImplementation$ApiMethodImpl<?, ?>>();
  
  private boolean zafd;
  
  private Map<zai<?>, ConnectionResult> zafe;
  
  private Map<zai<?>, ConnectionResult> zaff;
  
  private zaaa zafg;
  
  private ConnectionResult zafh;
  
  public zax(Context paramContext, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, ArrayList<zaq> paramArrayList, zaaw paramzaaw, boolean paramBoolean) {
    this.zaeo = paramLock;
    this.zabj = paramLooper;
    this.zaez = paramLock.newCondition();
    this.zaey = paramGoogleApiAvailabilityLight;
    this.zaex = paramzaaw;
    this.zaew = paramMap1;
    this.zaet = paramClientSettings;
    this.zafa = paramBoolean;
    HashMap<Object, Object> hashMap1 = new HashMap<Object, Object>();
    for (Api<?> api : paramMap1.keySet())
      hashMap1.put(api.getClientKey(), api); 
    HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
    int j = paramArrayList.size();
    int i = 0;
    while (i < j) {
      paramMap1 = (Map<Api<?>, Boolean>)paramArrayList.get(i);
      i++;
      zaq zaq = (zaq)paramMap1;
      hashMap2.put(zaq.mApi, zaq);
    } 
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    paramBoolean = true;
    j = 0;
    int k = 1;
    for (i = 0; iterator.hasNext(); i = m) {
      Map.Entry entry = iterator.next();
      Api<Api.ApiOptions> api = (Api)hashMap1.get(entry.getKey());
      Api.Client client = (Api.Client)entry.getValue();
      if (client.requiresGooglePlayServices()) {
        boolean bool = ((Boolean)this.zaew.get(api)).booleanValue();
        j = k;
        if (!bool) {
          k = 1;
        } else {
          k = i;
        } 
        i = 1;
      } else {
        k = i;
        boolean bool = false;
        i = j;
        j = bool;
      } 
      zaw<Api.ApiOptions> zaw = new zaw<Api.ApiOptions>(paramContext, api, paramLooper, client, (zaq)hashMap2.get(api), paramClientSettings, paramAbstractClientBuilder);
      this.zaeu.put((Api.AnyClientKey)entry.getKey(), zaw);
      if (client.requiresSignIn())
        this.zaev.put((Api.AnyClientKey)entry.getKey(), zaw); 
      int m = k;
      k = j;
      j = i;
    } 
    if (j == 0 || k != 0 || i != 0)
      paramBoolean = false; 
    this.zafb = paramBoolean;
    this.zabm = GoogleApiManager.zabc();
  }
  
  private final ConnectionResult zaa(Api.AnyClientKey<?> paramAnyClientKey) {
    this.zaeo.lock();
    try {
      zaw zaw = this.zaeu.get(paramAnyClientKey);
      if (this.zafe != null && zaw != null)
        return this.zafe.get(zaw.zak()); 
      return null;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  private final boolean zaa(zaw<?> paramzaw, ConnectionResult paramConnectionResult) {
    return (!paramConnectionResult.isSuccess() && !paramConnectionResult.hasResolution() && ((Boolean)this.zaew.get(paramzaw.getApi())).booleanValue() && paramzaw.zaab().requiresGooglePlayServices() && this.zaey.isUserResolvableError(paramConnectionResult.getErrorCode()));
  }
  
  private final void zaad() {
    ClientSettings clientSettings = this.zaet;
    if (clientSettings == null) {
      this.zaex.zaha = Collections.emptySet();
      return;
    } 
    HashSet<Scope> hashSet = new HashSet(clientSettings.getRequiredScopes());
    Map map = this.zaet.getOptionalApiSettings();
    for (Api<?> api : (Iterable<Api<?>>)map.keySet()) {
      ConnectionResult connectionResult = getConnectionResult(api);
      if (connectionResult != null && connectionResult.isSuccess())
        hashSet.addAll(((ClientSettings.OptionalApiSettings)map.get(api)).mScopes); 
    } 
    this.zaex.zaha = hashSet;
  }
  
  private final void zaae() {
    while (!this.zafc.isEmpty())
      execute(this.zafc.remove()); 
    this.zaex.zab((Bundle)null);
  }
  
  private final ConnectionResult zaaf() {
    Iterator<zaw> iterator = this.zaeu.values().iterator();
    ConnectionResult connectionResult1 = null;
    ConnectionResult connectionResult2 = null;
    int j = 0;
    int i = 0;
    while (iterator.hasNext()) {
      zaw zaw = iterator.next();
      Api api = zaw.getApi();
      zai zai = zaw.zak();
      ConnectionResult connectionResult = this.zafe.get(zai);
      if (!connectionResult.isSuccess() && (!((Boolean)this.zaew.get(api)).booleanValue() || connectionResult.hasResolution() || this.zaey.isUserResolvableError(connectionResult.getErrorCode()))) {
        if (connectionResult.getErrorCode() == 4 && this.zafa) {
          int m = api.zah().getPriority();
          if (connectionResult2 == null || i > m) {
            connectionResult2 = connectionResult;
            i = m;
          } 
          continue;
        } 
        int k = api.zah().getPriority();
        if (connectionResult1 == null || j > k) {
          connectionResult1 = connectionResult;
          j = k;
        } 
      } 
    } 
    return (connectionResult1 != null && connectionResult2 != null && j > i) ? connectionResult2 : connectionResult1;
  }
  
  private final <T extends BaseImplementation$ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(T paramT) {
    Api.AnyClientKey<?> anyClientKey = paramT.getClientKey();
    ConnectionResult connectionResult = zaa(anyClientKey);
    if (connectionResult != null && connectionResult.getErrorCode() == 4) {
      paramT.setFailedResult(new Status(4, null, this.zabm.zaa(((zaw)this.zaeu.get(anyClientKey)).zak(), System.identityHashCode(this.zaex))));
      return true;
    } 
    return false;
  }
  
  public final void connect() {
    this.zaeo.lock();
    try {
      boolean bool = this.zafd;
      if (bool)
        return; 
      this.zafd = true;
      this.zafe = null;
      this.zaff = null;
      this.zafg = null;
      this.zafh = null;
      this.zabm.zao();
      Task<Map<zai<?>, String>> task = this.zabm.zaa(this.zaeu.values());
      HandlerExecutor handlerExecutor = new HandlerExecutor();
      this(this.zabj);
      zaz zaz = new zaz();
      this(this, null);
      task.addOnCompleteListener((Executor)handlerExecutor, zaz);
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {}
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    Api.AnyClientKey anyClientKey = paramT.getClientKey();
    if (this.zafa && zab((BaseImplementation$ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT))
      return paramT; 
    this.zaex.zahf.zab((BasePendingResult<? extends Result>)paramT);
    ((zaw)this.zaeu.get(anyClientKey)).doWrite((BaseImplementation$ApiMethodImpl)paramT);
    return paramT;
  }
  
  public final ConnectionResult getConnectionResult(Api<?> paramApi) {
    return zaa(paramApi.getClientKey());
  }
  
  public final boolean isConnected() {
    this.zaeo.lock();
    try {
      if (this.zafe != null) {
        ConnectionResult connectionResult = this.zafh;
        if (connectionResult == null)
          return true; 
      } 
      return false;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void zaw() {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */