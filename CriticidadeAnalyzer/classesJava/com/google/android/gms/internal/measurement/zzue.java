package com.google.android.gms.internal.measurement;

final class zzue {
  private static final zzuc<?> zzbvh = new zzud();
  
  private static final zzuc<?> zzbvi = zzvt();
  
  private static zzuc<?> zzvt() {
    try {
      return Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  static zzuc<?> zzvu() {
    return zzbvh;
  }
  
  static zzuc<?> zzvv() {
    zzuc<?> zzuc1 = zzbvi;
    if (zzuc1 != null)
      return zzuc1; 
    throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */