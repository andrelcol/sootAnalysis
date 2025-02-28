package com.roadtrack.onstar.servicios;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Parcelable;
import com.google.android.gcm.GCMBroadcastReceiver;

public class GCMRegisterOreo {
  private static GCMBroadcastReceiver sRetryReceiver;
  
  private static String sRetryReceiverClassName;
  
  static String clearRegistrationId(Context paramContext) {
    return setRegistrationId(paramContext, "");
  }
  
  private static int getAppVersion(Context paramContext) {
    try {
      return (paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0)).versionCode;
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("Coult not get package name: ");
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
      for (byte b = 1; b < paramVarArgs.length; b++) {
        stringBuilder.append(',');
        stringBuilder.append(paramVarArgs[b]);
      } 
      return stringBuilder.toString();
    } 
    throw new IllegalArgumentException("No senderIds");
  }
  
  private static SharedPreferences getGCMPreferences(Context paramContext) {
    return paramContext.getSharedPreferences("com.google.android.gcm", 0);
  }
  
  static void internalRegister(Context paramContext, String... paramVarArgs) {
    String str = getFlatSenderIds(paramVarArgs);
    Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
    intent.setPackage("com.google.android.gsf");
    intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(paramContext, 0, new Intent(), 201326592));
    intent.putExtra("sender", str);
    paramContext.startService(intent);
  }
  
  static void internalUnregister(Context paramContext) {
    Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
    intent.setPackage("com.google.android.gsf");
    intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(paramContext, 0, new Intent(), 201326592));
    paramContext.startService(intent);
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
    //   0: ldc com/roadtrack/onstar/servicios/GCMRegisterOreo
    //   2: monitorenter
    //   3: getstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   6: ifnonnull -> 118
    //   9: getstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiverClassName : Ljava/lang/String;
    //   12: ifnonnull -> 30
    //   15: new com/google/android/gcm/GCMBroadcastReceiver
    //   18: astore_1
    //   19: aload_1
    //   20: invokespecial <init> : ()V
    //   23: aload_1
    //   24: putstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   27: goto -> 61
    //   30: getstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiverClassName : Ljava/lang/String;
    //   33: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
    //   36: invokevirtual newInstance : ()Ljava/lang/Object;
    //   39: checkcast com/google/android/gcm/GCMBroadcastReceiver
    //   42: putstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   45: goto -> 61
    //   48: astore_1
    //   49: new com/google/android/gcm/GCMBroadcastReceiver
    //   52: astore_1
    //   53: aload_1
    //   54: invokespecial <init> : ()V
    //   57: aload_1
    //   58: putstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
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
    //   86: invokespecial <init> : ()V
    //   89: aload_2
    //   90: aload_3
    //   91: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: pop
    //   95: aload_2
    //   96: ldc '.permission.C2D_MESSAGE'
    //   98: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   101: pop
    //   102: aload_2
    //   103: invokevirtual toString : ()Ljava/lang/String;
    //   106: astore_2
    //   107: aload_0
    //   108: getstatic com/roadtrack/onstar/servicios/GCMRegisterOreo.sRetryReceiver : Lcom/google/android/gcm/GCMBroadcastReceiver;
    //   111: aload_1
    //   112: aload_2
    //   113: aconst_null
    //   114: invokevirtual registerReceiver : (Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;Ljava/lang/String;Landroid/os/Handler;)Landroid/content/Intent;
    //   117: pop
    //   118: ldc com/roadtrack/onstar/servicios/GCMRegisterOreo
    //   120: monitorexit
    //   121: return
    //   122: astore_0
    //   123: ldc com/roadtrack/onstar/servicios/GCMRegisterOreo
    //   125: monitorexit
    //   126: aload_0
    //   127: athrow
    // Exception table:
    //   from	to	target	type
    //   3	27	122	finally
    //   30	45	48	java/lang/Exception
    //   30	45	122	finally
    //   49	61	122	finally
    //   61	118	122	finally
  }
  
  static void setRetryReceiverClassName(String paramString) {
    sRetryReceiverClassName = paramString;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/roadtrack/onstar/servicios/GCMRegisterOreo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */