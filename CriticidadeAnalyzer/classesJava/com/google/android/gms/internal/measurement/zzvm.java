package com.google.android.gms.internal.measurement;

final class zzvm implements zzvu {
  private zzvu[] zzcaj;
  
  zzvm(zzvu... paramVarArgs) {
    this.zzcaj = paramVarArgs;
  }
  
  public final boolean zze(Class<?> paramClass) {
    zzvu[] arrayOfZzvu = this.zzcaj;
    int i = arrayOfZzvu.length;
    for (byte b = 0; b < i; b++) {
      if (arrayOfZzvu[b].zze(paramClass))
        return true; 
    } 
    return false;
  }
  
  public final zzvt zzf(Class<?> paramClass) {
    for (zzvu zzvu1 : this.zzcaj) {
      if (zzvu1.zze(paramClass))
        return zzvu1.zzf(paramClass); 
    } 
    String str = String.valueOf(paramClass.getName());
    if (str.length() != 0) {
      str = "No factory is available for message type: ".concat(str);
    } else {
      str = new String("No factory is available for message type: ");
    } 
    throw new UnsupportedOperationException(str);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzvm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */