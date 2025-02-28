package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;

public final class zza extends zze {
  private final Map<String, Long> zzafb = (Map<String, Long>)new ArrayMap();
  
  private final Map<String, Integer> zzafc = (Map<String, Integer>)new ArrayMap();
  
  private long zzafd;
  
  public zza(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  private final void zza(long paramLong, zzdx paramzzdx) {
    if (paramzzdx == null) {
      zzgt().zzjo().zzby("Not logging ad exposure. No active activity");
      return;
    } 
    if (paramLong < 1000L) {
      zzgt().zzjo().zzg("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(paramLong));
      return;
    } 
    Bundle bundle = new Bundle();
    bundle.putLong("_xt", paramLong);
    zzdy.zza(paramzzdx, bundle, true);
    zzgj().logEvent("am", "_xa", bundle);
  }
  
  private final void zza(String paramString, long paramLong) {
    zzgg();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    if (this.zzafc.isEmpty())
      this.zzafd = paramLong; 
    Integer integer = this.zzafc.get(paramString);
    if (integer != null) {
      this.zzafc.put(paramString, Integer.valueOf(integer.intValue() + 1));
      return;
    } 
    if (this.zzafc.size() >= 100) {
      zzgt().zzjj().zzby("Too many ads visible");
      return;
    } 
    this.zzafc.put(paramString, Integer.valueOf(1));
    this.zzafb.put(paramString, Long.valueOf(paramLong));
  }
  
  private final void zza(String paramString, long paramLong, zzdx paramzzdx) {
    if (paramzzdx == null) {
      zzgt().zzjo().zzby("Not logging ad unit exposure. No active activity");
      return;
    } 
    if (paramLong < 1000L) {
      zzgt().zzjo().zzg("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(paramLong));
      return;
    } 
    Bundle bundle = new Bundle();
    bundle.putString("_ai", paramString);
    bundle.putLong("_xt", paramLong);
    zzdy.zza(paramzzdx, bundle, true);
    zzgj().logEvent("am", "_xu", bundle);
  }
  
  private final void zzb(String paramString, long paramLong) {
    zzgg();
    zzaf();
    Preconditions.checkNotEmpty(paramString);
    Integer integer = this.zzafc.get(paramString);
    if (integer != null) {
      zzdx zzdx = zzgm().zzle();
      int i = integer.intValue() - 1;
      if (i == 0) {
        this.zzafc.remove(paramString);
        Long long_ = this.zzafb.get(paramString);
        if (long_ == null) {
          zzgt().zzjg().zzby("First ad unit exposure time was never set");
        } else {
          long l = long_.longValue();
          this.zzafb.remove(paramString);
          zza(paramString, paramLong - l, zzdx);
        } 
        if (this.zzafc.isEmpty()) {
          long l = this.zzafd;
          if (l == 0L) {
            zzgt().zzjg().zzby("First ad exposure time was never set");
            return;
          } 
          zza(paramLong - l, zzdx);
          this.zzafd = 0L;
        } 
        return;
      } 
      this.zzafc.put(paramString, Integer.valueOf(i));
      return;
    } 
    zzgt().zzjg().zzg("Call to endAdUnitExposure for unknown ad unit id", paramString);
  }
  
  private final void zzn(long paramLong) {
    for (String str : this.zzafb.keySet())
      this.zzafb.put(str, Long.valueOf(paramLong)); 
    if (!this.zzafb.isEmpty())
      this.zzafd = paramLong; 
  }
  
  public final void beginAdUnitExposure(String paramString, long paramLong) {
    if (paramString == null || paramString.length() == 0) {
      zzgt().zzjg().zzby("Ad unit id must be a non-empty string");
      return;
    } 
    zzgs().zzc(new zzb(this, paramString, paramLong));
  }
  
  public final void endAdUnitExposure(String paramString, long paramLong) {
    if (paramString == null || paramString.length() == 0) {
      zzgt().zzjg().zzby("Ad unit id must be a non-empty string");
      return;
    } 
    zzgs().zzc(new zzc(this, paramString, paramLong));
  }
  
  public final void zzm(long paramLong) {
    zzdx zzdx = zzgm().zzle();
    for (String str : this.zzafb.keySet())
      zza(str, paramLong - ((Long)this.zzafb.get(str)).longValue(), zzdx); 
    if (!this.zzafb.isEmpty())
      zza(paramLong - this.zzafd, zzdx); 
    zzn(paramLong);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */