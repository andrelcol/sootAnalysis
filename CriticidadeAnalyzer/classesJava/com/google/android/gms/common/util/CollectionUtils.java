package com.google.android.gms.common.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class CollectionUtils {
  @Deprecated
  public static <T> List<T> listOf() {
    return Collections.emptyList();
  }
  
  @Deprecated
  public static <T> List<T> listOf(T paramT) {
    return Collections.singletonList(paramT);
  }
  
  @Deprecated
  public static <T> List<T> listOf(T... paramVarArgs) {
    int i = paramVarArgs.length;
    return (i != 0) ? ((i != 1) ? Collections.unmodifiableList(Arrays.asList(paramVarArgs)) : listOf(paramVarArgs[0])) : listOf();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/util/CollectionUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */