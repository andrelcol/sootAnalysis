package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;

public final class zzey<T extends Context & zzfc> {
  private final T zzaby;
  
  public zzey(T paramT) {
    Preconditions.checkNotNull(paramT);
    this.zzaby = paramT;
  }
  
  private final void zzb(Runnable paramRunnable) {
    zzfn zzfn = zzfn.zzn((Context)this.zzaby);
    zzfn.zzgs().zzc(new zzfb(this, zzfn, paramRunnable));
  }
  
  private final zzas zzgt() {
    return zzbw.zza((Context)this.zzaby, (zzan)null).zzgt();
  }
  
  public final IBinder onBind(Intent paramIntent) {
    if (paramIntent == null) {
      zzgt().zzjg().zzby("onBind called with null intent");
      return null;
    } 
    String str = paramIntent.getAction();
    if ("com.google.android.gms.measurement.START".equals(str))
      return (IBinder)new zzby(zzfn.zzn((Context)this.zzaby)); 
    zzgt().zzjj().zzg("onBind received unknown action", str);
    return null;
  }
  
  public final void onCreate() {
    zzbw zzbw = zzbw.zza((Context)this.zzaby, (zzan)null);
    zzas zzas = zzbw.zzgt();
    zzbw.zzgw();
    zzas.zzjo().zzby("Local AppMeasurementService is starting up");
  }
  
  public final void onDestroy() {
    zzbw zzbw = zzbw.zza((Context)this.zzaby, (zzan)null);
    zzas zzas = zzbw.zzgt();
    zzbw.zzgw();
    zzas.zzjo().zzby("Local AppMeasurementService is shutting down");
  }
  
  public final void onRebind(Intent paramIntent) {
    if (paramIntent == null) {
      zzgt().zzjg().zzby("onRebind called with null intent");
      return;
    } 
    String str = paramIntent.getAction();
    zzgt().zzjo().zzg("onRebind called. action", str);
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    zzbw zzbw = zzbw.zza((Context)this.zzaby, (zzan)null);
    zzas zzas = zzbw.zzgt();
    if (paramIntent == null) {
      zzas.zzjj().zzby("AppMeasurementService started with null intent");
      return 2;
    } 
    String str = paramIntent.getAction();
    zzbw.zzgw();
    zzas.zzjo().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(paramInt2), str);
    if ("com.google.android.gms.measurement.UPLOAD".equals(str))
      zzb(new zzez(this, paramInt2, zzas, paramIntent)); 
    return 2;
  }
  
  @TargetApi(24)
  public final boolean onStartJob(JobParameters paramJobParameters) {
    zzbw zzbw = zzbw.zza((Context)this.zzaby, (zzan)null);
    zzas zzas = zzbw.zzgt();
    String str = paramJobParameters.getExtras().getString("action");
    zzbw.zzgw();
    zzas.zzjo().zzg("Local AppMeasurementJobService called. action", str);
    if ("com.google.android.gms.measurement.UPLOAD".equals(str))
      zzb(new zzfa(this, zzas, paramJobParameters)); 
    return true;
  }
  
  public final boolean onUnbind(Intent paramIntent) {
    if (paramIntent == null) {
      zzgt().zzjg().zzby("onUnbind called with null intent");
      return true;
    } 
    String str = paramIntent.getAction();
    zzgt().zzjo().zzg("onUnbind called for intent. action", str);
    return true;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */