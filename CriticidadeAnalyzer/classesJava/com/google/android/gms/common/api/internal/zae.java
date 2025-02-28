package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zae<A extends BaseImplementation$ApiMethodImpl<? extends Result, Api.AnyClient>> extends zab {
  private final A zaco;
  
  public zae(int paramInt, A paramA) {
    super(paramInt);
    this.zaco = paramA;
  }
  
  public final void zaa(Status paramStatus) {
    this.zaco.setFailedResult(paramStatus);
  }
  
  public final void zaa(GoogleApiManager.zaa<?> paramzaa) throws DeadObjectException {
    try {
      this.zaco.run(paramzaa.zaab());
      return;
    } catch (RuntimeException runtimeException) {
      zaa(runtimeException);
      return;
    } 
  }
  
  public final void zaa(zaab paramzaab, boolean paramBoolean) {
    paramzaab.zaa((BasePendingResult<? extends Result>)this.zaco, paramBoolean);
  }
  
  public final void zaa(RuntimeException paramRuntimeException) {
    String str2 = paramRuntimeException.getClass().getSimpleName();
    String str1 = paramRuntimeException.getLocalizedMessage();
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 2 + String.valueOf(str1).length());
    stringBuilder.append(str2);
    stringBuilder.append(": ");
    stringBuilder.append(str1);
    Status status = new Status(10, stringBuilder.toString());
    this.zaco.setFailedResult(status);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zae.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */