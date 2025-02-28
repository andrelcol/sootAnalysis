package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Keep;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirebaseInstanceId {
  private static final long zzai = TimeUnit.HOURS.toSeconds(8L);
  
  private static zzaw zzaj;
  
  private static ScheduledThreadPoolExecutor zzak;
  
  private final Executor zzal;
  
  private final FirebaseApp zzam;
  
  private final zzan zzan;
  
  private MessagingChannel zzao;
  
  private final zzaq zzap;
  
  private final zzba zzaq;
  
  private boolean zzar;
  
  private final zza zzas;
  
  FirebaseInstanceId(FirebaseApp paramFirebaseApp, Subscriber paramSubscriber) {
    this(paramFirebaseApp, new zzan(paramFirebaseApp.getApplicationContext()), zzi.zzf(), zzi.zzf(), paramSubscriber);
  }
  
  private FirebaseInstanceId(FirebaseApp paramFirebaseApp, zzan paramzzan, Executor paramExecutor1, Executor paramExecutor2, Subscriber paramSubscriber) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial <init> : ()V
    //   4: aload_0
    //   5: iconst_0
    //   6: putfield zzar : Z
    //   9: aload_1
    //   10: invokestatic zza : (Lcom/google/firebase/FirebaseApp;)Ljava/lang/String;
    //   13: ifnull -> 188
    //   16: ldc com/google/firebase/iid/FirebaseInstanceId
    //   18: monitorenter
    //   19: getstatic com/google/firebase/iid/FirebaseInstanceId.zzaj : Lcom/google/firebase/iid/zzaw;
    //   22: ifnonnull -> 44
    //   25: new com/google/firebase/iid/zzaw
    //   28: astore #6
    //   30: aload #6
    //   32: aload_1
    //   33: invokevirtual getApplicationContext : ()Landroid/content/Context;
    //   36: invokespecial <init> : (Landroid/content/Context;)V
    //   39: aload #6
    //   41: putstatic com/google/firebase/iid/FirebaseInstanceId.zzaj : Lcom/google/firebase/iid/zzaw;
    //   44: ldc com/google/firebase/iid/FirebaseInstanceId
    //   46: monitorexit
    //   47: aload_0
    //   48: aload_1
    //   49: putfield zzam : Lcom/google/firebase/FirebaseApp;
    //   52: aload_0
    //   53: aload_2
    //   54: putfield zzan : Lcom/google/firebase/iid/zzan;
    //   57: aload_0
    //   58: getfield zzao : Lcom/google/firebase/iid/MessagingChannel;
    //   61: ifnonnull -> 113
    //   64: aload_1
    //   65: ldc com/google/firebase/iid/MessagingChannel
    //   67: invokevirtual get : (Ljava/lang/Class;)Ljava/lang/Object;
    //   70: checkcast com/google/firebase/iid/MessagingChannel
    //   73: astore #6
    //   75: aload #6
    //   77: ifnull -> 99
    //   80: aload #6
    //   82: invokeinterface isAvailable : ()Z
    //   87: ifeq -> 99
    //   90: aload_0
    //   91: aload #6
    //   93: putfield zzao : Lcom/google/firebase/iid/MessagingChannel;
    //   96: goto -> 113
    //   99: aload_0
    //   100: new com/google/firebase/iid/zzr
    //   103: dup
    //   104: aload_1
    //   105: aload_2
    //   106: aload_3
    //   107: invokespecial <init> : (Lcom/google/firebase/FirebaseApp;Lcom/google/firebase/iid/zzan;Ljava/util/concurrent/Executor;)V
    //   110: putfield zzao : Lcom/google/firebase/iid/MessagingChannel;
    //   113: aload_0
    //   114: aload_0
    //   115: getfield zzao : Lcom/google/firebase/iid/MessagingChannel;
    //   118: putfield zzao : Lcom/google/firebase/iid/MessagingChannel;
    //   121: aload_0
    //   122: aload #4
    //   124: putfield zzal : Ljava/util/concurrent/Executor;
    //   127: aload_0
    //   128: new com/google/firebase/iid/zzba
    //   131: dup
    //   132: getstatic com/google/firebase/iid/FirebaseInstanceId.zzaj : Lcom/google/firebase/iid/zzaw;
    //   135: invokespecial <init> : (Lcom/google/firebase/iid/zzaw;)V
    //   138: putfield zzaq : Lcom/google/firebase/iid/zzba;
    //   141: aload_0
    //   142: new com/google/firebase/iid/FirebaseInstanceId$zza
    //   145: dup
    //   146: aload_0
    //   147: aload #5
    //   149: invokespecial <init> : (Lcom/google/firebase/iid/FirebaseInstanceId;Lcom/google/firebase/events/Subscriber;)V
    //   152: putfield zzas : Lcom/google/firebase/iid/FirebaseInstanceId$zza;
    //   155: aload_0
    //   156: new com/google/firebase/iid/zzaq
    //   159: dup
    //   160: aload_3
    //   161: invokespecial <init> : (Ljava/util/concurrent/Executor;)V
    //   164: putfield zzap : Lcom/google/firebase/iid/zzaq;
    //   167: aload_0
    //   168: getfield zzas : Lcom/google/firebase/iid/FirebaseInstanceId$zza;
    //   171: invokevirtual isEnabled : ()Z
    //   174: ifeq -> 181
    //   177: aload_0
    //   178: invokespecial zzg : ()V
    //   181: return
    //   182: astore_1
    //   183: ldc com/google/firebase/iid/FirebaseInstanceId
    //   185: monitorexit
    //   186: aload_1
    //   187: athrow
    //   188: new java/lang/IllegalStateException
    //   191: dup
    //   192: ldc 'FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID'
    //   194: invokespecial <init> : (Ljava/lang/String;)V
    //   197: athrow
    // Exception table:
    //   from	to	target	type
    //   19	44	182	finally
    //   44	47	182	finally
    //   183	186	182	finally
  }
  
  public static FirebaseInstanceId getInstance() {
    return getInstance(FirebaseApp.getInstance());
  }
  
  @Keep
  public static FirebaseInstanceId getInstance(FirebaseApp paramFirebaseApp) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/FirebaseInstanceId
    //   2: monitorenter
    //   3: aload_0
    //   4: ldc com/google/firebase/iid/FirebaseInstanceId
    //   6: invokevirtual get : (Ljava/lang/Class;)Ljava/lang/Object;
    //   9: checkcast com/google/firebase/iid/FirebaseInstanceId
    //   12: astore_0
    //   13: ldc com/google/firebase/iid/FirebaseInstanceId
    //   15: monitorexit
    //   16: aload_0
    //   17: areturn
    //   18: astore_0
    //   19: ldc com/google/firebase/iid/FirebaseInstanceId
    //   21: monitorexit
    //   22: aload_0
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	18	finally
  }
  
  private final void startSync() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield zzar : Z
    //   6: ifne -> 14
    //   9: aload_0
    //   10: lconst_0
    //   11: invokevirtual zza : (J)V
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	17	finally
  }
  
  private final Task<InstanceIdResult> zza(String paramString1, String paramString2) {
    String str = zzd(paramString2);
    TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
    this.zzal.execute(new zzn(this, paramString1, paramString2, taskCompletionSource, str));
    return taskCompletionSource.getTask();
  }
  
  private final <T> T zza(Task<T> paramTask) throws IOException {
    try {
      return (T)Tasks.await(paramTask, 30000L, TimeUnit.MILLISECONDS);
    } catch (ExecutionException executionException) {
      Throwable throwable = executionException.getCause();
      if (throwable instanceof IOException) {
        if ("INSTANCE_ID_RESET".equals(throwable.getMessage()))
          zzm(); 
        throw (IOException)throwable;
      } 
      if (throwable instanceof RuntimeException)
        throw (RuntimeException)throwable; 
      throw new IOException(executionException);
    } catch (InterruptedException|java.util.concurrent.TimeoutException interruptedException) {
      throw new IOException("SERVICE_NOT_AVAILABLE");
    } 
  }
  
  static void zza(Runnable paramRunnable, long paramLong) {
    // Byte code:
    //   0: ldc com/google/firebase/iid/FirebaseInstanceId
    //   2: monitorenter
    //   3: getstatic com/google/firebase/iid/FirebaseInstanceId.zzak : Ljava/util/concurrent/ScheduledThreadPoolExecutor;
    //   6: ifnonnull -> 36
    //   9: new java/util/concurrent/ScheduledThreadPoolExecutor
    //   12: astore_3
    //   13: new com/google/android/gms/common/util/concurrent/NamedThreadFactory
    //   16: astore #4
    //   18: aload #4
    //   20: ldc 'FirebaseInstanceId'
    //   22: invokespecial <init> : (Ljava/lang/String;)V
    //   25: aload_3
    //   26: iconst_1
    //   27: aload #4
    //   29: invokespecial <init> : (ILjava/util/concurrent/ThreadFactory;)V
    //   32: aload_3
    //   33: putstatic com/google/firebase/iid/FirebaseInstanceId.zzak : Ljava/util/concurrent/ScheduledThreadPoolExecutor;
    //   36: getstatic com/google/firebase/iid/FirebaseInstanceId.zzak : Ljava/util/concurrent/ScheduledThreadPoolExecutor;
    //   39: aload_0
    //   40: lload_1
    //   41: getstatic java/util/concurrent/TimeUnit.SECONDS : Ljava/util/concurrent/TimeUnit;
    //   44: invokevirtual schedule : (Ljava/lang/Runnable;JLjava/util/concurrent/TimeUnit;)Ljava/util/concurrent/ScheduledFuture;
    //   47: pop
    //   48: ldc com/google/firebase/iid/FirebaseInstanceId
    //   50: monitorexit
    //   51: return
    //   52: astore_0
    //   53: ldc com/google/firebase/iid/FirebaseInstanceId
    //   55: monitorexit
    //   56: aload_0
    //   57: athrow
    // Exception table:
    //   from	to	target	type
    //   3	36	52	finally
    //   36	51	52	finally
    //   53	56	52	finally
  }
  
  private static zzax zzb(String paramString1, String paramString2) {
    return zzaj.zzb("", paramString1, paramString2);
  }
  
  private static String zzd(String paramString) {
    return (paramString.isEmpty() || paramString.equalsIgnoreCase("fcm") || paramString.equalsIgnoreCase("gcm")) ? "*" : paramString;
  }
  
  private final void zzg() {
    zzax zzax = zzj();
    if (!zzo() || zzax == null || zzax.zzj(this.zzan.zzad()) || this.zzaq.zzaq())
      startSync(); 
  }
  
  private static String zzi() {
    return zzan.zza(zzaj.zzg("").getKeyPair());
  }
  
  static boolean zzl() {
    return (Log.isLoggable("FirebaseInstanceId", 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3)));
  }
  
  public String getId() {
    zzg();
    return zzi();
  }
  
  @Deprecated
  public String getToken() {
    zzax zzax = zzj();
    if (zzax == null || zzax.zzj(this.zzan.zzad()))
      startSync(); 
    return (zzax != null) ? zzax.zzbq : null;
  }
  
  public String getToken(String paramString1, String paramString2) throws IOException {
    if (Looper.getMainLooper() != Looper.myLooper())
      return ((InstanceIdResult)zza(zza(paramString1, paramString2))).getToken(); 
    throw new IOException("MAIN_THREAD");
  }
  
  final void zza(long paramLong) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc2_w 30
    //   5: lload_1
    //   6: iconst_1
    //   7: lshl
    //   8: invokestatic max : (JJ)J
    //   11: getstatic com/google/firebase/iid/FirebaseInstanceId.zzai : J
    //   14: invokestatic min : (JJ)J
    //   17: lstore_3
    //   18: new com/google/firebase/iid/zzay
    //   21: astore #5
    //   23: aload #5
    //   25: aload_0
    //   26: aload_0
    //   27: getfield zzan : Lcom/google/firebase/iid/zzan;
    //   30: aload_0
    //   31: getfield zzaq : Lcom/google/firebase/iid/zzba;
    //   34: lload_3
    //   35: invokespecial <init> : (Lcom/google/firebase/iid/FirebaseInstanceId;Lcom/google/firebase/iid/zzan;Lcom/google/firebase/iid/zzba;J)V
    //   38: aload #5
    //   40: lload_1
    //   41: invokestatic zza : (Ljava/lang/Runnable;J)V
    //   44: aload_0
    //   45: iconst_1
    //   46: putfield zzar : Z
    //   49: aload_0
    //   50: monitorexit
    //   51: return
    //   52: astore #5
    //   54: aload_0
    //   55: monitorexit
    //   56: aload #5
    //   58: athrow
    // Exception table:
    //   from	to	target	type
    //   2	49	52	finally
  }
  
  final void zza(boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield zzar : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_2
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_2
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  final void zzb(String paramString) throws IOException {
    zzax zzax = zzj();
    if (zzax != null && !zzax.zzj(this.zzan.zzad())) {
      String str1 = zzi();
      String str2 = zzax.zzbq;
      zza(this.zzao.subscribeToTopic(str1, str2, paramString));
      return;
    } 
    throw new IOException("token not available");
  }
  
  final void zzc(String paramString) throws IOException {
    zzax zzax = zzj();
    if (zzax != null && !zzax.zzj(this.zzan.zzad())) {
      String str = zzi();
      zza(this.zzao.unsubscribeFromTopic(str, zzax.zzbq, paramString));
      return;
    } 
    throw new IOException("token not available");
  }
  
  final FirebaseApp zzh() {
    return this.zzam;
  }
  
  final zzax zzj() {
    return zzb(zzan.zza(this.zzam), "*");
  }
  
  final String zzk() throws IOException {
    return getToken(zzan.zza(this.zzam), "*");
  }
  
  final void zzm() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic com/google/firebase/iid/FirebaseInstanceId.zzaj : Lcom/google/firebase/iid/zzaw;
    //   5: invokevirtual zzal : ()V
    //   8: aload_0
    //   9: getfield zzas : Lcom/google/firebase/iid/FirebaseInstanceId$zza;
    //   12: invokevirtual isEnabled : ()Z
    //   15: ifeq -> 22
    //   18: aload_0
    //   19: invokespecial startSync : ()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	22	25	finally
  }
  
  final boolean zzn() {
    return this.zzao.isAvailable();
  }
  
  final boolean zzo() {
    return this.zzao.isChannelBuilt();
  }
  
  final void zzp() throws IOException {
    String str1 = zzi();
    String str2 = zzax.zza(zzj());
    zza(this.zzao.buildChannel(str1, str2));
  }
  
  final void zzq() {
    zzaj.zzh("");
    startSync();
  }
  
  private final class zza {
    private final boolean zzaz = zzu();
    
    private EventHandler<DataCollectionDefaultChange> zzbb;
    
    private Boolean zzbc = zzt();
    
    final FirebaseInstanceId zzbd;
    
    zza(FirebaseInstanceId this$0, Subscriber param1Subscriber) {
      if (this.zzbc == null && this.zzaz) {
        this.zzbb = new zzq(this);
        param1Subscriber.subscribe(DataCollectionDefaultChange.class, this.zzbb);
      } 
    }
    
    private final Boolean zzt() {
      Context context = FirebaseInstanceId.zza(this.zzbd).getApplicationContext();
      SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.firebase.messaging", 0);
      if (sharedPreferences.contains("auto_init"))
        return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false)); 
      try {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
          ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
          if (applicationInfo != null && applicationInfo.metaData != null && applicationInfo.metaData.containsKey("firebase_messaging_auto_init_enabled")) {
            boolean bool = applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled");
            return Boolean.valueOf(bool);
          } 
        } 
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
      return null;
    }
    
    private final boolean zzu() {
      try {
        Class.forName("com.google.firebase.messaging.FirebaseMessaging");
        return true;
      } catch (ClassNotFoundException classNotFoundException) {
        Context context = FirebaseInstanceId.zza(this.zzbd).getApplicationContext();
        Intent intent = new Intent("com.google.firebase.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        ResolveInfo resolveInfo = context.getPackageManager().resolveService(intent, 0);
        return (resolveInfo != null && resolveInfo.serviceInfo != null);
      } 
    }
    
    final boolean isEnabled() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield zzbc : Ljava/lang/Boolean;
      //   6: ifnull -> 21
      //   9: aload_0
      //   10: getfield zzbc : Ljava/lang/Boolean;
      //   13: invokevirtual booleanValue : ()Z
      //   16: istore_1
      //   17: aload_0
      //   18: monitorexit
      //   19: iload_1
      //   20: ireturn
      //   21: aload_0
      //   22: getfield zzaz : Z
      //   25: ifeq -> 47
      //   28: aload_0
      //   29: getfield zzbd : Lcom/google/firebase/iid/FirebaseInstanceId;
      //   32: invokestatic zza : (Lcom/google/firebase/iid/FirebaseInstanceId;)Lcom/google/firebase/FirebaseApp;
      //   35: invokevirtual isDataCollectionDefaultEnabled : ()Z
      //   38: istore_1
      //   39: iload_1
      //   40: ifeq -> 47
      //   43: aload_0
      //   44: monitorexit
      //   45: iconst_1
      //   46: ireturn
      //   47: aload_0
      //   48: monitorexit
      //   49: iconst_0
      //   50: ireturn
      //   51: astore_2
      //   52: aload_0
      //   53: monitorexit
      //   54: aload_2
      //   55: athrow
      // Exception table:
      //   from	to	target	type
      //   2	17	51	finally
      //   21	39	51	finally
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/FirebaseInstanceId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */