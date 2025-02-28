package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzas extends zzcs {
  private long zzade = -1L;
  
  private char zzalu = Character.MIN_VALUE;
  
  private String zzalv;
  
  private final zzau zzalw = new zzau(this, 6, false, false);
  
  private final zzau zzalx = new zzau(this, 6, true, false);
  
  private final zzau zzaly = new zzau(this, 6, false, true);
  
  private final zzau zzalz = new zzau(this, 5, false, false);
  
  private final zzau zzama = new zzau(this, 5, true, false);
  
  private final zzau zzamb = new zzau(this, 5, false, true);
  
  private final zzau zzamc = new zzau(this, 4, false, false);
  
  private final zzau zzamd = new zzau(this, 3, false, false);
  
  private final zzau zzame = new zzau(this, 2, false, false);
  
  zzas(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private static String zza(boolean paramBoolean, Object paramObject) {
    String str = "";
    if (paramObject == null)
      return ""; 
    Object object = paramObject;
    if (paramObject instanceof Integer)
      object = Long.valueOf(((Integer)paramObject).intValue()); 
    boolean bool = object instanceof Long;
    byte b = 0;
    if (bool) {
      if (!paramBoolean)
        return String.valueOf(object); 
      Long long_ = (Long)object;
      if (Math.abs(long_.longValue()) < 100L)
        return String.valueOf(object); 
      paramObject = str;
      if (String.valueOf(object).charAt(0) == '-')
        paramObject = "-"; 
      object = String.valueOf(Math.abs(long_.longValue()));
      long l1 = Math.round(Math.pow(10.0D, (object.length() - 1)));
      long l2 = Math.round(Math.pow(10.0D, object.length()) - 1.0D);
      object = new StringBuilder(paramObject.length() + 43 + paramObject.length());
      object.append((String)paramObject);
      object.append(l1);
      object.append("...");
      object.append((String)paramObject);
      object.append(l2);
      return object.toString();
    } 
    if (object instanceof Boolean)
      return String.valueOf(object); 
    if (object instanceof Throwable) {
      Throwable throwable = (Throwable)object;
      if (paramBoolean) {
        paramObject = throwable.getClass().getName();
      } else {
        paramObject = throwable.toString();
      } 
      paramObject = new StringBuilder((String)paramObject);
      str = zzbx(AppMeasurement.class.getCanonicalName());
      object = zzbx(zzbw.class.getCanonicalName());
      StackTraceElement[] arrayOfStackTraceElement = throwable.getStackTrace();
      int i = arrayOfStackTraceElement.length;
      while (b < i) {
        StackTraceElement stackTraceElement = arrayOfStackTraceElement[b];
        if (!stackTraceElement.isNativeMethod()) {
          String str1 = stackTraceElement.getClassName();
          if (str1 != null) {
            str1 = zzbx(str1);
            if (str1.equals(str) || str1.equals(object)) {
              paramObject.append(": ");
              paramObject.append(stackTraceElement);
              break;
            } 
          } 
        } 
        b++;
      } 
      return paramObject.toString();
    } 
    return (object instanceof zzav) ? zzav.zza((zzav)object) : (paramBoolean ? "-" : String.valueOf(object));
  }
  
  static String zza(boolean paramBoolean, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    String str2 = "";
    String str1 = paramString;
    if (paramString == null)
      str1 = ""; 
    String str4 = zza(paramBoolean, paramObject1);
    String str3 = zza(paramBoolean, paramObject2);
    paramObject3 = zza(paramBoolean, paramObject3);
    paramObject2 = new StringBuilder();
    paramObject1 = str2;
    if (!TextUtils.isEmpty(str1)) {
      paramObject2.append(str1);
      paramObject1 = ": ";
    } 
    Object object = paramObject1;
    if (!TextUtils.isEmpty(str4)) {
      paramObject2.append((String)paramObject1);
      paramObject2.append(str4);
      object = ", ";
    } 
    paramObject1 = object;
    if (!TextUtils.isEmpty(str3)) {
      paramObject2.append((String)object);
      paramObject2.append(str3);
      paramObject1 = ", ";
    } 
    if (!TextUtils.isEmpty((CharSequence)paramObject3)) {
      paramObject2.append((String)paramObject1);
      paramObject2.append((String)paramObject3);
    } 
    return paramObject2.toString();
  }
  
  protected static Object zzbw(String paramString) {
    return (paramString == null) ? null : new zzav(paramString);
  }
  
  private static String zzbx(String paramString) {
    if (TextUtils.isEmpty(paramString))
      return ""; 
    int i = paramString.lastIndexOf('.');
    return (i == -1) ? paramString : paramString.substring(0, i);
  }
  
  private final String zzjp() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzalv : Ljava/lang/String;
    //   6: ifnonnull -> 40
    //   9: aload_0
    //   10: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   13: invokevirtual zzkq : ()Ljava/lang/String;
    //   16: ifnull -> 33
    //   19: aload_0
    //   20: aload_0
    //   21: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   24: invokevirtual zzkq : ()Ljava/lang/String;
    //   27: putfield zzalv : Ljava/lang/String;
    //   30: goto -> 40
    //   33: aload_0
    //   34: invokestatic zzhy : ()Ljava/lang/String;
    //   37: putfield zzalv : Ljava/lang/String;
    //   40: aload_0
    //   41: getfield zzalv : Ljava/lang/String;
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: areturn
    //   49: astore_1
    //   50: aload_0
    //   51: monitorexit
    //   52: aload_1
    //   53: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	49	finally
    //   33	40	49	finally
    //   40	47	49	finally
    //   50	52	49	finally
  }
  
  protected final boolean isLoggable(int paramInt) {
    return Log.isLoggable(zzjp(), paramInt);
  }
  
  protected final void zza(int paramInt, String paramString) {
    Log.println(paramInt, zzjp(), paramString);
  }
  
  protected final void zza(int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (!paramBoolean1 && isLoggable(paramInt))
      zza(paramInt, zza(false, paramString, paramObject1, paramObject2, paramObject3)); 
    if (!paramBoolean2 && paramInt >= 5) {
      Preconditions.checkNotNull(paramString);
      zzbr zzbr = this.zzada.zzkl();
      if (zzbr == null) {
        zza(6, "Scheduler not set. Not logging error/warn");
        return;
      } 
      if (!zzbr.isInitialized()) {
        zza(6, "Scheduler not initialized. Not logging error/warn");
        return;
      } 
      int i = paramInt;
      if (paramInt < 0)
        i = 0; 
      if (i >= 9)
        i = 8; 
      zzbr.zzc(new zzat(this, i, paramString, paramObject1, paramObject2, paramObject3));
    } 
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final zzau zzjg() {
    return this.zzalw;
  }
  
  public final zzau zzjh() {
    return this.zzalx;
  }
  
  public final zzau zzji() {
    return this.zzaly;
  }
  
  public final zzau zzjj() {
    return this.zzalz;
  }
  
  public final zzau zzjk() {
    return this.zzama;
  }
  
  public final zzau zzjl() {
    return this.zzamb;
  }
  
  public final zzau zzjm() {
    return this.zzamc;
  }
  
  public final zzau zzjn() {
    return this.zzamd;
  }
  
  public final zzau zzjo() {
    return this.zzame;
  }
  
  public final String zzjq() {
    Pair<String, Long> pair = (zzgu()).zzanb.zzfm();
    if (pair == null || pair == zzbd.zzana)
      return null; 
    String str1 = String.valueOf(pair.second);
    String str2 = (String)pair.first;
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 1 + String.valueOf(str2).length());
    stringBuilder.append(str1);
    stringBuilder.append(":");
    stringBuilder.append(str2);
    return stringBuilder.toString();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */