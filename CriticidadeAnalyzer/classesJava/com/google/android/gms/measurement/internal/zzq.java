package com.google.android.gms.measurement.internal;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.InvocationTargetException;

public final class zzq extends zzcr {
  private Boolean zzagw;
  
  private zzs zzagx = zzr.zzagy;
  
  private Boolean zzyk;
  
  zzq(zzbw paramzzbw) {
    super(paramzzbw);
    zzai.zza(paramzzbw);
  }
  
  static String zzhy() {
    return zzai.zzaiu.get();
  }
  
  public static long zzib() {
    return ((Long)zzai.zzajx.get()).longValue();
  }
  
  public static long zzic() {
    return ((Long)zzai.zzaix.get()).longValue();
  }
  
  public static boolean zzie() {
    return ((Boolean)zzai.zzait.get()).booleanValue();
  }
  
  static boolean zzig() {
    return ((Boolean)zzai.zzakt.get()).booleanValue();
  }
  
  public final long zza(String paramString, zzai.zza<Long> paramzza) {
    if (paramString == null)
      return ((Long)paramzza.get()).longValue(); 
    paramString = this.zzagx.zzf(paramString, paramzza.getKey());
    if (TextUtils.isEmpty(paramString))
      return ((Long)paramzza.get()).longValue(); 
    try {
      return ((Long)paramzza.get(Long.valueOf(Long.parseLong(paramString)))).longValue();
    } catch (NumberFormatException numberFormatException) {
      return ((Long)paramzza.get()).longValue();
    } 
  }
  
  final void zza(zzs paramzzs) {
    this.zzagx = paramzzs;
  }
  
  public final boolean zza(zzai.zza<Boolean> paramzza) {
    return zzd((String)null, paramzza);
  }
  
  public final int zzaq(String paramString) {
    return zzb(paramString, zzai.zzaji);
  }
  
  final Boolean zzar(String paramString) {
    Preconditions.checkNotEmpty(paramString);
    try {
      if (getContext().getPackageManager() == null) {
        zzgt().zzjg().zzby("Failed to load metadata: PackageManager is null");
        return null;
      } 
      ApplicationInfo applicationInfo = Wrappers.packageManager(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
      if (applicationInfo == null) {
        zzgt().zzjg().zzby("Failed to load metadata: ApplicationInfo is null");
        return null;
      } 
      if (applicationInfo.metaData == null) {
        zzgt().zzjg().zzby("Failed to load metadata: Metadata bundle is null");
        return null;
      } 
      if (!applicationInfo.metaData.containsKey(paramString))
        return null; 
      boolean bool = applicationInfo.metaData.getBoolean(paramString);
      return Boolean.valueOf(bool);
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      zzgt().zzjg().zzg("Failed to load metadata: Package name not found", nameNotFoundException);
      return null;
    } 
  }
  
  public final boolean zzas(String paramString) {
    return "1".equals(this.zzagx.zzf(paramString, "gaia_collection_enabled"));
  }
  
  public final boolean zzat(String paramString) {
    return "1".equals(this.zzagx.zzf(paramString, "measurement.event_sampling_enabled"));
  }
  
  final boolean zzau(String paramString) {
    return zzd(paramString, zzai.zzakh);
  }
  
  final boolean zzav(String paramString) {
    return zzd(paramString, zzai.zzakj);
  }
  
  final boolean zzaw(String paramString) {
    return zzd(paramString, zzai.zzakk);
  }
  
  final boolean zzaz(String paramString) {
    return zzd(paramString, zzai.zzakl);
  }
  
  public final int zzb(String paramString, zzai.zza<Integer> paramzza) {
    if (paramString == null)
      return ((Integer)paramzza.get()).intValue(); 
    paramString = this.zzagx.zzf(paramString, paramzza.getKey());
    if (TextUtils.isEmpty(paramString))
      return ((Integer)paramzza.get()).intValue(); 
    try {
      return ((Integer)paramzza.get(Integer.valueOf(Integer.parseInt(paramString)))).intValue();
    } catch (NumberFormatException numberFormatException) {
      return ((Integer)paramzza.get()).intValue();
    } 
  }
  
  final boolean zzba(String paramString) {
    return zzd(paramString, zzai.zzakm);
  }
  
  final boolean zzbb(String paramString) {
    return zzd(paramString, zzai.zzako);
  }
  
  final boolean zzbc(String paramString) {
    return zzd(paramString, zzai.zzakp);
  }
  
  final boolean zzbd(String paramString) {
    return zzd(paramString, zzai.zzakq);
  }
  
  final boolean zzbe(String paramString) {
    return zzd(paramString, zzai.zzaks);
  }
  
  final boolean zzbf(String paramString) {
    return zzd(paramString, zzai.zzakr);
  }
  
  final boolean zzbg(String paramString) {
    return zzd(paramString, zzai.zzaku);
  }
  
  final boolean zzbh(String paramString) {
    return zzd(paramString, zzai.zzakv);
  }
  
  final boolean zzbi(String paramString) {
    return zzd(paramString, zzai.zzakw);
  }
  
  final boolean zzbj(String paramString) {
    return zzd(paramString, zzai.zzakx);
  }
  
  final boolean zzbk(String paramString) {
    return zzd(paramString, zzai.zzalb);
  }
  
  public final boolean zzd(String paramString, zzai.zza<Boolean> paramzza) {
    if (paramString == null)
      return ((Boolean)paramzza.get()).booleanValue(); 
    paramString = this.zzagx.zzf(paramString, paramzza.getKey());
    return TextUtils.isEmpty(paramString) ? ((Boolean)paramzza.get()).booleanValue() : ((Boolean)paramzza.get(Boolean.valueOf(Boolean.parseBoolean(paramString)))).booleanValue();
  }
  
  public final boolean zzdw() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzyk : Ljava/lang/Boolean;
    //   4: ifnonnull -> 101
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: getfield zzyk : Ljava/lang/Boolean;
    //   13: ifnonnull -> 91
    //   16: aload_0
    //   17: invokevirtual getContext : ()Landroid/content/Context;
    //   20: invokevirtual getApplicationInfo : ()Landroid/content/pm/ApplicationInfo;
    //   23: astore_3
    //   24: invokestatic getMyProcessName : ()Ljava/lang/String;
    //   27: astore_2
    //   28: aload_3
    //   29: ifnull -> 64
    //   32: aload_3
    //   33: getfield processName : Ljava/lang/String;
    //   36: astore_3
    //   37: aload_3
    //   38: ifnull -> 54
    //   41: aload_3
    //   42: aload_2
    //   43: invokevirtual equals : (Ljava/lang/Object;)Z
    //   46: ifeq -> 54
    //   49: iconst_1
    //   50: istore_1
    //   51: goto -> 56
    //   54: iconst_0
    //   55: istore_1
    //   56: aload_0
    //   57: iload_1
    //   58: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   61: putfield zzyk : Ljava/lang/Boolean;
    //   64: aload_0
    //   65: getfield zzyk : Ljava/lang/Boolean;
    //   68: ifnonnull -> 91
    //   71: aload_0
    //   72: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   75: putfield zzyk : Ljava/lang/Boolean;
    //   78: aload_0
    //   79: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   82: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   85: ldc_w 'My process not in the list of running processes'
    //   88: invokevirtual zzby : (Ljava/lang/String;)V
    //   91: aload_0
    //   92: monitorexit
    //   93: goto -> 101
    //   96: astore_2
    //   97: aload_0
    //   98: monitorexit
    //   99: aload_2
    //   100: athrow
    //   101: aload_0
    //   102: getfield zzyk : Ljava/lang/Boolean;
    //   105: invokevirtual booleanValue : ()Z
    //   108: ireturn
    // Exception table:
    //   from	to	target	type
    //   9	28	96	finally
    //   32	37	96	finally
    //   41	49	96	finally
    //   56	64	96	finally
    //   64	91	96	finally
    //   91	93	96	finally
    //   97	99	96	finally
  }
  
  public final boolean zze(String paramString, zzai.zza<Boolean> paramzza) {
    return zzd(paramString, paramzza);
  }
  
  public final long zzhh() {
    zzgw();
    return 14711L;
  }
  
  public final boolean zzhz() {
    zzgw();
    Boolean bool = zzar("firebase_analytics_collection_deactivated");
    return (bool != null && bool.booleanValue());
  }
  
  public final Boolean zzia() {
    zzgw();
    return zzar("firebase_analytics_collection_enabled");
  }
  
  public final String zzid() {
    try {
      return (String)Class.forName("android.os.SystemProperties").getMethod("get", new Class[] { String.class, String.class }).invoke(null, new Object[] { "debug.firebase.analytics.app", "" });
    } catch (ClassNotFoundException classNotFoundException) {
      zzgt().zzjg().zzg("Could not find SystemProperties class", classNotFoundException);
    } catch (NoSuchMethodException noSuchMethodException) {
      zzgt().zzjg().zzg("Could not find SystemProperties.get() method", noSuchMethodException);
    } catch (IllegalAccessException illegalAccessException) {
      zzgt().zzjg().zzg("Could not access SystemProperties.get()", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      zzgt().zzjg().zzg("SystemProperties.get() threw an exception", invocationTargetException);
    } 
    return "";
  }
  
  final boolean zzif() {
    if (this.zzagw == null) {
      this.zzagw = zzar("app_measurement_lite");
      if (this.zzagw == null)
        this.zzagw = Boolean.valueOf(false); 
    } 
    return (this.zzagw.booleanValue() || !this.zzada.zzkr());
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */