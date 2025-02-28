package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;

final class zzfi {
  private long startTime;
  
  private final Clock zzrz;
  
  public zzfi(Clock paramClock) {
    Preconditions.checkNotNull(paramClock);
    this.zzrz = paramClock;
  }
  
  public final void clear() {
    this.startTime = 0L;
  }
  
  public final void start() {
    this.startTime = this.zzrz.elapsedRealtime();
  }
  
  public final boolean zzj(long paramLong) {
    return (this.startTime == 0L) ? true : ((this.zzrz.elapsedRealtime() - this.startTime >= 3600000L));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */