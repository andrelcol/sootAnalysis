package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.TaskCompletionSource;

public abstract class RegisterListenerMethod<A extends Api.AnyClient, L> {
  public abstract void clearListener();
  
  public abstract Feature[] getRequiredFeatures();
  
  protected abstract void registerListener(A paramA, TaskCompletionSource<Void> paramTaskCompletionSource) throws RemoteException;
  
  public final boolean shouldAutoResolveMissingFeatures() {
    throw null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/RegisterListenerMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */