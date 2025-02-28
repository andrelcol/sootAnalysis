package com.google.firebase.iid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.io.IOException;
import java.util.Map;

final class zzaw {
  private final SharedPreferences zzdc;
  
  private final zzy zzdd;
  
  private final Map<String, zzz> zzde = (Map<String, zzz>)new ArrayMap();
  
  private final Context zzx;
  
  public zzaw(Context paramContext) {
    this(paramContext, new zzy());
  }
  
  private zzaw(Context paramContext, zzy paramzzy) {
    this.zzx = paramContext;
    this.zzdc = paramContext.getSharedPreferences("com.google.android.gms.appid", 0);
    this.zzdd = paramzzy;
    File file = new File(ContextCompat.getNoBackupFilesDir(this.zzx), "com.google.android.gms.appid-no-backup");
    if (!file.exists())
      try {
        if (file.createNewFile() && !isEmpty()) {
          zzal();
          FirebaseInstanceId.getInstance().zzm();
        } 
        return;
      } catch (IOException iOException) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
          String str = String.valueOf(iOException.getMessage());
          if (str.length() != 0) {
            "Error creating file in no backup dir: ".concat(str);
          } else {
            new String("Error creating file in no backup dir: ");
          } 
        } 
      }  
  }
  
  private final boolean isEmpty() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzdc : Landroid/content/SharedPreferences;
    //   6: invokeinterface getAll : ()Ljava/util/Map;
    //   11: invokeinterface isEmpty : ()Z
    //   16: istore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_1
    //   20: ireturn
    //   21: astore_2
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_2
    //   25: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	21	finally
  }
  
  private static String zza(String paramString1, String paramString2, String paramString3) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 4 + String.valueOf(paramString2).length() + String.valueOf(paramString3).length());
    stringBuilder.append(paramString1);
    stringBuilder.append("|T|");
    stringBuilder.append(paramString2);
    stringBuilder.append("|");
    stringBuilder.append(paramString3);
    return stringBuilder.toString();
  }
  
  static String zzd(String paramString1, String paramString2) {
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(paramString1).length() + 3 + String.valueOf(paramString2).length());
    stringBuilder.append(paramString1);
    stringBuilder.append("|S|");
    stringBuilder.append(paramString2);
    return stringBuilder.toString();
  }
  
  public final void zza(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload #4
    //   4: aload #5
    //   6: invokestatic currentTimeMillis : ()J
    //   9: invokestatic zza : (Ljava/lang/String;Ljava/lang/String;J)Ljava/lang/String;
    //   12: astore #4
    //   14: aload #4
    //   16: ifnonnull -> 22
    //   19: aload_0
    //   20: monitorexit
    //   21: return
    //   22: aload_0
    //   23: getfield zzdc : Landroid/content/SharedPreferences;
    //   26: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   31: astore #5
    //   33: aload #5
    //   35: aload_1
    //   36: aload_2
    //   37: aload_3
    //   38: invokestatic zza : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   41: aload #4
    //   43: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   48: pop
    //   49: aload #5
    //   51: invokeinterface commit : ()Z
    //   56: pop
    //   57: aload_0
    //   58: monitorexit
    //   59: return
    //   60: astore_1
    //   61: aload_0
    //   62: monitorexit
    //   63: aload_1
    //   64: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	60	finally
    //   22	57	60	finally
  }
  
  public final String zzak() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzdc : Landroid/content/SharedPreferences;
    //   6: ldc 'topic_operaion_queue'
    //   8: ldc ''
    //   10: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: areturn
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	20	finally
  }
  
  public final void zzal() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzde : Ljava/util/Map;
    //   6: invokeinterface clear : ()V
    //   11: aload_0
    //   12: getfield zzx : Landroid/content/Context;
    //   15: invokestatic zza : (Landroid/content/Context;)V
    //   18: aload_0
    //   19: getfield zzdc : Landroid/content/SharedPreferences;
    //   22: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   27: invokeinterface clear : ()Landroid/content/SharedPreferences$Editor;
    //   32: invokeinterface commit : ()Z
    //   37: pop
    //   38: aload_0
    //   39: monitorexit
    //   40: return
    //   41: astore_1
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_1
    //   45: athrow
    // Exception table:
    //   from	to	target	type
    //   2	38	41	finally
  }
  
  public final zzax zzb(String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzdc : Landroid/content/SharedPreferences;
    //   6: aload_1
    //   7: aload_2
    //   8: aload_3
    //   9: invokestatic zza : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   12: aconst_null
    //   13: invokeinterface getString : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   18: invokestatic zzi : (Ljava/lang/String;)Lcom/google/firebase/iid/zzax;
    //   21: astore_1
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_1
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	26	finally
  }
  
  public final void zzf(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzdc : Landroid/content/SharedPreferences;
    //   6: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   11: ldc 'topic_operaion_queue'
    //   13: aload_1
    //   14: invokeinterface putString : (Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   19: invokeinterface apply : ()V
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	27	finally
  }
  
  public final zzz zzg(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzde : Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   12: checkcast com/google/firebase/iid/zzz
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 24
    //   20: aload_0
    //   21: monitorexit
    //   22: aload_2
    //   23: areturn
    //   24: aload_0
    //   25: getfield zzdd : Lcom/google/firebase/iid/zzy;
    //   28: aload_0
    //   29: getfield zzx : Landroid/content/Context;
    //   32: aload_1
    //   33: invokevirtual zzb : (Landroid/content/Context;Ljava/lang/String;)Lcom/google/firebase/iid/zzz;
    //   36: astore_2
    //   37: goto -> 60
    //   40: astore_2
    //   41: invokestatic getInstance : ()Lcom/google/firebase/iid/FirebaseInstanceId;
    //   44: invokevirtual zzm : ()V
    //   47: aload_0
    //   48: getfield zzdd : Lcom/google/firebase/iid/zzy;
    //   51: aload_0
    //   52: getfield zzx : Landroid/content/Context;
    //   55: aload_1
    //   56: invokevirtual zzc : (Landroid/content/Context;Ljava/lang/String;)Lcom/google/firebase/iid/zzz;
    //   59: astore_2
    //   60: aload_0
    //   61: getfield zzde : Ljava/util/Map;
    //   64: aload_1
    //   65: aload_2
    //   66: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   71: pop
    //   72: aload_0
    //   73: monitorexit
    //   74: aload_2
    //   75: areturn
    //   76: astore_1
    //   77: aload_0
    //   78: monitorexit
    //   79: aload_1
    //   80: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	76	finally
    //   24	37	40	com/google/firebase/iid/zzaa
    //   24	37	76	finally
    //   41	60	76	finally
    //   60	72	76	finally
  }
  
  public final void zzh(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   6: ldc '|T|'
    //   8: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   11: astore_2
    //   12: aload_0
    //   13: getfield zzdc : Landroid/content/SharedPreferences;
    //   16: invokeinterface edit : ()Landroid/content/SharedPreferences$Editor;
    //   21: astore #4
    //   23: aload_0
    //   24: getfield zzdc : Landroid/content/SharedPreferences;
    //   27: invokeinterface getAll : ()Ljava/util/Map;
    //   32: invokeinterface keySet : ()Ljava/util/Set;
    //   37: invokeinterface iterator : ()Ljava/util/Iterator;
    //   42: astore_3
    //   43: aload_3
    //   44: invokeinterface hasNext : ()Z
    //   49: ifeq -> 82
    //   52: aload_3
    //   53: invokeinterface next : ()Ljava/lang/Object;
    //   58: checkcast java/lang/String
    //   61: astore_1
    //   62: aload_1
    //   63: aload_2
    //   64: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   67: ifeq -> 43
    //   70: aload #4
    //   72: aload_1
    //   73: invokeinterface remove : (Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;
    //   78: pop
    //   79: goto -> 43
    //   82: aload #4
    //   84: invokeinterface commit : ()Z
    //   89: pop
    //   90: aload_0
    //   91: monitorexit
    //   92: return
    //   93: astore_1
    //   94: aload_0
    //   95: monitorexit
    //   96: aload_1
    //   97: athrow
    // Exception table:
    //   from	to	target	type
    //   2	43	93	finally
    //   43	79	93	finally
    //   82	90	93	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzaw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */