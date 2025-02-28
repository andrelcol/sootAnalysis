package com.google.android.gms.internal.measurement;

import java.util.NoSuchElementException;

final class zztf extends zzth {
  private final int limit = this.zzbtv.size();
  
  private int position = 0;
  
  private final zzte zzbtv;
  
  zztf(zzte paramzzte) {}
  
  public final boolean hasNext() {
    return (this.position < this.limit);
  }
  
  public final byte nextByte() {
    int i = this.position;
    if (i < this.limit) {
      this.position = i + 1;
      return this.zzbtv.zzan(i);
    } 
    throw new NoSuchElementException();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */