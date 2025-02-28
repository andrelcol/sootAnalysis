package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class Feature extends AbstractSafeParcelable {
  public static final Parcelable.Creator<Feature> CREATOR = new zzb();
  
  private final String name;
  
  @Deprecated
  private final int zzk;
  
  private final long zzl;
  
  public Feature(String paramString, int paramInt, long paramLong) {
    this.name = paramString;
    this.zzk = paramInt;
    this.zzl = paramLong;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject instanceof Feature) {
      paramObject = paramObject;
      if (((getName() != null && getName().equals(paramObject.getName())) || (getName() == null && paramObject.getName() == null)) && getVersion() == paramObject.getVersion())
        return true; 
    } 
    return false;
  }
  
  public String getName() {
    return this.name;
  }
  
  public long getVersion() {
    long l2 = this.zzl;
    long l1 = l2;
    if (l2 == -1L)
      l1 = this.zzk; 
    return l1;
  }
  
  public int hashCode() {
    return Objects.hashCode(new Object[] { getName(), Long.valueOf(getVersion()) });
  }
  
  public String toString() {
    Objects.ToStringHelper toStringHelper = Objects.toStringHelper(this);
    toStringHelper.add("name", getName());
    toStringHelper.add("version", Long.valueOf(getVersion()));
    return toStringHelper.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt) {
    paramInt = SafeParcelWriter.beginObjectHeader(paramParcel);
    SafeParcelWriter.writeString(paramParcel, 1, getName(), false);
    SafeParcelWriter.writeInt(paramParcel, 2, this.zzk);
    SafeParcelWriter.writeLong(paramParcel, 3, getVersion());
    SafeParcelWriter.finishObjectHeader(paramParcel, paramInt);
  }
}


/* Location:              /home/andrelcol/Desktop/securityDebt/onstar/meuProjeto.jar!/com/google/android/gms/common/Feature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */