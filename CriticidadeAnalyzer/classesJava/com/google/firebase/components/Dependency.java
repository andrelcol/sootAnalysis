package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;

public final class Dependency {
  private final Class<?> zza;
  
  private final int zzb;
  
  private final int zzc;
  
  private Dependency(Class<?> paramClass, int paramInt1, int paramInt2) {
    Preconditions.checkNotNull(paramClass, "Null dependency anInterface.");
    this.zza = paramClass;
    this.zzb = paramInt1;
    this.zzc = paramInt2;
  }
  
  public static Dependency required(Class<?> paramClass) {
    return new Dependency(paramClass, 1, 0);
  }
  
  public final boolean equals(Object paramObject) {
    if (paramObject instanceof Dependency) {
      paramObject = paramObject;
      if (this.zza == ((Dependency)paramObject).zza && this.zzb == ((Dependency)paramObject).zzb && this.zzc == ((Dependency)paramObject).zzc)
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return ((this.zza.hashCode() ^ 0xF4243) * 1000003 ^ this.zzb) * 1000003 ^ this.zzc;
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder("Dependency{anInterface=");
    stringBuilder.append(this.zza);
    stringBuilder.append(", required=");
    int i = this.zzb;
    boolean bool2 = false;
    if (i == 1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    stringBuilder.append(bool1);
    stringBuilder.append(", direct=");
    boolean bool1 = bool2;
    if (this.zzc == 0)
      bool1 = true; 
    stringBuilder.append(bool1);
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
  
  public final Class<?> zza() {
    return this.zza;
  }
  
  public final boolean zzb() {
    return (this.zzb == 1);
  }
  
  public final boolean zzc() {
    return (this.zzc == 0);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/Dependency.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */