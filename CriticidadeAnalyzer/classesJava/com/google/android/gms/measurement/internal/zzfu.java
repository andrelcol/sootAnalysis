package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzfu extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzfu> CREATOR = new zzfv();
  
  public final String name;
  
  public final String origin;
  
  private final int versionCode;
  
  public final String zzamn;
  
  public final long zzaum;
  
  public final Long zzaun;
  
  public final Double zzaup;
  
  zzfu(int paramInt, String paramString1, long paramLong, Long paramLong1, Float paramFloat, String paramString2, String paramString3, Double paramDouble) {
    this.versionCode = paramInt;
    this.name = paramString1;
    this.zzaum = paramLong;
    this.zzaun = paramLong1;
    if (paramInt == 1) {
      if (paramFloat != null) {
        Double double_ = Double.valueOf(paramFloat.doubleValue());
      } else {
        paramString1 = null;
      } 
      this.zzaup = (Double)paramString1;
    } else {
      this.zzaup = paramDouble;
    } 
    this.zzamn = paramString2;
    this.origin = paramString3;
  }
  
  zzfu(zzfw paramzzfw) {
    this(paramzzfw.name, paramzzfw.zzaum, paramzzfw.value, paramzzfw.origin);
  }
  
  zzfu(String paramString1, long paramLong, Object paramObject, String paramString2) {
    Preconditions.checkNotEmpty(paramString1);
    this.versionCode = 2;
    this.name = paramString1;
    this.zzaum = paramLong;
    this.origin = paramString2;
    if (paramObject == null) {
      this.zzaun = null;
      this.zzaup = null;
      this.zzamn = null;
      return;
    } 
    if (paramObject instanceof Long) {
      this.zzaun = (Long)paramObject;
      this.zzaup = null;
      this.zzamn = null;
      return;
    } 
    if (paramObject instanceof String) {
      this.zzaun = null;
      this.zzaup = null;
      this.zzamn = (String)paramObject;
      return;
    } 
    if (paramObject instanceof Double) {
      this.zzaun = null;
      this.zzaup = (Double)paramObject;
      this.zzamn = null;
      return;
    } 
    throw new IllegalArgumentException("User attribute given of un-supported type");
  }
  
  public final Object getValue() {
    Long long_ = this.zzaun;
    if (long_ != null)
      return long_; 
    Double double_ = this.zzaup;
    if (double_ != null)
      return double_; 
    String str = this.zzamn;
    return (str != null) ? str : null;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.versionCode);
    SafeParcelWriter.writeString(paramParcel, 2, this.name, false);
    SafeParcelWriter.writeLong(paramParcel, 3, this.zzaum);
    SafeParcelWriter.writeLongObject(paramParcel, 4, this.zzaun, false);
    SafeParcelWriter.writeFloatObject(paramParcel, 5, null, false);
    SafeParcelWriter.writeString(paramParcel, 6, this.zzamn, false);
    SafeParcelWriter.writeString(paramParcel, 7, this.origin, false);
    SafeParcelWriter.writeDoubleObject(paramParcel, 8, this.zzaup, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzfu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */