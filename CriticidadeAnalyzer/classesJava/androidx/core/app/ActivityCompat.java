package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.core.content.ContextCompat;

public class ActivityCompat extends ContextCompat {
  private static PermissionCompatDelegate sDelegate;
  
  public static void finishAffinity(Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.finishAffinity();
    } else {
      paramActivity.finish();
    } 
  }
  
  public static PermissionCompatDelegate getPermissionCompatDelegate() {
    return sDelegate;
  }
  
  public static void requestPermissions(final Activity activity, final String[] permissions, final int requestCode) {
    PermissionCompatDelegate permissionCompatDelegate = sDelegate;
    if (permissionCompatDelegate != null && permissionCompatDelegate.requestPermissions(activity, permissions, requestCode))
      return; 
    if (Build.VERSION.SDK_INT >= 23) {
      if (activity instanceof RequestPermissionsRequestCodeValidator)
        ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(requestCode); 
      activity.requestPermissions(permissions, requestCode);
    } else if (activity instanceof OnRequestPermissionsResultCallback) {
      (new Handler(Looper.getMainLooper())).post(new Runnable() {
            final Activity val$activity;
            
            final String[] val$permissions;
            
            final int val$requestCode;
            
            public void run() {
              int[] arrayOfInt = new int[permissions.length];
              PackageManager packageManager = activity.getPackageManager();
              String str = activity.getPackageName();
              int i = permissions.length;
              for (byte b = 0; b < i; b++)
                arrayOfInt[b] = packageManager.checkPermission(permissions[b], str); 
              ((ActivityCompat.OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, arrayOfInt);
            }
          });
    } 
  }
  
  public static void startActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt, Bundle paramBundle) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.startActivityForResult(paramIntent, paramInt, paramBundle);
    } else {
      paramActivity.startActivityForResult(paramIntent, paramInt);
    } 
  }
  
  public static interface OnRequestPermissionsResultCallback {
    void onRequestPermissionsResult(int param1Int, String[] param1ArrayOfString, int[] param1ArrayOfint);
  }
  
  public static interface PermissionCompatDelegate {
    boolean onActivityResult(Activity param1Activity, int param1Int1, int param1Int2, Intent param1Intent);
    
    boolean requestPermissions(Activity param1Activity, String[] param1ArrayOfString, int param1Int);
  }
  
  public static interface RequestPermissionsRequestCodeValidator {
    void validateRequestPermissionsRequestCode(int param1Int);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/androidx/core/app/ActivityCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */