package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzo extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzo> CREATOR = new zzp();
  
  public boolean active;
  
  public long creationTimestamp;
  
  public String origin;
  
  public String packageName;
  
  public long timeToLive;
  
  public String triggerEventName;
  
  public long triggerTimeout;
  
  public zzfu zzags;
  
  public zzag zzagt;
  
  public zzag zzagu;
  
  public zzag zzagv;
  
  zzo(zzo paramzzo) {
    Preconditions.checkNotNull(paramzzo);
    this.packageName = paramzzo.packageName;
    this.origin = paramzzo.origin;
    this.zzags = paramzzo.zzags;
    this.creationTimestamp = paramzzo.creationTimestamp;
    this.active = paramzzo.active;
    this.triggerEventName = paramzzo.triggerEventName;
    this.zzagt = paramzzo.zzagt;
    this.triggerTimeout = paramzzo.triggerTimeout;
    this.zzagu = paramzzo.zzagu;
    this.timeToLive = paramzzo.timeToLive;
    this.zzagv = paramzzo.zzagv;
  }
  
  zzo(String paramString1, String paramString2, zzfu paramzzfu, long paramLong1, boolean paramBoolean, String paramString3, zzag paramzzag1, long paramLong2, zzag paramzzag2, long paramLong3, zzag paramzzag3) {
    this.packageName = paramString1;
    this.origin = paramString2;
    this.zzags = paramzzfu;
    this.creationTimestamp = paramLong1;
    this.active = paramBoolean;
    this.triggerEventName = paramString3;
    this.zzagt = paramzzag1;
    this.triggerTimeout = paramLong2;
    this.zzagu = paramzzag2;
    this.timeToLive = paramLong3;
    this.zzagv = paramzzag3;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 2, this.packageName, false);
    SafeParcelWriter.writeString(paramParcel, 3, this.origin, false);
    SafeParcelWriter.writeParcelable(paramParcel, 4, (Parcelable)this.zzags, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 5, this.creationTimestamp);
    SafeParcelWriter.writeBoolean(paramParcel, 6, this.active);
    SafeParcelWriter.writeString(paramParcel, 7, this.triggerEventName, false);
    SafeParcelWriter.writeParcelable(paramParcel, 8, (Parcelable)this.zzagt, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 9, this.triggerTimeout);
    SafeParcelWriter.writeParcelable(paramParcel, 10, (Parcelable)this.zzagu, paramInt, false);
    SafeParcelWriter.writeLong(paramParcel, 11, this.timeToLive);
    SafeParcelWriter.writeParcelable(paramParcel, 12, (Parcelable)this.zzagv, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/measurement/internal/zzo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */