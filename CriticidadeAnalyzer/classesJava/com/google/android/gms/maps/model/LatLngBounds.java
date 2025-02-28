package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
  
  public final LatLng northeast;
  
  public final LatLng southwest;
  
  public LatLngBounds(LatLng paramLatLng1, LatLng paramLatLng2) {
    boolean bool;
    Preconditions.checkNotNull(paramLatLng1, "null southwest");
    Preconditions.checkNotNull(paramLatLng2, "null northeast");
    if (paramLatLng2.latitude >= paramLatLng1.latitude) {
      bool = true;
    } else {
      bool = false;
    } 
    Preconditions.checkArgument(bool, "southern latitude exceeds northern latitude (%s > %s)", new Object[] { Double.valueOf(paramLatLng1.latitude), Double.valueOf(paramLatLng2.latitude) });
    this.southwest = paramLatLng1;
    this.northeast = paramLatLng2;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (!(paramObject instanceof LatLngBounds))
      return false; 
    paramObject = paramObject;
    return (this.southwest.equals(((LatLngBounds)paramObject).southwest) && this.northeast.equals(((LatLngBounds)paramObject).northeast));
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { this.southwest, this.northeast });
  }
  
  public final String toString() {
    Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
    toStringHelper.add("southwest", this.southwest);
    toStringHelper.add("northeast", this.northeast);
    return toStringHelper.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeParcelable(paramParcel, 2, (Parcelable)this.southwest, paramInt, false);
    SafeParcelWriter.writeParcelable(paramParcel, 3, (Parcelable)this.northeast, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/maps/model/LatLngBounds.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */