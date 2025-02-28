package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.google.android.gms.common.util.ClientLibraryUtils;
import java.util.Collections;
import java.util.List;

public class ConnectionTracker {
  private static final Object zzdp = new Object();
  
  private static volatile ConnectionTracker zzfa;
  
  private ConnectionTracker() {
    List list = Collections.EMPTY_LIST;
  }
  
  public static ConnectionTracker getInstance() {
    if (zzfa == null)
      synchronized (zzdp) {
        if (zzfa == null) {
          ConnectionTracker connectionTracker = new ConnectionTracker();
          this();
          zzfa = connectionTracker;
        } 
      }  
    return zzfa;
  }
  
  public boolean bindService(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  @SuppressLint({"UntrackedBindService"})
  public void unbindService(Context paramContext, ServiceConnection paramServiceConnection) {
    paramContext.unbindService(paramServiceConnection);
  }
  
  public final boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt) {
    boolean bool;
    ComponentName componentName = paramIntent.getComponent();
    if (componentName == null) {
      bool = false;
    } else {
      bool = ClientLibraryUtils.zzc(paramContext, componentName.getPackageName());
    } 
    return bool ? false : paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/stats/ConnectionTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */