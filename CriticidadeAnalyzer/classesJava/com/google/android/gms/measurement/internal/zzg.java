package com.google.android.gms.measurement.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;

final class zzg {
  private final zzbw zzada;
  
  private long zzade;
  
  private String zzafh;
  
  private String zzafi;
  
  private String zzafj;
  
  private String zzafk;
  
  private long zzafl;
  
  private long zzafm;
  
  private long zzafn;
  
  private long zzafo;
  
  private String zzafp;
  
  private long zzafq;
  
  private boolean zzafr;
  
  private long zzafs;
  
  private boolean zzaft;
  
  private boolean zzafu;
  
  private String zzafv;
  
  private long zzafw;
  
  private long zzafx;
  
  private long zzafy;
  
  private long zzafz;
  
  private long zzaga;
  
  private long zzagb;
  
  private String zzagc;
  
  private boolean zzagd;
  
  private long zzage;
  
  private long zzagf;
  
  private String zzts;
  
  private final String zztt;
  
  zzg(zzbw paramzzbw, String paramString) {
    Preconditions.checkNotNull(paramzzbw);
    Preconditions.checkNotEmpty(paramString);
    this.zzada = paramzzbw;
    this.zztt = paramString;
    this.zzada.zzgs().zzaf();
  }
  
  public final String getAppInstanceId() {
    this.zzada.zzgs().zzaf();
    return this.zzafh;
  }
  
  public final String getFirebaseInstanceId() {
    this.zzada.zzgs().zzaf();
    return this.zzafk;
  }
  
  public final String getGmpAppId() {
    this.zzada.zzgs().zzaf();
    return this.zzafi;
  }
  
  public final boolean isMeasurementEnabled() {
    this.zzada.zzgs().zzaf();
    return this.zzafr;
  }
  
  public final void setAppVersion(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzts, paramString) ^ true;
    this.zzts = paramString;
  }
  
  public final void setMeasurementEnabled(boolean paramBoolean) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafr != paramBoolean) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafr = paramBoolean;
  }
  
  public final void zzaa(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzagb != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzagb = paramLong;
  }
  
  public final void zzab(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzaga != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzaga = paramLong;
  }
  
  public final void zzac(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafs != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafs = paramLong;
  }
  
  public final void zzaj(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzafh, paramString) ^ true;
    this.zzafh = paramString;
  }
  
  public final String zzak() {
    this.zzada.zzgs().zzaf();
    return this.zzts;
  }
  
  public final void zzak(String paramString) {
    this.zzada.zzgs().zzaf();
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
      str = null; 
    this.zzagd |= zzfx.zzv(this.zzafi, str) ^ true;
    this.zzafi = str;
  }
  
  public final String zzal() {
    this.zzada.zzgs().zzaf();
    return this.zztt;
  }
  
  public final void zzal(String paramString) {
    this.zzada.zzgs().zzaf();
    String str = paramString;
    if (TextUtils.isEmpty(paramString))
      str = null; 
    this.zzagd |= zzfx.zzv(this.zzafv, str) ^ true;
    this.zzafv = str;
  }
  
  public final void zzam(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzafj, paramString) ^ true;
    this.zzafj = paramString;
  }
  
  public final void zzan(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzafk, paramString) ^ true;
    this.zzafk = paramString;
  }
  
  public final void zzao(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzafp, paramString) ^ true;
    this.zzafp = paramString;
  }
  
  public final void zzap(String paramString) {
    this.zzada.zzgs().zzaf();
    this.zzagd |= zzfx.zzv(this.zzagc, paramString) ^ true;
    this.zzagc = paramString;
  }
  
  public final void zze(boolean paramBoolean) {
    boolean bool;
    this.zzada.zzgs().zzaf();
    if (this.zzaft != paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.zzagd = bool;
    this.zzaft = paramBoolean;
  }
  
  public final void zzf(boolean paramBoolean) {
    boolean bool;
    this.zzada.zzgs().zzaf();
    if (this.zzafu != paramBoolean) {
      bool = true;
    } else {
      bool = false;
    } 
    this.zzagd = bool;
    this.zzafu = paramBoolean;
  }
  
  public final void zzha() {
    this.zzada.zzgs().zzaf();
    this.zzagd = false;
  }
  
  public final String zzhb() {
    this.zzada.zzgs().zzaf();
    return this.zzafv;
  }
  
  public final String zzhc() {
    this.zzada.zzgs().zzaf();
    return this.zzafj;
  }
  
  public final long zzhd() {
    this.zzada.zzgs().zzaf();
    return this.zzafm;
  }
  
  public final long zzhe() {
    this.zzada.zzgs().zzaf();
    return this.zzafn;
  }
  
  public final long zzhf() {
    this.zzada.zzgs().zzaf();
    return this.zzafo;
  }
  
  public final String zzhg() {
    this.zzada.zzgs().zzaf();
    return this.zzafp;
  }
  
  public final long zzhh() {
    this.zzada.zzgs().zzaf();
    return this.zzade;
  }
  
  public final long zzhi() {
    this.zzada.zzgs().zzaf();
    return this.zzafq;
  }
  
  public final long zzhj() {
    this.zzada.zzgs().zzaf();
    return this.zzafl;
  }
  
  public final long zzhk() {
    this.zzada.zzgs().zzaf();
    return this.zzage;
  }
  
  public final long zzhl() {
    this.zzada.zzgs().zzaf();
    return this.zzagf;
  }
  
  public final void zzhm() {
    this.zzada.zzgs().zzaf();
    long l2 = this.zzafl + 1L;
    long l1 = l2;
    if (l2 > 2147483647L) {
      this.zzada.zzgt().zzjj().zzg("Bundle index overflow. appId", zzas.zzbw(this.zztt));
      l1 = 0L;
    } 
    this.zzagd = true;
    this.zzafl = l1;
  }
  
  public final long zzhn() {
    this.zzada.zzgs().zzaf();
    return this.zzafw;
  }
  
  public final long zzho() {
    this.zzada.zzgs().zzaf();
    return this.zzafx;
  }
  
  public final long zzhp() {
    this.zzada.zzgs().zzaf();
    return this.zzafy;
  }
  
  public final long zzhq() {
    this.zzada.zzgs().zzaf();
    return this.zzafz;
  }
  
  public final long zzhr() {
    this.zzada.zzgs().zzaf();
    return this.zzagb;
  }
  
  public final long zzhs() {
    this.zzada.zzgs().zzaf();
    return this.zzaga;
  }
  
  public final String zzht() {
    this.zzada.zzgs().zzaf();
    return this.zzagc;
  }
  
  public final String zzhu() {
    this.zzada.zzgs().zzaf();
    String str = this.zzagc;
    zzap(null);
    return str;
  }
  
  public final long zzhv() {
    this.zzada.zzgs().zzaf();
    return this.zzafs;
  }
  
  public final boolean zzhw() {
    this.zzada.zzgs().zzaf();
    return this.zzaft;
  }
  
  public final boolean zzhx() {
    this.zzada.zzgs().zzaf();
    return this.zzafu;
  }
  
  public final void zzo(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafm != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafm = paramLong;
  }
  
  public final void zzp(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafn != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafn = paramLong;
  }
  
  public final void zzq(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafo != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafo = paramLong;
  }
  
  public final void zzr(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzade != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzade = paramLong;
  }
  
  public final void zzs(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafq != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafq = paramLong;
  }
  
  public final void zzt(long paramLong) {
    boolean bool1 = true;
    if (paramLong >= 0L) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    Preconditions.checkArgument(bool2);
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafl == paramLong)
      bool1 = false; 
    this.zzagd = bool1 | bool2;
    this.zzafl = paramLong;
  }
  
  public final void zzu(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzage != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzage = paramLong;
  }
  
  public final void zzv(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzagf != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzagf = paramLong;
  }
  
  public final void zzw(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafw != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafw = paramLong;
  }
  
  public final void zzx(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafx != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafx = paramLong;
  }
  
  public final void zzy(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafy != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafy = paramLong;
  }
  
  public final void zzz(long paramLong) {
    boolean bool1;
    this.zzada.zzgs().zzaf();
    boolean bool2 = this.zzagd;
    if (this.zzafz != paramLong) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    this.zzagd = bool2 | bool1;
    this.zzafz = paramLong;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */