package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbf {
  private boolean value;
  
  private final boolean zzanu;
  
  private boolean zzanv;
  
  private final zzbd zzanw;
  
  private final String zzoj;
  
  public zzbf(zzbd paramzzbd, String paramString, boolean paramBoolean) {
    Preconditions.checkNotEmpty(paramString);
    this.zzoj = paramString;
    this.zzanu = paramBoolean;
  }
  
  public final boolean get() {
    if (!this.zzanv) {
      this.zzanv = true;
      this.value = zzbd.zza(this.zzanw).getBoolean(this.zzoj, this.zzanu);
    } 
    return this.value;
  }
  
  public final void set(boolean paramBoolean) {
    SharedPreferences.Editor editor = zzbd.zza(this.zzanw).edit();
    editor.putBoolean(this.zzoj, paramBoolean);
    editor.apply();
    this.value = paramBoolean;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */