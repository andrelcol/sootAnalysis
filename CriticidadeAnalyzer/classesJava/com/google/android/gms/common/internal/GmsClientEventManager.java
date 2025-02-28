package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.base.zap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

public final class GmsClientEventManager implements Handler.Callback {
  private final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private final GmsClientEventState zaol;
  
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaom = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
  
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zaon = new ArrayList<GoogleApiClient.ConnectionCallbacks>();
  
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zaoo = new ArrayList<GoogleApiClient.OnConnectionFailedListener>();
  
  private volatile boolean zaop = false;
  
  private final AtomicInteger zaoq = new AtomicInteger(0);
  
  private boolean zaor = false;
  
  public GmsClientEventManager(Looper paramLooper, GmsClientEventState paramGmsClientEventState) {
    this.zaol = paramGmsClientEventState;
    this.mHandler = (Handler)new zap(paramLooper, this);
  }
  
  public final void disableCallbacks() {
    this.zaop = false;
    this.zaoq.incrementAndGet();
  }
  
  public final void enableCallbacks() {
    this.zaop = true;
  }
  
  public final boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i == 1) {
      null = (GoogleApiClient.ConnectionCallbacks)paramMessage.obj;
      synchronized (this.mLock) {
        if (this.zaop && this.zaol.isConnected() && this.zaom.contains(null))
          null.onConnected(this.zaol.getConnectionHint()); 
        return true;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder(45);
    stringBuilder.append("Don't know how to handle message: ");
    stringBuilder.append(i);
    stringBuilder.toString();
    new Exception();
    return false;
  }
  
  public final void onConnectionFailure(ConnectionResult paramConnectionResult) {
    Preconditions.checkHandlerThread(this.mHandler, "onConnectionFailure must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zaoo);
      int k = this.zaoq.get();
      int j = arrayList.size();
      int i = 0;
      while (i < j) {
        GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)arrayList.get(i);
        int m = i + 1;
        onConnectionFailedListener = onConnectionFailedListener;
        if (!this.zaop || this.zaoq.get() != k)
          return; 
        i = m;
        if (this.zaoo.contains(onConnectionFailedListener)) {
          onConnectionFailedListener.onConnectionFailed(paramConnectionResult);
          i = m;
        } 
      } 
      return;
    } 
  }
  
  public final void onConnectionSuccess(Bundle paramBundle) {
    Preconditions.checkHandlerThread(this.mHandler, "onConnectionSuccess must only be called on the Handler thread");
    synchronized (this.mLock) {
      boolean bool = this.zaor;
      boolean bool1 = true;
      if (!bool) {
        bool = true;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool);
      this.mHandler.removeMessages(1);
      this.zaor = true;
      if (this.zaon.size() == 0) {
        bool = bool1;
      } else {
        bool = false;
      } 
      Preconditions.checkState(bool);
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zaom);
      int j = this.zaoq.get();
      int k = arrayList.size();
      int i = 0;
      while (i < k) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(i);
        int m = i + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zaop && this.zaol.isConnected() && this.zaoq.get() == j) {
          i = m;
          if (!this.zaon.contains(connectionCallbacks)) {
            connectionCallbacks.onConnected(paramBundle);
            i = m;
          } 
        } 
      } 
      this.zaon.clear();
      this.zaor = false;
      return;
    } 
  }
  
  public final void onUnintentionalDisconnection(int paramInt) {
    Preconditions.checkHandlerThread(this.mHandler, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.mLock) {
      this.zaor = true;
      ArrayList<Object> arrayList = new ArrayList();
      this((Collection)this.zaom);
      int j = this.zaoq.get();
      int k = arrayList.size();
      int i = 0;
      while (i < k) {
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks)arrayList.get(i);
        int m = i + 1;
        connectionCallbacks = connectionCallbacks;
        if (this.zaop && this.zaoq.get() == j) {
          i = m;
          if (this.zaom.contains(connectionCallbacks)) {
            connectionCallbacks.onConnectionSuspended(paramInt);
            i = m;
          } 
        } 
      } 
      this.zaon.clear();
      this.zaor = false;
      return;
    } 
  }
  
  public final void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks) {
    Preconditions.checkNotNull(paramConnectionCallbacks);
    synchronized (this.mLock) {
      if (this.zaom.contains(paramConnectionCallbacks)) {
        String str = String.valueOf(paramConnectionCallbacks);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 62);
        stringBuilder.append("registerConnectionCallbacks(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        stringBuilder.toString();
      } else {
        this.zaom.add(paramConnectionCallbacks);
      } 
      if (this.zaol.isConnected()) {
        null = this.mHandler;
        null.sendMessage(null.obtainMessage(1, paramConnectionCallbacks));
      } 
      return;
    } 
  }
  
  public final void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener) {
    Preconditions.checkNotNull(paramOnConnectionFailedListener);
    synchronized (this.mLock) {
      StringBuilder stringBuilder;
      if (this.zaoo.contains(paramOnConnectionFailedListener)) {
        String str = String.valueOf(paramOnConnectionFailedListener);
        int i = String.valueOf(str).length();
        stringBuilder = new StringBuilder();
        this(i + 67);
        stringBuilder.append("registerConnectionFailedListener(): listener ");
        stringBuilder.append(str);
        stringBuilder.append(" is already registered");
        stringBuilder.toString();
      } else {
        this.zaoo.add(stringBuilder);
      } 
      return;
    } 
  }
  
  public static interface GmsClientEventState {
    Bundle getConnectionHint();
    
    boolean isConnected();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/GmsClientEventManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */