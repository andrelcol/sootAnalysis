package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import com.google.android.gms.internal.measurement.zzdi;

public final class zzfj extends zzfm {
  private final zzy zzatg;
  
  private final AlarmManager zzyt = (AlarmManager)getContext().getSystemService("alarm");
  
  private Integer zzyu;
  
  protected zzfj(zzfn paramzzfn) {
    super(paramzzfn);
    this.zzatg = new zzfk(this, paramzzfn.zzmh(), paramzzfn);
  }
  
  private final int getJobId() {
    if (this.zzyu == null) {
      String str = String.valueOf(getContext().getPackageName());
      if (str.length() != 0) {
        str = "measurement".concat(str);
      } else {
        str = new String("measurement");
      } 
      this.zzyu = Integer.valueOf(str.hashCode());
    } 
    return this.zzyu.intValue();
  }
  
  private final PendingIntent zzeo() {
    Context context = getContext();
    return PendingIntent.getBroadcast(context, 0, (new Intent()).setClassName(context, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), 0);
  }
  
  @TargetApi(24)
  private final void zzlr() {
    JobScheduler jobScheduler = (JobScheduler)getContext().getSystemService("jobscheduler");
    int i = getJobId();
    zzgt().zzjo().zzg("Cancelling job. JobID", Integer.valueOf(i));
    jobScheduler.cancel(i);
  }
  
  public final void cancel() {
    zzcl();
    this.zzyt.cancel(zzeo());
    this.zzatg.cancel();
    if (Build.VERSION.SDK_INT >= 24)
      zzlr(); 
  }
  
  protected final boolean zzgy() {
    this.zzyt.cancel(zzeo());
    if (Build.VERSION.SDK_INT >= 24)
      zzlr(); 
    return false;
  }
  
  public final void zzh(long paramLong) {
    zzcl();
    zzgw();
    Context context = getContext();
    if (!zzbm.zza(context))
      zzgt().zzjn().zzby("Receiver not registered/enabled"); 
    if (!zzfx.zza(context, false))
      zzgt().zzjn().zzby("Service not registered/enabled"); 
    cancel();
    long l = zzbx().elapsedRealtime();
    if (paramLong < Math.max(0L, ((Long)zzai.zzajr.get()).longValue()) && !this.zzatg.zzej()) {
      zzgt().zzjo().zzby("Scheduling upload with DelayedRunnable");
      this.zzatg.zzh(paramLong);
    } 
    zzgw();
    if (Build.VERSION.SDK_INT >= 24) {
      zzgt().zzjo().zzby("Scheduling upload with JobScheduler");
      context = getContext();
      ComponentName componentName = new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementJobService");
      int i = getJobId();
      PersistableBundle persistableBundle = new PersistableBundle();
      persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
      JobInfo jobInfo = (new JobInfo.Builder(i, componentName)).setMinimumLatency(paramLong).setOverrideDeadline(paramLong << 1L).setExtras(persistableBundle).build();
      zzgt().zzjo().zzg("Scheduling job. JobID", Integer.valueOf(i));
      zzdi.zza(context, jobInfo, "com.google.android.gms", "UploadAlarm");
      return;
    } 
    zzgt().zzjo().zzby("Scheduling upload with AlarmManager");
    this.zzyt.setInexactRepeating(2, l + paramLong, Math.max(((Long)zzai.zzajm.get()).longValue(), paramLong), zzeo());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */