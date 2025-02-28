package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.BaseGmsClient;

final class zabm implements BaseGmsClient.SignOutCallbacks {
  final GoogleApiManager.zaa zaiy;
  
  zabm(GoogleApiManager.zaa paramzaa) {}
  
  public final void onSignOutComplete() {
    GoogleApiManager.zaa(this.zaiy.zaim).post(new zabn(this));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */