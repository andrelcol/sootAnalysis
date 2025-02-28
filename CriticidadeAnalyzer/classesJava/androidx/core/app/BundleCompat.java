package androidx.core.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class BundleCompat {
  public static IBinder getBinder(Bundle paramBundle, String paramString) {
    return (Build.VERSION.SDK_INT >= 18) ? paramBundle.getBinder(paramString) : BundleCompatBaseImpl.getBinder(paramBundle, paramString);
  }
  
  static class BundleCompatBaseImpl {
    private static Method sGetIBinderMethod;
    
    private static boolean sGetIBinderMethodFetched;
    
    public static IBinder getBinder(Bundle param1Bundle, String param1String) {
      if (!sGetIBinderMethodFetched) {
        try {
          sGetIBinderMethod = Bundle.class.getMethod("getIBinder", new Class[] { String.class });
          sGetIBinderMethod.setAccessible(true);
        } catch (NoSuchMethodException noSuchMethodException) {}
        sGetIBinderMethodFetched = true;
      } 
      Method method = sGetIBinderMethod;
      if (method != null)
        try {
          return (IBinder)method.invoke(param1Bundle, new Object[] { param1String });
        } catch (InvocationTargetException|IllegalAccessException|IllegalArgumentException invocationTargetException) {
          sGetIBinderMethod = null;
        }  
      return null;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/BundleCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */