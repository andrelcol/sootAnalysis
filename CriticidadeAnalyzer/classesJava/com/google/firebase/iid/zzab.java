package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.ScheduledExecutorService;

public final class zzab {
  private static zzab zzbt;
  
  private final ScheduledExecutorService zzbu;
  
  private zzad zzbv = new zzad(this, null);
  
  private int zzbw = 1;
  
  private final Context zzx;
  
  private zzab(Context paramContext, ScheduledExecutorService paramScheduledExecutorService) {
    this.zzbu = paramScheduledExecutorService;
    this.zzx = paramContext.getApplicationContext();
  }
  
  private final <T> Task<T> zza(zzak<T> paramzzak) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'MessengerIpcClient'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: ifeq -> 57
    //   11: aload_1
    //   12: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   15: astore #4
    //   17: aload #4
    //   19: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   22: invokevirtual length : ()I
    //   25: istore_2
    //   26: new java/lang/StringBuilder
    //   29: astore_3
    //   30: aload_3
    //   31: iload_2
    //   32: bipush #9
    //   34: iadd
    //   35: invokespecial <init> : (I)V
    //   38: aload_3
    //   39: ldc 'Queueing '
    //   41: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: aload_3
    //   46: aload #4
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: pop
    //   52: aload_3
    //   53: invokevirtual toString : ()Ljava/lang/String;
    //   56: pop
    //   57: aload_0
    //   58: getfield zzbv : Lcom/google/firebase/iid/zzad;
    //   61: aload_1
    //   62: invokevirtual zzb : (Lcom/google/firebase/iid/zzak;)Z
    //   65: ifne -> 92
    //   68: new com/google/firebase/iid/zzad
    //   71: astore_3
    //   72: aload_3
    //   73: aload_0
    //   74: aconst_null
    //   75: invokespecial <init> : (Lcom/google/firebase/iid/zzab;Lcom/google/firebase/iid/zzac;)V
    //   78: aload_0
    //   79: aload_3
    //   80: putfield zzbv : Lcom/google/firebase/iid/zzad;
    //   83: aload_0
    //   84: getfield zzbv : Lcom/google/firebase/iid/zzad;
    //   87: aload_1
    //   88: invokevirtual zzb : (Lcom/google/firebase/iid/zzak;)Z
    //   91: pop
    //   92: aload_1
    //   93: getfield zzcg : Lcom/google/android/gms/tasks/TaskCompletionSource;
    //   96: invokevirtual getTask : ()Lcom/google/android/gms/tasks/Task;
    //   99: astore_1
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_1
    //   103: areturn
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: athrow
    // Exception table:
    //   from	to	target	type
    //   2	57	104	finally
    //   57	92	104	finally
    //   92	100	104	finally
  }
  
  public static zzab zzc(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/zzab
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/zzab.zzbt : Lcom/google/firebase/iid/zzab;
    //   6: ifnonnull -> 36
    //   9: new com/google/firebase/iid/zzab
    //   12: astore_1
    //   13: new com/google/android/gms/common/util/concurrent/NamedThreadFactory
    //   16: astore_2
    //   17: aload_2
    //   18: ldc 'MessengerIpcClient'
    //   20: invokespecial <init> : (Ljava/lang/String;)V
    //   23: aload_1
    //   24: aload_0
    //   25: aload_2
    //   26: invokestatic newSingleThreadScheduledExecutor : (Ljava/util/concurrent/ThreadFactory;)Ljava/util/concurrent/ScheduledExecutorService;
    //   29: invokespecial <init> : (Landroid/content/Context;Ljava/util/concurrent/ScheduledExecutorService;)V
    //   32: aload_1
    //   33: putstatic com/google/firebase/iid/zzab.zzbt : Lcom/google/firebase/iid/zzab;
    //   36: getstatic com/google/firebase/iid/zzab.zzbt : Lcom/google/firebase/iid/zzab;
    //   39: astore_0
    //   40: ldc com/google/firebase/iid/zzab
    //   42: monitorexit
    //   43: aload_0
    //   44: areturn
    //   45: astore_0
    //   46: ldc com/google/firebase/iid/zzab
    //   48: monitorexit
    //   49: aload_0
    //   50: athrow
    // Exception table:
    //   from	to	target	type
    //   3	36	45	finally
    //   36	40	45	finally
  }
  
  private final int zzx() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzbw : I
    //   6: istore_1
    //   7: aload_0
    //   8: iload_1
    //   9: iconst_1
    //   10: iadd
    //   11: putfield zzbw : I
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_1
    //   17: ireturn
    //   18: astore_2
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_2
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	18	finally
  }
  
  public final Task<Bundle> zzb(int paramInt, Bundle paramBundle) {
    return zza(new zzam(zzx(), 1, paramBundle));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */