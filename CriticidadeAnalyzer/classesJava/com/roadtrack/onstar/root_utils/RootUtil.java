package com.roadtrack.onstar.root_utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RootUtil {
  public static boolean checkIsRootMyDeviceOrEmulator(Context paramContext) {
    return (checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || isRooted() || isPhoneRooted() || checkRootMethod4(paramContext) || isRootAvailable2() || isEmulatorGeneric());
  }
  
  private static boolean checkRootMethod1() {
    boolean bool;
    String str = Build.TAGS;
    if (str != null && str.contains("test-keys")) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  private static boolean checkRootMethod2() {
    String[] arrayOfString = new String[21];
    arrayOfString[0] = "/system/app/Superuser.apk";
    arrayOfString[1] = "/system/app/SuperSU.apk";
    arrayOfString[2] = "/system/app/MagiskManager.apk";
    arrayOfString[3] = "/sbin/su";
    arrayOfString[4] = "/system/bin/su";
    arrayOfString[5] = "/system/xbin/su";
    arrayOfString[6] = "/data/local/xbin/su";
    arrayOfString[7] = "/data/local/bin/su";
    arrayOfString[8] = "/system/sd/xbin/su";
    arrayOfString[9] = "/system/bin/failsafe/su";
    arrayOfString[10] = "/data/local/su";
    arrayOfString[11] = "/system/app/Superuser.apk";
    arrayOfString[12] = "/sbin/su";
    arrayOfString[13] = "/system/bin/su";
    arrayOfString[14] = "/system/xbin/su";
    arrayOfString[15] = "/data/local/xbin/su";
    arrayOfString[16] = "/data/local/bin/su";
    arrayOfString[17] = "/system/sd/xbin/su";
    arrayOfString[18] = "/system/bin/failsafe/su";
    arrayOfString[19] = "/data/local/su";
    arrayOfString[20] = "/su/bin/su";
    int i = arrayOfString.length;
    for (byte b = 0; b < i; b++) {
      if ((new File(arrayOfString[b])).exists())
        return true; 
    } 
    return false;
  }
  
  private static boolean checkRootMethod3() {
    Process process = null;
    try {
      Process process1 = Runtime.getRuntime().exec(new String[] { "/system/xbin/which", "su" });
      process = process1;
      BufferedReader bufferedReader = new BufferedReader();
      process = process1;
      InputStreamReader inputStreamReader = new InputStreamReader();
      process = process1;
      this(process1.getInputStream());
      process = process1;
      this(inputStreamReader);
      process = process1;
      String str = bufferedReader.readLine();
      return false;
    } finally {
      Exception exception = null;
      if (process != null)
        process.destroy(); 
    } 
  }
  
  public static boolean checkRootMethod4(Context paramContext) {
    return isPackageInstalled("eu.chainfire.supersu", paramContext);
  }
  
  public static boolean findBinary(String paramString) {
    boolean bool1;
    String[] arrayOfString = new String[41];
    arrayOfString[0] = "/sbin/";
    arrayOfString[1] = "/system/bin/";
    arrayOfString[2] = "/system/xbin/";
    arrayOfString[3] = "/data/local/xbin/";
    arrayOfString[4] = "/data/local/bin/";
    arrayOfString[5] = "/system/sd/xbin/";
    arrayOfString[6] = "/system/bin/failsafe/";
    arrayOfString[7] = "/data/local/";
    arrayOfString[8] = "/data/local/";
    arrayOfString[9] = "/data/local/bin/";
    arrayOfString[10] = "/data/local/xbin/";
    arrayOfString[11] = "/sbin/";
    arrayOfString[12] = "/system/bin/";
    arrayOfString[13] = "/system/bin/.ext/";
    arrayOfString[14] = "/system/bin/failsafe/";
    arrayOfString[15] = "/system/sd/xbin/";
    arrayOfString[16] = "/su/xbin/";
    arrayOfString[17] = "/su/bin/";
    arrayOfString[18] = "/magisk/.core/bin/";
    arrayOfString[19] = "/system/usr/we-need-root/";
    arrayOfString[20] = "/system/xbin/";
    arrayOfString[21] = "/system/lib/libxposed_art.so";
    arrayOfString[22] = "/system/lib64/libxposed_art.so";
    arrayOfString[23] = "/system/xposed.prop";
    arrayOfString[24] = "/cache/recovery/xposed.zip";
    arrayOfString[25] = "/system/framework/XposedBridge.jar";
    arrayOfString[26] = "/system/bin/app_process64_xposed";
    arrayOfString[27] = "/system/bin/app_process32_xposed";
    arrayOfString[28] = "/magisk/xposed/system/lib/libsigchain.so";
    arrayOfString[29] = "/magisk/xposed/system/lib/libart.so";
    arrayOfString[30] = "/magisk/xposed/system/lib/libart-disassembler.so";
    arrayOfString[31] = "/magisk/xposed/system/lib/libart-compiler.so";
    arrayOfString[32] = "/system/bin/app_process32_orig";
    arrayOfString[33] = "/system/bin/app_process64_orig";
    arrayOfString[34] = "/system";
    arrayOfString[35] = "/system/bin";
    arrayOfString[36] = "/system/sbin";
    arrayOfString[37] = "/system/xbin";
    arrayOfString[38] = "/vendor/bin";
    arrayOfString[39] = "/sbin";
    arrayOfString[40] = "/etc";
    int i = arrayOfString.length;
    boolean bool2 = false;
    byte b = 0;
    while (true) {
      bool1 = bool2;
      if (b < i) {
        String str = arrayOfString[b];
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(paramString);
        if ((new File(stringBuilder.toString())).exists()) {
          bool1 = true;
          break;
        } 
        b++;
        continue;
      } 
      break;
    } 
    return bool1;
  }
  
  private static boolean isEmulatorGeneric() {
    return ((Build.MANUFACTURER == "Google" && Build.BRAND == "google" && ((Build.FINGERPRINT.startsWith("google/sdk_gphone_") && Build.FINGERPRINT.endsWith(":user/release-keys") && Build.PRODUCT.startsWith("sdk_gphone_") && Build.MODEL.startsWith("sdk_gphone_")) || (Build.FINGERPRINT.startsWith("google/sdk_gphone64_") && (Build.FINGERPRINT.endsWith(":userdebug/dev-keys") || Build.FINGERPRINT.endsWith(":user/release-keys")) && Build.PRODUCT.startsWith("sdk_gphone64_") && Build.MODEL.startsWith("sdk_gphone64_")))) || Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith("unknown") || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || ("QC_Reference_Phone" == Build.BOARD && !"Xiaomi".equals(Build.MANUFACTURER)) || Build.MANUFACTURER.contains("Genymotion") || Build.HOST.startsWith("Build") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || Build.PRODUCT == "google_sdk" || System.getProperty("ro.kernel.qemu") == "1");
  }
  
  private static boolean isPackageInstalled(String paramString, Context paramContext) {
    PackageManager packageManager = paramContext.getPackageManager();
    try {
      packageManager.getPackageInfo(paramString, 1);
      return true;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      return false;
    } 
  }
  
  public static boolean isPhoneRooted() {
    String str = Build.TAGS;
    if (str != null && str.contains("test-keys"))
      return true; 
    try {
      File file = new File();
      this("/system/app/Superuser.apk");
      boolean bool = file.exists();
      if (bool)
        return true; 
    } finally {}
    return false;
  }
  
  public static boolean isRootAvailable2() {
    null = null;
    Process process1 = null;
    Process process2 = null;
    try {
      Process process = Runtime.getRuntime().exec(new String[] { "su" });
      process2 = process;
      null = process;
      process1 = process;
      int i = process.waitFor();
      if (i == 0)
        return true; 
      process2 = process;
      null = process;
      process1 = process;
      Exception exception = new Exception();
      process2 = process;
      null = process;
      process1 = process;
      StringBuilder stringBuilder = new StringBuilder();
      process2 = process;
      null = process;
      process1 = process;
      this();
      process2 = process;
      null = process;
      process1 = process;
      stringBuilder.append("Root check result with exit command ");
      process2 = process;
      null = process;
      process1 = process;
      stringBuilder.append(i);
      process2 = process;
      null = process;
      process1 = process;
      this(stringBuilder.toString());
      process2 = process;
      null = process;
      process1 = process;
      throw exception;
    } catch (IOException iOException) {
    
    } catch (Exception exception) {
    
    } finally {
      if (process2 != null)
        process2.destroy(); 
    } 
    SYNTHETIC_LOCAL_VARIABLE_1.destroy();
    return false;
  }
  
  private static boolean isRooted() {
    return findBinary("su");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/root_utils/RootUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */