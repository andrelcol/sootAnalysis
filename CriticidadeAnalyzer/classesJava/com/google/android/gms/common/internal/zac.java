package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.Intent;

final class zac extends DialogRedirect {
  private final Activity val$activity;
  
  private final int val$requestCode;
  
  private final Intent zaoh;
  
  zac(Intent paramIntent) {}
  
  public final void redirect() {
    Intent intent = this.zaoh;
    if (intent != null)
      activity.startActivityForResult(intent, requestCode); 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */