package com.google.android.gms.dynamite;

import android.content.Context;

final class zzb implements DynamiteModule.VersionPolicy {
  public final DynamiteModule.VersionPolicy.zzb zza(Context paramContext, String paramString, DynamiteModule.VersionPolicy.zza paramzza) throws DynamiteModule.LoadingException {
    DynamiteModule.VersionPolicy.zzb zzb1 = new DynamiteModule.VersionPolicy.zzb();
    zzb1.zzis = paramzza.zza(paramContext, paramString, true);
    if (zzb1.zzis != 0) {
      zzb1.zzit = 1;
    } else {
      zzb1.zzir = paramzza.getLocalVersion(paramContext, paramString);
      if (zzb1.zzir != 0)
        zzb1.zzit = -1; 
    } 
    return zzb1;
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/dynamite/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */