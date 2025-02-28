package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.concurrent.atomic.AtomicReference;

public final class zzaq extends zzcs {
  private static final AtomicReference<String[]> zzalr = (AtomicReference)new AtomicReference<String>();
  
  private static final AtomicReference<String[]> zzals = (AtomicReference)new AtomicReference<String>();
  
  private static final AtomicReference<String[]> zzalt = (AtomicReference)new AtomicReference<String>();
  
  zzaq(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private static String zza(String paramString, String[] paramArrayOfString1, String[] paramArrayOfString2, AtomicReference<String[]> paramAtomicReference) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aload_2
    //   6: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   9: pop
    //   10: aload_3
    //   11: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   14: pop
    //   15: aload_1
    //   16: arraylength
    //   17: istore #6
    //   19: aload_2
    //   20: arraylength
    //   21: istore #5
    //   23: iconst_0
    //   24: istore #4
    //   26: iload #6
    //   28: iload #5
    //   30: if_icmpne -> 39
    //   33: iconst_1
    //   34: istore #7
    //   36: goto -> 42
    //   39: iconst_0
    //   40: istore #7
    //   42: iload #7
    //   44: invokestatic checkArgument : (Z)V
    //   47: iload #4
    //   49: aload_1
    //   50: arraylength
    //   51: if_icmpge -> 177
    //   54: aload_0
    //   55: aload_1
    //   56: iload #4
    //   58: aaload
    //   59: invokestatic zzv : (Ljava/lang/String;Ljava/lang/String;)Z
    //   62: ifeq -> 171
    //   65: aload_3
    //   66: monitorenter
    //   67: aload_3
    //   68: invokevirtual get : ()Ljava/lang/Object;
    //   71: checkcast [Ljava/lang/String;
    //   74: astore #8
    //   76: aload #8
    //   78: astore_0
    //   79: aload #8
    //   81: ifnonnull -> 95
    //   84: aload_2
    //   85: arraylength
    //   86: anewarray java/lang/String
    //   89: astore_0
    //   90: aload_3
    //   91: aload_0
    //   92: invokevirtual set : (Ljava/lang/Object;)V
    //   95: aload_0
    //   96: iload #4
    //   98: aaload
    //   99: ifnonnull -> 157
    //   102: new java/lang/StringBuilder
    //   105: astore #8
    //   107: aload #8
    //   109: invokespecial <init> : ()V
    //   112: aload #8
    //   114: aload_2
    //   115: iload #4
    //   117: aaload
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: pop
    //   122: aload #8
    //   124: ldc '('
    //   126: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: pop
    //   130: aload #8
    //   132: aload_1
    //   133: iload #4
    //   135: aaload
    //   136: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload #8
    //   142: ldc ')'
    //   144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   147: pop
    //   148: aload_0
    //   149: iload #4
    //   151: aload #8
    //   153: invokevirtual toString : ()Ljava/lang/String;
    //   156: aastore
    //   157: aload_0
    //   158: iload #4
    //   160: aaload
    //   161: astore_0
    //   162: aload_3
    //   163: monitorexit
    //   164: aload_0
    //   165: areturn
    //   166: astore_0
    //   167: aload_3
    //   168: monitorexit
    //   169: aload_0
    //   170: athrow
    //   171: iinc #4, 1
    //   174: goto -> 47
    //   177: aload_0
    //   178: areturn
    // Exception table:
    //   from	to	target	type
    //   67	76	166	finally
    //   84	95	166	finally
    //   102	157	166	finally
    //   162	164	166	finally
    //   167	169	166	finally
  }
  
  private final String zzb(zzad paramzzad) {
    return (paramzzad == null) ? null : (!zzjf() ? paramzzad.toString() : zzd(paramzzad.zziy()));
  }
  
  private final boolean zzjf() {
    zzgw();
    return (this.zzada.zzkn() && this.zzada.zzgt().isLoggable(3));
  }
  
  protected final String zza(zzab paramzzab) {
    if (paramzzab == null)
      return null; 
    if (!zzjf())
      return paramzzab.toString(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Event{appId='");
    stringBuilder.append(paramzzab.zztt);
    stringBuilder.append("', name='");
    stringBuilder.append(zzbt(paramzzab.name));
    stringBuilder.append("', params=");
    stringBuilder.append(zzb(paramzzab.zzahu));
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  protected final String zzb(zzag paramzzag) {
    if (paramzzag == null)
      return null; 
    if (!zzjf())
      return paramzzag.toString(); 
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("origin=");
    stringBuilder.append(paramzzag.origin);
    stringBuilder.append(",name=");
    stringBuilder.append(zzbt(paramzzag.name));
    stringBuilder.append(",params=");
    stringBuilder.append(zzb(paramzzag.zzahu));
    return stringBuilder.toString();
  }
  
  protected final String zzbt(String paramString) {
    return (paramString == null) ? null : (!zzjf() ? paramString : zza(paramString, zzcu.zzaqu, zzcu.zzaqt, zzalr));
  }
  
  protected final String zzbu(String paramString) {
    return (paramString == null) ? null : (!zzjf() ? paramString : zza(paramString, zzcv.zzaqw, zzcv.zzaqv, zzals));
  }
  
  protected final String zzbv(String paramString) {
    if (paramString == null)
      return null; 
    if (!zzjf())
      return paramString; 
    if (paramString.startsWith("_exp_")) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append("experiment_id");
      stringBuilder.append("(");
      stringBuilder.append(paramString);
      stringBuilder.append(")");
      return stringBuilder.toString();
    } 
    return zza(paramString, zzcw.zzaqy, zzcw.zzaqx, zzalt);
  }
  
  protected final String zzd(Bundle paramBundle) {
    if (paramBundle == null)
      return null; 
    if (!zzjf())
      return paramBundle.toString(); 
    StringBuilder stringBuilder = new StringBuilder();
    for (String str : paramBundle.keySet()) {
      if (stringBuilder.length() != 0) {
        stringBuilder.append(", ");
      } else {
        stringBuilder.append("Bundle[{");
      } 
      stringBuilder.append(zzbu(str));
      stringBuilder.append("=");
      stringBuilder.append(paramBundle.get(str));
    } 
    stringBuilder.append("}]");
    return stringBuilder.toString();
  }
  
  protected final boolean zzgy() {
    return false;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzaq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */