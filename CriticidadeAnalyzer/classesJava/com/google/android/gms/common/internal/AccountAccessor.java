package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;

public class AccountAccessor extends IAccountAccessor.Stub {
  public static Account getAccountBinderSafe(IAccountAccessor paramIAccountAccessor) {
    if (paramIAccountAccessor != null) {
      long l = Binder.clearCallingIdentity();
      try {
        Account account = paramIAccountAccessor.getAccount();
      } catch (RemoteException remoteException) {
        Binder.restoreCallingIdentity(l);
      } finally {
        Binder.restoreCallingIdentity(l);
      } 
      return (Account)paramIAccountAccessor;
    } 
    paramIAccountAccessor = null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/AccountAccessor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */