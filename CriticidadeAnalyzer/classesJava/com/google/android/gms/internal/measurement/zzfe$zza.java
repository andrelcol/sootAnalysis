package com.google.android.gms.internal.measurement;

public final class zzfe$zza extends zzuo<zzfe$zza, zzfe$zza.zza> implements zzvx {
  private static final zzfe$zza zzauy = new zzfe$zza();
  
  private static volatile zzwf<zzfe$zza> zznw;
  
  private String zzauw = "";
  
  private long zzaux;
  
  private int zznr;
  
  static {
    zzuo.zza(zzfe$zza.class, zzauy);
  }
  
  protected final Object zza(int paramInt, Object<zzfe$zza> paramObject1, Object<zzfe$zza> paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzff.zznq : [I
    //   3: iload_1
    //   4: iconst_1
    //   5: isub
    //   6: iaload
    //   7: tableswitch default -> 48, 1 -> 156, 2 -> 147, 3 -> 119, 4 -> 115, 5 -> 63, 6 -> 58, 7 -> 56
    //   48: new java/lang/UnsupportedOperationException
    //   51: dup
    //   52: invokespecial <init> : ()V
    //   55: athrow
    //   56: aconst_null
    //   57: areturn
    //   58: iconst_1
    //   59: invokestatic valueOf : (B)Ljava/lang/Byte;
    //   62: areturn
    //   63: getstatic com/google/android/gms/internal/measurement/zzfe$zza.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   66: astore_3
    //   67: aload_3
    //   68: astore_2
    //   69: aload_3
    //   70: ifnonnull -> 113
    //   73: ldc com/google/android/gms/internal/measurement/zzfe$zza
    //   75: monitorenter
    //   76: getstatic com/google/android/gms/internal/measurement/zzfe$zza.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   79: astore_3
    //   80: aload_3
    //   81: astore_2
    //   82: aload_3
    //   83: ifnonnull -> 101
    //   86: new com/google/android/gms/internal/measurement/zzuo$zzb
    //   89: astore_2
    //   90: aload_2
    //   91: getstatic com/google/android/gms/internal/measurement/zzfe$zza.zzauy : Lcom/google/android/gms/internal/measurement/zzfe$zza;
    //   94: invokespecial <init> : (Lcom/google/android/gms/internal/measurement/zzuo;)V
    //   97: aload_2
    //   98: putstatic com/google/android/gms/internal/measurement/zzfe$zza.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   101: ldc com/google/android/gms/internal/measurement/zzfe$zza
    //   103: monitorexit
    //   104: goto -> 113
    //   107: astore_2
    //   108: ldc com/google/android/gms/internal/measurement/zzfe$zza
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    //   113: aload_2
    //   114: areturn
    //   115: getstatic com/google/android/gms/internal/measurement/zzfe$zza.zzauy : Lcom/google/android/gms/internal/measurement/zzfe$zza;
    //   118: areturn
    //   119: getstatic com/google/android/gms/internal/measurement/zzfe$zza.zzauy : Lcom/google/android/gms/internal/measurement/zzfe$zza;
    //   122: ldc '    \\b '
    //   124: iconst_3
    //   125: anewarray java/lang/Object
    //   128: dup
    //   129: iconst_0
    //   130: ldc 'zznr'
    //   132: aastore
    //   133: dup
    //   134: iconst_1
    //   135: ldc 'zzauw'
    //   137: aastore
    //   138: dup
    //   139: iconst_2
    //   140: ldc 'zzaux'
    //   142: aastore
    //   143: invokestatic zza : (Lcom/google/android/gms/internal/measurement/zzvv;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   146: areturn
    //   147: new com/google/android/gms/internal/measurement/zzfe$zza$zza
    //   150: dup
    //   151: aconst_null
    //   152: invokespecial <init> : (Lcom/google/android/gms/internal/measurement/zzff;)V
    //   155: areturn
    //   156: new com/google/android/gms/internal/measurement/zzfe$zza
    //   159: dup
    //   160: invokespecial <init> : ()V
    //   163: areturn
    // Exception table:
    //   from	to	target	type
    //   76	80	107	finally
    //   86	101	107	finally
    //   101	104	107	finally
    //   108	111	107	finally
  }
  
  public static final class zza extends zzuo.zza<zzfe$zza, zza> implements zzvx {
    private zza() {
      super(zzfe$zza.zzmo());
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfe$zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */