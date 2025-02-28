package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Preconditions;

public final class zzes implements ServiceConnection, BaseGmsClient.BaseConnectionCallbacks, BaseGmsClient.BaseOnConnectionFailedListener {
  final zzeb zzasl;
  
  private volatile boolean zzasr;
  
  private volatile zzar zzass;
  
  protected zzes(zzeb paramzzeb) {}
  
  public final void onConnected(Bundle paramBundle) {
    Preconditions.checkMainThread("MeasurementServiceConnection.onConnected");
    /* monitor enter ThisExpression{ObjectType{com/google/android/gms/measurement/internal/zzes}} */
    try {
      zzaj zzaj = (zzaj)this.zzass.getService();
      zzbr zzbr = this.zzasl.zzgs();
      zzev zzev = new zzev();
      this(this, zzaj);
      zzbr.zzc(zzev);
    } catch (DeadObjectException|IllegalStateException deadObjectException) {
      this.zzass = null;
      this.zzasr = false;
    } finally {}
    /* monitor exit ThisExpression{ObjectType{com/google/android/gms/measurement/internal/zzes}} */
  }
  
  public final void onConnectionFailed(ConnectionResult paramConnectionResult) {
    // Byte code:
    //   0: ldc 'MeasurementServiceConnection.onConnectionFailed'
    //   2: invokestatic checkMainThread : (Ljava/lang/String;)V
    //   5: aload_0
    //   6: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   9: getfield zzada : Lcom/google/android/gms/measurement/internal/zzbw;
    //   12: invokevirtual zzkj : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 30
    //   20: aload_2
    //   21: invokevirtual zzjj : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   24: ldc 'Service connection failed'
    //   26: aload_1
    //   27: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   30: aload_0
    //   31: monitorenter
    //   32: aload_0
    //   33: iconst_0
    //   34: putfield zzasr : Z
    //   37: aload_0
    //   38: aconst_null
    //   39: putfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   42: aload_0
    //   43: monitorexit
    //   44: aload_0
    //   45: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   48: invokevirtual zzgs : ()Lcom/google/android/gms/measurement/internal/zzbr;
    //   51: new com/google/android/gms/measurement/internal/zzex
    //   54: dup
    //   55: aload_0
    //   56: invokespecial <init> : (Lcom/google/android/gms/measurement/internal/zzes;)V
    //   59: invokevirtual zzc : (Ljava/lang/Runnable;)V
    //   62: return
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    // Exception table:
    //   from	to	target	type
    //   32	44	63	finally
    //   64	66	63	finally
  }
  
  public final void onConnectionSuspended(int paramInt) {
    Preconditions.checkMainThread("MeasurementServiceConnection.onConnectionSuspended");
    this.zzasl.zzgt().zzjn().zzby("Service connection suspended");
    this.zzasl.zzgs().zzc(new zzew(this));
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    // Byte code:
    //   0: ldc 'MeasurementServiceConnection.onServiceConnected'
    //   2: invokestatic checkMainThread : (Ljava/lang/String;)V
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_2
    //   8: ifnonnull -> 38
    //   11: aload_0
    //   12: iconst_0
    //   13: putfield zzasr : Z
    //   16: aload_0
    //   17: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   20: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   23: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   26: ldc 'Service connected with null binder'
    //   28: invokevirtual zzby : (Ljava/lang/String;)V
    //   31: aload_0
    //   32: monitorexit
    //   33: return
    //   34: astore_1
    //   35: goto -> 243
    //   38: aconst_null
    //   39: astore_1
    //   40: aconst_null
    //   41: astore #5
    //   43: aconst_null
    //   44: astore #4
    //   46: aload_1
    //   47: astore_3
    //   48: aload_2
    //   49: invokeinterface getInterfaceDescriptor : ()Ljava/lang/String;
    //   54: astore #6
    //   56: aload_1
    //   57: astore_3
    //   58: ldc 'com.google.android.gms.measurement.internal.IMeasurementService'
    //   60: aload #6
    //   62: invokevirtual equals : (Ljava/lang/Object;)Z
    //   65: ifeq -> 142
    //   68: aload_2
    //   69: ifnonnull -> 78
    //   72: aload #4
    //   74: astore_1
    //   75: goto -> 122
    //   78: aload_1
    //   79: astore_3
    //   80: aload_2
    //   81: ldc 'com.google.android.gms.measurement.internal.IMeasurementService'
    //   83: invokeinterface queryLocalInterface : (Ljava/lang/String;)Landroid/os/IInterface;
    //   88: astore #4
    //   90: aload_1
    //   91: astore_3
    //   92: aload #4
    //   94: instanceof com/google/android/gms/measurement/internal/zzaj
    //   97: ifeq -> 111
    //   100: aload_1
    //   101: astore_3
    //   102: aload #4
    //   104: checkcast com/google/android/gms/measurement/internal/zzaj
    //   107: astore_1
    //   108: goto -> 122
    //   111: aload_1
    //   112: astore_3
    //   113: new com/google/android/gms/measurement/internal/zzal
    //   116: dup
    //   117: aload_2
    //   118: invokespecial <init> : (Landroid/os/IBinder;)V
    //   121: astore_1
    //   122: aload_1
    //   123: astore_3
    //   124: aload_0
    //   125: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   128: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   131: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   134: ldc 'Bound to IMeasurementService interface'
    //   136: invokevirtual zzby : (Ljava/lang/String;)V
    //   139: goto -> 185
    //   142: aload_1
    //   143: astore_3
    //   144: aload_0
    //   145: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   148: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   151: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   154: ldc 'Got binder with a wrong descriptor'
    //   156: aload #6
    //   158: invokevirtual zzg : (Ljava/lang/String;Ljava/lang/Object;)V
    //   161: aload #5
    //   163: astore_1
    //   164: goto -> 185
    //   167: astore_1
    //   168: aload_0
    //   169: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   172: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   175: invokevirtual zzjg : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   178: ldc 'Service connect failed to get IMeasurementService'
    //   180: invokevirtual zzby : (Ljava/lang/String;)V
    //   183: aload_3
    //   184: astore_1
    //   185: aload_1
    //   186: ifnonnull -> 217
    //   189: aload_0
    //   190: iconst_0
    //   191: putfield zzasr : Z
    //   194: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   197: aload_0
    //   198: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   201: invokevirtual getContext : ()Landroid/content/Context;
    //   204: aload_0
    //   205: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   208: invokestatic zza : (Lcom/google/android/gms/measurement/internal/zzeb;)Lcom/google/android/gms/measurement/internal/zzes;
    //   211: invokevirtual unbindService : (Landroid/content/Context;Landroid/content/ServiceConnection;)V
    //   214: goto -> 240
    //   217: aload_0
    //   218: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   221: invokevirtual zzgs : ()Lcom/google/android/gms/measurement/internal/zzbr;
    //   224: astore_2
    //   225: new com/google/android/gms/measurement/internal/zzet
    //   228: astore_3
    //   229: aload_3
    //   230: aload_0
    //   231: aload_1
    //   232: invokespecial <init> : (Lcom/google/android/gms/measurement/internal/zzes;Lcom/google/android/gms/measurement/internal/zzaj;)V
    //   235: aload_2
    //   236: aload_3
    //   237: invokevirtual zzc : (Ljava/lang/Runnable;)V
    //   240: aload_0
    //   241: monitorexit
    //   242: return
    //   243: aload_0
    //   244: monitorexit
    //   245: aload_1
    //   246: athrow
    //   247: astore_1
    //   248: goto -> 240
    // Exception table:
    //   from	to	target	type
    //   11	33	34	finally
    //   48	56	167	android/os/RemoteException
    //   48	56	34	finally
    //   58	68	167	android/os/RemoteException
    //   58	68	34	finally
    //   80	90	167	android/os/RemoteException
    //   80	90	34	finally
    //   92	100	167	android/os/RemoteException
    //   92	100	34	finally
    //   102	108	167	android/os/RemoteException
    //   102	108	34	finally
    //   113	122	167	android/os/RemoteException
    //   113	122	34	finally
    //   124	139	167	android/os/RemoteException
    //   124	139	34	finally
    //   144	161	167	android/os/RemoteException
    //   144	161	34	finally
    //   168	183	34	finally
    //   189	194	34	finally
    //   194	214	247	java/lang/IllegalArgumentException
    //   194	214	34	finally
    //   217	240	34	finally
    //   240	242	34	finally
    //   243	245	34	finally
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    Preconditions.checkMainThread("MeasurementServiceConnection.onServiceDisconnected");
    this.zzasl.zzgt().zzjn().zzby("Service disconnected");
    this.zzasl.zzgs().zzc(new zzeu(this, paramComponentName));
  }
  
  public final void zzb(Intent paramIntent) {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   4: invokevirtual zzaf : ()V
    //   7: aload_0
    //   8: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   11: invokevirtual getContext : ()Landroid/content/Context;
    //   14: astore_3
    //   15: invokestatic getInstance : ()Lcom/google/android/gms/common/stats/ConnectionTracker;
    //   18: astore_2
    //   19: aload_0
    //   20: monitorenter
    //   21: aload_0
    //   22: getfield zzasr : Z
    //   25: ifeq -> 46
    //   28: aload_0
    //   29: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   32: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   35: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   38: ldc 'Connection attempt already in progress'
    //   40: invokevirtual zzby : (Ljava/lang/String;)V
    //   43: aload_0
    //   44: monitorexit
    //   45: return
    //   46: aload_0
    //   47: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   50: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   53: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   56: ldc 'Using local app measurement service'
    //   58: invokevirtual zzby : (Ljava/lang/String;)V
    //   61: aload_0
    //   62: iconst_1
    //   63: putfield zzasr : Z
    //   66: aload_2
    //   67: aload_3
    //   68: aload_1
    //   69: aload_0
    //   70: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   73: invokestatic zza : (Lcom/google/android/gms/measurement/internal/zzeb;)Lcom/google/android/gms/measurement/internal/zzes;
    //   76: sipush #129
    //   79: invokevirtual bindService : (Landroid/content/Context;Landroid/content/Intent;Landroid/content/ServiceConnection;I)Z
    //   82: pop
    //   83: aload_0
    //   84: monitorexit
    //   85: return
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Exception table:
    //   from	to	target	type
    //   21	45	86	finally
    //   46	85	86	finally
    //   87	89	86	finally
  }
  
  public final void zzlk() {
    if (this.zzass != null && (this.zzass.isConnected() || this.zzass.isConnecting()))
      this.zzass.disconnect(); 
    this.zzass = null;
  }
  
  public final void zzll() {
    // Byte code:
    //   0: aload_0
    //   1: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   4: invokevirtual zzaf : ()V
    //   7: aload_0
    //   8: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   11: invokevirtual getContext : ()Landroid/content/Context;
    //   14: astore_1
    //   15: aload_0
    //   16: monitorenter
    //   17: aload_0
    //   18: getfield zzasr : Z
    //   21: ifeq -> 42
    //   24: aload_0
    //   25: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   28: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   31: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   34: ldc 'Connection attempt already in progress'
    //   36: invokevirtual zzby : (Ljava/lang/String;)V
    //   39: aload_0
    //   40: monitorexit
    //   41: return
    //   42: aload_0
    //   43: getfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   46: ifnull -> 87
    //   49: aload_0
    //   50: getfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   53: invokevirtual isConnecting : ()Z
    //   56: ifne -> 69
    //   59: aload_0
    //   60: getfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   63: invokevirtual isConnected : ()Z
    //   66: ifeq -> 87
    //   69: aload_0
    //   70: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   73: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   76: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   79: ldc 'Already awaiting connection attempt'
    //   81: invokevirtual zzby : (Ljava/lang/String;)V
    //   84: aload_0
    //   85: monitorexit
    //   86: return
    //   87: new com/google/android/gms/measurement/internal/zzar
    //   90: astore_2
    //   91: aload_2
    //   92: aload_1
    //   93: invokestatic getMainLooper : ()Landroid/os/Looper;
    //   96: aload_0
    //   97: aload_0
    //   98: invokespecial <init> : (Landroid/content/Context;Landroid/os/Looper;Lcom/google/android/gms/common/internal/BaseGmsClient$BaseConnectionCallbacks;Lcom/google/android/gms/common/internal/BaseGmsClient$BaseOnConnectionFailedListener;)V
    //   101: aload_0
    //   102: aload_2
    //   103: putfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   106: aload_0
    //   107: getfield zzasl : Lcom/google/android/gms/measurement/internal/zzeb;
    //   110: invokevirtual zzgt : ()Lcom/google/android/gms/measurement/internal/zzas;
    //   113: invokevirtual zzjo : ()Lcom/google/android/gms/measurement/internal/zzau;
    //   116: ldc 'Connecting to remote service'
    //   118: invokevirtual zzby : (Ljava/lang/String;)V
    //   121: aload_0
    //   122: iconst_1
    //   123: putfield zzasr : Z
    //   126: aload_0
    //   127: getfield zzass : Lcom/google/android/gms/measurement/internal/zzar;
    //   130: invokevirtual checkAvailabilityAndConnect : ()V
    //   133: aload_0
    //   134: monitorexit
    //   135: return
    //   136: astore_1
    //   137: aload_0
    //   138: monitorexit
    //   139: aload_1
    //   140: athrow
    // Exception table:
    //   from	to	target	type
    //   17	41	136	finally
    //   42	69	136	finally
    //   69	86	136	finally
    //   87	135	136	finally
    //   137	139	136	finally
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */