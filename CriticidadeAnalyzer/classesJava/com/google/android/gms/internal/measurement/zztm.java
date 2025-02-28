package com.google.android.gms.internal.measurement;

final class zztm {
  private final byte[] buffer;
  
  private final zztv zzbty;
  
  private zztm(int paramInt) {
    this.buffer = new byte[paramInt];
    this.zzbty = zztv.zzj(this.buffer);
  }
  
  public final zzte zzuh() {
    if (this.zzbty.zzvj() == 0)
      return new zzto(this.buffer); 
    throw new IllegalStateException("Did not write as much data as expected.");
  }
  
  public final zztv zzui() {
    return this.zzbty;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zztm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */