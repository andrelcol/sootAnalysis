package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.internal.BaseGmsClient;

public final class zzar extends BaseGmsClient<zzaj> {
  public zzar(Context paramContext, Looper paramLooper, BaseGmsClient.BaseConnectionCallbacks paramBaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener paramBaseOnConnectionFailedListener) {
    super(paramContext, paramLooper, 93, paramBaseConnectionCallbacks, paramBaseOnConnectionFailedListener, null);
  }
  
  public final int getMinApkVersion() {
    return 12451000;
  }
  
  protected final String getServiceDescriptor() {
    return "com.google.android.gms.measurement.internal.IMeasurementService";
  }
  
  protected final String getStartServiceAction() {
    return "com.google.android.gms.measurement.START";
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */