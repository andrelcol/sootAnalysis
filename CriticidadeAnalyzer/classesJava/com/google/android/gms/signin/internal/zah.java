package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zah extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zah> CREATOR = new zai();
  
  private final int zalf;
  
  private final ResolveAccountRequest zasd;
  
  zah(int paramInt, ResolveAccountRequest paramResolveAccountRequest) {
    this.zalf = paramInt;
    this.zasd = paramResolveAccountRequest;
  }
  
  public zah(ResolveAccountRequest paramResolveAccountRequest) {
    this(1, paramResolveAccountRequest);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zalf);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.zasd, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/signin/internal/zah.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */