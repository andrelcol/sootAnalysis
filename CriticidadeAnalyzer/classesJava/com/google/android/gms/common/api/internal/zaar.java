package com.google.android.gms.common.api.internal;

import com.google.android.gms.signin.internal.zac;
import com.google.android.gms.signin.internal.zaj;
import java.lang.ref.WeakReference;

final class zaar extends zac {
  private final WeakReference<zaak> zagk;
  
  zaar(zaak paramzaak) {
    this.zagk = new WeakReference<zaak>(paramzaak);
  }
  
  public final void zab(zaj paramzaj) {
    zaak zaak = this.zagk.get();
    if (zaak == null)
      return; 
    zaak.zad(zaak).zaa(new zaas(this, zaak, zaak, paramzaj));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zaar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */