package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

final class zzbd extends zzcs {
  static final Pair<String, Long> zzana = new Pair("", Long.valueOf(0L));
  
  private SharedPreferences zzabr;
  
  public zzbh zzanb;
  
  public final zzbg zzanc = new zzbg(this, "last_upload", 0L);
  
  public final zzbg zzand = new zzbg(this, "last_upload_attempt", 0L);
  
  public final zzbg zzane = new zzbg(this, "backoff", 0L);
  
  public final zzbg zzanf = new zzbg(this, "last_delete_stale", 0L);
  
  public final zzbg zzang = new zzbg(this, "midnight_offset", 0L);
  
  public final zzbg zzanh = new zzbg(this, "first_open_time", 0L);
  
  public final zzbg zzani = new zzbg(this, "app_install_time", 0L);
  
  public final zzbi zzanj = new zzbi(this, "app_instance_id", null);
  
  private String zzank;
  
  private boolean zzanl;
  
  private long zzanm;
  
  public final zzbg zzann = new zzbg(this, "time_before_start", 10000L);
  
  public final zzbg zzano = new zzbg(this, "session_timeout", 1800000L);
  
  public final zzbf zzanp = new zzbf(this, "start_new_session", true);
  
  public final zzbg zzanq = new zzbg(this, "last_pause_time", 0L);
  
  public final zzbg zzanr = new zzbg(this, "time_active", 0L);
  
  public boolean zzans;
  
  public zzbf zzant = new zzbf(this, "app_backgrounded", false);
  
  zzbd(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final SharedPreferences zzju() {
    zzaf();
    zzcl();
    return this.zzabr;
  }
  
  final void setMeasurementEnabled(boolean paramBoolean) {
    zzaf();
    zzgt().zzjo().zzg("Setting measurementEnabled", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor editor = zzju().edit();
    editor.putBoolean("measurement_enabled", paramBoolean);
    editor.apply();
  }
  
  final boolean zzaf(long paramLong) {
    return (paramLong - this.zzano.get() > this.zzanq.get());
  }
  
  final Pair<String, Boolean> zzbz(String paramString) {
    zzaf();
    long l = zzbx().elapsedRealtime();
    String str = this.zzank;
    if (str != null && l < this.zzanm)
      return new Pair(str, Boolean.valueOf(this.zzanl)); 
    this.zzanm = l + zzgv().zza(paramString, zzai.zzaiv);
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
    try {
      AdvertisingIdClient.Info info = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
      if (info != null) {
        this.zzank = info.getId();
        this.zzanl = info.isLimitAdTrackingEnabled();
      } 
      if (this.zzank == null)
        this.zzank = ""; 
    } catch (Exception exception) {
      zzgt().zzjn().zzg("Unable to get advertising id", exception);
      this.zzank = "";
    } 
    AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
    return new Pair(this.zzank, Boolean.valueOf(this.zzanl));
  }
  
  final String zzca(String paramString) {
    zzaf();
    paramString = (String)(zzbz(paramString)).first;
    MessageDigest messageDigest = zzfx.getMessageDigest();
    return (messageDigest == null) ? null : String.format(Locale.US, "%032X", new Object[] { new BigInteger(1, messageDigest.digest(paramString.getBytes())) });
  }
  
  final void zzcb(String paramString) {
    zzaf();
    SharedPreferences.Editor editor = zzju().edit();
    editor.putString("gmp_app_id", paramString);
    editor.apply();
  }
  
  final void zzcc(String paramString) {
    zzaf();
    SharedPreferences.Editor editor = zzju().edit();
    editor.putString("admob_app_id", paramString);
    editor.apply();
  }
  
  final void zzg(boolean paramBoolean) {
    zzaf();
    zzgt().zzjo().zzg("Setting useService", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor editor = zzju().edit();
    editor.putBoolean("use_service", paramBoolean);
    editor.apply();
  }
  
  protected final boolean zzgy() {
    return true;
  }
  
  protected final void zzgz() {
    this.zzabr = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
    this.zzans = this.zzabr.getBoolean("has_been_opened", false);
    if (!this.zzans) {
      SharedPreferences.Editor editor = this.zzabr.edit();
      editor.putBoolean("has_been_opened", true);
      editor.apply();
    } 
    this.zzanb = new zzbh(this, "health_monitor", Math.max(0L, ((Long)zzai.zzaiw.get()).longValue()), null);
  }
  
  final boolean zzh(boolean paramBoolean) {
    zzaf();
    return zzju().getBoolean("measurement_enabled", paramBoolean);
  }
  
  final void zzi(boolean paramBoolean) {
    zzaf();
    zzgt().zzjo().zzg("Updating deferred analytics collection", Boolean.valueOf(paramBoolean));
    SharedPreferences.Editor editor = zzju().edit();
    editor.putBoolean("deferred_analytics_collection", paramBoolean);
    editor.apply();
  }
  
  final String zzjv() {
    zzaf();
    return zzju().getString("gmp_app_id", null);
  }
  
  final String zzjw() {
    zzaf();
    return zzju().getString("admob_app_id", null);
  }
  
  final Boolean zzjx() {
    zzaf();
    return !zzju().contains("use_service") ? null : Boolean.valueOf(zzju().getBoolean("use_service", false));
  }
  
  final void zzjy() {
    zzaf();
    zzgt().zzjo().zzby("Clearing collection preferences.");
    if (zzgv().zza(zzai.zzale)) {
      Boolean bool = zzjz();
      SharedPreferences.Editor editor1 = zzju().edit();
      editor1.clear();
      editor1.apply();
      if (bool != null)
        setMeasurementEnabled(bool.booleanValue()); 
      return;
    } 
    boolean bool2 = zzju().contains("measurement_enabled");
    boolean bool1 = true;
    if (bool2)
      bool1 = zzh(true); 
    SharedPreferences.Editor editor = zzju().edit();
    editor.clear();
    editor.apply();
    if (bool2)
      setMeasurementEnabled(bool1); 
  }
  
  final Boolean zzjz() {
    zzaf();
    return zzju().contains("measurement_enabled") ? Boolean.valueOf(zzju().getBoolean("measurement_enabled", true)) : null;
  }
  
  protected final String zzka() {
    zzaf();
    String str2 = zzju().getString("previous_os_version", null);
    zzgp().zzcl();
    String str1 = Build.VERSION.RELEASE;
    if (!TextUtils.isEmpty(str1) && !str1.equals(str2)) {
      SharedPreferences.Editor editor = zzju().edit();
      editor.putString("previous_os_version", str1);
      editor.apply();
    } 
    return str2;
  }
  
  final boolean zzkb() {
    zzaf();
    return zzju().getBoolean("deferred_analytics_collection", false);
  }
  
  final boolean zzkc() {
    return this.zzabr.contains("deferred_analytics_collection");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */