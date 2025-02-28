package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseGmsClient<T extends IInterface> {
  private static final Feature[] zzbt = new Feature[0];
  
  private final Context mContext;
  
  final Handler mHandler;
  
  private final Object mLock = new Object();
  
  private int zzbu;
  
  private long zzbv;
  
  private long zzbw;
  
  private int zzbx;
  
  private long zzby;
  
  private zzh zzbz;
  
  private final GmsClientSupervisor zzcb;
  
  private final GoogleApiAvailabilityLight zzcc;
  
  private final Object zzcd = new Object();
  
  private IGmsServiceBroker zzce;
  
  protected ConnectionProgressReportCallbacks zzcf;
  
  private T zzcg;
  
  private final ArrayList<zzc<?>> zzch = new ArrayList<zzc<?>>();
  
  private zze zzci;
  
  private int zzcj = 1;
  
  private final BaseConnectionCallbacks zzck;
  
  private final BaseOnConnectionFailedListener zzcl;
  
  private final int zzcm;
  
  private final String zzcn;
  
  private ConnectionResult zzco = null;
  
  private boolean zzcp = false;
  
  private volatile zzb zzcq = null;
  
  protected AtomicInteger zzcr = new AtomicInteger(0);
  
  protected BaseGmsClient(Context paramContext, Looper paramLooper, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString) {
    this(paramContext, paramLooper, gmsClientSupervisor, googleApiAvailabilityLight, paramInt, paramBaseConnectionCallbacks, paramBaseOnConnectionFailedListener, paramString);
  }
  
  protected BaseGmsClient(Context paramContext, Looper paramLooper, GmsClientSupervisor paramGmsClientSupervisor, GoogleApiAvailabilityLight paramGoogleApiAvailabilityLight, int paramInt, BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener, String paramString) {
    Preconditions.checkNotNull(paramContext, "Context must not be null");
    this.mContext = paramContext;
    Preconditions.checkNotNull(paramLooper, "Looper must not be null");
    Looper looper = paramLooper;
    Preconditions.checkNotNull(paramGmsClientSupervisor, "Supervisor must not be null");
    this.zzcb = paramGmsClientSupervisor;
    Preconditions.checkNotNull(paramGoogleApiAvailabilityLight, "API availability must not be null");
    this.zzcc = paramGoogleApiAvailabilityLight;
    this.mHandler = (Handler)new zzb(this, paramLooper);
    this.zzcm = paramInt;
    this.zzck = paramBaseConnectionCallbacks;
    this.zzcl = paramBaseOnConnectionFailedListener;
    this.zzcn = paramString;
  }
  
  private final void zza(int paramInt, T paramT) {
    int i;
    byte b;
    boolean bool;
    if (paramInt == 4) {
      i = 1;
    } else {
      i = 0;
    } 
    if (paramT != null) {
      b = 1;
    } else {
      b = 0;
    } 
    if (i == b) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    synchronized (this.mLock) {
      this.zzcj = paramInt;
      this.zzcg = paramT;
      onSetConnectState(paramInt, paramT);
      if (paramInt != 1) {
        if (paramInt != 2 && paramInt != 3) {
          if (paramInt == 4)
            onConnectedLocked(paramT); 
        } else {
          zzh zzh1;
          if (this.zzci != null && this.zzbz != null) {
            String str5 = this.zzbz.zzt();
            String str4 = this.zzbz.getPackageName();
            i = String.valueOf(str5).length();
            paramInt = String.valueOf(str4).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 70 + paramInt);
            stringBuilder.append("Calling connect() while still connected, missing disconnect() for ");
            stringBuilder.append(str5);
            stringBuilder.append(" on ");
            stringBuilder.append(str4);
            stringBuilder.toString();
            this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
            this.zzcr.incrementAndGet();
          } 
          zze zze1 = new zze();
          this(this, this.zzcr.get());
          this.zzci = zze1;
          if (this.zzcj == 3 && getLocalStartServiceAction() != null) {
            zzh1 = new zzh();
            this(getContext().getPackageName(), getLocalStartServiceAction(), true, 129);
          } else {
            zzh1 = new zzh(getStartServicePackage(), getStartServiceAction(), false, 129);
          } 
          this.zzbz = zzh1;
          GmsClientSupervisor gmsClientSupervisor = this.zzcb;
          String str1 = this.zzbz.zzt();
          String str3 = this.zzbz.getPackageName();
          paramInt = this.zzbz.zzq();
          zze zze2 = this.zzci;
          String str2 = zzj();
          GmsClientSupervisor.zza zza = new GmsClientSupervisor.zza();
          this(str1, str3, paramInt);
          if (!gmsClientSupervisor.zza(zza, zze2, str2)) {
            str2 = this.zzbz.zzt();
            String str = this.zzbz.getPackageName();
            i = String.valueOf(str2).length();
            paramInt = String.valueOf(str).length();
            StringBuilder stringBuilder = new StringBuilder();
            this(i + 34 + paramInt);
            stringBuilder.append("unable to connect to service: ");
            stringBuilder.append(str2);
            stringBuilder.append(" on ");
            stringBuilder.append(str);
            stringBuilder.toString();
            zza(16, (Bundle)null, this.zzcr.get());
          } 
        } 
      } else if (this.zzci != null) {
        this.zzcb.zza(this.zzbz.zzt(), this.zzbz.getPackageName(), this.zzbz.zzq(), this.zzci, zzj());
        this.zzci = null;
      } 
      return;
    } 
  }
  
  private final void zza(zzb paramzzb) {
    this.zzcq = paramzzb;
  }
  
  private final boolean zza(int paramInt1, int paramInt2, T paramT) {
    synchronized (this.mLock) {
      if (this.zzcj != paramInt1)
        return false; 
      zza(paramInt2, paramT);
      return true;
    } 
  }
  
  private final void zzb(int paramInt) {
    if (zzk()) {
      paramInt = 5;
      this.zzcp = true;
    } else {
      paramInt = 4;
    } 
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(paramInt, this.zzcr.get(), 16));
  }
  
  private final String zzj() {
    String str2 = this.zzcn;
    String str1 = str2;
    if (str2 == null)
      str1 = this.mContext.getClass().getName(); 
    return str1;
  }
  
  private final boolean zzk() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzcj == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  private final boolean zzl() {
    if (this.zzcp)
      return false; 
    if (TextUtils.isEmpty(getServiceDescriptor()))
      return false; 
    if (TextUtils.isEmpty(getLocalStartServiceAction()))
      return false; 
    try {
      Class.forName(getServiceDescriptor());
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  public void checkAvailabilityAndConnect() {
    int i = this.zzcc.isGooglePlayServicesAvailable(this.mContext, getMinApkVersion());
    if (i != 0) {
      zza(1, (T)null);
      triggerNotAvailable(new LegacyClientCallbackAdapter(this), i, null);
      return;
    } 
    connect(new LegacyClientCallbackAdapter(this));
  }
  
  protected final void checkConnected() {
    if (isConnected())
      return; 
    throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
  }
  
  public void connect(ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks) {
    Preconditions.checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
    this.zzcf = paramConnectionProgressReportCallbacks;
    zza(2, (T)null);
  }
  
  protected abstract T createServiceInterface(IBinder paramIBinder);
  
  public void disconnect() {
    this.zzcr.incrementAndGet();
    synchronized (this.zzch) {
      int i = this.zzch.size();
      for (byte b = 0; b < i; b++)
        ((zzc)this.zzch.get(b)).removeListener(); 
      this.zzch.clear();
      synchronized (this.zzcd) {
        this.zzce = null;
        zza(1, (T)null);
        return;
      } 
    } 
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    synchronized (this.mLock) {
      int i = this.zzcj;
      T t = this.zzcg;
      synchronized (this.zzcd) {
        IGmsServiceBroker iGmsServiceBroker = this.zzce;
        paramPrintWriter.append(paramString).append("mConnectState=");
        if (i != 1) {
          if (i != 2) {
            if (i != 3) {
              if (i != 4) {
                if (i != 5) {
                  paramPrintWriter.print("UNKNOWN");
                } else {
                  paramPrintWriter.print("DISCONNECTING");
                } 
              } else {
                paramPrintWriter.print("CONNECTED");
              } 
            } else {
              paramPrintWriter.print("LOCAL_CONNECTING");
            } 
          } else {
            paramPrintWriter.print("REMOTE_CONNECTING");
          } 
        } else {
          paramPrintWriter.print("DISCONNECTED");
        } 
        paramPrintWriter.append(" mService=");
        if (t == null) {
          paramPrintWriter.append("null");
        } else {
          paramPrintWriter.append(getServiceDescriptor()).append("@").append(Integer.toHexString(System.identityHashCode(t.asBinder())));
        } 
        paramPrintWriter.append(" mServiceBroker=");
        if (iGmsServiceBroker == null) {
          paramPrintWriter.println("null");
        } else {
          paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(iGmsServiceBroker.asBinder())));
        } 
        null = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        if (this.zzbw > 0L) {
          PrintWriter printWriter = paramPrintWriter.append(paramString).append("lastConnectedTime=");
          long l = this.zzbw;
          String str = null.format(new Date(l));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append(str);
          printWriter.println(stringBuilder.toString());
        } 
        if (this.zzbv > 0L) {
          paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          i = this.zzbu;
          if (i != 1) {
            if (i != 2) {
              paramPrintWriter.append(String.valueOf(i));
            } else {
              paramPrintWriter.append("CAUSE_NETWORK_LOST");
            } 
          } else {
            paramPrintWriter.append("CAUSE_SERVICE_DISCONNECTED");
          } 
          PrintWriter printWriter = paramPrintWriter.append(" lastSuspendedTime=");
          long l = this.zzbv;
          String str = null.format(new Date(l));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append(str);
          printWriter.println(stringBuilder.toString());
        } 
        if (this.zzby > 0L) {
          paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzbx));
          PrintWriter printWriter = paramPrintWriter.append(" lastFailedTime=");
          long l = this.zzby;
          null = null.format(new Date(l));
          StringBuilder stringBuilder = new StringBuilder(String.valueOf(null).length() + 21);
          stringBuilder.append(l);
          stringBuilder.append(" ");
          stringBuilder.append((String)null);
          printWriter.println(stringBuilder.toString());
        } 
        return;
      } 
    } 
  }
  
  protected boolean enableLocalFallback() {
    return false;
  }
  
  public Account getAccount() {
    return null;
  }
  
  public Feature[] getApiFeatures() {
    return zzbt;
  }
  
  public final Feature[] getAvailableFeatures() {
    zzb zzb1 = this.zzcq;
    return (zzb1 == null) ? null : zzb1.zzdb;
  }
  
  public Bundle getConnectionHint() {
    return null;
  }
  
  public final Context getContext() {
    return this.mContext;
  }
  
  public String getEndpointPackageName() {
    if (isConnected()) {
      zzh zzh1 = this.zzbz;
      if (zzh1 != null)
        return zzh1.getPackageName(); 
    } 
    throw new RuntimeException("Failed to connect when checking package");
  }
  
  protected Bundle getGetServiceRequestExtraArgs() {
    return new Bundle();
  }
  
  protected String getLocalStartServiceAction() {
    return null;
  }
  
  public int getMinApkVersion() {
    return GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
  }
  
  public void getRemoteService(IAccountAccessor paramIAccountAccessor, Set<Scope> paramSet) {
    Bundle bundle = getGetServiceRequestExtraArgs();
    GetServiceRequest getServiceRequest = new GetServiceRequest(this.zzcm);
    getServiceRequest.zzy = this.mContext.getPackageName();
    getServiceRequest.zzdk = bundle;
    if (paramSet != null)
      getServiceRequest.zzdj = paramSet.<Scope>toArray(new Scope[paramSet.size()]); 
    if (requiresSignIn()) {
      Account account;
      if (getAccount() != null) {
        account = getAccount();
      } else {
        account = new Account("<<default account>>", "com.google");
      } 
      getServiceRequest.zzdl = account;
      if (paramIAccountAccessor != null)
        getServiceRequest.zzdi = paramIAccountAccessor.asBinder(); 
    } else if (requiresAccount()) {
      getServiceRequest.zzdl = getAccount();
    } 
    getServiceRequest.zzdm = zzbt;
    getServiceRequest.zzdn = getApiFeatures();
    try {
      synchronized (this.zzcd) {
        if (this.zzce != null) {
          IGmsServiceBroker iGmsServiceBroker = this.zzce;
          zzd zzd = new zzd();
          this(this, this.zzcr.get());
          iGmsServiceBroker.getService(zzd, getServiceRequest);
        } 
        return;
      } 
    } catch (DeadObjectException deadObjectException) {
      triggerConnectionSuspended(1);
      return;
    } catch (SecurityException securityException) {
      throw securityException;
    } catch (RemoteException|RuntimeException remoteException) {
      onPostInitHandler(8, null, null, this.zzcr.get());
      return;
    } 
  }
  
  protected Set<Scope> getScopes() {
    return Collections.EMPTY_SET;
  }
  
  public final T getService() throws DeadObjectException {
    synchronized (this.mLock) {
      if (this.zzcj != 5) {
        boolean bool;
        checkConnected();
        if (this.zzcg != null) {
          bool = true;
        } else {
          bool = false;
        } 
        Preconditions.checkState(bool, "Client is connected but service is null");
        return this.zzcg;
      } 
      DeadObjectException deadObjectException = new DeadObjectException();
      this();
      throw deadObjectException;
    } 
  }
  
  public IBinder getServiceBrokerBinder() {
    synchronized (this.zzcd) {
      if (this.zzce == null)
        return null; 
      return this.zzce.asBinder();
    } 
  }
  
  protected abstract String getServiceDescriptor();
  
  public Intent getSignInIntent() {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  protected abstract String getStartServiceAction();
  
  protected String getStartServicePackage() {
    return "com.google.android.gms";
  }
  
  public boolean isConnected() {
    synchronized (this.mLock) {
      boolean bool;
      if (this.zzcj == 4) {
        bool = true;
      } else {
        bool = false;
      } 
      return bool;
    } 
  }
  
  public boolean isConnecting() {
    synchronized (this.mLock) {
      if (this.zzcj == 2 || this.zzcj == 3)
        return true; 
      return false;
    } 
  }
  
  protected void onConnectedLocked(T paramT) {
    this.zzbw = System.currentTimeMillis();
  }
  
  protected void onConnectionFailed(ConnectionResult paramConnectionResult) {
    this.zzbx = paramConnectionResult.getErrorCode();
    this.zzby = System.currentTimeMillis();
  }
  
  protected void onConnectionSuspended(int paramInt) {
    this.zzbu = paramInt;
    this.zzbv = System.currentTimeMillis();
  }
  
  protected void onPostInitHandler(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(1, paramInt2, -1, new zzf(this, paramInt1, paramIBinder, paramBundle)));
  }
  
  void onSetConnectState(int paramInt, T paramT) {}
  
  public void onUserSignOut(SignOutCallbacks paramSignOutCallbacks) {
    paramSignOutCallbacks.onSignOutComplete();
  }
  
  public boolean providesSignIn() {
    return false;
  }
  
  public boolean requiresAccount() {
    return false;
  }
  
  public boolean requiresGooglePlayServices() {
    return true;
  }
  
  public boolean requiresSignIn() {
    return false;
  }
  
  public void triggerConnectionSuspended(int paramInt) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(6, this.zzcr.get(), paramInt));
  }
  
  protected void triggerNotAvailable(ConnectionProgressReportCallbacks paramConnectionProgressReportCallbacks, int paramInt, PendingIntent paramPendingIntent) {
    Preconditions.checkNotNull(paramConnectionProgressReportCallbacks, "Connection progress callbacks cannot be null.");
    this.zzcf = paramConnectionProgressReportCallbacks;
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(3, this.zzcr.get(), paramInt, paramPendingIntent));
  }
  
  protected final void zza(int paramInt1, Bundle paramBundle, int paramInt2) {
    Handler handler = this.mHandler;
    handler.sendMessage(handler.obtainMessage(7, paramInt2, -1, new zzg(this, paramInt1, null)));
  }
  
  public static interface BaseConnectionCallbacks {
    void onConnected(Bundle param1Bundle);
    
    void onConnectionSuspended(int param1Int);
  }
  
  public static interface BaseOnConnectionFailedListener {
    void onConnectionFailed(ConnectionResult param1ConnectionResult);
  }
  
  public static interface ConnectionProgressReportCallbacks {
    void onReportServiceBinding(ConnectionResult param1ConnectionResult);
  }
  
  protected class LegacyClientCallbackAdapter implements ConnectionProgressReportCallbacks {
    private final BaseGmsClient zzct;
    
    public LegacyClientCallbackAdapter(BaseGmsClient this$0) {}
    
    public void onReportServiceBinding(ConnectionResult param1ConnectionResult) {
      BaseGmsClient baseGmsClient;
      if (param1ConnectionResult.isSuccess()) {
        baseGmsClient = this.zzct;
        baseGmsClient.getRemoteService(null, baseGmsClient.getScopes());
        return;
      } 
      if (BaseGmsClient.zzg(this.zzct) != null)
        BaseGmsClient.zzg(this.zzct).onConnectionFailed((ConnectionResult)baseGmsClient); 
    }
  }
  
  public static interface SignOutCallbacks {
    void onSignOutComplete();
  }
  
  private abstract class zza extends zzc<Boolean> {
    private final int statusCode;
    
    private final Bundle zzcs;
    
    private final BaseGmsClient zzct;
    
    protected zza(BaseGmsClient this$0, int param1Int, Bundle param1Bundle) {
      super(this$0, Boolean.valueOf(true));
      this.statusCode = param1Int;
      this.zzcs = param1Bundle;
    }
    
    protected abstract void zza(ConnectionResult param1ConnectionResult);
    
    protected abstract boolean zzm();
    
    protected final void zzn() {}
  }
  
  final class zzb extends com.google.android.gms.internal.common.zze {
    private final BaseGmsClient zzct;
    
    public zzb(BaseGmsClient this$0, Looper param1Looper) {
      super(param1Looper);
    }
    
    private static void zza(Message param1Message) {
      BaseGmsClient.zzc zzc = (BaseGmsClient.zzc)param1Message.obj;
      zzc.zzn();
      zzc.unregister();
    }
    
    private static boolean zzb(Message param1Message) {
      int i = param1Message.what;
      return (i == 2 || i == 1 || i == 7);
    }
    
    public final void handleMessage(Message param1Message) {
      ConnectionResult connectionResult;
      if (this.zzct.zzcr.get() != param1Message.arg1) {
        if (zzb(param1Message))
          zza(param1Message); 
        return;
      } 
      int i = param1Message.what;
      if ((i == 1 || i == 7 || (i == 4 && !this.zzct.enableLocalFallback()) || param1Message.what == 5) && !this.zzct.isConnecting()) {
        zza(param1Message);
        return;
      } 
      i = param1Message.what;
      PendingIntent pendingIntent = null;
      if (i == 4) {
        BaseGmsClient.zza(this.zzct, new ConnectionResult(param1Message.arg2));
        if (BaseGmsClient.zzb(this.zzct) && !BaseGmsClient.zzc(this.zzct)) {
          BaseGmsClient.zza(this.zzct, 3, (IInterface)null);
          return;
        } 
        if (BaseGmsClient.zzd(this.zzct) != null) {
          connectionResult = BaseGmsClient.zzd(this.zzct);
        } else {
          connectionResult = new ConnectionResult(8);
        } 
        this.zzct.zzcf.onReportServiceBinding(connectionResult);
        this.zzct.onConnectionFailed(connectionResult);
        return;
      } 
      if (i == 5) {
        if (BaseGmsClient.zzd(this.zzct) != null) {
          connectionResult = BaseGmsClient.zzd(this.zzct);
        } else {
          connectionResult = new ConnectionResult(8);
        } 
        this.zzct.zzcf.onReportServiceBinding(connectionResult);
        this.zzct.onConnectionFailed(connectionResult);
        return;
      } 
      if (i == 3) {
        Object object = ((Message)connectionResult).obj;
        if (object instanceof PendingIntent)
          pendingIntent = (PendingIntent)object; 
        connectionResult = new ConnectionResult(((Message)connectionResult).arg2, pendingIntent);
        this.zzct.zzcf.onReportServiceBinding(connectionResult);
        this.zzct.onConnectionFailed(connectionResult);
        return;
      } 
      if (i == 6) {
        BaseGmsClient.zza(this.zzct, 5, (IInterface)null);
        if (BaseGmsClient.zze(this.zzct) != null)
          BaseGmsClient.zze(this.zzct).onConnectionSuspended(((Message)connectionResult).arg2); 
        this.zzct.onConnectionSuspended(((Message)connectionResult).arg2);
        BaseGmsClient.zza(this.zzct, 5, 1, null);
        return;
      } 
      if (i == 2 && !this.zzct.isConnected()) {
        zza((Message)connectionResult);
        return;
      } 
      if (zzb((Message)connectionResult)) {
        ((BaseGmsClient.zzc)((Message)connectionResult).obj).zzo();
        return;
      } 
      i = ((Message)connectionResult).what;
      StringBuilder stringBuilder = new StringBuilder(45);
      stringBuilder.append("Don't know how to handle message: ");
      stringBuilder.append(i);
      stringBuilder.toString();
      new Exception();
    }
  }
  
  protected abstract class zzc<TListener> {
    private final BaseGmsClient zzct;
    
    private TListener zzcu;
    
    private boolean zzcv;
    
    public zzc(BaseGmsClient this$0, TListener param1TListener) {
      this.zzcu = param1TListener;
      this.zzcv = false;
    }
    
    public final void removeListener() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: aconst_null
      //   4: putfield zzcu : Ljava/lang/Object;
      //   7: aload_0
      //   8: monitorexit
      //   9: return
      //   10: astore_1
      //   11: aload_0
      //   12: monitorexit
      //   13: aload_1
      //   14: athrow
      // Exception table:
      //   from	to	target	type
      //   2	9	10	finally
      //   11	13	10	finally
    }
    
    public final void unregister() {
      removeListener();
      synchronized (BaseGmsClient.zzf(this.zzct)) {
        BaseGmsClient.zzf(this.zzct).remove(this);
        return;
      } 
    }
    
    protected abstract void zza(TListener param1TListener);
    
    protected abstract void zzn();
    
    public final void zzo() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzcu : Ljava/lang/Object;
      //   6: astore #4
      //   8: aload_0
      //   9: getfield zzcv : Z
      //   12: ifeq -> 65
      //   15: aload_0
      //   16: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   19: astore_2
      //   20: aload_2
      //   21: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   24: invokevirtual length : ()I
      //   27: istore_1
      //   28: new java/lang/StringBuilder
      //   31: astore_3
      //   32: aload_3
      //   33: iload_1
      //   34: bipush #47
      //   36: iadd
      //   37: invokespecial <init> : (I)V
      //   40: aload_3
      //   41: ldc 'Callback proxy '
      //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   46: pop
      //   47: aload_3
      //   48: aload_2
      //   49: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: pop
      //   53: aload_3
      //   54: ldc ' being reused. This is not safe.'
      //   56: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   59: pop
      //   60: aload_3
      //   61: invokevirtual toString : ()Ljava/lang/String;
      //   64: pop
      //   65: aload_0
      //   66: monitorexit
      //   67: aload #4
      //   69: ifnull -> 88
      //   72: aload_0
      //   73: aload #4
      //   75: invokevirtual zza : (Ljava/lang/Object;)V
      //   78: goto -> 92
      //   81: astore_2
      //   82: aload_0
      //   83: invokevirtual zzn : ()V
      //   86: aload_2
      //   87: athrow
      //   88: aload_0
      //   89: invokevirtual zzn : ()V
      //   92: aload_0
      //   93: monitorenter
      //   94: aload_0
      //   95: iconst_1
      //   96: putfield zzcv : Z
      //   99: aload_0
      //   100: monitorexit
      //   101: aload_0
      //   102: invokevirtual unregister : ()V
      //   105: return
      //   106: astore_2
      //   107: aload_0
      //   108: monitorexit
      //   109: aload_2
      //   110: athrow
      //   111: astore_2
      //   112: aload_0
      //   113: monitorexit
      //   114: aload_2
      //   115: athrow
      // Exception table:
      //   from	to	target	type
      //   2	65	111	finally
      //   65	67	111	finally
      //   72	78	81	java/lang/RuntimeException
      //   94	101	106	finally
      //   107	109	106	finally
      //   112	114	111	finally
    }
  }
  
  public static final class zzd extends IGmsCallbacks.zza {
    private BaseGmsClient zzcw;
    
    private final int zzcx;
    
    public zzd(BaseGmsClient param1BaseGmsClient, int param1Int) {
      this.zzcw = param1BaseGmsClient;
      this.zzcx = param1Int;
    }
    
    public final void onPostInitComplete(int param1Int, IBinder param1IBinder, Bundle param1Bundle) {
      Preconditions.checkNotNull(this.zzcw, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzcw.onPostInitHandler(param1Int, param1IBinder, param1Bundle, this.zzcx);
      this.zzcw = null;
    }
    
    public final void zza(int param1Int, Bundle param1Bundle) {
      new Exception();
    }
    
    public final void zza(int param1Int, IBinder param1IBinder, zzb param1zzb) {
      Preconditions.checkNotNull(this.zzcw, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
      Preconditions.checkNotNull(param1zzb);
      BaseGmsClient.zza(this.zzcw, param1zzb);
      onPostInitComplete(param1Int, param1IBinder, param1zzb.zzda);
    }
  }
  
  public final class zze implements ServiceConnection {
    private final BaseGmsClient zzct;
    
    private final int zzcx;
    
    public zze(BaseGmsClient this$0, int param1Int) {
      this.zzcx = param1Int;
    }
    
    public final void onServiceConnected(ComponentName param1ComponentName, IBinder param1IBinder) {
      if (param1IBinder == null) {
        BaseGmsClient.zza(this.zzct, 16);
        return;
      } 
      synchronized (BaseGmsClient.zza(this.zzct)) {
        IInterface iInterface;
        BaseGmsClient baseGmsClient = this.zzct;
        if (param1IBinder == null) {
          param1ComponentName = null;
        } else {
          iInterface = param1IBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
          if (iInterface != null && iInterface instanceof IGmsServiceBroker) {
            iInterface = iInterface;
          } else {
            iInterface = new IGmsServiceBroker$Stub$zza(param1IBinder);
          } 
        } 
        BaseGmsClient.zza(baseGmsClient, (IGmsServiceBroker)iInterface);
        this.zzct.zza(0, (Bundle)null, this.zzcx);
        return;
      } 
    }
    
    public final void onServiceDisconnected(ComponentName param1ComponentName) {
      synchronized (BaseGmsClient.zza(this.zzct)) {
        BaseGmsClient.zza(this.zzct, (IGmsServiceBroker)null);
        null = this.zzct.mHandler;
        null.sendMessage(null.obtainMessage(6, this.zzcx, 1));
        return;
      } 
    }
  }
  
  protected final class zzf extends zza {
    private final BaseGmsClient zzct;
    
    private final IBinder zzcy;
    
    public zzf(BaseGmsClient this$0, int param1Int, IBinder param1IBinder, Bundle param1Bundle) {
      super(this$0, param1Int, param1Bundle);
      this.zzcy = param1IBinder;
    }
    
    protected final void zza(ConnectionResult param1ConnectionResult) {
      if (BaseGmsClient.zzg(this.zzct) != null)
        BaseGmsClient.zzg(this.zzct).onConnectionFailed(param1ConnectionResult); 
      this.zzct.onConnectionFailed(param1ConnectionResult);
    }
    
    protected final boolean zzm() {
      // Byte code:
      //   0: iconst_0
      //   1: istore_2
      //   2: aload_0
      //   3: getfield zzcy : Landroid/os/IBinder;
      //   6: invokeinterface getInterfaceDescriptor : ()Ljava/lang/String;
      //   11: astore_3
      //   12: aload_0
      //   13: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   16: invokevirtual getServiceDescriptor : ()Ljava/lang/String;
      //   19: aload_3
      //   20: invokevirtual equals : (Ljava/lang/Object;)Z
      //   23: ifne -> 102
      //   26: aload_0
      //   27: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   30: invokevirtual getServiceDescriptor : ()Ljava/lang/String;
      //   33: astore #5
      //   35: new java/lang/StringBuilder
      //   38: dup
      //   39: aload #5
      //   41: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   44: invokevirtual length : ()I
      //   47: bipush #34
      //   49: iadd
      //   50: aload_3
      //   51: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
      //   54: invokevirtual length : ()I
      //   57: iadd
      //   58: invokespecial <init> : (I)V
      //   61: astore #4
      //   63: aload #4
      //   65: ldc 'service descriptor mismatch: '
      //   67: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   70: pop
      //   71: aload #4
      //   73: aload #5
      //   75: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   78: pop
      //   79: aload #4
      //   81: ldc ' vs. '
      //   83: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   86: pop
      //   87: aload #4
      //   89: aload_3
      //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   93: pop
      //   94: aload #4
      //   96: invokevirtual toString : ()Ljava/lang/String;
      //   99: pop
      //   100: iconst_0
      //   101: ireturn
      //   102: aload_0
      //   103: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   106: aload_0
      //   107: getfield zzcy : Landroid/os/IBinder;
      //   110: invokevirtual createServiceInterface : (Landroid/os/IBinder;)Landroid/os/IInterface;
      //   113: astore_3
      //   114: iload_2
      //   115: istore_1
      //   116: aload_3
      //   117: ifnull -> 190
      //   120: aload_0
      //   121: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   124: iconst_2
      //   125: iconst_4
      //   126: aload_3
      //   127: invokestatic zza : (Lcom/google/android/gms/common/internal/BaseGmsClient;IILandroid/os/IInterface;)Z
      //   130: ifne -> 148
      //   133: iload_2
      //   134: istore_1
      //   135: aload_0
      //   136: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   139: iconst_3
      //   140: iconst_4
      //   141: aload_3
      //   142: invokestatic zza : (Lcom/google/android/gms/common/internal/BaseGmsClient;IILandroid/os/IInterface;)Z
      //   145: ifeq -> 190
      //   148: aload_0
      //   149: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   152: aconst_null
      //   153: invokestatic zza : (Lcom/google/android/gms/common/internal/BaseGmsClient;Lcom/google/android/gms/common/ConnectionResult;)Lcom/google/android/gms/common/ConnectionResult;
      //   156: pop
      //   157: aload_0
      //   158: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   161: invokevirtual getConnectionHint : ()Landroid/os/Bundle;
      //   164: astore_3
      //   165: aload_0
      //   166: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   169: invokestatic zze : (Lcom/google/android/gms/common/internal/BaseGmsClient;)Lcom/google/android/gms/common/internal/BaseGmsClient$BaseConnectionCallbacks;
      //   172: ifnull -> 188
      //   175: aload_0
      //   176: getfield zzct : Lcom/google/android/gms/common/internal/BaseGmsClient;
      //   179: invokestatic zze : (Lcom/google/android/gms/common/internal/BaseGmsClient;)Lcom/google/android/gms/common/internal/BaseGmsClient$BaseConnectionCallbacks;
      //   182: aload_3
      //   183: invokeinterface onConnected : (Landroid/os/Bundle;)V
      //   188: iconst_1
      //   189: istore_1
      //   190: iload_1
      //   191: ireturn
      //   192: astore_3
      //   193: iload_2
      //   194: istore_1
      //   195: goto -> 190
      // Exception table:
      //   from	to	target	type
      //   2	12	192	android/os/RemoteException
    }
  }
  
  protected final class zzg extends zza {
    private final BaseGmsClient zzct;
    
    public zzg(BaseGmsClient this$0, int param1Int, Bundle param1Bundle) {
      super(this$0, param1Int, null);
    }
    
    protected final void zza(ConnectionResult param1ConnectionResult) {
      if (this.zzct.enableLocalFallback() && BaseGmsClient.zzb(this.zzct)) {
        BaseGmsClient.zza(this.zzct, 16);
        return;
      } 
      this.zzct.zzcf.onReportServiceBinding(param1ConnectionResult);
      this.zzct.onConnectionFailed(param1ConnectionResult);
    }
    
    protected final boolean zzm() {
      this.zzct.zzcf.onReportServiceBinding(ConnectionResult.RESULT_SUCCESS);
      return true;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/BaseGmsClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */