package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

public class zzru {
  public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
  
  private static final Uri zzbqp = Uri.parse("content://com.google.android.gsf.gservices/prefix");
  
  public static final Pattern zzbqq = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
  
  public static final Pattern zzbqr = Pattern.compile("^(0|false|f|off|no|n)$", 2);
  
  private static final AtomicBoolean zzbqs = new AtomicBoolean();
  
  private static HashMap<String, String> zzbqt;
  
  private static final HashMap<String, Boolean> zzbqu = new HashMap<String, Boolean>();
  
  private static final HashMap<String, Integer> zzbqv = new HashMap<String, Integer>();
  
  private static final HashMap<String, Long> zzbqw = new HashMap<String, Long>();
  
  private static final HashMap<String, Float> zzbqx = new HashMap<String, Float>();
  
  private static Object zzbqy;
  
  private static boolean zzbqz;
  
  private static String[] zzbra = new String[0];
  
  public static String zza(ContentResolver paramContentResolver, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/measurement/zzru
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic zza : (Landroid/content/ContentResolver;)V
    //   7: getstatic com/google/android/gms/internal/measurement/zzru.zzbqy : Ljava/lang/Object;
    //   10: astore #6
    //   12: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   15: aload_1
    //   16: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   19: ifeq -> 47
    //   22: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   25: aload_1
    //   26: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   29: checkcast java/lang/String
    //   32: astore_0
    //   33: aload_0
    //   34: ifnull -> 40
    //   37: goto -> 42
    //   40: aconst_null
    //   41: astore_0
    //   42: ldc com/google/android/gms/internal/measurement/zzru
    //   44: monitorexit
    //   45: aload_0
    //   46: areturn
    //   47: getstatic com/google/android/gms/internal/measurement/zzru.zzbra : [Ljava/lang/String;
    //   50: astore_2
    //   51: aload_2
    //   52: arraylength
    //   53: istore #4
    //   55: iconst_0
    //   56: istore_3
    //   57: iload_3
    //   58: iload #4
    //   60: if_icmpge -> 153
    //   63: aload_1
    //   64: aload_2
    //   65: iload_3
    //   66: aaload
    //   67: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   70: ifeq -> 147
    //   73: getstatic com/google/android/gms/internal/measurement/zzru.zzbqz : Z
    //   76: ifeq -> 88
    //   79: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   82: invokevirtual isEmpty : ()Z
    //   85: ifeq -> 142
    //   88: getstatic com/google/android/gms/internal/measurement/zzru.zzbra : [Ljava/lang/String;
    //   91: astore_2
    //   92: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   95: aload_0
    //   96: aload_2
    //   97: invokestatic zza : (Landroid/content/ContentResolver;[Ljava/lang/String;)Ljava/util/Map;
    //   100: invokevirtual putAll : (Ljava/util/Map;)V
    //   103: iconst_1
    //   104: putstatic com/google/android/gms/internal/measurement/zzru.zzbqz : Z
    //   107: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   110: aload_1
    //   111: invokevirtual containsKey : (Ljava/lang/Object;)Z
    //   114: ifeq -> 142
    //   117: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   120: aload_1
    //   121: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   124: checkcast java/lang/String
    //   127: astore_0
    //   128: aload_0
    //   129: ifnull -> 135
    //   132: goto -> 137
    //   135: aconst_null
    //   136: astore_0
    //   137: ldc com/google/android/gms/internal/measurement/zzru
    //   139: monitorexit
    //   140: aload_0
    //   141: areturn
    //   142: ldc com/google/android/gms/internal/measurement/zzru
    //   144: monitorexit
    //   145: aconst_null
    //   146: areturn
    //   147: iinc #3, 1
    //   150: goto -> 57
    //   153: ldc com/google/android/gms/internal/measurement/zzru
    //   155: monitorexit
    //   156: aload_0
    //   157: getstatic com/google/android/gms/internal/measurement/zzru.CONTENT_URI : Landroid/net/Uri;
    //   160: aconst_null
    //   161: aconst_null
    //   162: iconst_1
    //   163: anewarray java/lang/String
    //   166: dup
    //   167: iconst_0
    //   168: aload_1
    //   169: aastore
    //   170: aconst_null
    //   171: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   174: astore #5
    //   176: aload #5
    //   178: ifnonnull -> 195
    //   181: aload #5
    //   183: ifnull -> 193
    //   186: aload #5
    //   188: invokeinterface close : ()V
    //   193: aconst_null
    //   194: areturn
    //   195: aload #5
    //   197: invokeinterface moveToFirst : ()Z
    //   202: ifne -> 226
    //   205: aload #6
    //   207: aload_1
    //   208: aconst_null
    //   209: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   212: aload #5
    //   214: ifnull -> 224
    //   217: aload #5
    //   219: invokeinterface close : ()V
    //   224: aconst_null
    //   225: areturn
    //   226: aload #5
    //   228: iconst_1
    //   229: invokeinterface getString : (I)Ljava/lang/String;
    //   234: astore_2
    //   235: aload_2
    //   236: astore_0
    //   237: aload_2
    //   238: ifnull -> 253
    //   241: aload_2
    //   242: astore_0
    //   243: aload_2
    //   244: aconst_null
    //   245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   248: ifeq -> 253
    //   251: aconst_null
    //   252: astore_0
    //   253: aload #6
    //   255: aload_1
    //   256: aload_0
    //   257: invokestatic zza : (Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)V
    //   260: aload_0
    //   261: ifnull -> 267
    //   264: goto -> 269
    //   267: aconst_null
    //   268: astore_0
    //   269: aload #5
    //   271: ifnull -> 281
    //   274: aload #5
    //   276: invokeinterface close : ()V
    //   281: aload_0
    //   282: areturn
    //   283: astore_0
    //   284: aload #5
    //   286: ifnull -> 296
    //   289: aload #5
    //   291: invokeinterface close : ()V
    //   296: aload_0
    //   297: athrow
    //   298: astore_0
    //   299: ldc com/google/android/gms/internal/measurement/zzru
    //   301: monitorexit
    //   302: aload_0
    //   303: athrow
    // Exception table:
    //   from	to	target	type
    //   3	33	298	finally
    //   42	45	298	finally
    //   47	55	298	finally
    //   63	88	298	finally
    //   88	128	298	finally
    //   137	140	298	finally
    //   142	145	298	finally
    //   153	156	298	finally
    //   195	212	283	finally
    //   226	235	283	finally
    //   243	251	283	finally
    //   253	260	283	finally
    //   299	302	298	finally
  }
  
  private static Map<String, String> zza(ContentResolver paramContentResolver, String... paramVarArgs) {
    Cursor cursor = paramContentResolver.query(zzbqp, null, null, paramVarArgs, null);
    null = new TreeMap<Object, Object>();
    if (cursor == null)
      return (Map)null; 
    try {
      while (cursor.moveToNext())
        null.put(cursor.getString(0), cursor.getString(1)); 
      return (Map)null;
    } finally {
      cursor.close();
    } 
  }
  
  private static void zza(ContentResolver paramContentResolver) {
    if (zzbqt == null) {
      zzbqs.set(false);
      zzbqt = new HashMap<String, String>();
      zzbqy = new Object();
      zzbqz = false;
      paramContentResolver.registerContentObserver(CONTENT_URI, true, new zzrv(null));
      return;
    } 
    if (zzbqs.getAndSet(false)) {
      zzbqt.clear();
      zzbqu.clear();
      zzbqv.clear();
      zzbqw.clear();
      zzbqx.clear();
      zzbqy = new Object();
      zzbqz = false;
    } 
  }
  
  private static void zza(Object paramObject, String paramString1, String paramString2) {
    // Byte code:
    //   0: ldc com/google/android/gms/internal/measurement/zzru
    //   2: monitorenter
    //   3: aload_0
    //   4: getstatic com/google/android/gms/internal/measurement/zzru.zzbqy : Ljava/lang/Object;
    //   7: if_acmpne -> 19
    //   10: getstatic com/google/android/gms/internal/measurement/zzru.zzbqt : Ljava/util/HashMap;
    //   13: aload_1
    //   14: aload_2
    //   15: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   18: pop
    //   19: ldc com/google/android/gms/internal/measurement/zzru
    //   21: monitorexit
    //   22: return
    //   23: astore_0
    //   24: ldc com/google/android/gms/internal/measurement/zzru
    //   26: monitorexit
    //   27: aload_0
    //   28: athrow
    // Exception table:
    //   from	to	target	type
    //   3	19	23	finally
    //   19	22	23	finally
    //   24	27	23	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzru.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */