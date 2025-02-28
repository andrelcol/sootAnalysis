package com.google.android.gms.common.util;

import java.util.regex.Pattern;

public class Strings {
  static {
    Pattern.compile("\\$\\{(.*?)\\}");
  }
  
  public static boolean isEmptyOrWhitespace(String paramString) {
    return (paramString == null || paramString.trim().isEmpty());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/Strings.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */