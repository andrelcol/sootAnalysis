package com.google.firebase.iid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.legacy.content.WakefulBroadcastReceiver;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public abstract class zzb extends Service {
  private final Object lock;
  
  final ExecutorService zzi;
  
  private Binder zzj;
  
  private int zzk;
  
  private int zzl;
  
  public zzb() {
    String str = String.valueOf(getClass().getSimpleName());
    if (str.length() != 0) {
      str = "Firebase-".concat(str);
    } else {
      str = new String("Firebase-");
    } 
    this.zzi = Executors.newSingleThreadExecutor((ThreadFactory)new NamedThreadFactory(str));
    this.lock = new Object();
    this.zzl = 0;
  }
  
  private final void zza(Intent paramIntent) {
    if (paramIntent != null)
      WakefulBroadcastReceiver.completeWakefulIntent(paramIntent); 
    synchronized (this.lock) {
      this.zzl--;
      if (this.zzl == 0)
        stopSelfResult(this.zzk); 
      return;
    } 
  }
  
  public final IBinder onBind(Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: ldc 'EnhancedIntentService'
    //   4: iconst_3
    //   5: invokestatic isLoggable : (Ljava/lang/String;I)Z
    //   8: pop
    //   9: aload_0
    //   10: getfield zzj : Landroid/os/Binder;
    //   13: ifnonnull -> 30
    //   16: new com/google/firebase/iid/zzf
    //   19: astore_1
    //   20: aload_1
    //   21: aload_0
    //   22: invokespecial <init> : (Lcom/google/firebase/iid/zzb;)V
    //   25: aload_0
    //   26: aload_1
    //   27: putfield zzj : Landroid/os/Binder;
    //   30: aload_0
    //   31: getfield zzj : Landroid/os/Binder;
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: areturn
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	39	finally
    //   30	35	39	finally
  }
  
  public final int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2) {
    synchronized (this.lock) {
      this.zzk = paramInt2;
      this.zzl++;
      null = zzb(paramIntent);
      if (null == null) {
        zza(paramIntent);
        return 2;
      } 
      if (zzc((Intent)null)) {
        zza(paramIntent);
        return 2;
      } 
      this.zzi.execute(new zzc(this, (Intent)null, paramIntent));
      return 3;
    } 
  }
  
  protected abstract Intent zzb(Intent paramIntent);
  
  public boolean zzc(Intent paramIntent) {
    return false;
  }
  
  public abstract void zzd(Intent paramIntent);
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/firebase/iid/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */