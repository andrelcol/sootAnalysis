package com.google.android.gms.internal.measurement;

final class zztb {
  static {
    if (zzfz("org.robolectric.Robolectric") != null) {
      bool = true;
    } else {
      bool = false;
    } 
    zzbtp = bool;
  }
  
  private static <T> Class<T> zzfz(String paramString) {
    try {
      return (Class)Class.forName(paramString);
    } finally {
      paramString = null;
    } 
  }
  
  static boolean zzub() {
    return (zzbto != null && !zzbtp);
  }
  
  static Class<?> zzuc() {
    return zzbto;
  }
  
  static {
    boolean bool;
  }
  
  private static final Class<?> zzbto = zzfz("libcore.io.Memory");
  
  private static final boolean zzbtp;
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */