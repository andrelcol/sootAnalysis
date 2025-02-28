package com.google.android.gms.measurement.internal;

import android.content.Context;

public final class zzam extends zzf {
  private String zzafi;
  
  private String zzafp;
  
  private long zzafs;
  
  private String zzafv;
  
  private int zzagp;
  
  private int zzalm;
  
  private long zzaln;
  
  private String zzts;
  
  private String zztt;
  
  zzam(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final String zzjc() {
    try {
      Class<?> clazz = getContext().getClassLoader().loadClass("com.google.firebase.analytics.FirebaseAnalytics");
      if (clazz == null)
        return null; 
      try {
        Object object = clazz.getDeclaredMethod("getInstance", new Class[] { Context.class }).invoke(null, new Object[] { getContext() });
        if (object == null)
          return null; 
        try {
          return (String)clazz.getDeclaredMethod("getFirebaseInstanceId", new Class[0]).invoke(object, new Object[0]);
        } catch (Exception exception) {
          zzgt().zzjl().zzby("Failed to retrieve Firebase Instance Id");
          return null;
        } 
      } catch (Exception exception) {
        zzgt().zzjk().zzby("Failed to obtain Firebase Analytics instance");
      } 
    } catch (ClassNotFoundException classNotFoundException) {}
    return null;
  }
  
  final String getGmpAppId() {
    zzcl();
    return this.zzafi;
  }
  
  final String zzal() {
    zzcl();
    return this.zztt;
  }
  
  final zzk zzbs(String paramString) {
    String str1;
    zzaf();
    zzgg();
    String str2 = zzal();
    String str3 = getGmpAppId();
    zzcl();
    String str5 = this.zzts;
    long l5 = zzjd();
    zzcl();
    String str4 = this.zzafp;
    long l3 = zzgv().zzhh();
    zzcl();
    zzaf();
    if (this.zzaln == 0L)
      this.zzaln = this.zzada.zzgr().zzd(getContext(), getContext().getPackageName()); 
    long l4 = this.zzaln;
    boolean bool1 = this.zzada.isEnabled();
    boolean bool2 = (zzgu()).zzans;
    zzaf();
    zzgg();
    if (zzgv().zzaz(this.zztt) && !this.zzada.isEnabled()) {
      str1 = null;
    } else {
      str1 = zzjc();
    } 
    zzcl();
    long l2 = this.zzafs;
    long l1 = this.zzada.zzkt();
    int i = zzje();
    zzq zzq2 = zzgv();
    zzq2.zzgg();
    Boolean bool5 = zzq2.zzar("google_analytics_adid_collection_enabled");
    if (bool5 == null || bool5.booleanValue()) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool3 = Boolean.valueOf(bool).booleanValue();
    zzq zzq1 = zzgv();
    zzq1.zzgg();
    Boolean bool4 = zzq1.zzar("google_analytics_ssaid_collection_enabled");
    if (bool4 == null || bool4.booleanValue()) {
      bool = true;
      return new zzk(str2, str3, str5, l5, str4, l3, l4, paramString, bool1, bool2 ^ true, str1, l2, l1, i, bool3, Boolean.valueOf(bool).booleanValue(), zzgu().zzkb(), zzhb());
    } 
    boolean bool = false;
    return new zzk(str2, str3, str5, l5, str4, l3, l4, paramString, bool1, bool2 ^ true, str1, l2, l1, i, bool3, Boolean.valueOf(bool).booleanValue(), zzgu().zzkb(), zzhb());
  }
  
  protected final boolean zzgy() {
    return true;
  }
  
  protected final void zzgz() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getContext : ()Landroid/content/Context;
    //   4: invokevirtual getPackageName : ()Ljava/lang/String;
    //   7: astore #9
    //   9: aload_0
    //   10: invokevirtual getContext : ()Landroid/content/Context;
    //   13: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   16: astore #11
    //   18: ldc 'Unknown'
    //   20: astore #5
    //   22: ldc ''
    //   24: astore #8
    //   26: ldc 'unknown'
    //   28: astore #7
    //   30: ldc -2147483648
    //   32: istore_2
    //   33: aload #11
    //   35: ifnonnull -> 64
    //   38: aload_0
    //   39: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   42: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   45: ldc 'PackageManager is null, app identity information might be inaccurate. appId'
    //   47: aload #9
    //   49: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   52: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   55: aload #5
    //   57: astore #6
    //   59: iload_2
    //   60: istore_1
    //   61: goto -> 259
    //   64: aload #11
    //   66: aload #9
    //   68: invokevirtual getInstallerPackageName : (Ljava/lang/String;)Ljava/lang/String;
    //   71: astore #6
    //   73: goto -> 99
    //   76: astore #4
    //   78: aload_0
    //   79: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   82: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   85: ldc 'Error retrieving app installer package name. appId'
    //   87: aload #9
    //   89: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   92: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   95: aload #7
    //   97: astore #6
    //   99: aload #6
    //   101: ifnonnull -> 111
    //   104: ldc 'manual_install'
    //   106: astore #4
    //   108: goto -> 129
    //   111: aload #6
    //   113: astore #4
    //   115: ldc 'com.android.vending'
    //   117: aload #6
    //   119: invokevirtual equals : (Ljava/lang/Object;)Z
    //   122: ifeq -> 129
    //   125: ldc ''
    //   127: astore #4
    //   129: aload #11
    //   131: aload_0
    //   132: invokevirtual getContext : ()Landroid/content/Context;
    //   135: invokevirtual getPackageName : ()Ljava/lang/String;
    //   138: iconst_0
    //   139: invokevirtual getPackageInfo : (Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   142: astore #10
    //   144: aload #5
    //   146: astore #6
    //   148: aload #4
    //   150: astore #7
    //   152: iload_2
    //   153: istore_1
    //   154: aload #10
    //   156: ifnull -> 259
    //   159: aload #11
    //   161: aload #10
    //   163: getfield applicationInfo : Landroid/content/pm/ApplicationInfo;
    //   166: invokevirtual getApplicationLabel : (Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   169: astore #6
    //   171: aload #6
    //   173: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   176: ifne -> 191
    //   179: aload #6
    //   181: invokeinterface toString : ()Ljava/lang/String;
    //   186: astore #6
    //   188: goto -> 195
    //   191: ldc 'Unknown'
    //   193: astore #6
    //   195: aload #10
    //   197: getfield versionName : Ljava/lang/String;
    //   200: astore #7
    //   202: aload #7
    //   204: astore #5
    //   206: aload #10
    //   208: getfield versionCode : I
    //   211: istore_1
    //   212: aload #7
    //   214: astore #6
    //   216: aload #4
    //   218: astore #7
    //   220: goto -> 259
    //   223: astore #6
    //   225: ldc 'Unknown'
    //   227: astore #6
    //   229: aload_0
    //   230: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   233: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   236: ldc_w 'Error retrieving package info. appId, appName'
    //   239: aload #9
    //   241: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   244: aload #6
    //   246: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   249: iload_2
    //   250: istore_1
    //   251: aload #4
    //   253: astore #7
    //   255: aload #5
    //   257: astore #6
    //   259: aload_0
    //   260: aload #9
    //   262: putfield zztt : Ljava/lang/String;
    //   265: aload_0
    //   266: aload #7
    //   268: putfield zzafp : Ljava/lang/String;
    //   271: aload_0
    //   272: aload #6
    //   274: putfield zzts : Ljava/lang/String;
    //   277: aload_0
    //   278: iload_1
    //   279: putfield zzalm : I
    //   282: aload_0
    //   283: lconst_0
    //   284: putfield zzaln : J
    //   287: aload_0
    //   288: invokevirtual zzgw : ()Lcom/google/android/gms/measurement/internal/zzn;
    //   291: pop
    //   292: aload_0
    //   293: invokevirtual getContext : ()Landroid/content/Context;
    //   296: invokestatic initialize : (Landroid/content/Context;)Lcom/google/android/gms/common/api/Status;
    //   299: astore #4
    //   301: iconst_1
    //   302: istore_3
    //   303: aload #4
    //   305: ifnull -> 321
    //   308: aload #4
    //   310: invokevirtual isSuccess : ()Z
    //   313: ifeq -> 321
    //   316: iconst_1
    //   317: istore_1
    //   318: goto -> 323
    //   321: iconst_0
    //   322: istore_1
    //   323: aload_0
    //   324: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   327: invokevirtual zzko : ()Ljava/lang/String;
    //   330: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   333: ifne -> 357
    //   336: ldc_w 'am'
    //   339: aload_0
    //   340: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   343: invokevirtual zzkp : ()Ljava/lang/String;
    //   346: invokevirtual equals : (Ljava/lang/Object;)Z
    //   349: ifeq -> 357
    //   352: iconst_1
    //   353: istore_2
    //   354: goto -> 359
    //   357: iconst_0
    //   358: istore_2
    //   359: iload_1
    //   360: iload_2
    //   361: ior
    //   362: istore_1
    //   363: iload_1
    //   364: ifne -> 414
    //   367: aload #4
    //   369: ifnonnull -> 388
    //   372: aload_0
    //   373: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   376: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   379: ldc_w 'GoogleService failed to initialize (no status)'
    //   382: invokevirtual zzby : (Ljava/lang/String;)V
    //   385: goto -> 414
    //   388: aload_0
    //   389: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   392: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   395: ldc_w 'GoogleService failed to initialize, status'
    //   398: aload #4
    //   400: invokevirtual getStatusCode : ()I
    //   403: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   406: aload #4
    //   408: invokevirtual getStatusMessage : ()Ljava/lang/String;
    //   411: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   414: iload_1
    //   415: ifeq -> 547
    //   418: aload_0
    //   419: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   422: invokevirtual zzia : ()Ljava/lang/Boolean;
    //   425: astore #4
    //   427: aload_0
    //   428: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   431: invokevirtual zzhz : ()Z
    //   434: ifeq -> 463
    //   437: aload_0
    //   438: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   441: invokevirtual zzkn : ()Z
    //   444: ifeq -> 547
    //   447: aload_0
    //   448: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   451: invokevirtual zzjm : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   454: ldc_w 'Collection disabled with firebase_analytics_collection_deactivated=1'
    //   457: invokevirtual zzby : (Ljava/lang/String;)V
    //   460: goto -> 547
    //   463: aload #4
    //   465: ifnull -> 502
    //   468: aload #4
    //   470: invokevirtual booleanValue : ()Z
    //   473: ifne -> 502
    //   476: aload_0
    //   477: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   480: invokevirtual zzkn : ()Z
    //   483: ifeq -> 547
    //   486: aload_0
    //   487: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   490: invokevirtual zzjm : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   493: ldc_w 'Collection disabled with firebase_analytics_collection_enabled=0'
    //   496: invokevirtual zzby : (Ljava/lang/String;)V
    //   499: goto -> 547
    //   502: aload #4
    //   504: ifnonnull -> 529
    //   507: invokestatic isMeasurementExplicitlyDisabled : ()Z
    //   510: ifeq -> 529
    //   513: aload_0
    //   514: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   517: invokevirtual zzjm : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   520: ldc_w 'Collection disabled with google_app_measurement_enable=0'
    //   523: invokevirtual zzby : (Ljava/lang/String;)V
    //   526: goto -> 547
    //   529: aload_0
    //   530: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   533: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   536: ldc_w 'Collection enabled'
    //   539: invokevirtual zzby : (Ljava/lang/String;)V
    //   542: iload_3
    //   543: istore_1
    //   544: goto -> 549
    //   547: iconst_0
    //   548: istore_1
    //   549: aload_0
    //   550: ldc ''
    //   552: putfield zzafi : Ljava/lang/String;
    //   555: aload_0
    //   556: ldc ''
    //   558: putfield zzafv : Ljava/lang/String;
    //   561: aload_0
    //   562: lconst_0
    //   563: putfield zzafs : J
    //   566: aload_0
    //   567: invokevirtual zzgw : ()Lcom/google/android/gms/measurement/internal/zzn;
    //   570: pop
    //   571: aload_0
    //   572: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   575: invokevirtual zzko : ()Ljava/lang/String;
    //   578: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   581: ifne -> 611
    //   584: ldc_w 'am'
    //   587: aload_0
    //   588: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   591: invokevirtual zzkp : ()Ljava/lang/String;
    //   594: invokevirtual equals : (Ljava/lang/Object;)Z
    //   597: ifeq -> 611
    //   600: aload_0
    //   601: aload_0
    //   602: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   605: invokevirtual zzko : ()Ljava/lang/String;
    //   608: putfield zzafv : Ljava/lang/String;
    //   611: invokestatic getGoogleAppId : ()Ljava/lang/String;
    //   614: astore #5
    //   616: aload #5
    //   618: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   621: ifeq -> 631
    //   624: aload #8
    //   626: astore #4
    //   628: goto -> 635
    //   631: aload #5
    //   633: astore #4
    //   635: aload_0
    //   636: aload #4
    //   638: putfield zzafi : Ljava/lang/String;
    //   641: aload #5
    //   643: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   646: ifne -> 675
    //   649: new com/google/android/gms/common/internal/StringResourceValueReader
    //   652: astore #4
    //   654: aload #4
    //   656: aload_0
    //   657: invokevirtual getContext : ()Landroid/content/Context;
    //   660: invokespecial <init> : (Landroid/content/Context;)V
    //   663: aload_0
    //   664: aload #4
    //   666: ldc_w 'admob_app_id'
    //   669: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   672: putfield zzafv : Ljava/lang/String;
    //   675: iload_1
    //   676: ifeq -> 725
    //   679: aload_0
    //   680: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   683: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   686: ldc_w 'App package, google app id'
    //   689: aload_0
    //   690: getfield zztt : Ljava/lang/String;
    //   693: aload_0
    //   694: getfield zzafi : Ljava/lang/String;
    //   697: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   700: goto -> 725
    //   703: astore #4
    //   705: aload_0
    //   706: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   709: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   712: ldc_w 'getGoogleAppId or isMeasurementEnabled failed with exception. appId'
    //   715: aload #9
    //   717: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   720: aload #4
    //   722: invokevirtual zze : (Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V
    //   725: getstatic android/os/Build$VERSION.SDK_INT : I
    //   728: bipush #16
    //   730: if_icmplt -> 745
    //   733: aload_0
    //   734: aload_0
    //   735: invokevirtual getContext : ()Landroid/content/Context;
    //   738: invokestatic isInstantApp : (Landroid/content/Context;)Z
    //   741: putfield zzagp : I
    //   744: return
    //   745: aload_0
    //   746: iconst_0
    //   747: putfield zzagp : I
    //   750: return
    //   751: astore #7
    //   753: goto -> 229
    // Exception table:
    //   from	to	target	type
    //   64	73	76	java/lang/IllegalArgumentException
    //   129	144	223	android/content/pm/PackageManager$NameNotFoundException
    //   159	188	223	android/content/pm/PackageManager$NameNotFoundException
    //   195	202	751	android/content/pm/PackageManager$NameNotFoundException
    //   206	212	751	android/content/pm/PackageManager$NameNotFoundException
    //   611	624	703	java/lang/IllegalStateException
    //   635	675	703	java/lang/IllegalStateException
    //   679	700	703	java/lang/IllegalStateException
  }
  
  final String zzhb() {
    zzcl();
    return this.zzafv;
  }
  
  final int zzjd() {
    zzcl();
    return this.zzalm;
  }
  
  final int zzje() {
    zzcl();
    return this.zzagp;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzam.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */