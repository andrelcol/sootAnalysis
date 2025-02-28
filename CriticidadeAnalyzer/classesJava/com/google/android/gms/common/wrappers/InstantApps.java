package com.google.android.gms.common.wrappers;

import android.content.Context;

public class InstantApps {
  private static Context zzhv;
  
  private static Boolean zzhw;
  
  public static boolean isInstantApp(Context paramContext) {
    // Byte code:
    //   0: ldc com/google/android/gms/common/wrappers/InstantApps
    //   2: monitorenter
    //   3: aload_0
    //   4: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   7: astore_2
    //   8: getstatic com/google/android/gms/common/wrappers/InstantApps.zzhv : Landroid/content/Context;
    //   11: ifnull -> 39
    //   14: getstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   17: ifnull -> 39
    //   20: getstatic com/google/android/gms/common/wrappers/InstantApps.zzhv : Landroid/content/Context;
    //   23: aload_2
    //   24: if_acmpne -> 39
    //   27: getstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   30: invokevirtual booleanValue : ()Z
    //   33: istore_1
    //   34: ldc com/google/android/gms/common/wrappers/InstantApps
    //   36: monitorexit
    //   37: iload_1
    //   38: ireturn
    //   39: aconst_null
    //   40: putstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   43: invokestatic isAtLeastO : ()Z
    //   46: ifeq -> 65
    //   49: aload_2
    //   50: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   53: invokevirtual isInstantApp : ()Z
    //   56: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   59: putstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   62: goto -> 93
    //   65: aload_0
    //   66: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   69: ldc 'com.google.android.instantapps.supervisor.InstantAppsRuntime'
    //   71: invokevirtual loadClass : (Ljava/lang/String;)Ljava/lang/Class;
    //   74: pop
    //   75: iconst_1
    //   76: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   79: putstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   82: goto -> 93
    //   85: astore_0
    //   86: iconst_0
    //   87: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   90: putstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   93: aload_2
    //   94: putstatic com/google/android/gms/common/wrappers/InstantApps.zzhv : Landroid/content/Context;
    //   97: getstatic com/google/android/gms/common/wrappers/InstantApps.zzhw : Ljava/lang/Boolean;
    //   100: invokevirtual booleanValue : ()Z
    //   103: istore_1
    //   104: ldc com/google/android/gms/common/wrappers/InstantApps
    //   106: monitorexit
    //   107: iload_1
    //   108: ireturn
    //   109: astore_0
    //   110: ldc com/google/android/gms/common/wrappers/InstantApps
    //   112: monitorexit
    //   113: aload_0
    //   114: athrow
    // Exception table:
    //   from	to	target	type
    //   3	34	109	finally
    //   39	62	109	finally
    //   65	82	85	java/lang/ClassNotFoundException
    //   65	82	109	finally
    //   86	93	109	finally
    //   93	104	109	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/wrappers/InstantApps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */