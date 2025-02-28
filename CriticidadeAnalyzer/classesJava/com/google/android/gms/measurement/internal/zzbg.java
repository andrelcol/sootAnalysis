package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbg {
  private long value;
  
  private boolean zzanv;
  
  private final zzbd zzanw;
  
  private final long zzanx;
  
  private final String zzoj;
  
  public zzbg(zzbd paramzzbd, String paramString, long paramLong) {
    Preconditions.checkNotEmpty(paramString);
    this.zzoj = paramString;
    this.zzanx = paramLong;
  }
  
  public final long get() {
    if (!this.zzanv) {
      this.zzanv = true;
      this.value = zzbd.zza(this.zzanw).getLong(this.zzoj, this.zzanx);
    } 
    return this.value;
  }
  
  public final void set(long paramLong) {
    SharedPreferences.Editor editor = zzbd.zza(this.zzanw).edit();
    editor.putLong(this.zzoj, paramLong);
    editor.apply();
    this.value = paramLong;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */