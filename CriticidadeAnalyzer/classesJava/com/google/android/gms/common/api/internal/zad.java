package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zad<T> extends zac {
  protected final TaskCompletionSource<T> zacn;
  
  public zad(int paramInt, TaskCompletionSource<T> paramTaskCompletionSource) {
    super(paramInt);
    this.zacn = paramTaskCompletionSource;
  }
  
  public void zaa(Status paramStatus) {
    this.zacn.trySetException((Exception)new ApiException(paramStatus));
  }
  
  public final void zaa(GoogleApiManager.zaa<?> paramzaa) throws DeadObjectException {
    try {
      zad(paramzaa);
      return;
    } catch (DeadObjectException deadObjectException) {
      zaa(zab.zab((RemoteException)deadObjectException));
      throw deadObjectException;
    } catch (RemoteException remoteException) {
      zaa(zab.zab(remoteException));
      return;
    } catch (RuntimeException runtimeException) {
      zaa(runtimeException);
      return;
    } 
  }
  
  public void zaa(RuntimeException paramRuntimeException) {
    this.zacn.trySetException(paramRuntimeException);
  }
  
  protected abstract void zad(GoogleApiManager.zaa<?> paramzaa) throws RemoteException;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */