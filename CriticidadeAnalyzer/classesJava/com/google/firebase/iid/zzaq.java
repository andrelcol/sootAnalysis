package com.google.firebase.iid;

import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import java.util.Map;
import java.util.concurrent.Executor;

final class zzaq {
  private final Executor zzbj;
  
  private final Map<Pair<String, String>, Task<String>> zzco = (Map<Pair<String, String>, Task<String>>)new ArrayMap();
  
  zzaq(Executor paramExecutor) {
    this.zzbj = paramExecutor;
  }
  
  final Task<String> zza(String paramString1, String paramString2, zzas paramzzas) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new android/util/Pair
    //   5: astore #5
    //   7: aload #5
    //   9: aload_1
    //   10: aload_2
    //   11: invokespecial <init> : (Ljava/lang/Object;Ljava/lang/Object;)V
    //   14: aload_0
    //   15: getfield zzco : Ljava/util/Map;
    //   18: aload #5
    //   20: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   25: checkcast com/google/android/gms/tasks/Task
    //   28: astore_1
    //   29: aload_1
    //   30: ifnull -> 92
    //   33: ldc 'FirebaseInstanceId'
    //   35: iconst_3
    //   36: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   39: ifeq -> 88
    //   42: aload #5
    //   44: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   47: astore_2
    //   48: aload_2
    //   49: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   52: invokevirtual length : ()I
    //   55: istore #4
    //   57: new java/lang/StringBuilder
    //   60: astore_3
    //   61: aload_3
    //   62: iload #4
    //   64: bipush #29
    //   66: iadd
    //   67: invokespecial <init> : (I)V
    //   70: aload_3
    //   71: ldc 'Joining ongoing request for: '
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload_3
    //   78: aload_2
    //   79: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   82: pop
    //   83: aload_3
    //   84: invokevirtual toString : ()Ljava/lang/String;
    //   87: pop
    //   88: aload_0
    //   89: monitorexit
    //   90: aload_1
    //   91: areturn
    //   92: ldc 'FirebaseInstanceId'
    //   94: iconst_3
    //   95: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   98: ifeq -> 147
    //   101: aload #5
    //   103: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   106: astore_2
    //   107: aload_2
    //   108: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   111: invokevirtual length : ()I
    //   114: istore #4
    //   116: new java/lang/StringBuilder
    //   119: astore_1
    //   120: aload_1
    //   121: iload #4
    //   123: bipush #24
    //   125: iadd
    //   126: invokespecial <init> : (I)V
    //   129: aload_1
    //   130: ldc 'Making new request for: '
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: pop
    //   136: aload_1
    //   137: aload_2
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: pop
    //   142: aload_1
    //   143: invokevirtual toString : ()Ljava/lang/String;
    //   146: pop
    //   147: aload_3
    //   148: invokeinterface zzs : ()Lcom/google/android/gms/tasks/Task;
    //   153: astore_1
    //   154: aload_0
    //   155: getfield zzbj : Ljava/util/concurrent/Executor;
    //   158: astore_2
    //   159: new com/google/firebase/iid/zzar
    //   162: astore_3
    //   163: aload_3
    //   164: aload_0
    //   165: aload #5
    //   167: invokespecial <init> : (Lcom/google/firebase/iid/zzaq;Landroid/util/Pair;)V
    //   170: aload_1
    //   171: aload_2
    //   172: aload_3
    //   173: invokevirtual continueWithTask : (Ljava/util/concurrent/Executor;Lcom/google/android/gms/tasks/Continuation;)Lcom/google/android/gms/tasks/Task;
    //   176: astore_1
    //   177: aload_0
    //   178: getfield zzco : Ljava/util/Map;
    //   181: aload #5
    //   183: aload_1
    //   184: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   189: pop
    //   190: aload_0
    //   191: monitorexit
    //   192: aload_1
    //   193: areturn
    //   194: astore_1
    //   195: aload_0
    //   196: monitorexit
    //   197: aload_1
    //   198: athrow
    // Exception table:
    //   from	to	target	type
    //   2	29	194	finally
    //   33	88	194	finally
    //   92	147	194	finally
    //   147	190	194	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */