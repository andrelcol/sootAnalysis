package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import com.google.android.gms.common.wrappers.Wrappers;

public class ClientLibraryUtils {
  public static boolean isPackageSide() {
    return false;
  }
  
  public static boolean zzc(Context paramContext, String paramString) {
    "com.google.android.gms".equals(paramString);
    try {
      int i = (Wrappers.packageManager(paramContext).getApplicationInfo(paramString, 0)).flags;
      if ((i & 0x200000) != 0)
        return true; 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/ClientLibraryUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */