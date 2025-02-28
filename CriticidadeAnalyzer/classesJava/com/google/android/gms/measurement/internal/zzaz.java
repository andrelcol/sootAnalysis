package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.List;
import java.util.Map;

final class zzaz implements Runnable {
  private final String packageName;
  
  private final int status;
  
  private final zzay zzamp;
  
  private final Throwable zzamq;
  
  private final byte[] zzamr;
  
  private final Map<String, List<String>> zzams;
  
  private zzaz(String paramString, zzay paramzzay, int paramInt, Throwable paramThrowable, byte[] paramArrayOfbyte, Map<String, List<String>> paramMap) {
    Preconditions.checkNotNull(paramzzay);
    this.zzamp = paramzzay;
    this.status = paramInt;
    this.zzamq = paramThrowable;
    this.zzamr = paramArrayOfbyte;
    this.packageName = paramString;
    this.zzams = paramMap;
  }
  
  public final void run() {
    this.zzamp.zza(this.packageName, this.status, this.zzamq, this.zzamr, this.zzams);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */