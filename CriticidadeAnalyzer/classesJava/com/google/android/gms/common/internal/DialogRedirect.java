package com.google.android.gms.common.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;

public abstract class DialogRedirect implements DialogInterface.OnClickListener {
  public static DialogRedirect getInstance(Activity paramActivity, Intent paramIntent, int paramInt) {
    return new zac(paramIntent, paramActivity, paramInt);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt) {
    try {
      redirect();
      return;
    } catch (ActivityNotFoundException activityNotFoundException) {
      return;
    } finally {
      paramDialogInterface.dismiss();
    } 
  }
  
  protected abstract void redirect();
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/DialogRedirect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */