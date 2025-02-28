package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationRequest> CREATOR = new zzab();
  
  private int priority = 102;
  
  private long zzaf = Long.MAX_VALUE;
  
  private long zzaw = 3600000L;
  
  private long zzax = 600000L;
  
  private boolean zzay = false;
  
  private float zzaz = 0.0F;
  
  private long zzba = 0L;
  
  private int zzx = Integer.MAX_VALUE;
  
  public LocationRequest() {}
  
  LocationRequest(int paramInt1, long paramLong1, long paramLong2, boolean paramBoolean, long paramLong3, int paramInt2, float paramFloat, long paramLong4) {}
  
  private static void zza(long paramLong) {
    if (paramLong >= 0L)
      return; 
    StringBuilder stringBuilder = new StringBuilder(38);
    stringBuilder.append("invalid interval: ");
    stringBuilder.append(paramLong);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LocationRequest))
      return false; 
    paramObject = paramObject;
    return (this.priority == ((LocationRequest)paramObject).priority && this.zzaw == ((LocationRequest)paramObject).zzaw && this.zzax == ((LocationRequest)paramObject).zzax && this.zzay == ((LocationRequest)paramObject).zzay && this.zzaf == ((LocationRequest)paramObject).zzaf && this.zzx == ((LocationRequest)paramObject).zzx && this.zzaz == ((LocationRequest)paramObject).zzaz && getMaxWaitTime() == paramObject.getMaxWaitTime());
  }
  
  public final long getMaxWaitTime() {
    long l3 = this.zzba;
    long l2 = this.zzaw;
    long l1 = l3;
    if (l3 < l2)
      l1 = l2; 
    return l1;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.priority), Long.valueOf(this.zzaw), Float.valueOf(this.zzaz), Long.valueOf(this.zzba) });
  }
  
  public final LocationRequest setFastestInterval(long paramLong) {
    zza(paramLong);
    this.zzay = true;
    this.zzax = paramLong;
    return this;
  }
  
  public final LocationRequest setInterval(long paramLong) {
    zza(paramLong);
    this.zzaw = paramLong;
    if (!this.zzay)
      this.zzax = (long)(this.zzaw / 6.0D); 
    return this;
  }
  
  public final LocationRequest setPriority(int paramInt) {
    if (paramInt == 100 || paramInt == 102 || paramInt == 104 || paramInt == 105) {
      this.priority = paramInt;
      return this;
    } 
    StringBuilder stringBuilder = new StringBuilder(28);
    stringBuilder.append("invalid quality: ");
    stringBuilder.append(paramInt);
    throw new IllegalArgumentException(stringBuilder.toString());
  }
  
  public final String toString() {
    String str;
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Request[");
    int i = this.priority;
    if (i != 100) {
      if (i != 102) {
        if (i != 104) {
          if (i != 105) {
            str = "???";
          } else {
            str = "PRIORITY_NO_POWER";
          } 
        } else {
          str = "PRIORITY_LOW_POWER";
        } 
      } else {
        str = "PRIORITY_BALANCED_POWER_ACCURACY";
      } 
    } else {
      str = "PRIORITY_HIGH_ACCURACY";
    } 
    stringBuilder.append(str);
    if (this.priority != 105) {
      stringBuilder.append(" requested=");
      stringBuilder.append(this.zzaw);
      stringBuilder.append("ms");
    } 
    stringBuilder.append(" fastest=");
    stringBuilder.append(this.zzax);
    stringBuilder.append("ms");
    if (this.zzba > this.zzaw) {
      stringBuilder.append(" maxWait=");
      stringBuilder.append(this.zzba);
      stringBuilder.append("ms");
    } 
    if (this.zzaz > 0.0F) {
      stringBuilder.append(" smallestDisplacement=");
      stringBuilder.append(this.zzaz);
      stringBuilder.append("m");
    } 
    long l = this.zzaf;
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
    SafeParcelWriter.writeInt(paramParcel, 1, this.priority);
    SafeParcelWriter.writeLong(paramParcel, 2, this.zzaw);
    SafeParcelWriter.writeLong(paramParcel, 3, this.zzax);
    SafeParcelWriter.writeBoolean(paramParcel, 4, this.zzay);
    SafeParcelWriter.writeLong(paramParcel, 5, this.zzaf);
    SafeParcelWriter.writeInt(paramParcel, 6, this.zzx);
    SafeParcelWriter.writeFloat(paramParcel, 7, this.zzaz);
    SafeParcelWriter.writeLong(paramParcel, 8, this.zzba);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/LocationRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */