package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class zzxi implements Iterator<String> {
  private final zzxg zzccm;
  
  private Iterator<String> zzccn = zzxg.zza(this.zzccm).iterator();
  
  zzxi(zzxg paramzzxg) {}
  
  public final boolean hasNext() {
    return this.zzccn.hasNext();
  }
  
  public final void remove() {
    throw new UnsupportedOperationException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzxi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */