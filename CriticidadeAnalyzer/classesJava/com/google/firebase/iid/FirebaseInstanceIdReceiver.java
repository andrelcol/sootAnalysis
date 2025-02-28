package com.google.firebase.iid;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.PlatformVersion;

public final class FirebaseInstanceIdReceiver extends WakefulBroadcastReceiver {
  private static zzh zzbg;
  
  private static zzh zzbh;
  
  @SuppressLint({"InlinedApi"})
  public static int zza(BroadcastReceiver paramBroadcastReceiver, Context paramContext, String paramString, Intent paramIntent) {
    boolean bool1 = PlatformVersion.isAtLeastO();
    boolean bool = true;
    if (bool1 && (paramContext.getApplicationInfo()).targetSdkVersion >= 26) {
      i = 1;
    } else {
      i = 0;
    } 
    if ((paramIntent.getFlags() & 0x10000000) == 0)
      bool = false; 
    if (i && !bool)
      return zzb(paramBroadcastReceiver, paramContext, paramString, paramIntent); 
    int i = zzav.zzai().zzb(paramContext, paramString, paramIntent);
    if (PlatformVersion.isAtLeastO() && i == 402) {
      zzb(paramBroadcastReceiver, paramContext, paramString, paramIntent);
      return 403;
    } 
    return i;
  }
  
  private static zzh zza(Context paramContext, String paramString) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/FirebaseInstanceIdReceiver
    //   2: monitorenter
    //   3: ldc 'com.google.firebase.MESSAGING_EVENT'
    //   5: aload_1
    //   6: invokevirtual equals : (Ljava/lang/Object;)Z
    //   9: ifeq -> 41
    //   12: getstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbh : Lcom/google/firebase/iid/zzh;
    //   15: ifnonnull -> 32
    //   18: new com/google/firebase/iid/zzh
    //   21: astore_2
    //   22: aload_2
    //   23: aload_0
    //   24: aload_1
    //   25: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;)V
    //   28: aload_2
    //   29: putstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbh : Lcom/google/firebase/iid/zzh;
    //   32: getstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbh : Lcom/google/firebase/iid/zzh;
    //   35: astore_0
    //   36: ldc com/google/firebase/iid/FirebaseInstanceIdReceiver
    //   38: monitorexit
    //   39: aload_0
    //   40: areturn
    //   41: getstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbg : Lcom/google/firebase/iid/zzh;
    //   44: ifnonnull -> 61
    //   47: new com/google/firebase/iid/zzh
    //   50: astore_2
    //   51: aload_2
    //   52: aload_0
    //   53: aload_1
    //   54: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;)V
    //   57: aload_2
    //   58: putstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbg : Lcom/google/firebase/iid/zzh;
    //   61: getstatic com/google/firebase/iid/FirebaseInstanceIdReceiver.zzbg : Lcom/google/firebase/iid/zzh;
    //   64: astore_0
    //   65: ldc com/google/firebase/iid/FirebaseInstanceIdReceiver
    //   67: monitorexit
    //   68: aload_0
    //   69: areturn
    //   70: astore_0
    //   71: ldc com/google/firebase/iid/FirebaseInstanceIdReceiver
    //   73: monitorexit
    //   74: aload_0
    //   75: athrow
    // Exception table:
    //   from	to	target	type
    //   3	32	70	finally
    //   32	36	70	finally
    //   41	61	70	finally
    //   61	65	70	finally
  }
  
  private final void zza(Context paramContext, Intent paramIntent, String paramString) {
    String str1 = null;
    paramIntent.setComponent(null);
    paramIntent.setPackage(paramContext.getPackageName());
    if (Build.VERSION.SDK_INT <= 18)
      paramIntent.removeCategory(paramContext.getPackageName()); 
    String str2 = paramIntent.getStringExtra("gcm.rawData64");
    if (str2 != null) {
      paramIntent.putExtra("rawData", Base64.decode(str2, 0));
      paramIntent.removeExtra("gcm.rawData64");
    } 
    if ("google.com/iid".equals(paramIntent.getStringExtra("from")) || "com.google.firebase.INSTANCE_ID_EVENT".equals(paramString)) {
      str1 = "com.google.firebase.INSTANCE_ID_EVENT";
    } else if ("com.google.android.c2dm.intent.RECEIVE".equals(paramString) || "com.google.firebase.MESSAGING_EVENT".equals(paramString)) {
      str1 = "com.google.firebase.MESSAGING_EVENT";
    } 
    int i = -1;
    if (str1 != null)
      i = zza((BroadcastReceiver)this, paramContext, str1, paramIntent); 
    if (isOrderedBroadcast())
      setResultCode(i); 
  }
  
  private static int zzb(BroadcastReceiver paramBroadcastReceiver, Context paramContext, String paramString, Intent paramIntent) {
    if (Log.isLoggable("FirebaseInstanceId", 3)) {
      String str = String.valueOf(paramString);
      if (str.length() != 0) {
        "Binding to service: ".concat(str);
      } else {
        new String("Binding to service: ");
      } 
    } 
    if (paramBroadcastReceiver.isOrderedBroadcast())
      paramBroadcastReceiver.setResultCode(-1); 
    zza(paramContext, paramString).zza(paramIntent, paramBroadcastReceiver.goAsync());
    return -1;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    if (paramIntent == null)
      return; 
    Parcelable parcelable = paramIntent.getParcelableExtra("wrapped_intent");
    if (parcelable instanceof Intent) {
      Intent intent = (Intent)parcelable;
    } else {
      parcelable = null;
    } 
    if (parcelable != null) {
      zza(paramContext, (Intent)parcelable, paramIntent.getAction());
      return;
    } 
    zza(paramContext, paramIntent, paramIntent.getAction());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/FirebaseInstanceIdReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */