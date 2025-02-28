package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public final class LocationAvailability extends AbstractSafeParcelable implements ReflectedParcelable {
  public static final Parcelable.Creator<LocationAvailability> CREATOR = new zzaa();
  
  @Deprecated
  private int zzar;
  
  @Deprecated
  private int zzas;
  
  private long zzat;
  
  private int zzau;
  
  private zzaj[] zzav;
  
  LocationAvailability(int paramInt1, int paramInt2, int paramInt3, long paramLong, zzaj[] paramArrayOfzzaj) {
    this.zzau = paramInt1;
    this.zzar = paramInt2;
    this.zzas = paramInt3;
    this.zzat = paramLong;
    this.zzav = paramArrayOfzzaj;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && LocationAvailability.class == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzar == ((LocationAvailability)paramObject).zzar && this.zzas == ((LocationAvailability)paramObject).zzas && this.zzat == ((LocationAvailability)paramObject).zzat && this.zzau == ((LocationAvailability)paramObject).zzau && Arrays.equals((Object[])this.zzav, (Object[])((LocationAvailability)paramObject).zzav))
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.zzau), Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), this.zzav });
  }
  
  public final boolean isLocationAvailable() {
    return (this.zzau < 1000);
  }
  
  public final String toString() {
    boolean bool = isLocationAvailable();
    StringBuilder stringBuilder = new StringBuilder(48);
    stringBuilder.append("LocationAvailability[isLocationAvailable: ");
    stringBuilder.append(bool);
    stringBuilder.append("]");
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    int i = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzar);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzas);
    SafeParcelWriter.writeLong(paramParcel, 3, this.zzat);
    SafeParcelWriter.writeInt(paramParcel, 4, this.zzau);
    SafeParcelWriter.writeTypedArray(paramParcel, 5, (Parcelable[])this.zzav, paramInt, false);
    SafeParcelWriter.finishObjectHeader(paramParcel, i);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/LocationAvailability.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */