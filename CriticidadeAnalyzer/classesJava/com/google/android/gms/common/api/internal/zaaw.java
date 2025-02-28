package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.Lock;

public final class zaaw extends GoogleApiClient implements zabt {
  private final Context mContext;
  
  private final Looper zabj;
  
  private final int zacb;
  
  private final GoogleApiAvailability zacd;
  
  private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
  
  private boolean zach;
  
  private final Lock zaeo;
  
  private final ClientSettings zaet;
  
  private final Map<Api<?>, Boolean> zaew;
  
  final Queue<BaseImplementation$ApiMethodImpl<?, ?>> zafc;
  
  private final GmsClientEventManager zags;
  
  private zabs zagt;
  
  private volatile boolean zagu;
  
  private long zagv;
  
  private long zagw;
  
  private final zabb zagx;
  
  private zabq zagy;
  
  final Map<Api.AnyClientKey<?>, Api.Client> zagz;
  
  Set<Scope> zaha;
  
  private final ListenerHolders zahb;
  
  private final ArrayList<zaq> zahc;
  
  private Integer zahd;
  
  Set<zacm> zahe;
  
  final zacp zahf;
  
  private final GmsClientEventManager.GmsClientEventState zahg;
  
  public zaaw(Context paramContext, Lock paramLock, Looper paramLooper, ClientSettings paramClientSettings, GoogleApiAvailability paramGoogleApiAvailability, Api.AbstractClientBuilder<? extends zad, SignInOptions> paramAbstractClientBuilder, Map<Api<?>, Boolean> paramMap, List<GoogleApiClient.ConnectionCallbacks> paramList, List<GoogleApiClient.OnConnectionFailedListener> paramList1, Map<Api.AnyClientKey<?>, Api.Client> paramMap1, int paramInt1, int paramInt2, ArrayList<zaq> paramArrayList, boolean paramBoolean) {
    long l;
    this.zagt = null;
    this.zafc = new LinkedList<BaseImplementation$ApiMethodImpl<?, ?>>();
    if (ClientLibraryUtils.isPackageSide()) {
      l = 10000L;
    } else {
      l = 120000L;
    } 
    this.zagv = l;
    this.zagw = 5000L;
    this.zaha = new HashSet<Scope>();
    this.zahb = new ListenerHolders();
    this.zahd = null;
    this.zahe = null;
    this.zahg = new zaax(this);
    this.mContext = paramContext;
    this.zaeo = paramLock;
    this.zach = false;
    this.zags = new GmsClientEventManager(paramLooper, this.zahg);
    this.zabj = paramLooper;
    this.zagx = new zabb(this, paramLooper);
    this.zacd = paramGoogleApiAvailability;
    this.zacb = paramInt1;
    if (this.zacb >= 0)
      this.zahd = Integer.valueOf(paramInt2); 
    this.zaew = paramMap;
    this.zagz = paramMap1;
    this.zahc = paramArrayList;
    this.zahf = new zacp(this.zagz);
    for (GoogleApiClient.ConnectionCallbacks connectionCallbacks : paramList)
      this.zags.registerConnectionCallbacks(connectionCallbacks); 
    for (GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener : paramList1)
      this.zags.registerConnectionFailedListener(onConnectionFailedListener); 
    this.zaet = paramClientSettings;
    this.zace = paramAbstractClientBuilder;
  }
  
  private final void resume() {
    this.zaeo.lock();
    try {
      if (this.zagu)
        zaau(); 
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public static int zaa(Iterable<Api.Client> paramIterable, boolean paramBoolean) {
    Iterator<Api.Client> iterator = paramIterable.iterator();
    boolean bool2 = false;
    boolean bool1 = false;
    while (iterator.hasNext()) {
      Api.Client client = iterator.next();
      boolean bool = bool2;
      if (client.requiresSignIn())
        bool = true; 
      bool2 = bool;
      if (client.providesSignIn()) {
        bool1 = true;
        bool2 = bool;
      } 
    } 
    return bool2 ? ((bool1 && paramBoolean) ? 2 : 1) : 3;
  }
  
  private final void zaau() {
    this.zags.enableCallbacks();
    this.zagt.connect();
  }
  
  private final void zaav() {
    this.zaeo.lock();
    try {
      if (zaaw())
        zaau(); 
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  private final void zae(int paramInt) {
    Integer integer = this.zahd;
    if (integer == null) {
      this.zahd = Integer.valueOf(paramInt);
    } else if (integer.intValue() != paramInt) {
      String str1 = zaf(paramInt);
      String str2 = zaf(this.zahd.intValue());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 51 + String.valueOf(str2).length());
      stringBuilder.append("Cannot use sign-in mode: ");
      stringBuilder.append(str1);
      stringBuilder.append(". Mode was already set to ");
      stringBuilder.append(str2);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    if (this.zagt != null)
      return; 
    Iterator<Api.Client> iterator = this.zagz.values().iterator();
    boolean bool = false;
    paramInt = 0;
    while (iterator.hasNext()) {
      Api.Client client = iterator.next();
      boolean bool1 = bool;
      if (client.requiresSignIn())
        bool1 = true; 
      bool = bool1;
      if (client.providesSignIn()) {
        paramInt = 1;
        bool = bool1;
      } 
    } 
    int i = this.zahd.intValue();
    if (i != 1) {
      if (i == 2 && bool) {
        if (this.zach) {
          this.zagt = new zax(this.mContext, this.zaeo, this.zabj, (GoogleApiAvailabilityLight)this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, true);
          return;
        } 
        this.zagt = zas.zaa(this.mContext, this, this.zaeo, this.zabj, (GoogleApiAvailabilityLight)this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc);
        return;
      } 
    } else if (bool) {
      if (paramInt != 0)
        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead."); 
    } else {
      throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
    } 
    if (this.zach && paramInt == 0) {
      this.zagt = new zax(this.mContext, this.zaeo, this.zabj, (GoogleApiAvailabilityLight)this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, false);
      return;
    } 
    this.zagt = new zabe(this.mContext, this, this.zaeo, this.zabj, (GoogleApiAvailabilityLight)this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this);
  }
  
  private static String zaf(int paramInt) {
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? "UNKNOWN" : "SIGN_IN_MODE_NONE") : "SIGN_IN_MODE_OPTIONAL") : "SIGN_IN_MODE_REQUIRED";
  }
  
  public final void connect() {
    this.zaeo.lock();
    try {
      int i = this.zacb;
      boolean bool = false;
      if (i >= 0) {
        if (this.zahd != null)
          bool = true; 
        Preconditions.checkState(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      } else if (this.zahd == null) {
        this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
      } else if (this.zahd.intValue() == 2) {
        IllegalStateException illegalStateException = new IllegalStateException();
        this("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
        throw illegalStateException;
      } 
      connect(this.zahd.intValue());
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void connect(int paramInt) {
    this.zaeo.lock();
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (paramInt != 3) {
      bool1 = bool2;
      if (paramInt != 1)
        if (paramInt == 2) {
          bool1 = bool2;
        } else {
          bool1 = false;
        }  
    } 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this(33);
      stringBuilder.append("Illegal sign-in mode: ");
      stringBuilder.append(paramInt);
      Preconditions.checkArgument(bool1, stringBuilder.toString());
      zae(paramInt);
      zaau();
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    paramPrintWriter.append(paramString).append("mContext=").println(this.mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(this.zagu);
    paramPrintWriter.append(" mWorkQueue.size()=").print(this.zafc.size());
    zacp zacp1 = this.zahf;
    paramPrintWriter.append(" mUnconsumedApiCalls.size()=").println(zacp1.zakz.size());
    zabs zabs1 = this.zagt;
    if (zabs1 != null)
      zabs1.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString); 
  }
  
  public final <A extends Api.AnyClient, T extends BaseImplementation$ApiMethodImpl<? extends Result, A>> T execute(T paramT) {
    String str;
    if (paramT.getClientKey() != null) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "This task can not be executed (it's probably a Batch or malformed)");
    boolean bool = this.zagz.containsKey(paramT.getClientKey());
    if (paramT.getApi() != null) {
      str = paramT.getApi().getName();
    } else {
      str = "the API";
    } 
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 65);
    stringBuilder.append("GoogleApiClient is not configured to use ");
    stringBuilder.append(str);
    stringBuilder.append(" required for this call.");
    Preconditions.checkArgument(bool, stringBuilder.toString());
    this.zaeo.lock();
    try {
      if (this.zagt != null) {
        if (this.zagu) {
          this.zafc.add((BaseImplementation$ApiMethodImpl<?, ?>)paramT);
          while (!this.zafc.isEmpty()) {
            BaseImplementation$ApiMethodImpl<? extends Result> baseImplementation$ApiMethodImpl = (BaseImplementation$ApiMethodImpl)this.zafc.remove();
            this.zahf.zab(baseImplementation$ApiMethodImpl);
            baseImplementation$ApiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
          } 
          return paramT;
        } 
        paramT = this.zagt.execute(paramT);
        return paramT;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      this("GoogleApiClient is not connected yet.");
      throw illegalStateException;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final Looper getLooper() {
    return this.zabj;
  }
  
  public final boolean isConnected() {
    zabs zabs1 = this.zagt;
    return (zabs1 != null && zabs1.isConnected());
  }
  
  final boolean zaaw() {
    if (!this.zagu)
      return false; 
    this.zagu = false;
    this.zagx.removeMessages(2);
    this.zagx.removeMessages(1);
    zabq zabq1 = this.zagy;
    if (zabq1 != null) {
      zabq1.unregister();
      this.zagy = null;
    } 
    return true;
  }
  
  final boolean zaax() {
    this.zaeo.lock();
    try {
      Set<zacm> set = this.zahe;
      if (set == null)
        return false; 
      boolean bool = this.zahe.isEmpty();
      return bool ^ true;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  final String zaay() {
    StringWriter stringWriter = new StringWriter();
    dump("", null, new PrintWriter(stringWriter), null);
    return stringWriter.toString();
  }
  
  public final void zab(int paramInt, boolean paramBoolean) {
    if (paramInt == 1 && !paramBoolean && !this.zagu) {
      this.zagu = true;
      if (this.zagy == null && !ClientLibraryUtils.isPackageSide())
        this.zagy = this.zacd.zaa(this.mContext.getApplicationContext(), new zabc(this)); 
      zabb zabb1 = this.zagx;
      zabb1.sendMessageDelayed(zabb1.obtainMessage(1), this.zagv);
      zabb1 = this.zagx;
      zabb1.sendMessageDelayed(zabb1.obtainMessage(2), this.zagw);
    } 
    this.zahf.zabx();
    this.zags.onUnintentionalDisconnection(paramInt);
    this.zags.disableCallbacks();
    if (paramInt == 2)
      zaau(); 
  }
  
  public final void zab(Bundle paramBundle) {
    while (!this.zafc.isEmpty())
      execute(this.zafc.remove()); 
    this.zags.onConnectionSuccess(paramBundle);
  }
  
  public final void zab(zacm paramzacm) {
    this.zaeo.lock();
    try {
      if (this.zahe == null) {
        new Exception();
      } else if (!this.zahe.remove(paramzacm)) {
        new Exception();
      } else if (!zaax()) {
        this.zagt.zaw();
      } 
      return;
    } finally {
      this.zaeo.unlock();
    } 
  }
  
  public final void zac(ConnectionResult paramConnectionResult) {
    if (!this.zacd.isPlayServicesPossiblyUpdating(this.mContext, paramConnectionResult.getErrorCode()))
      zaaw(); 
    if (!this.zagu) {
      this.zags.onConnectionFailure(paramConnectionResult);
      this.zags.disableCallbacks();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */