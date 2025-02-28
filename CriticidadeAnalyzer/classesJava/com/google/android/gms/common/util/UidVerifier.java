package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.wrappers.Wrappers;

public final class UidVerifier {
  public static boolean isGooglePlayServicesUid(Context paramContext, int paramInt) {
    if (!uidHasPackageName(paramContext, paramInt, "com.google.android.gms"))
      return false; 
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      PackageInfo packageInfo = packageManager.getPackageInfo("com.google.android.gms", 64);
      return GoogleSignatureVerifier.getInstance(paramContext).isGooglePublicSignedPackage(packageInfo);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.isLoggable("UidVerifier", 3);
      return false;
    } 
  }
  
  @TargetApi(19)
  public static boolean uidHasPackageName(Context paramContext, int paramInt, String paramString) {
    return Wrappers.packageManager(paramContext).zzb(paramInt, paramString);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/UidVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */