package com.google.android.gms.measurement.internal;

final class zzbn implements Runnable {
  private final zzbw zzaoh;
  
  private final zzas zzaoi;
  
  zzbn(zzbm paramzzbm, zzbw paramzzbw, zzas paramzzas) {}
  
  public final void run() {
    if (this.zzaoh.zzkk() == null) {
      this.zzaoi.zzjg().zzby("Install Referrer Reporter is null");
      return;
    } 
    zzbj zzbj = this.zzaoh.zzkk();
    zzbj.zzada.zzgg();
    zzbj.zzce(zzbj.zzada.getContext().getPackageName());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */