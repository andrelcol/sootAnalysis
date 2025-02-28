package com.google.firebase.components;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class zzd implements zze<Context> {
  private zzd() {}
  
  private static Bundle zza(Context paramContext) {
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      if (packageManager == null)
        return null; 
      ComponentName componentName = new ComponentName();
      this(paramContext, ComponentDiscoveryService.class);
      ServiceInfo serviceInfo = packageManager.getServiceInfo(componentName, 128);
      return (serviceInfo == null) ? null : serviceInfo.metaData;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */