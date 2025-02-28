package com.google.android.gms.internal.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

final class zzac extends zzak {
  private final BaseImplementation.ResultHolder<Status> zzcq;
  
  public zzac(BaseImplementation.ResultHolder<Status> paramResultHolder) {
    this.zzcq = paramResultHolder;
  }
  
  public final void zza(zzad paramzzad) {
    this.zzcq.setResult(paramzzad.getStatus());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */