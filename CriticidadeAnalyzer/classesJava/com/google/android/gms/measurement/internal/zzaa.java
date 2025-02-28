package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public final class zzaa extends zzcs {
  private long zzahq;
  
  private String zzahr;
  
  private Boolean zzahs;
  
  zzaa(zzbw paramzzbw) {
    super(paramzzbw);
  }
  
  protected final boolean zzgy() {
    Calendar calendar = Calendar.getInstance();
    this.zzahq = TimeUnit.MINUTES.convert((calendar.get(15) + calendar.get(16)), TimeUnit.MILLISECONDS);
    Locale locale = Locale.getDefault();
    String str1 = locale.getLanguage().toLowerCase(Locale.ENGLISH);
    String str2 = locale.getCountry().toLowerCase(Locale.ENGLISH);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 1 + String.valueOf(str2).length());
    stringBuilder.append(str1);
    stringBuilder.append("-");
    stringBuilder.append(str2);
    this.zzahr = stringBuilder.toString();
    return false;
  }
  
  public final long zziw() {
    zzcl();
    return this.zzahq;
  }
  
  public final String zzix() {
    zzcl();
    return this.zzahr;
  }
  
  public final boolean zzl(Context paramContext) {
    if (this.zzahs == null) {
      zzgw();
      this.zzahs = Boolean.valueOf(false);
      try {
        PackageManager packageManager = paramContext.getPackageManager();
        if (packageManager != null) {
          packageManager.getPackageInfo("com.google.android.gms", 128);
          this.zzahs = Boolean.valueOf(true);
        } 
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {}
    } 
    return this.zzahs.booleanValue();
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */