package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.measurement.zzrx;
import com.google.android.gms.internal.measurement.zzsh;
import com.google.android.gms.internal.measurement.zzsi;
import com.google.android.gms.internal.measurement.zzso;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class zzai {
  private static volatile zzbw zzada;
  
  static zzn zzaih;
  
  static List<zza<Integer>> zzaii = new ArrayList<zza<Integer>>();
  
  static List<zza<Long>> zzaij = new ArrayList<zza<Long>>();
  
  static List<zza<Boolean>> zzaik = new ArrayList<zza<Boolean>>();
  
  static List<zza<String>> zzail = new ArrayList<zza<String>>();
  
  static List<zza<Double>> zzaim = new ArrayList<zza<Double>>();
  
  private static final zzso zzain = new zzso(zzsh.zzfq("com.google.android.gms.measurement"));
  
  private static Boolean zzaio;
  
  public static zza<Boolean> zzait = zza.zzb("measurement.upload_dsid_enabled", false, false);
  
  public static zza<String> zzaiu = zza.zzd("measurement.log_tag", "FA", "FA-SVC");
  
  public static zza<Long> zzaiv = zza.zzb("measurement.ad_id_cache_time", 10000L, 10000L);
  
  public static zza<Long> zzaiw = zza.zzb("measurement.monitoring.sample_period_millis", 86400000L, 86400000L);
  
  public static zza<Long> zzaix = zza.zzb("measurement.config.cache_time", 86400000L, 3600000L);
  
  public static zza<String> zzaiy = zza.zzd("measurement.config.url_scheme", "https", "https");
  
  public static zza<String> zzaiz = zza.zzd("measurement.config.url_authority", "app-measurement.com", "app-measurement.com");
  
  public static zza<Integer> zzaja = zza.zzc("measurement.upload.max_bundles", 100, 100);
  
  public static zza<Integer> zzajb = zza.zzc("measurement.upload.max_batch_size", 65536, 65536);
  
  public static zza<Integer> zzajc = zza.zzc("measurement.upload.max_bundle_size", 65536, 65536);
  
  public static zza<Integer> zzajd = zza.zzc("measurement.upload.max_events_per_bundle", 1000, 1000);
  
  public static zza<Integer> zzaje = zza.zzc("measurement.upload.max_events_per_day", 100000, 100000);
  
  public static zza<Integer> zzajf = zza.zzc("measurement.upload.max_error_events_per_day", 1000, 1000);
  
  public static zza<Integer> zzajg = zza.zzc("measurement.upload.max_public_events_per_day", 50000, 50000);
  
  public static zza<Integer> zzajh = zza.zzc("measurement.upload.max_conversions_per_day", 500, 500);
  
  public static zza<Integer> zzaji = zza.zzc("measurement.upload.max_realtime_events_per_day", 10, 10);
  
  public static zza<Integer> zzajj = zza.zzc("measurement.store.max_stored_events_per_app", 100000, 100000);
  
  public static zza<String> zzajk = zza.zzd("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a");
  
  public static zza<Long> zzajl = zza.zzb("measurement.upload.backoff_period", 43200000L, 43200000L);
  
  public static zza<Long> zzajm = zza.zzb("measurement.upload.window_interval", 3600000L, 3600000L);
  
  public static zza<Long> zzajn = zza.zzb("measurement.upload.interval", 3600000L, 3600000L);
  
  public static zza<Long> zzajo = zza.zzb("measurement.upload.realtime_upload_interval", 10000L, 10000L);
  
  public static zza<Long> zzajp = zza.zzb("measurement.upload.debug_upload_interval", 1000L, 1000L);
  
  public static zza<Long> zzajq = zza.zzb("measurement.upload.minimum_delay", 500L, 500L);
  
  public static zza<Long> zzajr = zza.zzb("measurement.alarm_manager.minimum_interval", 60000L, 60000L);
  
  public static zza<Long> zzajs = zza.zzb("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L);
  
  public static zza<Long> zzajt = zza.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L);
  
  public static zza<Long> zzaju = zza.zzb("measurement.upload.initial_upload_delay_time", 15000L, 15000L);
  
  public static zza<Long> zzajv = zza.zzb("measurement.upload.retry_time", 1800000L, 1800000L);
  
  public static zza<Integer> zzajw = zza.zzc("measurement.upload.retry_count", 6, 6);
  
  public static zza<Long> zzajx = zza.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
  
  public static zza<Integer> zzajy = zza.zzc("measurement.lifetimevalue.max_currency_tracked", 4, 4);
  
  public static zza<Integer> zzajz = zza.zzc("measurement.audience.filter_result_max_count", 200, 200);
  
  public static zza<Long> zzaka = zza.zzb("measurement.service_client.idle_disconnect_millis", 5000L, 5000L);
  
  public static zza<Integer> zzakg = zza.zzc("measurement.experiment.max_ids", 50, 50);
  
  public static zza<Boolean> zzakh = zza.zzb("measurement.lifetimevalue.user_engagement_tracking_enabled", true, true);
  
  public static zza<Boolean> zzaki = zza.zzb("measurement.audience.complex_param_evaluation", true, true);
  
  public static zza<Boolean> zzakj = zza.zzb("measurement.validation.internal_limits_internal_event_params", false, false);
  
  public static zza<Boolean> zzakk = zza.zzb("measurement.quality.unsuccessful_update_retry_counter", true, true);
  
  public static zza<Boolean> zzakl = zza.zzb("measurement.iid.disable_on_collection_disabled", true, true);
  
  public static zza<Boolean> zzakm = zza.zzb("measurement.app_launch.call_only_when_enabled", true, true);
  
  public static zza<Boolean> zzakn = zza.zzb("measurement.run_on_worker_inline", true, false);
  
  public static zza<Boolean> zzako = zza.zzb("measurement.audience.dynamic_filters", true, true);
  
  public static zza<Boolean> zzakp = zza.zzb("measurement.reset_analytics.persist_time", false, false);
  
  public static zza<Boolean> zzakq = zza.zzb("measurement.validation.value_and_currency_params", false, false);
  
  public static zza<Boolean> zzakr = zza.zzb("measurement.sampling.time_zone_offset_enabled", false, false);
  
  public static zza<Boolean> zzaks = zza.zzb("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", false, false);
  
  public static zza<Boolean> zzakt = zza.zzb("measurement.fetch_config_with_admob_app_id", true, true);
  
  public static zza<Boolean> zzaku = zza.zzb("measurement.client.sessions.session_id_enabled", false, false);
  
  public static zza<Boolean> zzakv = zza.zzb("measurement.service.sessions.session_number_enabled", false, false);
  
  public static zza<Boolean> zzakw = zza.zzb("measurement.client.sessions.immediate_start_enabled", false, false);
  
  public static zza<Boolean> zzakx = zza.zzb("measurement.client.sessions.background_sessions_enabled", false, false);
  
  public static zza<Boolean> zzaky = zza.zzb("measurement.client.sessions.remove_expired_session_properties_enabled", false, false);
  
  public static zza<Boolean> zzala = zza.zzb("measurement.collection.firebase_global_collection_flag_enabled", true, true);
  
  public static zza<Boolean> zzalb = zza.zzb("measurement.collection.efficient_engagement_reporting_enabled", false, false);
  
  public static zza<Boolean> zzalc = zza.zzb("measurement.collection.redundant_engagement_removal_enabled", false, false);
  
  public static zza<Boolean> zzale = zza.zzb("measurement.collection.init_params_control_enabled", true, true);
  
  public static zza<Boolean> zzalf = zza.zzb("measurement.upload.disable_is_uploader", false, false);
  
  public static zza<Boolean> zzalg = zza.zzb("measurement.experiment.enable_experiment_reporting", false, false);
  
  public static zza<Boolean> zzalh = zza.zzb("measurement.collection.log_event_and_bundle_v2", true, true);
  
  public static zza<Boolean> zzali = zza.zzb("measurement.collection.null_empty_event_name_fix", true, true);
  
  static void zza(zzbw paramzzbw) {
    zzada = paramzzbw;
  }
  
  static void zza(zzn paramzzn) {
    zzaih = paramzzn;
    zza.zzjb();
  }
  
  static void zza(Exception paramException) {
    if (zzada == null)
      return; 
    Context context = zzada.getContext();
    if (zzaio == null) {
      boolean bool;
      if (GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000) == 0) {
        bool = true;
      } else {
        bool = false;
      } 
      zzaio = Boolean.valueOf(bool);
    } 
    if (zzaio.booleanValue())
      zzada.zzgt().zzjg().zzg("Got Exception on PhenotypeFlag.get on Play device", paramException); 
  }
  
  public static Map<String, String> zzm(Context paramContext) {
    return zzrx.zza(paramContext.getContentResolver(), zzsh.zzfq("com.google.android.gms.measurement")).zztk();
  }
  
  static {
    zza.zzb("measurement.log_third_party_store_events_enabled", false, false);
    zza.zzb("measurement.log_installs_enabled", false, false);
    zza.zzb("measurement.log_upgrades_enabled", false, false);
    zza.zzb("measurement.log_androidId_enabled", false, false);
  }
  
  static {
    zza.zzb("measurement.test.boolean_flag", false, false);
    zza.zzd("measurement.test.string_flag", "---", "---");
    zza.zzb("measurement.test.long_flag", -1L, -1L);
    zza.zzc("measurement.test.int_flag", -2, -2);
    zza.zza("measurement.test.double_flag", -3.0D, -3.0D);
  }
  
  static {
    zza.zzb("measurement.service.sessions.session_number_backfill_enabled", false, false);
  }
  
  static {
    zza.zzb("measurement.remove_app_instance_id_cache_enabled", true, true);
  }
  
  public static final class zza<V> {
    private final V zzaan;
    
    private zzsi<V> zzalj;
    
    private volatile V zzall;
    
    private final String zzoj;
    
    private zza(String param1String, V param1V1, V param1V2) {
      this.zzoj = param1String;
      this.zzaan = param1V1;
    }
    
    static zza<Double> zza(String param1String, double param1Double1, double param1Double2) {
      Double double_ = Double.valueOf(-3.0D);
      zza<Double> zza1 = new zza<Double>(param1String, double_, double_);
      zzai.zzaim.add(zza1);
      return zza1;
    }
    
    static zza<Long> zzb(String param1String, long param1Long1, long param1Long2) {
      zza<Long> zza1 = new zza<Long>(param1String, Long.valueOf(param1Long1), Long.valueOf(param1Long2));
      zzai.zzaij.add(zza1);
      return zza1;
    }
    
    static zza<Boolean> zzb(String param1String, boolean param1Boolean1, boolean param1Boolean2) {
      zza<Boolean> zza1 = new zza<Boolean>(param1String, Boolean.valueOf(param1Boolean1), Boolean.valueOf(param1Boolean2));
      zzai.zzaik.add(zza1);
      return zza1;
    }
    
    static zza<Integer> zzc(String param1String, int param1Int1, int param1Int2) {
      zza<Integer> zza1 = new zza<Integer>(param1String, Integer.valueOf(param1Int1), Integer.valueOf(param1Int2));
      zzai.zzaii.add(zza1);
      return zza1;
    }
    
    static zza<String> zzd(String param1String1, String param1String2, String param1String3) {
      zza<String> zza1 = new zza<String>(param1String1, param1String2, param1String3);
      zzai.zzail.add(zza1);
      return zza1;
    }
    
    private static void zzja() {
      // Byte code:
      //   0: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   2: monitorenter
      //   3: invokestatic isMainThread : ()Z
      //   6: ifne -> 232
      //   9: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   12: astore_0
      //   13: getstatic com/google/android/gms/measurement/internal/zzai.zzaik : Ljava/util/List;
      //   16: invokeinterface iterator : ()Ljava/util/Iterator;
      //   21: astore_0
      //   22: aload_0
      //   23: invokeinterface hasNext : ()Z
      //   28: ifeq -> 55
      //   31: aload_0
      //   32: invokeinterface next : ()Ljava/lang/Object;
      //   37: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   40: astore_1
      //   41: aload_1
      //   42: aload_1
      //   43: getfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   46: invokevirtual get : ()Ljava/lang/Object;
      //   49: putfield zzall : Ljava/lang/Object;
      //   52: goto -> 22
      //   55: getstatic com/google/android/gms/measurement/internal/zzai.zzail : Ljava/util/List;
      //   58: invokeinterface iterator : ()Ljava/util/Iterator;
      //   63: astore_1
      //   64: aload_1
      //   65: invokeinterface hasNext : ()Z
      //   70: ifeq -> 97
      //   73: aload_1
      //   74: invokeinterface next : ()Ljava/lang/Object;
      //   79: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   82: astore_0
      //   83: aload_0
      //   84: aload_0
      //   85: getfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   88: invokevirtual get : ()Ljava/lang/Object;
      //   91: putfield zzall : Ljava/lang/Object;
      //   94: goto -> 64
      //   97: getstatic com/google/android/gms/measurement/internal/zzai.zzaij : Ljava/util/List;
      //   100: invokeinterface iterator : ()Ljava/util/Iterator;
      //   105: astore_1
      //   106: aload_1
      //   107: invokeinterface hasNext : ()Z
      //   112: ifeq -> 139
      //   115: aload_1
      //   116: invokeinterface next : ()Ljava/lang/Object;
      //   121: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   124: astore_0
      //   125: aload_0
      //   126: aload_0
      //   127: getfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   130: invokevirtual get : ()Ljava/lang/Object;
      //   133: putfield zzall : Ljava/lang/Object;
      //   136: goto -> 106
      //   139: getstatic com/google/android/gms/measurement/internal/zzai.zzaii : Ljava/util/List;
      //   142: invokeinterface iterator : ()Ljava/util/Iterator;
      //   147: astore_0
      //   148: aload_0
      //   149: invokeinterface hasNext : ()Z
      //   154: ifeq -> 181
      //   157: aload_0
      //   158: invokeinterface next : ()Ljava/lang/Object;
      //   163: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   166: astore_1
      //   167: aload_1
      //   168: aload_1
      //   169: getfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   172: invokevirtual get : ()Ljava/lang/Object;
      //   175: putfield zzall : Ljava/lang/Object;
      //   178: goto -> 148
      //   181: getstatic com/google/android/gms/measurement/internal/zzai.zzaim : Ljava/util/List;
      //   184: invokeinterface iterator : ()Ljava/util/Iterator;
      //   189: astore_0
      //   190: aload_0
      //   191: invokeinterface hasNext : ()Z
      //   196: ifeq -> 228
      //   199: aload_0
      //   200: invokeinterface next : ()Ljava/lang/Object;
      //   205: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   208: astore_1
      //   209: aload_1
      //   210: aload_1
      //   211: getfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   214: invokevirtual get : ()Ljava/lang/Object;
      //   217: putfield zzall : Ljava/lang/Object;
      //   220: goto -> 190
      //   223: astore_0
      //   224: aload_0
      //   225: invokestatic zza : (Ljava/lang/Exception;)V
      //   228: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   230: monitorexit
      //   231: return
      //   232: new java/lang/IllegalStateException
      //   235: astore_0
      //   236: aload_0
      //   237: ldc 'Tried to refresh flag cache on main thread or on package side.'
      //   239: invokespecial <init> : (Ljava/lang/String;)V
      //   242: aload_0
      //   243: athrow
      //   244: astore_0
      //   245: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   247: monitorexit
      //   248: aload_0
      //   249: athrow
      // Exception table:
      //   from	to	target	type
      //   3	13	244	finally
      //   13	22	223	java/lang/SecurityException
      //   13	22	244	finally
      //   22	52	223	java/lang/SecurityException
      //   22	52	244	finally
      //   55	64	223	java/lang/SecurityException
      //   55	64	244	finally
      //   64	94	223	java/lang/SecurityException
      //   64	94	244	finally
      //   97	106	223	java/lang/SecurityException
      //   97	106	244	finally
      //   106	136	223	java/lang/SecurityException
      //   106	136	244	finally
      //   139	148	223	java/lang/SecurityException
      //   139	148	244	finally
      //   148	178	223	java/lang/SecurityException
      //   148	178	244	finally
      //   181	190	223	java/lang/SecurityException
      //   181	190	244	finally
      //   190	220	223	java/lang/SecurityException
      //   190	220	244	finally
      //   224	228	244	finally
      //   228	231	244	finally
      //   232	244	244	finally
      //   245	248	244	finally
    }
    
    private static void zzq() {
      // Byte code:
      //   0: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   2: monitorenter
      //   3: getstatic com/google/android/gms/measurement/internal/zzai.zzaik : Ljava/util/List;
      //   6: invokeinterface iterator : ()Ljava/util/Iterator;
      //   11: astore_1
      //   12: aload_1
      //   13: invokeinterface hasNext : ()Z
      //   18: ifeq -> 67
      //   21: aload_1
      //   22: invokeinterface next : ()Ljava/lang/Object;
      //   27: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   30: astore_2
      //   31: invokestatic zziz : ()Lcom/google/android/gms/internal/measurement/zzso;
      //   34: astore_3
      //   35: aload_2
      //   36: getfield zzoj : Ljava/lang/String;
      //   39: astore_0
      //   40: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   43: astore #4
      //   45: aload_2
      //   46: aload_3
      //   47: aload_0
      //   48: aload_2
      //   49: getfield zzaan : Ljava/lang/Object;
      //   52: checkcast java/lang/Boolean
      //   55: invokevirtual booleanValue : ()Z
      //   58: invokevirtual zzd : (Ljava/lang/String;Z)Lcom/google/android/gms/internal/measurement/zzsi;
      //   61: putfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   64: goto -> 12
      //   67: getstatic com/google/android/gms/measurement/internal/zzai.zzail : Ljava/util/List;
      //   70: invokeinterface iterator : ()Ljava/util/Iterator;
      //   75: astore_3
      //   76: aload_3
      //   77: invokeinterface hasNext : ()Z
      //   82: ifeq -> 129
      //   85: aload_3
      //   86: invokeinterface next : ()Ljava/lang/Object;
      //   91: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   94: astore_2
      //   95: invokestatic zziz : ()Lcom/google/android/gms/internal/measurement/zzso;
      //   98: astore #4
      //   100: aload_2
      //   101: getfield zzoj : Ljava/lang/String;
      //   104: astore_1
      //   105: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   108: astore_0
      //   109: aload_2
      //   110: aload #4
      //   112: aload_1
      //   113: aload_2
      //   114: getfield zzaan : Ljava/lang/Object;
      //   117: checkcast java/lang/String
      //   120: invokevirtual zzy : (Ljava/lang/String;Ljava/lang/String;)Lcom/google/android/gms/internal/measurement/zzsi;
      //   123: putfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   126: goto -> 76
      //   129: getstatic com/google/android/gms/measurement/internal/zzai.zzaij : Ljava/util/List;
      //   132: invokeinterface iterator : ()Ljava/util/Iterator;
      //   137: astore_1
      //   138: aload_1
      //   139: invokeinterface hasNext : ()Z
      //   144: ifeq -> 193
      //   147: aload_1
      //   148: invokeinterface next : ()Ljava/lang/Object;
      //   153: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   156: astore_3
      //   157: invokestatic zziz : ()Lcom/google/android/gms/internal/measurement/zzso;
      //   160: astore_2
      //   161: aload_3
      //   162: getfield zzoj : Ljava/lang/String;
      //   165: astore_0
      //   166: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   169: astore #4
      //   171: aload_3
      //   172: aload_2
      //   173: aload_0
      //   174: aload_3
      //   175: getfield zzaan : Ljava/lang/Object;
      //   178: checkcast java/lang/Long
      //   181: invokevirtual longValue : ()J
      //   184: invokevirtual zze : (Ljava/lang/String;J)Lcom/google/android/gms/internal/measurement/zzsi;
      //   187: putfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   190: goto -> 138
      //   193: getstatic com/google/android/gms/measurement/internal/zzai.zzaii : Ljava/util/List;
      //   196: invokeinterface iterator : ()Ljava/util/Iterator;
      //   201: astore_2
      //   202: aload_2
      //   203: invokeinterface hasNext : ()Z
      //   208: ifeq -> 260
      //   211: aload_2
      //   212: invokeinterface next : ()Ljava/lang/Object;
      //   217: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   220: astore #4
      //   222: invokestatic zziz : ()Lcom/google/android/gms/internal/measurement/zzso;
      //   225: astore_1
      //   226: aload #4
      //   228: getfield zzoj : Ljava/lang/String;
      //   231: astore_0
      //   232: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   235: astore_3
      //   236: aload #4
      //   238: aload_1
      //   239: aload_0
      //   240: aload #4
      //   242: getfield zzaan : Ljava/lang/Object;
      //   245: checkcast java/lang/Integer
      //   248: invokevirtual intValue : ()I
      //   251: invokevirtual zzd : (Ljava/lang/String;I)Lcom/google/android/gms/internal/measurement/zzsi;
      //   254: putfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   257: goto -> 202
      //   260: getstatic com/google/android/gms/measurement/internal/zzai.zzaim : Ljava/util/List;
      //   263: invokeinterface iterator : ()Ljava/util/Iterator;
      //   268: astore #4
      //   270: aload #4
      //   272: invokeinterface hasNext : ()Z
      //   277: ifeq -> 326
      //   280: aload #4
      //   282: invokeinterface next : ()Ljava/lang/Object;
      //   287: checkcast com/google/android/gms/measurement/internal/zzai$zza
      //   290: astore_2
      //   291: invokestatic zziz : ()Lcom/google/android/gms/internal/measurement/zzso;
      //   294: astore_0
      //   295: aload_2
      //   296: getfield zzoj : Ljava/lang/String;
      //   299: astore_1
      //   300: getstatic com/google/android/gms/measurement/internal/zzai.zzaih : Lcom/google/android/gms/measurement/internal/zzn;
      //   303: astore_3
      //   304: aload_2
      //   305: aload_0
      //   306: aload_1
      //   307: aload_2
      //   308: getfield zzaan : Ljava/lang/Object;
      //   311: checkcast java/lang/Double
      //   314: invokevirtual doubleValue : ()D
      //   317: invokevirtual zzb : (Ljava/lang/String;D)Lcom/google/android/gms/internal/measurement/zzsi;
      //   320: putfield zzalj : Lcom/google/android/gms/internal/measurement/zzsi;
      //   323: goto -> 270
      //   326: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   328: monitorexit
      //   329: return
      //   330: astore_0
      //   331: ldc com/google/android/gms/measurement/internal/zzai$zza
      //   333: monitorexit
      //   334: aload_0
      //   335: athrow
      // Exception table:
      //   from	to	target	type
      //   3	12	330	finally
      //   12	64	330	finally
      //   67	76	330	finally
      //   76	126	330	finally
      //   129	138	330	finally
      //   138	190	330	finally
      //   193	202	330	finally
      //   202	257	330	finally
      //   260	270	330	finally
      //   270	323	330	finally
      //   326	329	330	finally
      //   331	334	330	finally
    }
    
    public final V get() {
      if (zzai.zzaih == null)
        return this.zzaan; 
      if (zzn.isMainThread())
        return (this.zzall == null) ? this.zzaan : this.zzall; 
      zzja();
      try {
        return (V)this.zzalj.get();
      } catch (SecurityException securityException) {
        zzai.zza(securityException);
        return (V)this.zzalj.getDefaultValue();
      } 
    }
    
    public final V get(V param1V) {
      if (param1V != null)
        return param1V; 
      if (zzai.zzaih == null)
        return this.zzaan; 
      if (zzn.isMainThread())
        return (this.zzall == null) ? this.zzaan : this.zzall; 
      zzja();
      try {
        return (V)this.zzalj.get();
      } catch (SecurityException securityException) {
        zzai.zza(securityException);
        return (V)this.zzalj.getDefaultValue();
      } 
    }
    
    public final String getKey() {
      return this.zzoj;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzai.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */