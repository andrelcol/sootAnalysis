package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import com.google.android.gms.common.api.zac;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

final class zacr implements IBinder.DeathRecipient, zacs {
  private final WeakReference<BasePendingResult<?>> zalc;
  
  private final WeakReference<zac> zald;
  
  private final WeakReference<IBinder> zale;
  
  private zacr(BasePendingResult<?> paramBasePendingResult, zac paramzac, IBinder paramIBinder) {
    this.zald = new WeakReference<zac>(paramzac);
    this.zalc = new WeakReference<BasePendingResult<?>>(paramBasePendingResult);
    this.zale = new WeakReference<IBinder>(paramIBinder);
  }
  
  private final void zaby() {
    BasePendingResult basePendingResult = this.zalc.get();
    zac zac = this.zald.get();
    if (zac != null && basePendingResult != null)
      zac.remove(basePendingResult.zam().intValue()); 
    IBinder iBinder = this.zale.get();
    if (iBinder != null)
      try {
        iBinder.unlinkToDeath(this, 0);
      } catch (NoSuchElementException noSuchElementException) {} 
  }
  
  public final void binderDied() {
    zaby();
  }
  
  public final void zac(BasePendingResult<?> paramBasePendingResult) {
    zaby();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zacr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */