package com.google.android.gms.internal.measurement;

import android.annotation.SuppressLint;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzsi<T> {
  private static final Object zzbro = new Object();
  
  private static final AtomicInteger zzbrs = new AtomicInteger();
  
  @SuppressLint({"StaticFieldLeak"})
  private static Context zzri;
  
  private final String name;
  
  private volatile T zzall;
  
  private final zzso zzbrq;
  
  private final T zzbrr;
  
  private volatile int zzbrt = -1;
  
  private zzsi(zzso paramzzso, String paramString, T paramT) {
    if (zzso.zza(paramzzso) != null) {
      this.zzbrq = paramzzso;
      this.name = paramString;
      this.zzbrr = paramT;
      return;
    } 
    throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
  }
  
  private static zzsi<Double> zza(zzso paramzzso, String paramString, double paramDouble) {
    return new zzsm(paramzzso, paramString, Double.valueOf(paramDouble));
  }
  
  private static zzsi<Integer> zza(zzso paramzzso, String paramString, int paramInt) {
    return new zzsk(paramzzso, paramString, Integer.valueOf(paramInt));
  }
  
  private static zzsi<Long> zza(zzso paramzzso, String paramString, long paramLong) {
    return new zzsj(paramzzso, paramString, Long.valueOf(paramLong));
  }
  
  private static zzsi<String> zza(zzso paramzzso, String paramString1, String paramString2) {
    return new zzsn(paramzzso, paramString1, paramString2);
  }
  
  private static zzsi<Boolean> zza(zzso paramzzso, String paramString, boolean paramBoolean) {
    return new zzsl(paramzzso, paramString, Boolean.valueOf(paramBoolean));
  }
  
  public static void zzae(Context paramContext) {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzsi.zzbro : Ljava/lang/Object;
    //   3: astore_2
    //   4: aload_2
    //   5: monitorenter
    //   6: aload_0
    //   7: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull -> 18
    //   15: goto -> 20
    //   18: aload_1
    //   19: astore_0
    //   20: getstatic com/google/android/gms/internal/measurement/zzsi.zzri : Landroid/content/Context;
    //   23: aload_0
    //   24: if_acmpeq -> 97
    //   27: ldc com/google/android/gms/internal/measurement/zzrx
    //   29: monitorenter
    //   30: getstatic com/google/android/gms/internal/measurement/zzrx.zzbrd : Ljava/util/Map;
    //   33: invokeinterface clear : ()V
    //   38: ldc com/google/android/gms/internal/measurement/zzrx
    //   40: monitorexit
    //   41: ldc com/google/android/gms/internal/measurement/zzsp
    //   43: monitorenter
    //   44: getstatic com/google/android/gms/internal/measurement/zzsp.zzbsb : Ljava/util/Map;
    //   47: invokeinterface clear : ()V
    //   52: ldc com/google/android/gms/internal/measurement/zzsp
    //   54: monitorexit
    //   55: ldc com/google/android/gms/internal/measurement/zzse
    //   57: monitorenter
    //   58: aconst_null
    //   59: putstatic com/google/android/gms/internal/measurement/zzse.zzbrl : Lcom/google/android/gms/internal/measurement/zzse;
    //   62: ldc com/google/android/gms/internal/measurement/zzse
    //   64: monitorexit
    //   65: getstatic com/google/android/gms/internal/measurement/zzsi.zzbrs : Ljava/util/concurrent/atomic/AtomicInteger;
    //   68: invokevirtual incrementAndGet : ()I
    //   71: pop
    //   72: aload_0
    //   73: putstatic com/google/android/gms/internal/measurement/zzsi.zzri : Landroid/content/Context;
    //   76: goto -> 97
    //   79: astore_0
    //   80: ldc com/google/android/gms/internal/measurement/zzse
    //   82: monitorexit
    //   83: aload_0
    //   84: athrow
    //   85: astore_0
    //   86: ldc com/google/android/gms/internal/measurement/zzsp
    //   88: monitorexit
    //   89: aload_0
    //   90: athrow
    //   91: astore_0
    //   92: ldc com/google/android/gms/internal/measurement/zzrx
    //   94: monitorexit
    //   95: aload_0
    //   96: athrow
    //   97: aload_2
    //   98: monitorexit
    //   99: return
    //   100: astore_0
    //   101: aload_2
    //   102: monitorexit
    //   103: aload_0
    //   104: athrow
    // Exception table:
    //   from	to	target	type
    //   6	11	100	finally
    //   20	30	100	finally
    //   30	41	91	finally
    //   41	44	100	finally
    //   44	55	85	finally
    //   55	58	100	finally
    //   58	65	79	finally
    //   65	76	100	finally
    //   80	83	79	finally
    //   83	85	100	finally
    //   86	89	85	finally
    //   89	91	100	finally
    //   92	95	91	finally
    //   95	97	100	finally
    //   97	99	100	finally
    //   101	103	100	finally
  }
  
  private final String zzfr(String paramString) {
    if (paramString != null && paramString.isEmpty())
      return this.name; 
    paramString = String.valueOf(paramString);
    String str = String.valueOf(this.name);
    return (str.length() != 0) ? paramString.concat(str) : new String(paramString);
  }
  
  static void zztq() {
    zzbrs.incrementAndGet();
  }
  
  private final T zzts() {
    boolean bool;
    String str = (String)zzse.zzad(zzri).zzfn("gms:phenotype:phenotype_flag:debug_bypass_phenotype");
    if (str != null && zzru.zzbqq.matcher(str).matches()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!bool) {
      zzsp zzsp;
      if (zzso.zza(this.zzbrq) != null) {
        zzrx zzrx = zzrx.zza(zzri.getContentResolver(), zzso.zza(this.zzbrq));
      } else {
        zzsp = zzsp.zzi(zzri, null);
      } 
      if (zzsp != null) {
        Object object = zzsp.zzfn(zztr());
        if (object != null)
          return zzs(object); 
      } 
    } else {
      str = String.valueOf(zztr());
      if (str.length() != 0) {
        "Bypass reading Phenotype values for flag: ".concat(str);
      } else {
        new String("Bypass reading Phenotype values for flag: ");
      } 
    } 
    return null;
  }
  
  private final T zztt() {
    Object object = zzse.zzad(zzri).zzfn(zzfr(zzso.zzc(this.zzbrq)));
    return (object != null) ? zzs(object) : null;
  }
  
  public final T get() {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzsi.zzbrs : Ljava/util/concurrent/atomic/AtomicInteger;
    //   3: invokevirtual get : ()I
    //   6: istore_1
    //   7: aload_0
    //   8: getfield zzbrt : I
    //   11: iload_1
    //   12: if_icmpge -> 96
    //   15: aload_0
    //   16: monitorenter
    //   17: aload_0
    //   18: getfield zzbrt : I
    //   21: iload_1
    //   22: if_icmpge -> 86
    //   25: getstatic com/google/android/gms/internal/measurement/zzsi.zzri : Landroid/content/Context;
    //   28: ifnull -> 73
    //   31: aload_0
    //   32: invokespecial zzts : ()Ljava/lang/Object;
    //   35: astore_2
    //   36: aload_2
    //   37: ifnull -> 43
    //   40: goto -> 60
    //   43: aload_0
    //   44: invokespecial zztt : ()Ljava/lang/Object;
    //   47: astore_2
    //   48: aload_2
    //   49: ifnull -> 55
    //   52: goto -> 60
    //   55: aload_0
    //   56: getfield zzbrr : Ljava/lang/Object;
    //   59: astore_2
    //   60: aload_0
    //   61: aload_2
    //   62: putfield zzall : Ljava/lang/Object;
    //   65: aload_0
    //   66: iload_1
    //   67: putfield zzbrt : I
    //   70: goto -> 86
    //   73: new java/lang/IllegalStateException
    //   76: astore_2
    //   77: aload_2
    //   78: ldc_w 'Must call PhenotypeFlag.init() first'
    //   81: invokespecial <init> : (Ljava/lang/String;)V
    //   84: aload_2
    //   85: athrow
    //   86: aload_0
    //   87: monitorexit
    //   88: goto -> 96
    //   91: astore_2
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_2
    //   95: athrow
    //   96: aload_0
    //   97: getfield zzall : Ljava/lang/Object;
    //   100: areturn
    // Exception table:
    //   from	to	target	type
    //   17	36	91	finally
    //   43	48	91	finally
    //   55	60	91	finally
    //   60	70	91	finally
    //   73	86	91	finally
    //   86	88	91	finally
    //   92	94	91	finally
  }
  
  public final T getDefaultValue() {
    return this.zzbrr;
  }
  
  abstract T zzs(Object paramObject);
  
  public final String zztr() {
    return zzfr(zzso.zzb(this.zzbrq));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */