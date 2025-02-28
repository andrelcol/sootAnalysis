package com.google.android.gms.internal.measurement;

final class zzua {
  private static final Class<?> zzbvb = zzvn();
  
  private static final zzub zzge(String paramString) throws Exception {
    return (zzub)zzbvb.getDeclaredMethod(paramString, new Class[0]).invoke(null, new Object[0]);
  }
  
  private static Class<?> zzvn() {
    try {
      return Class.forName("com.google.protobuf.ExtensionRegistry");
    } catch (ClassNotFoundException classNotFoundException) {
      return null;
    } 
  }
  
  public static zzub zzvo() {
    if (zzbvb != null)
      try {
        return zzge("getEmptyRegistry");
      } catch (Exception exception) {} 
    return zzub.zzbvf;
  }
  
  static zzub zzvp() {
    // Byte code:
    //   0: getstatic com/google/android/gms/internal/measurement/zzua.zzbvb : Ljava/lang/Class;
    //   3: ifnull -> 15
    //   6: ldc 'loadGeneratedRegistry'
    //   8: invokestatic zzge : (Ljava/lang/String;)Lcom/google/android/gms/internal/measurement/zzub;
    //   11: astore_1
    //   12: goto -> 17
    //   15: aconst_null
    //   16: astore_1
    //   17: aload_1
    //   18: astore_0
    //   19: aload_1
    //   20: ifnonnull -> 27
    //   23: invokestatic zzvp : ()Lcom/google/android/gms/internal/measurement/zzub;
    //   26: astore_0
    //   27: aload_0
    //   28: astore_1
    //   29: aload_0
    //   30: ifnonnull -> 37
    //   33: invokestatic zzvo : ()Lcom/google/android/gms/internal/measurement/zzub;
    //   36: astore_1
    //   37: aload_1
    //   38: areturn
    //   39: astore_0
    //   40: goto -> 15
    // Exception table:
    //   from	to	target	type
    //   6	12	39	java/lang/Exception
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzua.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */