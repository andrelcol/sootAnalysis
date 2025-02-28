package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.UserManager;

public class zzrw {
  private static volatile UserManager zzbrb;
  
  private static volatile boolean zzbrc = zztj() ^ true;
  
  public static boolean isUserUnlocked(Context paramContext) {
    return (!zztj() || zzab(paramContext));
  }
  
  @TargetApi(24)
  private static boolean zzab(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzrw.zzbrc : Z
    //   3: istore_3
    //   4: iload_3
    //   5: istore_2
    //   6: iload_3
    //   7: ifne -> 108
    //   10: iload_3
    //   11: istore_2
    //   12: iconst_1
    //   13: istore_1
    //   14: iload_2
    //   15: istore_3
    //   16: iload_1
    //   17: iconst_2
    //   18: if_icmpgt -> 96
    //   21: aload_0
    //   22: invokestatic zzac : (Landroid/content/Context;)Landroid/os/UserManager;
    //   25: astore #4
    //   27: aload #4
    //   29: ifnonnull -> 38
    //   32: iconst_1
    //   33: putstatic com/google/android/gms/internal/measurement/zzrw.zzbrc : Z
    //   36: iconst_1
    //   37: ireturn
    //   38: iload_2
    //   39: istore_3
    //   40: aload #4
    //   42: invokevirtual isUserUnlocked : ()Z
    //   45: ifne -> 69
    //   48: iload_2
    //   49: istore_3
    //   50: aload #4
    //   52: invokestatic myUserHandle : ()Landroid/os/UserHandle;
    //   55: invokevirtual isUserRunning : (Landroid/os/UserHandle;)Z
    //   58: ifne -> 64
    //   61: goto -> 69
    //   64: iconst_0
    //   65: istore_2
    //   66: goto -> 71
    //   69: iconst_1
    //   70: istore_2
    //   71: iload_2
    //   72: istore_3
    //   73: iload_2
    //   74: putstatic com/google/android/gms/internal/measurement/zzrw.zzbrc : Z
    //   77: iload_2
    //   78: istore_3
    //   79: goto -> 96
    //   82: astore #4
    //   84: aconst_null
    //   85: putstatic com/google/android/gms/internal/measurement/zzrw.zzbrb : Landroid/os/UserManager;
    //   88: iinc #1, 1
    //   91: iload_3
    //   92: istore_2
    //   93: goto -> 14
    //   96: iload_3
    //   97: istore_2
    //   98: iload_3
    //   99: ifeq -> 108
    //   102: aconst_null
    //   103: putstatic com/google/android/gms/internal/measurement/zzrw.zzbrb : Landroid/os/UserManager;
    //   106: iload_3
    //   107: istore_2
    //   108: iload_2
    //   109: ireturn
    // Exception table:
    //   from	to	target	type
    //   40	48	82	java/lang/NullPointerException
    //   50	61	82	java/lang/NullPointerException
    //   73	77	82	java/lang/NullPointerException
  }
  
  @TargetApi(24)
  private static UserManager zzac(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzrw.zzbrb : Landroid/os/UserManager;
    //   3: astore_2
    //   4: aload_2
    //   5: astore_1
    //   6: aload_2
    //   7: ifnonnull -> 49
    //   10: ldc com/google/android/gms/internal/measurement/zzrw
    //   12: monitorenter
    //   13: getstatic com/google/android/gms/internal/measurement/zzrw.zzbrb : Landroid/os/UserManager;
    //   16: astore_2
    //   17: aload_2
    //   18: astore_1
    //   19: aload_2
    //   20: ifnonnull -> 37
    //   23: aload_0
    //   24: ldc android/os/UserManager
    //   26: invokevirtual getSystemService : (Ljava/lang/Class;)Ljava/lang/Object;
    //   29: checkcast android/os/UserManager
    //   32: astore_1
    //   33: aload_1
    //   34: putstatic com/google/android/gms/internal/measurement/zzrw.zzbrb : Landroid/os/UserManager;
    //   37: ldc com/google/android/gms/internal/measurement/zzrw
    //   39: monitorexit
    //   40: goto -> 49
    //   43: astore_0
    //   44: ldc com/google/android/gms/internal/measurement/zzrw
    //   46: monitorexit
    //   47: aload_0
    //   48: athrow
    //   49: aload_1
    //   50: areturn
    // Exception table:
    //   from	to	target	type
    //   13	17	43	finally
    //   23	37	43	finally
    //   37	40	43	finally
    //   44	47	43	finally
  }
  
  public static boolean zztj() {
    return (Build.VERSION.SDK_INT >= 24);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzrw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */