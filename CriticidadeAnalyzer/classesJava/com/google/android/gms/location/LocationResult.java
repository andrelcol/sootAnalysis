package com.google.android.gms.location;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationResult> CREATOR;
  
  static final List<Location> zzbb = Collections.emptyList();
  
  private final List<Location> zzbc;
  
  static {
    CREATOR = new zzac();
  }
  
  LocationResult(List<Location> paramList) {
    this.zzbc = paramList;
  }
  
  public final boolean equals(Object<Location> paramObject) {
    if (!(paramObject instanceof LocationResult))
      return false; 
    paramObject = paramObject;
    if (((LocationResult)paramObject).zzbc.size() != this.zzbc.size())
      return false; 
    paramObject = (Object<Location>)((LocationResult)paramObject).zzbc.iterator();
    Iterator<Location> iterator = this.zzbc.iterator();
    while (paramObject.hasNext()) {
      Location location1 = iterator.next();
      Location location2 = paramObject.next();
      if (location1.getTime() != location2.getTime())
        return false; 
    } 
    return true;
  }
  
  public final List<Location> getLocations() {
    return this.zzbc;
  }
  
  public final int hashCode() {
    Iterator<Location> iterator = this.zzbc.iterator();
    int i;
    for (i = 17; iterator.hasNext(); i = i * 31 + (int)(l ^ l >>> 32L))
      long l = ((Location)iterator.next()).getTime(); 
    return i;
  }
  
  public final String toString() {
    String str = String.valueOf(this.zzbc);
    StringBuilder stringBuilder = new StringBuilder(String.valueOf(str).length() + 27);
    stringBuilder.append("LocationResult[locations: ");
    stringBuilder.append(str);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeTypedList(paramParcel, 1, getLocations(), false);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/LocationResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */