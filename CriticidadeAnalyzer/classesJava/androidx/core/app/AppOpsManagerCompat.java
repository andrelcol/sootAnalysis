package androidx.core.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Build;

public final class AppOpsManagerCompat {
  public static int noteProxyOpNoThrow(Context paramContext, String paramString1, String paramString2) {
    return (Build.VERSION.SDK_INT >= 23) ? ((AppOpsManager)paramContext.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(paramString1, paramString2) : 1;
  }
  
  public static String permissionToOp(String paramString) {
    return (Build.VERSION.SDK_INT >= 23) ? AppOpsManager.permissionToOp(paramString) : null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/AppOpsManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */