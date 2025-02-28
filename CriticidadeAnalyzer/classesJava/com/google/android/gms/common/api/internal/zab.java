package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.PlatformVersion;

public abstract class zab {
  public zab(int paramInt) {}
  
  private static Status zaa(RemoteException paramRemoteException) {
    StringBuilder stringBuilder = new StringBuilder();
    if (PlatformVersion.isAtLeastIceCreamSandwichMR1() && paramRemoteException instanceof android.os.TransactionTooLargeException)
      stringBuilder.append("TransactionTooLargeException: "); 
    stringBuilder.append(paramRemoteException.getLocalizedMessage());
    return new Status(8, stringBuilder.toString());
  }
  
  public abstract void zaa(Status paramStatus);
  
  public abstract void zaa(GoogleApiManager.zaa<?> paramzaa) throws DeadObjectException;
  
  public abstract void zaa(zaab paramzaab, boolean paramBoolean);
  
  public abstract void zaa(RuntimeException paramRuntimeException);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */