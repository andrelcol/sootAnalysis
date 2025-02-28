package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import androidx.collection.SimpleArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

final class zzat {
  private static int zzcf;
  
  private static PendingIntent zzcr;
  
  private final zzan zzan;
  
  private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcs = new SimpleArrayMap();
  
  private Messenger zzct;
  
  private Messenger zzcu;
  
  private zzl zzcv;
  
  private final Context zzx;
  
  public zzat(Context paramContext, zzan paramzzan) {
    this.zzx = paramContext;
    this.zzan = paramzzan;
    this.zzct = new Messenger((Handler)new zzau(this, Looper.getMainLooper()));
  }
  
  private static void zza(Context paramContext, Intent paramIntent) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzat
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzat.zzcr : Landroid/app/PendingIntent;
    //   6: ifnonnull -> 34
    //   9: new android/content/Intent
    //   12: astore_2
    //   13: aload_2
    //   14: invokespecial <init> : ()V
    //   17: aload_2
    //   18: ldc 'com.google.example.invalidpackage'
    //   20: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   23: pop
    //   24: aload_0
    //   25: iconst_0
    //   26: aload_2
    //   27: iconst_0
    //   28: invokestatic getBroadcast : (Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   31: putstatic com/google/firebase/iid/zzat.zzcr : Landroid/app/PendingIntent;
    //   34: aload_1
    //   35: ldc 'app'
    //   37: getstatic com/google/firebase/iid/zzat.zzcr : Landroid/app/PendingIntent;
    //   40: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   43: pop
    //   44: ldc com/google/firebase/iid/zzat
    //   46: monitorexit
    //   47: return
    //   48: astore_0
    //   49: ldc com/google/firebase/iid/zzat
    //   51: monitorexit
    //   52: aload_0
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   3	34	48	finally
    //   34	44	48	finally
  }
  
  private final void zza(String paramString, Bundle paramBundle) {
    synchronized (this.zzcs) {
      TaskCompletionSource taskCompletionSource = (TaskCompletionSource)this.zzcs.remove(paramString);
      if (taskCompletionSource == null) {
        paramString = String.valueOf(paramString);
        if (paramString.length() != 0) {
          "Missing callback for ".concat(paramString);
        } else {
          new String("Missing callback for ");
        } 
        return;
      } 
      taskCompletionSource.setResult(paramBundle);
      return;
    } 
  }
  
  private static String zzah() {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzat
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzat.zzcf : I
    //   6: istore_0
    //   7: iload_0
    //   8: iconst_1
    //   9: iadd
    //   10: putstatic com/google/firebase/iid/zzat.zzcf : I
    //   13: iload_0
    //   14: invokestatic toString : (I)Ljava/lang/String;
    //   17: astore_1
    //   18: ldc com/google/firebase/iid/zzat
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: astore_1
    //   24: ldc com/google/firebase/iid/zzat
    //   26: monitorexit
    //   27: aload_1
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   3	18	23	finally
  }
  
  private final void zzb(Message paramMessage) {
    // Byte code:
    //   0: aload_1
    //   1: ifnull -> 537
    //   4: aload_1
    //   5: getfield obj : Ljava/lang/Object;
    //   8: astore_3
    //   9: aload_3
    //   10: instanceof android/content/Intent
    //   13: ifeq -> 537
    //   16: aload_3
    //   17: checkcast android/content/Intent
    //   20: astore_3
    //   21: aload_3
    //   22: new com/google/firebase/iid/zzl$zza
    //   25: dup
    //   26: invokespecial <init> : ()V
    //   29: invokevirtual setExtrasClassLoader : (Ljava/lang/ClassLoader;)V
    //   32: aload_3
    //   33: ldc 'google.messenger'
    //   35: invokevirtual hasExtra : (Ljava/lang/String;)Z
    //   38: ifeq -> 78
    //   41: aload_3
    //   42: ldc 'google.messenger'
    //   44: invokevirtual getParcelableExtra : (Ljava/lang/String;)Landroid/os/Parcelable;
    //   47: astore_3
    //   48: aload_3
    //   49: instanceof com/google/firebase/iid/zzl
    //   52: ifeq -> 63
    //   55: aload_0
    //   56: aload_3
    //   57: checkcast com/google/firebase/iid/zzl
    //   60: putfield zzcv : Lcom/google/firebase/iid/zzl;
    //   63: aload_3
    //   64: instanceof android/os/Messenger
    //   67: ifeq -> 78
    //   70: aload_0
    //   71: aload_3
    //   72: checkcast android/os/Messenger
    //   75: putfield zzcu : Landroid/os/Messenger;
    //   78: aload_1
    //   79: getfield obj : Ljava/lang/Object;
    //   82: checkcast android/content/Intent
    //   85: astore #4
    //   87: aload #4
    //   89: invokevirtual getAction : ()Ljava/lang/String;
    //   92: astore_1
    //   93: ldc 'com.google.android.c2dm.intent.REGISTRATION'
    //   95: aload_1
    //   96: invokevirtual equals : (Ljava/lang/Object;)Z
    //   99: ifne -> 144
    //   102: ldc 'FirebaseInstanceId'
    //   104: iconst_3
    //   105: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   108: ifeq -> 143
    //   111: aload_1
    //   112: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   115: astore_1
    //   116: aload_1
    //   117: invokevirtual length : ()I
    //   120: ifeq -> 133
    //   123: ldc 'Unexpected response action: '
    //   125: aload_1
    //   126: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   129: pop
    //   130: goto -> 143
    //   133: new java/lang/String
    //   136: dup
    //   137: ldc 'Unexpected response action: '
    //   139: invokespecial <init> : (Ljava/lang/String;)V
    //   142: pop
    //   143: return
    //   144: aload #4
    //   146: ldc 'registration_id'
    //   148: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   151: astore_3
    //   152: aload_3
    //   153: astore_1
    //   154: aload_3
    //   155: ifnonnull -> 166
    //   158: aload #4
    //   160: ldc 'unregistered'
    //   162: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   165: astore_1
    //   166: aload_1
    //   167: ifnonnull -> 444
    //   170: aload #4
    //   172: ldc 'error'
    //   174: invokevirtual getStringExtra : (Ljava/lang/String;)Ljava/lang/String;
    //   177: astore_3
    //   178: aload_3
    //   179: ifnonnull -> 230
    //   182: aload #4
    //   184: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   187: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   190: astore_3
    //   191: new java/lang/StringBuilder
    //   194: dup
    //   195: aload_3
    //   196: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   199: invokevirtual length : ()I
    //   202: bipush #49
    //   204: iadd
    //   205: invokespecial <init> : (I)V
    //   208: astore_1
    //   209: aload_1
    //   210: ldc 'Unexpected response, no error or registration id '
    //   212: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: pop
    //   216: aload_1
    //   217: aload_3
    //   218: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload_1
    //   223: invokevirtual toString : ()Ljava/lang/String;
    //   226: pop
    //   227: goto -> 537
    //   230: ldc 'FirebaseInstanceId'
    //   232: iconst_3
    //   233: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   236: ifeq -> 271
    //   239: aload_3
    //   240: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   243: astore_1
    //   244: aload_1
    //   245: invokevirtual length : ()I
    //   248: ifeq -> 261
    //   251: ldc 'Received InstanceID error '
    //   253: aload_1
    //   254: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   257: pop
    //   258: goto -> 271
    //   261: new java/lang/String
    //   264: dup
    //   265: ldc 'Received InstanceID error '
    //   267: invokespecial <init> : (Ljava/lang/String;)V
    //   270: pop
    //   271: aload_3
    //   272: ldc '|'
    //   274: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   277: ifeq -> 388
    //   280: aload_3
    //   281: ldc '\|'
    //   283: invokevirtual split : (Ljava/lang/String;)[Ljava/lang/String;
    //   286: astore_1
    //   287: aload_1
    //   288: arraylength
    //   289: iconst_2
    //   290: if_icmple -> 353
    //   293: ldc 'ID'
    //   295: aload_1
    //   296: iconst_1
    //   297: aaload
    //   298: invokevirtual equals : (Ljava/lang/Object;)Z
    //   301: ifne -> 307
    //   304: goto -> 353
    //   307: aload_1
    //   308: iconst_2
    //   309: aaload
    //   310: astore #5
    //   312: aload_1
    //   313: iconst_3
    //   314: aaload
    //   315: astore_3
    //   316: aload_3
    //   317: astore_1
    //   318: aload_3
    //   319: ldc ':'
    //   321: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   324: ifeq -> 333
    //   327: aload_3
    //   328: iconst_1
    //   329: invokevirtual substring : (I)Ljava/lang/String;
    //   332: astore_1
    //   333: aload_0
    //   334: aload #5
    //   336: aload #4
    //   338: ldc 'error'
    //   340: aload_1
    //   341: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   344: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   347: invokespecial zza : (Ljava/lang/String;Landroid/os/Bundle;)V
    //   350: goto -> 537
    //   353: aload_3
    //   354: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   357: astore_1
    //   358: aload_1
    //   359: invokevirtual length : ()I
    //   362: ifeq -> 375
    //   365: ldc 'Unexpected structured response '
    //   367: aload_1
    //   368: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   371: pop
    //   372: goto -> 537
    //   375: new java/lang/String
    //   378: dup
    //   379: ldc 'Unexpected structured response '
    //   381: invokespecial <init> : (Ljava/lang/String;)V
    //   384: pop
    //   385: goto -> 537
    //   388: aload_0
    //   389: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   392: astore_1
    //   393: aload_1
    //   394: monitorenter
    //   395: iconst_0
    //   396: istore_2
    //   397: iload_2
    //   398: aload_0
    //   399: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   402: invokevirtual size : ()I
    //   405: if_icmpge -> 434
    //   408: aload_0
    //   409: aload_0
    //   410: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   413: iload_2
    //   414: invokevirtual keyAt : (I)Ljava/lang/Object;
    //   417: checkcast java/lang/String
    //   420: aload #4
    //   422: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   425: invokespecial zza : (Ljava/lang/String;Landroid/os/Bundle;)V
    //   428: iinc #2, 1
    //   431: goto -> 397
    //   434: aload_1
    //   435: monitorexit
    //   436: goto -> 537
    //   439: astore_3
    //   440: aload_1
    //   441: monitorexit
    //   442: aload_3
    //   443: athrow
    //   444: ldc '\|ID\|([^|]+)\|:?+(.*)'
    //   446: invokestatic compile : (Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   449: aload_1
    //   450: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   453: astore_3
    //   454: aload_3
    //   455: invokevirtual matches : ()Z
    //   458: ifne -> 503
    //   461: ldc 'FirebaseInstanceId'
    //   463: iconst_3
    //   464: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   467: ifeq -> 502
    //   470: aload_1
    //   471: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   474: astore_1
    //   475: aload_1
    //   476: invokevirtual length : ()I
    //   479: ifeq -> 492
    //   482: ldc 'Unexpected response string: '
    //   484: aload_1
    //   485: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   488: pop
    //   489: goto -> 502
    //   492: new java/lang/String
    //   495: dup
    //   496: ldc 'Unexpected response string: '
    //   498: invokespecial <init> : (Ljava/lang/String;)V
    //   501: pop
    //   502: return
    //   503: aload_3
    //   504: iconst_1
    //   505: invokevirtual group : (I)Ljava/lang/String;
    //   508: astore_1
    //   509: aload_3
    //   510: iconst_2
    //   511: invokevirtual group : (I)Ljava/lang/String;
    //   514: astore_3
    //   515: aload #4
    //   517: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   520: astore #4
    //   522: aload #4
    //   524: ldc 'registration_id'
    //   526: aload_3
    //   527: invokevirtual putString : (Ljava/lang/String;Ljava/lang/String;)V
    //   530: aload_0
    //   531: aload_1
    //   532: aload #4
    //   534: invokespecial zza : (Ljava/lang/String;Landroid/os/Bundle;)V
    //   537: return
    // Exception table:
    //   from	to	target	type
    //   397	428	439	finally
    //   434	436	439	finally
    //   440	442	439	finally
  }
  
  private final Bundle zzd(Bundle paramBundle) throws IOException {
    Bundle bundle2 = zze(paramBundle);
    Bundle bundle1 = bundle2;
    if (bundle2 != null) {
      bundle1 = bundle2;
      if (bundle2.containsKey("google.messenger")) {
        paramBundle = zze(paramBundle);
        bundle1 = paramBundle;
        if (paramBundle != null) {
          bundle1 = paramBundle;
          if (paramBundle.containsKey("google.messenger"))
            bundle1 = null; 
        } 
      } 
    } 
    return bundle1;
  }
  
  private final Bundle zze(Bundle paramBundle) throws IOException {
    // Byte code:
    //   0: invokestatic zzah : ()Ljava/lang/String;
    //   3: astore_2
    //   4: new com/google/android/gms/tasks/TaskCompletionSource
    //   7: dup
    //   8: invokespecial <init> : ()V
    //   11: astore_3
    //   12: aload_0
    //   13: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   16: astore #4
    //   18: aload #4
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   25: aload_2
    //   26: aload_3
    //   27: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   30: pop
    //   31: aload #4
    //   33: monitorexit
    //   34: aload_0
    //   35: getfield zzan : Lcom/google/firebase/iid/zzan;
    //   38: invokevirtual zzac : ()I
    //   41: ifeq -> 423
    //   44: new android/content/Intent
    //   47: dup
    //   48: invokespecial <init> : ()V
    //   51: astore #4
    //   53: aload #4
    //   55: ldc_w 'com.google.android.gms'
    //   58: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   61: pop
    //   62: aload_0
    //   63: getfield zzan : Lcom/google/firebase/iid/zzan;
    //   66: invokevirtual zzac : ()I
    //   69: iconst_2
    //   70: if_icmpne -> 85
    //   73: aload #4
    //   75: ldc_w 'com.google.iid.TOKEN_REQUEST'
    //   78: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   81: pop
    //   82: goto -> 94
    //   85: aload #4
    //   87: ldc_w 'com.google.android.c2dm.intent.REGISTER'
    //   90: invokevirtual setAction : (Ljava/lang/String;)Landroid/content/Intent;
    //   93: pop
    //   94: aload #4
    //   96: aload_1
    //   97: invokevirtual putExtras : (Landroid/os/Bundle;)Landroid/content/Intent;
    //   100: pop
    //   101: aload_0
    //   102: getfield zzx : Landroid/content/Context;
    //   105: aload #4
    //   107: invokestatic zza : (Landroid/content/Context;Landroid/content/Intent;)V
    //   110: new java/lang/StringBuilder
    //   113: dup
    //   114: aload_2
    //   115: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   118: invokevirtual length : ()I
    //   121: iconst_5
    //   122: iadd
    //   123: invokespecial <init> : (I)V
    //   126: astore_1
    //   127: aload_1
    //   128: ldc_w '|ID|'
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: pop
    //   135: aload_1
    //   136: aload_2
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload_1
    //   142: ldc '|'
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload #4
    //   150: ldc_w 'kid'
    //   153: aload_1
    //   154: invokevirtual toString : ()Ljava/lang/String;
    //   157: invokevirtual putExtra : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   160: pop
    //   161: ldc 'FirebaseInstanceId'
    //   163: iconst_3
    //   164: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   167: ifeq -> 220
    //   170: aload #4
    //   172: invokevirtual getExtras : ()Landroid/os/Bundle;
    //   175: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   178: astore_1
    //   179: new java/lang/StringBuilder
    //   182: dup
    //   183: aload_1
    //   184: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   187: invokevirtual length : ()I
    //   190: bipush #8
    //   192: iadd
    //   193: invokespecial <init> : (I)V
    //   196: astore #5
    //   198: aload #5
    //   200: ldc_w 'Sending '
    //   203: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: pop
    //   207: aload #5
    //   209: aload_1
    //   210: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: pop
    //   214: aload #5
    //   216: invokevirtual toString : ()Ljava/lang/String;
    //   219: pop
    //   220: aload #4
    //   222: ldc 'google.messenger'
    //   224: aload_0
    //   225: getfield zzct : Landroid/os/Messenger;
    //   228: invokevirtual putExtra : (Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
    //   231: pop
    //   232: aload_0
    //   233: getfield zzcu : Landroid/os/Messenger;
    //   236: ifnonnull -> 246
    //   239: aload_0
    //   240: getfield zzcv : Lcom/google/firebase/iid/zzl;
    //   243: ifnull -> 293
    //   246: invokestatic obtain : ()Landroid/os/Message;
    //   249: astore_1
    //   250: aload_1
    //   251: aload #4
    //   253: putfield obj : Ljava/lang/Object;
    //   256: aload_0
    //   257: getfield zzcu : Landroid/os/Messenger;
    //   260: ifnull -> 274
    //   263: aload_0
    //   264: getfield zzcu : Landroid/os/Messenger;
    //   267: aload_1
    //   268: invokevirtual send : (Landroid/os/Message;)V
    //   271: goto -> 326
    //   274: aload_0
    //   275: getfield zzcv : Lcom/google/firebase/iid/zzl;
    //   278: aload_1
    //   279: invokevirtual send : (Landroid/os/Message;)V
    //   282: goto -> 326
    //   285: astore_1
    //   286: ldc 'FirebaseInstanceId'
    //   288: iconst_3
    //   289: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   292: pop
    //   293: aload_0
    //   294: getfield zzan : Lcom/google/firebase/iid/zzan;
    //   297: invokevirtual zzac : ()I
    //   300: iconst_2
    //   301: if_icmpne -> 316
    //   304: aload_0
    //   305: getfield zzx : Landroid/content/Context;
    //   308: aload #4
    //   310: invokevirtual sendBroadcast : (Landroid/content/Intent;)V
    //   313: goto -> 326
    //   316: aload_0
    //   317: getfield zzx : Landroid/content/Context;
    //   320: aload #4
    //   322: invokevirtual startService : (Landroid/content/Intent;)Landroid/content/ComponentName;
    //   325: pop
    //   326: aload_3
    //   327: invokevirtual getTask : ()Lcom/google/android/gms/tasks/Task;
    //   330: ldc2_w 30000
    //   333: getstatic java/util/concurrent/TimeUnit.MILLISECONDS : Ljava/util/concurrent/TimeUnit;
    //   336: invokestatic await : (Lcom/google/android/gms/tasks/Task;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   339: checkcast android/os/Bundle
    //   342: astore_3
    //   343: aload_0
    //   344: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   347: astore_1
    //   348: aload_1
    //   349: monitorenter
    //   350: aload_0
    //   351: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   354: aload_2
    //   355: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   358: pop
    //   359: aload_1
    //   360: monitorexit
    //   361: aload_3
    //   362: areturn
    //   363: astore_2
    //   364: aload_1
    //   365: monitorexit
    //   366: aload_2
    //   367: athrow
    //   368: astore_3
    //   369: goto -> 398
    //   372: astore_3
    //   373: new java/io/IOException
    //   376: astore_1
    //   377: aload_1
    //   378: aload_3
    //   379: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   382: aload_1
    //   383: athrow
    //   384: astore_1
    //   385: new java/io/IOException
    //   388: astore_1
    //   389: aload_1
    //   390: ldc_w 'TIMEOUT'
    //   393: invokespecial <init> : (Ljava/lang/String;)V
    //   396: aload_1
    //   397: athrow
    //   398: aload_0
    //   399: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   402: astore_1
    //   403: aload_1
    //   404: monitorenter
    //   405: aload_0
    //   406: getfield zzcs : Landroidx/collection/SimpleArrayMap;
    //   409: aload_2
    //   410: invokevirtual remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   413: pop
    //   414: aload_1
    //   415: monitorexit
    //   416: aload_3
    //   417: athrow
    //   418: astore_2
    //   419: aload_1
    //   420: monitorexit
    //   421: aload_2
    //   422: athrow
    //   423: new java/io/IOException
    //   426: dup
    //   427: ldc_w 'MISSING_INSTANCEID_SERVICE'
    //   430: invokespecial <init> : (Ljava/lang/String;)V
    //   433: athrow
    //   434: astore_1
    //   435: aload #4
    //   437: monitorexit
    //   438: aload_1
    //   439: athrow
    // Exception table:
    //   from	to	target	type
    //   21	34	434	finally
    //   256	271	285	android/os/RemoteException
    //   274	282	285	android/os/RemoteException
    //   326	343	384	java/lang/InterruptedException
    //   326	343	384	java/util/concurrent/TimeoutException
    //   326	343	372	java/util/concurrent/ExecutionException
    //   326	343	368	finally
    //   350	361	363	finally
    //   364	366	363	finally
    //   373	384	368	finally
    //   385	398	368	finally
    //   405	416	418	finally
    //   419	421	418	finally
    //   435	438	434	finally
  }
  
  final Bundle zzc(Bundle paramBundle) throws IOException {
    if (this.zzan.zzaf() >= 12000000) {
      Task<Bundle> task = zzab.zzc(this.zzx).zzb(1, paramBundle);
      try {
        return (Bundle)Tasks.await(task);
      } catch (InterruptedException interruptedException) {
      
      } catch (ExecutionException executionException) {}
      if (Log.isLoggable("FirebaseInstanceId", 3)) {
        String str = String.valueOf(executionException);
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 22);
        stringBuilder.append("Error making request: ");
        stringBuilder.append(str);
        stringBuilder.toString();
      } 
      return (executionException.getCause() instanceof zzal && ((zzal)executionException.getCause()).getErrorCode() == 4) ? zzd(paramBundle) : null;
    } 
    return zzd(paramBundle);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */