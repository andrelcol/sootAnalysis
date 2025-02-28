package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

public class ProcessUtils {
  private static String zzhf;
  
  private static int zzhg;
  
  public static String getMyProcessName() {
    if (zzhf == null) {
      if (zzhg == 0)
        zzhg = Process.myPid(); 
      zzhf = zzd(zzhg);
    } 
    return zzhf;
  }
  
  private static String zzd(int paramInt) {
    IOException iOException1;
    null = null;
    String str = null;
    if (paramInt <= 0)
      return null; 
    try {
      StringBuilder stringBuilder = new StringBuilder();
      this(25);
      stringBuilder.append("/proc/");
      stringBuilder.append(paramInt);
      stringBuilder.append("/cmdline");
      BufferedReader bufferedReader = zzk(stringBuilder.toString());
      try {
        return str;
      } catch (IOException iOException2) {
      
      } finally {}
      IOUtils.closeQuietly(bufferedReader);
      throw null;
    } catch (IOException null) {
    
    } finally {
      iOException1 = iOException2;
      IOUtils.closeQuietly((Closeable)iOException1);
    } 
    IOUtils.closeQuietly((Closeable)iOException1);
    return (String)SYNTHETIC_LOCAL_VARIABLE_2;
  }
  
  private static BufferedReader zzk(String paramString) throws IOException {
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskReads();
    try {
      FileReader fileReader = new FileReader();
      this(paramString);
      return new BufferedReader(fileReader);
    } finally {
      StrictMode.setThreadPolicy(threadPolicy);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/ProcessUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */