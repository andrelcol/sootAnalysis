package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzby extends zzak {
  private final zzfn zzamx;
  
  private Boolean zzaql;
  
  private String zzaqm;
  
  public zzby(zzfn paramzzfn) {
    this(paramzzfn, null);
  }
  
  private zzby(zzfn paramzzfn, String paramString) {
    Preconditions.checkNotNull(paramzzfn);
    this.zzamx = paramzzfn;
    this.zzaqm = null;
  }
  
  private final void zzb(zzk paramzzk, boolean paramBoolean) {
    Preconditions.checkNotNull(paramzzk);
    zzc(paramzzk.packageName, false);
    this.zzamx.zzgr().zzu(paramzzk.zzafi, paramzzk.zzafv);
  }
  
  private final void zzc(String paramString, boolean paramBoolean) {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   4: ifne -> 179
    //   7: iload_2
    //   8: ifeq -> 93
    //   11: aload_0
    //   12: getfield zzaql : Ljava/lang/Boolean;
    //   15: ifnonnull -> 83
    //   18: ldc 'com.google.android.gms'
    //   20: aload_0
    //   21: getfield zzaqm : Ljava/lang/String;
    //   24: invokevirtual equals : (Ljava/lang/Object;)Z
    //   27: ifne -> 73
    //   30: aload_0
    //   31: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   34: invokevirtual getContext : ()Landroid/content/Context;
    //   37: invokestatic getCallingUid : ()I
    //   40: invokestatic isGooglePlayServicesUid : (Landroid/content/Context;I)Z
    //   43: ifne -> 73
    //   46: aload_0
    //   47: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   50: invokevirtual getContext : ()Landroid/content/Context;
    //   53: invokestatic getInstance : (Landroid/content/Context;)Lcom/google/android/gms/common/GoogleSignatureVerifier;
    //   56: invokestatic getCallingUid : ()I
    //   59: invokevirtual isUidGoogleSigned : (I)Z
    //   62: ifeq -> 68
    //   65: goto -> 73
    //   68: iconst_0
    //   69: istore_2
    //   70: goto -> 75
    //   73: iconst_1
    //   74: istore_2
    //   75: aload_0
    //   76: iload_2
    //   77: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   80: putfield zzaql : Ljava/lang/Boolean;
    //   83: aload_0
    //   84: getfield zzaql : Ljava/lang/Boolean;
    //   87: invokevirtual booleanValue : ()Z
    //   90: ifne -> 133
    //   93: aload_0
    //   94: getfield zzaqm : Ljava/lang/String;
    //   97: ifnonnull -> 122
    //   100: aload_0
    //   101: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   104: invokevirtual getContext : ()Landroid/content/Context;
    //   107: invokestatic getCallingUid : ()I
    //   110: aload_1
    //   111: invokestatic uidHasPackageName : (Landroid/content/Context;ILjava/lang/String;)Z
    //   114: ifeq -> 122
    //   117: aload_0
    //   118: aload_1
    //   119: putfield zzaqm : Ljava/lang/String;
    //   122: aload_1
    //   123: aload_0
    //   124: getfield zzaqm : Ljava/lang/String;
    //   127: invokevirtual equals : (Ljava/lang/Object;)Z
    //   130: ifeq -> 134
    //   133: return
    //   134: new java/lang/SecurityException
    //   137: astore_3
    //   138: aload_3
    //   139: ldc 'Unknown calling package name '%s'.'
    //   141: iconst_1
    //   142: anewarray java/lang/Object
    //   145: dup
    //   146: iconst_0
    //   147: aload_1
    //   148: aastore
    //   149: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   152: invokespecial <init> : (Ljava/lang/String;)V
    //   155: aload_3
    //   156: athrow
    //   157: astore_3
    //   158: aload_0
    //   159: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   162: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   165: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   168: ldc 'Measurement Service called with invalid calling package. appId'
    //   170: aload_1
    //   171: invokestatic zzbw : (Ljava/lang/String;)Ljava/lang/Object;
    //   174: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   177: aload_3
    //   178: athrow
    //   179: aload_0
    //   180: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   183: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   186: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   189: ldc 'Measurement Service called without app package'
    //   191: invokevirtual zzby : (Ljava/lang/String;)V
    //   194: new java/lang/SecurityException
    //   197: dup
    //   198: ldc 'Measurement Service called without app package'
    //   200: invokespecial <init> : (Ljava/lang/String;)V
    //   203: athrow
    // Exception table:
    //   from	to	target	type
    //   11	65	157	java/lang/SecurityException
    //   75	83	157	java/lang/SecurityException
    //   83	93	157	java/lang/SecurityException
    //   93	122	157	java/lang/SecurityException
    //   122	133	157	java/lang/SecurityException
    //   134	157	157	java/lang/SecurityException
  }
  
  private final void zze(Runnable paramRunnable) {
    Preconditions.checkNotNull(paramRunnable);
    if (((Boolean)zzai.zzakn.get()).booleanValue() && this.zzamx.zzgs().zzkf()) {
      paramRunnable.run();
      return;
    } 
    this.zzamx.zzgs().zzc(paramRunnable);
  }
  
  public final List<zzfu> zza(zzk paramzzk, boolean paramBoolean) {
    zzb(paramzzk, false);
    Future<?> future = this.zzamx.zzgs().zzb(new zzco(this, paramzzk));
    try {
      List list = (List)future.get();
      ArrayList<zzfu> arrayList = new ArrayList();
      this(list.size());
      for (zzfw zzfw : list) {
        if (paramBoolean || !zzfx.zzcy(zzfw.name)) {
          zzfu zzfu = new zzfu();
          this(zzfw);
          arrayList.add(zzfu);
        } 
      } 
      return arrayList;
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(paramzzk.packageName), executionException);
    return null;
  }
  
  public final List<zzo> zza(String paramString1, String paramString2, zzk paramzzk) {
    zzb(paramzzk, false);
    Future<?> future = this.zzamx.zzgs().zzb(new zzcg(this, paramzzk, paramString1, paramString2));
    try {
      return (List)future.get();
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zzg("Failed to get conditional user properties", executionException);
    return Collections.emptyList();
  }
  
  public final List<zzfu> zza(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {
    zzc(paramString1, true);
    Future<?> future = this.zzamx.zzgs().zzb(new zzcf(this, paramString1, paramString2, paramString3));
    try {
      List list = (List)future.get();
      ArrayList<zzfu> arrayList = new ArrayList();
      this(list.size());
      for (zzfw zzfw : list) {
        if (paramBoolean || !zzfx.zzcy(zzfw.name)) {
          zzfu zzfu = new zzfu();
          this(zzfw);
          arrayList.add(zzfu);
        } 
      } 
      return arrayList;
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(paramString1), executionException);
    return Collections.emptyList();
  }
  
  public final List<zzfu> zza(String paramString1, String paramString2, boolean paramBoolean, zzk paramzzk) {
    zzb(paramzzk, false);
    Future<?> future = this.zzamx.zzgs().zzb(new zzce(this, paramzzk, paramString1, paramString2));
    try {
      List list = (List)future.get();
      ArrayList<zzfu> arrayList = new ArrayList();
      this(list.size());
      for (zzfw zzfw : list) {
        if (paramBoolean || !zzfx.zzcy(zzfw.name)) {
          zzfu zzfu = new zzfu();
          this(zzfw);
          arrayList.add(zzfu);
        } 
      } 
      return arrayList;
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zze("Failed to get user attributes. appId", zzas.zzbw(paramzzk.packageName), executionException);
    return Collections.emptyList();
  }
  
  public final void zza(long paramLong, String paramString1, String paramString2, String paramString3) {
    zze(new zzcq(this, paramString2, paramString3, paramString1, paramLong));
  }
  
  public final void zza(zzag paramzzag, zzk paramzzk) {
    Preconditions.checkNotNull(paramzzag);
    zzb(paramzzk, false);
    zze(new zzcj(this, paramzzag, paramzzk));
  }
  
  public final void zza(zzag paramzzag, String paramString1, String paramString2) {
    Preconditions.checkNotNull(paramzzag);
    Preconditions.checkNotEmpty(paramString1);
    zzc(paramString1, true);
    zze(new zzck(this, paramzzag, paramString1));
  }
  
  public final void zza(zzfu paramzzfu, zzk paramzzk) {
    Preconditions.checkNotNull(paramzzfu);
    zzb(paramzzk, false);
    if (paramzzfu.getValue() == null) {
      zze(new zzcm(this, paramzzfu, paramzzk));
      return;
    } 
    zze(new zzcn(this, paramzzfu, paramzzk));
  }
  
  public final void zza(zzk paramzzk) {
    zzb(paramzzk, false);
    zze(new zzcp(this, paramzzk));
  }
  
  public final void zza(zzo paramzzo, zzk paramzzk) {
    Preconditions.checkNotNull(paramzzo);
    Preconditions.checkNotNull(paramzzo.zzags);
    zzb(paramzzk, false);
    zzo zzo1 = new zzo(paramzzo);
    zzo1.packageName = paramzzk.packageName;
    if (paramzzo.zzags.getValue() == null) {
      zze(new zzca(this, zzo1, paramzzk));
      return;
    } 
    zze(new zzcb(this, zzo1, paramzzk));
  }
  
  public final byte[] zza(zzag paramzzag, String paramString) {
    Preconditions.checkNotEmpty(paramString);
    Preconditions.checkNotNull(paramzzag);
    zzc(paramString, true);
    this.zzamx.zzgt().zzjn().zzg("Log and bundle. event", this.zzamx.zzgq().zzbt(paramzzag.name));
    long l = this.zzamx.zzbx().nanoTime() / 1000000L;
    Future<?> future = this.zzamx.zzgs().zzc(new zzcl(this, paramzzag, paramString));
    try {
      byte[] arrayOfByte2 = (byte[])future.get();
      byte[] arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte2 == null) {
        this.zzamx.zzgt().zzjg().zzg("Log and bundle returned null. appId", zzas.zzbw(paramString));
        arrayOfByte1 = new byte[0];
      } 
      long l1 = this.zzamx.zzbx().nanoTime() / 1000000L;
      this.zzamx.zzgt().zzjn().zzd("Log and bundle processed. event, size, time_ms", this.zzamx.zzgq().zzbt(paramzzag.name), Integer.valueOf(arrayOfByte1.length), Long.valueOf(l1 - l));
      return arrayOfByte1;
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zzd("Failed to log and bundle. appId, event, error", zzas.zzbw(paramString), this.zzamx.zzgq().zzbt(paramzzag.name), executionException);
    return null;
  }
  
  final zzag zzb(zzag paramzzag, zzk paramzzk) {
    // Byte code:
    //   0: ldc_w '_cmp'
    //   3: aload_1
    //   4: getfield name : Ljava/lang/String;
    //   7: invokevirtual equals : (Ljava/lang/Object;)Z
    //   10: istore #5
    //   12: iconst_0
    //   13: istore #4
    //   15: iload #4
    //   17: istore_3
    //   18: iload #5
    //   20: ifeq -> 121
    //   23: aload_1
    //   24: getfield zzahu : Lcom/google/android/gms/measurement/internal/zzad;
    //   27: astore #6
    //   29: iload #4
    //   31: istore_3
    //   32: aload #6
    //   34: ifnull -> 121
    //   37: aload #6
    //   39: invokevirtual size : ()I
    //   42: ifne -> 51
    //   45: iload #4
    //   47: istore_3
    //   48: goto -> 121
    //   51: aload_1
    //   52: getfield zzahu : Lcom/google/android/gms/measurement/internal/zzad;
    //   55: ldc_w '_cis'
    //   58: invokevirtual getString : (Ljava/lang/String;)Ljava/lang/String;
    //   61: astore #6
    //   63: iload #4
    //   65: istore_3
    //   66: aload #6
    //   68: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   71: ifne -> 121
    //   74: ldc_w 'referrer broadcast'
    //   77: aload #6
    //   79: invokevirtual equals : (Ljava/lang/Object;)Z
    //   82: ifne -> 99
    //   85: iload #4
    //   87: istore_3
    //   88: ldc_w 'referrer API'
    //   91: aload #6
    //   93: invokevirtual equals : (Ljava/lang/Object;)Z
    //   96: ifeq -> 121
    //   99: iload #4
    //   101: istore_3
    //   102: aload_0
    //   103: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   106: invokevirtual zzgv : ()Lcom/google/android/gms/measurement/internal/zzq;
    //   109: aload_2
    //   110: getfield packageName : Ljava/lang/String;
    //   113: invokevirtual zzbe : (Ljava/lang/String;)Z
    //   116: ifeq -> 121
    //   119: iconst_1
    //   120: istore_3
    //   121: iload_3
    //   122: ifeq -> 168
    //   125: aload_0
    //   126: getfield zzamx : Lcom/google/android/gms/measurement/internal/zzfn;
    //   129: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   132: invokevirtual zzjm : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   135: ldc_w 'Event has been filtered '
    //   138: aload_1
    //   139: invokevirtual toString : ()Ljava/lang/String;
    //   142: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   145: new com/google/android/gms/measurement/internal/zzag
    //   148: dup
    //   149: ldc_w '_cmpx'
    //   152: aload_1
    //   153: getfield zzahu : Lcom/google/android/gms/measurement/internal/zzad;
    //   156: aload_1
    //   157: getfield origin : Ljava/lang/String;
    //   160: aload_1
    //   161: getfield zzaig : J
    //   164: invokespecial <init> : (Ljava/lang/String;Lcom/google/android/gms/measurement/internal/zzad;Ljava/lang/String;J)V
    //   167: areturn
    //   168: aload_1
    //   169: areturn
  }
  
  public final void zzb(zzk paramzzk) {
    zzb(paramzzk, false);
    zze(new zzbz(this, paramzzk));
  }
  
  public final void zzb(zzo paramzzo) {
    Preconditions.checkNotNull(paramzzo);
    Preconditions.checkNotNull(paramzzo.zzags);
    zzc(paramzzo.packageName, true);
    zzo zzo1 = new zzo(paramzzo);
    if (paramzzo.zzags.getValue() == null) {
      zze(new zzcc(this, zzo1));
      return;
    } 
    zze(new zzcd(this, zzo1));
  }
  
  public final String zzc(zzk paramzzk) {
    zzb(paramzzk, false);
    return this.zzamx.zzh(paramzzk);
  }
  
  public final void zzd(zzk paramzzk) {
    zzc(paramzzk.packageName, false);
    zze(new zzci(this, paramzzk));
  }
  
  public final List<zzo> zze(String paramString1, String paramString2, String paramString3) {
    zzc(paramString1, true);
    Future<?> future = this.zzamx.zzgs().zzb(new zzch(this, paramString1, paramString2, paramString3));
    try {
      return (List)future.get();
    } catch (InterruptedException interruptedException) {
    
    } catch (ExecutionException executionException) {}
    this.zzamx.zzgt().zzjg().zzg("Failed to get conditional user properties", executionException);
    return Collections.emptyList();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzby.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */