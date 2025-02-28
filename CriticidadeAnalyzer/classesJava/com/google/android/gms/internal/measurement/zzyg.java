package com.google.android.gms.internal.measurement;

import java.nio.charset.Charset;
import java.util.Arrays;

public final class zzyg {
  protected static final Charset UTF_8 = Charset.forName("UTF-8");
  
  public static final Object zzcfe = new Object();
  
  public static boolean equals(int[] paramArrayOfint1, int[] paramArrayOfint2) {
    return (paramArrayOfint1 == null || paramArrayOfint1.length == 0) ? ((paramArrayOfint2 == null || paramArrayOfint2.length == 0)) : Arrays.equals(paramArrayOfint1, paramArrayOfint2);
  }
  
  public static boolean equals(long[] paramArrayOflong1, long[] paramArrayOflong2) {
    return (paramArrayOflong1 == null || paramArrayOflong1.length == 0) ? ((paramArrayOflong2 == null || paramArrayOflong2.length == 0)) : Arrays.equals(paramArrayOflong1, paramArrayOflong2);
  }
  
  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2) {
    int j;
    int k;
    if (paramArrayOfObject1 == null) {
      j = 0;
    } else {
      j = paramArrayOfObject1.length;
    } 
    if (paramArrayOfObject2 == null) {
      k = 0;
    } else {
      k = paramArrayOfObject2.length;
    } 
    byte b = 0;
    for (int i = 0;; i = m + 1) {
      byte b1;
      int m = i;
      if (b < j) {
        m = i;
        if (paramArrayOfObject1[b] == null) {
          b++;
          continue;
        } 
      } 
      while (m < k && paramArrayOfObject2[m] == null)
        m++; 
      if (b >= j) {
        i = 1;
      } else {
        i = 0;
      } 
      if (m >= k) {
        b1 = 1;
      } else {
        b1 = 0;
      } 
      if (i != 0 && b1)
        return true; 
      if (i != b1)
        return false; 
      if (!paramArrayOfObject1[b].equals(paramArrayOfObject2[m]))
        return false; 
      b++;
    } 
  }
  
  public static int hashCode(int[] paramArrayOfint) {
    return (paramArrayOfint == null || paramArrayOfint.length == 0) ? 0 : Arrays.hashCode(paramArrayOfint);
  }
  
  public static int hashCode(long[] paramArrayOflong) {
    return (paramArrayOflong == null || paramArrayOflong.length == 0) ? 0 : Arrays.hashCode(paramArrayOflong);
  }
  
  public static int hashCode(Object[] paramArrayOfObject) {
    int i;
    byte b = 0;
    if (paramArrayOfObject == null) {
      i = 0;
    } else {
      i = paramArrayOfObject.length;
    } 
    int j;
    for (j = 0; b < i; j = k) {
      Object object = paramArrayOfObject[b];
      int k = j;
      if (object != null)
        k = j * 31 + object.hashCode(); 
      b++;
    } 
    return j;
  }
  
  public static void zza(zzyc paramzzyc1, zzyc paramzzyc2) {
    zzye zzye = paramzzyc1.zzcev;
    if (zzye != null)
      paramzzyc2.zzcev = (zzye)zzye.clone(); 
  }
  
  static {
    Charset.forName("ISO-8859-1");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzyg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */