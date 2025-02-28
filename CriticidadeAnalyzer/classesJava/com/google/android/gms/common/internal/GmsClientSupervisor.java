package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

public abstract class GmsClientSupervisor {
  private static final Object zzdp = new Object();
  
  private static GmsClientSupervisor zzdq;
  
  public static GmsClientSupervisor getInstance(Context paramContext) {
    synchronized (zzdp) {
      if (zzdq == null) {
        zze zze = new zze();
        this(paramContext.getApplicationContext());
        zzdq = zze;
      } 
      return zzdq;
    } 
  }
  
  public final void zza(String paramString1, String paramString2, int paramInt, ServiceConnection paramServiceConnection, String paramString3) {
    zzb(new zza(paramString1, paramString2, paramInt), paramServiceConnection, paramString3);
  }
  
  protected abstract boolean zza(zza paramzza, ServiceConnection paramServiceConnection, String paramString);
  
  protected abstract void zzb(zza paramzza, ServiceConnection paramServiceConnection, String paramString);
  
  protected static final class zza {
    private final ComponentName mComponentName;
    
    private final String zzdr;
    
    private final String zzds;
    
    private final int zzdt;
    
    public zza(String param1String1, String param1String2, int param1Int) {
      Preconditions.checkNotEmpty(param1String1);
      this.zzdr = param1String1;
      Preconditions.checkNotEmpty(param1String2);
      this.zzds = param1String2;
      this.mComponentName = null;
      this.zzdt = param1Int;
    }
    
    public final boolean equals(Object param1Object) {
      if (this == param1Object)
        return true; 
      if (!(param1Object instanceof zza))
        return false; 
      param1Object = param1Object;
      return (Objects.equal(this.zzdr, ((zza)param1Object).zzdr) && Objects.equal(this.zzds, ((zza)param1Object).zzds) && Objects.equal(this.mComponentName, ((zza)param1Object).mComponentName) && this.zzdt == ((zza)param1Object).zzdt);
    }
    
    public final ComponentName getComponentName() {
      return this.mComponentName;
    }
    
    public final String getPackage() {
      return this.zzds;
    }
    
    public final int hashCode() {
      return Objects.hashCode(new Object[] { this.zzdr, this.zzds, this.mComponentName, Integer.valueOf(this.zzdt) });
    }
    
    public final String toString() {
      String str2 = this.zzdr;
      String str1 = str2;
      if (str2 == null)
        str1 = this.mComponentName.flattenToString(); 
      return str1;
    }
    
    public final Intent zzb(Context param1Context) {
      Intent intent;
      String str = this.zzdr;
      if (str != null) {
        intent = (new Intent(str)).setPackage(this.zzds);
      } else {
        intent = (new Intent()).setComponent(this.mComponentName);
      } 
      return intent;
    }
    
    public final int zzq() {
      return this.zzdt;
    }
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/GmsClientSupervisor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */