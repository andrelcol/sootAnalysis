package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.components.Component;
import com.google.firebase.components.zzf;
import com.google.firebase.events.Publisher;
import com.google.firebase.internal.zza;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
  static final Map<String, FirebaseApp> zza;
  
  private static final List<String> zzb = Arrays.asList(new String[] { "com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId" });
  
  private static final List<String> zzc = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
  
  private static final List<String> zzd = Arrays.asList(new String[] { "com.google.android.gms.measurement.AppMeasurement" });
  
  private static final List<String> zze = Arrays.asList(new String[0]);
  
  private static final Set<String> zzf = Collections.emptySet();
  
  private static final Object zzg = new Object();
  
  private static final Executor zzh = new zzb((byte)0);
  
  private final Context zzi;
  
  private final String zzj;
  
  private final FirebaseOptions zzk;
  
  private final zzf zzl;
  
  private final SharedPreferences zzm;
  
  private final AtomicBoolean zzo = new AtomicBoolean(false);
  
  private final AtomicBoolean zzp = new AtomicBoolean();
  
  private final AtomicBoolean zzq;
  
  private final List<BackgroundStateChangeListener> zzs;
  
  static {
    zza = (Map<String, FirebaseApp>)new ArrayMap();
  }
  
  private FirebaseApp(Context paramContext, String paramString, FirebaseOptions paramFirebaseOptions) {
    new CopyOnWriteArrayList();
    this.zzs = new CopyOnWriteArrayList<BackgroundStateChangeListener>();
    new CopyOnWriteArrayList();
    Preconditions.checkNotNull(paramContext);
    this.zzi = paramContext;
    Preconditions.checkNotEmpty(paramString);
    this.zzj = paramString;
    Preconditions.checkNotNull(paramFirebaseOptions);
    this.zzk = paramFirebaseOptions;
    new zza();
    this.zzm = paramContext.getSharedPreferences("com.google.firebase.common.prefs", 0);
    this.zzq = new AtomicBoolean(zzb());
    List list = Component.null.zza(paramContext).zza();
    this.zzl = new zzf(zzh, list, new Component[] { Component.of(paramContext, Context.class, new Class[0]), Component.of(this, FirebaseApp.class, new Class[0]), Component.of(paramFirebaseOptions, FirebaseOptions.class, new Class[0]) });
    Publisher publisher = (Publisher)this.zzl.get(Publisher.class);
  }
  
  public static FirebaseApp getInstance() {
    synchronized (zzg) {
      FirebaseApp firebaseApp = zza.get("[DEFAULT]");
      if (firebaseApp != null)
        return firebaseApp; 
      IllegalStateException illegalStateException = new IllegalStateException();
      StringBuilder stringBuilder = new StringBuilder();
      this("Default FirebaseApp is not initialized in this process ");
      stringBuilder.append(ProcessUtils.getMyProcessName());
      stringBuilder.append(". Make sure to call FirebaseApp.initializeApp(Context) first.");
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
  
  public static FirebaseApp initializeApp(Context paramContext) {
    synchronized (zzg) {
      if (zza.containsKey("[DEFAULT]")) {
        firebaseApp = getInstance();
        return firebaseApp;
      } 
      FirebaseOptions firebaseOptions = FirebaseOptions.fromResource((Context)firebaseApp);
      if (firebaseOptions == null)
        return null; 
      FirebaseApp firebaseApp = initializeApp((Context)firebaseApp, firebaseOptions);
      return firebaseApp;
    } 
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions) {
    return initializeApp(paramContext, paramFirebaseOptions, "[DEFAULT]");
  }
  
  public static FirebaseApp initializeApp(Context paramContext, FirebaseOptions paramFirebaseOptions, String paramString) {
    zza.zza(paramContext);
    paramString = paramString.trim();
    if (paramContext.getApplicationContext() != null)
      paramContext = paramContext.getApplicationContext(); 
    synchronized (zzg) {
      boolean bool;
      if (!zza.containsKey(paramString)) {
        bool = true;
      } else {
        bool = false;
      } 
      StringBuilder stringBuilder = new StringBuilder();
      this("FirebaseApp name ");
      stringBuilder.append(paramString);
      stringBuilder.append(" already exists!");
      Preconditions.checkState(bool, stringBuilder.toString());
      Preconditions.checkNotNull(paramContext, "Application context cannot be null.");
      FirebaseApp firebaseApp = new FirebaseApp();
      this(paramContext, paramString, paramFirebaseOptions);
      zza.put(paramString, firebaseApp);
      firebaseApp.zze();
      return firebaseApp;
    } 
  }
  
  private static <T> void zza(Class<T> paramClass, T paramT, Iterable<String> paramIterable, boolean paramBoolean) {
    Iterator<String> iterator = paramIterable.iterator();
    while (true) {
      while (true)
        break; 
      if (Modifier.isPublic(SYNTHETIC_LOCAL_VARIABLE_4) && Modifier.isStatic(SYNTHETIC_LOCAL_VARIABLE_4))
        SYNTHETIC_LOCAL_VARIABLE_6.invoke(null, new Object[] { paramT }); 
    } 
  }
  
  private void zza(boolean paramBoolean) {
    Iterator<BackgroundStateChangeListener> iterator = this.zzs.iterator();
    while (iterator.hasNext())
      ((BackgroundStateChangeListener)iterator.next()).onBackgroundStateChanged(paramBoolean); 
  }
  
  private boolean zzb() {
    if (this.zzm.contains("firebase_data_collection_default_enabled"))
      return this.zzm.getBoolean("firebase_data_collection_default_enabled", true); 
    try {
      PackageManager packageManager = this.zzi.getPackageManager();
      if (packageManager != null) {
        ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.zzi.getPackageName(), 128);
        if (applicationInfo != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("firebase_data_collection_default_enabled"))
          return applicationInfo.metaData.getBoolean("firebase_data_collection_default_enabled"); 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return true;
  }
  
  private void zzc() {
    Preconditions.checkState(this.zzp.get() ^ true, "FirebaseApp was deleted");
  }
  
  private void zze() {
    boolean bool = ContextCompat.isDeviceProtectedStorage(this.zzi);
    if (bool) {
      zzc.zza(this.zzi);
    } else {
      this.zzl.zza(isDefaultApp());
    } 
    zza(FirebaseApp.class, this, zzb, bool);
    if (isDefaultApp()) {
      zza(FirebaseApp.class, this, zzc, bool);
      zza(Context.class, this.zzi, zzd, bool);
    } 
  }
  
  public boolean equals(Object paramObject) {
    return !(paramObject instanceof FirebaseApp) ? false : this.zzj.equals(((FirebaseApp)paramObject).getName());
  }
  
  public <T> T get(Class<T> paramClass) {
    zzc();
    return (T)this.zzl.get(paramClass);
  }
  
  public Context getApplicationContext() {
    zzc();
    return this.zzi;
  }
  
  public String getName() {
    zzc();
    return this.zzj;
  }
  
  public FirebaseOptions getOptions() {
    zzc();
    return this.zzk;
  }
  
  public int hashCode() {
    return this.zzj.hashCode();
  }
  
  public boolean isDataCollectionDefaultEnabled() {
    zzc();
    return this.zzq.get();
  }
  
  public boolean isDefaultApp() {
    return "[DEFAULT]".equals(getName());
  }
  
  public String toString() {
    Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
    toStringHelper.add("name", this.zzj);
    toStringHelper.add("options", this.zzk);
    return toStringHelper.toString();
  }
  
  public static interface BackgroundStateChangeListener {
    void onBackgroundStateChanged(boolean param1Boolean);
  }
  
  @Deprecated
  public static interface IdTokenListenersCountChangedListener {}
  
  @TargetApi(14)
  static final class zza implements BackgroundDetector.BackgroundStateChangeListener {
    private static AtomicReference<zza> zza = new AtomicReference<zza>();
    
    public final void onBackgroundStateChanged(boolean param1Boolean) {
      synchronized (FirebaseApp.zza()) {
        ArrayList arrayList = new ArrayList();
        this((Collection)FirebaseApp.zza.values());
        for (FirebaseApp firebaseApp : arrayList) {
          if (FirebaseApp.zzb(firebaseApp).get())
            FirebaseApp.zza(firebaseApp, param1Boolean); 
        } 
        return;
      } 
    }
  }
  
  static final class zzb implements Executor {
    private static final Handler zza = new Handler(Looper.getMainLooper());
    
    private zzb() {}
    
    public final void execute(Runnable param1Runnable) {
      zza.post(param1Runnable);
    }
  }
  
  @TargetApi(24)
  static final class zzc extends BroadcastReceiver {
    private static AtomicReference<zzc> zza = new AtomicReference<zzc>();
    
    private final Context zzb;
    
    private zzc(Context param1Context) {
      this.zzb = param1Context;
    }
    
    public final void onReceive(Context param1Context, Intent param1Intent) {
      synchronized (FirebaseApp.zza()) {
        Iterator<FirebaseApp> iterator = FirebaseApp.zza.values().iterator();
        while (iterator.hasNext())
          FirebaseApp.zza(iterator.next()); 
        this.zzb.unregisterReceiver(this);
        return;
      } 
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/FirebaseApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */