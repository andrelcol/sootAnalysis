package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.internal.measurement.zzu;
import com.google.android.gms.internal.measurement.zzv;

public final class zzbk implements ServiceConnection {
  private final String packageName;
  
  final zzbj zzaoc;
  
  zzbk(zzbj paramzzbj, String paramString) {
    this.packageName = paramString;
  }
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
    if (paramIBinder == null) {
      this.zzaoc.zzada.zzgt().zzjj().zzby("Install Referrer connection returned with null binder");
      return;
    } 
    try {
      zzu zzu = zzv.zza(paramIBinder);
      if (zzu == null) {
        this.zzaoc.zzada.zzgt().zzjj().zzby("Install Referrer Service implementation was not found");
        return;
      } 
      this.zzaoc.zzada.zzgt().zzjm().zzby("Install Referrer Service connected");
      zzbr zzbr = this.zzaoc.zzada.zzgs();
      zzbl zzbl = new zzbl();
      this(this, zzu, this);
      zzbr.zzc(zzbl);
      return;
    } catch (Exception exception) {
      this.zzaoc.zzada.zzgt().zzjj().zzg("Exception occurred while calling Install Referrer API", exception);
      return;
    } 
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName) {
    this.zzaoc.zzada.zzgt().zzjm().zzby("Install Referrer Service disconnected");
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */