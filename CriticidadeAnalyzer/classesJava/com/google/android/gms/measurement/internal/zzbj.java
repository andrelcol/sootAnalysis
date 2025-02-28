package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzu;
import java.util.List;

public final class zzbj {
  final zzbw zzada;
  
  zzbj(zzbw paramzzbw) {
    this.zzada = paramzzbw;
  }
  
  private final boolean zzke() {
    boolean bool = false;
    try {
      PackageManagerWrapper packageManagerWrapper = Wrappers.packageManager(this.zzada.getContext());
      if (packageManagerWrapper == null) {
        this.zzada.zzgt().zzjm().zzby("Failed to retrieve Package Manager to check Play Store compatibility");
        return false;
      } 
      int i = (packageManagerWrapper.getPackageInfo("com.android.vending", 128)).versionCode;
      if (i >= 80837300)
        bool = true; 
      return bool;
    } catch (Exception exception) {
      this.zzada.zzgt().zzjm().zzg("Failed to retrieve Play Store version", exception);
      return false;
    } 
  }
  
  final Bundle zza(String paramString, zzu paramzzu) {
    this.zzada.zzgs().zzaf();
    if (paramzzu == null) {
      this.zzada.zzgt().zzjj().zzby("Attempting to use Install Referrer Service while it is not initialized");
      return null;
    } 
    Bundle bundle = new Bundle();
    bundle.putString("package_name", paramString);
    try {
      Bundle bundle1 = paramzzu.zza(bundle);
      if (bundle1 == null) {
        this.zzada.zzgt().zzjg().zzby("Install Referrer Service returned a null response");
        return null;
      } 
      return bundle1;
    } catch (Exception exception) {
      this.zzada.zzgt().zzjg().zzg("Exception occurred while retrieving the Install Referrer", exception.getMessage());
      return null;
    } 
  }
  
  protected final void zzce(String paramString) {
    if (paramString == null || paramString.isEmpty()) {
      this.zzada.zzgt().zzjm().zzby("Install Referrer Reporter was called with invalid app package name");
      return;
    } 
    this.zzada.zzgs().zzaf();
    if (!zzke()) {
      this.zzada.zzgt().zzjm().zzby("Install Referrer Reporter is not available");
      return;
    } 
    this.zzada.zzgt().zzjm().zzby("Install Referrer Reporter is initializing");
    zzbk zzbk = new zzbk(this, paramString);
    this.zzada.zzgs().zzaf();
    Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
    intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
    PackageManager packageManager = this.zzada.getContext().getPackageManager();
    if (packageManager == null) {
      this.zzada.zzgt().zzjj().zzby("Failed to obtain Package Manager to verify binding conditions");
      return;
    } 
    List<ResolveInfo> list = packageManager.queryIntentServices(intent, 0);
    if (list != null && !list.isEmpty()) {
      ResolveInfo resolveInfo = list.get(0);
      ServiceInfo serviceInfo = resolveInfo.serviceInfo;
      if (serviceInfo != null) {
        String str = serviceInfo.packageName;
        if (resolveInfo.serviceInfo.name != null && "com.android.vending".equals(str) && zzke()) {
          intent = new Intent(intent);
          try {
            String str1;
            boolean bool = ConnectionTracker.getInstance().bindService(this.zzada.getContext(), intent, zzbk, 1);
            zzau zzau = this.zzada.zzgt().zzjm();
            if (bool) {
              str1 = "available";
            } else {
              str1 = "not available";
            } 
            zzau.zzg("Install Referrer Service is", str1);
            return;
          } catch (Exception exception) {
            this.zzada.zzgt().zzjg().zzg("Exception occurred while binding to Install Referrer Service", exception.getMessage());
            return;
          } 
        } 
        this.zzada.zzgt().zzjm().zzby("Play Store missing or incompatible. Version 8.3.73 or later required");
      } 
      return;
    } 
    this.zzada.zzgt().zzjm().zzby("Play Service for fetching Install Referrer is unavailable on device");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */