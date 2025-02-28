package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzsp implements zzsb {
  static final Map<String, zzsp> zzbsb = new HashMap<String, zzsp>();
  
  private final Object zzbrf = new Object();
  
  private volatile Map<String, ?> zzbrg;
  
  private final List<zzsa> zzbrh = new ArrayList<zzsa>();
  
  private final SharedPreferences zzbsc;
  
  private final SharedPreferences.OnSharedPreferenceChangeListener zzbsd = new zzsq(this);
  
  private zzsp(SharedPreferences paramSharedPreferences) {
    this.zzbsc = paramSharedPreferences;
    this.zzbsc.registerOnSharedPreferenceChangeListener(this.zzbsd);
  }
  
  static zzsp zzi(Context paramContext, String paramString) {
    // Byte code:
    //   0: invokestatic zztj : ()Z
    //   3: ifeq -> 23
    //   6: aload_1
    //   7: ldc 'direct_boot:'
    //   9: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   12: ifne -> 23
    //   15: aload_0
    //   16: invokestatic isUserUnlocked : (Landroid/content/Context;)Z
    //   19: istore_2
    //   20: goto -> 25
    //   23: iconst_1
    //   24: istore_2
    //   25: iload_2
    //   26: ifne -> 31
    //   29: aconst_null
    //   30: areturn
    //   31: ldc com/google/android/gms/internal/measurement/zzsp
    //   33: monitorenter
    //   34: getstatic com/google/android/gms/internal/measurement/zzsp.zzbsb : Ljava/util/Map;
    //   37: aload_1
    //   38: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   43: checkcast com/google/android/gms/internal/measurement/zzsp
    //   46: astore #4
    //   48: aload #4
    //   50: astore_3
    //   51: aload #4
    //   53: ifnonnull -> 126
    //   56: new com/google/android/gms/internal/measurement/zzsp
    //   59: astore #4
    //   61: aload_1
    //   62: ldc 'direct_boot:'
    //   64: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   67: ifeq -> 98
    //   70: aload_0
    //   71: astore_3
    //   72: invokestatic zztj : ()Z
    //   75: ifeq -> 83
    //   78: aload_0
    //   79: invokevirtual createDeviceProtectedStorageContext : ()Landroid/content/Context;
    //   82: astore_3
    //   83: aload_3
    //   84: aload_1
    //   85: bipush #12
    //   87: invokevirtual substring : (I)Ljava/lang/String;
    //   90: iconst_0
    //   91: invokevirtual getSharedPreferences : (Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   94: astore_0
    //   95: goto -> 105
    //   98: aload_0
    //   99: aload_1
    //   100: iconst_0
    //   101: invokevirtual getSharedPreferences : (Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   104: astore_0
    //   105: aload #4
    //   107: aload_0
    //   108: invokespecial <init> : (Landroid/content/SharedPreferences;)V
    //   111: getstatic com/google/android/gms/internal/measurement/zzsp.zzbsb : Ljava/util/Map;
    //   114: aload_1
    //   115: aload #4
    //   117: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   122: pop
    //   123: aload #4
    //   125: astore_3
    //   126: ldc com/google/android/gms/internal/measurement/zzsp
    //   128: monitorexit
    //   129: aload_3
    //   130: areturn
    //   131: astore_0
    //   132: ldc com/google/android/gms/internal/measurement/zzsp
    //   134: monitorexit
    //   135: aload_0
    //   136: athrow
    // Exception table:
    //   from	to	target	type
    //   34	48	131	finally
    //   56	70	131	finally
    //   72	83	131	finally
    //   83	95	131	finally
    //   98	105	131	finally
    //   105	123	131	finally
    //   126	129	131	finally
    //   132	135	131	finally
  }
  
  public final Object zzfn(String paramString) {
    Map<String, ?> map2 = this.zzbrg;
    Map<String, ?> map1 = map2;
    if (map2 == null)
      synchronized (this.zzbrf) {
        map2 = this.zzbrg;
        map1 = map2;
        if (map2 == null) {
          map1 = this.zzbsc.getAll();
          this.zzbrg = map1;
        } 
      }  
    return (map1 != null) ? map1.get(paramString) : null;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzsp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */