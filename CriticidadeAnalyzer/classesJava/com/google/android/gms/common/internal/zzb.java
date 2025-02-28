package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzb extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzb> CREATOR = new zzc();
  
  Bundle zzda;
  
  Feature[] zzdb;
  
  public zzb() {}
  
  zzb(Bundle paramBundle, Feature[] paramArrayOfFeature) {
    this.zzda = paramBundle;
    this.zzdb = paramArrayOfFeature;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeBundle(paramParcel, 1, this.zzda, false);
    SafeParcelWriter.writeTypedArray(paramParcel, 2, (Parcelable[])this.zzdb, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/internal/zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */