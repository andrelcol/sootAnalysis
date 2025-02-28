package org.apache.commons.io;

import java.io.File;

public class FilenameUtils {
  private static final char SYSTEM_SEPARATOR = File.separatorChar;
  
  static {
    isSystemWindows();
  }
  
  public static String getBaseName(String paramString) {
    return removeExtension(getName(paramString));
  }
  
  public static String getExtension(String paramString) {
    if (paramString == null)
      return null; 
    int i = indexOfExtension(paramString);
    return (i == -1) ? "" : paramString.substring(i + 1);
  }
  
  public static String getName(String paramString) {
    return (paramString == null) ? null : paramString.substring(indexOfLastSeparator(paramString) + 1);
  }
  
  public static int indexOfExtension(String paramString) {
    byte b = -1;
    if (paramString == null)
      return -1; 
    int i = paramString.lastIndexOf('.');
    if (indexOfLastSeparator(paramString) > i)
      i = b; 
    return i;
  }
  
  public static int indexOfLastSeparator(String paramString) {
    return (paramString == null) ? -1 : Math.max(paramString.lastIndexOf('/'), paramString.lastIndexOf('\\'));
  }
  
  static boolean isSystemWindows() {
    boolean bool;
    if (SYSTEM_SEPARATOR == '\\') {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static String removeExtension(String paramString) {
    if (paramString == null)
      return null; 
    int i = indexOfExtension(paramString);
    return (i == -1) ? paramString : paramString.substring(0, i);
  }
  
  static {
    Character.toString('.');
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/org/apache/commons/io/FilenameUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */