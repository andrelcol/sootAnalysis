package com.roadtrack.onstar.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.roadtrack.onstar.servicios.PermissionsChecker;
import com.roadtrack.onstar.ui.login.LoginActivity;

@TargetApi(23)
public class PermissionsManagerMarshmallow implements ActivityCompat.OnRequestPermissionsResultCallback {
  private static String[] ALL_Permissions = new String[] { "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WAKE_LOCK", "android.permission.READ_PHONE_STATE", "android.permission.CALL_PHONE", "android.permission.POST_NOTIFICATIONS" };
  
  public static boolean Statusresponce = false;
  
  public void Permissions(Activity paramActivity, int paramInt) {
    if (paramInt == 0) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.READ_PHONE_STATE" }, 0);
    } else if (1 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.READ_EXTERNAL_STORAGE" }, 1);
    } else if (2 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.WRITE_EXTERNAL_STORAGE" }, 2);
    } else if (3 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.ACCESS_FINE_LOCATION" }, 3);
    } else if (6 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.CALL_PHONE" }, 6);
    } else if (7 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, new String[] { "android.permission.POST_NOTIFICATIONS" }, 7);
    } else if (100 == paramInt) {
      ActivityCompat.requestPermissions(paramActivity, ALL_Permissions, 100);
    } 
  }
  
  public boolean checkAllPermissions(Context paramContext, int paramInt, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 23) {
      if (100 == paramInt) {
        if (paramContext.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 || paramContext.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") != 0 || paramContext.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 || paramContext.checkSelfPermission("android.permission.READ_PHONE_STATE") != 0 || paramContext.checkSelfPermission("android.permission.CALL_PHONE") != 0) {
          paramContext.stopService(new Intent(paramContext, PermissionsChecker.class));
          if (paramBoolean) {
            Intent intent = new Intent(paramContext, LoginActivity.class);
            intent.addFlags(268468224);
            intent.putExtra("EXIT_PERMISSIONS", true);
            paramContext.startActivity(intent);
          } 
          return false;
        } 
        return true;
      } 
      return false;
    } 
    paramContext.stopService(new Intent(paramContext, PermissionsChecker.class));
    return true;
  }
  
  public boolean checkPermissions(Activity paramActivity, int paramInt) {
    Context context = paramActivity.getBaseContext();
    if (Build.VERSION.SDK_INT >= 23) {
      if (200 == paramInt) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == -1) {
          Permissions(paramActivity, 0);
          Statusresponce = false;
        } else if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == -1) {
          Permissions(paramActivity, 3);
          Statusresponce = false;
        } else if (ContextCompat.checkSelfPermission(context, "android.permission.CALL_PHONE") == -1) {
          Permissions(paramActivity, 6);
          Statusresponce = false;
        } else if (Build.VERSION.SDK_INT >= 33 && ContextCompat.checkSelfPermission(context, "android.permission.POST_NOTIFICATIONS") == -1) {
          Permissions(paramActivity, 7);
          Statusresponce = false;
        } else {
          Statusresponce = true;
          return Statusresponce;
        } 
      } else if (100 == paramInt) {
        if (context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == -1 && context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == -1 && context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1 && context.checkSelfPermission("android.permission.READ_PHONE_STATE") == -1 && context.checkSelfPermission("android.permission.CALL_PHONE") == -1 && context.checkSelfPermission("android.permission.POST_NOTIFICATIONS") == -1) {
          Permissions(paramActivity, 100);
          Statusresponce = false;
        } else {
          checkPermissions(paramActivity, 200);
        } 
      } 
    } else {
      Statusresponce = true;
    } 
    return Statusresponce;
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    if (paramInt == 0) {
      if (paramArrayOfint.length == 1 && paramArrayOfint[0] == 0) {
        Statusresponce = true;
      } else {
        Statusresponce = false;
      } 
    } else {
      onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfint);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/utils/PermissionsManagerMarshmallow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */