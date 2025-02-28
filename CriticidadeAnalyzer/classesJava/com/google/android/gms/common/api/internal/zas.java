package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;

final class zas implements zabs {
  private final Context mContext;
  
  private final zaaw zaee;
  
  private final zabe zaef;
  
  private final zabe zaeg;
  
  private final Map<Api.AnyClientKey<?>, zabe> zaeh;
  
  private final Set<SignInConnectionListener> zaei = Collections.newSetFromMap(new WeakHashMap<SignInConnectionListener, Boolean>());
  
  private final Api.Client zaej;
  
  private Bundle zaek;
  
  private ConnectionResult zael = null;
  
  private ConnectionResult zaem = null;
  
  private boolean zaen = false;
  
  private final Lock zaeo;
  
  private int zaep = 0;
  
  private zas(Context paramContext, zaaw paramzaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap1, Map<Api.AnyClientKey<?>, Api.Client> paramMap2, ClientSettings paramClientSettings, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, Api.Client paramClient, ArrayList<zaq> paramArrayList1, ArrayList<zaq> paramArrayList2, Map<Api<?>, Boolean> paramMap3, Map<Api<?>, Boolean> paramMap4) {
    this.mContext = paramContext;
    this.zaee = paramzaaw;
    this.zaeo = paramLock;
    this.zaej = paramClient;
    this.zaef = new zabe(paramContext, this.zaee, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap2, null, paramMap4, null, paramArrayList2, new zau(this, null));
    this.zaeg = new zabe(paramContext, this.zaee, paramLock, paramLooper, paramGoogleApiAvailabilityLight, paramMap1, paramClientSettings, paramMap3, paramAbstractClientBuilder, paramArrayList1, new zav(this, null));
    ArrayMap arrayMap = new ArrayMap();
    Iterator<Api.AnyClientKey> iterator = paramMap2.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zaef); 
    iterator = paramMap1.keySet().iterator();
    while (iterator.hasNext())
      arrayMap.put(iterator.next(), this.zaeg); 
    this.zaeh = Collections.unmodifiableMap((Map<? extends Api.AnyClientKey<?>, ? extends zabe>)arrayMap);
  }
  
  public static zas zaa(Context paramContext, zaaw paramzaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, ArrayList<zaq> paramArrayList) {
    Api.Client client;
    ArrayMap<Api.AnyClientKey, Api.Client> arrayMap3 = new ArrayMap();
    ArrayMap<Api.AnyClientKey, Api.Client> arrayMap2 = new ArrayMap();
    Iterator<Map.Entry> iterator = paramMap.entrySet().iterator();
    paramMap = null;
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      Api.Client client1 = (Api.Client)entry.getValue();
      if (client1.providesSignIn())
        client = client1; 
      if (client1.requiresSignIn()) {
        arrayMap3.put((Api.AnyClientKey)entry.getKey(), client1);
        continue;
      } 
      arrayMap2.put((Api.AnyClientKey)entry.getKey(), client1);
    } 
    Preconditions.checkState(arrayMap3.isEmpty() ^ true, "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
    ArrayMap<Api, Boolean> arrayMap4 = new ArrayMap();
    ArrayMap<Api, Boolean> arrayMap1 = new ArrayMap();
    for (Api<?> api : paramMap1.keySet()) {
      Api.AnyClientKey anyClientKey = api.getClientKey();
      if (arrayMap3.containsKey(anyClientKey)) {
        arrayMap4.put(api, paramMap1.get(api));
        continue;
      } 
      if (arrayMap2.containsKey(anyClientKey)) {
        arrayMap1.put(api, paramMap1.get(api));
        continue;
      } 
      throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
    } 
    ArrayList<zaq> arrayList1 = new ArrayList();
    ArrayList<zaq> arrayList2 = new ArrayList();
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      zaq zaq = (zaq)paramArrayList.get(b);
      b++;
      zaq = zaq;
      if (arrayMap4.containsKey(zaq.mApi)) {
        arrayList1.add(zaq);
        continue;
      } 
      if (arrayMap1.containsKey(zaq.mApi)) {
        arrayList2.add(zaq);
        continue;
      } 
      throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
    } 
    return new zas(paramContext, paramzaaw, paramLock, paramLooper, paramGoogleApiAvailabilityLight, (Map)arrayMap3, (Map)arrayMap2, paramClientSettings, paramAbstractClientBuilder, client, arrayList1, arrayList2, (Map)arrayMap4, (Map)arrayMap1);
  }
  
  private final void zaa(int paramInt, boolean paramBoolean) {
    this.zaee.zab(paramInt, paramBoolean);
    this.zaem = null;
    this.zael = null;
  }
  
  private final void zaa(Bundle paramBundle) {
    Bundle bundle = this.zaek;
    if (bundle == null) {
      this.zaek = paramBundle;
      return;
    } 
    if (paramBundle != null)
      bundle.putAll(paramBundle); 
  }
  
  private final void zaa(ConnectionResult paramConnectionResult) {
    int i = this.zaep;
    if (i != 1) {
      if (i != 2) {
        new Exception();
      } else {
        this.zaee.zac(paramConnectionResult);
        zay();
      } 
      this.zaep = 0;
      return;
    } 
    zay();
  }
  
  private final boolean zaa(BaseImplementation$ApiMethodImpl<? extends Result, ? extends Api.AnyClient> paramBaseImplementation$ApiMethodImpl) {
    Api.AnyClientKey<? extends Api.AnyClient> anyClientKey = paramBaseImplementation$ApiMethodImpl.getClientKey();
    Preconditions.checkArgument(this.zaeh.containsKey(anyClientKey), "GoogleApiClient is not configured to use the API required for this call.");
    return ((zabe)this.zaeh.get(anyClientKey)).equals(this.zaeg);
  }
  
  private final PendingIntent zaaa() {
    if (this.zaej == null)
      return null; 
    System.identityHashCode(this.zaee);
    this.zaej.getSignInIntent();
    throw null;
  }
  
  private static boolean zab(ConnectionResult paramConnectionResult) {
    return (paramConnectionResult != null && paramConnectionResult.isSuccess());
  }
  
  private final void zax() {
    if (zab(this.zael)) {
      if (zab(this.zaem) || zaz()) {
        int i = this.zaep;
        if (i != 1) {
          if (i != 2) {
            new AssertionError();
          } else {
            this.zaee.zab(this.zaek);
            zay();
          } 
          this.zaep = 0;
          return;
        } 
      } else {
        ConnectionResult connectionResult = this.zaem;
        if (connectionResult != null) {
          if (this.zaep == 1) {
            zay();
            return;
          } 
          zaa(connectionResult);
          this.zaef.disconnect();
          return;
        } 
        return;
      } 
    } else {
      if (this.zael != null && zab(this.zaem)) {
        this.zaeg.disconnect();
        zaa(this.zael);
        return;
      } 
      ConnectionResult connectionResult = this.zael;
      if (connectionResult != null) {
        ConnectionResult connectionResult1 = this.zaem;
        if (connectionResult1 != null) {
          if (this.zaeg.zahs < this.zaef.zahs)
            connectionResult = connectionResult1; 
          zaa(connectionResult);
        } 
      } 
      return;
    } 
    zay();
  }
  
  private final void zay() {
    Iterator<SignInConnectionListener> iterator = this.zaei.iterator();
    while (iterator.hasNext())
      ((SignInConnectionListener)iterator.next()).onComplete(); 
    this.zaei.clear();
  }
  
  private final boolean zaz() {
    ConnectionResult connectionResult = this.zaem;
    return (connectionResult != null && connectionResult.getErrorCode() == 4);
  }
  
  public final void connect() {
    this.zaep = 2;
    this.zaen = false;
    this.zaem = null;
    this.zael = null;
    this.zaef.connect();
    this.zaeg.connect();
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("authClient").println(":");
    this.zaeg.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.append(paramString).append("anonClient").println(":");
    this.zaef.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    if (zaa((BaseImplementation$ApiMethodImpl<? extends Result, ? extends Api.AnyClient>)paramT)) {
      if (zaz()) {
        paramT.setFailedResult(new Status(4, null, zaaa()));
        return paramT;
      } 
      return this.zaeg.execute(paramT);
    } 
    return this.zaef.execute(paramT);
  }
  
  public final boolean isConnected() {
    this.zaeo.lock();
    try {
      boolean bool = this.zaef.isConnected();
      boolean bool1 = true;
      if (bool) {
        bool = bool1;
        if (!this.zaeg.isConnected()) {
          bool = bool1;
          if (!zaz()) {
            int i = this.zaep;
            if (i == 1) {
              bool = bool1;
              return bool;
            } 
          } else {
            return bool;
          } 
        } else {
          return bool;
        } 
      } 
      bool = false;
      return bool;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void zaw() {
    this.zaef.zaw();
    this.zaeg.zaw();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */