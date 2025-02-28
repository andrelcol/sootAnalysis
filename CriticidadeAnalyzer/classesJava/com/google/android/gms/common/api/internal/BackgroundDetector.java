package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
  private static final BackgroundDetector zzat = new BackgroundDetector();
  
  private final AtomicBoolean zzau = new AtomicBoolean();
  
  private final AtomicBoolean zzav = new AtomicBoolean();
  
  private final ArrayList<BackgroundStateChangeListener> zzaw = new ArrayList<BackgroundStateChangeListener>();
  
  private boolean zzax = false;
  
  public static BackgroundDetector getInstance() {
    return zzat;
  }
  
  public static void initialize(Application paramApplication) {
    synchronized (zzat) {
      if (!zzat.zzax) {
        paramApplication.registerActivityLifecycleCallbacks(zzat);
        paramApplication.registerComponentCallbacks((ComponentCallbacks)zzat);
        zzat.zzax = true;
      } 
      return;
    } 
  }
  
  private final void onBackgroundStateChanged(boolean paramBoolean) {
    synchronized (zzat) {
      ArrayList<BackgroundStateChangeListener> arrayList = this.zzaw;
      int i = arrayList.size();
      byte b = 0;
      while (b < i) {
        BackgroundStateChangeListener backgroundStateChangeListener = (BackgroundStateChangeListener)arrayList.get(b);
        b++;
        ((BackgroundStateChangeListener)backgroundStateChangeListener).onBackgroundStateChanged(paramBoolean);
      } 
      return;
    } 
  }
  
  public final void addListener(BackgroundStateChangeListener paramBackgroundStateChangeListener) {
    synchronized (zzat) {
      this.zzaw.add(paramBackgroundStateChangeListener);
      return;
    } 
  }
  
  public final boolean isInBackground() {
    return this.zzau.get();
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    boolean bool = this.zzau.compareAndSet(true, false);
    this.zzav.set(true);
    if (bool)
      onBackgroundStateChanged(false); 
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {}
  
  public final void onActivityPaused(Activity paramActivity) {}
  
  public final void onActivityResumed(Activity paramActivity) {
    boolean bool = this.zzau.compareAndSet(true, false);
    this.zzav.set(true);
    if (bool)
      onBackgroundStateChanged(false); 
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
  
  public final void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public final void onLowMemory() {}
  
  public final void onTrimMemory(int paramInt) {
    if (paramInt == 20 && this.zzau.compareAndSet(false, true)) {
      this.zzav.set(true);
      onBackgroundStateChanged(true);
    } 
  }
  
  @TargetApi(16)
  public final boolean readCurrentStateIfPossible(boolean paramBoolean) {
    if (!this.zzav.get())
      if (PlatformVersion.isAtLeastJellyBean()) {
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(runningAppProcessInfo);
        if (!this.zzav.getAndSet(true) && runningAppProcessInfo.importance > 100)
          this.zzau.set(true); 
      } else {
        return paramBoolean;
      }  
    return isInBackground();
  }
  
  public static interface BackgroundStateChangeListener {
    void onBackgroundStateChanged(boolean param1Boolean);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/BackgroundDetector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */