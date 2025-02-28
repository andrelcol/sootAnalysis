package com.roadtrack.onstar.BO;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.roadtrack.onstar.Enums;
import com.roadtrack.onstar.utils.Utilities;

public class PreferenceRT {
  public static int GetValuePreference(String paramString, int paramInt, Context paramContext) {
    try {
      paramString = Utilities.Crypt(paramString);
      int i = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).getInt(paramString, paramInt);
      paramInt = i;
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
    } 
    return paramInt;
  }
  
  public static long GetValuePreference(String paramString, long paramLong, Context paramContext) {
    try {
      paramString = Utilities.Crypt(paramString);
      long l = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).getLong(paramString, paramLong);
      paramLong = l;
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
    } 
    return paramLong;
  }
  
  public static Object GetValuePreference(Enums.SettingsPreference paramSettingsPreference, Object paramObject, Context paramContext) {
    String str = paramObject.getClass().getName();
    if (paramContext != null) {
      String str1;
      Long long_;
      if (String.class.getName().equals(str)) {
        str1 = GetValuePreference(paramSettingsPreference.toString(), paramObject.toString(), paramContext);
      } else {
        Integer integer;
        if (Integer.class.getName().equals(str)) {
          integer = Integer.valueOf(GetValuePreference(str1.toString(), Integer.parseInt(paramObject.toString()), paramContext));
        } else if (Long.class.getName().equals(str)) {
          long_ = Long.valueOf(GetValuePreference(integer.toString(), Long.parseLong(paramObject.toString()), paramContext));
        } else {
          if (Boolean.class.getName().equals(str))
            return Boolean.valueOf(GetValuePreference(long_.toString(), Boolean.parseBoolean(paramObject.toString()), paramContext)); 
          long_ = null;
        } 
      } 
      return long_;
    } 
    paramSettingsPreference = null;
  }
  
  public static String GetValuePreference(String paramString1, String paramString2, Context paramContext) {
    String str1;
    String str2 = paramString2;
    try {
      paramString1 = Utilities.Crypt(paramString1.replace("\n", "").trim());
      str2 = paramString2;
      String str = Utilities.Crypt(paramString2.replace("\n", "").trim());
      str2 = paramString2;
      paramString1 = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).getString(paramString1, str);
      str2 = paramString1;
      paramString1 = Utilities.Decrypt(paramString1);
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      str1 = str2;
    } 
    return str1;
  }
  
  public static boolean GetValuePreference(String paramString, boolean paramBoolean, Context paramContext) {
    try {
      paramString = Utilities.Crypt(paramString);
      boolean bool = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).getBoolean(paramString, paramBoolean);
      paramBoolean = bool;
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
    } 
    return paramBoolean;
  }
  
  public static boolean SetStringPreference(String paramString1, String paramString2, Context paramContext) {
    boolean bool;
    try {
      paramString1 = Utilities.Crypt(paramString1).replace("\n", "").trim();
      paramString2 = Utilities.Crypt(paramString2).replace("\n", "").trim();
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).edit();
      editor.putString(paramString1, paramString2);
      bool = editor.commit();
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      bool = false;
    } 
    return bool;
  }
  
  public static void SetValuePreference(Enums.SettingsPreference paramSettingsPreference, Object paramObject, Context paramContext) {
    try {
      String str = paramObject.getClass().getName();
      if (String.class.getName().equals(str)) {
        SetValuePreference(paramSettingsPreference.toString(), paramObject.toString(), paramContext);
      } else if (Integer.class.getName().equals(str)) {
        SetValuePreference(paramSettingsPreference.toString(), Integer.parseInt(paramObject.toString()), paramContext);
      } else if (Long.class.getName().equals(str)) {
        SetValuePreference(paramSettingsPreference.toString(), Long.parseLong(paramObject.toString()), paramContext);
      } else if (Boolean.class.getName().equals(str)) {
        SetValuePreference(paramSettingsPreference.toString(), Boolean.parseBoolean(paramObject.toString()), paramContext);
      } 
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "Error: ChangePreferences", exception.getMessage());
    } 
  }
  
  public static boolean SetValuePreference(String paramString, int paramInt, Context paramContext) {
    boolean bool;
    try {
      paramString = Utilities.Crypt(paramString);
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).edit();
      editor.putInt(paramString, paramInt);
      bool = editor.commit();
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      bool = false;
    } 
    return bool;
  }
  
  public static boolean SetValuePreference(String paramString, long paramLong, Context paramContext) {
    boolean bool;
    try {
      paramString = Utilities.Crypt(paramString);
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).edit();
      editor.putLong(paramString, paramLong);
      bool = editor.commit();
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      bool = false;
    } 
    return bool;
  }
  
  public static boolean SetValuePreference(String paramString1, String paramString2, Context paramContext) {
    boolean bool;
    try {
      paramString1 = Utilities.Crypt(paramString1);
      paramString2 = Utilities.Crypt(paramString2);
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).edit();
      editor.putString(paramString1, paramString2);
      bool = editor.commit();
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      bool = false;
    } 
    return bool;
  }
  
  public static boolean SetValuePreference(String paramString, boolean paramBoolean, Context paramContext) {
    try {
      paramString = Utilities.Crypt(paramString);
      SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(paramContext.getApplicationContext()).edit();
      editor.putBoolean(paramString, paramBoolean);
      paramBoolean = editor.commit();
    } catch (Exception exception) {
      Utilities.escribeArchivo("PreferenceRT", "ERROR", exception.getMessage());
      paramBoolean = false;
    } 
    return paramBoolean;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/BO/PreferenceRT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */