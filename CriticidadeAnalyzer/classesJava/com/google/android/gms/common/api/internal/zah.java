package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zah extends zad<Boolean> {
  private final ListenerHolder.ListenerKey<?> zact;
  
  public zah(ListenerHolder.ListenerKey<?> paramListenerKey, TaskCompletionSource<Boolean> paramTaskCompletionSource) {
    super(4, paramTaskCompletionSource);
    this.zact = paramListenerKey;
  }
  
  public final Feature[] zab(GoogleApiManager.zaa<?> paramzaa) {
    zabw zabw = paramzaa.zabk().get(this.zact);
    return (zabw == null) ? null : zabw.zajx.getRequiredFeatures();
  }
  
  public final boolean zac(GoogleApiManager.zaa<?> paramzaa) {
    zabw zabw = paramzaa.zabk().get(this.zact);
    return (zabw != null && zabw.zajx.shouldAutoResolveMissingFeatures());
  }
  
  public final void zad(GoogleApiManager.zaa<?> paramzaa) throws RemoteException {
    zabw zabw = paramzaa.zabk().remove(this.zact);
    if (zabw != null) {
      zabw.zajy.unregisterListener(paramzaa.zaab(), (TaskCompletionSource)this.zacn);
      zabw.zajx.clearListener();
      return;
    } 
    this.zacn.trySetResult(Boolean.valueOf(false));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */