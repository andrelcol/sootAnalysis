package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzag extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzag> CREATOR = new zzah();
  
  public final String name;
  
  public final String origin;
  
  public final zzad zzahu;
  
  public final long zzaig;
  
  zzag(zzag paramzzag, long paramLong) {
    Preconditions.checkNotNull(paramzzag);
    this.name = paramzzag.name;
    this.zzahu = paramzzag.zzahu;
    this.origin = paramzzag.origin;
    this.zzaig = paramLong;
  }
  
  public zzag(String paramString1, zzad paramzzad, String paramString2, long paramLong) {
    this.name = paramString1;
    this.zzahu = paramzzad;
    this.origin = paramString2;
    this.zzaig = paramLong;
  }
  
  public final String toString() {
    String str1 = this.origin;
    String str2 = this.name;
    String str3 = String.valueOf(this.zzahu);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str1).length() + 21 + String.valueOf(str2).length() + String.valueOf(str3).length());
    stringBuilder.append("origin=");
    stringBuilder.append(str1);
    stringBuilder.append(",name=");
    stringBuilder.append(str2);
    stringBuilder.append(",params=");
    stringBuilder.append(str3);
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, this.name, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.zzahu, paramInt, false);
    SafeParcelWriter.writeString(paramParcel, 4, this.origin, false);
    SafeParcelWriter.writeLong(paramParcel, 5, this.zzaig);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzag.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */