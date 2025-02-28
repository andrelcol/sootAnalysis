package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzsi;
import com.google.android.gms.measurement.AppMeasurement;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class zzbw implements zzct {
  private static volatile zzbw zzapj;
  
  private final boolean zzadg;
  
  private final String zzadi;
  
  private final long zzago;
  
  private final zzn zzaih;
  
  private final String zzapk;
  
  private final String zzapl;
  
  private final zzq zzapm;
  
  private final zzbd zzapn;
  
  private final zzas zzapo;
  
  private final zzbr zzapp;
  
  private final zzfd zzapq;
  
  private final AppMeasurement zzapr;
  
  private final zzfx zzaps;
  
  private final zzaq zzapt;
  
  private final zzdy zzapu;
  
  private final zzda zzapv;
  
  private final zza zzapw;
  
  private zzao zzapx;
  
  private zzeb zzapy;
  
  private zzaa zzapz;
  
  private zzam zzaqa;
  
  private zzbj zzaqb;
  
  private Boolean zzaqc;
  
  private long zzaqd;
  
  private volatile Boolean zzaqe;
  
  private Boolean zzaqf;
  
  private Boolean zzaqg;
  
  private int zzaqh;
  
  private AtomicInteger zzaqi = new AtomicInteger(0);
  
  private final Context zzri;
  
  private final Clock zzrz;
  
  private boolean zzvz = false;
  
  private zzbw(zzcz paramzzcz) {
    Preconditions.checkNotNull(paramzzcz);
    this.zzaih = new zzn(paramzzcz.zzri);
    zzai.zza(this.zzaih);
    this.zzri = paramzzcz.zzri;
    this.zzadi = paramzzcz.zzadi;
    this.zzapk = paramzzcz.zzapk;
    this.zzapl = paramzzcz.zzapl;
    this.zzadg = paramzzcz.zzadg;
    this.zzaqe = paramzzcz.zzaqe;
    zzan zzan = paramzzcz.zzaqz;
    if (zzan != null) {
      Bundle bundle = zzan.zzadj;
      if (bundle != null) {
        Object object2 = bundle.get("measurementEnabled");
        if (object2 instanceof Boolean)
          this.zzaqf = (Boolean)object2; 
        Object object1 = zzan.zzadj.get("measurementDeactivated");
        if (object1 instanceof Boolean)
          this.zzaqg = (Boolean)object1; 
      } 
    } 
    zzsi.zzae(this.zzri);
    this.zzrz = DefaultClock.getInstance();
    this.zzago = this.zzrz.currentTimeMillis();
    this.zzapm = new zzq(this);
    zzbd zzbd1 = new zzbd(this);
    zzbd1.zzq();
    this.zzapn = zzbd1;
    zzas zzas1 = new zzas(this);
    zzas1.zzq();
    this.zzapo = zzas1;
    zzfx zzfx1 = new zzfx(this);
    zzfx1.zzq();
    this.zzaps = zzfx1;
    zzaq zzaq1 = new zzaq(this);
    zzaq1.zzq();
    this.zzapt = zzaq1;
    this.zzapw = new zza(this);
    zzdy zzdy1 = new zzdy(this);
    zzdy1.zzq();
    this.zzapu = zzdy1;
    zzda zzda1 = new zzda(this);
    zzda1.zzq();
    this.zzapv = zzda1;
    this.zzapr = new AppMeasurement(this);
    zzfd zzfd1 = new zzfd(this);
    zzfd1.zzq();
    this.zzapq = zzfd1;
    zzbr zzbr1 = new zzbr(this);
    zzbr1.zzq();
    this.zzapp = zzbr1;
    if (this.zzri.getApplicationContext() instanceof Application) {
      zzda zzda2 = zzgj();
      if (zzda2.getContext().getApplicationContext() instanceof Application) {
        Application application = (Application)zzda2.getContext().getApplicationContext();
        if (zzda2.zzara == null)
          zzda2.zzara = new zzdu(zzda2, null); 
        application.unregisterActivityLifecycleCallbacks(zzda2.zzara);
        application.registerActivityLifecycleCallbacks(zzda2.zzara);
        zzda2.zzgt().zzjo().zzby("Registered activity lifecycle callback");
      } 
    } else {
      zzgt().zzjj().zzby("Application context is not an Application");
    } 
    this.zzapp.zzc(new zzbx(this, paramzzcz));
  }
  
  public static zzbw zza(Context paramContext, zzan paramzzan) {
    // Byte code:
    //   0: aload_1
    //   1: astore_2
    //   2: aload_1
    //   3: ifnull -> 52
    //   6: aload_1
    //   7: getfield origin : Ljava/lang/String;
    //   10: ifnull -> 22
    //   13: aload_1
    //   14: astore_2
    //   15: aload_1
    //   16: getfield zzadi : Ljava/lang/String;
    //   19: ifnonnull -> 52
    //   22: new com/google/android/gms/measurement/internal/zzan
    //   25: dup
    //   26: aload_1
    //   27: getfield zzade : J
    //   30: aload_1
    //   31: getfield zzadf : J
    //   34: aload_1
    //   35: getfield zzadg : Z
    //   38: aload_1
    //   39: getfield zzadh : Ljava/lang/String;
    //   42: aconst_null
    //   43: aconst_null
    //   44: aload_1
    //   45: getfield zzadj : Landroid/os/Bundle;
    //   48: invokespecial <init> : (JJZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V
    //   51: astore_2
    //   52: aload_0
    //   53: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: aload_0
    //   58: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   61: invokestatic checkNotNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   64: pop
    //   65: getstatic com/google/android/gms/measurement/internal/zzbw.zzapj : Lcom/google/android/gms/measurement/internal/zzbw;
    //   68: ifnonnull -> 115
    //   71: ldc com/google/android/gms/measurement/internal/zzbw
    //   73: monitorenter
    //   74: getstatic com/google/android/gms/measurement/internal/zzbw.zzapj : Lcom/google/android/gms/measurement/internal/zzbw;
    //   77: ifnonnull -> 103
    //   80: new com/google/android/gms/measurement/internal/zzcz
    //   83: astore_1
    //   84: aload_1
    //   85: aload_0
    //   86: aload_2
    //   87: invokespecial <init> : (Landroid/content/Context;Lcom/google/android/gms/measurement/internal/zzan;)V
    //   90: new com/google/android/gms/measurement/internal/zzbw
    //   93: astore_0
    //   94: aload_0
    //   95: aload_1
    //   96: invokespecial <init> : (Lcom/google/android/gms/measurement/internal/zzcz;)V
    //   99: aload_0
    //   100: putstatic com/google/android/gms/measurement/internal/zzbw.zzapj : Lcom/google/android/gms/measurement/internal/zzbw;
    //   103: ldc com/google/android/gms/measurement/internal/zzbw
    //   105: monitorexit
    //   106: goto -> 154
    //   109: astore_0
    //   110: ldc com/google/android/gms/measurement/internal/zzbw
    //   112: monitorexit
    //   113: aload_0
    //   114: athrow
    //   115: aload_2
    //   116: ifnull -> 154
    //   119: aload_2
    //   120: getfield zzadj : Landroid/os/Bundle;
    //   123: astore_0
    //   124: aload_0
    //   125: ifnull -> 154
    //   128: aload_0
    //   129: ldc_w 'dataCollectionDefaultEnabled'
    //   132: invokevirtual containsKey : (Ljava/lang/String;)Z
    //   135: ifeq -> 154
    //   138: getstatic com/google/android/gms/measurement/internal/zzbw.zzapj : Lcom/google/android/gms/measurement/internal/zzbw;
    //   141: aload_2
    //   142: getfield zzadj : Landroid/os/Bundle;
    //   145: ldc_w 'dataCollectionDefaultEnabled'
    //   148: invokevirtual getBoolean : (Ljava/lang/String;)Z
    //   151: invokevirtual zzd : (Z)V
    //   154: getstatic com/google/android/gms/measurement/internal/zzbw.zzapj : Lcom/google/android/gms/measurement/internal/zzbw;
    //   157: areturn
    // Exception table:
    //   from	to	target	type
    //   74	103	109	finally
    //   103	106	109	finally
    //   110	113	109	finally
  }
  
  private static void zza(zzcr paramzzcr) {
    if (paramzzcr != null)
      return; 
    throw new IllegalStateException("Component not created");
  }
  
  private static void zza(zzcs paramzzcs) {
    if (paramzzcs != null) {
      if (paramzzcs.isInitialized())
        return; 
      String str = String.valueOf(paramzzcs.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 27);
      stringBuilder.append("Component not initialized: ");
      stringBuilder.append(str);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Component not created");
  }
  
  private final void zza(zzcz paramzzcz) {
    zzgs().zzaf();
    zzq.zzhy();
    zzaa zzaa1 = new zzaa(this);
    zzaa1.zzq();
    this.zzapz = zzaa1;
    zzam zzam1 = new zzam(this);
    zzam1.zzq();
    this.zzaqa = zzam1;
    zzao zzao1 = new zzao(this);
    zzao1.zzq();
    this.zzapx = zzao1;
    zzeb zzeb1 = new zzeb(this);
    zzeb1.zzq();
    this.zzapy = zzeb1;
    this.zzaps.zzgx();
    this.zzapn.zzgx();
    this.zzaqb = new zzbj(this);
    this.zzaqa.zzgx();
    zzgt().zzjm().zzg("App measurement is starting up, version", Long.valueOf(this.zzapm.zzhh()));
    zzgt().zzjm().zzby("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
    String str = zzam1.zzal();
    if (TextUtils.isEmpty(this.zzadi)) {
      zzau zzau;
      if (zzgr().zzcz(str)) {
        zzau = zzgt().zzjm();
        str = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
      } else {
        zzau = zzgt().zzjm();
        str = String.valueOf(str);
        if (str.length() != 0) {
          str = "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(str);
        } else {
          str = new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
        } 
      } 
      zzau.zzby(str);
    } 
    zzgt().zzjn().zzby("Debug-level message logging enabled");
    if (this.zzaqh != this.zzaqi.get())
      zzgt().zzjg().zze("Not all components initialized", Integer.valueOf(this.zzaqh), Integer.valueOf(this.zzaqi.get())); 
    this.zzvz = true;
  }
  
  private static void zza(zzf paramzzf) {
    if (paramzzf != null) {
      if (paramzzf.isInitialized())
        return; 
      String str = String.valueOf(paramzzf.getClass());
      StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 27);
      stringBuilder.append("Component not initialized: ");
      stringBuilder.append(str);
      throw new IllegalStateException(stringBuilder.toString());
    } 
    throw new IllegalStateException("Component not created");
  }
  
  private final void zzcl() {
    if (this.zzvz)
      return; 
    throw new IllegalStateException("AppMeasurement is not initialized");
  }
  
  public final Context getContext() {
    return this.zzri;
  }
  
  public final boolean isEnabled() {
    boolean bool;
    zzgs().zzaf();
    zzcl();
    if (this.zzapm.zza(zzai.zzale)) {
      if (this.zzapm.zzhz())
        return false; 
      Boolean bool2 = this.zzaqg;
      if (bool2 != null && bool2.booleanValue())
        return false; 
      bool2 = zzgu().zzjz();
      if (bool2 != null)
        return bool2.booleanValue(); 
      bool2 = this.zzapm.zzia();
      if (bool2 != null)
        return bool2.booleanValue(); 
      bool2 = this.zzaqf;
      return (bool2 != null) ? bool2.booleanValue() : (GoogleServices.isMeasurementExplicitlyDisabled() ? false : ((this.zzapm.zza(zzai.zzala) && this.zzaqe != null) ? this.zzaqe.booleanValue() : true));
    } 
    if (this.zzapm.zzhz())
      return false; 
    Boolean bool1 = this.zzapm.zzia();
    if (bool1 != null) {
      bool = bool1.booleanValue();
    } else {
      int j = GoogleServices.isMeasurementExplicitlyDisabled() ^ true;
      int i = j;
      if (j != 0) {
        i = j;
        if (this.zzaqe != null) {
          i = j;
          if (((Boolean)zzai.zzala.get()).booleanValue())
            bool = this.zzaqe.booleanValue(); 
        } 
      } 
    } 
    return zzgu().zzh(bool);
  }
  
  protected final void start() {
    zzgs().zzaf();
    if ((zzgu()).zzanc.get() == 0L)
      (zzgu()).zzanc.set(this.zzrz.currentTimeMillis()); 
    if (Long.valueOf((zzgu()).zzanh.get()).longValue() == 0L) {
      zzgt().zzjo().zzg("Persisting first open", Long.valueOf(this.zzago));
      (zzgu()).zzanh.set(this.zzago);
    } 
    if (!zzkv()) {
      if (isEnabled()) {
        if (!zzgr().zzx("android.permission.INTERNET"))
          zzgt().zzjg().zzby("App is missing INTERNET permission"); 
        if (!zzgr().zzx("android.permission.ACCESS_NETWORK_STATE"))
          zzgt().zzjg().zzby("App is missing ACCESS_NETWORK_STATE permission"); 
        if (!Wrappers.packageManager(this.zzri).isCallerInstantApp() && !this.zzapm.zzif()) {
          if (!zzbm.zza(this.zzri))
            zzgt().zzjg().zzby("AppMeasurementReceiver not registered/enabled"); 
          if (!zzfx.zza(this.zzri, false))
            zzgt().zzjg().zzby("AppMeasurementService not registered/enabled"); 
        } 
        zzgt().zzjg().zzby("Uploading is not possible. App measurement disabled");
        return;
      } 
    } else {
      if (!TextUtils.isEmpty(zzgk().getGmpAppId()) || !TextUtils.isEmpty(zzgk().zzhb())) {
        zzgr();
        if (zzfx.zza(zzgk().getGmpAppId(), zzgu().zzjv(), zzgk().zzhb(), zzgu().zzjw())) {
          zzgt().zzjm().zzby("Rechecking which service to use due to a GMP App Id change");
          zzgu().zzjy();
          zzgn().resetAnalyticsData();
          this.zzapy.disconnect();
          this.zzapy.zzdj();
          (zzgu()).zzanh.set(this.zzago);
          (zzgu()).zzanj.zzcd(null);
        } 
        zzgu().zzcb(zzgk().getGmpAppId());
        zzgu().zzcc(zzgk().zzhb());
        if (this.zzapm.zzbi(zzgk().zzal()))
          this.zzapq.zzaj(this.zzago); 
      } 
      zzgj().zzcp((zzgu()).zzanj.zzkd());
      if (!TextUtils.isEmpty(zzgk().getGmpAppId()) || !TextUtils.isEmpty(zzgk().zzhb())) {
        boolean bool = isEnabled();
        if (!zzgu().zzkc() && !this.zzapm.zzhz())
          zzgu().zzi(bool ^ true); 
        if (!this.zzapm.zzba(zzgk().zzal()) || bool)
          zzgj().zzld(); 
        zzgl().zza(new AtomicReference<String>());
      } 
    } 
  }
  
  final void zzb(zzcs paramzzcs) {
    this.zzaqh++;
  }
  
  final void zzb(zzf paramzzf) {
    this.zzaqh++;
  }
  
  public final Clock zzbx() {
    return this.zzrz;
  }
  
  final void zzd(boolean paramBoolean) {
    this.zzaqe = Boolean.valueOf(paramBoolean);
  }
  
  final void zzgf() {
    throw new IllegalStateException("Unexpected call on client side");
  }
  
  final void zzgg() {}
  
  public final zza zzgi() {
    zza zza1 = this.zzapw;
    if (zza1 != null)
      return zza1; 
    throw new IllegalStateException("Component not created");
  }
  
  public final zzda zzgj() {
    zza(this.zzapv);
    return this.zzapv;
  }
  
  public final zzam zzgk() {
    zza(this.zzaqa);
    return this.zzaqa;
  }
  
  public final zzeb zzgl() {
    zza(this.zzapy);
    return this.zzapy;
  }
  
  public final zzdy zzgm() {
    zza(this.zzapu);
    return this.zzapu;
  }
  
  public final zzao zzgn() {
    zza(this.zzapx);
    return this.zzapx;
  }
  
  public final zzfd zzgo() {
    zza(this.zzapq);
    return this.zzapq;
  }
  
  public final zzaa zzgp() {
    zza(this.zzapz);
    return this.zzapz;
  }
  
  public final zzaq zzgq() {
    zza((zzcr)this.zzapt);
    return this.zzapt;
  }
  
  public final zzfx zzgr() {
    zza((zzcr)this.zzaps);
    return this.zzaps;
  }
  
  public final zzbr zzgs() {
    zza(this.zzapp);
    return this.zzapp;
  }
  
  public final zzas zzgt() {
    zza(this.zzapo);
    return this.zzapo;
  }
  
  public final zzbd zzgu() {
    zza((zzcr)this.zzapn);
    return this.zzapn;
  }
  
  public final zzq zzgv() {
    return this.zzapm;
  }
  
  public final zzn zzgw() {
    return this.zzaih;
  }
  
  public final zzas zzkj() {
    zzas zzas1 = this.zzapo;
    return (zzas1 != null && zzas1.isInitialized()) ? this.zzapo : null;
  }
  
  public final zzbj zzkk() {
    return this.zzaqb;
  }
  
  final zzbr zzkl() {
    return this.zzapp;
  }
  
  public final AppMeasurement zzkm() {
    return this.zzapr;
  }
  
  public final boolean zzkn() {
    return TextUtils.isEmpty(this.zzadi);
  }
  
  public final String zzko() {
    return this.zzadi;
  }
  
  public final String zzkp() {
    return this.zzapk;
  }
  
  public final String zzkq() {
    return this.zzapl;
  }
  
  public final boolean zzkr() {
    return this.zzadg;
  }
  
  public final boolean zzks() {
    return (this.zzaqe != null && this.zzaqe.booleanValue());
  }
  
  final long zzkt() {
    Long long_ = Long.valueOf((zzgu()).zzanh.get());
    return (long_.longValue() == 0L) ? this.zzago : Math.min(this.zzago, long_.longValue());
  }
  
  final void zzku() {
    this.zzaqi.incrementAndGet();
  }
  
  protected final boolean zzkv() {
    zzcl();
    zzgs().zzaf();
    Boolean bool = this.zzaqc;
    if (bool == null || this.zzaqd == 0L || (bool != null && !bool.booleanValue() && Math.abs(this.zzrz.elapsedRealtime() - this.zzaqd) > 1000L)) {
      this.zzaqd = this.zzrz.elapsedRealtime();
      boolean bool1 = zzgr().zzx("android.permission.INTERNET");
      boolean bool2 = true;
      if (bool1 && zzgr().zzx("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzri).isCallerInstantApp() || this.zzapm.zzif() || (zzbm.zza(this.zzri) && zzfx.zza(this.zzri, false)))) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      this.zzaqc = Boolean.valueOf(bool1);
      if (this.zzaqc.booleanValue()) {
        bool1 = bool2;
        if (!zzgr().zzu(zzgk().getGmpAppId(), zzgk().zzhb()))
          if (!TextUtils.isEmpty(zzgk().zzhb())) {
            bool1 = bool2;
          } else {
            bool1 = false;
          }  
        this.zzaqc = Boolean.valueOf(bool1);
      } 
    } 
    return this.zzaqc.booleanValue();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */