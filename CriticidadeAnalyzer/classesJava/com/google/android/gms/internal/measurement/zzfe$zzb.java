package com.google.android.gms.internal.measurement;

public final class zzfe$zzb extends zzuo<zzfe$zzb, zzfe$zzb.zza> implements zzvx {
  private static final zzfe$zzb zzavb = new zzfe$zzb();
  
  private static volatile zzwf<zzfe$zzb> zznw;
  
  private int zzauz = 1;
  
  private zzuu<zzfe$zza> zzava = zzuo.zzwg();
  
  private int zznr;
  
  static {
    zzuo.zza(zzfe$zzb.class, zzavb);
  }
  
  public static zzwf<zzfe$zzb> zza() {
    return (zzwf<zzfe$zzb>)zzavb.zza(zzuo.zze.zzbys, null, null);
  }
  
  protected final Object zza(int paramInt, Object<zzfe$zzb> paramObject1, Object<zzfe$zzb> paramObject2) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzff.zznq : [I
    //   3: iload_1
    //   4: iconst_1
    //   5: isub
    //   6: iaload
    //   7: tableswitch default -> 48, 1 -> 169, 2 -> 160, 3 -> 119, 4 -> 115, 5 -> 63, 6 -> 58, 7 -> 56
    //   48: new java/lang/UnsupportedOperationException
    //   51: dup
    //   52: invokespecial <init> : ()V
    //   55: athrow
    //   56: aconst_null
    //   57: areturn
    //   58: iconst_1
    //   59: invokestatic valueOf : (B)Ljava/lang/Byte;
    //   62: areturn
    //   63: getstatic com/google/android/gms/internal/measurement/zzfe$zzb.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   66: astore_3
    //   67: aload_3
    //   68: astore_2
    //   69: aload_3
    //   70: ifnonnull -> 113
    //   73: ldc com/google/android/gms/internal/measurement/zzfe$zzb
    //   75: monitorenter
    //   76: getstatic com/google/android/gms/internal/measurement/zzfe$zzb.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   79: astore_3
    //   80: aload_3
    //   81: astore_2
    //   82: aload_3
    //   83: ifnonnull -> 101
    //   86: new com/google/android/gms/internal/measurement/zzuo$zzb
    //   89: astore_2
    //   90: aload_2
    //   91: getstatic com/google/android/gms/internal/measurement/zzfe$zzb.zzavb : Lcom/google/android/gms/internal/measurement/zzfe$zzb;
    //   94: invokespecial <init> : (Lcom/google/android/gms/internal/measurement/zzuo;)V
    //   97: aload_2
    //   98: putstatic com/google/android/gms/internal/measurement/zzfe$zzb.zznw : Lcom/google/android/gms/internal/measurement/zzwf;
    //   101: ldc com/google/android/gms/internal/measurement/zzfe$zzb
    //   103: monitorexit
    //   104: goto -> 113
    //   107: astore_2
    //   108: ldc com/google/android/gms/internal/measurement/zzfe$zzb
    //   110: monitorexit
    //   111: aload_2
    //   112: athrow
    //   113: aload_2
    //   114: areturn
    //   115: getstatic com/google/android/gms/internal/measurement/zzfe$zzb.zzavb : Lcom/google/android/gms/internal/measurement/zzfe$zzb;
    //   118: areturn
    //   119: invokestatic zzd : ()Lcom/google/android/gms/internal/measurement/zzut;
    //   122: astore_2
    //   123: getstatic com/google/android/gms/internal/measurement/zzfe$zzb.zzavb : Lcom/google/android/gms/internal/measurement/zzfe$zzb;
    //   126: ldc '   \\f '
    //   128: iconst_5
    //   129: anewarray java/lang/Object
    //   132: dup
    //   133: iconst_0
    //   134: ldc 'zznr'
    //   136: aastore
    //   137: dup
    //   138: iconst_1
    //   139: ldc 'zzauz'
    //   141: aastore
    //   142: dup
    //   143: iconst_2
    //   144: aload_2
    //   145: aastore
    //   146: dup
    //   147: iconst_3
    //   148: ldc 'zzava'
    //   150: aastore
    //   151: dup
    //   152: iconst_4
    //   153: ldc com/google/android/gms/internal/measurement/zzfe$zza
    //   155: aastore
    //   156: invokestatic zza : (Lcom/google/android/gms/internal/measurement/zzvv;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   159: areturn
    //   160: new com/google/android/gms/internal/measurement/zzfe$zzb$zza
    //   163: dup
    //   164: aconst_null
    //   165: invokespecial <init> : (Lcom/google/android/gms/internal/measurement/zzff;)V
    //   168: areturn
    //   169: new com/google/android/gms/internal/measurement/zzfe$zzb
    //   172: dup
    //   173: invokespecial <init> : ()V
    //   176: areturn
    // Exception table:
    //   from	to	target	type
    //   76	80	107	finally
    //   86	101	107	finally
    //   101	104	107	finally
    //   108	111	107	finally
  }
  
  public static final class zza extends zzuo.zza<zzfe$zzb, zza> implements zzvx {
    private zza() {
      super(zzfe$zzb.zzmq());
    }
  }
  
  public enum zzb implements zzur {
    zzavc(1),
    zzavd(2);
    
    private static final zzb[] zzave = new zzb[] { zzavc, zzavd };
    
    private final int value;
    
    static {
      new zzfg();
    }
    
    zzb(int param1Int1) {
      this.value = param1Int1;
    }
    
    public static zzut zzd() {
      return zzfh.zzoc;
    }
    
    public static zzb zzt(int param1Int) {
      return (param1Int != 1) ? ((param1Int != 2) ? null : zzavd) : zzavc;
    }
    
    public final int zzc() {
      return this.value;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzfe$zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */