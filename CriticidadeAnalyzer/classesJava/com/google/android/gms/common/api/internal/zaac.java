package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

final class zaac implements PendingResult.StatusListener {
  private final BasePendingResult zafm;
  
  private final zaab zafn;
  
  zaac(zaab paramzaab, BasePendingResult paramBasePendingResult) {}
  
  public final void onComplete(Status paramStatus) {
    zaab.zaa(this.zafn).remove(this.zafm);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */