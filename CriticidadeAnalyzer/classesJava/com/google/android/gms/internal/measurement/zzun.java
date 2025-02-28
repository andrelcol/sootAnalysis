package com.google.android.gms.internal.measurement;

final class zzun implements zzvu {
  private static final zzun zzbye = new zzun();
  
  public static zzun zzwe() {
    return zzbye;
  }
  
  public final boolean zze(Class<?> paramClass) {
    return zzuo.class.isAssignableFrom(paramClass);
  }
  
  public final zzvt zzf(Class<?> paramClass) {
    String str;
    if (!zzuo.class.isAssignableFrom(paramClass)) {
      str = String.valueOf(paramClass.getName());
      if (str.length() != 0) {
        str = "Unsupported message type: ".concat(str);
      } else {
        str = new String("Unsupported message type: ");
      } 
      throw new IllegalArgumentException(str);
    } 
    try {
      return (zzvt)zzuo.<zzuo<?, ?>>zzg(str.asSubclass(zzuo.class)).zza(zzuo.zze.zzbyo, (Object)null, (Object)null);
    } catch (Exception exception) {
      str = String.valueOf(str.getName());
      if (str.length() != 0) {
        str = "Unable to get message info for ".concat(str);
      } else {
        str = new String("Unable to get message info for ");
      } 
      throw new RuntimeException(str, exception);
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzun.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */