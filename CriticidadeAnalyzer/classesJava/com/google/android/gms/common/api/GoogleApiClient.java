package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.api.internal.zaaw;
import com.google.android.gms.common.api.internal.zacm;
import com.google.android.gms.common.api.internal.zaj;
import com.google.android.gms.common.api.internal.zaq;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zaa;
import com.google.android.gms.signin.zad;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient {
  private static final Set<GoogleApiClient> zabq = Collections.newSetFromMap(new WeakHashMap<GoogleApiClient, Boolean>());
  
  public abstract void connect();
  
  public <A extends Api.AnyClient, T extends com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    throw new UnsupportedOperationException();
  }
  
  public Looper getLooper() {
    throw new UnsupportedOperationException();
  }
  
  public void zab(zacm paramzacm) {
    throw new UnsupportedOperationException();
  }
  
  public static final class Builder {
    private final Context mContext;
    
    private Looper zabj;
    
    private final Set<Scope> zabr = new HashSet<Scope>();
    
    private final Set<Scope> zabs = new HashSet<Scope>();
    
    private int zabt;
    
    private View zabu;
    
    private String zabv;
    
    private String zabw;
    
    private final Map<Api<?>, ClientSettings.OptionalApiSettings> zabx = (Map<Api<?>, ClientSettings.OptionalApiSettings>)new ArrayMap();
    
    private final Map<Api<?>, Api.ApiOptions> zabz = (Map<Api<?>, Api.ApiOptions>)new ArrayMap();
    
    private LifecycleActivity zaca;
    
    private int zacb = -1;
    
    private GoogleApiAvailability zacd = GoogleApiAvailability.getInstance();
    
    private Api.AbstractClientBuilder<? extends zad, SignInOptions> zace = zaa.zaph;
    
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zacf = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
    
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zacg = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
    
    private Account zax;
    
    public Builder(Context param1Context) {
      this.mContext = param1Context;
      this.zabj = param1Context.getMainLooper();
      this.zabv = param1Context.getPackageName();
      this.zabw = param1Context.getClass().getName();
    }
    
    public final Builder addApi(Api<? extends Api.ApiOptions.NotRequiredOptions> param1Api) {
      Preconditions.checkNotNull(param1Api, "Api must not be null");
      this.zabz.put(param1Api, null);
      List<Scope> list = param1Api.zah().getImpliedScopes(null);
      this.zabs.addAll(list);
      this.zabr.addAll(list);
      return this;
    }
    
    public final Builder addConnectionCallbacks(GoogleApiClient.ConnectionCallbacks param1ConnectionCallbacks) {
      Preconditions.checkNotNull(param1ConnectionCallbacks, "Listener must not be null");
      this.zacf.add(param1ConnectionCallbacks);
      return this;
    }
    
    public final Builder addOnConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener param1OnConnectionFailedListener) {
      Preconditions.checkNotNull(param1OnConnectionFailedListener, "Listener must not be null");
      this.zacg.add(param1OnConnectionFailedListener);
      return this;
    }
    
    public final GoogleApiClient build() {
      StringBuilder stringBuilder;
      String str;
      Preconditions.checkArgument(this.zabz.isEmpty() ^ true, "must call addApi() to add at least one API");
      ClientSettings clientSettings = buildClientSettings();
      Map map = clientSettings.getOptionalApiSettings();
      ArrayMap<Api, Boolean> arrayMap1 = new ArrayMap();
      ArrayMap<Api.AnyClientKey<?>, zaq> arrayMap = new ArrayMap();
      ArrayList<zaq> arrayList = new ArrayList();
      Iterator<Api> iterator = this.zabz.keySet().iterator();
      Api api = null;
      boolean bool = false;
      while (iterator.hasNext()) {
        boolean bool2;
        Api api1 = iterator.next();
        Object object = this.zabz.get(api1);
        if (map.get(api1) != null) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        arrayMap1.put(api1, Boolean.valueOf(bool2));
        zaq zaq = new zaq(api1, bool2);
        arrayList.add(zaq);
        Api.AbstractClientBuilder abstractClientBuilder = api1.zai();
        zaq = (zaq)abstractClientBuilder.buildClient(this.mContext, this.zabj, clientSettings, object, (GoogleApiClient.ConnectionCallbacks)zaq, (GoogleApiClient.OnConnectionFailedListener)zaq);
        arrayMap.put(api1.getClientKey(), zaq);
        boolean bool1 = bool;
        if (abstractClientBuilder.getPriority() == 1)
          if (object != null) {
            bool1 = true;
          } else {
            bool1 = false;
          }  
        bool = bool1;
        if (zaq.providesSignIn()) {
          if (api == null) {
            api = api1;
            bool = bool1;
            continue;
          } 
          String str1 = api1.getName();
          str = api.getName();
          stringBuilder = new StringBuilder(String.valueOf(str1).length() + 21 + String.valueOf(str).length());
          stringBuilder.append(str1);
          stringBuilder.append(" cannot be used with ");
          stringBuilder.append(str);
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } 
      if (stringBuilder != null)
        if (!bool) {
          boolean bool1;
          if (this.zax == null) {
            bool1 = true;
          } else {
            bool1 = false;
          } 
          Preconditions.checkState(bool1, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { stringBuilder.getName() });
          Preconditions.checkState(this.zabr.equals(this.zabs), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { stringBuilder.getName() });
        } else {
          String str1 = stringBuilder.getName();
          StringBuilder stringBuilder1 = new StringBuilder(String.valueOf(str1).length() + 82);
          stringBuilder1.append("With using ");
          stringBuilder1.append(str1);
          stringBuilder1.append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          throw new IllegalStateException(stringBuilder1.toString());
        }  
      int i = zaaw.zaa(str.values(), true);
      null = new zaaw(this.mContext, new ReentrantLock(), this.zabj, clientSettings, this.zacd, this.zace, (Map)arrayMap1, this.zacf, this.zacg, (Map)str, this.zacb, i, arrayList, false);
      synchronized (GoogleApiClient.zal()) {
        GoogleApiClient.zal().add(null);
        if (this.zacb < 0)
          return (GoogleApiClient)null; 
        zaj.zaa(this.zaca);
        throw null;
      } 
    }
    
    public final ClientSettings buildClientSettings() {
      SignInOptions signInOptions = SignInOptions.DEFAULT;
      if (this.zabz.containsKey(zaa.API))
        signInOptions = (SignInOptions)this.zabz.get(zaa.API); 
      return new ClientSettings(this.zax, this.zabr, this.zabx, this.zabt, this.zabu, this.zabv, this.zabw, signInOptions, false);
    }
  }
  
  public static interface ConnectionCallbacks {
    void onConnected(Bundle param1Bundle);
    
    void onConnectionSuspended(int param1Int);
  }
  
  public static interface OnConnectionFailedListener {
    void onConnectionFailed(ConnectionResult param1ConnectionResult);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */