package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzrx implements zzsb {
  static final Map<Uri, zzrx> zzbrd = (Map<Uri, zzrx>)new ArrayMap();
  
  private static final String[] zzbri = new String[] { "key", "value" };
  
  private final Uri uri;
  
  private final ContentResolver zzbre;
  
  private final Object zzbrf = new Object();
  
  private volatile Map<String, String> zzbrg;
  
  private final List<zzsa> zzbrh = new ArrayList<zzsa>();
  
  private zzrx(ContentResolver paramContentResolver, Uri paramUri) {
    this.zzbre = paramContentResolver;
    this.uri = paramUri;
    this.zzbre.registerContentObserver(paramUri, false, new zzrz(this, null));
  }
  
  public static zzrx zza(ContentResolver paramContentResolver, Uri paramUri) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/measurement/zzrx
    //   2: monitorenter
    //   3: getstatic com/google/android/gms/internal/measurement/zzrx.zzbrd : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/google/android/gms/internal/measurement/zzrx
    //   15: astore_3
    //   16: aload_3
    //   17: astore_2
    //   18: aload_3
    //   19: ifnonnull -> 43
    //   22: new com/google/android/gms/internal/measurement/zzrx
    //   25: astore_2
    //   26: aload_2
    //   27: aload_0
    //   28: aload_1
    //   29: invokespecial <init> : (Landroid/content/ContentResolver;Landroid/net/Uri;)V
    //   32: getstatic com/google/android/gms/internal/measurement/zzrx.zzbrd : Ljava/util/Map;
    //   35: aload_1
    //   36: aload_2
    //   37: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   42: pop
    //   43: ldc com/google/android/gms/internal/measurement/zzrx
    //   45: monitorexit
    //   46: aload_2
    //   47: areturn
    //   48: astore_0
    //   49: ldc com/google/android/gms/internal/measurement/zzrx
    //   51: monitorexit
    //   52: aload_0
    //   53: athrow
    //   54: astore_0
    //   55: aload_3
    //   56: astore_2
    //   57: goto -> 43
    //   60: astore_0
    //   61: goto -> 43
    // Exception table:
    //   from	to	target	type
    //   3	16	48	finally
    //   22	32	54	java/lang/SecurityException
    //   22	32	48	finally
    //   32	43	60	java/lang/SecurityException
    //   32	43	48	finally
    //   43	46	48	finally
    //   49	52	48	finally
  }
  
  private final Map<String, String> zztm() {
    try {
      zzry zzry = new zzry();
      this(this);
      return zzsc.<Map>zza(zzry);
    } catch (SecurityException|android.database.sqlite.SQLiteException securityException) {
      return null;
    } 
  }
  
  public final Map<String, String> zztk() {
    Map<String, String> map2 = this.zzbrg;
    Map<String, String> map1 = map2;
    if (map2 == null)
      synchronized (this.zzbrf) {
        map2 = this.zzbrg;
        map1 = map2;
        if (map2 == null) {
          map1 = zztm();
          this.zzbrg = map1;
        } 
      }  
    return (map1 != null) ? map1 : Collections.emptyMap();
  }
  
  public final void zztl() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzbrf : Ljava/lang/Object;
    //   4: astore_2
    //   5: aload_2
    //   6: monitorenter
    //   7: aload_0
    //   8: aconst_null
    //   9: putfield zzbrg : Ljava/util/Map;
    //   12: invokestatic zztq : ()V
    //   15: aload_2
    //   16: monitorexit
    //   17: aload_0
    //   18: monitorenter
    //   19: aload_0
    //   20: getfield zzbrh : Ljava/util/List;
    //   23: invokeinterface iterator : ()Ljava/util/Iterator;
    //   28: astore_1
    //   29: aload_1
    //   30: invokeinterface hasNext : ()Z
    //   35: ifeq -> 55
    //   38: aload_1
    //   39: invokeinterface next : ()Ljava/lang/Object;
    //   44: checkcast com/google/android/gms/internal/measurement/zzsa
    //   47: invokeinterface zztp : ()V
    //   52: goto -> 29
    //   55: aload_0
    //   56: monitorexit
    //   57: return
    //   58: astore_1
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_1
    //   62: athrow
    //   63: astore_1
    //   64: aload_2
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   7	17	63	finally
    //   19	29	58	finally
    //   29	52	58	finally
    //   55	57	58	finally
    //   59	61	58	finally
    //   64	66	63	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzrx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */