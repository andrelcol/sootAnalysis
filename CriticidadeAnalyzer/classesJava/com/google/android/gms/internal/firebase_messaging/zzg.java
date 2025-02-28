package com.google.android.gms.internal.firebase_messaging;

final class zzg extends zzd {
  private final zze zzh = new zze();
  
  public final void zza(Throwable paramThrowable1, Throwable paramThrowable2) {
    if (paramThrowable2 != paramThrowable1) {
      if (paramThrowable2 != null) {
        this.zzh.zza(paramThrowable1, true).add(paramThrowable2);
        return;
      } 
      throw new NullPointerException("The suppressed exception cannot be null.");
    } 
    throw new IllegalArgumentException("Self suppression is not allowed.", paramThrowable2);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/firebase_messaging/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */