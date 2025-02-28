package com.google.android.gms.common.wrappers;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import com.google.android.gms.common.util.PlatformVersion;

public class PackageManagerWrapper {
  private final Context zzhx;
  
  public PackageManagerWrapper(Context paramContext) {
    this.zzhx = paramContext;
  }
  
  public int checkCallingOrSelfPermission(String paramString) {
    return this.zzhx.checkCallingOrSelfPermission(paramString);
  }
  
  public ApplicationInfo getApplicationInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return this.zzhx.getPackageManager().getApplicationInfo(paramString, paramInt);
  }
  
  public CharSequence getApplicationLabel(String paramString) throws PackageManager.NameNotFoundException {
    return this.zzhx.getPackageManager().getApplicationLabel(this.zzhx.getPackageManager().getApplicationInfo(paramString, 0));
  }
  
  public PackageInfo getPackageInfo(String paramString, int paramInt) throws PackageManager.NameNotFoundException {
    return this.zzhx.getPackageManager().getPackageInfo(paramString, paramInt);
  }
  
  public final String[] getPackagesForUid(int paramInt) {
    return this.zzhx.getPackageManager().getPackagesForUid(paramInt);
  }
  
  public boolean isCallerInstantApp() {
    if (Binder.getCallingUid() == Process.myUid())
      return InstantApps.isInstantApp(this.zzhx); 
    if (PlatformVersion.isAtLeastO()) {
      String str = this.zzhx.getPackageManager().getNameForUid(Binder.getCallingUid());
      if (str != null)
        return this.zzhx.getPackageManager().isInstantApp(str); 
    } 
    return false;
  }
  
  public final PackageInfo zza(String paramString, int paramInt1, int paramInt2) throws PackageManager.NameNotFoundException {
    return this.zzhx.getPackageManager().getPackageInfo(paramString, 64);
  }
  
  @TargetApi(19)
  public final boolean zzb(int paramInt, String paramString) {
    if (PlatformVersion.isAtLeastKitKat())
      try {
        ((AppOpsManager)this.zzhx.getSystemService("appops")).checkPackage(paramInt, paramString);
        return true;
      } catch (SecurityException securityException) {
        return false;
      }  
    String[] arrayOfString = this.zzhx.getPackageManager().getPackagesForUid(paramInt);
    if (securityException != null && arrayOfString != null)
      for (paramInt = 0; paramInt < arrayOfString.length; paramInt++) {
        if (securityException.equals(arrayOfString[paramInt]))
          return true; 
      }  
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/wrappers/PackageManagerWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */