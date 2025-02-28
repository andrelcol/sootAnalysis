package com.roadtrack.onstar.servicios;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.legacy.content.WakefulBroadcastReceiver;

public class BroadcastGCMOreo extends WakefulBroadcastReceiver {
  public void onReceive(Context paramContext, Intent paramIntent) {
    ComponentName componentName = new ComponentName(paramContext.getPackageName(), ServiceGCMOreo.class.getName());
    (new Intent(paramContext, ServiceGCMOreo.class)).putExtras(paramIntent.getExtras());
    ServiceGCMOreo.enqueueWork(paramContext, paramIntent.setComponent(componentName));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/BroadcastGCMOreo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */