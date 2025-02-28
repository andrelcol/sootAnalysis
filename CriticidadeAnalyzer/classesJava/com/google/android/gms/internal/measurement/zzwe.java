package com.google.android.gms.internal.measurement;

final class zzwe {
  private static final zzwc zzcbj = zzxs();
  
  private static final zzwc zzcbk = new zzwd();
  
  static zzwc zzxq() {
    return zzcbj;
  }
  
  static zzwc zzxr() {
    return zzcbk;
  }
  
  private static zzwc zzxs() {
    try {
      return Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzwe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */