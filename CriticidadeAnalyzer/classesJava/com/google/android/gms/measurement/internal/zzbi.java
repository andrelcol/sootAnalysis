package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbi {
  private String value;
  
  private boolean zzanv;
  
  private final zzbd zzanw;
  
  private final String zzoj;
  
  public zzbi(zzbd paramzzbd, String paramString1, String paramString2) {
    Preconditions.checkNotEmpty(paramString1);
    this.zzoj = paramString1;
  }
  
  public final void zzcd(String paramString) {
    if (zzfx.zzv(paramString, this.value))
      return; 
    SharedPreferences.Editor editor = zzbd.zza(this.zzanw).edit();
    editor.putString(this.zzoj, paramString);
    editor.apply();
    this.value = paramString;
  }
  
  public final String zzkd() {
    if (!this.zzanv) {
      this.zzanv = true;
      this.value = zzbd.zza(this.zzanw).getString(this.zzoj, null);
    } 
    return this.value;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */