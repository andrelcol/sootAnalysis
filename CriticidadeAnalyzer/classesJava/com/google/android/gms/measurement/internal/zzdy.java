package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

public final class zzdy extends zzf {
  protected zzdx zzart;
  
  private volatile zzdx zzaru;
  
  private zzdx zzarv;
  
  private final Map<Activity, zzdx> zzarw = (Map<Activity, zzdx>)new ArrayMap();
  
  private String zzary;
  
  public zzdy(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final void zza(Activity paramActivity, zzdx paramzzdx, boolean paramBoolean) {
    zzdx zzdx1;
    if (this.zzaru == null) {
      zzdx1 = this.zzarv;
    } else {
      zzdx1 = this.zzaru;
    } 
    zzdx zzdx2 = paramzzdx;
    if (paramzzdx.zzarq == null)
      zzdx2 = new zzdx(paramzzdx.zzuw, zzcq(paramActivity.getClass().getCanonicalName()), paramzzdx.zzarr); 
    this.zzarv = this.zzaru;
    this.zzaru = zzdx2;
    zzgs().zzc(new zzdz(this, paramBoolean, zzdx1, zzdx2));
  }
  
  public static void zza(zzdx paramzzdx, Bundle paramBundle, boolean paramBoolean) {
    if (paramBundle != null && paramzzdx != null && (!paramBundle.containsKey("_sc") || paramBoolean)) {
      String str = paramzzdx.zzuw;
      if (str != null) {
        paramBundle.putString("_sn", str);
      } else {
        paramBundle.remove("_sn");
      } 
      paramBundle.putString("_sc", paramzzdx.zzarq);
      paramBundle.putLong("_si", paramzzdx.zzarr);
      return;
    } 
    if (paramBundle != null && paramzzdx == null && paramBoolean) {
      paramBundle.remove("_sn");
      paramBundle.remove("_sc");
      paramBundle.remove("_si");
    } 
  }
  
  private final void zza(zzdx paramzzdx, boolean paramBoolean) {
    zzgi().zzm(zzbx().elapsedRealtime());
    if (zzgo().zza(paramzzdx.zzars, paramBoolean))
      paramzzdx.zzars = false; 
  }
  
  private static String zzcq(String paramString) {
    String str1;
    String[] arrayOfString = paramString.split("\\.");
    if (arrayOfString.length > 0) {
      str1 = arrayOfString[arrayOfString.length - 1];
    } else {
      str1 = "";
    } 
    String str2 = str1;
    if (str1.length() > 100)
      str2 = str1.substring(0, 100); 
    return str2;
  }
  
  private final zzdx zze(Activity paramActivity) {
    Preconditions.checkNotNull(paramActivity);
    zzdx zzdx2 = this.zzarw.get(paramActivity);
    zzdx zzdx1 = zzdx2;
    if (zzdx2 == null) {
      zzdx1 = new zzdx(null, zzcq(paramActivity.getClass().getCanonicalName()), zzgr().zzmj());
      this.zzarw.put(paramActivity, zzdx1);
    } 
    return zzdx1;
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle) {
    if (paramBundle == null)
      return; 
    paramBundle = paramBundle.getBundle("com.google.app_measurement.screen_service");
    if (paramBundle == null)
      return; 
    zzdx zzdx1 = new zzdx(paramBundle.getString("name"), paramBundle.getString("referrer_name"), paramBundle.getLong("id"));
    this.zzarw.put(paramActivity, zzdx1);
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {
    this.zzarw.remove(paramActivity);
  }
  
  public final void onActivityPaused(Activity paramActivity) {
    zzdx zzdx1 = zze(paramActivity);
    this.zzarv = this.zzaru;
    this.zzaru = null;
    zzgs().zzc(new zzea(this, zzdx1));
  }
  
  public final void onActivityResumed(Activity paramActivity) {
    zza(paramActivity, zze(paramActivity), false);
    zza zza = zzgi();
    long l = zza.zzbx().elapsedRealtime();
    zza.zzgs().zzc(new zzd(zza, l));
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {
    if (paramBundle == null)
      return; 
    zzdx zzdx1 = this.zzarw.get(paramActivity);
    if (zzdx1 == null)
      return; 
    Bundle bundle = new Bundle();
    bundle.putLong("id", zzdx1.zzarr);
    bundle.putString("name", zzdx1.zzuw);
    bundle.putString("referrer_name", zzdx1.zzarq);
    paramBundle.putBundle("com.google.app_measurement.screen_service", bundle);
  }
  
  public final void setCurrentScreen(Activity paramActivity, String paramString1, String paramString2) {
    if (this.zzaru == null) {
      zzgt().zzjj().zzby("setCurrentScreen cannot be called while no activity active");
      return;
    } 
    if (this.zzarw.get(paramActivity) == null) {
      zzgt().zzjj().zzby("setCurrentScreen must be called with an activity in the activity lifecycle");
      return;
    } 
    String str = paramString2;
    if (paramString2 == null)
      str = zzcq(paramActivity.getClass().getCanonicalName()); 
    boolean bool1 = this.zzaru.zzarq.equals(str);
    boolean bool2 = zzfx.zzv(this.zzaru.zzuw, paramString1);
    if (bool1 && bool2) {
      zzgt().zzjl().zzby("setCurrentScreen cannot be called with the same class and name");
      return;
    } 
    if (paramString1 != null && (paramString1.length() <= 0 || paramString1.length() > 100)) {
      zzgt().zzjj().zzg("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(paramString1.length()));
      return;
    } 
    if (str != null && (str.length() <= 0 || str.length() > 100)) {
      zzgt().zzjj().zzg("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
      return;
    } 
    zzau zzau = zzgt().zzjo();
    if (paramString1 == null) {
      paramString2 = "null";
    } else {
      paramString2 = paramString1;
    } 
    zzau.zze("Setting current screen to name, class", paramString2, str);
    zzdx zzdx1 = new zzdx(paramString1, str, zzgr().zzmj());
    this.zzarw.put(paramActivity, zzdx1);
    zza(paramActivity, zzdx1, true);
  }
  
  public final void zza(String paramString, zzdx paramzzdx) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield zzary : Ljava/lang/String;
    //   10: ifnull -> 28
    //   13: aload_0
    //   14: getfield zzary : Ljava/lang/String;
    //   17: aload_1
    //   18: invokevirtual equals : (Ljava/lang/Object;)Z
    //   21: ifne -> 28
    //   24: aload_2
    //   25: ifnull -> 33
    //   28: aload_0
    //   29: aload_1
    //   30: putfield zzary : Ljava/lang/String;
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   6	24	36	finally
    //   28	33	36	finally
    //   33	35	36	finally
    //   37	39	36	finally
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  public final zzdx zzle() {
    zzcl();
    zzaf();
    return this.zzart;
  }
  
  public final zzdx zzlf() {
    zzgg();
    return this.zzaru;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzdy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */