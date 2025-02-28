package com.google.android.gcm;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Parcelable;

public final class GCMRegistrar {
  private static GCMBroadcastReceiver sRetryReceiver;
  
  private static String sRetryReceiverClassName;
  
  static String clearRegistrationId(Context paramContext) {
    return setRegistrationId(paramContext, "");
  }
  
  private static int getAppVersion(Context paramContext) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder("Coult not get package name: ");
      stringBuilder.append(nameNotFoundException);
      throw new RuntimeException(stringBuilder.toString());
    } 
  }
  
  static int getBackoff(Context paramContext) {
    return getGCMPreferences(paramContext).getInt("backoff_ms", 3000);
  }
  
  static String getFlatSenderIds(String... paramVarArgs) {
    if (paramVarArgs != null && paramVarArgs.length != 0) {
      StringBuilder stringBuilder = new StringBuilder(paramVarArgs[0]);
      for (byte b = 1;; b++) {
        if (b >= paramVarArgs.length)
          return stringBuilder.toString(); 
        stringBuilder.append(',');
        stringBuilder.append(paramVarArgs[b]);
      } 
    } 
    throw new IllegalArgumentException("No senderIds");
  }
  
  private static SharedPreferences getGCMPreferences(Context paramContext) {
    return paramContext.getSharedPreferences("com.google.android.gcm", 0);
  }
  
  public static String getRegistrationId(Context paramContext) {
    String str1;
    SharedPreferences sharedPreferences = getGCMPreferences(paramContext);
    String str2 = "";
    String str3 = sharedPreferences.getString("regId", "");
    int i = sharedPreferences.getInt("appVersion", -2147483648);
    int j = getAppVersion(paramContext);
    if (i != Integer.MIN_VALUE && i != j) {
      clearRegistrationId(paramContext);
      str1 = str2;
    } else {
      str1 = str3;
    } 
    return str1;
  }
  
  static void internalRegister(Context paramContext, String... paramVarArgs) {
    String str = getFlatSenderIds(paramVarArgs);
    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
    intent.setPackage("com.google.android.gsf");
    intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    intent.putExtra("sender", str);
    paramContext.startService(intent);
  }
  
  static void internalUnregister(Context paramContext) {
    Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
    intent.setPackage("com.google.android.gsf");
    intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(paramContext, 0, new Intent(), 0));
    paramContext.startService(intent);
  }
  
  public static boolean isRegistered(Context paramContext) {
    return (getRegistrationId(paramContext).length() > 0);
  }
  
  static void resetBackoff(Context paramContext) {
    setBackoff(paramContext, 3000);
  }
  
  static void setBackoff(Context paramContext, int paramInt) {
    SharedPreferences.Editor editor = getGCMPreferences(paramContext).edit();
    editor.putInt("backoff_ms", paramInt);
    editor.commit();
  }
  
  static String setRegistrationId(Context paramContext, String paramString) {
    SharedPreferences sharedPreferences = getGCMPreferences(paramContext);
    String str = sharedPreferences.getString("regId", "");
    int i = getAppVersion(paramContext);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("regId", paramString);
    editor.putInt("appVersion", i);
    editor.commit();
    return str;
  }
  
  static void setRetryBroadcastReceiver(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gcm/GCMRegistrar
    //   2: monitorenter
    //   3: getstatic com/google/android/gcm/GCMRegistrar.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   6: ifnonnull -> 116
    //   9: getstatic com/google/android/gcm/GCMRegistrar.sRetryReceiverClassName : Ljava/lang/String;
    //   12: ifnonnull -> 30
    //   15: new com/google/android/gcm/GCMBroadcastReceiver
    //   18: astore_1
    //   19: aload_1
    //   20: invokespecial <init> : ()V
    //   23: aload_1
    //   24: putstatic com/google/android/gcm/GCMRegistrar.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   27: goto -> 61
    //   30: getstatic com/google/android/gcm/GCMRegistrar.sRetryReceiverClassName : Ljava/lang/String;
    //   33: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   36: invokevirtual newInstance : ()Ljava/lang/Object;
    //   39: checkcast com/google/android/gcm/GCMBroadcastReceiver
    //   42: putstatic com/google/android/gcm/GCMRegistrar.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   45: goto -> 61
    //   48: astore_1
    //   49: new com/google/android/gcm/GCMBroadcastReceiver
    //   52: astore_1
    //   53: aload_1
    //   54: invokespecial <init> : ()V
    //   57: aload_1
    //   58: putstatic com/google/android/gcm/GCMRegistrar.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   61: aload_0
    //   62: invokevirtual getPackageName : ()Ljava/lang/String;
    //   65: astore_3
    //   66: new android/content/IntentFilter
    //   69: astore_1
    //   70: aload_1
    //   71: ldc 'com.google.android.gcm.intent.RETRY'
    //   73: invokespecial <init> : (Ljava/lang/String;)V
    //   76: aload_1
    //   77: aload_3
    //   78: invokevirtual addCategory : (Ljava/lang/String;)V
    //   81: new java/lang/StringBuilder
    //   84: astore_2
    //   85: aload_2
    //   86: aload_3
    //   87: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   90: invokespecial <init> : (Ljava/lang/String;)V
    //   93: aload_2
    //   94: ldc '.permission.C2D_MESSAGE'
    //   96: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload_2
    //   101: invokevirtual toString : ()Ljava/lang/String;
    //   104: astore_2
    //   105: aload_0
    //   106: getstatic com/google/android/gcm/GCMRegistrar.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   109: aload_1
    //   110: aload_2
    //   111: aconst_null
    //   112: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;Ljava/lang/String;Landroid/os/Handler;)Landroid/content/Intent;
    //   115: pop
    //   116: ldc com/google/android/gcm/GCMRegistrar
    //   118: monitorexit
    //   119: return
    //   120: astore_0
    //   121: ldc com/google/android/gcm/GCMRegistrar
    //   123: monitorexit
    //   124: aload_0
    //   125: athrow
    // Exception table:
    //   from	to	target	type
    //   3	27	120	finally
    //   30	45	48	java/lang/Exception
    //   30	45	120	finally
    //   49	61	120	finally
    //   61	116	120	finally
  }
  
  static void setRetryReceiverClassName(String paramString) {
    sRetryReceiverClassName = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gcm/GCMRegistrar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */