package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzeb extends zzf {
  private final zzes zzase;
  
  private zzaj zzasf;
  
  private volatile Boolean zzasg;
  
  private final zzy zzash;
  
  private final zzfi zzasi;
  
  private final List<Runnable> zzasj = new ArrayList<Runnable>();
  
  private final zzy zzask;
  
  protected zzeb(zzbw paramzzbw) {
    super(paramzzbw);
    this.zzasi = new zzfi(paramzzbw.zzbx());
    this.zzase = new zzes(this);
    this.zzash = new zzec(this, paramzzbw);
    this.zzask = new zzek(this, paramzzbw);
  }
  
  private final void onServiceDisconnected(ComponentName paramComponentName) {
    zzaf();
    if (this.zzasf != null) {
      this.zzasf = null;
      zzgt().zzjo().zzg("Disconnected from device MeasurementService", paramComponentName);
      zzaf();
      zzdj();
    } 
  }
  
  private final void zzcy() {
    zzaf();
    this.zzasi.start();
    this.zzash.zzh(((Long)zzai.zzaka.get()).longValue());
  }
  
  private final void zzcz() {
    zzaf();
    if (!isConnected())
      return; 
    zzgt().zzjo().zzby("Inactivity, disconnecting from the service");
    disconnect();
  }
  
  private final void zzf(Runnable paramRunnable) throws IllegalStateException {
    zzaf();
    if (isConnected()) {
      paramRunnable.run();
      return;
    } 
    if (this.zzasj.size() >= 1000L) {
      zzgt().zzjg().zzby("Discarding data. Max runnable queue size reached");
      return;
    } 
    this.zzasj.add(paramRunnable);
    this.zzask.zzh(60000L);
    zzdj();
  }
  
  private final zzk zzl(boolean paramBoolean) {
    String str;
    zzgw();
    zzam zzam = zzgk();
    if (paramBoolean) {
      str = zzgt().zzjq();
    } else {
      str = null;
    } 
    return zzam.zzbs(str);
  }
  
  private final boolean zzlh() {
    zzgw();
    return true;
  }
  
  private final void zzlj() {
    zzaf();
    zzgt().zzjo().zzg("Processing queued up service tasks", Integer.valueOf(this.zzasj.size()));
    for (Runnable runnable : this.zzasj) {
      try {
        runnable.run();
      } catch (Exception exception) {
        zzgt().zzjg().zzg("Task exception while flushing queue", exception);
      } 
    } 
    this.zzasj.clear();
    this.zzask.cancel();
  }
  
  public final void disconnect() {
    zzaf();
    zzcl();
    this.zzase.zzlk();
    try {
      ConnectionTracker.getInstance().unbindService(getContext(), this.zzase);
    } catch (IllegalStateException|IllegalArgumentException illegalStateException) {}
    this.zzasf = null;
  }
  
  public final boolean isConnected() {
    zzaf();
    zzcl();
    return (this.zzasf != null);
  }
  
  protected final void zza(zzaj paramzzaj) {
    zzaf();
    Preconditions.checkNotNull(paramzzaj);
    this.zzasf = paramzzaj;
    zzcy();
    zzlj();
  }
  
  final void zza(zzaj paramzzaj, AbstractSafeParcelable paramAbstractSafeParcelable, zzk paramzzk) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: invokevirtual zzgg : ()V
    //   8: aload_0
    //   9: invokevirtual zzcl : ()V
    //   12: aload_0
    //   13: invokespecial zzlh : ()Z
    //   16: istore #8
    //   18: iconst_0
    //   19: istore #5
    //   21: bipush #100
    //   23: istore #4
    //   25: iload #5
    //   27: sipush #1001
    //   30: if_icmpge -> 302
    //   33: iload #4
    //   35: bipush #100
    //   37: if_icmpne -> 302
    //   40: new java/util/ArrayList
    //   43: dup
    //   44: invokespecial <init> : ()V
    //   47: astore #9
    //   49: iload #8
    //   51: ifeq -> 92
    //   54: aload_0
    //   55: invokevirtual zzgn : ()Lcom/google/android/gms/measurement/internal/zzao;
    //   58: bipush #100
    //   60: invokevirtual zzr : (I)Ljava/util/List;
    //   63: astore #10
    //   65: aload #10
    //   67: ifnull -> 92
    //   70: aload #9
    //   72: aload #10
    //   74: invokeinterface addAll : (Ljava/util/Collection;)Z
    //   79: pop
    //   80: aload #10
    //   82: invokeinterface size : ()I
    //   87: istore #4
    //   89: goto -> 95
    //   92: iconst_0
    //   93: istore #4
    //   95: aload_2
    //   96: ifnull -> 115
    //   99: iload #4
    //   101: bipush #100
    //   103: if_icmpge -> 115
    //   106: aload #9
    //   108: aload_2
    //   109: invokeinterface add : (Ljava/lang/Object;)Z
    //   114: pop
    //   115: aload #9
    //   117: invokevirtual size : ()I
    //   120: istore #7
    //   122: iconst_0
    //   123: istore #6
    //   125: iload #6
    //   127: iload #7
    //   129: if_icmpge -> 296
    //   132: aload #9
    //   134: iload #6
    //   136: invokevirtual get : (I)Ljava/lang/Object;
    //   139: astore #10
    //   141: iinc #6, 1
    //   144: aload #10
    //   146: checkcast com/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable
    //   149: astore #10
    //   151: aload #10
    //   153: instanceof com/google/android/gms/measurement/internal/zzag
    //   156: ifeq -> 194
    //   159: aload_1
    //   160: aload #10
    //   162: checkcast com/google/android/gms/measurement/internal/zzag
    //   165: aload_3
    //   166: invokeinterface zza : (Lcom/google/android/gms/measurement/internal/zzag;Lcom/google/android/gms/measurement/internal/zzk;)V
    //   171: goto -> 125
    //   174: astore #10
    //   176: aload_0
    //   177: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   180: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   183: ldc_w 'Failed to send event to the service'
    //   186: aload #10
    //   188: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   191: goto -> 125
    //   194: aload #10
    //   196: instanceof com/google/android/gms/measurement/internal/zzfu
    //   199: ifeq -> 237
    //   202: aload_1
    //   203: aload #10
    //   205: checkcast com/google/android/gms/measurement/internal/zzfu
    //   208: aload_3
    //   209: invokeinterface zza : (Lcom/google/android/gms/measurement/internal/zzfu;Lcom/google/android/gms/measurement/internal/zzk;)V
    //   214: goto -> 125
    //   217: astore #10
    //   219: aload_0
    //   220: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   223: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   226: ldc_w 'Failed to send attribute to the service'
    //   229: aload #10
    //   231: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   234: goto -> 125
    //   237: aload #10
    //   239: instanceof com/google/android/gms/measurement/internal/zzo
    //   242: ifeq -> 280
    //   245: aload_1
    //   246: aload #10
    //   248: checkcast com/google/android/gms/measurement/internal/zzo
    //   251: aload_3
    //   252: invokeinterface zza : (Lcom/google/android/gms/measurement/internal/zzo;Lcom/google/android/gms/measurement/internal/zzk;)V
    //   257: goto -> 125
    //   260: astore #10
    //   262: aload_0
    //   263: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   266: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   269: ldc_w 'Failed to send conditional property to the service'
    //   272: aload #10
    //   274: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   277: goto -> 125
    //   280: aload_0
    //   281: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   284: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   287: ldc_w 'Discarding data. Unrecognized parcel type.'
    //   290: invokevirtual zzby : (Ljava/lang/String;)V
    //   293: goto -> 125
    //   296: iinc #5, 1
    //   299: goto -> 25
    //   302: return
    // Exception table:
    //   from	to	target	type
    //   159	171	174	android/os/RemoteException
    //   202	214	217	android/os/RemoteException
    //   245	257	260	android/os/RemoteException
  }
  
  protected final void zza(zzdx paramzzdx) {
    zzaf();
    zzcl();
    zzf(new zzei(this, paramzzdx));
  }
  
  public final void zza(AtomicReference<String> paramAtomicReference) {
    zzaf();
    zzcl();
    zzf(new zzef(this, paramAtomicReference, zzl(false)));
  }
  
  protected final void zza(AtomicReference<List<zzo>> paramAtomicReference, String paramString1, String paramString2, String paramString3) {
    zzaf();
    zzcl();
    zzf(new zzeo(this, paramAtomicReference, paramString1, paramString2, paramString3, zzl(false)));
  }
  
  protected final void zza(AtomicReference<List<zzfu>> paramAtomicReference, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    zzaf();
    zzcl();
    zzf(new zzep(this, paramAtomicReference, paramString1, paramString2, paramString3, paramBoolean, zzl(false)));
  }
  
  protected final void zzb(zzfu paramzzfu) {
    boolean bool;
    zzaf();
    zzcl();
    if (zzlh() && zzgn().zza(paramzzfu)) {
      bool = true;
    } else {
      bool = false;
    } 
    zzf(new zzer(this, bool, paramzzfu, zzl(true)));
  }
  
  protected final void zzc(zzag paramzzag, String paramString) {
    boolean bool;
    Preconditions.checkNotNull(paramzzag);
    zzaf();
    zzcl();
    boolean bool1 = zzlh();
    if (bool1 && zzgn().zza(paramzzag)) {
      bool = true;
    } else {
      bool = false;
    } 
    zzf(new zzem(this, bool1, bool, paramzzag, zzl(true), paramString));
  }
  
  protected final void zzd(zzo paramzzo) {
    boolean bool;
    Preconditions.checkNotNull(paramzzo);
    zzaf();
    zzcl();
    zzgw();
    if (zzgn().zzc(paramzzo)) {
      bool = true;
    } else {
      bool = false;
    } 
    zzf(new zzen(this, true, bool, new zzo(paramzzo), zzl(true), paramzzo));
  }
  
  final void zzdj() {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual zzaf : ()V
    //   4: aload_0
    //   5: invokevirtual zzcl : ()V
    //   8: aload_0
    //   9: invokevirtual isConnected : ()Z
    //   12: ifeq -> 16
    //   15: return
    //   16: aload_0
    //   17: getfield zzasg : Ljava/lang/Boolean;
    //   20: astore #6
    //   22: iconst_0
    //   23: istore_3
    //   24: aload #6
    //   26: ifnonnull -> 373
    //   29: aload_0
    //   30: invokevirtual zzaf : ()V
    //   33: aload_0
    //   34: invokevirtual zzcl : ()V
    //   37: aload_0
    //   38: invokevirtual zzgu : ()Lcom/google/android/gms/measurement/internal/zzbd;
    //   41: invokevirtual zzjx : ()Ljava/lang/Boolean;
    //   44: astore #6
    //   46: aload #6
    //   48: ifnull -> 65
    //   51: aload #6
    //   53: invokevirtual booleanValue : ()Z
    //   56: ifeq -> 65
    //   59: iconst_1
    //   60: istore #5
    //   62: goto -> 364
    //   65: aload_0
    //   66: invokevirtual zzgw : ()Lcom/google/android/gms/measurement/internal/zzn;
    //   69: pop
    //   70: aload_0
    //   71: invokevirtual zzgk : ()Lcom/google/android/gms/measurement/internal/zzam;
    //   74: invokevirtual zzje : ()I
    //   77: iconst_1
    //   78: if_icmpne -> 89
    //   81: iconst_1
    //   82: istore #4
    //   84: iconst_1
    //   85: istore_1
    //   86: goto -> 309
    //   89: aload_0
    //   90: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   93: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   96: ldc_w 'Checking service availability'
    //   99: invokevirtual zzby : (Ljava/lang/String;)V
    //   102: aload_0
    //   103: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   106: ldc_w 12451000
    //   109: invokevirtual zzs : (I)I
    //   112: istore_1
    //   113: iload_1
    //   114: ifeq -> 293
    //   117: iload_1
    //   118: iconst_1
    //   119: if_icmpeq -> 274
    //   122: iload_1
    //   123: iconst_2
    //   124: if_icmpeq -> 217
    //   127: iload_1
    //   128: iconst_3
    //   129: if_icmpeq -> 201
    //   132: iload_1
    //   133: bipush #9
    //   135: if_icmpeq -> 185
    //   138: iload_1
    //   139: bipush #18
    //   141: if_icmpeq -> 169
    //   144: aload_0
    //   145: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   148: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   151: ldc_w 'Unexpected service status'
    //   154: iload_1
    //   155: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   158: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   161: iconst_0
    //   162: istore #4
    //   164: iconst_0
    //   165: istore_1
    //   166: goto -> 309
    //   169: aload_0
    //   170: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   173: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   176: ldc_w 'Service updating'
    //   179: invokevirtual zzby : (Ljava/lang/String;)V
    //   182: goto -> 81
    //   185: aload_0
    //   186: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   189: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   192: ldc_w 'Service invalid'
    //   195: invokevirtual zzby : (Ljava/lang/String;)V
    //   198: goto -> 161
    //   201: aload_0
    //   202: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   205: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   208: ldc_w 'Service disabled'
    //   211: invokevirtual zzby : (Ljava/lang/String;)V
    //   214: goto -> 161
    //   217: aload_0
    //   218: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   221: invokevirtual zzjn : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   224: ldc_w 'Service container out of date'
    //   227: invokevirtual zzby : (Ljava/lang/String;)V
    //   230: aload_0
    //   231: invokevirtual zzgr : ()Lcom/google/android/gms/measurement/internal/zzfx;
    //   234: invokevirtual zzml : ()I
    //   237: sipush #14500
    //   240: if_icmpge -> 246
    //   243: goto -> 287
    //   246: aload_0
    //   247: invokevirtual zzgu : ()Lcom/google/android/gms/measurement/internal/zzbd;
    //   250: invokevirtual zzjx : ()Ljava/lang/Boolean;
    //   253: astore #6
    //   255: aload #6
    //   257: ifnull -> 268
    //   260: aload #6
    //   262: invokevirtual booleanValue : ()Z
    //   265: ifeq -> 161
    //   268: iconst_1
    //   269: istore #4
    //   271: goto -> 164
    //   274: aload_0
    //   275: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   278: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   281: ldc_w 'Service missing'
    //   284: invokevirtual zzby : (Ljava/lang/String;)V
    //   287: iconst_0
    //   288: istore #4
    //   290: goto -> 84
    //   293: aload_0
    //   294: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   297: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   300: ldc_w 'Service available'
    //   303: invokevirtual zzby : (Ljava/lang/String;)V
    //   306: goto -> 81
    //   309: iload_1
    //   310: istore_2
    //   311: iload #4
    //   313: ifne -> 343
    //   316: iload_1
    //   317: istore_2
    //   318: aload_0
    //   319: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   322: invokevirtual zzif : ()Z
    //   325: ifeq -> 343
    //   328: aload_0
    //   329: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   332: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   335: ldc_w 'No way to upload. Consider using the full version of Analytics'
    //   338: invokevirtual zzby : (Ljava/lang/String;)V
    //   341: iconst_0
    //   342: istore_2
    //   343: iload #4
    //   345: istore #5
    //   347: iload_2
    //   348: ifeq -> 364
    //   351: aload_0
    //   352: invokevirtual zzgu : ()Lcom/google/android/gms/measurement/internal/zzbd;
    //   355: iload #4
    //   357: invokevirtual zzg : (Z)V
    //   360: iload #4
    //   362: istore #5
    //   364: aload_0
    //   365: iload #5
    //   367: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   370: putfield zzasg : Ljava/lang/Boolean;
    //   373: aload_0
    //   374: getfield zzasg : Ljava/lang/Boolean;
    //   377: invokevirtual booleanValue : ()Z
    //   380: ifeq -> 391
    //   383: aload_0
    //   384: getfield zzase : Lcom/google/android/gms/measurement/internal/zzes;
    //   387: invokevirtual zzll : ()V
    //   390: return
    //   391: aload_0
    //   392: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   395: invokevirtual zzif : ()Z
    //   398: ifne -> 527
    //   401: aload_0
    //   402: invokevirtual zzgw : ()Lcom/google/android/gms/measurement/internal/zzn;
    //   405: pop
    //   406: aload_0
    //   407: invokevirtual getContext : ()Landroid/content/Context;
    //   410: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   413: new android/content/Intent
    //   416: dup
    //   417: invokespecial <init> : ()V
    //   420: aload_0
    //   421: invokevirtual getContext : ()Landroid/content/Context;
    //   424: ldc_w 'com.google.android.gms.measurement.AppMeasurementService'
    //   427: invokevirtual setClassName : (Landroid/content/Context;Ljava/lang/String;)Landroid/content/Intent;
    //   430: ldc_w 65536
    //   433: invokevirtual queryIntentServices : (Landroid/content/Intent;I)Ljava/util/List;
    //   436: astore #6
    //   438: iload_3
    //   439: istore_1
    //   440: aload #6
    //   442: ifnull -> 459
    //   445: iload_3
    //   446: istore_1
    //   447: aload #6
    //   449: invokeinterface size : ()I
    //   454: ifle -> 459
    //   457: iconst_1
    //   458: istore_1
    //   459: iload_1
    //   460: ifeq -> 514
    //   463: new android/content/Intent
    //   466: dup
    //   467: ldc_w 'com.google.android.gms.measurement.START'
    //   470: invokespecial <init> : (Ljava/lang/String;)V
    //   473: astore #7
    //   475: aload_0
    //   476: invokevirtual getContext : ()Landroid/content/Context;
    //   479: astore #6
    //   481: aload_0
    //   482: invokevirtual zzgw : ()Lcom/google/android/gms/measurement/internal/zzn;
    //   485: pop
    //   486: aload #7
    //   488: new android/content/ComponentName
    //   491: dup
    //   492: aload #6
    //   494: ldc_w 'com.google.android.gms.measurement.AppMeasurementService'
    //   497: invokespecial <init> : (Landroid/content/Context;Ljava/lang/String;)V
    //   500: invokevirtual setComponent : (Landroid/content/ComponentName;)Landroid/content/Intent;
    //   503: pop
    //   504: aload_0
    //   505: getfield zzase : Lcom/google/android/gms/measurement/internal/zzes;
    //   508: aload #7
    //   510: invokevirtual zzb : (Landroid/content/Intent;)V
    //   513: return
    //   514: aload_0
    //   515: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   518: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   521: ldc_w 'Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest'
    //   524: invokevirtual zzby : (Ljava/lang/String;)V
    //   527: return
  }
  
  protected final boolean zzgy() {
    return false;
  }
  
  protected final void zzld() {
    zzaf();
    zzcl();
    zzf(new zzeh(this, zzl(true)));
  }
  
  protected final void zzlg() {
    zzaf();
    zzcl();
    zzf(new zzel(this, zzl(true)));
  }
  
  final Boolean zzli() {
    return this.zzasg;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzeb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */