package com.google.android.gms.dynamic;

final class zaf implements DeferredLifecycleHelper.zaa {
  private final DeferredLifecycleHelper zarj;
  
  zaf(DeferredLifecycleHelper paramDeferredLifecycleHelper) {}
  
  public final int getState() {
    return 4;
  }
  
  public final void zaa(LifecycleDelegate paramLifecycleDelegate) {
    DeferredLifecycleHelper.zab(this.zarj).onStart();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/zaf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */