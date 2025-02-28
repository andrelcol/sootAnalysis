package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public final class zzaj extends AbstractSafeParcelable {
  public static final Parcelable.Creator<zzaj> CREATOR = new zzak();
  
  private final int zzar;
  
  private final int zzas;
  
  private final long zzat;
  
  private final long zzbt;
  
  zzaj(int paramInt1, int paramInt2, long paramLong1, long paramLong2) {
    this.zzas = paramInt1;
    this.zzar = paramInt2;
    this.zzbt = paramLong1;
    this.zzat = paramLong2;
  }
  
  public final boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null && zzaj.class == paramObject.getClass()) {
      paramObject = paramObject;
      if (this.zzas == ((zzaj)paramObject).zzas && this.zzar == ((zzaj)paramObject).zzar && this.zzbt == ((zzaj)paramObject).zzbt && this.zzat == ((zzaj)paramObject).zzat)
        return true; 
    } 
    return false;
  }
  
  public final int hashCode() {
    return Objects.hashCode(new Object[] { Integer.valueOf(this.zzar), Integer.valueOf(this.zzas), Long.valueOf(this.zzat), Long.valueOf(this.zzbt) });
  }
  
  public final String toString() {
    StringBuilder stringBuilder = new StringBuilder("NetworkLocationStatus:");
    stringBuilder.append(" Wifi status: ");
    stringBuilder.append(this.zzas);
    stringBuilder.append(" Cell status: ");
    stringBuilder.append(this.zzar);
    stringBuilder.append(" elapsed time NS: ");
    stringBuilder.append(this.zzat);
    stringBuilder.append(" system time ms: ");
    stringBuilder.append(this.zzbt);
    return stringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeInt(paramParcel, 1, this.zzas);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzar);
    SafeParcelWriter.writeLong(paramParcel, 3, this.zzbt);
    SafeParcelWriter.writeLong(paramParcel, 4, this.zzat);
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/location/zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */