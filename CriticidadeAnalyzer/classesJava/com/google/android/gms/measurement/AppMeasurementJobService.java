package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzey;
import com.google.android.gms.measurement.internal.zzfc;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzfc {
  private zzey<AppMeasurementJobService> zzadc;
  
  private final zzey<AppMeasurementJobService> zzfz() {
    if (this.zzadc == null)
      this.zzadc = new zzey((Context)this); 
    return this.zzadc;
  }
  
  public final boolean callServiceStopSelfResult(int paramInt) {
    throw new UnsupportedOperationException();
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
  
  public final boolean onStartJob(JobParameters paramJobParameters) {
    return zzfz().onStartJob(paramJobParameters);
  }
  
  public final boolean onStopJob(JobParameters paramJobParameters) {
    return false;
  }
  
  public final boolean onUnbind(Intent paramIntent) {
    return zzfz().onUnbind(paramIntent);
  }
  
  @TargetApi(24)
  public final void zza(JobParameters paramJobParameters, boolean paramBoolean) {
    jobFinished(paramJobParameters, false);
  }
  
  public final void zza(Intent paramIntent) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/AppMeasurementJobService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */