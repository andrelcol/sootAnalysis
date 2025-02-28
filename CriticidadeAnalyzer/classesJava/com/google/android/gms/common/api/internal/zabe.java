package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zabe implements zabs, zar {
  private final Context mContext;
  
  private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
  
  final zaaw zaee;
  
  private final Lock zaeo;
  
  private final ClientSettings zaet;
  
  private final Map<Api<?>, Boolean> zaew;
  
  private final GoogleApiAvailabilityLight zaey;
  
  final Map<Api.AnyClientKey<?>, Api.Client> zagz;
  
  private final Condition zahn;
  
  private final zabg zaho;
  
  final Map<Api.AnyClientKey<?>, ConnectionResult> zahp = new HashMap<Api.AnyClientKey<?>, ConnectionResult>();
  
  private volatile zabd zahq;
  
  int zahs;
  
  final zabt zaht;
  
  public zabe(Context paramContext, zaaw paramzaaw, Lock paramLock, Looper paramLooper, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, Map<Api.AnyClientKey<?>, Api.Client> paramMap, ClientSettings paramClientSettings, Map<Api<?>, Boolean> paramMap1, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, ArrayList<zaq> paramArrayList, zabt paramzabt) {
    this.mContext = paramContext;
    this.zaeo = paramLock;
    this.zaey = paramGoogleApiAvailabilityLight;
    this.zagz = paramMap;
    this.zaet = paramClientSettings;
    this.zaew = paramMap1;
    this.zace = paramAbstractClientBuilder;
    this.zaee = paramzaaw;
    this.zaht = paramzabt;
    int i = paramArrayList.size();
    byte b = 0;
    while (b < i) {
      paramContext = (Context)paramArrayList.get(b);
      b++;
      ((zaq)paramContext).zaa(this);
    } 
    this.zaho = new zabg(this, paramLooper);
    this.zahn = paramLock.newCondition();
    this.zahq = new zaav(this);
  }
  
  public final void connect() {
    this.zahq.connect();
  }
  
  public final void disconnect() {
    if (this.zahq.disconnect())
      this.zahp.clear(); 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    String str = String.valueOf(paramString).concat("  ");
    paramPrintWriter.append(paramString).append("mState=").println(this.zahq);
    for (Api<?> api : this.zaew.keySet()) {
      paramPrintWriter.append(paramString).append(api.getName()).println(":");
      ((Api.Client)this.zagz.get(api.getClientKey())).dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    } 
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends com.google.android.gms.common.api.Result, A>> T execute(T paramT) {
    paramT.zau();
    return this.zahq.execute(paramT);
  }
  
  public final boolean isConnected() {
    return this.zahq instanceof zaah;
  }
  
  public final void onConnected(Bundle paramBundle) {
    this.zaeo.lock();
    try {
      this.zahq.onConnected(paramBundle);
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void onConnectionSuspended(int paramInt) {
    this.zaeo.lock();
    try {
      this.zahq.onConnectionSuspended(paramInt);
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void zaa(ConnectionResult paramConnectionResult, Api<?> paramApi, boolean paramBoolean) {
    this.zaeo.lock();
    try {
      this.zahq.zaa(paramConnectionResult, paramApi, paramBoolean);
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  final void zaa(zabf paramzabf) {
    Message message = this.zaho.obtainMessage(1, paramzabf);
    this.zaho.sendMessage(message);
  }
  
  final void zaaz() {
    this.zaeo.lock();
    try {
      zaak zaak = new zaak();
      this(this, this.zaet, this.zaew, this.zaey, this.zace, this.zaeo, this.mContext);
      this.zahq = zaak;
      this.zahq.begin();
      this.zahn.signalAll();
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  final void zab(RuntimeException paramRuntimeException) {
    Message message = this.zaho.obtainMessage(2, paramRuntimeException);
    this.zaho.sendMessage(message);
  }
  
  final void zaba() {
    this.zaeo.lock();
    try {
      this.zaee.zaaw();
      zaah zaah = new zaah();
      this(this);
      this.zahq = zaah;
      this.zahq.begin();
      this.zahn.signalAll();
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  final void zaf(ConnectionResult paramConnectionResult) {
    this.zaeo.lock();
    try {
      zaav zaav = new zaav();
      this(this);
      this.zahq = zaav;
      this.zahq.begin();
      this.zahn.signalAll();
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void zaw() {
    if (isConnected())
      ((zaah)this.zahq).zaam(); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */