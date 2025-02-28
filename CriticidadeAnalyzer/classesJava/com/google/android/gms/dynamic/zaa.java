package com.google.android.gms.dynamic;

import android.os.Bundle;
import java.util.Iterator;

final class zaa implements OnDelegateCreatedListener<T> {
  private final DeferredLifecycleHelper zarj;
  
  zaa(DeferredLifecycleHelper paramDeferredLifecycleHelper) {}
  
  public final void onDelegateCreated(T paramT) {
    DeferredLifecycleHelper.zaa(this.zarj, (LifecycleDelegate)paramT);
    Iterator<DeferredLifecycleHelper.zaa> iterator = DeferredLifecycleHelper.zaa(this.zarj).iterator();
    while (iterator.hasNext())
      ((DeferredLifecycleHelper.zaa)iterator.next()).zaa(DeferredLifecycleHelper.zab(this.zarj)); 
    DeferredLifecycleHelper.zaa(this.zarj).clear();
    DeferredLifecycleHelper.zaa(this.zarj, (Bundle)null);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamic/zaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */