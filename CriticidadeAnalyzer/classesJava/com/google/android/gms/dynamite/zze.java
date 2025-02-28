package com.google.android.gms.dynamite;

import android.content.Context;

final class zze implements DynamiteModule.VersionPolicy {
  public final DynamiteModule.VersionPolicy.zzb zza(Context paramContext, String paramString, DynamiteModule.VersionPolicy.zza paramzza) throws DynamiteModule.LoadingException {
    DynamiteModule.VersionPolicy.zzb zzb = new DynamiteModule.VersionPolicy.zzb();
    zzb.zzir = paramzza.getLocalVersion(paramContext, paramString);
    if (zzb.zzir != 0) {
      zzb.zzis = paramzza.zza(paramContext, paramString, false);
    } else {
      zzb.zzis = paramzza.zza(paramContext, paramString, true);
    } 
    if (zzb.zzir == 0 && zzb.zzis == 0) {
      zzb.zzit = 0;
    } else if (zzb.zzir >= zzb.zzis) {
      zzb.zzit = -1;
    } else {
      zzb.zzit = 1;
    } 
    return zzb;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamite/zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */