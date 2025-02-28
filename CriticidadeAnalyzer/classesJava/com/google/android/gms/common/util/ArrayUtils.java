package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.Objects;
import java.lang.reflect.Array;
import java.util.Arrays;

public final class ArrayUtils {
  public static <T> boolean contains(T[] paramArrayOfT, T paramT) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 10
    //   4: aload_0
    //   5: arraylength
    //   6: istore_3
    //   7: goto -> 12
    //   10: iconst_0
    //   11: istore_3
    //   12: iconst_0
    //   13: istore_2
    //   14: iload_2
    //   15: iload_3
    //   16: if_icmpge -> 38
    //   19: aload_0
    //   20: iload_2
    //   21: aaload
    //   22: aload_1
    //   23: invokestatic equal : (Ljava/lang/Object;Ljava/lang/Object;)Z
    //   26: ifeq -> 32
    //   29: goto -> 40
    //   32: iinc #2, 1
    //   35: goto -> 14
    //   38: iconst_m1
    //   39: istore_2
    //   40: iload_2
    //   41: iflt -> 46
    //   44: iconst_1
    //   45: ireturn
    //   46: iconst_0
    //   47: ireturn
  }
  
  public static <T> T[] removeAll(T[] paramArrayOfT1, T... paramVarArgs1) {
    int j;
    if (paramArrayOfT1 == null)
      return null; 
    if (paramVarArgs1 == null || paramVarArgs1.length == 0)
      return Arrays.copyOf(paramArrayOfT1, paramArrayOfT1.length); 
    Object[] arrayOfObject = (Object[])Array.newInstance(paramVarArgs1.getClass().getComponentType(), paramArrayOfT1.length);
    int i = paramVarArgs1.length;
    byte b = 0;
    if (i == 1) {
      int k = paramArrayOfT1.length;
      b = 0;
      i = 0;
      while (true) {
        j = i;
        if (b < k) {
          T t = paramArrayOfT1[b];
          j = i;
          if (!Objects.equal(paramVarArgs1[0], t)) {
            arrayOfObject[i] = t;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } else {
      int k = paramArrayOfT1.length;
      i = 0;
      while (true) {
        j = i;
        if (b < k) {
          T t = paramArrayOfT1[b];
          j = i;
          if (!contains(paramVarArgs1, t)) {
            arrayOfObject[i] = t;
            j = i + 1;
          } 
          b++;
          i = j;
          continue;
        } 
        break;
      } 
    } 
    if (arrayOfObject == null)
      return null; 
    paramArrayOfT1 = (T[])arrayOfObject;
    if (j != arrayOfObject.length)
      paramArrayOfT1 = Arrays.copyOf((T[])arrayOfObject, j); 
    return paramArrayOfT1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/ArrayUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */