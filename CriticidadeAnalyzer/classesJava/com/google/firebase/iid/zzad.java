package com.google.firebase.iid;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.SparseArray;
import com.google.android.gms.internal.firebase_messaging.zza;
import java.util.ArrayDeque;
import java.util.Queue;

final class zzad implements ServiceConnection {
  int state = 0;
  
  final Messenger zzbx = new Messenger((Handler)new zza(Looper.getMainLooper(), new zzae(this)));
  
  zzai zzby;
  
  final Queue<zzak<?>> zzbz = new ArrayDeque<zzak<?>>();
  
  final SparseArray<zzak<?>> zzca = new SparseArray();
  
  final zzab zzcb;
  
  private zzad(zzab paramzzab) {}
  
  private final void zzy() {
    zzab.zzb(this.zzcb).execute(new zzag(this));
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'MessengerIpcClient'
    //   4: iconst_2
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: pop
    //   9: aload_2
    //   10: ifnonnull -> 23
    //   13: aload_0
    //   14: iconst_0
    //   15: ldc 'Null service connection'
    //   17: invokevirtual zza : (ILjava/lang/String;)V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: new com/google/firebase/iid/zzai
    //   26: astore_1
    //   27: aload_1
    //   28: aload_2
    //   29: invokespecial <init> : (Landroid/os/IBinder;)V
    //   32: aload_0
    //   33: aload_1
    //   34: putfield zzby : Lcom/google/firebase/iid/zzai;
    //   37: aload_0
    //   38: iconst_2
    //   39: putfield state : I
    //   42: aload_0
    //   43: invokespecial zzy : ()V
    //   46: aload_0
    //   47: monitorexit
    //   48: return
    //   49: astore_1
    //   50: aload_0
    //   51: iconst_0
    //   52: aload_1
    //   53: invokevirtual getMessage : ()Ljava/lang/String;
    //   56: invokevirtual zza : (ILjava/lang/String;)V
    //   59: aload_0
    //   60: monitorexit
    //   61: return
    //   62: astore_1
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_1
    //   66: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	62	finally
    //   13	20	62	finally
    //   23	37	49	android/os/RemoteException
    //   23	37	62	finally
    //   37	46	62	finally
    //   50	59	62	finally
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'MessengerIpcClient'
    //   4: iconst_2
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: pop
    //   9: aload_0
    //   10: iconst_2
    //   11: ldc 'Service disconnected'
    //   13: invokevirtual zza : (ILjava/lang/String;)V
    //   16: aload_0
    //   17: monitorexit
    //   18: return
    //   19: astore_1
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	19	finally
  }
  
  final void zza(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzca : Landroid/util/SparseArray;
    //   6: iload_1
    //   7: invokevirtual get : (I)Ljava/lang/Object;
    //   10: checkcast com/google/firebase/iid/zzak
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull -> 74
    //   18: new java/lang/StringBuilder
    //   21: astore_3
    //   22: aload_3
    //   23: bipush #31
    //   25: invokespecial <init> : (I)V
    //   28: aload_3
    //   29: ldc 'Timing out request: '
    //   31: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: pop
    //   35: aload_3
    //   36: iload_1
    //   37: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   40: pop
    //   41: aload_3
    //   42: invokevirtual toString : ()Ljava/lang/String;
    //   45: pop
    //   46: aload_0
    //   47: getfield zzca : Landroid/util/SparseArray;
    //   50: iload_1
    //   51: invokevirtual remove : (I)V
    //   54: new com/google/firebase/iid/zzal
    //   57: astore_3
    //   58: aload_3
    //   59: iconst_3
    //   60: ldc 'Timed out waiting for response'
    //   62: invokespecial <init> : (ILjava/lang/String;)V
    //   65: aload_2
    //   66: aload_3
    //   67: invokevirtual zza : (Lcom/google/firebase/iid/zzal;)V
    //   70: aload_0
    //   71: invokevirtual zzz : ()V
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_2
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_2
    //   81: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	77	finally
    //   18	74	77	finally
  }
  
  final void zza(int paramInt, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'MessengerIpcClient'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: ifeq -> 46
    //   11: aload_2
    //   12: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   15: astore #4
    //   17: aload #4
    //   19: invokevirtual length : ()I
    //   22: ifeq -> 36
    //   25: ldc 'Disconnected: '
    //   27: aload #4
    //   29: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   32: pop
    //   33: goto -> 46
    //   36: new java/lang/String
    //   39: dup
    //   40: ldc 'Disconnected: '
    //   42: invokespecial <init> : (Ljava/lang/String;)V
    //   45: pop
    //   46: aload_0
    //   47: getfield state : I
    //   50: istore_3
    //   51: iload_3
    //   52: ifeq -> 261
    //   55: iload_3
    //   56: iconst_1
    //   57: if_icmpeq -> 133
    //   60: iload_3
    //   61: iconst_2
    //   62: if_icmpeq -> 133
    //   65: iload_3
    //   66: iconst_3
    //   67: if_icmpeq -> 125
    //   70: iload_3
    //   71: iconst_4
    //   72: if_icmpne -> 78
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: new java/lang/IllegalStateException
    //   81: astore_2
    //   82: aload_0
    //   83: getfield state : I
    //   86: istore_1
    //   87: new java/lang/StringBuilder
    //   90: astore #4
    //   92: aload #4
    //   94: bipush #26
    //   96: invokespecial <init> : (I)V
    //   99: aload #4
    //   101: ldc 'Unknown state: '
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: pop
    //   107: aload #4
    //   109: iload_1
    //   110: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   113: pop
    //   114: aload_2
    //   115: aload #4
    //   117: invokevirtual toString : ()Ljava/lang/String;
    //   120: invokespecial <init> : (Ljava/lang/String;)V
    //   123: aload_2
    //   124: athrow
    //   125: aload_0
    //   126: iconst_4
    //   127: putfield state : I
    //   130: aload_0
    //   131: monitorexit
    //   132: return
    //   133: ldc 'MessengerIpcClient'
    //   135: iconst_2
    //   136: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   139: pop
    //   140: aload_0
    //   141: iconst_4
    //   142: putfield state : I
    //   145: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   148: aload_0
    //   149: getfield zzcb : Lcom/google/firebase/iid/zzab;
    //   152: invokestatic zza : (Lcom/google/firebase/iid/zzab;)Landroid/content/Context;
    //   155: aload_0
    //   156: invokevirtual unbindService : (Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   159: new com/google/firebase/iid/zzal
    //   162: astore #4
    //   164: aload #4
    //   166: iload_1
    //   167: aload_2
    //   168: invokespecial <init> : (ILjava/lang/String;)V
    //   171: aload_0
    //   172: getfield zzbz : Ljava/util/Queue;
    //   175: invokeinterface iterator : ()Ljava/util/Iterator;
    //   180: astore_2
    //   181: aload_2
    //   182: invokeinterface hasNext : ()Z
    //   187: ifeq -> 207
    //   190: aload_2
    //   191: invokeinterface next : ()Ljava/lang/Object;
    //   196: checkcast com/google/firebase/iid/zzak
    //   199: aload #4
    //   201: invokevirtual zza : (Lcom/google/firebase/iid/zzal;)V
    //   204: goto -> 181
    //   207: aload_0
    //   208: getfield zzbz : Ljava/util/Queue;
    //   211: invokeinterface clear : ()V
    //   216: iconst_0
    //   217: istore_1
    //   218: iload_1
    //   219: aload_0
    //   220: getfield zzca : Landroid/util/SparseArray;
    //   223: invokevirtual size : ()I
    //   226: if_icmpge -> 251
    //   229: aload_0
    //   230: getfield zzca : Landroid/util/SparseArray;
    //   233: iload_1
    //   234: invokevirtual valueAt : (I)Ljava/lang/Object;
    //   237: checkcast com/google/firebase/iid/zzak
    //   240: aload #4
    //   242: invokevirtual zza : (Lcom/google/firebase/iid/zzal;)V
    //   245: iinc #1, 1
    //   248: goto -> 218
    //   251: aload_0
    //   252: getfield zzca : Landroid/util/SparseArray;
    //   255: invokevirtual clear : ()V
    //   258: aload_0
    //   259: monitorexit
    //   260: return
    //   261: new java/lang/IllegalStateException
    //   264: astore_2
    //   265: aload_2
    //   266: invokespecial <init> : ()V
    //   269: aload_2
    //   270: athrow
    //   271: astore_2
    //   272: aload_0
    //   273: monitorexit
    //   274: aload_2
    //   275: athrow
    // Exception table:
    //   from	to	target	type
    //   2	33	271	finally
    //   36	46	271	finally
    //   46	51	271	finally
    //   78	125	271	finally
    //   125	130	271	finally
    //   133	181	271	finally
    //   181	204	271	finally
    //   207	216	271	finally
    //   218	245	271	finally
    //   251	258	271	finally
    //   261	271	271	finally
  }
  
  final boolean zza(Message paramMessage) {
    // Byte code:
    //   0: aload_1
    //   1: getfield arg1 : I
    //   4: istore_2
    //   5: ldc 'MessengerIpcClient'
    //   7: iconst_3
    //   8: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   11: ifeq -> 42
    //   14: new java/lang/StringBuilder
    //   17: dup
    //   18: bipush #41
    //   20: invokespecial <init> : (I)V
    //   23: astore_3
    //   24: aload_3
    //   25: ldc 'Received response to request: '
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: pop
    //   31: aload_3
    //   32: iload_2
    //   33: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: aload_3
    //   38: invokevirtual toString : ()Ljava/lang/String;
    //   41: pop
    //   42: aload_0
    //   43: monitorenter
    //   44: aload_0
    //   45: getfield zzca : Landroid/util/SparseArray;
    //   48: iload_2
    //   49: invokevirtual get : (I)Ljava/lang/Object;
    //   52: checkcast com/google/firebase/iid/zzak
    //   55: astore_3
    //   56: aload_3
    //   57: ifnonnull -> 92
    //   60: new java/lang/StringBuilder
    //   63: astore_1
    //   64: aload_1
    //   65: bipush #50
    //   67: invokespecial <init> : (I)V
    //   70: aload_1
    //   71: ldc 'Received response for unknown request: '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload_1
    //   78: iload_2
    //   79: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload_1
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: pop
    //   88: aload_0
    //   89: monitorexit
    //   90: iconst_1
    //   91: ireturn
    //   92: aload_0
    //   93: getfield zzca : Landroid/util/SparseArray;
    //   96: iload_2
    //   97: invokevirtual remove : (I)V
    //   100: aload_0
    //   101: invokevirtual zzz : ()V
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: invokevirtual getData : ()Landroid/os/Bundle;
    //   110: astore_1
    //   111: aload_1
    //   112: ldc 'unsupported'
    //   114: iconst_0
    //   115: invokevirtual getBoolean : (Ljava/lang/String;Z)Z
    //   118: ifeq -> 138
    //   121: aload_3
    //   122: new com/google/firebase/iid/zzal
    //   125: dup
    //   126: iconst_4
    //   127: ldc 'Not supported by GmsCore'
    //   129: invokespecial <init> : (ILjava/lang/String;)V
    //   132: invokevirtual zza : (Lcom/google/firebase/iid/zzal;)V
    //   135: goto -> 143
    //   138: aload_3
    //   139: aload_1
    //   140: invokevirtual zzb : (Landroid/os/Bundle;)V
    //   143: iconst_1
    //   144: ireturn
    //   145: astore_1
    //   146: aload_0
    //   147: monitorexit
    //   148: aload_1
    //   149: athrow
    // Exception table:
    //   from	to	target	type
    //   44	56	145	finally
    //   60	90	145	finally
    //   92	106	145	finally
    //   146	148	145	finally
  }
  
  final void zzaa() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield state : I
    //   6: iconst_1
    //   7: if_icmpne -> 17
    //   10: aload_0
    //   11: iconst_1
    //   12: ldc 'Timed out while binding'
    //   14: invokevirtual zza : (ILjava/lang/String;)V
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  final boolean zzb(zzak paramzzak) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield state : I
    //   6: istore_2
    //   7: iload_2
    //   8: ifeq -> 117
    //   11: iload_2
    //   12: iconst_1
    //   13: if_icmpeq -> 102
    //   16: iload_2
    //   17: iconst_2
    //   18: if_icmpeq -> 83
    //   21: iload_2
    //   22: iconst_3
    //   23: if_icmpeq -> 79
    //   26: iload_2
    //   27: iconst_4
    //   28: if_icmpne -> 34
    //   31: goto -> 79
    //   34: new java/lang/IllegalStateException
    //   37: astore #4
    //   39: aload_0
    //   40: getfield state : I
    //   43: istore_2
    //   44: new java/lang/StringBuilder
    //   47: astore_1
    //   48: aload_1
    //   49: bipush #26
    //   51: invokespecial <init> : (I)V
    //   54: aload_1
    //   55: ldc 'Unknown state: '
    //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: pop
    //   61: aload_1
    //   62: iload_2
    //   63: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   66: pop
    //   67: aload #4
    //   69: aload_1
    //   70: invokevirtual toString : ()Ljava/lang/String;
    //   73: invokespecial <init> : (Ljava/lang/String;)V
    //   76: aload #4
    //   78: athrow
    //   79: aload_0
    //   80: monitorexit
    //   81: iconst_0
    //   82: ireturn
    //   83: aload_0
    //   84: getfield zzbz : Ljava/util/Queue;
    //   87: aload_1
    //   88: invokeinterface add : (Ljava/lang/Object;)Z
    //   93: pop
    //   94: aload_0
    //   95: invokespecial zzy : ()V
    //   98: aload_0
    //   99: monitorexit
    //   100: iconst_1
    //   101: ireturn
    //   102: aload_0
    //   103: getfield zzbz : Ljava/util/Queue;
    //   106: aload_1
    //   107: invokeinterface add : (Ljava/lang/Object;)Z
    //   112: pop
    //   113: aload_0
    //   114: monitorexit
    //   115: iconst_1
    //   116: ireturn
    //   117: aload_0
    //   118: getfield zzbz : Ljava/util/Queue;
    //   121: aload_1
    //   122: invokeinterface add : (Ljava/lang/Object;)Z
    //   127: pop
    //   128: aload_0
    //   129: getfield state : I
    //   132: ifne -> 140
    //   135: iconst_1
    //   136: istore_3
    //   137: goto -> 142
    //   140: iconst_0
    //   141: istore_3
    //   142: iload_3
    //   143: invokestatic checkState : (Z)V
    //   146: ldc 'MessengerIpcClient'
    //   148: iconst_2
    //   149: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   152: pop
    //   153: aload_0
    //   154: iconst_1
    //   155: putfield state : I
    //   158: new android/content/Intent
    //   161: astore_1
    //   162: aload_1
    //   163: ldc_w 'com.google.android.c2dm.intent.REGISTER'
    //   166: invokespecial <init> : (Ljava/lang/String;)V
    //   169: aload_1
    //   170: ldc_w 'com.google.android.gms'
    //   173: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   176: pop
    //   177: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   180: aload_0
    //   181: getfield zzcb : Lcom/google/firebase/iid/zzab;
    //   184: invokestatic zza : (Lcom/google/firebase/iid/zzab;)Landroid/content/Context;
    //   187: aload_1
    //   188: aload_0
    //   189: iconst_1
    //   190: invokevirtual bindService : (Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   193: ifne -> 207
    //   196: aload_0
    //   197: iconst_0
    //   198: ldc_w 'Unable to bind to service'
    //   201: invokevirtual zza : (ILjava/lang/String;)V
    //   204: goto -> 241
    //   207: aload_0
    //   208: getfield zzcb : Lcom/google/firebase/iid/zzab;
    //   211: invokestatic zzb : (Lcom/google/firebase/iid/zzab;)Ljava/util/concurrent/ScheduledExecutorService;
    //   214: astore_1
    //   215: new com/google/firebase/iid/zzaf
    //   218: astore #4
    //   220: aload #4
    //   222: aload_0
    //   223: invokespecial <init> : (Lcom/google/firebase/iid/zzad;)V
    //   226: aload_1
    //   227: aload #4
    //   229: ldc2_w 30
    //   232: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   235: invokeinterface schedule : (Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)Ljava/util/concurrent/ScheduledFuture;
    //   240: pop
    //   241: aload_0
    //   242: monitorexit
    //   243: iconst_1
    //   244: ireturn
    //   245: astore_1
    //   246: aload_0
    //   247: monitorexit
    //   248: aload_1
    //   249: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	245	finally
    //   34	79	245	finally
    //   83	98	245	finally
    //   102	113	245	finally
    //   117	135	245	finally
    //   142	204	245	finally
    //   207	241	245	finally
  }
  
  final void zzz() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield state : I
    //   6: iconst_2
    //   7: if_icmpne -> 58
    //   10: aload_0
    //   11: getfield zzbz : Ljava/util/Queue;
    //   14: invokeinterface isEmpty : ()Z
    //   19: ifeq -> 58
    //   22: aload_0
    //   23: getfield zzca : Landroid/util/SparseArray;
    //   26: invokevirtual size : ()I
    //   29: ifne -> 58
    //   32: ldc 'MessengerIpcClient'
    //   34: iconst_2
    //   35: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   38: pop
    //   39: aload_0
    //   40: iconst_3
    //   41: putfield state : I
    //   44: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   47: aload_0
    //   48: getfield zzcb : Lcom/google/firebase/iid/zzab;
    //   51: invokestatic zza : (Lcom/google/firebase/iid/zzab;)Landroid/content/Context;
    //   54: aload_0
    //   55: invokevirtual unbindService : (Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: astore_1
    //   62: aload_0
    //   63: monitorexit
    //   64: aload_1
    //   65: athrow
    // Exception table:
    //   from	to	target	type
    //   2	58	61	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */