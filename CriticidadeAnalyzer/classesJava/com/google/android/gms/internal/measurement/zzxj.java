package com.google.android.gms.internal.measurement;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

final class zzxj {
  private static final Logger logger;
  
  private static final Class<?> zzbto;
  
  private static final boolean zzbuo;
  
  private static final Unsafe zzcar;
  
  private static final boolean zzcco;
  
  private static final boolean zzccp;
  
  private static final zzd zzccq;
  
  private static final boolean zzccr;
  
  private static final long zzccs;
  
  private static final long zzcdf;
  
  private static final boolean zzcdg;
  
  static {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/measurement/zzxj
    //   2: invokevirtual getName : ()Ljava/lang/String;
    //   5: invokestatic getLogger : (Ljava/lang/String;)Ljava/util/logging/Logger;
    //   8: putstatic com/google/android/gms/internal/measurement/zzxj.logger : Ljava/util/logging/Logger;
    //   11: invokestatic zzyq : ()Lsun/misc/Unsafe;
    //   14: putstatic com/google/android/gms/internal/measurement/zzxj.zzcar : Lsun/misc/Unsafe;
    //   17: invokestatic zzuc : ()Ljava/lang/Class;
    //   20: putstatic com/google/android/gms/internal/measurement/zzxj.zzbto : Ljava/lang/Class;
    //   23: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
    //   26: invokestatic zzn : (Ljava/lang/Class;)Z
    //   29: putstatic com/google/android/gms/internal/measurement/zzxj.zzcco : Z
    //   32: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   35: invokestatic zzn : (Ljava/lang/Class;)Z
    //   38: putstatic com/google/android/gms/internal/measurement/zzxj.zzccp : Z
    //   41: getstatic com/google/android/gms/internal/measurement/zzxj.zzcar : Lsun/misc/Unsafe;
    //   44: astore #4
    //   46: aconst_null
    //   47: astore_3
    //   48: aload #4
    //   50: ifnonnull -> 56
    //   53: goto -> 113
    //   56: invokestatic zzub : ()Z
    //   59: ifeq -> 102
    //   62: getstatic com/google/android/gms/internal/measurement/zzxj.zzcco : Z
    //   65: ifeq -> 82
    //   68: new com/google/android/gms/internal/measurement/zzxj$zzb
    //   71: dup
    //   72: getstatic com/google/android/gms/internal/measurement/zzxj.zzcar : Lsun/misc/Unsafe;
    //   75: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   78: astore_3
    //   79: goto -> 113
    //   82: getstatic com/google/android/gms/internal/measurement/zzxj.zzccp : Z
    //   85: ifeq -> 113
    //   88: new com/google/android/gms/internal/measurement/zzxj$zza
    //   91: dup
    //   92: getstatic com/google/android/gms/internal/measurement/zzxj.zzcar : Lsun/misc/Unsafe;
    //   95: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   98: astore_3
    //   99: goto -> 113
    //   102: new com/google/android/gms/internal/measurement/zzxj$zzc
    //   105: dup
    //   106: getstatic com/google/android/gms/internal/measurement/zzxj.zzcar : Lsun/misc/Unsafe;
    //   109: invokespecial <init> : (Lsun/misc/Unsafe;)V
    //   112: astore_3
    //   113: aload_3
    //   114: putstatic com/google/android/gms/internal/measurement/zzxj.zzccq : Lcom/google/android/gms/internal/measurement/zzxj$zzd;
    //   117: invokestatic zzys : ()Z
    //   120: putstatic com/google/android/gms/internal/measurement/zzxj.zzccr : Z
    //   123: invokestatic zzyr : ()Z
    //   126: putstatic com/google/android/gms/internal/measurement/zzxj.zzbuo : Z
    //   129: ldc [B
    //   131: invokestatic zzl : (Ljava/lang/Class;)I
    //   134: i2l
    //   135: putstatic com/google/android/gms/internal/measurement/zzxj.zzccs : J
    //   138: ldc [Z
    //   140: invokestatic zzl : (Ljava/lang/Class;)I
    //   143: pop
    //   144: ldc [Z
    //   146: invokestatic zzm : (Ljava/lang/Class;)I
    //   149: pop
    //   150: ldc [I
    //   152: invokestatic zzl : (Ljava/lang/Class;)I
    //   155: pop
    //   156: ldc [I
    //   158: invokestatic zzm : (Ljava/lang/Class;)I
    //   161: pop
    //   162: ldc [J
    //   164: invokestatic zzl : (Ljava/lang/Class;)I
    //   167: pop
    //   168: ldc [J
    //   170: invokestatic zzm : (Ljava/lang/Class;)I
    //   173: pop
    //   174: ldc [F
    //   176: invokestatic zzl : (Ljava/lang/Class;)I
    //   179: pop
    //   180: ldc [F
    //   182: invokestatic zzm : (Ljava/lang/Class;)I
    //   185: pop
    //   186: ldc [D
    //   188: invokestatic zzl : (Ljava/lang/Class;)I
    //   191: pop
    //   192: ldc [D
    //   194: invokestatic zzm : (Ljava/lang/Class;)I
    //   197: pop
    //   198: ldc [Ljava/lang/Object;
    //   200: invokestatic zzl : (Ljava/lang/Class;)I
    //   203: pop
    //   204: ldc [Ljava/lang/Object;
    //   206: invokestatic zzm : (Ljava/lang/Class;)I
    //   209: pop
    //   210: invokestatic zzyt : ()Ljava/lang/reflect/Field;
    //   213: astore_3
    //   214: aload_3
    //   215: ifnull -> 244
    //   218: getstatic com/google/android/gms/internal/measurement/zzxj.zzccq : Lcom/google/android/gms/internal/measurement/zzxj$zzd;
    //   221: astore #4
    //   223: aload #4
    //   225: ifnonnull -> 231
    //   228: goto -> 244
    //   231: aload #4
    //   233: getfield zzcdh : Lsun/misc/Unsafe;
    //   236: aload_3
    //   237: invokevirtual objectFieldOffset : (Ljava/lang/reflect/Field;)J
    //   240: lstore_0
    //   241: goto -> 248
    //   244: ldc2_w -1
    //   247: lstore_0
    //   248: lload_0
    //   249: putstatic com/google/android/gms/internal/measurement/zzxj.zzcdf : J
    //   252: invokestatic nativeOrder : ()Ljava/nio/ByteOrder;
    //   255: getstatic java/nio/ByteOrder.BIG_ENDIAN : Ljava/nio/ByteOrder;
    //   258: if_acmpne -> 266
    //   261: iconst_1
    //   262: istore_2
    //   263: goto -> 268
    //   266: iconst_0
    //   267: istore_2
    //   268: iload_2
    //   269: putstatic com/google/android/gms/internal/measurement/zzxj.zzcdg : Z
    //   272: return
  }
  
  static byte zza(byte[] paramArrayOfbyte, long paramLong) {
    return zzccq.zzy(paramArrayOfbyte, zzccs + paramLong);
  }
  
  static void zza(long paramLong, byte paramByte) {
    zzccq.zza(paramLong, paramByte);
  }
  
  private static void zza(Object paramObject, long paramLong, byte paramByte) {
    long l = 0xFFFFFFFFFFFFFFFCL & paramLong;
    int i = zzk(paramObject, l);
    int j = (((int)paramLong ^ 0xFFFFFFFF) & 0x3) << 3;
    zzb(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  static void zza(Object paramObject, long paramLong, double paramDouble) {
    zzccq.zza(paramObject, paramLong, paramDouble);
  }
  
  static void zza(Object paramObject, long paramLong, float paramFloat) {
    zzccq.zza(paramObject, paramLong, paramFloat);
  }
  
  static void zza(Object paramObject, long paramLong1, long paramLong2) {
    zzccq.zza(paramObject, paramLong1, paramLong2);
  }
  
  static void zza(Object paramObject1, long paramLong, Object paramObject2) {
    zzccq.zzcdh.putObject(paramObject1, paramLong, paramObject2);
  }
  
  static void zza(Object paramObject, long paramLong, boolean paramBoolean) {
    zzccq.zza(paramObject, paramLong, paramBoolean);
  }
  
  static void zza(byte[] paramArrayOfbyte, long paramLong, byte paramByte) {
    zzccq.zze(paramArrayOfbyte, zzccs + paramLong, paramByte);
  }
  
  static void zza(byte[] paramArrayOfbyte, long paramLong1, long paramLong2, long paramLong3) {
    zzccq.zza(paramArrayOfbyte, paramLong1, paramLong2, paramLong3);
  }
  
  static long zzb(ByteBuffer paramByteBuffer) {
    return zzccq.zzl(paramByteBuffer, zzcdf);
  }
  
  private static Field zzb(Class<?> paramClass, String paramString) {
    try {
      Field field = paramClass.getDeclaredField(paramString);
      field.setAccessible(true);
    } finally {
      paramClass = null;
    } 
  }
  
  private static void zzb(Object paramObject, long paramLong, byte paramByte) {
    long l = 0xFFFFFFFFFFFFFFFCL & paramLong;
    int i = zzk(paramObject, l);
    int j = ((int)paramLong & 0x3) << 3;
    zzb(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  static void zzb(Object paramObject, long paramLong, int paramInt) {
    zzccq.zzb(paramObject, paramLong, paramInt);
  }
  
  private static void zzb(Object paramObject, long paramLong, boolean paramBoolean) {
    zza(paramObject, paramLong, (byte)paramBoolean);
  }
  
  private static void zzc(Object paramObject, long paramLong, boolean paramBoolean) {
    zzb(paramObject, paramLong, (byte)paramBoolean);
  }
  
  static int zzk(Object paramObject, long paramLong) {
    return zzccq.zzk(paramObject, paramLong);
  }
  
  static <T> T zzk(Class<T> paramClass) {
    try {
      return (T)zzcar.allocateInstance(paramClass);
    } catch (InstantiationException instantiationException) {
      throw new IllegalStateException(instantiationException);
    } 
  }
  
  private static int zzl(Class<?> paramClass) {
    return zzbuo ? zzccq.zzcdh.arrayBaseOffset(paramClass) : -1;
  }
  
  static long zzl(Object paramObject, long paramLong) {
    return zzccq.zzl(paramObject, paramLong);
  }
  
  private static int zzm(Class<?> paramClass) {
    return zzbuo ? zzccq.zzcdh.arrayIndexScale(paramClass) : -1;
  }
  
  static boolean zzm(Object paramObject, long paramLong) {
    return zzccq.zzm(paramObject, paramLong);
  }
  
  static float zzn(Object paramObject, long paramLong) {
    return zzccq.zzn(paramObject, paramLong);
  }
  
  private static boolean zzn(Class<?> paramClass) {
    if (!zztb.zzub())
      return false; 
    try {
      Class<?> clazz = zzbto;
      clazz.getMethod("peekLong", new Class[] { paramClass, boolean.class });
      clazz.getMethod("pokeLong", new Class[] { paramClass, long.class, boolean.class });
      clazz.getMethod("pokeInt", new Class[] { paramClass, int.class, boolean.class });
      clazz.getMethod("peekInt", new Class[] { paramClass, boolean.class });
      clazz.getMethod("pokeByte", new Class[] { paramClass, byte.class });
      clazz.getMethod("peekByte", new Class[] { paramClass });
      clazz.getMethod("pokeByteArray", new Class[] { paramClass, byte[].class, int.class, int.class });
      return true;
    } finally {
      paramClass = null;
    } 
  }
  
  static double zzo(Object paramObject, long paramLong) {
    return zzccq.zzo(paramObject, paramLong);
  }
  
  static Object zzp(Object paramObject, long paramLong) {
    return zzccq.zzcdh.getObject(paramObject, paramLong);
  }
  
  private static byte zzq(Object paramObject, long paramLong) {
    return (byte)(zzk(paramObject, 0xFFFFFFFFFFFFFFFCL & paramLong) >>> (int)(((paramLong ^ 0xFFFFFFFFFFFFFFFFL) & 0x3L) << 3L));
  }
  
  private static byte zzr(Object paramObject, long paramLong) {
    return (byte)(zzk(paramObject, 0xFFFFFFFFFFFFFFFCL & paramLong) >>> (int)((paramLong & 0x3L) << 3L));
  }
  
  private static boolean zzs(Object paramObject, long paramLong) {
    return (zzq(paramObject, paramLong) != 0);
  }
  
  private static boolean zzt(Object paramObject, long paramLong) {
    return (zzr(paramObject, paramLong) != 0);
  }
  
  static boolean zzyo() {
    return zzbuo;
  }
  
  static boolean zzyp() {
    return zzccr;
  }
  
  static Unsafe zzyq() {
    Exception exception;
    try {
      zzxk zzxk = new zzxk();
      this();
      Unsafe unsafe = AccessController.<Unsafe>doPrivileged(zzxk);
    } finally {
      exception = null;
    } 
  }
  
  private static boolean zzyr() {
    Unsafe unsafe = zzcar;
    if (unsafe == null)
      return false; 
    try {
      Class<?> clazz = unsafe.getClass();
      clazz.getMethod("objectFieldOffset", new Class[] { Field.class });
      clazz.getMethod("arrayBaseOffset", new Class[] { Class.class });
      clazz.getMethod("arrayIndexScale", new Class[] { Class.class });
      clazz.getMethod("getInt", new Class[] { Object.class, long.class });
      clazz.getMethod("putInt", new Class[] { Object.class, long.class, int.class });
      clazz.getMethod("getLong", new Class[] { Object.class, long.class });
      clazz.getMethod("putLong", new Class[] { Object.class, long.class, long.class });
      clazz.getMethod("getObject", new Class[] { Object.class, long.class });
      clazz.getMethod("putObject", new Class[] { Object.class, long.class, Object.class });
      if (zztb.zzub())
        return true; 
      return true;
    } finally {
      Exception exception = null;
      Logger logger = logger;
      Level level = Level.WARNING;
      String str = String.valueOf(exception);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 71);
      stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      stringBuilder.append(str);
      logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", stringBuilder.toString());
    } 
  }
  
  private static boolean zzys() {
    Unsafe unsafe = zzcar;
    if (unsafe == null)
      return false; 
    try {
      Class<?> clazz = unsafe.getClass();
      clazz.getMethod("objectFieldOffset", new Class[] { Field.class });
      clazz.getMethod("getLong", new Class[] { Object.class, long.class });
      if (zzyt() == null)
        return false; 
      if (zztb.zzub())
        return true; 
      return true;
    } finally {
      Exception exception = null;
      Logger logger = logger;
      Level level = Level.WARNING;
      String str = String.valueOf(exception);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 71);
      stringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      stringBuilder.append(str);
      logger.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", stringBuilder.toString());
    } 
  }
  
  private static Field zzyt() {
    if (zztb.zzub()) {
      Field field1 = zzb(Buffer.class, "effectiveDirectAddress");
      if (field1 != null)
        return field1; 
    } 
    Field field = zzb(Buffer.class, "address");
    return (field != null && field.getType() == long.class) ? field : null;
  }
  
  static final class zza extends zzd {
    zza(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zza(long param1Long, byte param1Byte) {
      Memory.pokeByte((int)(param1Long & 0xFFFFFFFFFFFFFFFFL), param1Byte);
    }
    
    public final void zza(Object param1Object, long param1Long, double param1Double) {
      zza(param1Object, param1Long, Double.doubleToLongBits(param1Double));
    }
    
    public final void zza(Object param1Object, long param1Long, float param1Float) {
      zzb(param1Object, param1Long, Float.floatToIntBits(param1Float));
    }
    
    public final void zza(Object param1Object, long param1Long, boolean param1Boolean) {
      if (zzxj.zzvk()) {
        zzxj.zzd(param1Object, param1Long, param1Boolean);
        return;
      } 
      zzxj.zze(param1Object, param1Long, param1Boolean);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, long param1Long1, long param1Long2, long param1Long3) {
      Memory.pokeByteArray((int)(param1Long2 & 0xFFFFFFFFFFFFFFFFL), param1ArrayOfbyte, (int)param1Long1, (int)param1Long3);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      if (zzxj.zzvk()) {
        zzxj.zzc(param1Object, param1Long, param1Byte);
        return;
      } 
      zzxj.zzd(param1Object, param1Long, param1Byte);
    }
    
    public final boolean zzm(Object param1Object, long param1Long) {
      return zzxj.zzvk() ? zzxj.zzw(param1Object, param1Long) : zzxj.zzx(param1Object, param1Long);
    }
    
    public final float zzn(Object param1Object, long param1Long) {
      return Float.intBitsToFloat(zzk(param1Object, param1Long));
    }
    
    public final double zzo(Object param1Object, long param1Long) {
      return Double.longBitsToDouble(zzl(param1Object, param1Long));
    }
    
    public final byte zzy(Object param1Object, long param1Long) {
      return zzxj.zzvk() ? zzxj.zzu(param1Object, param1Long) : zzxj.zzv(param1Object, param1Long);
    }
  }
  
  static final class zzb extends zzd {
    zzb(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zza(long param1Long, byte param1Byte) {
      Memory.pokeByte(param1Long, param1Byte);
    }
    
    public final void zza(Object param1Object, long param1Long, double param1Double) {
      zza(param1Object, param1Long, Double.doubleToLongBits(param1Double));
    }
    
    public final void zza(Object param1Object, long param1Long, float param1Float) {
      zzb(param1Object, param1Long, Float.floatToIntBits(param1Float));
    }
    
    public final void zza(Object param1Object, long param1Long, boolean param1Boolean) {
      if (zzxj.zzvk()) {
        zzxj.zzd(param1Object, param1Long, param1Boolean);
        return;
      } 
      zzxj.zze(param1Object, param1Long, param1Boolean);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, long param1Long1, long param1Long2, long param1Long3) {
      Memory.pokeByteArray(param1Long2, param1ArrayOfbyte, (int)param1Long1, (int)param1Long3);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      if (zzxj.zzvk()) {
        zzxj.zzc(param1Object, param1Long, param1Byte);
        return;
      } 
      zzxj.zzd(param1Object, param1Long, param1Byte);
    }
    
    public final boolean zzm(Object param1Object, long param1Long) {
      return zzxj.zzvk() ? zzxj.zzw(param1Object, param1Long) : zzxj.zzx(param1Object, param1Long);
    }
    
    public final float zzn(Object param1Object, long param1Long) {
      return Float.intBitsToFloat(zzk(param1Object, param1Long));
    }
    
    public final double zzo(Object param1Object, long param1Long) {
      return Double.longBitsToDouble(zzl(param1Object, param1Long));
    }
    
    public final byte zzy(Object param1Object, long param1Long) {
      return zzxj.zzvk() ? zzxj.zzu(param1Object, param1Long) : zzxj.zzv(param1Object, param1Long);
    }
  }
  
  static final class zzc extends zzd {
    zzc(Unsafe param1Unsafe) {
      super(param1Unsafe);
    }
    
    public final void zza(long param1Long, byte param1Byte) {
      this.zzcdh.putByte(param1Long, param1Byte);
    }
    
    public final void zza(Object param1Object, long param1Long, double param1Double) {
      this.zzcdh.putDouble(param1Object, param1Long, param1Double);
    }
    
    public final void zza(Object param1Object, long param1Long, float param1Float) {
      this.zzcdh.putFloat(param1Object, param1Long, param1Float);
    }
    
    public final void zza(Object param1Object, long param1Long, boolean param1Boolean) {
      this.zzcdh.putBoolean(param1Object, param1Long, param1Boolean);
    }
    
    public final void zza(byte[] param1ArrayOfbyte, long param1Long1, long param1Long2, long param1Long3) {
      this.zzcdh.copyMemory(param1ArrayOfbyte, zzxj.zzyu() + param1Long1, null, param1Long2, param1Long3);
    }
    
    public final void zze(Object param1Object, long param1Long, byte param1Byte) {
      this.zzcdh.putByte(param1Object, param1Long, param1Byte);
    }
    
    public final boolean zzm(Object param1Object, long param1Long) {
      return this.zzcdh.getBoolean(param1Object, param1Long);
    }
    
    public final float zzn(Object param1Object, long param1Long) {
      return this.zzcdh.getFloat(param1Object, param1Long);
    }
    
    public final double zzo(Object param1Object, long param1Long) {
      return this.zzcdh.getDouble(param1Object, param1Long);
    }
    
    public final byte zzy(Object param1Object, long param1Long) {
      return this.zzcdh.getByte(param1Object, param1Long);
    }
  }
  
  static abstract class zzd {
    Unsafe zzcdh;
    
    zzd(Unsafe param1Unsafe) {
      this.zzcdh = param1Unsafe;
    }
    
    public abstract void zza(long param1Long, byte param1Byte);
    
    public abstract void zza(Object param1Object, long param1Long, double param1Double);
    
    public abstract void zza(Object param1Object, long param1Long, float param1Float);
    
    public final void zza(Object param1Object, long param1Long1, long param1Long2) {
      this.zzcdh.putLong(param1Object, param1Long1, param1Long2);
    }
    
    public abstract void zza(Object param1Object, long param1Long, boolean param1Boolean);
    
    public abstract void zza(byte[] param1ArrayOfbyte, long param1Long1, long param1Long2, long param1Long3);
    
    public final void zzb(Object param1Object, long param1Long, int param1Int) {
      this.zzcdh.putInt(param1Object, param1Long, param1Int);
    }
    
    public abstract void zze(Object param1Object, long param1Long, byte param1Byte);
    
    public final int zzk(Object param1Object, long param1Long) {
      return this.zzcdh.getInt(param1Object, param1Long);
    }
    
    public final long zzl(Object param1Object, long param1Long) {
      return this.zzcdh.getLong(param1Object, param1Long);
    }
    
    public abstract boolean zzm(Object param1Object, long param1Long);
    
    public abstract float zzn(Object param1Object, long param1Long);
    
    public abstract double zzo(Object param1Object, long param1Long);
    
    public abstract byte zzy(Object param1Object, long param1Long);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */