package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;

public final class DeviceProperties {
  private static Boolean zzgp;
  
  private static Boolean zzgq;
  
  private static Boolean zzgs;
  
  @TargetApi(21)
  public static boolean isSidewinder(Context paramContext) {
    if (zzgq == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastLollipop() && paramContext.getPackageManager().hasSystemFeature("cn.google")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzgq = Boolean.valueOf(bool);
    } 
    return zzgq.booleanValue();
  }
  
  public static boolean isUserBuild() {
    return "user".equals(Build.TYPE);
  }
  
  @TargetApi(20)
  public static boolean isWearable(Context paramContext) {
    if (zzgp == null) {
      boolean bool;
      if (PlatformVersion.isAtLeastKitKatWatch() && paramContext.getPackageManager().hasSystemFeature("android.hardware.type.watch")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzgp = Boolean.valueOf(bool);
    } 
    return zzgp.booleanValue();
  }
  
  @TargetApi(26)
  public static boolean isWearableWithoutPlayStore(Context paramContext) {
    return (isWearable(paramContext) && (!PlatformVersion.isAtLeastN() || (isSidewinder(paramContext) && !PlatformVersion.isAtLeastO())));
  }
  
  public static boolean zzf(Context paramContext) {
    if (zzgs == null) {
      boolean bool;
      if (paramContext.getPackageManager().hasSystemFeature("android.hardware.type.iot") || paramContext.getPackageManager().hasSystemFeature("android.hardware.type.embedded")) {
        bool = true;
      } else {
        bool = false;
      } 
      zzgs = Boolean.valueOf(bool);
    } 
    return zzgs.booleanValue();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/DeviceProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */