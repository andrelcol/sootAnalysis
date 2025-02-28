package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zabc extends zabr {
  private WeakReference<zaaw> zahm;
  
  zabc(zaaw paramzaaw) {
    this.zahm = new WeakReference<zaaw>(paramzaaw);
  }
  
  public final void zas() {
    zaaw zaaw = this.zahm.get();
    if (zaaw == null)
      return; 
    zaaw.zaa(zaaw);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */