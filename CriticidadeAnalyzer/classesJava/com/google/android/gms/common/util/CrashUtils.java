package com.google.android.gms.common.util;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

public final class CrashUtils {
  static {
  
  }
  
  public static boolean addDynamiteErrorToDropBox(Context paramContext, Throwable paramThrowable) {
    return zza(paramContext, paramThrowable, 536870912);
  }
  
  private static boolean zza(Context paramContext, Throwable paramThrowable, int paramInt) {
    try {
      Preconditions.checkNotNull(paramContext);
      Preconditions.checkNotNull(paramThrowable);
    } catch (Exception exception) {}
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/CrashUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */