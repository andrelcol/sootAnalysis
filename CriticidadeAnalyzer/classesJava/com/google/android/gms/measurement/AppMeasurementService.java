package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.measurement.internal.zzey;
import com.google.android.gms.measurement.internal.zzfc;

public final class AppMeasurementService extends Service implements zzfc {
  private zzey<AppMeasurementService> zzadc;
  
  private final zzey<AppMeasurementService> zzfz() {
    if (this.zzadc == null)
      this.zzadc = new zzey((Context)this); 
    return this.zzadc;
  }
  
  public final boolean callServiceStopSelfResult(int paramInt) {
    return stopSelfResult(paramInt);
  }
  
  public final IBinder onBind(Intent paramIntent) {
    return zzfz().onBind(paramIntent);
  }
  
  public final void onCreate() {
    super.onCreate();
    zzfz().onCreate();
  }
  
  public final void onDestroy() {
    zzfz().onDestroy();
    super.onDestroy();
  }
  
  public final void onRebind(Intent paramIntent) {
    zzfz().onRebind(paramIntent);
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    return zzfz().onStartCommand(paramIntent, paramInt1, paramInt2);
  }
  
  public final boolean onUnbind(Intent paramIntent) {
    return zzfz().onUnbind(paramIntent);
  }
  
  public final void zza(JobParameters paramJobParameters, boolean paramBoolean) {
    throw new UnsupportedOperationException();
  }
  
  public final void zza(Intent paramIntent) {
    WakefulBroadcastReceiver.completeWakefulIntent(paramIntent);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/AppMeasurementService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */