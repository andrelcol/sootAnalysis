package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Message;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.HashMap;

final class zze extends GmsClientSupervisor implements Handler.Callback {
  private final Handler mHandler;
  
  private final HashMap<GmsClientSupervisor.zza, zzf> zzdu = new HashMap<GmsClientSupervisor.zza, zzf>();
  
  private final Context zzdv;
  
  private final ConnectionTracker zzdw;
  
  private final long zzdx;
  
  private final long zzdy;
  
  zze(Context paramContext) {
    this.zzdv = paramContext.getApplicationContext();
    this.mHandler = (Handler)new com.google.android.gms.internal.common.zze(paramContext.getMainLooper(), this);
    this.zzdw = ConnectionTracker.getInstance();
    this.zzdx = 5000L;
    this.zzdy = 300000L;
  }
  
  public final boolean handleMessage(Message paramMessage) {
    int i = paramMessage.what;
    if (i != 0) {
      if (i != 1)
        return false; 
      synchronized (this.zzdu) {
        GmsClientSupervisor.zza zza = (GmsClientSupervisor.zza)paramMessage.obj;
        zzf zzf = this.zzdu.get(zza);
        if (zzf != null && zzf.getState() == 3) {
          String str = String.valueOf(zza);
          i = String.valueOf(str).length();
          StringBuilder stringBuilder = new StringBuilder();
          this(i + 47);
          stringBuilder.append("Timeout waiting for ServiceConnection callback ");
          stringBuilder.append(str);
          stringBuilder.toString();
          ComponentName componentName2 = zzf.getComponentName();
          ComponentName componentName1 = componentName2;
          if (componentName2 == null)
            componentName1 = zza.getComponentName(); 
          componentName2 = componentName1;
          if (componentName1 == null) {
            componentName2 = new ComponentName();
            this(zza.getPackage(), "unknown");
          } 
          zzf.onServiceDisconnected(componentName2);
        } 
        return true;
      } 
    } 
    synchronized (this.zzdu) {
      GmsClientSupervisor.zza zza = (GmsClientSupervisor.zza)paramMessage.obj;
      zzf zzf = this.zzdu.get(zza);
      if (zzf != null && zzf.zzr()) {
        if (zzf.isBound())
          zzf.zzf("GmsClientSupervisor"); 
        this.zzdu.remove(zza);
      } 
      return true;
    } 
  }
  
  protected final boolean zza(GmsClientSupervisor.zza paramzza, ServiceConnection paramServiceConnection, String paramString) {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zzdu) {
      zzf zzf1;
      String str;
      zzf zzf2 = this.zzdu.get(paramzza);
      if (zzf2 == null) {
        zzf2 = new zzf();
        this(this, paramzza);
        zzf2.zza(paramServiceConnection, paramString);
        zzf2.zze(paramString);
        this.zzdu.put(paramzza, zzf2);
        zzf1 = zzf2;
      } else {
        this.mHandler.removeMessages(0, zzf1);
        if (!zzf2.zza(paramServiceConnection)) {
          zzf2.zza(paramServiceConnection, paramString);
          int j = zzf2.getState();
          if (j != 1) {
            if (j != 2) {
              zzf1 = zzf2;
            } else {
              zzf2.zze(paramString);
              zzf1 = zzf2;
            } 
          } else {
            paramServiceConnection.onServiceConnected(zzf2.getComponentName(), zzf2.getBinder());
            zzf1 = zzf2;
          } 
          return zzf1.isBound();
        } 
        IllegalStateException illegalStateException = new IllegalStateException();
        str = String.valueOf(zzf1);
        int i = String.valueOf(str).length();
        StringBuilder stringBuilder = new StringBuilder();
        this(i + 81);
        stringBuilder.append("Trying to bind a GmsServiceConnection that was already connected before.  config=");
        stringBuilder.append(str);
        this(stringBuilder.toString());
        throw illegalStateException;
      } 
      return str.isBound();
    } 
  }
  
  protected final void zzb(GmsClientSupervisor.zza paramzza, ServiceConnection paramServiceConnection, String paramString) {
    Preconditions.checkNotNull(paramServiceConnection, "ServiceConnection must not be null");
    synchronized (this.zzdu) {
      zzf zzf = this.zzdu.get(paramzza);
      if (zzf != null) {
        Message message;
        if (zzf.zza(paramServiceConnection)) {
          zzf.zzb(paramServiceConnection, paramString);
          if (zzf.zzr()) {
            message = this.mHandler.obtainMessage(0, paramzza);
            this.mHandler.sendMessageDelayed(message, this.zzdx);
          } 
          return;
        } 
        IllegalStateException illegalStateException1 = new IllegalStateException();
        paramString = String.valueOf(message);
        int j = String.valueOf(paramString).length();
        stringBuilder = new StringBuilder();
        this(j + 76);
        stringBuilder.append("Trying to unbind a GmsServiceConnection  that was not bound before.  config=");
        stringBuilder.append(paramString);
        this(stringBuilder.toString());
        throw illegalStateException1;
      } 
      IllegalStateException illegalStateException = new IllegalStateException();
      paramString = String.valueOf(stringBuilder);
      int i = String.valueOf(paramString).length();
      StringBuilder stringBuilder = new StringBuilder();
      this(i + 50);
      stringBuilder.append("Nonexistent connection status for service config: ");
      stringBuilder.append(paramString);
      this(stringBuilder.toString());
      throw illegalStateException;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */