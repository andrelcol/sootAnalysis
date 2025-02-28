package com.google.android.gms.common.api.internal;

final class zabi implements BackgroundDetector.BackgroundStateChangeListener {
  private final GoogleApiManager zaim;
  
  zabi(GoogleApiManager paramGoogleApiManager) {}
  
  public final void onBackgroundStateChanged(boolean paramBoolean) {
    GoogleApiManager.zaa(this.zaim).sendMessage(GoogleApiManager.zaa(this.zaim).obtainMessage(1, Boolean.valueOf(paramBoolean)));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/internal/zabi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */