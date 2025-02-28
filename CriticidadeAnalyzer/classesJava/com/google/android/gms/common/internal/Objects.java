package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Objects {
  public static boolean equal(Object paramObject1, Object paramObject2) {
    return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject1.equals(paramObject2)));
  }
  
  public static int hashCode(Object... paramVarArgs) {
    return Arrays.hashCode(paramVarArgs);
  }
  
  public static ToStringHelper toStringHelper(Object paramObject) {
    return new ToStringHelper(paramObject, null);
  }
  
  public static final class ToStringHelper {
    private final List<String> zzer;
    
    private final Object zzes;
    
    private ToStringHelper(Object param1Object) {
      Preconditions.checkNotNull(param1Object);
      this.zzes = param1Object;
      this.zzer = new ArrayList<String>();
    }
    
    public final ToStringHelper add(String param1String, Object param1Object) {
      List<String> list = this.zzer;
      Preconditions.checkNotNull(param1String);
      param1String = param1String;
      param1Object = String.valueOf(param1Object);
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(param1String).length() + 1 + String.valueOf(param1Object).length());
      stringBuilder.append(param1String);
      stringBuilder.append("=");
      stringBuilder.append((String)param1Object);
      list.add(stringBuilder.toString());
      return this;
    }
    
    public final String toString() {
      StringBuilder stringBuilder = new StringBuilder(100);
      stringBuilder.append(this.zzes.getClass().getSimpleName());
      stringBuilder.append('{');
      int i = this.zzer.size();
      for (byte b = 0; b < i; b++) {
        stringBuilder.append(this.zzer.get(b));
        if (b < i - 1)
          stringBuilder.append(", "); 
      } 
      stringBuilder.append('}');
      return stringBuilder.toString();
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/Objects.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */