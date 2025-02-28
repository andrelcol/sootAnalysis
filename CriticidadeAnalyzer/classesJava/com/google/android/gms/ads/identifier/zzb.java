package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

public final class zzb {
  private SharedPreferences zzs;
  
  public zzb(Context paramContext) {
    try {
      paramContext = GooglePlayServicesUtilLight.getRemoteContext(paramContext);
      return;
    } finally {
      paramContext = null;
      this.zzs = null;
    } 
  }
  
  public final boolean getBoolean(String paramString, boolean paramBoolean) {
    try {
      return (this.zzs == null) ? false : this.zzs.getBoolean(paramString, false);
    } finally {
      paramString = null;
    } 
  }
  
  final float getFloat(String paramString, float paramFloat) {
    try {
      return (this.zzs == null) ? 0.0F : this.zzs.getFloat(paramString, 0.0F);
    } finally {
      paramString = null;
    } 
  }
  
  final String getString(String paramString1, String paramString2) {
    try {
      return (this.zzs == null) ? paramString2 : this.zzs.getString(paramString1, paramString2);
    } finally {
      paramString1 = null;
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/ads/identifier/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */