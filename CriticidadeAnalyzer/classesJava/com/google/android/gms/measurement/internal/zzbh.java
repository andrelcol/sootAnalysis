package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;

public final class zzbh {
  private final long zzabv;
  
  private final zzbd zzanw;
  
  private final String zzany;
  
  private final String zzanz;
  
  private final String zzaoa;
  
  private zzbh(zzbd paramzzbd, String paramString, long paramLong) {
    boolean bool;
    Preconditions.checkNotEmpty(paramString);
    if (paramLong > 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool);
    this.zzany = String.valueOf(paramString).concat(":start");
    this.zzanz = String.valueOf(paramString).concat(":count");
    this.zzaoa = String.valueOf(paramString).concat(":value");
    this.zzabv = paramLong;
  }
  
  private final void zzfl() {
    this.zzanw.zzaf();
    long l = this.zzanw.zzbx().currentTimeMillis();
    SharedPreferences.Editor editor = zzbd.zza(this.zzanw).edit();
    editor.remove(this.zzanz);
    editor.remove(this.zzaoa);
    editor.putLong(this.zzany, l);
    editor.apply();
  }
  
  private final long zzfn() {
    return zzbd.zza(this.zzanw).getLong(this.zzany, 0L);
  }
  
  public final void zzc(String paramString, long paramLong) {
    boolean bool;
    this.zzanw.zzaf();
    if (zzfn() == 0L)
      zzfl(); 
    String str = paramString;
    if (paramString == null)
      str = ""; 
    long l = zzbd.zza(this.zzanw).getLong(this.zzanz, 0L);
    if (l <= 0L) {
      SharedPreferences.Editor editor1 = zzbd.zza(this.zzanw).edit();
      editor1.putString(this.zzaoa, str);
      editor1.putLong(this.zzanz, 1L);
      editor1.apply();
      return;
    } 
    paramLong = this.zzanw.zzgr().zzmk().nextLong();
    l++;
    if ((paramLong & Long.MAX_VALUE) < Long.MAX_VALUE / l) {
      bool = true;
    } else {
      bool = false;
    } 
    SharedPreferences.Editor editor = zzbd.zza(this.zzanw).edit();
    if (bool)
      editor.putString(this.zzaoa, str); 
    editor.putLong(this.zzanz, l);
    editor.apply();
  }
  
  public final Pair<String, Long> zzfm() {
    this.zzanw.zzaf();
    this.zzanw.zzaf();
    long l1 = zzfn();
    if (l1 == 0L) {
      zzfl();
      l1 = 0L;
    } else {
      l1 = Math.abs(l1 - this.zzanw.zzbx().currentTimeMillis());
    } 
    long l2 = this.zzabv;
    if (l1 < l2)
      return null; 
    if (l1 > l2 << 1L) {
      zzfl();
      return null;
    } 
    String str = zzbd.zza(this.zzanw).getString(this.zzaoa, null);
    l1 = zzbd.zza(this.zzanw).getLong(this.zzanz, 0L);
    zzfl();
    return (str == null || l1 <= 0L) ? zzbd.zzana : new Pair(str, Long.valueOf(l1));
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzbh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */