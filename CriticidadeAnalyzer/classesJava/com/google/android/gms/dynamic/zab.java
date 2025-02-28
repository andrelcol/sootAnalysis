package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zab implements DeferredLifecycleHelper.zaa {
  private final Activity val$activity;
  
  private final DeferredLifecycleHelper zarj;
  
  private final Bundle zark;
  
  private final Bundle zarl;
  
  zab(DeferredLifecycleHelper paramDeferredLifecycleHelper, Activity paramActivity, Bundle paramBundle1) {}
  
  public final int getState() {
    return 0;
  }
  
  public final void zaa(LifecycleDelegate paramLifecycleDelegate) {
    DeferredLifecycleHelper.zab(this.zarj).onInflate(activity, this.zark, this.zarl);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/zab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */