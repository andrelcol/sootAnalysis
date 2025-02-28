package com.google.firebase.iid;

import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

final class zzba {
  private final zzaw zzaj;
  
  private int zzdl = 0;
  
  private final Map<Integer, TaskCompletionSource<Void>> zzdm = (Map<Integer, TaskCompletionSource<Void>>)new ArrayMap();
  
  zzba(zzaw paramzzaw) {
    this.zzaj = paramzzaw;
  }
  
  private static boolean zza(FirebaseInstanceId paramFirebaseInstanceId, String paramString) {
    String[] arrayOfString = paramString.split("!");
    if (arrayOfString.length == 2) {
      paramString = arrayOfString[0];
      String str = arrayOfString[1];
      byte b = -1;
      try {
        int i = paramString.hashCode();
        if (i != 83) {
          if (i == 85 && paramString.equals("U"))
            b = 1; 
        } else if (paramString.equals("S")) {
          b = 0;
        } 
        if (b != 0) {
          if (b == 1) {
            paramFirebaseInstanceId.zzc(str);
            FirebaseInstanceId.zzl();
          } 
        } else {
          paramFirebaseInstanceId.zzb(str);
          FirebaseInstanceId.zzl();
        } 
      } catch (IOException iOException) {
        String str1 = String.valueOf(iOException.getMessage());
        if (str1.length() != 0) {
          "Topic sync failed: ".concat(str1);
        } else {
          new String("Topic sync failed: ");
        } 
        return false;
      } 
    } 
    return true;
  }
  
  private final String zzar() {
    zzaw zzaw1;
    String[] arrayOfString;
    synchronized (this.zzaj) {
      String str = this.zzaj.zzak();
      if (!TextUtils.isEmpty(str)) {
        arrayOfString = str.split(",");
        if (arrayOfString.length > 1 && !TextUtils.isEmpty(arrayOfString[1]))
          return arrayOfString[1]; 
      } 
      return null;
    } 
  }
  
  private final boolean zzk(String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzaj : Lcom/google/firebase/iid/zzaw;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield zzaj : Lcom/google/firebase/iid/zzaw;
    //   13: invokevirtual zzak : ()Ljava/lang/String;
    //   16: astore #4
    //   18: aload_1
    //   19: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   22: astore_2
    //   23: aload_2
    //   24: invokevirtual length : ()I
    //   27: ifeq -> 40
    //   30: ldc ','
    //   32: aload_2
    //   33: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   36: astore_2
    //   37: goto -> 50
    //   40: new java/lang/String
    //   43: dup
    //   44: ldc ','
    //   46: invokespecial <init> : (Ljava/lang/String;)V
    //   49: astore_2
    //   50: aload #4
    //   52: aload_2
    //   53: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   56: ifeq -> 115
    //   59: aload_1
    //   60: invokestatic valueOf : (Ljava/lang/Object;)Ljava/lang/String;
    //   63: astore_1
    //   64: aload_1
    //   65: invokevirtual length : ()I
    //   68: ifeq -> 81
    //   71: ldc ','
    //   73: aload_1
    //   74: invokevirtual concat : (Ljava/lang/String;)Ljava/lang/String;
    //   77: astore_1
    //   78: goto -> 91
    //   81: new java/lang/String
    //   84: dup
    //   85: ldc ','
    //   87: invokespecial <init> : (Ljava/lang/String;)V
    //   90: astore_1
    //   91: aload #4
    //   93: aload_1
    //   94: invokevirtual length : ()I
    //   97: invokevirtual substring : (I)Ljava/lang/String;
    //   100: astore_1
    //   101: aload_0
    //   102: getfield zzaj : Lcom/google/firebase/iid/zzaw;
    //   105: aload_1
    //   106: invokevirtual zzf : (Ljava/lang/String;)V
    //   109: aload_3
    //   110: monitorexit
    //   111: aload_0
    //   112: monitorexit
    //   113: iconst_1
    //   114: ireturn
    //   115: aload_3
    //   116: monitorexit
    //   117: aload_0
    //   118: monitorexit
    //   119: iconst_0
    //   120: ireturn
    //   121: astore_1
    //   122: aload_3
    //   123: monitorexit
    //   124: aload_1
    //   125: athrow
    //   126: astore_1
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_1
    //   130: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	126	finally
    //   9	37	121	finally
    //   40	50	121	finally
    //   50	78	121	finally
    //   81	91	121	finally
    //   91	111	121	finally
    //   115	117	121	finally
    //   122	124	121	finally
    //   124	126	126	finally
  }
  
  final boolean zzaq() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial zzar : ()Ljava/lang/String;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull -> 17
    //   11: iconst_1
    //   12: istore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: iload_1
    //   16: ireturn
    //   17: iconst_0
    //   18: istore_1
    //   19: goto -> 13
    //   22: astore_2
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_2
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	22	finally
  }
  
  final boolean zzc(FirebaseInstanceId paramFirebaseInstanceId) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial zzar : ()Ljava/lang/String;
    //   6: astore_3
    //   7: aload_3
    //   8: ifnonnull -> 19
    //   11: invokestatic zzl : ()Z
    //   14: pop
    //   15: aload_0
    //   16: monitorexit
    //   17: iconst_1
    //   18: ireturn
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: aload_3
    //   23: invokestatic zza : (Lcom/google/firebase/iid/FirebaseInstanceId;Ljava/lang/String;)Z
    //   26: ifne -> 31
    //   29: iconst_0
    //   30: ireturn
    //   31: aload_0
    //   32: monitorenter
    //   33: aload_0
    //   34: getfield zzdm : Ljava/util/Map;
    //   37: aload_0
    //   38: getfield zzdl : I
    //   41: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   44: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   49: checkcast com/google/android/gms/tasks/TaskCompletionSource
    //   52: astore_2
    //   53: aload_0
    //   54: aload_3
    //   55: invokespecial zzk : (Ljava/lang/String;)Z
    //   58: pop
    //   59: aload_0
    //   60: aload_0
    //   61: getfield zzdl : I
    //   64: iconst_1
    //   65: iadd
    //   66: putfield zzdl : I
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_2
    //   72: ifnull -> 0
    //   75: aload_2
    //   76: aconst_null
    //   77: invokevirtual setResult : (Ljava/lang/Object;)V
    //   80: goto -> 0
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    //   88: astore_1
    //   89: aload_0
    //   90: monitorexit
    //   91: aload_1
    //   92: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	88	finally
    //   11	17	88	finally
    //   19	21	88	finally
    //   33	71	83	finally
    //   84	86	83	finally
    //   89	91	88	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */