package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

final class zacn implements Runnable {
  private final Result zakv;
  
  private final zacm zakw;
  
  zacn(zacm paramzacm, Result paramResult) {}
  
  public final void run() {
    Exception exception;
    try {
      BasePendingResult.zadn.set(Boolean.valueOf(true));
      PendingResult pendingResult = zacm.zac(this.zakw).onSuccess(this.zakv);
      zacm.zad(this.zakw).sendMessage(zacm.zad(this.zakw).obtainMessage(0, pendingResult));
      BasePendingResult.zadn.set(Boolean.valueOf(false));
      zacm.zaa(this.zakw, this.zakv);
      GoogleApiClient googleApiClient1 = zacm.zae(this.zakw).get();
      if (googleApiClient1 != null)
        googleApiClient1.zab(this.zakw); 
      return;
    } catch (RuntimeException runtimeException) {
      zacm.zad(this.zakw).sendMessage(zacm.zad(this.zakw).obtainMessage(1, runtimeException));
      BasePendingResult.zadn.set(Boolean.valueOf(false));
      zacm.zaa(this.zakw, this.zakv);
      GoogleApiClient googleApiClient1 = zacm.zae(this.zakw).get();
      if (googleApiClient1 != null)
        googleApiClient1.zab(this.zakw); 
      return;
    } finally {}
    BasePendingResult.zadn.set(Boolean.valueOf(false));
    zacm.zaa(this.zakw, this.zakv);
    GoogleApiClient googleApiClient = zacm.zae(this.zakw).get();
    if (googleApiClient != null)
      googleApiClient.zab(this.zakw); 
    throw exception;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zacn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */