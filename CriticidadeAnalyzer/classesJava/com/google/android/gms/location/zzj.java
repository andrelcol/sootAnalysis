package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzj extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzj> CREATOR = new zzk();
  
  private boolean zzt;
  
  private long zzu;
  
  private float zzv;
  
  private long zzw;
  
  private int zzx;
  
  public zzj() {
    this(true, 50L, 0.0F, Long.MAX_VALUE, 2147483647);
  }
  
  zzj(boolean paramBoolean, long paramLong1, float paramFloat, long paramLong2, int paramInt) {
    this.zzt = paramBoolean;
    this.zzu = paramLong1;
    this.zzv = paramFloat;
    this.zzw = paramLong2;
    this.zzx = paramInt;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof zzj))
      return false; 
    paramObject = paramObject;
    return (this.zzt == ((zzj)paramObject).zzt && this.zzu == ((zzj)paramObject).zzu && Float.compare(this.zzv, ((zzj)paramObject).zzv) == 0 && this.zzw == ((zzj)paramObject).zzw && this.zzx == ((zzj)paramObject).zzx);
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Boolean.valueOf(this.zzt), Long.valueOf(this.zzu), Float.valueOf(this.zzv), Long.valueOf(this.zzw), Integer.valueOf(this.zzx) });
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("DeviceOrientationRequest[mShouldUseMag=");
    stringBuilder.append(this.zzt);
    stringBuilder.append(" mMinimumSamplingPeriodMs=");
    stringBuilder.append(this.zzu);
    stringBuilder.append(" mSmallestAngleChangeRadians=");
    stringBuilder.append(this.zzv);
    long l = this.zzw;
    if (l != Long.MAX_VALUE) {
      long l1 = SystemClock.elapsedRealtime();
      stringBuilder.append(" expireIn=");
      stringBuilder.append(l - l1);
      stringBuilder.append("ms");
    } 
    if (this.zzx != Integer.MAX_VALUE) {
      stringBuilder.append(" num=");
      stringBuilder.append(this.zzx);
    } 
    stringBuilder.append(']');
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeBoolean(paramParcel, 1, this.zzt);
    SafeParcelWriter.writeLong(paramParcel, 2, this.zzu);
    SafeParcelWriter.writeFloat(paramParcel, 3, this.zzv);
    SafeParcelWriter.writeLong(paramParcel, 4, this.zzw);
    SafeParcelWriter.writeInt(paramParcel, 5, this.zzx);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */