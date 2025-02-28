package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

final class zav implements zabt {
  private final zas zaeq;
  
  private zav(zas paramzas) {}
  
  public final void zab(int paramInt, boolean paramBoolean) {
    zas.zaa(this.zaeq).lock();
    try {
      if (zas.zac(this.zaeq)) {
        zas.zaa(this.zaeq, false);
        zas.zaa(this.zaeq, paramInt, paramBoolean);
        return;
      } 
      zas.zaa(this.zaeq, true);
      zas.zaf(this.zaeq).onConnectionSuspended(paramInt);
      return;
    } finally {
      zas.zaa(this.zaeq).unlock();
    } 
  }
  
  public final void zab(Bundle paramBundle) {
    zas.zaa(this.zaeq).lock();
    try {
      zas.zab(this.zaeq, ConnectionResult.RESULT_SUCCESS);
      zas.zab(this.zaeq);
      return;
    } finally {
      zas.zaa(this.zaeq).unlock();
    } 
  }
  
  public final void zac(ConnectionResult paramConnectionResult) {
    zas.zaa(this.zaeq).lock();
    try {
      zas.zab(this.zaeq, paramConnectionResult);
      zas.zab(this.zaeq);
      return;
    } finally {
      zas.zaa(this.zaeq).unlock();
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */