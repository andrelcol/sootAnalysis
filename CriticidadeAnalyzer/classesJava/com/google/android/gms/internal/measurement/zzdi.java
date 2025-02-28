package com.google.android.gms.internal.measurement;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.Method;

@TargetApi(24)
public final class zzdi {
  private static final Method zzacv = zzfu();
  
  private static final Method zzacw = zzfv();
  
  private static volatile zzdk zzacx = zzdj.zzacy;
  
  private final JobScheduler zzacu;
  
  private zzdi(JobScheduler paramJobScheduler) {
    this.zzacu = paramJobScheduler;
  }
  
  private final int zza(JobInfo paramJobInfo, String paramString1, int paramInt, String paramString2) {
    Method method = zzacv;
    if (method != null)
      try {
        return ((Integer)method.invoke(this.zzacu, new Object[] { paramJobInfo, paramString1, Integer.valueOf(paramInt), paramString2 })).intValue();
      } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {} 
    return this.zzacu.schedule(paramJobInfo);
  }
  
  public static int zza(Context paramContext, JobInfo paramJobInfo, String paramString1, String paramString2) {
    JobScheduler jobScheduler = (JobScheduler)paramContext.getSystemService("jobscheduler");
    return (zzacv == null || !zzacx.zzfy() || paramContext.checkSelfPermission("android.permission.UPDATE_DEVICE_STATS") != 0) ? jobScheduler.schedule(paramJobInfo) : (new zzdi(jobScheduler)).zza(paramJobInfo, paramString1, zzfw(), paramString2);
  }
  
  private static Method zzfu() {
    if (Build.VERSION.SDK_INT >= 24)
      try {
        return JobScheduler.class.getDeclaredMethod("scheduleAsPackage", new Class[] { JobInfo.class, String.class, int.class, String.class });
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.isLoggable("JobSchedulerCompat", 6);
      }  
    return null;
  }
  
  private static Method zzfv() {
    if (Build.VERSION.SDK_INT >= 24)
      try {
        return UserHandle.class.getDeclaredMethod("myUserId", null);
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.isLoggable("JobSchedulerCompat", 6);
      }  
    return null;
  }
  
  private static int zzfw() {
    Method method = zzacw;
    if (method != null)
      try {
        return ((Integer)method.invoke(null, new Object[0])).intValue();
      } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {
        Log.isLoggable("JobSchedulerCompat", 6);
      }  
    return 0;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/measurement/zzdi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */