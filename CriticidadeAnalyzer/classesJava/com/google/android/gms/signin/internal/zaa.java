package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zaa extends AbstractSafeParcelable implements Result {
  public static final Parcelable.Creator<zaa> CREATOR = new zab();
  
  private final int zalf;
  
  private int zarz;
  
  private Intent zasa;
  
  public zaa() {
    this(0, null);
  }
  
  zaa(int paramInt1, int paramInt2, Intent paramIntent) {
    this.zalf = paramInt1;
    this.zarz = paramInt2;
    this.zasa = paramIntent;
  }
  
  private zaa(int paramInt, Intent paramIntent) {
    this(2, 0, null);
  }
  
  public final Status getStatus() {
    return (this.zarz == 0) ? Status.RESULT_SUCCESS : Status.RESULT_CANCELED;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zalf);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zarz);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.zasa, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/signin/internal/zaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */