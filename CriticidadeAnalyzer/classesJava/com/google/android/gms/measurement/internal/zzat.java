package com.google.android.gms.measurement.internal;

final class zzat implements Runnable {
  private final int zzamf;
  
  private final String zzamg;
  
  private final Object zzamh;
  
  private final Object zzami;
  
  private final Object zzamj;
  
  private final zzas zzamk;
  
  zzat(zzas paramzzas, int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3) {}
  
  public final void run() {
    zzbd zzbd = this.zzamk.zzada.zzgu();
    if (!zzbd.isInitialized()) {
      this.zzamk.zza(6, "Persisted config not initialized. Not logging error/warn");
      return;
    } 
    if (zzas.zza(this.zzamk) == '\000')
      if (this.zzamk.zzgv().zzdw()) {
        zzas zzas1 = this.zzamk;
        zzas1.zzgw();
        zzas.zza(zzas1, 'C');
      } else {
        zzas zzas1 = this.zzamk;
        zzas1.zzgw();
        zzas.zza(zzas1, 'c');
      }  
    if (zzas.zzb(this.zzamk) < 0L) {
      zzas zzas1 = this.zzamk;
      zzas.zza(zzas1, zzas1.zzgv().zzhh());
    } 
    char c1 = "01VDIWEA?".charAt(this.zzamf);
    char c2 = zzas.zza(this.zzamk);
    long l = zzas.zzb(this.zzamk);
    String str2 = zzas.zza(true, this.zzamg, this.zzamh, this.zzami, this.zzamj);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str2).length() + 24);
    stringBuilder.append("2");
    stringBuilder.append(c1);
    stringBuilder.append(c2);
    stringBuilder.append(l);
    stringBuilder.append(":");
    stringBuilder.append(str2);
    str2 = stringBuilder.toString();
    String str1 = str2;
    if (str2.length() > 1024)
      str1 = this.zzamg.substring(0, 1024); 
    zzbd.zzanb.zzc(str1, 1L);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */