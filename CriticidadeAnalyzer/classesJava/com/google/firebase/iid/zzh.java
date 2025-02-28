package com.google.firebase.iid;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public final class zzh implements ServiceConnection {
  private final Queue<zzd> zzaa = new ArrayDeque<zzd>();
  
  private zzf zzab;
  
  private boolean zzac = false;
  
  private final Context zzx;
  
  private final Intent zzy;
  
  private final ScheduledExecutorService zzz;
  
  public zzh(Context paramContext, String paramString) {
    this(paramContext, paramString, new ScheduledThreadPoolExecutor(0, (ThreadFactory)new NamedThreadFactory("Firebase-FirebaseInstanceIdServiceConnection")));
  }
  
  private zzh(Context paramContext, String paramString, ScheduledExecutorService paramScheduledExecutorService) {
    this.zzx = paramContext.getApplicationContext();
    this.zzy = (new Intent(paramString)).setPackage(this.zzx.getPackageName());
    this.zzz = paramScheduledExecutorService;
  }
  
  private final void zzc() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: pop
    //   9: aload_0
    //   10: getfield zzaa : Ljava/util/Queue;
    //   13: invokeinterface isEmpty : ()Z
    //   18: ifne -> 176
    //   21: ldc 'EnhancedIntentService'
    //   23: iconst_3
    //   24: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   27: pop
    //   28: aload_0
    //   29: getfield zzab : Lcom/google/firebase/iid/zzf;
    //   32: ifnull -> 76
    //   35: aload_0
    //   36: getfield zzab : Lcom/google/firebase/iid/zzf;
    //   39: invokevirtual isBinderAlive : ()Z
    //   42: ifeq -> 76
    //   45: ldc 'EnhancedIntentService'
    //   47: iconst_3
    //   48: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   51: pop
    //   52: aload_0
    //   53: getfield zzaa : Ljava/util/Queue;
    //   56: invokeinterface poll : ()Ljava/lang/Object;
    //   61: checkcast com/google/firebase/iid/zzd
    //   64: astore_2
    //   65: aload_0
    //   66: getfield zzab : Lcom/google/firebase/iid/zzf;
    //   69: aload_2
    //   70: invokevirtual zza : (Lcom/google/firebase/iid/zzd;)V
    //   73: goto -> 9
    //   76: ldc 'EnhancedIntentService'
    //   78: iconst_3
    //   79: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   82: ifeq -> 127
    //   85: aload_0
    //   86: getfield zzac : Z
    //   89: ifne -> 97
    //   92: iconst_1
    //   93: istore_1
    //   94: goto -> 99
    //   97: iconst_0
    //   98: istore_1
    //   99: new java/lang/StringBuilder
    //   102: astore_2
    //   103: aload_2
    //   104: bipush #39
    //   106: invokespecial <init> : (I)V
    //   109: aload_2
    //   110: ldc 'binder is dead. start connection? '
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   115: pop
    //   116: aload_2
    //   117: iload_1
    //   118: invokevirtual append : (Z)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload_2
    //   123: invokevirtual toString : ()Ljava/lang/String;
    //   126: pop
    //   127: aload_0
    //   128: getfield zzac : Z
    //   131: ifne -> 173
    //   134: aload_0
    //   135: iconst_1
    //   136: putfield zzac : Z
    //   139: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   142: aload_0
    //   143: getfield zzx : Landroid/content/Context;
    //   146: aload_0
    //   147: getfield zzy : Landroid/content/Intent;
    //   150: aload_0
    //   151: bipush #65
    //   153: invokevirtual bindService : (Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   156: istore_1
    //   157: iload_1
    //   158: ifeq -> 164
    //   161: aload_0
    //   162: monitorexit
    //   163: return
    //   164: aload_0
    //   165: iconst_0
    //   166: putfield zzac : Z
    //   169: aload_0
    //   170: invokespecial zzd : ()V
    //   173: aload_0
    //   174: monitorexit
    //   175: return
    //   176: aload_0
    //   177: monitorexit
    //   178: return
    //   179: astore_2
    //   180: aload_0
    //   181: monitorexit
    //   182: aload_2
    //   183: athrow
    //   184: astore_2
    //   185: goto -> 164
    // Exception table:
    //   from	to	target	type
    //   2	9	179	finally
    //   9	73	179	finally
    //   76	92	179	finally
    //   99	127	179	finally
    //   127	139	179	finally
    //   139	157	184	java/lang/SecurityException
    //   139	157	179	finally
    //   164	173	179	finally
  }
  
  private final void zzd() {
    while (!this.zzaa.isEmpty())
      ((zzd)this.zzaa.poll()).finish(); 
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield zzac : Z
    //   7: aload_0
    //   8: aload_2
    //   9: checkcast com/google/firebase/iid/zzf
    //   12: putfield zzab : Lcom/google/firebase/iid/zzf;
    //   15: ldc 'EnhancedIntentService'
    //   17: iconst_3
    //   18: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   21: ifeq -> 70
    //   24: aload_1
    //   25: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   28: astore #4
    //   30: aload #4
    //   32: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   35: invokevirtual length : ()I
    //   38: istore_3
    //   39: new java/lang/StringBuilder
    //   42: astore_1
    //   43: aload_1
    //   44: iload_3
    //   45: bipush #20
    //   47: iadd
    //   48: invokespecial <init> : (I)V
    //   51: aload_1
    //   52: ldc 'onServiceConnected: '
    //   54: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   57: pop
    //   58: aload_1
    //   59: aload #4
    //   61: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: pop
    //   65: aload_1
    //   66: invokevirtual toString : ()Ljava/lang/String;
    //   69: pop
    //   70: aload_2
    //   71: ifnonnull -> 81
    //   74: aload_0
    //   75: invokespecial zzd : ()V
    //   78: goto -> 85
    //   81: aload_0
    //   82: invokespecial zzc : ()V
    //   85: aload_0
    //   86: monitorexit
    //   87: return
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Exception table:
    //   from	to	target	type
    //   2	70	88	finally
    //   74	78	88	finally
    //   81	85	88	finally
    //   85	87	88	finally
    //   89	91	88	finally
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    if (Log.isLoggable("EnhancedIntentService", 3)) {
      String str = String.valueOf(paramComponentName);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 23);
      stringBuilder.append("onServiceDisconnected: ");
      stringBuilder.append(str);
      stringBuilder.toString();
    } 
    zzc();
  }
  
  public final void zza(Intent paramIntent, BroadcastReceiver.PendingResult paramPendingResult) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: pop
    //   9: aload_0
    //   10: getfield zzaa : Ljava/util/Queue;
    //   13: astore #4
    //   15: new com/google/firebase/iid/zzd
    //   18: astore_3
    //   19: aload_3
    //   20: aload_1
    //   21: aload_2
    //   22: aload_0
    //   23: getfield zzz : Ljava/util/concurrent/ScheduledExecutorService;
    //   26: invokespecial <init> : (Landroid/content/Intent;Landroid/content/BroadcastReceiver$PendingResult;Ljava/util/concurrent/ScheduledExecutorService;)V
    //   29: aload #4
    //   31: aload_3
    //   32: invokeinterface add : (Ljava/lang/Object;)Z
    //   37: pop
    //   38: aload_0
    //   39: invokespecial zzc : ()V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	42	45	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */