package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

@TargetApi(14)
final class zzdu implements Application.ActivityLifecycleCallbacks {
  private final zzda zzarh;
  
  private zzdu(zzda paramzzda) {}
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    try {
      this.zzarh.zzgt().zzjo().zzby("onActivityCreated");
      Intent intent = paramActivity.getIntent();
      if (intent != null) {
        Uri uri = intent.getData();
        if (uri != null) {
          boolean bool = uri.isHierarchical();
          if (bool) {
            boolean bool1;
            if (paramBundle == null) {
              String str1;
              Bundle bundle = this.zzarh.zzgr().zza(uri);
              this.zzarh.zzgr();
              if (zzfx.zzc(intent)) {
                str1 = "gs";
              } else {
                str1 = "auto";
              } 
              if (bundle != null)
                this.zzarh.logEvent(str1, "_cmp", bundle); 
            } 
            String str = uri.getQueryParameter("referrer");
            if (TextUtils.isEmpty(str))
              return; 
            if (str.contains("gclid") && (str.contains("utm_campaign") || str.contains("utm_source") || str.contains("utm_medium") || str.contains("utm_term") || str.contains("utm_content"))) {
              bool1 = true;
            } else {
              bool1 = false;
            } 
            if (!bool1) {
              this.zzarh.zzgt().zzjn().zzby("Activity created with data 'referrer' param without gclid and at least one utm field");
              return;
            } 
            this.zzarh.zzgt().zzjn().zzg("Activity created with referrer", str);
            if (!TextUtils.isEmpty(str))
              this.zzarh.zzb("auto", "_ldl", str, true); 
          } 
        } 
      } 
    } catch (Exception exception) {
      this.zzarh.zzgt().zzjg().zzg("Throwable caught in onActivityCreated", exception);
    } 
    this.zzarh.zzgm().onActivityCreated(paramActivity, paramBundle);
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {
    this.zzarh.zzgm().onActivityDestroyed(paramActivity);
  }
  
  public final void onActivityPaused(Activity paramActivity) {
    this.zzarh.zzgm().onActivityPaused(paramActivity);
    zzfd zzfd = this.zzarh.zzgo();
    long l = zzfd.zzbx().elapsedRealtime();
    zzfd.zzgs().zzc(new zzfh(zzfd, l));
  }
  
  public final void onActivityResumed(Activity paramActivity) {
    this.zzarh.zzgm().onActivityResumed(paramActivity);
    zzfd zzfd = this.zzarh.zzgo();
    long l = zzfd.zzbx().elapsedRealtime();
    zzfd.zzgs().zzc(new zzfg(zzfd, l));
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    this.zzarh.zzgm().onActivitySaveInstanceState(paramActivity, paramBundle);
  }
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzdu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */