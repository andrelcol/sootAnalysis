package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzad extends AbstractSafeParcelable implements Result {
  public static final Parcelable.Creator<zzad> CREATOR = new zzae();
  
  private final Status zzbl;
  
  public zzad(Status paramStatus) {
    this.zzbl = paramStatus;
  }
  
  public final Status getStatus() {
    return this.zzbl;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 1, (Parcelable)getStatus(), paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  static {
    new zzad(Status.RESULT_SUCCESS);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/internal/location/zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */