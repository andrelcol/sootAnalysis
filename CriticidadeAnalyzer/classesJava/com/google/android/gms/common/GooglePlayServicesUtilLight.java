package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserManager;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GooglePlayServicesUtilLight {
  @Deprecated
  public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 12451000;
  
  static final AtomicBoolean sCanceledAvailabilityNotification = new AtomicBoolean();
  
  private static boolean zzaj = false;
  
  private static boolean zzak = false;
  
  private static final AtomicBoolean zzal = new AtomicBoolean();
  
  @Deprecated
  public static void cancelAvailabilityErrorNotifications(Context paramContext) {
    if (sCanceledAvailabilityNotification.getAndSet(true))
      return; 
    try {
      NotificationManager notificationManager = (NotificationManager)paramContext.getSystemService("notification");
      if (notificationManager != null)
        notificationManager.cancel(10436); 
    } catch (SecurityException securityException) {}
  }
  
  @Deprecated
  public static int getApkVersion(Context paramContext) {
    try {
      PackageInfo packageInfo = paramContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
      return packageInfo.versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return 0;
    } 
  }
  
  @Deprecated
  public static String getErrorString(int paramInt) {
    return ConnectionResult.zza(paramInt);
  }
  
  public static Context getRemoteContext(Context paramContext) {
    try {
      return paramContext.createPackageContext("com.google.android.gms", 3);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static Resources getRemoteResource(Context paramContext) {
    try {
      return paramContext.getPackageManager().getResourcesForApplication("com.google.android.gms");
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return null;
    } 
  }
  
  public static boolean honorsDebugCertificates(Context paramContext) {
    if (!zzak)
      try {
        PackageInfo packageInfo = Wrappers.packageManager(paramContext).getPackageInfo("com.google.android.gms", 64);
        GoogleSignatureVerifier.getInstance(paramContext);
        if (packageInfo != null && !GoogleSignatureVerifier.zza(packageInfo, false) && GoogleSignatureVerifier.zza(packageInfo, true)) {
          zzaj = true;
        } else {
          zzaj = false;
        } 
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      
      } finally {
        zzak = true;
      }  
    return (zzaj || !DeviceProperties.isUserBuild());
  }
  
  @Deprecated
  public static int isGooglePlayServicesAvailable(Context paramContext, int paramInt) {
    StringBuilder stringBuilder;
    boolean bool;
    try {
      paramContext.getResources().getString(R$string.common_google_play_services_unknown_issue);
    } finally {
      Exception exception;
    } 
    if (!"com.google.android.gms".equals(paramContext.getPackageName()) && !zzal.get()) {
      int i = zzp.zzd(paramContext);
      if (i != 0) {
        int j = GOOGLE_PLAY_SERVICES_VERSION_CODE;
        if (i != j) {
          stringBuilder = new StringBuilder(320);
          stringBuilder.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
          stringBuilder.append(j);
          stringBuilder.append(" but found ");
          stringBuilder.append(i);
          stringBuilder.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
          throw new IllegalStateException(stringBuilder.toString());
        } 
      } else {
        throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
      } 
    } 
    if (!DeviceProperties.isWearableWithoutPlayStore((Context)stringBuilder) && !DeviceProperties.zzf((Context)stringBuilder)) {
      bool = true;
    } else {
      bool = false;
    } 
    return zza((Context)stringBuilder, bool, paramInt);
  }
  
  @Deprecated
  public static boolean isPlayServicesPossiblyUpdating(Context paramContext, int paramInt) {
    return (paramInt == 18) ? true : ((paramInt == 1) ? isUninstalledAppPossiblyUpdating(paramContext, "com.google.android.gms") : false);
  }
  
  @TargetApi(18)
  public static boolean isRestrictedUserProfile(Context paramContext) {
    if (PlatformVersion.isAtLeastJellyBeanMR2()) {
      Bundle bundle = ((UserManager)paramContext.getSystemService("user")).getApplicationRestrictions(paramContext.getPackageName());
      if (bundle != null && "true".equals(bundle.getString("restricted_profile")))
        return true; 
    } 
    return false;
  }
  
  @TargetApi(21)
  static boolean isUninstalledAppPossiblyUpdating(Context paramContext, String paramString) {
    boolean bool = paramString.equals("com.google.android.gms");
    if (PlatformVersion.isAtLeastLollipop())
      try {
        List list = paramContext.getPackageManager().getPackageInstaller().getAllSessions();
        Iterator<PackageInstaller.SessionInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
          if (paramString.equals(((PackageInstaller.SessionInfo)iterator.next()).getAppPackageName()))
            return true; 
        } 
      } catch (Exception exception) {
        return false;
      }  
    PackageManager packageManager = exception.getPackageManager();
    try {
      ApplicationInfo applicationInfo = packageManager.getApplicationInfo(paramString, 8192);
      if (bool)
        return applicationInfo.enabled; 
      if (applicationInfo.enabled) {
        bool = isRestrictedUserProfile((Context)exception);
        if (!bool)
          return true; 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
  
  @Deprecated
  public static boolean isUserRecoverableError(int paramInt) {
    return !(paramInt != 1 && paramInt != 2 && paramInt != 3 && paramInt != 9);
  }
  
  @Deprecated
  @TargetApi(19)
  public static boolean uidHasPackageName(Context paramContext, int paramInt, String paramString) {
    return UidVerifier.uidHasPackageName(paramContext, paramInt, paramString);
  }
  
  private static int zza(Context paramContext, boolean paramBoolean, int paramInt) {
    boolean bool;
    if (paramInt >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    PackageManager packageManager = paramContext.getPackageManager();
    PackageInfo packageInfo = null;
    if (paramBoolean)
      try {
        packageInfo = packageManager.getPackageInfo("com.android.vending", 8256);
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return 9;
      }  
    try {
      PackageInfo packageInfo1 = packageManager.getPackageInfo("com.google.android.gms", 64);
      GoogleSignatureVerifier.getInstance((Context)nameNotFoundException);
      if (!GoogleSignatureVerifier.zza(packageInfo1, true))
        return 9; 
      if (paramBoolean && (!GoogleSignatureVerifier.zza(packageInfo, true) || !packageInfo.signatures[0].equals(packageInfo1.signatures[0])))
        return 9; 
      if (zzb.zzc(packageInfo1.versionCode) < zzb.zzc(paramInt)) {
        int i = packageInfo1.versionCode;
        StringBuilder stringBuilder = new StringBuilder(77);
        stringBuilder.append("Google Play services out of date.  Requires ");
        stringBuilder.append(paramInt);
        stringBuilder.append(" but found ");
        stringBuilder.append(i);
        stringBuilder.toString();
        return 2;
      } 
      ApplicationInfo applicationInfo2 = packageInfo1.applicationInfo;
      ApplicationInfo applicationInfo1 = applicationInfo2;
      if (applicationInfo2 == null)
        try {
          applicationInfo1 = packageManager.getApplicationInfo("com.google.android.gms", 0);
        } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
          return 1;
        }  
      return !((ApplicationInfo)nameNotFoundException1).enabled ? 3 : 0;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException1) {
      return 1;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/GooglePlayServicesUtilLight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */