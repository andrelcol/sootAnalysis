package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbm {
  private final zzbp zzaog;
  
  public zzbm(zzbp paramzzbp) {
    Preconditions.checkNotNull(paramzzbp);
    this.zzaog = paramzzbp;
  }
  
  public static boolean zza(Context paramContext) {
    Preconditions.checkNotNull(paramContext);
    try {
      PackageManager packageManager = paramContext.getPackageManager();
      if (packageManager == null)
        return false; 
      ComponentName componentName = new ComponentName();
      this(paramContext, "com.google.android.gms.measurement.AppMeasurementReceiver");
      ActivityInfo activityInfo = packageManager.getReceiverInfo(componentName, 0);
      if (activityInfo != null) {
        boolean bool = activityInfo.enabled;
        if (bool)
          return true; 
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    return false;
  }
  
  public final void onReceive(Context paramContext, Intent paramIntent) {
    zzbw zzbw = zzbw.zza(paramContext, (zzan)null);
    zzas zzas = zzbw.zzgt();
    if (paramIntent == null) {
      zzas.zzjj().zzby("Receiver called with null intent");
      return;
    } 
    zzbw.zzgw();
    String str = paramIntent.getAction();
    zzas.zzjo().zzg("Local receiver got", str);
    if ("com.google.android.gms.measurement.UPLOAD".equals(str)) {
      paramIntent = (new Intent()).setClassName(paramContext, "com.google.android.gms.measurement.AppMeasurementService");
      paramIntent.setAction("com.google.android.gms.measurement.UPLOAD");
      zzas.zzjo().zzby("Starting wakeful intent.");
      this.zzaog.doStartService(paramContext, paramIntent);
      return;
    } 
    if ("com.android.vending.INSTALL_REFERRER".equals(str)) {
      try {
        zzbr zzbr = zzbw.zzgs();
        zzbn zzbn = new zzbn();
        this(this, zzbw, zzas);
        zzbr.zzc(zzbn);
      } catch (Exception exception) {
        zzas.zzjj().zzg("Install Referrer Reporter encountered a problem", exception);
      } 
      BroadcastReceiver.PendingResult pendingResult = this.zzaog.doGoAsync();
      String str1 = paramIntent.getStringExtra("referrer");
      if (str1 == null) {
        zzas.zzjo().zzby("Install referrer extras are null");
        if (pendingResult != null)
          pendingResult.finish(); 
        return;
      } 
      zzas.zzjm().zzg("Install referrer extras are", str1);
      str = str1;
      if (!str1.contains("?")) {
        str = String.valueOf(str1);
        if (str.length() != 0) {
          str = "?".concat(str);
        } else {
          str = new String("?");
        } 
      } 
      Uri uri = Uri.parse(str);
      Bundle bundle = zzbw.zzgr().zza(uri);
      if (bundle == null) {
        zzas.zzjo().zzby("No campaign defined in install referrer broadcast");
        if (pendingResult != null) {
          pendingResult.finish();
          return;
        } 
      } else {
        long l = paramIntent.getLongExtra("referrer_timestamp_seconds", 0L) * 1000L;
        if (l == 0L)
          zzas.zzjj().zzby("Install referrer is missing timestamp"); 
        zzbw.zzgs().zzc(new zzbo(this, zzbw, l, bundle, paramContext, zzas, pendingResult));
      } 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */