package com.google.android.gms.internal.measurement;

import android.database.ContentObserver;
import android.os.Handler;

final class zzrv extends ContentObserver {
  zzrv(Handler paramHandler) {
    super(null);
  }
  
  public final void onChange(boolean paramBoolean) {
    zzru.zzti().set(true);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzrv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */