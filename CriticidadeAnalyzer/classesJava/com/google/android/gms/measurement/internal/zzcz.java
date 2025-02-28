package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

public final class zzcz {
  boolean zzadg = true;
  
  String zzadi;
  
  String zzapk;
  
  String zzapl;
  
  Boolean zzaqe;
  
  zzan zzaqz;
  
  final Context zzri;
  
  public zzcz(Context paramContext, zzan paramzzan) {
    Preconditions.checkNotNull(paramContext);
    paramContext = paramContext.getApplicationContext();
    Preconditions.checkNotNull(paramContext);
    this.zzri = paramContext;
    if (paramzzan != null) {
      this.zzaqz = paramzzan;
      this.zzadi = paramzzan.zzadi;
      this.zzapk = paramzzan.origin;
      this.zzapl = paramzzan.zzadh;
      this.zzadg = paramzzan.zzadg;
      Bundle bundle = paramzzan.zzadj;
      if (bundle != null)
        this.zzaqe = Boolean.valueOf(bundle.getBoolean("dataCollectionDefaultEnabled", true)); 
    } 
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzcz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */