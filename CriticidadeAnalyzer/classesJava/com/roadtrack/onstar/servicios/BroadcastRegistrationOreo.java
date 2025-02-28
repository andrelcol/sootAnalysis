package com.roadtrack.onstar.servicios;

import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gcm.GCMBroadcastReceiver;

public class BroadcastRegistrationOreo extends WakefulBroadcastReceiver {
  private static boolean mReceiverSet = false;
  
  static final String getDefaultIntentServiceClassName(Context paramContext) {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(paramContext.getPackageName());
    stringBuilder.append(".GCMIntentService");
    return stringBuilder.toString();
  }
  
  protected String getGCMIntentServiceClassName(Context paramContext) {
    return getDefaultIntentServiceClassName(paramContext);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent) {
    if (!mReceiverSet) {
      mReceiverSet = true;
      String str = BroadcastRegistrationOreo.class.getName();
      if (!str.equals(GCMBroadcastReceiver.class.getName()))
        GCMRegisterOreo.setRetryReceiverClassName(str); 
    } 
    GCMBaseRegisterOreo.runIntentInService(paramContext, paramIntent, getGCMIntentServiceClassName(paramContext));
    setResult(-1, null, null);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/BroadcastRegistrationOreo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */