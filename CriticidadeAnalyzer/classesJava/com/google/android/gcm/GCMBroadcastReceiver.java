package com.google.android.gcm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GCMBroadcastReceiver extends BroadcastReceiver {
  private static boolean mReceiverSet = false;
  
  static final String getDefaultIntentServiceClassName(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramContext.getPackageName()));
    stringBuilder.append(".GCMIntentService");
    return stringBuilder.toString();
  }
  
  protected String getGCMIntentServiceClassName(Context paramContext) {
    return getDefaultIntentServiceClassName(paramContext);
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    if (!mReceiverSet) {
      mReceiverSet = true;
      String str = GCMBroadcastReceiver.class.getName();
      if (!str.equals(GCMBroadcastReceiver.class.getName()))
        GCMRegistrar.setRetryReceiverClassName(str); 
    } 
    GCMBaseIntentService.runIntentInService(paramContext, paramIntent, getGCMIntentServiceClassName(paramContext));
    setResult(-1, null, null);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gcm/GCMBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */