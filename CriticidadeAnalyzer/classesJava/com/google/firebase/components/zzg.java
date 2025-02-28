package com.google.firebase.components;

import java.util.HashSet;
import java.util.Set;

final class zzg {
  private final Component<?> zza;
  
  private final Set<zzg> zzb = new HashSet<zzg>();
  
  private final Set<zzg> zzc = new HashSet<zzg>();
  
  zzg(Component<?> paramComponent) {
    this.zza = paramComponent;
  }
  
  final Set<zzg> zza() {
    return this.zzb;
  }
  
  final void zza(zzg paramzzg) {
    this.zzb.add(paramzzg);
  }
  
  final Component<?> zzb() {
    return this.zza;
  }
  
  final void zzb(zzg paramzzg) {
    this.zzc.add(paramzzg);
  }
  
  final void zzc(zzg paramzzg) {
    this.zzc.remove(paramzzg);
  }
  
  final boolean zzc() {
    return this.zzc.isEmpty();
  }
  
  final boolean zzd() {
    return this.zzb.isEmpty();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/components/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */