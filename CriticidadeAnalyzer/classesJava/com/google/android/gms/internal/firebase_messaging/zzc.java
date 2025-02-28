package com.google.android.gms.internal.firebase_messaging;

public final class zzc {
  private static final zzd zzb;
  
  static {
    // Byte code:
    //   0: invokestatic zza : ()Ljava/lang/Integer;
    //   3: astore_1
    //   4: aload_1
    //   5: ifnull -> 28
    //   8: aload_1
    //   9: invokevirtual intValue : ()I
    //   12: bipush #19
    //   14: if_icmplt -> 28
    //   17: new com/google/android/gms/internal/firebase_messaging/zzh
    //   20: astore_0
    //   21: aload_0
    //   22: invokespecial <init> : ()V
    //   25: goto -> 142
    //   28: ldc 'com.google.devtools.build.android.desugar.runtime.twr_disable_mimic'
    //   30: invokestatic getBoolean : (Ljava/lang/String;)Z
    //   33: iconst_1
    //   34: ixor
    //   35: ifeq -> 49
    //   38: new com/google/android/gms/internal/firebase_messaging/zzg
    //   41: dup
    //   42: invokespecial <init> : ()V
    //   45: astore_0
    //   46: goto -> 142
    //   49: new com/google/android/gms/internal/firebase_messaging/zzc$zza
    //   52: dup
    //   53: invokespecial <init> : ()V
    //   56: astore_0
    //   57: goto -> 142
    //   60: astore_0
    //   61: goto -> 67
    //   64: astore_0
    //   65: aconst_null
    //   66: astore_1
    //   67: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   70: astore_3
    //   71: ldc com/google/android/gms/internal/firebase_messaging/zzc$zza
    //   73: invokevirtual getName : ()Ljava/lang/String;
    //   76: astore #4
    //   78: new java/lang/StringBuilder
    //   81: dup
    //   82: aload #4
    //   84: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   87: invokevirtual length : ()I
    //   90: sipush #132
    //   93: iadd
    //   94: invokespecial <init> : (I)V
    //   97: astore_2
    //   98: aload_2
    //   99: ldc 'An error has occured when initializing the try-with-resources desuguring strategy. The default strategy '
    //   101: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload_2
    //   106: aload #4
    //   108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: aload_2
    //   113: ldc 'will be used. The error is: '
    //   115: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload_3
    //   120: aload_2
    //   121: invokevirtual toString : ()Ljava/lang/String;
    //   124: invokevirtual println : (Ljava/lang/String;)V
    //   127: aload_0
    //   128: getstatic java/lang/System.err : Ljava/io/PrintStream;
    //   131: invokevirtual printStackTrace : (Ljava/io/PrintStream;)V
    //   134: new com/google/android/gms/internal/firebase_messaging/zzc$zza
    //   137: dup
    //   138: invokespecial <init> : ()V
    //   141: astore_0
    //   142: aload_0
    //   143: putstatic com/google/android/gms/internal/firebase_messaging/zzc.zzb : Lcom/google/android/gms/internal/firebase_messaging/zzd;
    //   146: aload_1
    //   147: ifnonnull -> 153
    //   150: goto -> 158
    //   153: aload_1
    //   154: invokevirtual intValue : ()I
    //   157: pop
    //   158: return
    // Exception table:
    //   from	to	target	type
    //   0	4	64	finally
    //   8	25	60	finally
    //   28	46	60	finally
    //   49	57	60	finally
  }
  
  private static Integer zza() {
    try {
      return (Integer)Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null);
    } catch (Exception exception) {
      System.err.println("Failed to retrieve value from android.os.Build$VERSION.SDK_INT due to the following exception.");
      exception.printStackTrace(System.err);
      return null;
    } 
  }
  
  public static void zza(Throwable paramThrowable1, Throwable paramThrowable2) {
    zzb.zza(paramThrowable1, paramThrowable2);
  }
  
  static final class zza extends zzd {
    public final void zza(Throwable param1Throwable1, Throwable param1Throwable2) {}
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/firebase_messaging/zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */