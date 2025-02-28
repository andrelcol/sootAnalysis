package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayDeque;
import java.util.Queue;

public final class zzav {
  private static zzav zzcx;
  
  private final SimpleArrayMap<String, String> zzcy = new SimpleArrayMap();
  
  private Boolean zzcz = null;
  
  final Queue<Intent> zzda = new ArrayDeque<Intent>();
  
  private final Queue<Intent> zzdb = new ArrayDeque<Intent>();
  
  private static Intent zza(Context paramContext, String paramString, Intent paramIntent) {
    Intent intent = new Intent(paramContext, FirebaseInstanceIdReceiver.class);
    intent.setAction(paramString);
    intent.putExtra("wrapped_intent", (Parcelable)paramIntent);
    return intent;
  }
  
  public static zzav zzai() {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzav
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzav.zzcx : Lcom/google/firebase/iid/zzav;
    //   6: ifnonnull -> 21
    //   9: new com/google/firebase/iid/zzav
    //   12: astore_0
    //   13: aload_0
    //   14: invokespecial <init> : ()V
    //   17: aload_0
    //   18: putstatic com/google/firebase/iid/zzav.zzcx : Lcom/google/firebase/iid/zzav;
    //   21: getstatic com/google/firebase/iid/zzav.zzcx : Lcom/google/firebase/iid/zzav;
    //   24: astore_0
    //   25: ldc com/google/firebase/iid/zzav
    //   27: monitorexit
    //   28: aload_0
    //   29: areturn
    //   30: astore_0
    //   31: ldc com/google/firebase/iid/zzav
    //   33: monitorexit
    //   34: aload_0
    //   35: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	30	finally
    //   21	25	30	finally
  }
  
  public static void zzb(Context paramContext, Intent paramIntent) {
    paramContext.sendBroadcast(zza(paramContext, "com.google.firebase.INSTANCE_ID_EVENT", paramIntent));
  }
  
  public static void zzc(Context paramContext, Intent paramIntent) {
    paramContext.sendBroadcast(zza(paramContext, "com.google.firebase.MESSAGING_EVENT", paramIntent));
  }
  
  private final int zzd(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzcy : Landroidx/collection/SimpleArrayMap;
    //   4: astore #4
    //   6: aload #4
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield zzcy : Landroidx/collection/SimpleArrayMap;
    //   13: aload_2
    //   14: invokevirtual getAction : ()Ljava/lang/String;
    //   17: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast java/lang/String
    //   23: astore #5
    //   25: aload #4
    //   27: monitorexit
    //   28: iconst_0
    //   29: istore_3
    //   30: aload #5
    //   32: astore #4
    //   34: aload #5
    //   36: ifnonnull -> 283
    //   39: aload_1
    //   40: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   43: aload_2
    //   44: iconst_0
    //   45: invokevirtual resolveService : (Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
    //   48: astore #4
    //   50: aload #4
    //   52: ifnull -> 339
    //   55: aload #4
    //   57: getfield serviceInfo : Landroid/content/pm/ServiceInfo;
    //   60: astore #5
    //   62: aload #5
    //   64: ifnonnull -> 70
    //   67: goto -> 339
    //   70: aload_1
    //   71: invokevirtual getPackageName : ()Ljava/lang/String;
    //   74: aload #5
    //   76: getfield packageName : Ljava/lang/String;
    //   79: invokevirtual equals : (Ljava/lang/Object;)Z
    //   82: ifeq -> 199
    //   85: aload #5
    //   87: getfield name : Ljava/lang/String;
    //   90: ifnonnull -> 96
    //   93: goto -> 199
    //   96: aload #5
    //   98: getfield name : Ljava/lang/String;
    //   101: astore #5
    //   103: aload #5
    //   105: astore #4
    //   107: aload #5
    //   109: ldc '.'
    //   111: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   114: ifeq -> 164
    //   117: aload_1
    //   118: invokevirtual getPackageName : ()Ljava/lang/String;
    //   121: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   124: astore #4
    //   126: aload #5
    //   128: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   131: astore #5
    //   133: aload #5
    //   135: invokevirtual length : ()I
    //   138: ifeq -> 153
    //   141: aload #4
    //   143: aload #5
    //   145: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   148: astore #4
    //   150: goto -> 164
    //   153: new java/lang/String
    //   156: dup
    //   157: aload #4
    //   159: invokespecial <init> : (Ljava/lang/String;)V
    //   162: astore #4
    //   164: aload_0
    //   165: getfield zzcy : Landroidx/collection/SimpleArrayMap;
    //   168: astore #5
    //   170: aload #5
    //   172: monitorenter
    //   173: aload_0
    //   174: getfield zzcy : Landroidx/collection/SimpleArrayMap;
    //   177: aload_2
    //   178: invokevirtual getAction : ()Ljava/lang/String;
    //   181: aload #4
    //   183: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   186: pop
    //   187: aload #5
    //   189: monitorexit
    //   190: goto -> 283
    //   193: astore_1
    //   194: aload #5
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    //   199: aload #5
    //   201: getfield packageName : Ljava/lang/String;
    //   204: astore #4
    //   206: aload #5
    //   208: getfield name : Ljava/lang/String;
    //   211: astore #5
    //   213: new java/lang/StringBuilder
    //   216: dup
    //   217: aload #4
    //   219: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   222: invokevirtual length : ()I
    //   225: bipush #94
    //   227: iadd
    //   228: aload #5
    //   230: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   233: invokevirtual length : ()I
    //   236: iadd
    //   237: invokespecial <init> : (I)V
    //   240: astore #6
    //   242: aload #6
    //   244: ldc 'Error resolving target intent service, skipping classname enforcement. Resolved service was: '
    //   246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: pop
    //   250: aload #6
    //   252: aload #4
    //   254: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   257: pop
    //   258: aload #6
    //   260: ldc '/'
    //   262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: pop
    //   266: aload #6
    //   268: aload #5
    //   270: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: pop
    //   274: aload #6
    //   276: invokevirtual toString : ()Ljava/lang/String;
    //   279: pop
    //   280: goto -> 339
    //   283: ldc 'FirebaseInstanceId'
    //   285: iconst_3
    //   286: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   289: ifeq -> 328
    //   292: aload #4
    //   294: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   297: astore #5
    //   299: aload #5
    //   301: invokevirtual length : ()I
    //   304: ifeq -> 318
    //   307: ldc 'Restricting intent to a specific service: '
    //   309: aload #5
    //   311: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   314: pop
    //   315: goto -> 328
    //   318: new java/lang/String
    //   321: dup
    //   322: ldc 'Restricting intent to a specific service: '
    //   324: invokespecial <init> : (Ljava/lang/String;)V
    //   327: pop
    //   328: aload_2
    //   329: aload_1
    //   330: invokevirtual getPackageName : ()Ljava/lang/String;
    //   333: aload #4
    //   335: invokevirtual setClassName : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   338: pop
    //   339: aload_0
    //   340: getfield zzcz : Ljava/lang/Boolean;
    //   343: ifnonnull -> 365
    //   346: aload_1
    //   347: ldc 'android.permission.WAKE_LOCK'
    //   349: invokevirtual checkCallingOrSelfPermission : (Ljava/lang/String;)I
    //   352: ifne -> 357
    //   355: iconst_1
    //   356: istore_3
    //   357: aload_0
    //   358: iload_3
    //   359: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   362: putfield zzcz : Ljava/lang/Boolean;
    //   365: aload_0
    //   366: getfield zzcz : Ljava/lang/Boolean;
    //   369: invokevirtual booleanValue : ()Z
    //   372: ifeq -> 384
    //   375: aload_1
    //   376: aload_2
    //   377: invokestatic startWakefulService : (Landroid/content/Context;Landroid/content/Intent;)Landroid/content/ComponentName;
    //   380: astore_1
    //   381: goto -> 390
    //   384: aload_1
    //   385: aload_2
    //   386: invokevirtual startService : (Landroid/content/Intent;)Landroid/content/ComponentName;
    //   389: astore_1
    //   390: aload_1
    //   391: ifnonnull -> 398
    //   394: sipush #404
    //   397: ireturn
    //   398: iconst_m1
    //   399: ireturn
    //   400: astore_1
    //   401: aload_1
    //   402: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   405: astore_2
    //   406: new java/lang/StringBuilder
    //   409: dup
    //   410: aload_2
    //   411: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   414: invokevirtual length : ()I
    //   417: bipush #45
    //   419: iadd
    //   420: invokespecial <init> : (I)V
    //   423: astore_1
    //   424: aload_1
    //   425: ldc 'Failed to start service while in background: '
    //   427: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   430: pop
    //   431: aload_1
    //   432: aload_2
    //   433: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: pop
    //   437: aload_1
    //   438: invokevirtual toString : ()Ljava/lang/String;
    //   441: pop
    //   442: sipush #402
    //   445: ireturn
    //   446: astore_1
    //   447: sipush #401
    //   450: ireturn
    //   451: astore_1
    //   452: aload #4
    //   454: monitorexit
    //   455: aload_1
    //   456: athrow
    // Exception table:
    //   from	to	target	type
    //   9	28	451	finally
    //   173	190	193	finally
    //   194	197	193	finally
    //   339	346	446	java/lang/SecurityException
    //   339	346	400	java/lang/IllegalStateException
    //   346	355	446	java/lang/SecurityException
    //   346	355	400	java/lang/IllegalStateException
    //   357	365	446	java/lang/SecurityException
    //   357	365	400	java/lang/IllegalStateException
    //   365	381	446	java/lang/SecurityException
    //   365	381	400	java/lang/IllegalStateException
    //   384	390	446	java/lang/SecurityException
    //   384	390	400	java/lang/IllegalStateException
    //   452	455	451	finally
  }
  
  public final int zzb(Context paramContext, String paramString, Intent paramIntent) {
    String str;
    if (Log.isLoggable("FirebaseInstanceId", 3)) {
      String str1 = String.valueOf(paramString);
      if (str1.length() != 0) {
        "Starting service: ".concat(str1);
      } else {
        new String("Starting service: ");
      } 
    } 
    byte b = -1;
    int i = paramString.hashCode();
    if (i != -842411455) {
      if (i == 41532704 && paramString.equals("com.google.firebase.MESSAGING_EVENT"))
        b = 1; 
    } else if (paramString.equals("com.google.firebase.INSTANCE_ID_EVENT")) {
      b = 0;
    } 
    if (b != 0) {
      if (b != 1) {
        str = String.valueOf(paramString);
        if (str.length() != 0) {
          "Unknown service action: ".concat(str);
        } else {
          new String("Unknown service action: ");
        } 
        return 500;
      } 
      this.zzdb.offer(paramIntent);
    } else {
      this.zzda.offer(paramIntent);
    } 
    Intent intent = new Intent(paramString);
    intent.setPackage(str.getPackageName());
    return zzd((Context)str, intent);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzav.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */