package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zac implements DeferredLifecycleHelper.zaa {
  private final DeferredLifecycleHelper zarj;
  
  private final Bundle zarl;
  
  zac(DeferredLifecycleHelper paramDeferredLifecycleHelper, Bundle paramBundle) {}
  
  public final int getState() {
    return 1;
  }
  
  public final void zaa(LifecycleDelegate paramLifecycleDelegate) {
    DeferredLifecycleHelper.zab(this.zarj).onCreate(this.zarl);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/zac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */