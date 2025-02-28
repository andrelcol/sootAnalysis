package com.google.firebase.iid;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Base64;
import com.google.firebase.FirebaseApp;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class zzan {
  private String zzci;
  
  private String zzcj;
  
  private int zzck;
  
  private int zzcl = 0;
  
  private final Context zzx;
  
  public zzan(Context paramContext) {
    this.zzx = paramContext;
  }
  
  public static String zza(FirebaseApp paramFirebaseApp) {
    String str3 = paramFirebaseApp.getOptions().getGcmSenderId();
    if (str3 != null)
      return str3; 
    String str2 = paramFirebaseApp.getOptions().getApplicationId();
    if (!str2.startsWith("1:"))
      return str2; 
    String[] arrayOfString = str2.split(":");
    if (arrayOfString.length < 2)
      return null; 
    String str1 = arrayOfString[1];
    return str1.isEmpty() ? null : str1;
  }
  
  public static String zza(KeyPair paramKeyPair) {
    byte[] arrayOfByte = paramKeyPair.getPublic().getEncoded();
    try {
      arrayOfByte = MessageDigest.getInstance("SHA1").digest(arrayOfByte);
      arrayOfByte[0] = (byte)((arrayOfByte[0] & 0xF) + 112);
      return Base64.encodeToString(arrayOfByte, 0, 8, 11);
    } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
      return null;
    } 
  }
  
  private final void zzag() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield zzx : Landroid/content/Context;
    //   7: invokevirtual getPackageName : ()Ljava/lang/String;
    //   10: invokespecial zze : (Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull -> 37
    //   18: aload_0
    //   19: aload_1
    //   20: getfield versionCode : I
    //   23: invokestatic toString : (I)Ljava/lang/String;
    //   26: putfield zzci : Ljava/lang/String;
    //   29: aload_0
    //   30: aload_1
    //   31: getfield versionName : Ljava/lang/String;
    //   34: putfield zzcj : Ljava/lang/String;
    //   37: aload_0
    //   38: monitorexit
    //   39: return
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	40	finally
    //   18	37	40	finally
  }
  
  private final PackageInfo zze(String paramString) {
    try {
      return this.zzx.getPackageManager().getPackageInfo(paramString, 0);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      String str = String.valueOf(nameNotFoundException);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 23);
      stringBuilder.append("Failed to find package ");
      stringBuilder.append(str);
      stringBuilder.toString();
      return null;
    } 
  }
  
  public final int zzac() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzcl : I
    //   6: ifeq -> 18
    //   9: aload_0
    //   10: getfield zzcl : I
    //   13: istore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_1
    //   17: ireturn
    //   18: aload_0
    //   19: getfield zzx : Landroid/content/Context;
    //   22: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   25: astore_2
    //   26: aload_2
    //   27: ldc 'com.google.android.c2dm.permission.SEND'
    //   29: ldc 'com.google.android.gms'
    //   31: invokevirtual checkPermission : (Ljava/lang/String;Ljava/lang/String;)I
    //   34: istore_1
    //   35: iload_1
    //   36: iconst_m1
    //   37: if_icmpne -> 44
    //   40: aload_0
    //   41: monitorexit
    //   42: iconst_0
    //   43: ireturn
    //   44: invokestatic isAtLeastO : ()Z
    //   47: ifne -> 101
    //   50: new android/content/Intent
    //   53: astore_3
    //   54: aload_3
    //   55: ldc 'com.google.android.c2dm.intent.REGISTER'
    //   57: invokespecial <init> : (Ljava/lang/String;)V
    //   60: aload_3
    //   61: ldc 'com.google.android.gms'
    //   63: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   66: pop
    //   67: aload_2
    //   68: aload_3
    //   69: iconst_0
    //   70: invokevirtual queryIntentServices : (Landroid/content/Intent;I)Ljava/util/List;
    //   73: astore_3
    //   74: aload_3
    //   75: ifnull -> 101
    //   78: aload_3
    //   79: invokeinterface size : ()I
    //   84: ifle -> 101
    //   87: aload_0
    //   88: iconst_1
    //   89: putfield zzcl : I
    //   92: aload_0
    //   93: getfield zzcl : I
    //   96: istore_1
    //   97: aload_0
    //   98: monitorexit
    //   99: iload_1
    //   100: ireturn
    //   101: new android/content/Intent
    //   104: astore_3
    //   105: aload_3
    //   106: ldc 'com.google.iid.TOKEN_REQUEST'
    //   108: invokespecial <init> : (Ljava/lang/String;)V
    //   111: aload_3
    //   112: ldc 'com.google.android.gms'
    //   114: invokevirtual setPackage : (Ljava/lang/String;)Landroid/content/Intent;
    //   117: pop
    //   118: aload_2
    //   119: aload_3
    //   120: iconst_0
    //   121: invokevirtual queryBroadcastReceivers : (Landroid/content/Intent;I)Ljava/util/List;
    //   124: astore_2
    //   125: aload_2
    //   126: ifnull -> 152
    //   129: aload_2
    //   130: invokeinterface size : ()I
    //   135: ifle -> 152
    //   138: aload_0
    //   139: iconst_2
    //   140: putfield zzcl : I
    //   143: aload_0
    //   144: getfield zzcl : I
    //   147: istore_1
    //   148: aload_0
    //   149: monitorexit
    //   150: iload_1
    //   151: ireturn
    //   152: invokestatic isAtLeastO : ()Z
    //   155: ifeq -> 166
    //   158: aload_0
    //   159: iconst_2
    //   160: putfield zzcl : I
    //   163: goto -> 171
    //   166: aload_0
    //   167: iconst_1
    //   168: putfield zzcl : I
    //   171: aload_0
    //   172: getfield zzcl : I
    //   175: istore_1
    //   176: aload_0
    //   177: monitorexit
    //   178: iload_1
    //   179: ireturn
    //   180: astore_2
    //   181: aload_0
    //   182: monitorexit
    //   183: aload_2
    //   184: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	180	finally
    //   18	35	180	finally
    //   44	74	180	finally
    //   78	97	180	finally
    //   101	125	180	finally
    //   129	148	180	finally
    //   152	163	180	finally
    //   166	171	180	finally
    //   171	176	180	finally
  }
  
  public final String zzad() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzci : Ljava/lang/String;
    //   6: ifnonnull -> 13
    //   9: aload_0
    //   10: invokespecial zzag : ()V
    //   13: aload_0
    //   14: getfield zzci : Ljava/lang/String;
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	22	finally
    //   13	18	22	finally
  }
  
  public final String zzae() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzcj : Ljava/lang/String;
    //   6: ifnonnull -> 13
    //   9: aload_0
    //   10: invokespecial zzag : ()V
    //   13: aload_0
    //   14: getfield zzcj : Ljava/lang/String;
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	22	finally
    //   13	18	22	finally
  }
  
  public final int zzaf() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzck : I
    //   6: ifne -> 28
    //   9: aload_0
    //   10: ldc 'com.google.android.gms'
    //   12: invokespecial zze : (Ljava/lang/String;)Landroid/content/pm/PackageInfo;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 28
    //   20: aload_0
    //   21: aload_2
    //   22: getfield versionCode : I
    //   25: putfield zzck : I
    //   28: aload_0
    //   29: getfield zzck : I
    //   32: istore_1
    //   33: aload_0
    //   34: monitorexit
    //   35: iload_1
    //   36: ireturn
    //   37: astore_2
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_2
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	37	finally
    //   20	28	37	finally
    //   28	33	37	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */