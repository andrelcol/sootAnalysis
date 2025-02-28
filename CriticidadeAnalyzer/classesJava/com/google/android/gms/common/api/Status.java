package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
  public static final Parcelable.Creator<Status> CREATOR;
  
  public static final Status RESULT_CANCELED;
  
  public static final Status RESULT_INTERNAL_ERROR;
  
  public static final Status RESULT_SUCCESS = new Status(0);
  
  public static final Status RESULT_TIMEOUT;
  
  private final int zzg;
  
  private final int zzh;
  
  private final PendingIntent zzi;
  
  private final String zzj;
  
  static {
    new Status(14);
    RESULT_INTERNAL_ERROR = new Status(8);
    RESULT_TIMEOUT = new Status(15);
    RESULT_CANCELED = new Status(16);
    new Status(17);
    new Status(18);
    CREATOR = new zzb();
  }
  
  public Status(int paramInt) {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, String paramString, PendingIntent paramPendingIntent) {
    this.zzg = paramInt1;
    this.zzh = paramInt2;
    this.zzj = paramString;
    this.zzi = paramPendingIntent;
  }
  
  public Status(int paramInt, String paramString) {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, String paramString, PendingIntent paramPendingIntent) {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  public final boolean equals(Object paramObject) {
    if (!(paramObject instanceof Status))
      return false; 
    paramObject = paramObject;
    return (this.zzg == ((Status)paramObject).zzg && this.zzh == ((Status)paramObject).zzh && Objects.equal(this.zzj, ((Status)paramObject).zzj) && Objects.equal(this.zzi, ((Status)paramObject).zzi));
  }
  
  public final Status getStatus() {
    return this;
  }
  
  public final int getStatusCode() {
    return this.zzh;
  }
  
  public final String getStatusMessage() {
    return this.zzj;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.zzg), Integer.valueOf(this.zzh), this.zzj, this.zzi });
  }
  
  public final boolean isSuccess() {
    return (this.zzh <= 0);
  }
  
  public final String toString() {
    Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
    toStringHelper.add("statusCode", zzg());
    toStringHelper.add("resolution", this.zzi);
    return toStringHelper.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, getStatusCode());
    SafeParcelWriter.writeString(paramParcel, 2, getStatusMessage(), false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.zzi, paramInt, false);
    SafeParcelWriter.writeInt(paramParcel, 1000, this.zzg);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
  
  public final String zzg() {
    String str = this.zzj;
    return (str != null) ? str : CommonStatusCodes.getStatusCodeString(this.zzh);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/api/Status.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */