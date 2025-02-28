package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.base.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.R;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.wrappers.Wrappers;

public final class ConnectionErrorMessages {
  private static final SimpleArrayMap<String, String> zaog = new SimpleArrayMap();
  
  public static String getAppName(Context paramContext) {
    String str = paramContext.getPackageName();
    try {
      return Wrappers.packageManager(paramContext).getApplicationLabel(str).toString();
    } catch (android.content.pm.PackageManager.NameNotFoundException|NullPointerException nameNotFoundException) {
      String str1 = (paramContext.getApplicationInfo()).name;
      return TextUtils.isEmpty(str1) ? str : str1;
    } 
  }
  
  public static String getDefaultNotificationChannelName(Context paramContext) {
    return paramContext.getResources().getString(R.string.common_google_play_services_notification_channel_name);
  }
  
  public static String getErrorDialogButtonMessage(Context paramContext, int paramInt) {
    Resources resources = paramContext.getResources();
    return (paramInt != 1) ? ((paramInt != 2) ? ((paramInt != 3) ? resources.getString(17039370) : resources.getString(R.string.common_google_play_services_enable_button)) : resources.getString(R.string.common_google_play_services_update_button)) : resources.getString(R.string.common_google_play_services_install_button);
  }
  
  public static String getErrorMessage(Context paramContext, int paramInt) {
    Resources resources = paramContext.getResources();
    String str = getAppName(paramContext);
    if (paramInt != 1) {
      if (paramInt != 2) {
        if (paramInt != 3) {
          if (paramInt != 5) {
            if (paramInt != 7) {
              if (paramInt != 9) {
                if (paramInt != 20) {
                  switch (paramInt) {
                    default:
                      return resources.getString(R.string.common_google_play_services_unknown_issue, new Object[] { str });
                    case 18:
                      return resources.getString(R.string.common_google_play_services_updating_text, new Object[] { str });
                    case 17:
                      return zaa(paramContext, "common_google_play_services_sign_in_failed_text", str);
                    case 16:
                      break;
                  } 
                  return zaa(paramContext, "common_google_play_services_api_unavailable_text", str);
                } 
                return zaa(paramContext, "common_google_play_services_restricted_profile_text", str);
              } 
              return resources.getString(R.string.common_google_play_services_unsupported_text, new Object[] { str });
            } 
            return zaa(paramContext, "common_google_play_services_network_error_text", str);
          } 
          return zaa(paramContext, "common_google_play_services_invalid_account_text", str);
        } 
        return resources.getString(R.string.common_google_play_services_enable_text, new Object[] { str });
      } 
      return DeviceProperties.isWearableWithoutPlayStore(paramContext) ? resources.getString(R.string.common_google_play_services_wear_update_text) : resources.getString(R.string.common_google_play_services_update_text, new Object[] { str });
    } 
    return resources.getString(R.string.common_google_play_services_install_text, new Object[] { str });
  }
  
  public static String getErrorNotificationMessage(Context paramContext, int paramInt) {
    return (paramInt == 6) ? zaa(paramContext, "common_google_play_services_resolution_required_text", getAppName(paramContext)) : getErrorMessage(paramContext, paramInt);
  }
  
  public static String getErrorNotificationTitle(Context paramContext, int paramInt) {
    String str1;
    if (paramInt == 6) {
      str1 = zaa(paramContext, "common_google_play_services_resolution_required_title");
    } else {
      str1 = getErrorTitle(paramContext, paramInt);
    } 
    String str2 = str1;
    if (str1 == null)
      str2 = paramContext.getResources().getString(R.string.common_google_play_services_notification_ticker); 
    return str2;
  }
  
  public static String getErrorTitle(Context paramContext, int paramInt) {
    StringBuilder stringBuilder;
    Resources resources = paramContext.getResources();
    switch (paramInt) {
      default:
        stringBuilder = new StringBuilder(33);
        stringBuilder.append("Unexpected error code ");
        stringBuilder.append(paramInt);
        stringBuilder.toString();
        return null;
      case 20:
        return zaa((Context)stringBuilder, "common_google_play_services_restricted_profile_title");
      case 17:
        return zaa((Context)stringBuilder, "common_google_play_services_sign_in_failed_title");
      case 8:
      case 9:
      case 10:
      case 11:
      case 16:
        return null;
      case 7:
        return zaa((Context)stringBuilder, "common_google_play_services_network_error_title");
      case 5:
        return zaa((Context)stringBuilder, "common_google_play_services_invalid_account_title");
      case 4:
      case 6:
      case 18:
        return null;
      case 3:
        return resources.getString(R.string.common_google_play_services_enable_title);
      case 2:
        return resources.getString(R.string.common_google_play_services_update_title);
      case 1:
        break;
    } 
    return resources.getString(R.string.common_google_play_services_install_title);
  }
  
  private static String zaa(Context paramContext, String paramString) {
    synchronized (zaog) {
      String str2 = (String)zaog.get(paramString);
      if (str2 != null)
        return str2; 
      Resources resources = GooglePlayServicesUtil.getRemoteResource(paramContext);
      if (resources == null)
        return null; 
      int i = resources.getIdentifier(paramString, "string", "com.google.android.gms");
      if (i == 0) {
        str1 = String.valueOf(paramString);
        if (str1.length() != 0) {
          "Missing resource: ".concat(str1);
        } else {
          new String("Missing resource: ");
        } 
        return null;
      } 
      String str1 = str1.getString(i);
      if (TextUtils.isEmpty(str1)) {
        str1 = String.valueOf(paramString);
        if (str1.length() != 0) {
          "Got empty resource: ".concat(str1);
        } else {
          new String("Got empty resource: ");
        } 
        return null;
      } 
      zaog.put(paramString, str1);
      return str1;
    } 
  }
  
  private static String zaa(Context paramContext, String paramString1, String paramString2) {
    Resources resources = paramContext.getResources();
    paramString1 = zaa(paramContext, paramString1);
    String str = paramString1;
    if (paramString1 == null)
      str = resources.getString(R.string.common_google_play_services_unknown_issue); 
    return String.format((resources.getConfiguration()).locale, str, new Object[] { paramString2 });
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/ConnectionErrorMessages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */